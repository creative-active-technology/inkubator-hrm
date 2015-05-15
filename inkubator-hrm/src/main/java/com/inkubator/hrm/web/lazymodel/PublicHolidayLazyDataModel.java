package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.PublicHoliday;
import com.inkubator.hrm.service.PublicHolidayService;

/**
 *
 * @author Taufik Hidayat
 */
public class PublicHolidayLazyDataModel extends LazyDataModel<PublicHoliday> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PublicHolidayLazyDataModel.class);
    private final String parameter;
    private final PublicHolidayService publicHolidayService;
    private List<PublicHoliday> publicHolidays = new ArrayList<>();
    private Integer total;

    public PublicHolidayLazyDataModel(String parameter, PublicHolidayService publicHolidayService) {
        this.parameter = parameter;
        this.publicHolidayService = publicHolidayService;
    }

    @Override
    public List<PublicHoliday> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
        try {
            Order orderable = null;
            if (sortField != null) {
                orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                orderable = Order.desc("leaveScheme");
            }

            publicHolidays = publicHolidayService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(publicHolidayService.getTotalPublicHolidayByParam(parameter)));
            LOGGER.info("Success Load Lazy data Model");

        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return publicHolidays;
    }

    @Override
    public Object getRowKey(PublicHoliday publicHoliday) {
        return publicHoliday.getId();
    }

    @Override
    public PublicHoliday getRowData(String id) {
        for (PublicHoliday publicHoliday : publicHolidays) {
            if (id.equals(String.valueOf(publicHoliday.getId()))) {
                return publicHoliday;
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
