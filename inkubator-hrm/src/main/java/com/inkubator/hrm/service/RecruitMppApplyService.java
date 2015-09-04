package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.web.model.MppApplyHistoryViewModel;
import com.inkubator.hrm.web.model.RecruitMppApplyViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface RecruitMppApplyService extends IService<RecruitMppApply>, BaseApprovalService{

    public List<RecruitMppApply> getByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(RecruitMppApplySearchParameter parameter) throws Exception;   
    
    public RecruitMppApply getEntityWithDetailById(Long id);
    
    public String saveRecruitMppApplytWithApproval(RecruitMppApply entity, List<RecruitMppApplyDetail> listDetailRecruitMppApply, UploadedFile recruitMppApplyFile) throws Exception;
    
    public String updateRecruitMppApplytWithApproval(RecruitMppApply entity, List<RecruitMppApplyDetail> listDetailRecruitMppApply, UploadedFile recruitMppApplyFile, String activityNumber) throws Exception;
    
    public List<RecruitMppApplyViewModel> getUndisbursedActivityByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
        
    public Long getTotalUndisbursedActivityByParam(RecruitMppApplySearchParameter parameter) throws Exception;
    
    public RecruitMppApply getEntityWithDetailByActivityNumber(String activityNumber) throws Exception;
    
    public UploadedFile convertFileToUploadedFile(String path);
    
    public List<RecruitMppApply> getListWithDetailByApprovalStatus(Integer approvalStatus) throws Exception;
    
    public List<MppApplyHistoryViewModel> getAllDataMppApplyHistoryByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalMppApplyHistoryByParam(RecruitMppApplySearchParameter parameter) throws Exception;
    
}
