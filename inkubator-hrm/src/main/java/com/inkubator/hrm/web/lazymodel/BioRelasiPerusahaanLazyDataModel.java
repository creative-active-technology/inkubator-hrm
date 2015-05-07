/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.BioPotensiSwot;
import com.inkubator.hrm.entity.BioRelasiPerusahaan;
import com.inkubator.hrm.service.BioRelasiPerusahaanService;
import com.inkubator.hrm.web.search.BioRelasiPerusahaanSearchParameter;
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
public class BioRelasiPerusahaanLazyDataModel extends LazyDataModel<BioRelasiPerusahaan> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(BioRelasiPerusahaanLazyDataModel.class);
    private final BioRelasiPerusahaanSearchParameter searchParameter;
    private final BioRelasiPerusahaanService service;
    private List<BioRelasiPerusahaan> bioRelasiPerusahaanList = new ArrayList<>();
    private Integer jumlahData;

    public BioRelasiPerusahaanLazyDataModel(BioRelasiPerusahaanSearchParameter searchParameter, BioRelasiPerusahaanService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<BioRelasiPerusahaan> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                bioRelasiPerusahaanList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return bioRelasiPerusahaanList;
    }
    
    @Override
    public Object getRowKey(BioRelasiPerusahaan bioRelasiPerusahaan) {
        return bioRelasiPerusahaan.getId();
    }

    @Override
    public BioRelasiPerusahaan getRowData(String id) {
        for (BioRelasiPerusahaan bioRelasiPerusahaan : bioRelasiPerusahaanList) {
            if (id.equals(String.valueOf(bioRelasiPerusahaan.getId()))) {
                return bioRelasiPerusahaan;
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
