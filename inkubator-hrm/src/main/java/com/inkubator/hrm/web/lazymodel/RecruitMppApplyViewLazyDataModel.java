/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.web.model.RecruitMppApplyViewModel;
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
public class RecruitMppApplyViewLazyDataModel extends LazyDataModel<RecruitMppApplyViewModel> implements Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(RecruitMppApplyViewLazyDataModel.class);
    private final RecruitMppApplySearchParameter searchParameter;
    private final RecruitMppApplyService service;
    private List<RecruitMppApplyViewModel> recruitMppApplyViewModelList = new ArrayList<>();
    private Integer jumlahData;

    public RecruitMppApplyViewLazyDataModel(RecruitMppApplySearchParameter searchParameter, RecruitMppApplyService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<RecruitMppApplyViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("recruitMppApplyCode");
                }
                recruitMppApplyViewModelList = service.getUndisbursedActivityByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalUndisbursedActivityByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
         LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return recruitMppApplyViewModelList;
    }
    
    @Override
    public Object getRowKey(RecruitMppApplyViewModel RecruitMppApplyViewModel) {
        return RecruitMppApplyViewModel.getApprovalActivityId();
    }

    @Override
    public RecruitMppApplyViewModel getRowData(String id) {
        for (RecruitMppApplyViewModel recruitMppApplyViewModel : recruitMppApplyViewModelList) {
            if (id.equals(String.valueOf(recruitMppApplyViewModel.getApprovalActivityId()))) {
                return recruitMppApplyViewModel;
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