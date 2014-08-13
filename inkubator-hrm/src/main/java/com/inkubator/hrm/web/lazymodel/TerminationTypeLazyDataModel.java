package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TerminationType;
import com.inkubator.hrm.service.TerminationTypeService;
import com.inkubator.hrm.web.search.TerminationTypeSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class TerminationTypeLazyDataModel extends LazyDataModel<TerminationType> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(TerminationTypeLazyDataModel.class);
    private final TerminationTypeSearchParameter terminationTypeSearchParameter;
    private final TerminationTypeService terminationTypeService;
    private List<TerminationType> terminationTypes = new ArrayList<>();
    private Integer total;

    public TerminationTypeLazyDataModel(TerminationTypeSearchParameter terminationTypeSearchParameter, TerminationTypeService terminationTypeService) {
        this.terminationTypeSearchParameter = terminationTypeSearchParameter;
        this.terminationTypeService = terminationTypeService;
    }

    @Override
    public List<TerminationType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	terminationTypes = terminationTypeService.getByParam(terminationTypeSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(terminationTypeService.getTotalByParam(terminationTypeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	terminationTypes = terminationTypeService.getByParam(terminationTypeSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(terminationTypeService.getTotalByParam(terminationTypeSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	terminationTypes = terminationTypeService.getByParam(terminationTypeSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(terminationTypeService.getTotalByParam(terminationTypeSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return terminationTypes;
    }

    @Override
    public Object getRowKey(TerminationType terminationType) {
        return terminationType.getId();
    }

    @Override
    public TerminationType getRowData(String id) {
        for (TerminationType terminationType : terminationTypes) {
            if (id.equals(String.valueOf(terminationType.getId()))) {
                return terminationType;
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
