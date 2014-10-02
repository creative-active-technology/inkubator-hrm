/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.service.NeracaCutiService;
import com.inkubator.hrm.web.search.NeracaCutiSearchParameter;
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
public class NeracaCutiLazyDataModel extends LazyDataModel<NeracaCuti> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(NeracaCutiLazyDataModel.class);
    private final NeracaCutiSearchParameter searchParameter;
    private final NeracaCutiService service;
    private List<NeracaCuti> neracaCutiList = new ArrayList<>();
    private Integer jumlahData;

    public NeracaCutiLazyDataModel(NeracaCutiSearchParameter searchParameter, NeracaCutiService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    } 
    
    @Override
    public List<NeracaCuti> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    neracaCutiList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalNeracaCutiByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    neracaCutiList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalNeracaCutiByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                neracaCutiList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc("kredit"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalNeracaCutiByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return neracaCutiList;
    }
    
    @Override
    public Object getRowKey(NeracaCuti neracaCuti) {
        return neracaCuti.getId();
    }

    @Override
    public NeracaCuti getRowData(String id) {
        for (NeracaCuti neracaCuti : neracaCutiList) {
            if (id.equals(String.valueOf(neracaCuti.getId()))) {
                return neracaCuti;
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
