package com.inkubator.hrm.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
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

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.CurrencyDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.OrgTypeOfSpecListDao;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.dao.RecruitHireApplyDetailDao;
import com.inkubator.hrm.dao.RecruitMppPeriodDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantInitialDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitHireApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import com.inkubator.hrm.web.model.RecruitmentScheduleSettingViewModel;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;
import com.inkubator.hrm.web.search.RecruitmentScheduleSettingSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author WebGenX
 */
@Service(value = "recruitHireApplyService")
@Lazy
public class RecruitHireApplyServiceImpl extends BaseApprovalServiceImpl implements RecruitHireApplyService {

    @Autowired
    private RecruitHireApplyDao recruitHireApplyDao;

    @Autowired
    private RecruitHireApplyDetailDao recruitHireApplyDetailDao;

    @Autowired
    private ApprovalActivityDao approvalActivityDao;

    @Autowired
    private ApprovalDefinitionDao approvalDefinitionDao;

    @Autowired
    private HrmUserDao hrmUserDao;

    @Autowired
    private EmpDataDao empDataDao;

    @Autowired
    private JabatanDao jabatanDao;

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private EmployeeTypeDao employeeTypeDao;

    @Autowired
    private RecruitMppPeriodDao recruitMppPeriodDao;

    @Autowired
    private TransactionCodeficationDao transactionCodeficationDao;

    @Autowired
    private OrgTypeOfSpecListDao orgTypeOfSpecListDao;
    
    @Autowired
    private RecruitSelectionApplicantInitialDao recruitSelectionApplicantInitialDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(RecruitHireApply recruitHireApply) throws Exception {
        recruitHireApplyDao.delete(recruitHireApply);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitHireApply recruitHireApply) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = recruitHireApplyDao.getTotalByCode(recruitHireApply.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("recruitHireApply.error_duplicate_code")
//}
        recruitHireApply.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        recruitHireApply.setCreatedBy(UserInfoUtil.getUserName());
        recruitHireApply.setCreatedOn(new Date());
        recruitHireApplyDao.save(recruitHireApply);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitHireApply recruitHireApply) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = recruitHireApplyDao.getTotalByCodeAndNotId(recruitHireApply.getCode(),recruitHireApply.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("recruitHireApply.error_duplicate_code")
//}
        RecruitHireApply recruitHireApply1 = recruitHireApplyDao.getEntiyByPK(recruitHireApply.getId());
        recruitHireApply1.setUpdatedBy(UserInfoUtil.getUserName());
        recruitHireApply1.setUpdatedOn(new Date());
        recruitHireApply1.setEmployeeType(recruitHireApply.getEmployeeType());
        recruitHireApply1.setSalaryMax(recruitHireApply.getSalaryMax());
        recruitHireApply1.setReason(recruitHireApply.getReason());
        recruitHireApply1.setProposeDate(recruitHireApply.getProposeDate());
        recruitHireApply1.setReqHireCode(recruitHireApply.getReqHireCode());
        recruitHireApply1.setAgeMax(recruitHireApply.getAgeMax());
        recruitHireApply1.setRecruitMppPeriod(recruitHireApply.getRecruitMppPeriod());
        recruitHireApply1.setMaritalStatus(recruitHireApply.getMaritalStatus());
        recruitHireApply1.setGpaMin(recruitHireApply.getGpaMin());
        recruitHireApply1.setGpaMax(recruitHireApply.getGpaMax());
        recruitHireApply1.setCurrency(recruitHireApply.getCurrency());
        recruitHireApply1.setJabatan(recruitHireApply.getJabatan());
        recruitHireApply1.setAgeMin(recruitHireApply.getAgeMin());
        recruitHireApply1.setEfectiveDate(recruitHireApply.getEfectiveDate());
        recruitHireApply1.setCandidateCountRequest(recruitHireApply.getCandidateCountRequest());
        recruitHireApply1.setGender(recruitHireApply.getGender());
        recruitHireApply1.setSalaryMin(recruitHireApply.getSalaryMin());
        recruitHireApplyDao.update(recruitHireApply1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitHireApply getEntiyByPK(Long id) {
        return this.recruitHireApplyDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.recruitHireApplyDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter searchParameter) throws Exception {
        return this.recruitHireApplyDao.getTotalRecruitHireApplyByParam(searchParameter);
    }

    @Override
    public RecruitHireApply getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(RecruitHireApply enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply saveData(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply updateData(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply saveOrUpdateData(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<RecruitHireApply> getAllData() throws Exception {
        return recruitHireApplyDao.getAllData();
    }

    @Override
    public List<RecruitHireApply> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitReqHistoryViewModel> getRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        List<RecruitReqHistoryViewModel> listRecruitReqHistoryViewModel = recruitHireApplyDao.getRecruitmentReqActivityByParam(parameter, firstResult, maxResults, orderable);
        setRecruitmentActivityComplexData(listRecruitReqHistoryViewModel);

        return listRecruitReqHistoryViewModel;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter) throws Exception {
        return this.recruitHireApplyDao.getTotalRecruitmentReqActivityByParam(parameter);
    }

    private void setRecruitmentActivityComplexData(List<RecruitReqHistoryViewModel> listRecruitReqHistoryViewModels) {
        for (RecruitReqHistoryViewModel recruitReqHistoryViewModel : listRecruitReqHistoryViewModels) {
            HrmUser hrmUser = hrmUserDao.getUserWithDetailByUserId(recruitReqHistoryViewModel.getRequestBy());
            EmpData empData = empDataDao.getEmpDataWithBiodata(hrmUser.getEmpData().getId());

            if (recruitReqHistoryViewModel.getRhaId() == null) {

                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
                String jsonData = recruitReqHistoryViewModel.getJsonData();
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = (JsonObject) parser.parse(jsonData);

                RecruitHireApply recruitHireApplyTemp = gson.fromJson(jsonObject, RecruitHireApply.class);
                Jabatan jabatan = jabatanDao.getEntiyByPK(recruitHireApplyTemp.getJabatan().getId());
                recruitReqHistoryViewModel.setRecHireCode(recruitHireApplyTemp.getReqHireCode());
                recruitReqHistoryViewModel.setEfectiveDate(recruitHireApplyTemp.getEfectiveDate());
                recruitReqHistoryViewModel.setTotalReq(recruitHireApplyTemp.getCandidateCountRequest());
                recruitReqHistoryViewModel.setJabatanCode(jabatan.getCode());
                recruitReqHistoryViewModel.setJabatanName(jabatan.getName());
                recruitReqHistoryViewModel.setNikRequester(empData.getNik());
                recruitReqHistoryViewModel.setNameRequester(empData.getBioData().getFullName());

            } else {

                RecruitHireApply recruitHireApply = recruitHireApplyDao.getEntityWithDetailByPk(recruitReqHistoryViewModel.getRhaId().longValue());
                recruitReqHistoryViewModel.setRecHireCode(recruitHireApply.getReqHireCode());
                recruitReqHistoryViewModel.setEfectiveDate(recruitHireApply.getEfectiveDate());
                recruitReqHistoryViewModel.setTotalReq(recruitHireApply.getCandidateCountRequest());
                recruitReqHistoryViewModel.setJabatanCode(recruitHireApply.getJabatan().getCode());
                recruitReqHistoryViewModel.setJabatanName(recruitHireApply.getJabatan().getName());
                recruitReqHistoryViewModel.setNikRequester(empData.getNik());
                recruitReqHistoryViewModel.setNameRequester(empData.getBioData().getFullName());
            }
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String saveRecruitHireWithApproval(RecruitHireApply recruitHireApply) throws Exception {
    	String result = StringUtils.EMPTY;
    	Long totalRecruitApprovalDef = approvalDefinitionDao.getTotalApprovalExistWithSequenceOne(HRMConstant.RECRUITMENT_REQUEST);
        String reqHireCode = "RCRQ-" + RandomNumberUtil.getRandomNumber(6);
        
        //If Approval Defintion for RECRUITMENT_REQUEST Process have not been created, throw Exception
        if (totalRecruitApprovalDef <= 0) {
            throw new BussinessException("mppRecruitmentHist.error_approval_def_not_found");
        }

        //If reqHireCode duplicate, re-generate reqHireCode
        while (isRecruitmentHireCodeDuplicate(reqHireCode, null)) {
            reqHireCode = "RCRQ-" + RandomNumberUtil.getRandomNumber(6);
        }
        
        List<RecruitHireApplyDetail> listRecruitHireApplyDetails = new ArrayList<RecruitHireApplyDetail>(recruitHireApply.getRecruitHireApplyDetails());
        String createdBy = StringUtils.isEmpty(recruitHireApply.getCreatedBy()) ? UserInfoUtil.getUserName() : recruitHireApply.getCreatedBy();
        Date createdOn = recruitHireApply.getCreatedOn() == null ? new Date() : recruitHireApply.getCreatedOn();
        
        recruitHireApply.setCreatedBy(createdBy);
        recruitHireApply.setCreatedOn(createdOn);
    	recruitHireApply.setReqHireCode(reqHireCode);
    	
    	result = save(recruitHireApply, listRecruitHireApplyDetails, Boolean.FALSE);
        return result;
     
    }
    
    private String save(RecruitHireApply entity, List<RecruitHireApplyDetail> listRecruitHireApplyDetails, Boolean isBypassApprovalChecking ) throws Exception{
    	String result = "error";
    	
    	String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
        
        try {
        	
			entity.setCreatedBy(createdBy);
			entity.setCreatedOn(createdOn);
			entity.setApplicationStatus(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL);
			ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.RECRUITMENT_REQUEST, createdBy);
			
			if(approvalActivity == null){
				
				entity.setReqHireCode(this.generateRecruitmentRequestNumber());
				entity.setCurrency(currencyDao.getEntiyByPK(entity.getCurrency().getId()));
				entity.setJabatan(jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
				entity.setEmployeeType(employeeTypeDao.getEntiyByPK(entity.getEmployeeType().getId()));
				entity.setRecruitMppPeriod(recruitMppPeriodDao.getEntiyByPK(entity.getRecruitMppPeriod().getId()));
				recruitHireApplyDao.save(entity);
				
				for(RecruitHireApplyDetail hireApplyDetail : listRecruitHireApplyDetails){
					hireApplyDetail.setCreatedBy(createdBy);
					hireApplyDetail.setCreatedOn(createdOn);
					hireApplyDetail.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
					hireApplyDetail.setOrgTypeOfSpecList(orgTypeOfSpecListDao.getEntiyByPK(hireApplyDetail.getOrgTypeOfSpecList().getId()));
					hireApplyDetail.setRecruitHireApply(entity);
					recruitHireApplyDetailDao.save(hireApplyDetail);
				}
				
			    result = "save_without_approval";
				
			}else{
				
				approvalActivity.setPendingData(convertToJsonData(entity, listRecruitHireApplyDetails));
			 	approvalActivityDao.save(approvalActivity);
			 	
			 	//sending email notification
			    this.sendingApprovalNotification(approvalActivity);
			    result = "success_need_approval";
			    
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    	return result;
    }
    
    private String convertToJsonData(RecruitHireApply entity, List<RecruitHireApplyDetail> listHireApplyDetails){
    	 Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
         JsonParser parser = new JsonParser();
         JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));
         JsonArray jsonDetailRecruitHireApply = (JsonArray) parser.parse(gson.toJson(listHireApplyDetails));
         jsonObject.add("listDetailRecruitHireApply", jsonDetailRecruitHireApply);
         jsonObject.addProperty(HRMConstant.CONTEXT_PATH, FacesUtil.getRequest().getContextPath());
         String pendingData = gson.toJson(jsonObject); 
         return pendingData;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateRecruitHireWithApproval(RecruitHireApply recruitHireApply, String activityNumber) throws Exception {
        Long totalRecruitApprovalDef = approvalDefinitionDao.getTotalApprovalExistWithSequenceOne(HRMConstant.RECRUITMENT_REQUEST);

        //If Approval Definition for RECRUITMENT_REQUEST Process have not been created, throw Exception
        if (totalRecruitApprovalDef <= 0) {
            throw new BussinessException("mppRecruitmentHist.error_approval_def_not_found");
        }

        // if activity already approved throw exception
        ApprovalActivity approvalActivity = approvalActivityDao.getApprovalTimeByApprovalActivityNumber(activityNumber);
        if (approvalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) {
            throw new BussinessException("mpp_recruitment.error_activity_already_approved");
        }
        
        String updatedBy = UserInfoUtil.getUserName();
        Date updatedOn = new Date();
        recruitHireApply.setUpdatedBy(updatedBy);
        recruitHireApply.setUpdatedOn(updatedOn);
        recruitHireApply.setApplicationStatus(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL);
        List<RecruitHireApplyDetail> listHireApplyDetails = new ArrayList<RecruitHireApplyDetail>(recruitHireApply.getRecruitHireApplyDetails());
        approvalActivity.setPendingData(convertToJsonData(recruitHireApply, listHireApplyDetails));

        approvalActivityDao.update(approvalActivity);
    }

    private Boolean isRecruitmentHireCodeDuplicate(String reqHireCode, String activityNumber) {
        Boolean result = Boolean.FALSE;

        Long totalDuplicateFromEntity = this.recruitHireApplyDao.getTotalDataByReqHireCode(reqHireCode);
        Long totalDuplicateFormWaitingApprovalActivity = 0l;
        
        //Get List waiting approval activity
        List<ApprovalActivity> listWaitingApproval = this.approvalActivityDao.getAllDataWaitingStatusApprovalFetchedApprovalDef();

        //Filter only activity that comes from RECRUITMENT_REQUEST process
        listWaitingApproval = Lambda.select(listWaitingApproval, Lambda.having(Lambda.on(ApprovalActivity.class).getApprovalDefinition().getName(), Matchers.equalTo(HRMConstant.RECRUITMENT_REQUEST)));

        //grouping list by ActivityNumber
        Group<ApprovalActivity> groupWaitingApprovalActivity = Lambda.group(listWaitingApproval, Lambda.by(Lambda.on(ApprovalActivity.class).getActivityNumber()));

        //iterate each group list element
        for (String key : groupWaitingApprovalActivity.keySet()) {
            List<ApprovalActivity> listGroupedActivity = groupWaitingApprovalActivity.find(key);

            // Get activity with highest sequence from each grouped list activities
            ApprovalActivity approvalActivityWithHighestSequence = Lambda.selectMax(listGroupedActivity, Lambda.on(ApprovalDefinition.class).getSequence());

            // convert json pendingData into RecruitHireApply
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String jsonData = approvalActivityWithHighestSequence.getPendingData();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
            RecruitHireApply recruitHireApply = gson.fromJson(jsonObject, RecruitHireApply.class);

            // chek whether reqHireCode already used
            if (StringUtils.equals(reqHireCode, recruitHireApply.getReqHireCode())
                    && !StringUtils.equals(activityNumber, approvalActivityWithHighestSequence.getActivityNumber())) {
                totalDuplicateFormWaitingApprovalActivity++;
                break; // Break immediately when duplicate found.
            }

        }

        if (totalDuplicateFromEntity > 0 || totalDuplicateFormWaitingApprovalActivity > 0) {
            result = Boolean.TRUE;
        }
        return result;
    }

    @Override
    protected void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
    	//send sms notification to approver if need approval OR
        //send sms notification to requester if need revision
		super.sendApprovalSmsnotif(appActivity);
		
		//initialization
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            try {
				ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        //parsing object data to json, for email purpose
        RecruitHireApply recruitHireApply = gson.fromJson(appActivity.getPendingData(), RecruitHireApply.class);
        RecruitMppPeriod recruitMppPeriod = recruitMppPeriodDao.getEntiyByPK(recruitHireApply.getRecruitMppPeriod().getId());

        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("proposeDate", dateFormat.format(recruitHireApply.getProposeDate()));
            jsonObj.put("periodeStart", dateFormat.format(recruitMppPeriod.getPeriodeStart()));
            jsonObj.put("periodeEnd", dateFormat.format(recruitMppPeriod.getPeriodeEnd()));
            jsonObj.put("jabatan", recruitHireApply.getJabatan().getName());
            jsonObj.put("mppName", recruitHireApply.getRecruitMppPeriod().getName());
            jsonObj.put("salaryMin", recruitHireApply.getSalaryMin());
            jsonObj.put("salaryMax", recruitHireApply.getSalaryMax());
            jsonObj.put("candidateCountRequest", recruitHireApply.getCandidateCountRequest());
            jsonObj.put("urlLinkToApprove", FacesUtil.getRequest().getContextPath() + "" + HRMConstant.RECRUITMENT_REQUEST + "" +"?faces-redirect=true&execution=e" + appActivity.getId());

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
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
        Map<String, Object> result = super.approvedAndCheckNextApproval(approvalActivityId, pendingDataUpdate, comment);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
            /**
             * kalau status akhir sudah di approved dan tidak ada next approval,
             * berarti langsung insert ke database
             */
            RecruitHireApply entity = this.convertJsonToEntity(appActivity.getPendingData());
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());
            entity.setApplicationStatus(HRMConstant.APPROVAL_STATUS_APPROVED);

            List<RecruitHireApplyDetail> listDetail = new ArrayList<>(entity.getRecruitHireApplyDetails());
            this.save(entity, listDetail, Boolean.TRUE);

        }
        
        this.sendingApprovalNotification(appActivity);
        
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
            RecruitHireApply entity = this.convertJsonToEntity(appActivity.getPendingData());
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());
            entity.setApplicationStatus(HRMConstant.APPROVAL_STATUS_REJECTED);

            List<RecruitHireApplyDetail> listDetail = new ArrayList<>(entity.getRecruitHireApplyDetails());
            this.save(entity, listDetail, Boolean.TRUE);
           
        }
        this.sendingApprovalNotification(appActivity);
    }

    private String generateRecruitmentRequestNumber(){
		/** generate number form codification, from recruitment request module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.RECRUITMENT_REQUEST_KODE);
        Long currentMaxId = recruitHireApplyDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}
    
    @Override
    public void diverted(long approvalActivityId) throws Exception {
    	Map<String, Object> result = super.divertedAndCheckNextApproval(approvalActivityId);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
            /**
             * kalau status akhir sudah di approved dan tidak ada next approval,
             * berarti langsung insert ke database
             */
            RecruitHireApply entity = this.convertJsonToEntity(appActivity.getPendingData());
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());
            entity.setApplicationStatus(HRMConstant.APPROVAL_STATUS_APPROVED);

            List<RecruitHireApplyDetail> listDetail = new ArrayList<>(entity.getRecruitHireApplyDetails());
            this.save(entity, listDetail, Boolean.TRUE);
            
        }
        
        this.sendingApprovalNotification(appActivity);
    }

    private RecruitHireApply convertJsonToEntity(String jsonPendingData) {
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(jsonPendingData);
        RecruitHireApply recruitHireApply = gson.fromJson(jsonObject, RecruitHireApply.class);

        JsonArray arrayDetailRecruitmentRequest = jsonObject.getAsJsonArray("listDetailRecruitHireApply");
        HashSet<RecruitHireApplyDetail> setRecruitHireApplyDetail = new HashSet<RecruitHireApplyDetail>();
        if (null != arrayDetailRecruitmentRequest) {
            for (int i = 0; i < arrayDetailRecruitmentRequest.size(); i++) {
                RecruitHireApplyDetail detail = gson.fromJson(arrayDetailRecruitmentRequest.get(i), RecruitHireApplyDetail.class);
                setRecruitHireApplyDetail.add(detail);
            }
        }

        recruitHireApply.setRecruitHireApplyDetails(setRecruitHireApplyDetail);
        return recruitHireApply;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getCurrentMaxId() throws Exception {
        return this.recruitHireApplyDao.getCurrentMaxId();
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public RecruitHireApply getEntityByPkWithDetail(Long id) throws Exception {
        return recruitHireApplyDao.getEntityWithDetailByPk(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitHireApply getEntityWithDetailByActivityNumber(String activityNumber) throws Exception {
        return this.recruitHireApplyDao.getEntityWithDetailByActivityNumber(activityNumber);

    }

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		RecruitHireApply entity = this.convertJsonToEntity(appActivity.getPendingData());
		
		detail.append("Pengajuan rekrutmen untuk jabatan " + entity.getJabatan().getName() + ", oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
		detail.append("Jumlah karyawan " + entity.getCandidateCountRequest() + " orang. ");
		detail.append("Tanggal efektif " + dateFormat.format(entity.getEfectiveDate()) + ". ");
		detail.append("Periode MPP " + dateFormat.format(entity.getRecruitMppPeriod().getPeriodeStart()) + " s/d " + dateFormat.format(entity.getRecruitMppPeriod().getPeriodeEnd()));
		return detail.toString();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitHireApply> getAllDataWithDetail() throws Exception {
		return recruitHireApplyDao.getAllDataWithDetail();
	}
	
	
}
