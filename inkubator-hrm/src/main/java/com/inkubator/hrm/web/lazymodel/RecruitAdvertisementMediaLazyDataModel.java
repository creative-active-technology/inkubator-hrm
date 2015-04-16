/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitAdvertisementCategory;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.service.RecruitAdvertisementMediaService;
import com.inkubator.hrm.web.search.RecruitAdvertisementMediaSearchParameter;
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
public class RecruitAdvertisementMediaLazyDataModel extends LazyDataModel<RecruitAdvertisementMedia> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(RecruitAdvertisementMediaLazyDataModel.class);
    private final RecruitAdvertisementMediaSearchParameter searchParameter;
    private final RecruitAdvertisementMediaService service;
    private List<RecruitAdvertisementMedia> recruitAdvertisementMediaList = new ArrayList<>();
    private Integer jumlahData;

    public RecruitAdvertisementMediaLazyDataModel(RecruitAdvertisementMediaSearchParameter searchParameter, RecruitAdvertisementMediaService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<RecruitAdvertisementMedia> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                recruitAdvertisementMediaList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return recruitAdvertisementMediaList;
    }
    
    @Override
    public Object getRowKey(RecruitAdvertisementMedia recruitAdvertisementMedia) {
        return recruitAdvertisementMedia.getId();
    }

    @Override
    public RecruitAdvertisementMedia getRowData(String id) {
        for (RecruitAdvertisementMedia recruitAdvertisementMedia : recruitAdvertisementMediaList) {
            if (id.equals(String.valueOf(recruitAdvertisementMedia.getId()))) {
                return recruitAdvertisementMedia;
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
