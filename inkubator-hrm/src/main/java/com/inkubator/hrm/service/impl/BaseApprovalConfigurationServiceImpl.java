package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rizkykojek
 */
public abstract class BaseApprovalConfigurationServiceImpl<T> extends IServiceImpl {

	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private ApprovalDefinitionDao approvalDefinitionDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private JabatanDao jabatanDao;
	
	protected abstract void saveManyToMany(ApprovalDefinition appDef, T entity);
	
	protected abstract void deleteManyToMany(Object entity);
	
	protected void validateApprovalConf(List<ApprovalDefinition> listAppDef) throws BussinessException{	
		
		/** cek apakah masih terdapat approval activity yang pending */
		if(approvalActivityDao.isStillHaveWaitingStatus(listAppDef)){
			throw new BussinessException("approval.error_still_have_waiting_status");
		}		
		
		/** sorting by sequence, lalu sequence di cek harus dimulai dari 1 */
		List<ApprovalDefinition> sortBySequence = Lambda.sort(listAppDef, Lambda.on(ApprovalDefinition.class).getSequence());
        if(!sortBySequence.isEmpty() && sortBySequence.get(0).getSequence()!=1){        	
        	throw new BussinessException("approval.error_first");
        }
        
        /** cek sequence tidak boleh duplikat */
        List<Integer> sequences = Lambda.extract(sortBySequence, Lambda.on(ApprovalDefinition.class).getSequence());
    	Set <Integer> uniques = new HashSet<Integer>(sequences);
        if(sequences.size() != uniques.size()){        	
        	throw new BussinessException("approval.error_unik");
        }
        
        /** cek kalo process type "ON_APPROVE_INFO" atau "ON_REJECT_INFO" tidak boleh lebih kecil sequence-nya dari APPROVAL_PROCESS */
        ApprovalDefinition max = Lambda.selectMax(Lambda.select(listAppDef, Lambda.having(Lambda.on(ApprovalDefinition.class).getProcessType(), Matchers.equalToIgnoringCase(HRMConstant.APPROVAL_PROCESS))), Lambda.on(ApprovalDefinition.class).getSequence());
        if(max != null) {
	        List<ApprovalDefinition> excludeApprovalProcess =  Lambda.select(listAppDef, Lambda.having(Lambda.on(ApprovalDefinition.class).getProcessType(), Matchers.not(HRMConstant.APPROVAL_PROCESS)));
	        if(Lambda.selectFirst(excludeApprovalProcess, Lambda.having(Lambda.on(ApprovalDefinition.class).getSequence(), Matchers.lessThanOrEqualTo(max.getSequence()))) != null){
	        	throw new BussinessException("approval.error_process_less_than");
	        }
        }
	}
	
	protected void saveApprovalConf(List<ApprovalDefinition> appDefs, T entity){
		//saving many to many relations
		for(ApprovalDefinition appDef: appDefs){
			this.saveApprovalConf(appDef, entity);			
		}		
	}
	
	protected void saveApprovalConf(ApprovalDefinition appDef, T entity){
		//saving approvalDefinition
		appDef.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		appDef.setIsNoLongerInUse(Boolean.FALSE);
		appDef.setCreatedBy(UserInfoUtil.getUserName());
		appDef.setCreatedOn(new Date());
		approvalDefinitionDao.save(appDef);
			
		//set many to many objects
		this.saveManyToMany(appDef, entity);		
	}
	
	protected void updateApprovalConf(List<ApprovalDefinition> appDefs, Iterator<?> iterManyToMany, T entity) throws Exception {
		/** Proses UPDATE approval definition, berdasarkan relasi many to many leave dan approval defintion 
	     *  REMOVE relasi-nya jika sudah tidak terdapat di list */
		boolean isRemoveRelation;
	    while(iterManyToMany.hasNext()) {
	    	isRemoveRelation = true;
	    	
	    	Object currentManyToMany = iterManyToMany.next();
	    	Long currentAppDefId = Long.parseLong(BeanUtils.getProperty(currentManyToMany, "approvalDefinition.id"));	    	
	    	Iterator<ApprovalDefinition> iterAppDefs = appDefs.iterator();
	    	while(iterAppDefs.hasNext()) {
	    		ApprovalDefinition appDef = iterAppDefs.next();
	    		if(ObjectUtils.equals(currentAppDefId, appDef.getId())){	    				    			
	    			//update approval definition
	    			this.updateApprovalDefinition(appDef);	 
	    			iterAppDefs.remove(); //remove object from list that will being ADD in the process below	    			
	    			isRemoveRelation = false;
	    			break;
	    		}
	    	}
	    	
	    	if(isRemoveRelation){
	    		//delete approval definition
	    		this.deleteApprovalConf(currentManyToMany, currentAppDefId);	    		
	    	}
	    }
	    
	    /** Proses ADD approval definition, hanya approval definition yang memang belum di create
	     *  Data yang akan di create, sudah di filter di proses sebelumnya */
	    this.saveApprovalConf(appDefs, entity);
	}
	
	protected void deleteApprovalConf(Object currentManyToMany, Long currentAppDefId){
		//delete object ManyToMany that no longer available in current entity
		this.deleteManyToMany(currentManyToMany); 	    		
		
		//delete approval definition that no longer available in current entity 
		ApprovalDefinition appDef = approvalDefinitionDao.getEntiyByPK(currentAppDefId);
		if(appDef.getApprovalActivities().isEmpty()){
			//delete permanently if has not been used in any approval activity process
			this.approvalDefinitionDao.delete(appDef);
		} else {
			//since this appDef already use in approval activity process, then update appDef to noLongerInUse. 
			//It still maintain in database but no longer in use anymore
			appDef.setIsNoLongerInUse(Boolean.TRUE);
    		this.approvalDefinitionDao.update(appDef);
		}
	}
	
	protected void updateApprovalDefinition(ApprovalDefinition entity){
		ApprovalDefinition ad = this.approvalDefinitionDao.getEntiyByPK(entity.getId());
        ad.setAllowOnBehalf(entity.getAllowOnBehalf());
        ad.setApproverType(entity.getApproverType());
        ad.setAutoApproveOnDelay(entity.getAutoApproveOnDelay());
        ad.setDelayTime(entity.getDelayTime());
        ad.setEscalateOnDelay(entity.getEscalateOnDelay());
        if (entity.getHrmUserByApproverIndividual() != null) {
            ad.setHrmUserByApproverIndividual(hrmUserDao.getEntiyByPK(entity.getHrmUserByApproverIndividual().getId()));
        }
        if (entity.getHrmUserByOnBehalfIndividual() != null) {
            ad.setHrmUserByOnBehalfIndividual(hrmUserDao.getEntiyByPK(entity.getHrmUserByOnBehalfIndividual().getId()));
        }        
        if (entity.getJabatanByApproverPosition() != null) {
            ad.setJabatanByApproverPosition(jabatanDao.getEntiyByPK(entity.getJabatanByApproverPosition().getId()));
        }
        if (entity.getJabatanByOnBehalfPosition() != null) {
            ad.setJabatanByOnBehalfPosition(jabatanDao.getEntiyByPK(entity.getJabatanByOnBehalfPosition().getId()));
        }
        ad.setMinApprover(entity.getMinApprover());
        ad.setMinRejector(entity.getMinRejector());
        ad.setName(entity.getName());
        ad.setOnBehalfType(entity.getOnBehalfType());
        ad.setProcessType(entity.getProcessType());
        ad.setSequence(entity.getSequence());
        ad.setSmsNotification(entity.getSmsNotification());
        ad.setSpecificName(entity.getSpecificName());
        ad.setUpdatedBy(UserInfoUtil.getUserName());
        ad.setUpdatedOn(new Date());
        this.approvalDefinitionDao.update(ad);
	}
}
