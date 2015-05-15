/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesService;
import com.inkubator.hrm.web.search.RecruitmenSelectionSeriesSearchParameter;
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
public class RecruitmenSelectionSeriesLazyDataModel extends LazyDataModel<RecruitmenSelectionSeries> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(RecruitmenSelectionSeriesLazyDataModel.class);
    private final RecruitmenSelectionSeriesSearchParameter searchParameter;
    private final RecruitmenSelectionSeriesService service;
    private List<RecruitmenSelectionSeries> recruitSelectionList = new ArrayList<>();
    private Integer jumlahData;

    public RecruitmenSelectionSeriesLazyDataModel(RecruitmenSelectionSeriesSearchParameter searchParameter, RecruitmenSelectionSeriesService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<RecruitmenSelectionSeries> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                recruitSelectionList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return recruitSelectionList;
    }
    
    @Override
    public Object getRowKey(RecruitmenSelectionSeries recruitmenSelectionSeries) {
        return recruitmenSelectionSeries.getId();
    }

    @Override
    public RecruitmenSelectionSeries getRowData(String id) {
        for (RecruitmenSelectionSeries recruitmenSelectionSeries : recruitSelectionList) {
            if (id.equals(String.valueOf(recruitmenSelectionSeries.getId()))) {
                return recruitmenSelectionSeries;
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
