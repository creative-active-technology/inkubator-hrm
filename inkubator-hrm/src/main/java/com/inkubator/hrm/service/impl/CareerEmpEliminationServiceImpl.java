package com.inkubator.hrm.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.CareerEmpEliminationDao;
import com.inkubator.hrm.dao.CareerTerminationTypeDao;
import com.inkubator.hrm.dao.EmpCareerHistoryDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.SystemCareerConstDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.CareerEmpElimination;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.SystemCareerConst;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.CareerEmpEliminationService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;
import com.inkubator.webcore.util.FacesUtil;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Service(value = "careerEmpEliminationService")
@Lazy
public class CareerEmpEliminationServiceImpl extends BaseApprovalServiceImpl  implements CareerEmpEliminationService {
	
	@Autowired
	private CareerTerminationTypeDao careerTerminationTypeDao;
	@Autowired
	private CareerEmpEliminationDao careerEmpEliminationDao;
	@Autowired
	private ApprovalDefinitionDao approvalDefinitionDao;
	@Autowired
	private SystemCareerConstDao systemCareerConstDao;
	@Autowired
	private EmpCareerHistoryDao empCareerHistoryDao;
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private GolonganJabatanDao golonganJabatanDao;
	@Autowired
	private EmployeeTypeDao employeeTypeDao;
	@Autowired
	private WtPeriodeDao wtPeriodeDao;
	@Autowired
	private JabatanDao jabatanDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
	private BioDataDao bioDataDao;
	
	
	@Override
	public void delete(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CareerEmpElimination> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllData(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllData(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllDataPageAble(int arg0, int arg1, Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Boolean arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Integer arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareerEmpElimination> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Byte arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(String arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(String arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(String arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Integer arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Integer arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Integer arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Long arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Long arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntityByPkIsActive(Long arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareerEmpElimination getEntiyByPK(Long arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CareerEmpElimination saveData(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CareerEmpElimination saveOrUpdateData(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CareerEmpElimination updateData(CareerEmpElimination arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<EmpEliminationViewModel> getListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter, int firstResult, int maxResults, Order order)	throws Exception {
		/*List<EmpEliminationViewModel> listModel =  careerEmpEliminationDao.getListEmpEliminationViewModelByParam(searchParameter, firstResult, maxResults, order);
		return listModel;*/
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		List<EmpEliminationViewModel> listEmpEliminationViewModel = careerEmpEliminationDao.getListEmpEliminationViewModelByParam(searchParameter, firstResult, maxResults, order);
		
		for(EmpEliminationViewModel model : listEmpEliminationViewModel){
			EmpData empData = empDataDao.getByEmpDataByBioDataId(model.getBioDataId());
			model.setEmpName(empData.getBioData().getFullName());
			model.setReason(model.getReason());
			//model.setReason(getReasonByEmpCareerHistoryStatus(model.getEmpCareerHistoryStatus(), resourceBundle));
		}
		
		return listEmpEliminationViewModel;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) throws Exception {
		return careerEmpEliminationDao.getTotalListEmpEliminationViewModelByParam(searchParameter);
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
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveCareerEmpEliminationWithApproval(CareerEmpElimination careerEmpElimination) throws Exception {
		String result = "error";
		
		String isValid = isEliminationProcessValid(careerEmpElimination);
		if(!StringUtils.equals(isValid, "yes")){
			throw new BussinessException(isValid);
		}
		
		result = this.save(careerEmpElimination, Boolean.FALSE, null);
		return result;
	}
	
	private String isEliminationProcessValid(CareerEmpElimination careerEmpElimination){
		List<ApprovalDefinition> listEmpEliminationApprovalDefinition = approvalDefinitionDao.getAllDataByName(HRMConstant.EMPLOYEE_ELIMINATION);
		if(listEmpEliminationApprovalDefinition.isEmpty()){
			return "career.employee_elimination_approval_def_not_found";
		}
		
		return "yes";
	}
	
	 private String save(CareerEmpElimination careerEmpElimination, Boolean isBypassApprovalChecking,  Long revisedApprActivityId) throws Exception{
		 String result = "error";
		 ApprovalActivity approvalActivity = checkApprovalIfAny(isBypassApprovalChecking);
		 if (approvalActivity == null) {
			 
			 //Save CareerEmpElimination
			 careerEmpElimination.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			 careerEmpElimination.setEmpData(empDataDao.getEntiyByPK(careerEmpElimination.getEmpData().getId()));
			 careerEmpElimination.setCareerTerminationType(careerTerminationTypeDao.getEntiyByPK(careerEmpElimination.getCareerTerminationType().getId()));
			 careerEmpElimination.setWtPeriode(wtPeriodeDao.getEntiyByPK(careerEmpElimination.getWtPeriode().getId()));
			 careerEmpElimination.setCreatedBy(careerEmpElimination.getCreatedBy() == null ? HrmUserInfoUtil.getUserName() : careerEmpElimination.getCreatedBy());
			 careerEmpElimination.setCreatedOn(new Date());
			 careerEmpEliminationDao.save(careerEmpElimination);
			 
			 //Update Employee Status And Generate EmpCareerHistory if Approved
			 if(careerEmpElimination.getEliminationStatus() == HRMConstant.EMP_ELIMINATION_APPROVED){
				//Update Status Karyawan yang telah di approve proses terminasinya
				 CareerTerminationType careerTerminationType = careerTerminationTypeDao.getEntityWithDetailById(careerEmpElimination.getCareerTerminationType().getId());
				 SystemCareerConst systemCareerConst = systemCareerConstDao.getEntiyByPK(careerTerminationType.getSystemCareerConst().getId());
				 EmpData empData = empDataDao.getEntiyByPK(careerEmpElimination.getEmpData().getId());
				 empData.setStatus(systemCareerConst.getConstant());
				 empDataDao.update(empData);
				 
				 //Generate EmpCareerHistory
				 EmpCareerHistory empCareerHistory = generateEmpCareerHistory(careerEmpElimination);
				 empCareerHistoryDao.save(empCareerHistory);
			 }
			 
			 
			 result = "success_without_approval";
			 
		 }else{
			approvalActivity.setPendingData(getJsonData(careerEmpElimination));
			approvalActivityDao.save(approvalActivity);
			result = "success_need_approval";

			// sending email/sms notification
			this.sendingApprovalNotification(approvalActivity);
		 }
		 return result;
	 }
	
	 private ApprovalActivity checkApprovalIfAny(Boolean isBypassApprovalChecking) throws Exception{
		 HrmUser requestUser = hrmUserDao.getByEmpDataId(HrmUserInfoUtil.getEmpId());
		 List<ApprovalDefinition> listEmpEliminationApprovalDefinition = approvalDefinitionDao.getAllDataByName(HRMConstant.EMPLOYEE_ELIMINATION);
		 return isBypassApprovalChecking ? null : super.checkApprovalProcess(listEmpEliminationApprovalDefinition, requestUser.getUserId());
		 
	 }
	 
	 private String getJsonData(CareerEmpElimination careerEmpElimination){
		//parsing object to json 
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(careerEmpElimination));
		jsonObject.addProperty(HRMConstant.CONTEXT_PATH, FacesUtil.getRequest().getContextPath());
		return gson.toJson(jsonObject);
	 }
	 
	 private EmpCareerHistory generateEmpCareerHistory(CareerEmpElimination careerEmpElimination){
		 EmpData empData = empDataDao.getByEmpIdWithDetail(careerEmpElimination.getEmpData().getId());
		 BioData bioData = bioDataDao.getEntiyByPK(empData.getBioData().getId());
		 EmpCareerHistory empCareerHistory = new EmpCareerHistory();
		 empCareerHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		 empCareerHistory.setBioData(bioData);
		 empCareerHistory.setGolonganJabatan(golonganJabatanDao.getEntiyByPK(empData.getGolonganJabatan().getId()));
		 empCareerHistory.setJabatan(jabatanDao.getEntiyByPK(empData.getJabatanByJabatanId().getId()));
		 empCareerHistory.setJoinDate(empData.getJoinDate());
		 empCareerHistory.setNik(empData.getNik());
		 empCareerHistory.setEmployeeType(employeeTypeDao.getEntiyByPK(empData.getEmployeeType().getId()));
		 empCareerHistory.setSalary(empData.getBasicSalary());
		 empCareerHistory.setCreatedBy(HrmUserInfoUtil.getUserName());
		 empCareerHistory.setCreatedOn(new Date());
		 return empCareerHistory;
	 }
	 
	 private CareerEmpElimination convertJsonToEntity(String jsonData){
		 Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		 CareerEmpElimination careerEmpElimination = gson.fromJson(jsonData, CareerEmpElimination.class);
		 return careerEmpElimination;
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
			CareerEmpElimination careerEmpElimination = convertJsonToEntity(appActivity.getPendingData());
			careerEmpElimination.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval 
			careerEmpElimination.setEliminationStatus(HRMConstant.EMP_ELIMINATION_APPROVED);
			/**
             * saving to DB
             */
            this.save(careerEmpElimination, Boolean.TRUE, null);
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
			/**
             * kalau status akhir sudah di reject dan tidak ada next approval,
             * berarti langsung insert ke database
             */
			CareerEmpElimination careerEmpElimination = convertJsonToEntity(appActivity.getPendingData());
			careerEmpElimination.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval 
			careerEmpElimination.setEliminationStatus(HRMConstant.EMP_ELIMINATION_REJECTED);
			/**
             * saving to DB
             */
            this.save(careerEmpElimination, Boolean.TRUE, null);
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
			CareerEmpElimination careerEmpElimination = convertJsonToEntity(appActivity.getPendingData());
			careerEmpElimination.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval 
			careerEmpElimination.setEliminationStatus(HRMConstant.EMP_ELIMINATION_APPROVED);

            this.save(careerEmpElimination, Boolean.TRUE, null);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
	}

	@Override
	protected void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
		//send sms notification to requester if need revision
		super.sendApprovalSmsnotif(appActivity);
	}

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		CareerEmpElimination entity = this.convertJsonToEntity(appActivity.getPendingData());
		EmpData empDataToTerminate = empDataDao.getByEmpIdWithDetail(entity.getEmpData().getId());
		detail.append("Pengajuan Terminasi Karyawan, diajukan oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
		detail.append("Atas Nama : " + empDataToTerminate.getNikWithFullName() + ". ");
		detail.append("Dengan Jabatan Terakhir : " + empDataToTerminate.getJabatanByJabatanId().getName() + ".");
		return detail.toString();
	}

	

}
