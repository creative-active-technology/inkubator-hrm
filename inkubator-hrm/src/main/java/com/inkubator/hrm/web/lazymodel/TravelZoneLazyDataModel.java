package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.service.TravelZoneService;
import com.inkubator.hrm.web.search.TravelZoneSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class TravelZoneLazyDataModel extends LazyDataModel<TravelZone> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(TravelZoneLazyDataModel.class);
    private final TravelZoneSearchParameter travelZoneSearchParameter;
    private final TravelZoneService travelZoneService;
    private List<TravelZone> travelZones = new ArrayList<>();
    private Integer total;

    public TravelZoneLazyDataModel(TravelZoneSearchParameter travelZoneSearchParameter, TravelZoneService travelZoneService) {
        this.travelZoneSearchParameter = travelZoneSearchParameter;
        this.travelZoneService = travelZoneService;
    }

    @Override
    public List<TravelZone> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	travelZones = travelZoneService.getByParam(travelZoneSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(travelZoneService.getTotalByParam(travelZoneSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	travelZones = travelZoneService.getByParam(travelZoneSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(travelZoneService.getTotalByParam(travelZoneSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	travelZones = travelZoneService.getByParam(travelZoneSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(travelZoneService.getTotalByParam(travelZoneSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return travelZones;
    }

    @Override
    public Object getRowKey(TravelZone travelZone) {
        return travelZone.getId();
    }

    @Override
    public TravelZone getRowData(String id) {
        for (TravelZone travelZone : travelZones) {
            if (id.equals(String.valueOf(travelZone.getId()))) {
                return travelZone;
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
