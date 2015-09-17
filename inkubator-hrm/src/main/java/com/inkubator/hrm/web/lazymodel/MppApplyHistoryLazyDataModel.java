package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.web.model.MppApplyHistoryViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;


/**
*
* @author rizkykojek
*/
public class MppApplyHistoryLazyDataModel extends LazyDataModel<MppApplyHistoryViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(MppApplyHistoryLazyDataModel.class);
    private final RecruitMppApplySearchParameter parameter;
    private final RecruitMppApplyService service;
    private List<MppApplyHistoryViewModel> list = new ArrayList<>();
    private Integer total;

    public MppApplyHistoryLazyDataModel(RecruitMppApplySearchParameter parameter, RecruitMppApplyService service) {
        this.parameter = parameter;
        this.service = service;
    }

    @Override
    public List<MppApplyHistoryViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("approvalActivity.created_time");
	        }
	       
	        list = service.getAllDataMppApplyHistoryByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(service.getTotalMppApplyHistoryByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }

    @Override
    public Object getRowKey(MppApplyHistoryViewModel model) {
        return model.getApprovalActivityId();
    }

    @Override
    public MppApplyHistoryViewModel getRowData(String id) {
        for (MppApplyHistoryViewModel model : list) {
            if (id.equals(String.valueOf(model.getApprovalActivityId()))) {
                return model;
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
