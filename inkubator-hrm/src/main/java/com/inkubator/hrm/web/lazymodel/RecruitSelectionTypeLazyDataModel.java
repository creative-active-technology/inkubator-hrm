/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitAdvertisementCategory;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.web.search.RecruitSelectionTypeSearchParameter;
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
public class RecruitSelectionTypeLazyDataModel extends LazyDataModel<RecruitSelectionType> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(RecruitSelectionTypeLazyDataModel.class);
    private final RecruitSelectionTypeSearchParameter searchParameter;
    private final RecruitSelectionTypeService service;
    private List<RecruitSelectionType> recruitSelectionList = new ArrayList<>();
    private Integer jumlahData;

    public RecruitSelectionTypeLazyDataModel(RecruitSelectionTypeSearchParameter searchParameter, RecruitSelectionTypeService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<RecruitSelectionType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
    public Object getRowKey(RecruitSelectionType recruitSelectionType) {
        return recruitSelectionType.getId();
    }

    @Override
    public RecruitSelectionType getRowData(String id) {
        for (RecruitSelectionType recruitSelectionType : recruitSelectionList) {
            if (id.equals(String.valueOf(recruitSelectionType.getId()))) {
                return recruitSelectionType;
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
