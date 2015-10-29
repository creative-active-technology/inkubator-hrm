package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.RecruitHireApplyDetailService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitSelectionApplicantInitialService;
import com.inkubator.hrm.web.model.RecruitmentScheduleSettingViewModel;
import com.inkubator.hrm.web.search.RecruitmentScheduleSettingSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/

public class RecruitmentScheduleSettingLazyDataModel extends LazyDataModel<RecruitmentScheduleSettingViewModel> implements Serializable{
	private static final Logger LOGGER = Logger.getLogger(RecruitmentScheduleSettingLazyDataModel.class);
    private final RecruitmentScheduleSettingSearchParameter parameter;
    private final RecruitSelectionApplicantInitialService recruitSelectionApplicantInitialService;
    private List<RecruitmentScheduleSettingViewModel> list = new ArrayList<>();
    private Integer total;
    
    public RecruitmentScheduleSettingLazyDataModel(RecruitmentScheduleSettingSearchParameter parameter, RecruitSelectionApplicantInitialService recruitSelectionApplicantInitialService) {
        this.parameter = parameter;
        this.recruitSelectionApplicantInitialService = recruitSelectionApplicantInitialService;
    }

    @Override
    public List<RecruitmentScheduleSettingViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("recruitHireApply.proposeDate");
	        }
	        
	        list = recruitSelectionApplicantInitialService.getByParamForRecruitmentScheduleSetting(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(recruitSelectionApplicantInitialService.getTotalByParamforRecruitmentScheduleSetting(parameter)));            
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
    public Object getRowKey(RecruitmentScheduleSettingViewModel entity) {
        return entity.getId();
    }

    @Override
    public RecruitmentScheduleSettingViewModel getRowData(String id) {
        for (RecruitmentScheduleSettingViewModel entity : list) {
            if (id.equals(String.valueOf(entity.getId()))) {
                return entity;
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