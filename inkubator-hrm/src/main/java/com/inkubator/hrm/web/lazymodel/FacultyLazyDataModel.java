package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.service.FacultyService;
import com.inkubator.hrm.web.search.FacultySearchParameter;

/**
*
* @author Taufik Hidayat
*/
public class FacultyLazyDataModel extends LazyDataModel<Faculty> implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(FacultyLazyDataModel.class);
    private final FacultySearchParameter searchParameter;
    private final FacultyService facultyService;
    private List<Faculty> facultys = new ArrayList<>();
    private Integer total;

    public FacultyLazyDataModel(FacultySearchParameter searchParameter, FacultyService facultyService) {
        this.searchParameter = searchParameter;
        this.facultyService = facultyService;
    }

    @Override
    public List<Faculty> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        facultys = facultyService.getByParam(searchParameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(facultyService.getTotalFacultyByParam(searchParameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return facultys;
    }

    @Override
    public Object getRowKey(Faculty faculty) {
        return faculty.getId();
    }

    @Override
    public Faculty getRowData(String id) {
        for (Faculty faculty : facultys) {
            if (id.equals(String.valueOf(faculty.getId()))) {
                return faculty;
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
