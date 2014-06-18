/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
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
public class JabatanLazyDataModel extends LazyDataModel<Jabatan> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(JabatanLazyDataModel.class);
    private final JabatanSearchParameter jabatanSearchParameter;
    private final JabatanService jabatanService;
    private List<Jabatan> jabatans = new ArrayList<>();
    private Integer jumlahData;
    
    public JabatanLazyDataModel(JabatanSearchParameter jabatanSearchParameter, JabatanService jabatanService) {
        this.jabatanSearchParameter = jabatanSearchParameter;
        this.jabatanService = jabatanService;
    }
    
    @Override
    public List<Jabatan> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    jabatans = jabatanService.getByParam(jabatanSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(jabatanService.getTotalJabatanByParam(jabatanSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    jabatans = jabatanService.getByParam(jabatanSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(jabatanService.getTotalJabatanByParam(jabatanSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                jabatans = jabatanService.getByParam(jabatanSearchParameter, first, pageSize, Order.desc("code"));
                jumlahData = Integer.parseInt(String.valueOf(jabatanService.getTotalJabatanByParam(jabatanSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return jabatans;
    }
    
    @Override
    public Object getRowKey(Jabatan jabatan) {
        return jabatan.getId();
    }

    @Override
    public Jabatan getRowData(String id) {
        for (Jabatan jabatan : jabatans) {
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
