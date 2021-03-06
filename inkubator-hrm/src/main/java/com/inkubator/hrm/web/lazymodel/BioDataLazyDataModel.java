/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.web.search.BioDataSearchParameter;
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
 * @author Deni Husni Fahri Rizal
 */
public class BioDataLazyDataModel extends LazyDataModel<BioData> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(BioDataLazyDataModel.class);
    private final BioDataSearchParameter bioDataSearchParameter;
    private final BioDataService bioDataService;
    private List<BioData> bioDatas = new ArrayList<>();
    private Integer jumlahData;
    
    public BioDataLazyDataModel(BioDataSearchParameter bioDataSearchParameter, BioDataService bioDataService) {
        this.bioDataSearchParameter = bioDataSearchParameter;
        this.bioDataService = bioDataService;
    }
    
    @Override
    public List<BioData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    bioDatas = bioDataService.getByParam(bioDataSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(bioDataService.getTotalByParam(bioDataSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    bioDatas = bioDataService.getByParam(bioDataSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(bioDataService.getTotalByParam(bioDataSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                bioDatas = bioDataService.getByParam(bioDataSearchParameter, first, pageSize, Order.desc("firstName"));
                jumlahData = Integer.parseInt(String.valueOf(bioDataService.getTotalByParam(bioDataSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return bioDatas;
    }
    
    @Override
    public Object getRowKey(BioData jabatan) {
        return jabatan.getId();
    }

    @Override
    public BioData getRowData(String id) {
        for (BioData jabatan : bioDatas) {
            if (id.equals(String.valueOf(jabatan.getId()))) {
                return jabatan;
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
