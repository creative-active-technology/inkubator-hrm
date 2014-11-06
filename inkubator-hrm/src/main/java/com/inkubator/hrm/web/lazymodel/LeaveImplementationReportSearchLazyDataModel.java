package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Leave;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.web.search.LeaveImplementationReportSearchParameter;


/**
*
* @author rizkykojek
*/
public class LeaveImplementationReportSearchLazyDataModel extends LazyDataModel<LeaveImplementation> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LeaveImplementationReportSearchLazyDataModel.class);
    private final LeaveImplementationReportSearchParameter parameter;
    private final LeaveImplementationService leaveImplementationService;
    private List<LeaveImplementation> leaveImplementations = new ArrayList<>();
    private List<String> activityNumbers = new ArrayList<>();
    private Integer total;
    private Long empDataId;

    public LeaveImplementationReportSearchLazyDataModel(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId, LeaveImplementationService leaveImplementationService) {
        this.parameter = parameter;
        this.leaveImplementationService = leaveImplementationService;
        this.activityNumbers = activityNumbers;
        this.empDataId = empDataId;
    }

    @Override
    public List<LeaveImplementation> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("numberFilling");
	        }
	        
	        leaveImplementations = leaveImplementationService.getReportByParam(parameter, activityNumbers, empDataId, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(leaveImplementationService.getReportTotalByParam(parameter, activityNumbers, empDataId)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return leaveImplementations;
    }

    @Override
    public Object getRowKey(LeaveImplementation leaveImplementation) {
        return leaveImplementation.getId();
    }

    @Override
    public LeaveImplementation getRowData(String id) {
        for (LeaveImplementation leaveImplementation : leaveImplementations) {
            if (id.equals(String.valueOf(leaveImplementation.getId()))) {
                return leaveImplementation;
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
