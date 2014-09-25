package com.inkubator.hrm.service.impl;



import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.ApprovalDefinitionLeaveDao;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import com.inkubator.hrm.entity.ApprovalDefinitionLeaveId;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.web.search.LeaveSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "leaveService")
@Lazy
public class LeaveServiceImpl extends IServiceImpl implements LeaveService {

	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private AttendanceStatusDao attendanceStatusDao;
	@Autowired
	private ApprovalDefinitionDao approvalDefinitionDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private JabatanDao jabatanDao;
	@Autowired
	private ApprovalDefinitionLeaveDao approvalDefinitionLeaveDao;
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Leave leave) throws Exception {
		leaveDao.delete(leave);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<Leave> getAllData() throws Exception {
		return leaveDao.getAllData();
	}

	@Override
	public List<Leave> getAllData(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllData(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllData(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllDataPageAble(int arg0, int arg1, Order arg2)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Boolean arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Integer arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<Leave> getAllDataPageAbleIsActive(int arg0, int arg1,
			Order arg2, Byte arg3) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(String arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(String arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(String arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Integer arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Integer arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Integer arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Long arg0, Integer arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntityByPkIsActive(Long arg0, Boolean arg1)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntiyByPK(String arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave getEntiyByPK(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Leave getEntiyByPK(Long id) throws Exception {
		return leaveDao.getEntiyByPK(id);
	}
        
	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Leave getEntityByPkFetchAttendStatus(Long id) throws Exception {
		return leaveDao.getEntityByPkFetchAttendStatus(id);
	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Leave leave) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveDao.getTotalByName(leave.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveDao.getTotalByCode(leave.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		
		leave.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		AttendanceStatus attendanceStatus = attendanceStatusDao.getEntiyByPK(leave.getAttendanceStatus().getId());
	    leave.setAttendanceStatus(attendanceStatus);
		leave.setCreatedBy(UserInfoUtil.getUserName());
		leave.setCreatedOn(new Date());
		leaveDao.save(leave);
	}

	@Override
	public Leave saveData(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Leave saveOrUpdateData(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Leave l) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveDao.getTotalByNameAndNotId(l.getName(),l.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveDao.getTotalByCodeAndNotId(l.getCode(),l.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		
		Leave leave = leaveDao.getEntiyByPK(l.getId());
		leave.setCode(l.getCode());
		leave.setName(l.getName());
		leave.setDescription(l.getDescription());	
		leave.setDayType(l.getDayType());
	    leave.setCalculation(l.getCalculation());
	    AttendanceStatus attendanceStatus = attendanceStatusDao.getEntiyByPK(l.getAttendanceStatus().getId());
	    leave.setAttendanceStatus(attendanceStatus);
	    leave.setPeriodBase(l.getPeriodBase());
	    leave.setAvailability(l.getAvailability());
	    leave.setAvailabilityAtSpecificDate(l.getAvailabilityAtSpecificDate());
	    leave.setIsTakingLeaveToNextYear(l.getIsTakingLeaveToNextYear());
	    leave.setMaxTakingLeaveToNextYear(l.getMaxTakingLeaveToNextYear());
	    leave.setBackwardPeriodLimit(l.getBackwardPeriodLimit());
	    leave.setIsAllowedMinus(l.getIsAllowedMinus());
	    leave.setMaxAllowedMinus(l.getMaxAllowedMinus());
	    leave.setEffectiveFrom(l.getEffectiveFrom());
	    leave.setSubmittedLimit(l.getSubmittedLimit());
	    leave.setApprovalLevel(l.getApprovalLevel());
	    leave.setIsQuotaReduction(l.getIsQuotaReduction());
	    leave.setEndOfPeriod(l.getEndOfPeriod());
	    leave.setEndOfPeriodMonth(l.getEndOfPeriodMonth());
	    leave.setIsOnlyOncePerEmployee(l.getIsOnlyOncePerEmployee());
	    leave.setUpdatedBy(UserInfoUtil.getUserName());
	    leave.setUpdatedOn(new Date());
		leaveDao.update(leave);
	}

	@Override
	public Leave updateData(Leave arg0) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<Leave> getByParam(LeaveSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return leaveDao.getByParam(parameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(LeaveSearchParameter parameter) throws Exception {
		return leaveDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Leave entity, List<ApprovalDefinition> appDefs) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveDao.getTotalByName(entity.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		
		//validasi approval definition
		this.validateApprovalDefinition(appDefs);
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		AttendanceStatus attendanceStatus = attendanceStatusDao.getEntiyByPK(entity.getAttendanceStatus().getId());
		entity.setAttendanceStatus(attendanceStatus);
		entity.setIsActive(true);
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		leaveDao.save(entity);
		
		//saving many to many relations
		Set<ApprovalDefinitionLeave> appDefinitionLeaves = new HashSet<ApprovalDefinitionLeave>();
		for(ApprovalDefinition appDef: appDefs){
			//saving approvalDefinition
			appDef.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			appDef.setCreatedBy(UserInfoUtil.getUserName());
			appDef.setCreatedOn(new Date());
			approvalDefinitionDao.save(appDef);
			
			//set many to many objects
			ApprovalDefinitionLeave approvalDefinitionLeave =  new ApprovalDefinitionLeave();
			approvalDefinitionLeave.setId(new ApprovalDefinitionLeaveId(appDef.getId(), entity.getId()));
			approvalDefinitionLeave.setApprovalDefinition(appDef);
			approvalDefinitionLeave.setLeave(entity);
			approvalDefinitionLeaveDao.save(approvalDefinitionLeave);			
		}
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Leave entity, List<ApprovalDefinition> listAppDefs) throws Exception {
		// check duplicate name
		long totalDuplicates = leaveDao.getTotalByNameAndNotId(entity.getName(),entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = leaveDao.getTotalByCodeAndNotId(entity.getCode(),entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		
		//validasi approval definition
		this.validateApprovalDefinition(listAppDefs);
		
		Leave leave = leaveDao.getEntiyByPK(entity.getId());
		leave.setCode(entity.getCode());
		leave.setName(entity.getName());
		leave.setDescription(entity.getDescription());	
		leave.setDayType(entity.getDayType());
	    leave.setCalculation(entity.getCalculation());
	    AttendanceStatus attendanceStatus = attendanceStatusDao.getEntiyByPK(entity.getAttendanceStatus().getId());
	    leave.setAttendanceStatus(attendanceStatus);
	    leave.setPeriodBase(entity.getPeriodBase());
	    leave.setAvailability(entity.getAvailability());
	    leave.setAvailabilityAtSpecificDate(entity.getAvailabilityAtSpecificDate());
	    leave.setIsTakingLeaveToNextYear(entity.getIsTakingLeaveToNextYear());
	    leave.setMaxTakingLeaveToNextYear(entity.getMaxTakingLeaveToNextYear());
	    leave.setBackwardPeriodLimit(entity.getBackwardPeriodLimit());
	    leave.setIsAllowedMinus(entity.getIsAllowedMinus());
	    leave.setMaxAllowedMinus(entity.getMaxAllowedMinus());
	    leave.setEffectiveFrom(entity.getEffectiveFrom());
	    leave.setSubmittedLimit(entity.getSubmittedLimit());
	    leave.setApprovalLevel(entity.getApprovalLevel());
	    leave.setIsQuotaReduction(entity.getIsQuotaReduction());
	    leave.setEndOfPeriod(entity.getEndOfPeriod());
	    leave.setEndOfPeriodMonth(entity.getEndOfPeriodMonth());
	    leave.setIsOnlyOncePerEmployee(entity.getIsOnlyOncePerEmployee());
	    leave.setUpdatedBy(UserInfoUtil.getUserName());
	    leave.setUpdatedOn(new Date());
	    leaveDao.update(leave);
	    
	    /** Proses UPDATE approval definition, berdasarkan relasi many to many leave dan approval defintion 
	     *  REMOVE relasi-nya jika sudah tidak terdapat di list */
	    boolean isRemoveRelation;
	    Iterator<ApprovalDefinition> iterAppDefs;
	    Iterator<ApprovalDefinitionLeave> iterAppDefLeaves = leave.getApprovalDefinitionLeaves().iterator();
	    while(iterAppDefLeaves.hasNext()) {
	    	isRemoveRelation = true;
	    	ApprovalDefinitionLeave currentAppDefLeave = iterAppDefLeaves.next();
	    	ApprovalDefinition currentAppDef = currentAppDefLeave.getApprovalDefinition();	    	
	    	iterAppDefs = listAppDefs.iterator();
	    	while(iterAppDefs.hasNext()) {
	    		ApprovalDefinition appDef = iterAppDefs.next();
	    		if(currentAppDef.getId() == appDef.getId()){
	    			isRemoveRelation = false;	    			
	    			this.updateApprovalDefinition(appDef);	 
	    			iterAppDefs.remove();
	    			break;
	    		}
	    	}
	    	
	    	if(isRemoveRelation){
	    		approvalDefinitionLeaveDao.delete(currentAppDefLeave);
	    	}
	    }
	    
	    /** Proses ADD approval definition, hanya approval definition yang memang belum di create
	     *  data yang akan di insert sudah di filter di proses sebelumnya */
	    iterAppDefs = listAppDefs.iterator();
	    while(iterAppDefs.hasNext()) {
  			//saving approvalDefinition
	    	ApprovalDefinition appDef = iterAppDefs.next();
  			appDef.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
  			appDef.setCreatedBy(UserInfoUtil.getUserName());
  			appDef.setCreatedOn(new Date());
  			approvalDefinitionDao.save(appDef);
  			
  			//set many to many objects
  			ApprovalDefinitionLeave approvalDefinitionLeave =  new ApprovalDefinitionLeave();
  			approvalDefinitionLeave.setId(new ApprovalDefinitionLeaveId(appDef.getId(), leave.getId()));
  			approvalDefinitionLeave.setApprovalDefinition(appDef);
  			approvalDefinitionLeave.setLeave(leave);
  			approvalDefinitionLeaveDao.save(approvalDefinitionLeave);
  		}
	}
	
	private void updateApprovalDefinition(ApprovalDefinition entity){
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
        ad.setSpecificName(entity.getSpecificName());
        ad.setUpdatedBy(UserInfoUtil.getUserName());
        ad.setUpdatedOn(new Date());
        this.approvalDefinitionDao.update(ad);
	}
	
	private void validateApprovalDefinition(List<ApprovalDefinition> appDefs) throws BussinessException{
		//sorting by sequence, lalu sequence di cek harus dimulai dari 1
		List<ApprovalDefinition> sortBySequence = Lambda.sort(appDefs, Lambda.on(ApprovalDefinition.class).getSequence());
        if(!sortBySequence.isEmpty() && sortBySequence.get(0).getSequence()!=1){        	
        	throw new BussinessException("approval.error_first");
        }
        
        //cek sequence tidak boleh duplikat
        List<Integer> sequences = Lambda.extract(sortBySequence, Lambda.on(ApprovalDefinition.class).getSequence());
    	Set <Integer> uniques = new HashSet<Integer>(sequences);
        if(sequences.size() != uniques.size()){        	
        	throw new BussinessException("approval.error_unik");
        }
        
        //cek kalo process type "ON_APPROVE_INFO" atau "ON_REJECT_INFO" tidak boleh lebih kecil sequence-nya dari APPROVAL_PROCESS
        ApprovalDefinition max = Lambda.selectMax(Lambda.select(appDefs, Lambda.having(Lambda.on(ApprovalDefinition.class).getProcessType(), Matchers.equalToIgnoringCase(HRMConstant.APPROVAL_PROCESS))), Lambda.on(ApprovalDefinition.class).getSequence());
        List<ApprovalDefinition> excludeApprovalProcess =  Lambda.select(appDefs, Lambda.having(Lambda.on(ApprovalDefinition.class).getProcessType(), Matchers.not(HRMConstant.APPROVAL_PROCESS)));
        if(Lambda.selectFirst(excludeApprovalProcess, Lambda.having(Lambda.on(ApprovalDefinition.class).getSequence(), Matchers.lessThanOrEqualTo(max.getSequence()))) != null){
        	throw new BussinessException("approval.error_process_less_than");
        }
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public Leave getEntityByPkFetchApprovalDefinition(Long id) throws Exception {
		return leaveDao.getEntityByPkFetchApprovalDefinition(id);
	}

}
