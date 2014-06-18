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
 * @author deniarianto
 */
public class JabatanLazyDataModel extends LazyDataModel<Jabatan> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(JabatanLazyDataModel.class);
    private final JabatanSearchParameter passwordHistorySearchParameter;
    private final JabatanService jabatanService;
    private List<Jabatan> passwordHistorys = new ArrayList<>();
    private Integer jumlahData;
    
    public JabatanLazyDataModel(JabatanSearchParameter passwordHistorySearchParameter, JabatanService jabatanService) {
        this.passwordHistorySearchParameter = passwordHistorySearchParameter;
        this.jabatanService = jabatanService;
    }
    
    @Override
    public List<Jabatan> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    passwordHistorys = jabatanService.getByParam(passwordHistorySearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(jabatanService.getTotalJabatanByParam(passwordHistorySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    passwordHistorys = jabatanService.getByParam(passwordHistorySearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(jabatanService.getTotalJabatanByParam(passwordHistorySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                passwordHistorys = jabatanService.getByParam(passwordHistorySearchParameter, first, pageSize, Order.desc("realName"));
                jumlahData = Integer.parseInt(String.valueOf(jabatanService.getTotalJabatanByParam(passwordHistorySearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return passwordHistorys;
    }
    
    @Override
    public Object getRowKey(Jabatan passwordHistory) {
        return passwordHistory.getId();
    }

    @Override
    public Jabatan getRowData(String id) {
        for (Jabatan passwordHistory : passwordHistorys) {
            if (id.equals(String.valueOf(passwordHistory.getId()))) {
                return passwordHistory;
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
