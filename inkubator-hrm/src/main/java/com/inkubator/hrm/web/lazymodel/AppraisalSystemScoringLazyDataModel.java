package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.AppraisalSystemScoring;
import com.inkubator.hrm.service.AppraisalSystemScoringService;
import com.inkubator.hrm.web.search.AppraisalSystemScoringSearchParameter;

public class AppraisalSystemScoringLazyDataModel extends LazyDataModel<AppraisalSystemScoring> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(AppraisalSystemScoringLazyDataModel.class);
    private final AppraisalSystemScoringSearchParameter searchParameter;
    private final AppraisalSystemScoringService service;
    private List<AppraisalSystemScoring> appraisalSystemScoringList = new ArrayList<>();
    private Integer jumlahData;

    
    
    public AppraisalSystemScoringLazyDataModel(AppraisalSystemScoringSearchParameter searchParameter, AppraisalSystemScoringService service) {
		this.searchParameter = searchParameter;
		this.service = service;
	}

	@Override
    public List<AppraisalSystemScoring> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                appraisalSystemScoringList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return appraisalSystemScoringList;
    }
    
    @Override
    public Object getRowKey(AppraisalSystemScoring appraisalSystemScoring) {
        return appraisalSystemScoring.getId();
    }

    @Override
    public AppraisalSystemScoring getRowData(String id) {
        for (AppraisalSystemScoring appraisalSystemScoring : appraisalSystemScoringList) {
            if (id.equals(String.valueOf(appraisalSystemScoring.getId()))) {
                return appraisalSystemScoring;
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
