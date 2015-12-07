package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.web.search.ApprovalActivitySearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface ApprovalActivityDao extends IDAO<ApprovalActivity> {

	public void updateAndFlush(ApprovalActivity approvalActivity);
	
	
	/** get Entity */
	public ApprovalActivity getEntityByPkWithAllRelation(Long id);
	
	public ApprovalActivity getEntityByPkWithDetail(Long id);	
    
    public ApprovalActivity getApprovalTimeByApprovalActivityNumber(String activityNumber);
    
    public ApprovalActivity getEntityByActivityNumberAndSequence(String activityNumber, Integer sequence);
    
    public ApprovalActivity getEntityByActivityNumberAndLastSequence(String activityNumber);
	
	
    /** get Total */
    public Long getTotalByParam(ApprovalActivitySearchParameter searchParameter);
    
    public Boolean isStillHaveWaitingStatus(List<ApprovalDefinition> appDefs, String requestBy, Long typeSpecific);

    public Boolean isStillHaveWaitingStatus(List<ApprovalDefinition> appDefs, String requestBy);
    
    public Boolean isStillHaveWaitingStatus(List<ApprovalDefinition> appDefs);

    public Boolean isStillHaveWaitingStatus(Long appDefId);

    public Boolean isStillHaveWaitingStatus(String activityNumber);
    
    public Boolean isAlreadyHaveApprovedStatus(String activityNumber);
    
    
	
    /** get All Data
     * @param userName
     * @param firstResult */
	public List<ApprovalActivity> getRequestHistory(String userName, int firstResult, int maxResults, Order order);

    public List<ApprovalActivity> getAllDataWithAllRelation(ApprovalActivitySearchParameter searchParameter, int firstResult, int maxResults, Order order);    

    public List<ApprovalActivity> getReguestHistoryById(long id);

    public List<ApprovalActivity> getPendingRequest(String userName);

    public List<ApprovalActivity> getPendingTask(String userName);    

    public List<ApprovalActivity> getAllDataByActivityNumberWithDetail(String activityNumber, Order order);

    public List<ApprovalActivity> getAllDataByPreviousActivityNumber(String previousActivityNumber, Order order);

    public List<ApprovalActivity> getDataNotSendEmailYet();

    public List<ApprovalActivity> getAllDataWaitingStatusApproval();

    public List<ApprovalActivity> getByApprovalStatus(Integer approvalStatus);
    
    public List<ApprovalActivity> getAllDataNotApprovedYet(String userId, String approvalDefinitionName);
    
     public List<ApprovalActivity> getAllDataWaitingStatusApprovalFetchedApprovalDef();
     
     public List<ApprovalActivity> getAllDataPendingRequestByApprovalDefName(String approvalDefName);
    
}
