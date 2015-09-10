/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
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
public class RecruitMppApplyDetailLazyDataModel extends LazyDataModel<RecruitMppApplyDetail> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(RecruitMppApplyDetailLazyDataModel.class);
    private final RecruitMppApplyDetailSearchParameter searchParameter;
    private final RecruitMppApplyDetailService service;
    private List<RecruitMppApplyDetail> listData = new ArrayList<>();
    private Integer jumlahData;

    public RecruitMppApplyDetailLazyDataModel(RecruitMppApplyDetailSearchParameter searchParameter, RecruitMppApplyDetailService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<RecruitMppApplyDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
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
    public Object getRowKey(RecruitMppApplyDetail recruitMppApplyDetail) {
        return recruitMppApplyDetail.getId();
    }

    @Override
    public RecruitMppApplyDetail getRowData(String id) {
        for (RecruitMppApplyDetail recruitMppApplyDetail : listData) {
            if (id.equals(String.valueOf(recruitMppApplyDetail.getId()))) {
                return recruitMppApplyDetail;
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