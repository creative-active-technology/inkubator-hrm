package com.inkubator.hrm.web.lazymodel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;

/**
 *
 * @author WebGenX
 */
public class TempAttendanceRealizationLazyDataModel extends LazyDataModel<TempAttendanceRealization> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(TempAttendanceRealizationLazyDataModel.class);
    private final TempAttendanceRealizationSearchParameter tempAttendanceRealizationSearchParameter;
    private final TempAttendanceRealizationService tempAttendanceRealizationService;
    private List<TempAttendanceRealization> tempAttendanceRealizations = new ArrayList<>();
    private Integer totalData;

    public TempAttendanceRealizationLazyDataModel(TempAttendanceRealizationSearchParameter searchParameter, TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.tempAttendanceRealizationSearchParameter = searchParameter;
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

    @Override
    public List<TempAttendanceRealization> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    tempAttendanceRealizations = tempAttendanceRealizationService.getByParam(tempAttendanceRealizationSearchParameter, first, pageSize, Order.asc(sortField));
                    totalData = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalTempAttendanceRealizationByParam(tempAttendanceRealizationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    tempAttendanceRealizations = tempAttendanceRealizationService.getByParam(tempAttendanceRealizationSearchParameter, first, pageSize, Order.desc(sortField));
                    totalData = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalTempAttendanceRealizationByParam(tempAttendanceRealizationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
// Change default type order if u want change from id to other entity variable
                tempAttendanceRealizations = tempAttendanceRealizationService.getByParam(tempAttendanceRealizationSearchParameter, first, pageSize, Order.desc("id"));
                totalData = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalTempAttendanceRealizationByParam(tempAttendanceRealizationSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(totalData);
        return tempAttendanceRealizations;
    }

    @Override
    public Object getRowKey(TempAttendanceRealization tempAttendanceRealization) {
        return tempAttendanceRealization.getId();
    }

    @Override
    public TempAttendanceRealization getRowData(String id) {
        for (TempAttendanceRealization tempAttendanceRealization : tempAttendanceRealizations) {
            if (id.equals(String.valueOf(tempAttendanceRealization.getId()))) {
                return tempAttendanceRealization;
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
