package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.AppraisalElement;
import com.inkubator.hrm.service.AppraisalElementService;

/**
*
* @author Taufik Hidayat
*/
public class AppraisalElementLazyDataModel extends LazyDataModel<AppraisalElement> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(AppraisalElementLazyDataModel.class);
    private final String parameter;
    private final AppraisalElementService appraisalElementService;
    private List<AppraisalElement> appraisalElements = new ArrayList<>();
    private Integer total;

    public AppraisalElementLazyDataModel(String parameter, AppraisalElementService appraisalElementService) {
        this.parameter = parameter;
        this.appraisalElementService = appraisalElementService;
    }

    @Override
    public List<AppraisalElement> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        appraisalElements = appraisalElementService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(appraisalElementService.getTotalAppraisalElementByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return appraisalElements;
    }

    @Override
    public Object getRowKey(AppraisalElement appraisalElement) {
        return appraisalElement.getId();
    }

    @Override
    public AppraisalElement getRowData(String id) {
        for (AppraisalElement appraisalElement : appraisalElements) {
            if (id.equals(String.valueOf(appraisalElement.getId()))) {
                return appraisalElement;
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
