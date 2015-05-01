/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
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
public class RecruitmenSelectionSeriesDetailLazyDataModel extends LazyDataModel<RecruitmenSelectionSeriesDetail> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(RecruitmenSelectionSeriesDetailLazyDataModel.class);
    private final RecruitmenSelectionSeriesDetailService service;
    private final Long selectionSeriesId;
    private List<RecruitmenSelectionSeriesDetail> recruitSelectionDetailList = new ArrayList<>();
    private Integer jumlahData;

    public RecruitmenSelectionSeriesDetailLazyDataModel(RecruitmenSelectionSeriesDetailService service, Long selectionSeriesId) {
        this.service = service;
        this.selectionSeriesId = selectionSeriesId;
    }
    
    @Override
    public List<RecruitmenSelectionSeriesDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("recruitSelectionType");
                }
                recruitSelectionDetailList = service.getAllDataBySelectionSeriesId(selectionSeriesId, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalBySelectionSeriesId(selectionSeriesId)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return recruitSelectionDetailList;
    }
    
    @Override
    public Object getRowKey(RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail) {
        return recruitmenSelectionSeriesDetail.getId();
    }

    @Override
    public RecruitmenSelectionSeriesDetail getRowData(String id) {
        for (RecruitmenSelectionSeriesDetail recruitmenSelectionSeriesDetail : recruitSelectionDetailList) {
            if (id.equals(String.valueOf(recruitmenSelectionSeriesDetail.getId()))) {
                return recruitmenSelectionSeriesDetail;
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
