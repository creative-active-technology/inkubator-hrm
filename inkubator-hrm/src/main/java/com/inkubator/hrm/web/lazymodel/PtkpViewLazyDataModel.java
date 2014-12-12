/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.model.PtkpViewModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
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
 * @author deni
 */
public class PtkpViewLazyDataModel extends LazyDataModel<PtkpViewModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PtkpViewLazyDataModel.class);
    private final EmpDataSearchParameter searchParameter;
    private final EmpDataService service;
    private List<PtkpViewModel> ptkpViewModelList = new ArrayList<>();
    private Integer jumlahData;

    public PtkpViewLazyDataModel(EmpDataSearchParameter searchParameter, EmpDataService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<PtkpViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("bioData");
                }
                ptkpViewModelList = service.getByParamForPtkpView(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalEmpDataByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(9);
        return ptkpViewModelList;
    }
    
    @Override
    public Object getRowKey(PtkpViewModel ptkpViewModel) {
        return ptkpViewModel.getId();
    }

    @Override
    public PtkpViewModel getRowData(String id) {
        for (PtkpViewModel ptkpViewModel : ptkpViewModelList) {
            if (id.equals(String.valueOf(ptkpViewModel.getId()))) {
                return ptkpViewModel;
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
