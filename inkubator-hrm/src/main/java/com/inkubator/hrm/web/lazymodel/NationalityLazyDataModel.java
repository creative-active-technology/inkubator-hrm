package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Nationality;
import com.inkubator.hrm.service.NationalityService;
import com.inkubator.hrm.web.search.NationalitySearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class NationalityLazyDataModel extends LazyDataModel<Nationality> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(NationalityLazyDataModel.class);
    private final NationalitySearchParameter nationalitySearchParameter;
    private final NationalityService nationalityService;
    private List<Nationality> nationalitys = new ArrayList<>();
    private Integer total;

    public NationalityLazyDataModel(NationalitySearchParameter nationalitySearchParameter, NationalityService nationalityService) {
        this.nationalitySearchParameter = nationalitySearchParameter;
        this.nationalityService = nationalityService;
    }

    @Override
    public List<Nationality> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	nationalitys = nationalityService.getByParam(nationalitySearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(nationalityService.getTotalByParam(nationalitySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	nationalitys = nationalityService.getByParam(nationalitySearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(nationalityService.getTotalByParam(nationalitySearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	nationalitys = nationalityService.getByParam(nationalitySearchParameter, first, pageSize, Order.asc("nationalityName"));
                total = Integer.parseInt(String.valueOf(nationalityService.getTotalByParam(nationalitySearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return nationalitys;
    }

    @Override
    public Object getRowKey(Nationality nationality) {
        return nationality.getId();
    }

    @Override
    public Nationality getRowData(String id) {
        for (Nationality nationality : nationalitys) {
            if (id.equals(String.valueOf(nationality.getId()))) {
                return nationality;
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
