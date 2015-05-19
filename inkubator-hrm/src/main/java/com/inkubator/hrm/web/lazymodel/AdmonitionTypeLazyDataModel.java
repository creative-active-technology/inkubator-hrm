package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.AdmonitionType;
import com.inkubator.hrm.service.AdmonitionTypeService;
import com.inkubator.hrm.web.search.AdmonitionTypeSearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Taufik Hidayat
 */
public class AdmonitionTypeLazyDataModel extends LazyDataModel<AdmonitionType> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(AdmonitionTypeLazyDataModel.class);
    private final AdmonitionTypeSearchParameter admonitionTypeSearchParameter;
    private final AdmonitionTypeService admonitionTypeService;
    private List<AdmonitionType> admonitionTypes = new ArrayList<>();
    private Integer total;

    public AdmonitionTypeLazyDataModel(AdmonitionTypeSearchParameter admonitionTypeSearchParameter, AdmonitionTypeService admonitionTypeService) {
        this.admonitionTypeSearchParameter = admonitionTypeSearchParameter;
        this.admonitionTypeService = admonitionTypeService;
    }

 
    @Override
    public List<AdmonitionType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    admonitionTypes = admonitionTypeService.getByParam(admonitionTypeSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(admonitionTypeService.getTotalByParam(admonitionTypeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    admonitionTypes = admonitionTypeService.getByParam(admonitionTypeSearchParameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(admonitionTypeService.getTotalByParam(admonitionTypeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                admonitionTypes = admonitionTypeService.getByParam(admonitionTypeSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(admonitionTypeService.getTotalByParam(admonitionTypeSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return admonitionTypes;
    }

    @Override
    public Object getRowKey(AdmonitionType admonitionType) {
        return admonitionType.getId();
    }

    @Override
    public AdmonitionType getRowData(String id) {
        for (AdmonitionType admonitionType : admonitionTypes) {
            if (id.equals(String.valueOf(admonitionType.getId()))) {
                return admonitionType;
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
