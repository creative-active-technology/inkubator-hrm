package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.web.search.ApprovalActivitySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface ApprovalActivityService extends IService<ApprovalActivity> {

	public ApprovalActivity getEntityByPkWithDetail(Long id) throws Exception;
    
    public List<ApprovalActivity> getAllDataWithAllRelation(ApprovalActivitySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalByParam(ApprovalActivitySearchParameter searchParameter) throws Exception;
    
    public ApprovalActivity getEntityByPkWithAllRelation(Long id) throws Exception;

    public List<ApprovalActivity> getRequestHistory(String userName) throws Exception;

    public List<ApprovalActivity> getReguestHistoryById(long id) throws Exception;

    public List<ApprovalActivity> getPendingRequest(String userName) throws Exception;

    public List<ApprovalActivity> getPendingTask(String userName) throws Exception;
    
    public List<ApprovalActivity> getAllDataByActivityNumberWithDetail(String activityNumber)  throws Exception;

	public ApprovalActivity getEntityByActivityNumberLastSequence(String activityNumber) throws Exception;
	
	public ApprovalActivity getEntityByPreviousActivityNumberLastSequence(String previousActivityNumber) throws Exception;
	
	public Boolean isStillHaveWaitingStatus(String activityNumber) throws Exception;
        
        public List<ApprovalActivity> getByApprovalStatus(Integer approvalStatus) throws Exception;
}
