package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.web.search.ProvinceSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class ProvinceLazyDataModel extends LazyDataModel<Province> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ProvinceLazyDataModel.class);
    private final ProvinceSearchParameter provinceSearchParameter;
    private final ProvinceService provinceService;
    private List<Province> provinces = new ArrayList<>();
    private Integer total;

    public ProvinceLazyDataModel(ProvinceSearchParameter provinceSearchParameter, ProvinceService provinceService) {
        this.provinceSearchParameter = provinceSearchParameter;
        this.provinceService = provinceService;
    }

    @Override
    public List<Province> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	provinces = provinceService.getByParam(provinceSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(provinceService.getTotalByParam(provinceSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	provinces = provinceService.getByParam(provinceSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(provinceService.getTotalByParam(provinceSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	provinces = provinceService.getByParam(provinceSearchParameter, first, pageSize, Order.asc("provinceName"));
                total = Integer.parseInt(String.valueOf(provinceService.getTotalByParam(provinceSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return provinces;
    }

    @Override
    public Object getRowKey(Province province) {
        return province.getId();
    }

    @Override
    public Province getRowData(String id) {
        for (Province province : provinces) {
            if (id.equals(String.valueOf(province.getId()))) {
                return province;
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
