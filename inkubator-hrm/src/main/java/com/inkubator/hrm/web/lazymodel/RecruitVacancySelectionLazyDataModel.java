/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitVacancySelection;
import com.inkubator.hrm.service.RecruitVacancySelectionService;
import com.inkubator.hrm.web.search.RecruitVacancySelectionSearchParameter;
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
public class RecruitVacancySelectionLazyDataModel extends LazyDataModel<RecruitVacancySelection> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(RecruitVacancySelectionLazyDataModel.class);
    private final RecruitVacancySelectionSearchParameter searchParameter;
    private final RecruitVacancySelectionService service;
    private List<RecruitVacancySelection> recruitVacancySelectionList = new ArrayList<>();
    private Integer jumlahData;

    public RecruitVacancySelectionLazyDataModel(RecruitVacancySelectionSearchParameter searchParameter, RecruitVacancySelectionService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<RecruitVacancySelection> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("code");
                }
                recruitVacancySelectionList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return recruitVacancySelectionList;
    }
    
    @Override
    public Object getRowKey(RecruitVacancySelection recruitVacancySelection) {
        return recruitVacancySelection.getId();
    }

    @Override
    public RecruitVacancySelection getRowData(String id) {
        for (RecruitVacancySelection recruitVacancySelection : recruitVacancySelectionList) {
            if (id.equals(String.valueOf(recruitVacancySelection.getId()))) {
                return recruitVacancySelection;
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
