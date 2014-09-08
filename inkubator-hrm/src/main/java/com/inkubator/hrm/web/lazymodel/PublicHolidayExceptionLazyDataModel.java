package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.PublicHolidayException;
import com.inkubator.hrm.service.PublicHolidayExceptionService;
import com.inkubator.hrm.web.search.PublicHolidayExceptionSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class PublicHolidayExceptionLazyDataModel extends LazyDataModel<PublicHolidayException> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(PublicHolidayExceptionLazyDataModel.class);
    private final PublicHolidayExceptionSearchParameter publicHolidayExceptionSearchParameter;
    private final PublicHolidayExceptionService publicHolidayExceptionService;
    private List<PublicHolidayException> publicHolidayExceptions = new ArrayList<>();
    private Integer total;

    public PublicHolidayExceptionLazyDataModel(PublicHolidayExceptionSearchParameter publicHolidayExceptionSearchParameter, PublicHolidayExceptionService publicHolidayExceptionService) {
        this.publicHolidayExceptionSearchParameter = publicHolidayExceptionSearchParameter;
        this.publicHolidayExceptionService = publicHolidayExceptionService;
    }

    @Override
    public List<PublicHolidayException> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	publicHolidayExceptions = publicHolidayExceptionService.getByParam(publicHolidayExceptionSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(publicHolidayExceptionService.getTotalByParam(publicHolidayExceptionSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	publicHolidayExceptions = publicHolidayExceptionService.getByParam(publicHolidayExceptionSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(publicHolidayExceptionService.getTotalByParam(publicHolidayExceptionSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	publicHolidayExceptions = publicHolidayExceptionService.getByParam(publicHolidayExceptionSearchParameter, first, pageSize, Order.asc("empData"));
                total = Integer.parseInt(String.valueOf(publicHolidayExceptionService.getTotalByParam(publicHolidayExceptionSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return publicHolidayExceptions;
    }

    @Override
    public Object getRowKey(PublicHolidayException publicHolidayException) {
        return publicHolidayException.getId();
    }

    @Override
    public PublicHolidayException getRowData(String id) {
        for (PublicHolidayException publicHolidayException : publicHolidayExceptions) {
            if (id.equals(String.valueOf(publicHolidayException.getId()))) {
                return publicHolidayException;
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
