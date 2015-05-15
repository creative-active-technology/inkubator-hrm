package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.web.search.CountrySearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class CountryLazyDataModel extends LazyDataModel<Country> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(CountryLazyDataModel.class);
    private final CountrySearchParameter countrySearchParameter;
    private final CountryService countryService;
    private List<Country> countrys = new ArrayList<>();
    private Integer total;

    public CountryLazyDataModel(CountrySearchParameter countrySearchParameter, CountryService countryService) {
        this.countrySearchParameter = countrySearchParameter;
        this.countryService = countryService;
    }

    @Override
    public List<Country> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	countrys = countryService.getByParam(countrySearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(countryService.getTotalByParam(countrySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	countrys = countryService.getByParam(countrySearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(countryService.getTotalByParam(countrySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	countrys = countryService.getByParam(countrySearchParameter, first, pageSize, Order.asc("countryName"));
                total = Integer.parseInt(String.valueOf(countryService.getTotalByParam(countrySearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return countrys;
    }

    @Override
    public Object getRowKey(Country country) {
        return country.getId();
    }

    @Override
    public Country getRowData(String id) {
        for (Country country : countrys) {
            if (id.equals(String.valueOf(country.getId()))) {
                return country;
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
