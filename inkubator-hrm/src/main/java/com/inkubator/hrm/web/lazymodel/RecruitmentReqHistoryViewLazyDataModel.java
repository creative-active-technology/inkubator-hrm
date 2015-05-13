/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.web.model.RecruitMppApplyViewModel;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;
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
public class RecruitmentReqHistoryViewLazyDataModel extends LazyDataModel<RecruitReqHistoryViewModel> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(RecruitmentReqHistoryViewLazyDataModel.class);
    private final RecruitReqHistorySearchParameter searchParameter;
    private final RecruitHireApplyService service;
    private List<RecruitReqHistoryViewModel> recruitReqHistoryViewModelsList = new ArrayList<>();
    private Integer jumlahData;

    public RecruitmentReqHistoryViewLazyDataModel(RecruitReqHistorySearchParameter searchParameter, RecruitHireApplyService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }

    @Override
    public List<RecruitReqHistoryViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        try {
            Order order = null;
            if (sortField != null) {
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("activityNumber");
            }
            recruitReqHistoryViewModelsList = service.getRecruitmentReqActivityByParam(searchParameter, first, pageSize, order);
            jumlahData = Integer.parseInt(String.valueOf(service.getTotalRecruitmentReqActivityByParam(searchParameter)));
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return recruitReqHistoryViewModelsList;
    }

    @Override
    public Object getRowKey(RecruitReqHistoryViewModel recruitReqHistoryViewModel) {
        return recruitReqHistoryViewModel.getApprovalActivityId();
    }

    @Override
    public RecruitReqHistoryViewModel getRowData(String id) {
        for (RecruitReqHistoryViewModel recruitReqHistoryViewModel : recruitReqHistoryViewModelsList) {
            if (id.equals(String.valueOf(recruitReqHistoryViewModel.getApprovalActivityId()))) {
                return recruitReqHistoryViewModel;
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
