/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.service.WtHolidayService;
import com.inkubator.hrm.web.search.HolidaySearchParameter;
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
 * @author Deni Husni FR
 */
public class WtHolidayLazyModel extends LazyDataModel<WtHoliday> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(WtHolidayLazyModel.class);
    private final HolidaySearchParameter holidaySearchParameter;
    private final WtHolidayService wtHolidayService;
    private List<WtHoliday> wtHolidays = new ArrayList<>();
    private Integer jumlahData;

    public WtHolidayLazyModel(HolidaySearchParameter holidaySearchParameter, WtHolidayService wtHolidayService) {
        this.holidaySearchParameter = holidaySearchParameter;
        this.wtHolidayService = wtHolidayService;
    }

    @Override
    public List<WtHoliday> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    wtHolidays = wtHolidayService.getByParam(holidaySearchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtHolidayService.getTotalWtHolidayByParam(holidaySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    wtHolidays = wtHolidayService.getByParam(holidaySearchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(wtHolidayService.getTotalWtHolidayByParam(holidaySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                wtHolidays = wtHolidayService.getByParam(holidaySearchParameter, first, pageSize, Order.asc("holidayDate"));
                jumlahData = Integer.parseInt(String.valueOf(wtHolidayService.getTotalWtHolidayByParam(holidaySearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
//            jumlahData = Integer.parseInt(String.valueOf(loginHistoryService.getTotalLoginHistoryByParam(loginHistorySearchParameter)));
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return wtHolidays;
    }

    @Override
    public Object getRowKey(WtHoliday hrmRole) {
        return hrmRole.getId();
    }

    @Override
    public WtHoliday getRowData(String id) {
        for (WtHoliday wtHoliday : wtHolidays) {
            if (id.equals(String.valueOf(wtHoliday.getId()))) {
                return wtHoliday;
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
