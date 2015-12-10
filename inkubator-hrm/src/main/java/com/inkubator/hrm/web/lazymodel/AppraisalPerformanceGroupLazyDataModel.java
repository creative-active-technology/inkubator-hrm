package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.service.AppraisalPerformanceGroupService;
import com.inkubator.hrm.web.search.AppraisalPerformanceGroupSearchParameter;

public class AppraisalPerformanceGroupLazyDataModel extends LazyDataModel<AppraisalPerformanceGroup> implements Serializable{
	private static final Logger LOGGER = Logger.getLogger(AppraisalPerformanceGroupLazyDataModel.class);
	private final AppraisalPerformanceGroupSearchParameter searchParameter;
	private final AppraisalPerformanceGroupService service;
	private List<AppraisalPerformanceGroup> appraisalPerformanceGroupList;
	private Integer total;
	
	public AppraisalPerformanceGroupLazyDataModel(AppraisalPerformanceGroupSearchParameter searchParameter, AppraisalPerformanceGroupService service){
		this.searchParameter = searchParameter;
		this.service = service;
	}
	
	@Override
	public List<AppraisalPerformanceGroup> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
		LOGGER.info("Step load lazy data model");
		try{
			Order order = null;
			if(sortField != null){
				order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
			}else{
				order = Order.desc("id");
			}
			appraisalPerformanceGroupList = service.getByParam(searchParameter, first, pageSize, order);
			total = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
		}catch(Exception ex){
			LOGGER.error("Error : ", ex);
		}
		LOGGER.info("Success load lazy data model");
		
		setPageSize(pageSize);
		setRowCount(total);
		return appraisalPerformanceGroupList;
	}
	
	@Override
	public Object getRowKey(AppraisalPerformanceGroup appraisalPerformanceGroup){
		return appraisalPerformanceGroup.getId();
	}
	
	@Override
	public AppraisalPerformanceGroup getRowData(String id){
		for(AppraisalPerformanceGroup appraisalPerformanceGroup : appraisalPerformanceGroupList){
			if(id.equals(String.valueOf(appraisalPerformanceGroup.getId()))){
				return appraisalPerformanceGroup;
			}
		}
		return null;
	}
	
	@Override
    public void setRowIndex(int rowIndex){
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}