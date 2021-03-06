/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.model.ReportEmployeeEducationViewModel;
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
 * @author Deni
 */
public class ReportEmployeeEducationLazyDataModel extends LazyDataModel<ReportEmployeeEducationViewModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ReportEmployeeEducationLazyDataModel.class);
    private final List<Department> listDepartment;
    private final List<EducationLevel> listEducationLevel;
    private final EmpDataService service;
    private List<ReportEmployeeEducationViewModel> empDataList = new ArrayList<>();
    private Integer jumlahData;

    public ReportEmployeeEducationLazyDataModel(List<Department> listDepartment, List<EducationLevel> listEducationLevel, EmpDataService service) {
        this.listDepartment = listDepartment;
        this.listEducationLevel = listEducationLevel;
        this.service = service;
    }




    
    @Override
    public List<ReportEmployeeEducationViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("bioData");
                }
                empDataList = service.getAllDataByDepartementAndEducationWithHql(listDepartment, listEducationLevel,  first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalDataByDepartementAndEducation(listDepartment, listEducationLevel)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return empDataList;
    }
    
    @Override
    public Object getRowKey(ReportEmployeeEducationViewModel reportEmployeeEducationViewModel) {
        return reportEmployeeEducationViewModel.getId();
    }

    @Override
    public ReportEmployeeEducationViewModel getRowData(String id) {
        for (ReportEmployeeEducationViewModel reportEmployeeEducationViewModel : empDataList) {
            if (id.equals(String.valueOf(reportEmployeeEducationViewModel.getId()))) {
                return reportEmployeeEducationViewModel;
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
