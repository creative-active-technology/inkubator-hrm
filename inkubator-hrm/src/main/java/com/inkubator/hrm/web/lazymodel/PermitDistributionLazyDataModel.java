/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.service.PermitDistributionService;
import com.inkubator.hrm.web.search.PermitDistributionSearchParameter;
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
public class PermitDistributionLazyDataModel extends LazyDataModel<PermitDistribution> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PermitDistributionLazyDataModel.class);
    private final PermitDistributionSearchParameter searchParameter;
    private final PermitDistributionService service;
    private List<PermitDistribution> permitDistributionList = new ArrayList<>();
    private Integer jumlahData;

    public PermitDistributionLazyDataModel(PermitDistributionSearchParameter searchParameter, PermitDistributionService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<PermitDistribution> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    permitDistributionList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPermitDistributionByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    permitDistributionList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalPermitDistributionByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                permitDistributionList = service.getByParamWithDetail(searchParameter, first, pageSize, Order.desc("empData"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalPermitDistributionByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return permitDistributionList;
    }
    
    @Override
    public Object getRowKey(PermitDistribution permitDistribution) {
        return permitDistribution.getId();
    }

    @Override
    public PermitDistribution getRowData(String id) {
        for (PermitDistribution permitDistribution : permitDistributionList) {
            if (id.equals(String.valueOf(permitDistribution.getId()))) {
                return permitDistribution;
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
