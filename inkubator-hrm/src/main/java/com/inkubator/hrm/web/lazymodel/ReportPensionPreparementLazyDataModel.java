/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.model.ReportEmpPensionPreparationModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.ReportPensionPreparementSearchParameter;
import com.inkubator.hrm.web.search.ReportRekapJabatanEmpSearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class ReportPensionPreparementLazyDataModel extends LazyDataModel<ReportEmpPensionPreparationModel> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ReportPensionPreparementLazyDataModel.class);
    private final ReportPensionPreparementSearchParameter searchParameter;
    private final EmpDataService empDataService;
    private List<ReportEmpPensionPreparationModel> empDatadatas = new ArrayList<>();
    private Integer jumlah;

    public ReportPensionPreparementLazyDataModel(ReportPensionPreparementSearchParameter searchParameter, EmpDataService empDataService) {       
        this.searchParameter = searchParameter;
        this.empDataService = empDataService;
    }

    @Override
    public List<ReportEmpPensionPreparationModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
      
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    empDatadatas = empDataService.getReportPensionPreparementByParam(searchParameter.getListDepartmentId(),searchParameter.getListEmployeeTypeId(), searchParameter.getListEmployeeAges(), first, pageSize, Order.asc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(empDataService.getTotalReportPensionPreparementByParam(searchParameter.getListDepartmentId(),searchParameter.getListEmployeeTypeId(), searchParameter.getListEmployeeAges())));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    empDatadatas = empDataService.getReportPensionPreparementByParam(searchParameter.getListDepartmentId(),searchParameter.getListEmployeeTypeId(), searchParameter.getListEmployeeAges(), first, pageSize, Order.desc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(empDataService.getTotalReportPensionPreparementByParam(searchParameter.getListDepartmentId(),searchParameter.getListEmployeeTypeId(), searchParameter.getListEmployeeAges())));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                empDatadatas = empDataService.getReportPensionPreparementByParam(searchParameter.getListDepartmentId(),searchParameter.getListEmployeeTypeId(),searchParameter.getListEmployeeAges(), first, pageSize, Order.asc("nik"));
                jumlah = Integer.parseInt(String.valueOf(empDataService.getTotalReportPensionPreparementByParam(searchParameter.getListDepartmentId(),searchParameter.getListEmployeeTypeId(), searchParameter.getListEmployeeAges())));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        setPageSize(pageSize);
        setRowCount(jumlah);
        return empDatadatas;
    }

    @Override
    public Object getRowKey(ReportEmpPensionPreparationModel empData) {
        return empData.getId();
    }

    @Override
    public ReportEmpPensionPreparationModel getRowData(String id) {
        for (ReportEmpPensionPreparationModel empData : empDatadatas) {
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
