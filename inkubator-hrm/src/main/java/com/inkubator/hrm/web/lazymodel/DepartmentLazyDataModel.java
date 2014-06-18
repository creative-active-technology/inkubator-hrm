/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.web.search.DepartmentSearchParameter;
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
 * @author deniarianto
 */
public class DepartmentLazyDataModel extends LazyDataModel<Department> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(DepartmentLazyDataModel.class);
    private final DepartmentSearchParameter departmentSearchParameter;
    private final DepartmentService departmentService;
    private List<Department> departmentList = new ArrayList<>();
    private Integer jumlahData;

    public DepartmentLazyDataModel(DepartmentSearchParameter departmentSearchParameter, DepartmentService departmentService) {
        this.departmentSearchParameter = departmentSearchParameter;
        this.departmentService = departmentService;
    }
    
    @Override
    public List<Department> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    departmentList = departmentService.getByParam(departmentSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(departmentService.getTotalDepartmentByParam(departmentSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    departmentList = departmentService.getByParam(departmentSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(departmentService.getTotalDepartmentByParam(departmentSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                departmentList = departmentService.getByParam(departmentSearchParameter, first, pageSize, Order.desc("departmentName"));
                jumlahData = Integer.parseInt(String.valueOf(departmentService.getTotalDepartmentByParam(departmentSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return departmentList;
    }
    
    @Override
    public Object getRowKey(Department department) {
        return department.getId();
    }

    @Override
    public Department getRowData(String id) {
        for (Department department : departmentList) {
            if (id.equals(String.valueOf(department.getId()))) {
                return department;
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
