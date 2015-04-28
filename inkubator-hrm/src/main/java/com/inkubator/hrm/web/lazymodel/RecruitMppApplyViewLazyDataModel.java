/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;
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
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitMppApplyViewLazyDataModel extends LazyDataModel<RecruitMppApply> implements Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(RecruitMppApplyViewLazyDataModel.class);
    private final RecruitMppApplySearchParameter searchParameter;
    private final RecruitMppApplyService service;
    private List<RecruitMppApply> recruitMppApplyList = new ArrayList<>();
    private Integer jumlahData;

    public RecruitMppApplyViewLazyDataModel(RecruitMppApplySearchParameter searchParameter, RecruitMppApplyService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<RecruitMppApply> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("recruitMppApplyCode");
                }
                recruitMppApplyList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
         LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return recruitMppApplyList;
    }
    
    @Override
    public Object getRowKey(RecruitMppApply recruitMppApply) {
        return recruitMppApply.getId();
    }

    @Override
    public RecruitMppApply getRowData(String id) {
        for (RecruitMppApply recruitMppApply : recruitMppApplyList) {
            if (id.equals(String.valueOf(recruitMppApply.getId()))) {
                return recruitMppApply;
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