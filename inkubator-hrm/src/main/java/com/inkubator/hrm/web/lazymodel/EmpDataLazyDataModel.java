/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
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
 * @author Deni Husni FR
 */
public class EmpDataLazyDataModel extends LazyDataModel<EmpData> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EmpDataLazyDataModel.class);
    private final EmpDataSearchParameter empDataSearchParameter;
    private final EmpDataService empDataService;
    private List<EmpData> empDatadatas = new ArrayList<>();
    private Integer jumlah;

    public EmpDataLazyDataModel(EmpDataSearchParameter empDataSearchParameter, EmpDataService empDataService) {
        this.empDataSearchParameter = empDataSearchParameter;
        this.empDataService = empDataService;
    }

    @Override
    public List<EmpData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    empDatadatas = empDataService.getByParam(empDataSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(empDataService.getTotalEmpDataByParam(empDataSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    empDatadatas = empDataService.getByParam(empDataSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(empDataService.getTotalEmpDataByParam(empDataSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                empDatadatas = empDataService.getByParam(empDataSearchParameter, first, pageSize, Order.asc("nik"));
                jumlah = Integer.parseInt(String.valueOf(empDataService.getTotalEmpDataByParam(empDataSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        setPageSize(pageSize);
        setRowCount(jumlah);
        return empDatadatas;
    }

    @Override
    public Object getRowKey(EmpData empData) {
        return empData.getId();
    }

    @Override
    public EmpData getRowData(String id) {
        for (EmpData empData : empDatadatas) {
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
