package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Dialect;
import com.inkubator.hrm.service.DialectService;
import com.inkubator.hrm.web.search.DialectSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class DialectLazyDataModel extends LazyDataModel<Dialect> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(DialectLazyDataModel.class);
    private final DialectSearchParameter dialectSearchParameter;
    private final DialectService dialectService;
    private List<Dialect> dialects = new ArrayList<>();
    private Integer total;

    public DialectLazyDataModel(DialectSearchParameter dialectSearchParameter, DialectService dialectService) {
        this.dialectSearchParameter = dialectSearchParameter;
        this.dialectService = dialectService;
    }

    @Override
    public List<Dialect> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	dialects = dialectService.getByParam(dialectSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(dialectService.getTotalByParam(dialectSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	dialects = dialectService.getByParam(dialectSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(dialectService.getTotalByParam(dialectSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	dialects = dialectService.getByParam(dialectSearchParameter, first, pageSize, Order.asc("dialectName"));
                total = Integer.parseInt(String.valueOf(dialectService.getTotalByParam(dialectSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return dialects;
    }

    @Override
    public Object getRowKey(Dialect dialect) {
        return dialect.getId();
    }

    @Override
    public Dialect getRowData(String id) {
        for (Dialect dialect : dialects) {
            if (id.equals(String.valueOf(dialect.getId()))) {
                return dialect;
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
