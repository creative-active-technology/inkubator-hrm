/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.service.OverTimeDistributionService;
import com.inkubator.hrm.web.search.OverTimeDistributionSearchParameter;
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
public class OverTimeDistributionLazyDataModel extends LazyDataModel<OverTimeDistribution> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(OverTimeDistributionLazyDataModel.class);
    private final OverTimeDistributionSearchParameter searchParameter;
    private final OverTimeDistributionService service;
    private List<OverTimeDistribution> overTimeDistributionList = new ArrayList<>();
    private Integer jumlahData;

    public OverTimeDistributionLazyDataModel(OverTimeDistributionSearchParameter searchParameter, OverTimeDistributionService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<OverTimeDistribution> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    overTimeDistributionList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalOverTimeDistributionByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    overTimeDistributionList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalOverTimeDistributionByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                overTimeDistributionList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc("empData"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalOverTimeDistributionByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return overTimeDistributionList;
    }
    
    @Override
    public Object getRowKey(OverTimeDistribution overTimeDistribution) {
        return overTimeDistribution.getId();
    }

    @Override
    public OverTimeDistribution getRowData(String id) {
        for (OverTimeDistribution overTimeDistribution : overTimeDistributionList) {
            if (id.equals(String.valueOf(overTimeDistribution.getId()))) {
                return overTimeDistribution;
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
