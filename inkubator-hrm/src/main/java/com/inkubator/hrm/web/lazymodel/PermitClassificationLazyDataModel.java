package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.service.PermitClassificationService;
import com.inkubator.hrm.web.search.PermitClassificationSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class PermitClassificationLazyDataModel extends LazyDataModel<PermitClassification> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(PermitClassificationLazyDataModel.class);
    private final PermitClassificationSearchParameter permitClassificationSearchParameter;
    private final PermitClassificationService permitClassificationService;
    private List<PermitClassification> permitClassifications = new ArrayList<>();
    private Integer total;

    public PermitClassificationLazyDataModel(PermitClassificationSearchParameter permitClassificationSearchParameter, PermitClassificationService permitClassificationService) {
        this.permitClassificationSearchParameter = permitClassificationSearchParameter;
        this.permitClassificationService = permitClassificationService;
    }

    @Override
    public List<PermitClassification> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	permitClassifications = permitClassificationService.getByParam(permitClassificationSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(permitClassificationService.getTotalByParam(permitClassificationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	permitClassifications = permitClassificationService.getByParam(permitClassificationSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(permitClassificationService.getTotalByParam(permitClassificationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	permitClassifications = permitClassificationService.getByParam(permitClassificationSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(permitClassificationService.getTotalByParam(permitClassificationSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return permitClassifications;
    }

    @Override
    public Object getRowKey(PermitClassification permitClassification) {
        return permitClassification.getId();
    }

    @Override
    public PermitClassification getRowData(String id) {
        for (PermitClassification permitClassification : permitClassifications) {
            if (id.equals(String.valueOf(permitClassification.getId()))) {
                return permitClassification;
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
