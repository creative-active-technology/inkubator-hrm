package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Race;
import com.inkubator.hrm.service.RaceService;
import com.inkubator.hrm.web.search.RaceSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class RaceLazyDataModel extends LazyDataModel<Race> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RaceLazyDataModel.class);
    private final RaceSearchParameter raceSearchParameter;
    private final RaceService raceService;
    private List<Race> races = new ArrayList<>();
    private Integer total;

    public RaceLazyDataModel(RaceSearchParameter raceSearchParameter, RaceService raceService) {
        this.raceSearchParameter = raceSearchParameter;
        this.raceService = raceService;
    }

    @Override
    public List<Race> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	races = raceService.getByParam(raceSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(raceService.getTotalByParam(raceSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	races = raceService.getByParam(raceSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(raceService.getTotalByParam(raceSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	races = raceService.getByParam(raceSearchParameter, first, pageSize, Order.asc("raceName"));
                total = Integer.parseInt(String.valueOf(raceService.getTotalByParam(raceSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return races;
    }

    @Override
    public Object getRowKey(Race race) {
        return race.getId();
    }

    @Override
    public Race getRowData(String id) {
        for (Race race : races) {
            if (id.equals(String.valueOf(race.getId()))) {
                return race;
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
