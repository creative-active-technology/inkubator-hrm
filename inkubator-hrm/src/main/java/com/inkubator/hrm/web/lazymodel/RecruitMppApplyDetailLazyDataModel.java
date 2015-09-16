/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.web.model.RecruitMppApplyDetailViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplyDetailSearchParameter;

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
public class RecruitMppApplyDetailLazyDataModel extends LazyDataModel<RecruitMppApplyDetailViewModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(RecruitMppApplyDetailLazyDataModel.class);
    private final RecruitMppApplyDetailSearchParameter searchParameter;
    private final RecruitMppApplyDetailService service;
    private List<RecruitMppApplyDetailViewModel> listData = new ArrayList<>();
    private Integer jumlahData;

    public RecruitMppApplyDetailLazyDataModel(RecruitMppApplyDetailSearchParameter searchParameter, RecruitMppApplyDetailService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<RecruitMppApplyDetailViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("jabatan.code");
                }
                listData = service.getAllDataByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalDataByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
         LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return listData;
    }
    
    @Override
    public Object getRowKey(RecruitMppApplyDetailViewModel recruitMppApplyDetailViewModel) {
        return recruitMppApplyDetailViewModel.getJabatanId();
    }

    @Override
    public RecruitMppApplyDetailViewModel getRowData(String id) {
        for (RecruitMppApplyDetailViewModel recruitMppApplyDetailViewModel : listData) {
            if (id.equals(String.valueOf(recruitMppApplyDetailViewModel.getId()))) {
                return recruitMppApplyDetailViewModel;
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