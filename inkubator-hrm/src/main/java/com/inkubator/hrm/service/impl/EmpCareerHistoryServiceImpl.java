/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.MessageCreator;
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
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.CareerTransitionInboxSearchParameter;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpMutationParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;

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
		
		/** do validation */
		this.doValidationTransition(model, approvalActivityId);
		
		/** proceed of revising data */
		String pendingData = this.convertToJsonData(model);
		this.revised(approvalActivityId, pendingData);

		message = "success_need_approval";

		return message;

	}

	private EmpCareerHistoryModel doSetAdditionalValue(EmpCareerHistoryModel model) {
		String createdBy = StringUtils.isEmpty(model.getCreatedBy()) ? UserInfoUtil.getUserName() : model.getCreatedBy();
        Date createdOn = model.getCreatedOn() == null ? new Date() : model.getCreatedOn();
        
		model.setCreatedOn(createdOn);
		model.setCreatedBy(createdBy);
		
		return model;
	}
	
	private void doValidationTransition(EmpCareerHistoryModel model, Long excludeAppActivityId) throws BussinessException {
		CareerTransition careerTransition = careerTransitionDao.getEntiyByPK(model.getCareerTransitionId());
		/** if employee is not working anymore(ex:termination), 
			then it should check if the employee still have pending task approval */
		if(careerTransition.getSystemCareerConst().getIsWork() == false){
	        HrmUser user = hrmUserDao.getByEmpDataId(model.getEmpData().getId());
	        if (user != null) {
	            List<ApprovalActivity> listPendingTask = approvalActivityDao.getPendingTask(user.getUserId());
	            
	            if(excludeAppActivityId != null){
	            	//exclude this activityId, digunakan ketika melakukan revisi approval
	            	listPendingTask.remove(new ApprovalActivity(excludeAppActivityId)); 	
	            }
	            
	            if (listPendingTask.size() > 0) {
	                throw new BussinessException("emp_data.error_cannot_do_transition_still_have_pending_task");
	            }
	        }
		}
		
		/** nik should not be duplicate */
		long totalDuplicates = empDataDao.getTotalByNikandNotId(model.getNik(), model.getEmpData().getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("emp_data.error_nik_duplicate");
        }
	}

	private String saveTransition(EmpCareerHistoryModel model, Boolean isBypassApprovalChecking) throws Exception {
		String message = "error";
		
		/** do validation */
		this.doValidationTransition(model, null);
		
		/** do set addtional value */
		model = this.doSetAdditionalValue(model);
		
		/** start approval checking and saving data also */
        ApprovalActivity approvalActivity = this.checkApprovalIfAny(model.getEmpData().getId(), isBypassApprovalChecking);
		if(approvalActivity == null){				
			/** proceed of saving data(entity) to DB */  
			EmpCareerHistory careerHistory = new EmpCareerHistory();
			EmployeeType employeeType = employeeTypeDao.getEntiyByPK(model.getEmployeeTypeId());
			Jabatan jabatan = jabatanDao.getEntiyByPK(model.getJabatanId());
			GolonganJabatan golonganJabatan = golonganJabatanDao.getEntiyByPK(model.getGolonganJabatanId());
			CareerTransition careerTransition = careerTransitionDao.getEntiyByPK(model.getCareerTransitionId());
			EmpData copyOfLetterTo = empDataDao.getEntiyByPK(model.getCopyOfLetterTo().getId());
			
			EmpData empData = empDataDao.getEntiyByPK(model.getEmpData().getId());
			/** save previous join date to career history */
			careerHistory.setJoinDate(empData.getJoinDate());
			System.out.println("ini teh join date sebelumnya" + empData.getJoinDate());
			String salaryEncrypted = this.calculateSalaryEncrypted(empData.getBasicSalary(), model.getSalaryChangesType(), model.getSalaryChangesPercent());
			empData.setBasicSalary(salaryEncrypted);
			empData.setNik(model.getNik());
			empData.setEmployeeType(employeeType);
			empData.setJabatanByJabatanId(jabatan);
			empData.setGolonganJabatan(golonganJabatan);
			empData.setJoinDate(model.getJoinDate());
			/** status di karyawan/empData berbeda dengan yang di empCareerHistory.
			 *  cukup dua status saja termination(tidak bekerja) atau placement(masih bekerja), untuk detailnya silahkan di liat di status empCareerHistory */
			String status = careerTransition.getSystemCareerConst().getIsWork() ? HRMConstant.EMP_PLACEMENT : HRMConstant.EMP_TERMINATION;
			empData.setStatus(status);
			empData.setUpdatedBy(StringUtils.isEmpty(model.getCreatedBy()) ? UserInfoUtil.getUserName() : model.getCreatedBy());
			empData.setUpdatedOn(model.getCreatedOn() == null ? new Date() : model.getCreatedOn());
			empDataDao.update(empData);
			
			/*EmpCareerHistory careerHistory = new EmpCareerHistory();*/
			careerHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
	        careerHistory.setBioData(empData.getBioData());
	        careerHistory.setCopyOfLetterTo(copyOfLetterTo);
	        careerHistory.setGolonganJabatan(empData.getGolonganJabatan());
	        careerHistory.setJabatan(empData.getJabatanByJabatanId());
	        careerHistory.setNik(model.getNik());
	        careerHistory.setNoSk(model.getNoSk());
	        careerHistory.setSalary(empData.getBasicSalary());
	        careerHistory.setTglPenganngkatan(model.getEffectiveDate());
	        careerHistory.setStatus(careerTransition.getSystemCareerConst().getConstant());
	        careerHistory.setCareerTransition(careerTransition);
	        careerHistory.setEmployeeType(empData.getEmployeeType());
	        careerHistory.setSalaryChangesType(model.getSalaryChangesType());
	        careerHistory.setSalaryChangesPercent(model.getSalaryChangesPercent());
	        careerHistory.setNotes(model.getNotes());
	        careerHistory.setApprovalActivityNumber(model.getApprovalActivityNumber());
	        careerHistory.setCreatedBy(StringUtils.isEmpty(model.getCreatedBy()) ? UserInfoUtil.getUserName() : model.getCreatedBy());
	        careerHistory.setCreatedOn(model.getCreatedOn() == null ? new Date() : model.getCreatedOn());
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
		//send sms notification to approver if need approval OR
        //send sms notification to requester if need revision
		super.sendApprovalSmsnotif(appActivity);
		
		//initialization
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy", new Locale(appActivity.getLocale()));
        EmpCareerHistoryModel model = this.convertJsonToModel(appActivity.getPendingData());
        EmployeeType beforeEmployeeType = employeeTypeDao.getEntiyByPK(model.getEmpData().getEmployeeType().getId());
        Jabatan beforeJabatan = jabatanDao.getEntiyByPK(model.getEmpData().getJabatanByJabatanId().getId());
        EmployeeType afterEmployeeType = employeeTypeDao.getEntiyByPK(model.getEmployeeTypeId());
        Jabatan afterJabatan = jabatanDao.getEntiyByPK(model.getJabatanId());
        
        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
            
            //jika di approve, maka tambahkan juga tembusanSurat di list CC of EmailAddress
            if(appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED){
            	HrmUser copyOfLetterTo = hrmUserDao.getByEmpDataId(model.getCopyOfLetterTo().getId());
            	if(copyOfLetterTo != null){
            		ccEmailAddresses.add(copyOfLetterTo.getEmailAddress());
            	}
            }
        }
        
        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("proposeDate", dateFormat.format(model.getCreatedOn()));
            jsonObj.put("effectiveDate", dateFormat.format(model.getEffectiveDate()));
            jsonObj.put("beforeNik", model.getEmpData().getNik());
            jsonObj.put("beforeJoinDate", dateFormat.format(model.getEmpData().getJoinDate()));
            jsonObj.put("beforeEmployeeType", beforeEmployeeType.getName());
            jsonObj.put("beforeJabatan", beforeJabatan.getName());
            jsonObj.put("beforeDepartment", beforeJabatan.getDepartment().getDepartmentName());
            jsonObj.put("afterNik", model.getNik());
            jsonObj.put("afterJoinDate", dateFormat.format(model.getJoinDate()));
            jsonObj.put("afterEmployeeType", afterEmployeeType.getName());
            jsonObj.put("afterJabatan", afterJabatan.getName());
            jsonObj.put("afterDepartment", afterJabatan.getDepartment().getDepartmentName());
            
            jsonObj.put("urlLinkToApprove", FacesUtil.getRequest().getContextPath() + "" + HRMConstant.EMPLOYEE_CAREER_TRANSITION_APPROVAL_PAGE + "" +"?faces-redirect=true&execution=e" + appActivity.getId());

        } catch (JSONException e) {
            LOGGER.error("Error when create json Object ", e);
        }

        //send messaging, to trigger sending email
        super.jmsTemplateApproval.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(jsonObj.toString());
            }
        });

	}

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		EmpCareerHistoryModel model = this.convertJsonToModel(appActivity.getPendingData());
		EmployeeType employeeType = employeeTypeDao.getEntiyByPK(model.getEmployeeTypeId());
		Jabatan jabatan = jabatanDao.getEntiyByPK(model.getJabatanId());
		
		detail.append("Pengajuan transisi karir oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
		detail.append("Departemen :" + jabatan.getDepartment().getDepartmentName() + ". ");
		detail.append("Jabatan :" + jabatan.getName() + ". ");
		detail.append("Status Karyawan :" + employeeType.getName() + ". ");
		return detail.toString();
	}

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<CareerTransitionInboxViewModel> getEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception{
    	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
    	List<CareerTransitionInboxViewModel> listModel = empCareerHistoryDao.getEntityEmpCareerHistoryInboxByParam(searchParameter, firstResult, maxResults, order);
    	System.out.println(listModel.size());
    	for(CareerTransitionInboxViewModel careerTransitionInbox : listModel){
    		EmpCareerHistoryModel model = this.convertJsonToModel(careerTransitionInbox.getJsonData());
    		Jabatan jabatan = jabatanDao.getEntiyByPK(model.getJabatanId());
    		EmployeeType employeeType = employeeTypeDao.getEntiyByPK(model.getEmployeeTypeId());
    		GolonganJabatan golJab = golonganJabatanDao.getEntiyByPK(model.getGolonganJabatanId());
    		CareerTransition careerTransition = careerTransitionDao.getEntiyByPK(model.getCareerTransitionId());
    		
    		careerTransitionInbox.setJabatanName(jabatan.getName());
    		careerTransitionInbox.setGolonganJabatanName(golJab.getCode());
    		careerTransitionInbox.setEmployeeTypeName(employeeType.getName());
    		careerTransitionInbox.setTransitionRole(careerTransition.getTransitionRole());
    		careerTransitionInbox.setNotes(model.getNotes());
    	}
    	return listModel;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalgetEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter) throws Exception {
    	return empCareerHistoryDao.getTotalgetEntityEmpCareerHistoryInboxByParam(searchParameter);
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmpEliminationViewModel> getListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter, int firstResult, int maxResults, Order order)	throws Exception {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		List<EmpEliminationViewModel> listEmpEliminationViewModel = empCareerHistoryDao.getListEmpEliminationViewModelByParam(searchParameter, firstResult, maxResults, order);
		
		for(EmpEliminationViewModel model : listEmpEliminationViewModel){
			EmpData empData = empDataDao.getByEmpDataByBioDataId(model.getBioDataId());
			model.setEmpName(empData.getBioData().getFullName());
			model.setJoinDate(empData.getJoinDate());
			model.setReason(getReasonByEmpCareerHistoryStatus(model.getEmpCareerHistoryStatus(), resourceBundle));
		}
		return listEmpEliminationViewModel;
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) throws Exception {
		return empCareerHistoryDao.getTotalListEmpEliminationViewModelByParam(searchParameter);
	}
	
	private String getReasonByEmpCareerHistoryStatus(String status, ResourceBundle resourceBundle){
		String reason = StringUtils.EMPTY;
		
		switch (status) {
		case HRMConstant.EMP_STOP_CONTRACT:
			reason = resourceBundle.getString("career.employee_elimination_status_stop_contract");
			break;
			
		case HRMConstant.EMP_TERMINATION:
			reason = resourceBundle.getString("career.employee_elimination_status_resign");
			break;
			
		case HRMConstant.EMP_LAID_OFF:
			reason = resourceBundle.getString("career.employee_elimination_status_laid_off");
			break;
			
		case HRMConstant.EMP_PENSION:
			reason = resourceBundle.getString("finance.pension");
			break;
			
		case HRMConstant.EMP_DISCHAGED:
			reason = resourceBundle.getString("career.employee_elimination_status_discharge");
			break;

		default:
			break;
		}
		
		return reason;
	}
	
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public EmpCareerHistory getEntityByApprovalActivityNumber(String approvalActivityNumber) throws Exception {
		EmpCareerHistory empCareerHistory = empCareerHistoryDao.getEntityByApprovalActivityNumber(approvalActivityNumber);
		/*Jabatan jabatan = jabatanDao.getJabatanByIdWithDetail(empCareerHistory.getJabatan().getId());
		empCareerHistory.setCompanyName(jabatan.getDepartment().getCompany().getName());*/
		return empCareerHistory;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpCareerHistory> getPreviousEmpCareerByBioDataIdAndCurrentCreatedOn(Long bioDataId, Date currentCreatedOn) throws Exception {
		return empCareerHistoryDao.getPreviousEmpCareerByBioDataIdAndCurrentCreatedOn(bioDataId, currentCreatedOn);
	}

}
