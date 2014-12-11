/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.service.TravelComponentCostRateService;
import com.inkubator.hrm.web.search.TravelComponentCostRateSearchParameter;
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
public class TravelComponentCostRateLazyDataModel extends LazyDataModel<TravelComponentCostRate> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(TravelComponentCostRateLazyDataModel.class);
    private final TravelComponentCostRateSearchParameter parameter;
    private final TravelComponentCostRateService service;
    private List<TravelComponentCostRate> travelComponentCostRateList = new ArrayList<>();
    private Integer jumlahData;

    public TravelComponentCostRateLazyDataModel(TravelComponentCostRateSearchParameter parameter, TravelComponentCostRateService service) {
        this.parameter = parameter;
        this.service = service;
    }
    
    @Override
    public List<TravelComponentCostRate> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                travelComponentCostRateList = service.getAllDataWithAllRelation(parameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalTravelComponentRateByParam(parameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return travelComponentCostRateList;
    }
    
    @Override
    public Object getRowKey(TravelComponentCostRate travelComponentCostRate) {
        return travelComponentCostRate.getId();
    }

    @Override
    public TravelComponentCostRate getRowData(String id) {
        for (TravelComponentCostRate travelComponentCostRate : travelComponentCostRateList) {
            if (id.equals(String.valueOf(travelComponentCostRate.getId()))) {
                return travelComponentCostRate;
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
