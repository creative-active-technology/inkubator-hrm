package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.service.WtWorkingHourService;
import com.inkubator.hrm.web.search.WorkingHourSearchParameter;

/**
*
* @author rizkykojek
*/
public class WorkingHourLazyDataModel extends LazyDataModel<WtWorkingHour> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(WorkingHourLazyDataModel.class);
    private final WorkingHourSearchParameter parameter;
    private final WtWorkingHourService workingHourService;
    private List<WtWorkingHour> workingHours = new ArrayList<>();
    private Integer total;

    public WorkingHourLazyDataModel(WorkingHourSearchParameter parameter, WtWorkingHourService workingHourService) {
        this.parameter = parameter;
        this.workingHourService = workingHourService;
    }

    @Override
    public List<WtWorkingHour> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        workingHours = workingHourService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(workingHourService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return workingHours;
    }

    @Override
    public Object getRowKey(WtWorkingHour workingHour) {
        return workingHour.getId();
    }

    @Override
    public WtWorkingHour getRowData(String id) {
        for (WtWorkingHour workingHour : workingHours) {
            if (id.equals(String.valueOf(workingHour.getId()))) {
                return workingHour;
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
