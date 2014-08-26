/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.EmpRotasi;
import com.inkubator.hrm.service.EmpRotasiService;
import com.inkubator.hrm.web.search.EmpRotasiSearchParameter;
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
public class EmpRotasiLazyDataModel extends LazyDataModel<EmpRotasi> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EmpRotasiLazyDataModel.class);
    private final EmpRotasiSearchParameter empRotasiSearchParameter;
    private final EmpRotasiService empRotasiService;
    private List<EmpRotasi> empDatadatas = new ArrayList<>();
    private Integer jumlah;

    public EmpRotasiLazyDataModel(EmpRotasiSearchParameter empRotasiSearchParameter, EmpRotasiService empRotasiService) {
        this.empRotasiSearchParameter = empRotasiSearchParameter;
        this.empRotasiService = empRotasiService;
    }

    @Override
    public List<EmpRotasi> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    empDatadatas = empRotasiService.getByParam(empRotasiSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(empRotasiService.getTotalEmpDataByParam(empRotasiSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    empDatadatas = empRotasiService.getByParam(empRotasiSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(empRotasiService.getTotalEmpDataByParam(empRotasiSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                empDatadatas = empRotasiService.getByParam(empRotasiSearchParameter, first, pageSize, Order.asc("empData"));
                jumlah = Integer.parseInt(String.valueOf(empRotasiService.getTotalEmpDataByParam(empRotasiSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        setPageSize(pageSize);
        setRowCount(jumlah);
        return empDatadatas;
    }

    @Override
    public Object getRowKey(EmpRotasi empData) {
        return empData.getId();
    }

    @Override
    public EmpRotasi getRowData(String id) {
        for (EmpRotasi empData : empDatadatas) {
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
