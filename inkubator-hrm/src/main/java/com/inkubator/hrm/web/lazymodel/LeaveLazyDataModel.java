package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.web.search.LeaveSearchParameter;


/**
*
* @author rizkykojek
*/
public class LeaveLazyDataModel extends LazyDataModel<Leave> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LeaveLazyDataModel.class);
    private final LeaveSearchParameter parameter;
    private final LeaveService leaveService;
    private List<Leave> leaves = new ArrayList<>();
    private Integer total;

    public LeaveLazyDataModel(LeaveSearchParameter parameter, LeaveService leaveService) {
        this.parameter = parameter;
        this.leaveService = leaveService;
    }

    @Override
    public List<Leave> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        leaves = leaveService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(leaveService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return leaves;
    }

    @Override
    public Object getRowKey(Leave leave) {
        return leave.getId();
    }

    @Override
    public Leave getRowData(String id) {
        for (Leave leave : leaves) {
            if (id.equals(String.valueOf(leave.getId()))) {
                return leave;
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
