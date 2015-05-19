package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.web.search.CitySearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class CityLazyDataModel extends LazyDataModel<City> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(CityLazyDataModel.class);
    private final CitySearchParameter citySearchParameter;
    private final CityService cityService;
    private List<City> citys = new ArrayList<>();
    private Integer total;

    public CityLazyDataModel(CitySearchParameter citySearchParameter, CityService cityService) {
        this.citySearchParameter = citySearchParameter;
        this.cityService = cityService;
    }

    @Override
    public List<City> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	citys = cityService.getByParam(citySearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(cityService.getTotalByParam(citySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    citys = cityService.getByParam(citySearchParameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(cityService.getTotalByParam(citySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	citys = cityService.getByParam(citySearchParameter, first, pageSize, Order.asc("cityName"));
                total = Integer.parseInt(String.valueOf(cityService.getTotalByParam(citySearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return citys;
    }

    @Override
    public Object getRowKey(City city) {
        return city.getId();
    }

    @Override
    public City getRowData(String id) {
        for (City city : citys) {
            if (id.equals(String.valueOf(city.getId()))) {
                return city;
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
