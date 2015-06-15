package com.inkubator.hrm.web.lazymodel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.model.WorkingTimeDeviation;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;

/**
 *
 * @author WebGenX
 */
public class WorkingTimeDeviationLazyDataModel extends LazyDataModel<WorkingTimeDeviation> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(WorkingTimeDeviationLazyDataModel.class);
    private final TempAttendanceRealizationSearchParameter tempAttendanceRealizationSearchParameter;
    private final TempAttendanceRealizationService tempAttendanceRealizationService;
    private List<WorkingTimeDeviation> workingTimeDeviations = new ArrayList<>();
    private Integer totalData;

    public WorkingTimeDeviationLazyDataModel(TempAttendanceRealizationSearchParameter searchParameter, TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.tempAttendanceRealizationSearchParameter = searchParameter;
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

    @Override
    public List<WorkingTimeDeviation> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model "+sortField);
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    workingTimeDeviations = tempAttendanceRealizationService.getWorkingHourDeviation(tempAttendanceRealizationSearchParameter, first, pageSize, Order.asc(sortField));
                    totalData = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalWorkingHourDeviation(tempAttendanceRealizationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    workingTimeDeviations = tempAttendanceRealizationService.getWorkingHourDeviation(tempAttendanceRealizationSearchParameter, first, pageSize, Order.desc(sortField));
                    totalData = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalWorkingHourDeviation(tempAttendanceRealizationSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
// Change default type order if u want change from id to other entity variable
                workingTimeDeviations = tempAttendanceRealizationService.getWorkingHourDeviation(tempAttendanceRealizationSearchParameter, first, pageSize, Order.desc("id"));
                totalData = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalWorkingHourDeviation(tempAttendanceRealizationSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(totalData);
        return workingTimeDeviations;
    }

    @Override
    public Object getRowKey(WorkingTimeDeviation deviation) {
        return deviation.getEmpId();
    }

    @Override
    public WorkingTimeDeviation getRowData(String id) {
        for (WorkingTimeDeviation deviation : workingTimeDeviations) {
            if (id.equals(String.valueOf(deviation.getEmpId()))) {
                return deviation;
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
