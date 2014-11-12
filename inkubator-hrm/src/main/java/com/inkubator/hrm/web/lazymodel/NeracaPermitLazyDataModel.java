/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.NeracaPermit;
import com.inkubator.hrm.service.NeracaPermitService;
import com.inkubator.hrm.web.search.NeracaPermitSearchParameter;
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
 * @author Taufik
 */
public class NeracaPermitLazyDataModel extends LazyDataModel<NeracaPermit> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(NeracaPermitLazyDataModel.class);
    private final NeracaPermitSearchParameter searchParameter;
    private final NeracaPermitService service;
    private List<NeracaPermit> neracaPermitList = new ArrayList<>();
    private Integer jumlahData;

    public NeracaPermitLazyDataModel(NeracaPermitSearchParameter searchParameter, NeracaPermitService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    } 
    
    @Override
    public List<NeracaPermit> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    neracaPermitList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalNeracaPermitByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    neracaPermitList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalNeracaPermitByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                neracaPermitList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc("kredit"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalNeracaPermitByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return neracaPermitList;
    }
    
    @Override
    public Object getRowKey(NeracaPermit neracaPermit) {
        return neracaPermit.getId();
    }

    @Override
    public NeracaPermit getRowData(String id) {
        for (NeracaPermit neracaPermit : neracaPermitList) {
            if (id.equals(String.valueOf(neracaPermit.getId()))) {
                return neracaPermit;
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
