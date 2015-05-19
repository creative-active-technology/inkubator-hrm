package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.EmpCareerHistory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmpRotasi;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmpRotasiService;
import com.inkubator.hrm.web.search.ReportEmpMutationParameter;
import com.inkubator.hrm.web.search.ReportEmpWorkingGroupParameter;


/**
*
* @author Ahmad Mudzakkir Amal
*/
public class ReportEmpMutationLazyDataModel extends LazyDataModel<EmpCareerHistory> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ReportEmpMutationLazyDataModel.class);
    private final ReportEmpMutationParameter parameter;
    private final EmpCareerHistoryService empCareerHistoryService;
    private List<EmpCareerHistory> empCareerHistoryDataList = new ArrayList<EmpCareerHistory>();
    private Integer total;

    public ReportEmpMutationLazyDataModel(ReportEmpMutationParameter parameter, EmpCareerHistoryService empCareerHistoryService) {
        this.parameter = parameter;
        this.empCareerHistoryService = empCareerHistoryService;
    }

    @Override
    public List<EmpCareerHistory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    empCareerHistoryDataList = empCareerHistoryService.getByParamReport(parameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(empCareerHistoryService.getTotalEmpCareerHistoryDataByParamReport(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    empCareerHistoryDataList = empCareerHistoryService.getByParamReport(parameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(empCareerHistoryService.getTotalEmpCareerHistoryDataByParamReport(parameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                empCareerHistoryDataList = empCareerHistoryService.getByParamReport(parameter, first, pageSize, Order.asc("nik"));
                total = Integer.parseInt(String.valueOf(empCareerHistoryService.getTotalEmpCareerHistoryDataByParamReport(parameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }        
	       
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return empCareerHistoryDataList;
    }

    @Override
    public Object getRowKey(EmpCareerHistory empCareerHistory) {
        return empCareerHistory.getId();
    }

    @Override
    public EmpCareerHistory getRowData(String id) {
        for (EmpCareerHistory empCareerHistory : empCareerHistoryDataList) {
            if (id.equals(String.valueOf(empCareerHistory.getId()))) {
                return empCareerHistory;
            }
        }
        return null;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
