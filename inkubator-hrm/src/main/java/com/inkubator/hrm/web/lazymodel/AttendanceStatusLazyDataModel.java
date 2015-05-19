package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.service.AttendanceStatusService;
import com.inkubator.hrm.web.search.AttendanceStatusSearchParamater;
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
 * @author Deni Husni FR
 */
public class AttendanceStatusLazyDataModel extends LazyDataModel<AttendanceStatus> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(AttendanceStatusLazyDataModel.class);
    private final AttendanceStatusSearchParamater attendanceStatusSearchParamater;
    private final AttendanceStatusService attendanceStatusService;
    private List<AttendanceStatus> attendanceStatuses = new ArrayList<>();
    private Integer total;

    public AttendanceStatusLazyDataModel(AttendanceStatusSearchParamater attendanceStatusSearchParamater, AttendanceStatusService attendanceStatusService) {
        this.attendanceStatusSearchParamater = attendanceStatusSearchParamater;
        this.attendanceStatusService = attendanceStatusService;
    }

    @Override
    public List<AttendanceStatus> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    attendanceStatuses = attendanceStatusService.getByParam(attendanceStatusSearchParamater, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(attendanceStatusService.getTotalAttendanceStatusyParam(attendanceStatusSearchParamater)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    attendanceStatuses = attendanceStatusService.getByParam(attendanceStatusSearchParamater, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(attendanceStatusService.getTotalAttendanceStatusyParam(attendanceStatusSearchParamater)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                attendanceStatuses = attendanceStatusService.getByParam(attendanceStatusSearchParamater, first, pageSize, Order.asc("code"));
                total = Integer.parseInt(String.valueOf(attendanceStatusService.getTotalAttendanceStatusyParam(attendanceStatusSearchParamater)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        setPageSize(pageSize);
        setRowCount(total);
        return attendanceStatuses;
    }

    @Override
    public Object getRowKey(AttendanceStatus religion) {
        return religion.getId();
    }

    @Override
    public AttendanceStatus getRowData(String id) {
        for (AttendanceStatus attendanceStatus : attendanceStatuses) {
            if (id.equals(String.valueOf(attendanceStatus.getId()))) {
                return attendanceStatus;
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
