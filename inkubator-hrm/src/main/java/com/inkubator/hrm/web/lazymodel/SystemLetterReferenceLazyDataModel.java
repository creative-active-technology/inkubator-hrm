package com.inkubator.hrm.web.lazymodel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.search.SystemLetterReferenceSearchParameter;

/**
 *
 * @author Deni
 */
public class SystemLetterReferenceLazyDataModel extends LazyDataModel<SystemLetterReference> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(SystemLetterReferenceLazyDataModel.class);
    private final SystemLetterReferenceSearchParameter systemLetterReferenceSearchParameter;
    private final SystemLetterReferenceService systemLetterReferenceService;
    private List<SystemLetterReference> systemLetterReferences = new ArrayList<>();
    private Integer totalData;

    public SystemLetterReferenceLazyDataModel(SystemLetterReferenceSearchParameter searchParameter, SystemLetterReferenceService systemLetterReferenceService) {
        this.systemLetterReferenceSearchParameter = searchParameter;
        this.systemLetterReferenceService = systemLetterReferenceService;
    }

    @Override
    public List<SystemLetterReference> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    systemLetterReferences = systemLetterReferenceService.getByParam(systemLetterReferenceSearchParameter, first, pageSize, Order.asc(sortField));
                    totalData = Integer.parseInt(String.valueOf(systemLetterReferenceService.getTotalSystemLetterReferenceByParam(systemLetterReferenceSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    systemLetterReferences = systemLetterReferenceService.getByParam(systemLetterReferenceSearchParameter, first, pageSize, Order.desc(sortField));
                    totalData = Integer.parseInt(String.valueOf(systemLetterReferenceService.getTotalSystemLetterReferenceByParam(systemLetterReferenceSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
// Change default type order if u want change from id to other entity variable
                systemLetterReferences = systemLetterReferenceService.getByParam(systemLetterReferenceSearchParameter, first, pageSize, Order.desc("id"));
                totalData = Integer.parseInt(String.valueOf(systemLetterReferenceService.getTotalSystemLetterReferenceByParam(systemLetterReferenceSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(totalData);
        return systemLetterReferences;
    }

    @Override
    public Object getRowKey(SystemLetterReference systemLetterReference) {
        return systemLetterReference.getId();
    }

    @Override
    public SystemLetterReference getRowData(String id) {
        for (SystemLetterReference systemLetterReference : systemLetterReferences) {
            if (id.equals(String.valueOf(systemLetterReference.getId()))) {
                return systemLetterReference;
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
