package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.AppraisalDetail;
import com.inkubator.hrm.service.AppraisalDetailService;

/**
*
* @author Taufik Hidayat
*/
public class AppraisalDetailLazyDataModel extends LazyDataModel<AppraisalDetail> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(AppraisalDetailLazyDataModel.class);
    private final String parameter;
    private final AppraisalDetailService appraisalDetailService;
    private List<AppraisalDetail> appraisalDetails = new ArrayList<>();
    private Integer total;

    public AppraisalDetailLazyDataModel(String parameter, AppraisalDetailService appraisalDetailService) {
        this.parameter = parameter;
        this.appraisalDetailService = appraisalDetailService;
    }

    @Override
    public List<AppraisalDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        appraisalDetails = appraisalDetailService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(appraisalDetailService.getTotalAppraisalDetailByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return appraisalDetails;
    }

    @Override
    public Object getRowKey(AppraisalDetail appraisalDetail) {
        return appraisalDetail.getId();
    }

    @Override
    public AppraisalDetail getRowData(String id) {
        for (AppraisalDetail appraisalDetail : appraisalDetails) {
            if (id.equals(String.valueOf(appraisalDetail.getId()))) {
                return appraisalDetail;
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
