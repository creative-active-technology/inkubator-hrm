package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.service.TravelTypeService;
import com.inkubator.hrm.web.search.TravelTypeSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class TravelTypeLazyDataModel extends LazyDataModel<TravelType> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(TravelTypeLazyDataModel.class);
    private final TravelTypeSearchParameter travelTypeSearchParameter;
    private final TravelTypeService travelTypeService;
    private List<TravelType> travelTypes = new ArrayList<>();
    private Integer total;

    public TravelTypeLazyDataModel(TravelTypeSearchParameter travelTypeSearchParameter, TravelTypeService travelTypeService) {
        this.travelTypeSearchParameter = travelTypeSearchParameter;
        this.travelTypeService = travelTypeService;
    }

    @Override
    public List<TravelType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	travelTypes = travelTypeService.getByParam(travelTypeSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(travelTypeService.getTotalByParam(travelTypeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	travelTypes = travelTypeService.getByParam(travelTypeSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(travelTypeService.getTotalByParam(travelTypeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	travelTypes = travelTypeService.getByParam(travelTypeSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(travelTypeService.getTotalByParam(travelTypeSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return travelTypes;
    }

    @Override
    public Object getRowKey(TravelType travelType) {
        return travelType.getId();
    }

    @Override
    public TravelType getRowData(String id) {
        for (TravelType travelType : travelTypes) {
            if (id.equals(String.valueOf(travelType.getId()))) {
                return travelType;
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
