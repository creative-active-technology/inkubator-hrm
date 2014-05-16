package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.service.EducationLevelService;

/**
*
* @author rizkykojek
*/
public class EducationLevelLazyDataModel extends LazyDataModel<EducationLevel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(EducationLevelLazyDataModel.class);
    private final String parameter;
    private final EducationLevelService educationLevelService;
    private List<EducationLevel> educationLevels = new ArrayList<>();
    private Integer total;

    public EducationLevelLazyDataModel(String parameter, EducationLevelService educationLevelService) {
        this.parameter = parameter;
        this.educationLevelService = educationLevelService;
    }

    @Override
    public List<EducationLevel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("level");
	        }
	        
	        educationLevels = educationLevelService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(educationLevelService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return educationLevels;
    }

    @Override
    public Object getRowKey(EducationLevel educationLevel) {
        return educationLevel.getId();
    }

    @Override
    public EducationLevel getRowData(String id) {
        for (EducationLevel educationLevel : educationLevels) {
            if (id.equals(String.valueOf(educationLevel.getId()))) {
                return educationLevel;
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
