/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.service.impl.EmpCareerHistoryServiceImpl;
import com.inkubator.hrm.web.model.CareerTransitionInboxViewModel;
import com.inkubator.hrm.web.search.CareerTransitionInboxSearchParameter;

/**
 *
 * @author Deni
 */
public class CareerTransitionInboxLazyDataModel extends LazyDataModel<CareerTransitionInboxViewModel> implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(CareerTransitionInboxLazyDataModel.class);
    private final CareerTransitionInboxSearchParameter searchParameter;
    private final EmpCareerHistoryService service;
    private List<CareerTransitionInboxViewModel> careerTransitionInboxList = new ArrayList<>();
    private Integer jumlahData;

    public CareerTransitionInboxLazyDataModel(CareerTransitionInboxSearchParameter searchParameter, EmpCareerHistoryService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<CareerTransitionInboxViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("requestTime");
                }
                careerTransitionInboxList = service.getEntityEmpCareerHistoryInboxByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalgetEntityEmpCareerHistoryInboxByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return careerTransitionInboxList;
    }
    
    @Override
    public Object getRowKey(CareerTransitionInboxViewModel careerTransitionInboxViewModel) {
        return careerTransitionInboxViewModel.getApprovalActivityId();
    }

    @Override
    public CareerTransitionInboxViewModel getRowData(String id) {
        for (CareerTransitionInboxViewModel careerTransitionInboxViewModel : careerTransitionInboxList) {
            if (id.equals(String.valueOf(careerTransitionInboxViewModel.getApprovalActivityId()))) {
                return careerTransitionInboxViewModel;
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
