package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.ReligionService;

/**
*
* @author rizkykojek
*/
public class ReligionLazyDataModel extends LazyDataModel<Religion> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ReligionLazyDataModel.class);
    private final String parameter;
    private final ReligionService religionService;
    private List<Religion> religions = new ArrayList<>();
    private Integer total;

    public ReligionLazyDataModel(String parameter, ReligionService religionService) {
        this.parameter = parameter;
        this.religionService = religionService;
    }

    @Override
    public List<Religion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        religions = religionService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(religionService.getTotalReligionByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return religions;
    }

    @Override
    public Object getRowKey(Religion religion) {
        return religion.getId();
    }

    @Override
    public Religion getRowData(String id) {
        for (Religion religion : religions) {
            if (id.equals(String.valueOf(religion.getId()))) {
                return religion;
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
