package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.service.MajorService;

/**
*
* @author Taufik Hidayat
*/
public class MajorLazyDataModel extends LazyDataModel<Major> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(MajorLazyDataModel.class);
    private final String parameter;
    private final MajorService majorService;
    private List<Major> majors = new ArrayList<>();
    private Integer total;

    public MajorLazyDataModel(String parameter, MajorService majorService) {
        this.parameter = parameter;
        this.majorService = majorService;
    }

    @Override
    public List<Major> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("majorName");
	        }
	        
	        majors = majorService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(majorService.getTotalMajorByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return majors;
    }

    @Override
    public Object getRowKey(Major major) {
        return major.getId();
    }

    @Override
    public Major getRowData(String id) {
        for (Major major : majors) {
            if (id.equals(String.valueOf(major.getId()))) {
                return major;
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
