/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.CareerTransitionDao;
import com.inkubator.hrm.dao.EmpCareerHistoryDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.CareerTransition;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.web.model.CareerTransitionInboxViewModel;
import com.inkubator.hrm.web.model.EmpCareerHistoryModel;
import com.inkubator.hrm.web.search.CareerTransitionInboxSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpMutationParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "empCareerHistoryService")
@Lazy
public class EmpCareerHistoryServiceImpl extends BaseApprovalServiceImpl implements EmpCareerHistoryService {

	@Autowired
	private EmpCareerHistoryDao empCareerHistoryDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private EmployeeTypeDao employeeTypeDao;
	@Autowired
	private JabatanDao jabatanDao;
	@Autowired
	private GolonganJabatanDao golonganJabatanDao;
	@Autowired
	private CareerTransitionDao careerTransitionDao;

	@Override
	public EmpCareerHistory getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public EmpCareerHistory getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public void save(EmpCareerHistory entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public void update(EmpCareerHistory entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public void saveOrUpdate(EmpCareerHistory enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory saveData(EmpCareerHistory entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory updateData(EmpCareerHistory entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory saveOrUpdateData(EmpCareerHistory entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public EmpCareerHistory getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public void delete(EmpCareerHistory entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public void softDelete(EmpCareerHistory entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public List<EmpCareerHistory> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public List<EmpCareerHistory> getAllData(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public List<EmpCareerHistory> getAllData(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public List<EmpCareerHistory> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public List<EmpCareerHistory> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public List<EmpCareerHistory> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public List<EmpCareerHistory> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public List<EmpCareerHistory> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmpCareerHistory> getEmployeeCareerByBioId(long id) throws Exception {
		return this.empCareerHistoryDao.getEmployeeCareerByBioId(id);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmpCareerHistory> getByParamReport(ReportEmpMutationParameter searchParameter, int firstResult,
			int maxResults, Order order) {
		return this.empCareerHistoryDao.getByParamReport(searchParameter, firstResult, maxResults, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalEmpCareerHistoryDataByParamReport(ReportEmpMutationParameter searchParameter) {
		return this.empCareerHistoryDao.getTotalEmpCareerHistoryDataByParamReport(searchParameter);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveTransitionWithApproval(EmpCareerHistoryModel model) throws Exception {

		return this.saveTransition(model, Boolean.FALSE);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveTransitionWithRevised(EmpCareerHistoryModel model, Long approvalActivityId) throws Exception {
		String message = "error";

		/** proceed of revising data */
		String pendingData = this.convertToJsonData(model);
		this.revised(approvalActivityId, pendingData);

		message = "success_need_approval";

		return message;

	}

	private void doValidationTransition(EmpCareerHistoryModel model) throws BussinessException {
		CareerTransition careerTransition = careerTransitionDao.getEntiyByPK(model.getCareerTransitionId());
		/** if employee is not working anymore(ex:termination), 
			then it should check if the employee still have pending task approval */
		if(careerTransition.getSystemCareerConst().getIsWork() == false){
	        HrmUser user = hrmUserDao.getByEmpDataId(model.getEmpData().getId());
	        if (user != null) {
	            long totalPendingTask = approvalActivityDao.getPendingTask(user.getUserId()).size();
	            if (totalPendingTask > 0) {
	                throw new BussinessException("emp_data.error_cannot_do_transition_still_have_pending_task");
	            }
	        }
		}
	}

	private String saveTransition(EmpCareerHistoryModel model, Boolean isBypassApprovalChecking) throws Exception {
		String message = "error";
		
		/** do validation */
		this.doValidationTransition(model);
		
		/** start approval checking and saving data also */
        ApprovalActivity approvalActivity = this.checkApprovalIfAny(model.getEmpData().getId(), isBypassApprovalChecking);
		if(approvalActivity == null){				
			/** proceed of saving data(entity) to DB */            
			EmployeeType employeeType = employeeTypeDao.getEntiyByPK(model.getEmployeeTypeId());
			Jabatan jabatan = jabatanDao.getEntiyByPK(model.getJabatanId());
			GolonganJabatan golonganJabatan = golonganJabatanDao.getEntiyByPK(model.getGolonganJabatanId());
			CareerTransition careerTransition = careerTransitionDao.getEntiyByPK(model.getCareerTransitionId());
			EmpData copyOfLetterTo = empDataDao.getEntiyByPK(model.getCopyOfLetterTo().getId());
			
			EmpData empData = empDataDao.getEntiyByPK(model.getEmpData().getId());
			String salaryEncrypted = this.calculateSalaryEncrypted(empData.getBasicSalary(), model.getSalaryChangesType(), model.getSalaryChangesPercent());
			empData.setBasicSalary(salaryEncrypted);
			empData.setEmployeeType(employeeType);
			empData.setJabatanByJabatanId(jabatan);
			empData.setGolonganJabatan(golonganJabatan);
			empData.setJoinDate(model.getJoinDate());
			empData.setStatus(careerTransition.getSystemCareerConst().getConstant());
			empData.setUpdatedBy(UserInfoUtil.getUserName());
			empData.setUpdatedOn(new Date());
			empDataDao.update(empData);
			
			EmpCareerHistory careerHistory = new EmpCareerHistory();
			careerHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
	        careerHistory.setBioData(empData.getBioData());
	        careerHistory.setCopyOfLetterTo(copyOfLetterTo);
	        careerHistory.setGolonganJabatan(empData.getGolonganJabatan());
	        careerHistory.setJabatan(empData.getJabatanByJabatanId());
	        careerHistory.setNik(empData.getNik());
	        careerHistory.setNoSk(model.getNoSk());
	        careerHistory.setSalary(empData.getBasicSalary());
	        careerHistory.setTglPenganngkatan(model.getEffectiveDate());
	        careerHistory.setStatus(careerTransition.getSystemCareerConst().getConstant());
	        careerHistory.setEmployeeType(empData.getEmployeeType());
	        careerHistory.setSalaryChangesType(model.getSalaryChangesType());
	        careerHistory.setSalaryChangesPercent(model.getSalaryChangesPercent());
	        careerHistory.setNotes(model.getNotes());
	        careerHistory.setApprovalActivityNumber(model.getApprovalActivityNumber());
	        careerHistory.setCreatedBy(UserInfoUtil.getUserName());
	        careerHistory.setCreatedOn(new Date());
	        empCareerHistoryDao.save(careerHistory);
            
            message = "success_without_approval";
            
		} else {	
			/** proceed of saving approval activity */
			String pendingData = this.convertToJsonData(model);
            approvalActivity.setPendingData(pendingData);
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingApprovalNotification(approvalActivity);
            
            message = "success_need_approval";
		}    
		
		return message;
	}
	
	private String calculateSalaryEncrypted(String salaryEncrypt, String salaryChangesType, Double salaryChangesPercent){
		String salaryDecrypt = AESUtil.getAESDescription(salaryEncrypt, HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
        double salary = Double.parseDouble(salaryDecrypt);
        if(StringUtils.equals(salaryChangesType, HRMConstant.SALARY_INCREASES)){
        	salary = salary + (salary * (salaryChangesPercent/100));
        } else if(StringUtils.equals(salaryChangesType, HRMConstant.SALARY_DECREASES)){
        	salary = salary - (salary * (salaryChangesPercent/100));
        }
        return AESUtil.getAESEncription(String.valueOf(salary), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
	}
	
	private ApprovalActivity checkApprovalIfAny(Long empDataId, Boolean isBypassApprovalChecking) throws Exception{
		/** check approval process if any,
		 *  return null if no need approval process */
		HrmUser requestUser = hrmUserDao.getByEmpDataId(empDataId);
		
        return isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.EMPLOYEE_CAREER_TRANSITION, requestUser.getUserId());
	}

	private String convertToJsonData(EmpCareerHistoryModel model) {
		// parsing object to json
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		return gson.toJson(model);
	}
	
	private EmpCareerHistoryModel convertJsonToModel(String json) {
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		EmpCareerHistoryModel model = gson.fromJson(json, EmpCareerHistoryModel.class);
        return model;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
		Map<String, Object> result = super.approvedAndCheckNextApproval(approvalActivityId, pendingDataUpdate, comment);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
            /**
             * kalau status akhir sudah di approved dan tidak ada next approval,
             * berarti langsung insert ke database
             */
            EmpCareerHistoryModel model = this.convertJsonToModel(appActivity.getPendingData());
            model.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose

            /** saving to DB */
            this.saveTransition(model, Boolean.TRUE);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void rejected(long approvalActivityId, String comment) throws Exception {
		Map<String, Object> result = super.rejectedAndCheckNextApproval(approvalActivityId, comment);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
        	
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void diverted(long approvalActivityId) throws Exception {
		Map<String, Object> result = super.divertedAndCheckNextApproval(approvalActivityId);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
            /**
             * kalau status akhir sudah di approved dan tidak ada next approval,
             * berarti langsung insert ke database
             */                        
        	EmpCareerHistoryModel model = this.convertJsonToModel(appActivity.getPendingData());
            model.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose
            
            /** saving to DB */
            this.saveTransition(model, Boolean.TRUE);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);

	}

	@Override
	protected void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<CareerTransitionInboxViewModel> getEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception{
        return empCareerHistoryDao.getEntityEmpCareerHistoryInboxByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    public Long getTotalgetEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
