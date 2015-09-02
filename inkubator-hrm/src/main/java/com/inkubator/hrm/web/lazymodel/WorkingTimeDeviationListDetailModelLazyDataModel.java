package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.model.WorkingTimeDeviationListDetailModel;

public class WorkingTimeDeviationListDetailModelLazyDataModel extends LazyDataModel<WorkingTimeDeviationListDetailModel> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(WorkingTimeDeviationListDetailModelLazyDataModel.class);
    private final Long id;
    private final TempAttendanceRealizationService tempAttendanceRealizationService;
    private List<WorkingTimeDeviationListDetailModel> workingTimeDeviationListDetail = new ArrayList<>();
    private Integer totalData;

    public WorkingTimeDeviationListDetailModelLazyDataModel(Long id, TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.id = id;
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;

    }

    @Override
    public List<WorkingTimeDeviationListDetailModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model " + sortField);
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    workingTimeDeviationListDetail = tempAttendanceRealizationService.getAllDataOvertimeAndReadFingerByEmpDataId(id, first, pageSize, Order.asc(sortField));
                    totalData = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalOvertimeAndReadFingerByEmpDataId(id)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    workingTimeDeviationListDetail = tempAttendanceRealizationService.getAllDataOvertimeAndReadFingerByEmpDataId(id, first, pageSize, Order.asc(sortField));
                    totalData = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalOvertimeAndReadFingerByEmpDataId(id)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                // Change default type order if u want change from id to other entity variable
                workingTimeDeviationListDetail = tempAttendanceRealizationService.getAllDataOvertimeAndReadFingerByEmpDataId(id, first, pageSize, Order.desc("scheduleDate"));
                totalData = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalOvertimeAndReadFingerByEmpDataId(id)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
        setPageSize(pageSize);
        setRowCount(totalData);
        return workingTimeDeviationListDetail;
    }

    @Override
    public Object getRowKey(WorkingTimeDeviationListDetailModel deviation) {
        return deviation.getEmployeeId();
    }

    @Override
    public WorkingTimeDeviationListDetailModel getRowData(String id) {
        for (WorkingTimeDeviationListDetailModel deviation : workingTimeDeviationListDetail) {
            if (id.equals(String.valueOf(deviation.getEmployeeId()))) {
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
