/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.service.JabatanDeskripsiService;
import com.inkubator.hrm.web.search.JabatanDeskripsiSearcParameter;
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
public class JabatanDescriptionLazyDataModel extends LazyDataModel<JabatanDeskripsi> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(JabatanDescriptionLazyDataModel.class);
    private final JabatanDeskripsiSearcParameter jabatanSearchParameter;
    private final JabatanDeskripsiService jabatanService;
    private List<JabatanDeskripsi> jabatans = new ArrayList<>();
    private Integer jumlahData;
    
    public JabatanDescriptionLazyDataModel(JabatanDeskripsiSearcParameter jabatanSearchParameter, JabatanDeskripsiService jabatanService) {
        this.jabatanSearchParameter = jabatanSearchParameter;
        this.jabatanService = jabatanService;
    }
    
    @Override
    public List<JabatanDeskripsi> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
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
                jabatans = jabatanService.getByParam(jabatanSearchParameter, first, pageSize, Order.desc("kategoryTugas"));
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
    public Object getRowKey(JabatanDeskripsi jabatan) {
        return jabatan.getId();
    }

    @Override
    public JabatanDeskripsi getRowData(String id) {
        for (JabatanDeskripsi jabatan : jabatans) {
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
