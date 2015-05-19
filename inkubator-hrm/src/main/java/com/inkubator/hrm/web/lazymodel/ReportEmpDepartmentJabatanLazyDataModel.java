package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.search.ReportEmpDepartmentJabatanParameter;


/**
*
* @author Taufik
*/
public class ReportEmpDepartmentJabatanLazyDataModel extends LazyDataModel<EmpData> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ReportEmpDepartmentJabatanLazyDataModel.class);
    private final ReportEmpDepartmentJabatanParameter parameter;
    private final EmpDataService empDataService;
    private List<EmpData> empDatas = new ArrayList<>();
    private Integer total;

    public ReportEmpDepartmentJabatanLazyDataModel(ReportEmpDepartmentJabatanParameter parameter, EmpDataService empDataService) {
        this.parameter = parameter;
        this.empDataService = empDataService;
    }

    @Override
    public List<EmpData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("nik");
	        }
	        
	        empDatas = empDataService.getAllDataReportEmpDepartmentJabatanByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(empDataService.getTotalReportEmpDepartmentJabatanByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return empDatas;
    }

    @Override
    public Object getRowKey(EmpData empData) {
        return empData.getId();
    }

    @Override
    public EmpData getRowData(String id) {
        for (EmpData empData : empDatas) {
            if (id.equals(String.valueOf(empData.getId()))) {
                return empData;
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
