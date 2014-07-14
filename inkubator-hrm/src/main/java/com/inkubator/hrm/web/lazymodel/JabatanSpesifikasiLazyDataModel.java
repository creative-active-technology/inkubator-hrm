/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.service.JabatanSpesifikasiService;
import com.inkubator.hrm.web.search.JabatanSpesifikasiSearchParameter;
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
public class JabatanSpesifikasiLazyDataModel extends LazyDataModel<JabatanSpesifikasi> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(JabatanSpesifikasiLazyDataModel.class);
    private final JabatanSpesifikasiSearchParameter parameter;
    private final JabatanSpesifikasiService service;
    private List<JabatanSpesifikasi> listJabatan = new ArrayList<>();
    private Long specId;
    private Integer jumlahData;
    
    public JabatanSpesifikasiLazyDataModel(JabatanSpesifikasiSearchParameter parameter, JabatanSpesifikasiService service) {
        this.parameter = parameter;
        this.service = service;
    }
    
    public JabatanSpesifikasiLazyDataModel(JabatanSpesifikasiSearchParameter parameter, JabatanSpesifikasiService service, Long id) {
        this.parameter = parameter;
        this.service = service;
        this.specId = id;
    }
    
    @Override
    public List<JabatanSpesifikasi> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    listJabatan = service.getByJabatan(parameter, specId,first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalJabatanSpesifikasiByJabatan(parameter,specId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    listJabatan = service.getByJabatan(parameter, specId, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalJabatanSpesifikasiByJabatan(parameter, specId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                listJabatan = service.getByJabatan(parameter, specId, first, pageSize, Order.desc("specificationAbility"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalJabatanSpesifikasiByJabatan(parameter, specId)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return listJabatan;
    }
    
    @Override
    public Object getRowKey(JabatanSpesifikasi jabatanSpesifikasi) {
        return jabatanSpesifikasi.getId();
    }

    @Override
    public JabatanSpesifikasi getRowData(String id) {
        for (JabatanSpesifikasi jabatan : listJabatan) {
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
