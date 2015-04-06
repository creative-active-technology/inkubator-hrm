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

	//get entity
	public ApprovalActivity getEntityByPkWithDetail(Long id) throws Exception;
	
	public ApprovalActivity getEntityByPkWithAllRelation(Long id) throws Exception;
	
	public ApprovalActivity getEntityByActivityNumberLastSequence(String activityNumber) throws Exception;
	
	public ApprovalActivity getEntityByPreviousActivityNumberLastSequence(String previousActivityNumber) throws Exception;
	
	public ApprovalActivity getApprovalTimeByApprovalActivityNumber(String activityNumber) throws Exception;
    
    public ApprovalActivity getEntityByActivityNumberAndSequence(String activityNumber, Integer sequence) throws Exception;
    
    
    //get field    
    public Long getTotalByParam(ApprovalActivitySearchParameter searchParameter) throws Exception;
    
    public Boolean isStillHaveWaitingStatus(String activityNumber) throws Exception;
    
    public Boolean isAlreadyHaveApprovedStatus(String activityNumber) throws Exception;
    
    
    //get all data
    public List<ApprovalActivity> getRequestHistory(String userName) throws Exception;

    public List<ApprovalActivity> getReguestHistoryById(long id) throws Exception;

    public List<ApprovalActivity> getPendingRequest(String userName) throws Exception;

    public List<ApprovalActivity> getPendingTask(String userName) throws Exception;
    
    public List<ApprovalActivity> getAllDataByActivityNumberWithDetail(String activityNumber)  throws Exception;
    
    public List<ApprovalActivity> getByApprovalStatus(Integer approvalStatus) throws Exception;
    
    public List<ApprovalActivity> getAllDataWithAllRelation(ApprovalActivitySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;		
	
}
