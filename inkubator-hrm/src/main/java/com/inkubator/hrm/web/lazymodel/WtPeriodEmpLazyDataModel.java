/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;
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
 * @author Ahmad Mudzakkir Amal
 */
public class WtPeriodEmpLazyDataModel extends LazyDataModel<WtPeriode> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(WtPeriodEmpLazyDataModel.class);
    private final WtPeriodeSearchParameter periodeSearchParameter;
    private final WtPeriodeService wtPeriodeService;
    private List<WtPeriode> wtPeriodes = new ArrayList<>();
    private Integer jumlahData;

    public WtPeriodEmpLazyDataModel(WtPeriodeSearchParameter periodeSearchParameter, WtPeriodeService wtPeriodeService) {
        this.periodeSearchParameter = periodeSearchParameter;
        this.wtPeriodeService = wtPeriodeService;
    }

    @Override
    public List<WtPeriode> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    wtPeriodes = wtPeriodeService.getByParam(periodeSearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtPeriodeService.getTotalWtPeriodeByParam(periodeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    wtPeriodes = wtPeriodeService.getByParam(periodeSearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtPeriodeService.getTotalWtPeriodeByParam(periodeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                wtPeriodes = wtPeriodeService.getByParam(periodeSearchParameter, first, pageSize, Order.desc("tahun"));
                jumlahData = Integer.parseInt(String.valueOf(wtPeriodeService.getTotalWtPeriodeByParam(periodeSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
//            jumlahData = Integer.parseInt(String.valueOf(loginHistoryService.getTotalLoginHistoryByParam(loginHistorySearchParameter)));
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return wtPeriodes;
    }

    @Override
    public Object getRowKey(WtPeriode hrmRole) {
        return hrmRole.getId();
    }

    @Override
    public WtPeriode getRowData(String id) {
        for (WtPeriode hrmRole : wtPeriodes) {
            if (id.equals(String.valueOf(hrmRole.getId()))) {
                return hrmRole;
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
