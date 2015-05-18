package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TravelComponent;
import com.inkubator.hrm.service.TravelComponentService;
import com.inkubator.hrm.web.search.TravelComponentSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class TravelComponentLazyDataModel extends LazyDataModel<TravelComponent> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(TravelComponentLazyDataModel.class);
    private final TravelComponentSearchParameter travelComponentSearchParameter;
    private final TravelComponentService travelComponentService;
    private List<TravelComponent> travelComponents = new ArrayList<>();
    private Integer total;

    public TravelComponentLazyDataModel(TravelComponentSearchParameter travelComponentSearchParameter, TravelComponentService travelComponentService) {
        this.travelComponentSearchParameter = travelComponentSearchParameter;
        this.travelComponentService = travelComponentService;
    }

    @Override
    public List<TravelComponent> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	travelComponents = travelComponentService.getByParam(travelComponentSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(travelComponentService.getTotalByParam(travelComponentSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	travelComponents = travelComponentService.getByParam(travelComponentSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(travelComponentService.getTotalByParam(travelComponentSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	travelComponents = travelComponentService.getByParam(travelComponentSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(travelComponentService.getTotalByParam(travelComponentSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return travelComponents;
    }

    @Override
    public Object getRowKey(TravelComponent travelComponent) {
        return travelComponent.getId();
    }

    @Override
    public TravelComponent getRowData(String id) {
        for (TravelComponent travelComponent : travelComponents) {
            if (id.equals(String.valueOf(travelComponent.getId()))) {
                return travelComponent;
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
