package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;
import com.inkubator.hrm.service.WtEmpCorrectionAttendanceService;
import com.inkubator.hrm.web.search.EmpCorrectionAttendanceSearchParameter;


/**
*
* @author rizkykojek
*/
public class EmpCorrectionAttendanceLazyDataModel extends LazyDataModel<WtEmpCorrectionAttendance> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(EmpCorrectionAttendanceLazyDataModel.class);
    private final EmpCorrectionAttendanceSearchParameter parameter;
    private final WtEmpCorrectionAttendanceService empCorrectionAttendanceService;
    private List<WtEmpCorrectionAttendance> list = new ArrayList<>();
    private Integer total;

    public EmpCorrectionAttendanceLazyDataModel(EmpCorrectionAttendanceSearchParameter parameter, WtEmpCorrectionAttendanceService empCorrectionAttendanceService) {
        this.parameter = parameter;
        this.empCorrectionAttendanceService = empCorrectionAttendanceService;
    }

    @Override
    public List<WtEmpCorrectionAttendance> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        list = empCorrectionAttendanceService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(empCorrectionAttendanceService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }

    @Override
    public Object getRowKey(WtEmpCorrectionAttendance entity) {
        return entity.getId();
    }

    @Override
    public WtEmpCorrectionAttendance getRowData(String id) {
        for (WtEmpCorrectionAttendance entity : list) {
            if (id.equals(String.valueOf(entity.getId()))) {
                return entity;
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
