/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.service.LeaveDistributionService;
import com.inkubator.hrm.web.search.LeaveDistributionSearchParameter;
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
public class LeaveDistributionLazyDataModel extends LazyDataModel<LeaveDistribution> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LeaveDistributionLazyDataModel.class);
    private final LeaveDistributionSearchParameter searchParameter;
    private final LeaveDistributionService service;
    private List<LeaveDistribution> leaveDistributionList = new ArrayList<>();
    private Integer jumlahData;

    public LeaveDistributionLazyDataModel(LeaveDistributionSearchParameter searchParameter, LeaveDistributionService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<LeaveDistribution> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        
            
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("bio.firstName");
                }
                leaveDistributionList = service.getByParamWithDetail(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalLeaveDistributionByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return leaveDistributionList;
    }
    
    @Override
    public Object getRowKey(LeaveDistribution leaveDistribution) {
        return leaveDistribution.getId();
    }

    @Override
    public LeaveDistribution getRowData(String id) {
        for (LeaveDistribution leaveDistribution : leaveDistributionList) {
            if (id.equals(String.valueOf(leaveDistribution.getId()))) {
                return leaveDistribution;
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
