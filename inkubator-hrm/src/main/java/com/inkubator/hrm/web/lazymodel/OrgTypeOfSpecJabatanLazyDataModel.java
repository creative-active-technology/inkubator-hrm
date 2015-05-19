/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.OrgTypeOfSpecJabatan;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.OrgTypeOfSpecJabatanService;
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
public class OrgTypeOfSpecJabatanLazyDataModel extends LazyDataModel<OrgTypeOfSpecJabatan> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(OrgTypeOfSpecJabatanLazyDataModel.class);
    private final Long id;
    private final OrgTypeOfSpecJabatanService service;
    private List<OrgTypeOfSpecJabatan> orgTypeOfSpecJabatanList = new ArrayList<>();
    private Integer jumlahData;

    public OrgTypeOfSpecJabatanLazyDataModel(Long id, OrgTypeOfSpecJabatanService service) {
        this.id = id;
        this.service = service;
    }
    
    @Override
    public List<OrgTypeOfSpecJabatan> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("orgTypeOfSpecList");
                }
                orgTypeOfSpecJabatanList = service.getAllDataByJabatanId(id, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalDataByJabatanId(id)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return orgTypeOfSpecJabatanList;
    }
    
    @Override
    public Object getRowKey(OrgTypeOfSpecJabatan orgTypeOfSpecJabatan) {
        return orgTypeOfSpecJabatan.getId();
    }

    @Override
    public OrgTypeOfSpecJabatan getRowData(String id) {
        for (OrgTypeOfSpecJabatan orgTypeOfSpecJabatan : orgTypeOfSpecJabatanList) {
            if (id.equals(String.valueOf(orgTypeOfSpecJabatan.getId()))) {
                return orgTypeOfSpecJabatan;
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
