/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitAdvertisementCategory;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.RecruitAdvertisementCategoryService;
import com.inkubator.hrm.web.search.RecruitAdvertisementCategorySearchParameter;
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
public class RecruitAdvertisementCategoryLazyDataModel extends LazyDataModel<RecruitAdvertisementCategory> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(RecruitAdvertisementCategoryLazyDataModel.class);
    private final RecruitAdvertisementCategorySearchParameter searchParameter;
    private final RecruitAdvertisementCategoryService service;
    private List<RecruitAdvertisementCategory> recruitAdvertisementCategoryList = new ArrayList<>();
    private Integer jumlahData;

    public RecruitAdvertisementCategoryLazyDataModel(RecruitAdvertisementCategorySearchParameter searchParameter, RecruitAdvertisementCategoryService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<RecruitAdvertisementCategory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                recruitAdvertisementCategoryList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return recruitAdvertisementCategoryList;
    }
    
    @Override
    public Object getRowKey(RecruitAdvertisementCategory recruitAdvertisementCategory) {
        return recruitAdvertisementCategory.getId();
    }

    @Override
    public RecruitAdvertisementCategory getRowData(String id) {
        for (RecruitAdvertisementCategory recruitAdvertisementCategory : recruitAdvertisementCategoryList) {
            if (id.equals(String.valueOf(recruitAdvertisementCategory.getId()))) {
                return recruitAdvertisementCategory;
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
