package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.service.ImplementationOfOverTimeService;
import com.inkubator.hrm.web.model.OvertimeImplSearchingModel;


/**
*
* @author Ahmad Mudzakkir Amal
*/
public class OvertimeImplSearchingLazyDataModel extends LazyDataModel<ImplementationOfOverTime> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(OvertimeImplSearchingLazyDataModel.class);
    private final OvertimeImplSearchingModel overtimeImplSearchingModel;
    private final ImplementationOfOverTimeService implementationOfOverTimeService;
    private List<ImplementationOfOverTime> listImplementationOfOvertime = new ArrayList<>();
    private Integer total;

    public OvertimeImplSearchingLazyDataModel(OvertimeImplSearchingModel overtimeImplSearchingModel, ImplementationOfOverTimeService implementationOfOverTimeService) {
        this.overtimeImplSearchingModel = overtimeImplSearchingModel;
        this.implementationOfOverTimeService = implementationOfOverTimeService;
    }

    @Override
    public List<ImplementationOfOverTime> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        listImplementationOfOvertime = implementationOfOverTimeService.getListSearchByParam(overtimeImplSearchingModel, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(implementationOfOverTimeService.getTotalListSearchByParam(overtimeImplSearchingModel)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return listImplementationOfOvertime;
    }

    @Override
    public Object getRowKey(ImplementationOfOverTime implementationOfOverTime) {
        return implementationOfOverTime.getId();
    }

    @Override
    public ImplementationOfOverTime getRowData(String id) {
        for (ImplementationOfOverTime implementationOfOverTime : listImplementationOfOvertime) {
            if (id.equals(String.valueOf(implementationOfOverTime.getId()))) {
                return implementationOfOverTime;
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
