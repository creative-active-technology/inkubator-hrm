/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.KlasifikasiKerjaLevel;
import com.inkubator.hrm.service.KlasifikasiKerjaLevelService;
import com.inkubator.hrm.web.search.KlasifikasiKerjaLevelSearchParameter;
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
 * @author EKA
 */
public class KlasifikasiKerjaLevelLazyDataModel extends LazyDataModel<KlasifikasiKerjaLevel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(KlasifikasiKerjaLevelLazyDataModel.class);
    private final KlasifikasiKerjaLevelSearchParameter searchParameter;
    private final KlasifikasiKerjaLevelService service;
    private List<KlasifikasiKerjaLevel> klasifikasiKerjaLevelList = new ArrayList<>();
    private Integer jumlah;
    
    public KlasifikasiKerjaLevelLazyDataModel(KlasifikasiKerjaLevelSearchParameter searchParameter, KlasifikasiKerjaLevelService service){
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    public List<KlasifikasiKerjaLevel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step loas lazy data model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("name");
            }
            klasifikasiKerjaLevelList = service.getByParam(searchParameter, first, pageSize, order);
            jumlah = Integer.parseInt(String.valueOf(service.getTotalKlasifikasiKerjaLevelByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("error", ex);
        }
        LOGGER.info("Success load lazy data model");
        
        setPageSize(pageSize);
        setRowCount(jumlah);
        return klasifikasiKerjaLevelList;
    }
    
    @Override
    public Object getRowKey(KlasifikasiKerjaLevel klasifikasiKerjaLevel){
        return klasifikasiKerjaLevel.getId();
    }
    
    @Override
    public KlasifikasiKerjaLevel getRowData(String id){
        for(KlasifikasiKerjaLevel klasifikasiKerjaLevel : klasifikasiKerjaLevelList){
            if(id.equals(String.valueOf(klasifikasiKerjaLevel.getId())));
            return klasifikasiKerjaLevel;
        }
        return null;
    }
    
    @Override
    public void setRowIndex(int rowIndex){
        if(rowIndex == -1 || getPageSize() == 0){
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
