package com.inkubator.hrm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.dao.LeaveDistributionDao;
import com.inkubator.hrm.dao.LeaveImplementationDao;
import com.inkubator.hrm.dao.LeaveImplementationDateDao;
import com.inkubator.hrm.dao.NeracaCutiDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.service.WtScheduleShiftService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.ReportLeaveDataViewModel;
import com.inkubator.hrm.web.search.LeaveImplementationReportSearchParameter;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;
import com.inkubator.hrm.web.search.ReportLeaveDataSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "leaveImplementationService")
@Lazy
public class LeaveImplementationServiceImpl extends BaseApprovalServiceImpl implements LeaveImplementationService {

    @Autowired
    private LeaveImplementationDao leaveImplementationDao;
    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private WtScheduleShiftService wtScheduleShiftService;
    @Autowired
    private LeaveDistributionDao leaveDistributionDao;
    @Autowired
    private NeracaCutiDao neracaCutiDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private LeaveImplementationDateDao leaveImplementationDateDao;
    @Autowired
    private TransactionCodeficationDao transactionCodeficationDao;

    @Override
    public LeaveImplementation getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LeaveImplementation getEntiyByPK(Long id) throws Exception {
        return leaveImplementationDao.getEntiyByPK(id);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(LeaveImplementation entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		/*// check duplicate number filling
         long totalDuplicates = leaveImplementationDao.getTotalByNumberFilling(entity.getNumberFilling());
         if (totalDuplicates > 0) {
         throw new BussinessException("leaveimplementation.error_duplicate_filling_number");
         }		
		
         EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
         Leave leave = leaveDao.getEntiyByPK(entity.getLeave().getId());
         EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;
         LeaveDistribution leaveDistribution = leaveDistributionDao.getEntityByLeaveIdAndEmpDataId(leave.getId(), empData.getId());
		
         // check submitted leave tidak boleh lebih besar dari batasPengajuan di leaveDefinition
         long differenceDaysOfFilling = DateTimeUtil.getTotalDayDifference (new Date(), entity.getStartDate());
         if(differenceDaysOfFilling > leave.getSubmittedLimit()){
         throw new BussinessException("leaveimplementation.error_submitted_limit");
         }
				
         // check actualLeave yg diambil, tidak boleh lebih besar dari balanceCuti yg tersedia
         Double actualLeave = this.getTotalActualLeave(empData.getId(), leave.getId(), entity.getStartDate(), entity.getEndDate());		
         if(actualLeave > leaveDistribution.getBalance()){
         throw new BussinessException("leaveimplementation.error_leave_balance is insufficient");
         }
				
         entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
         entity.setEmpData(empData);
         entity.setLeave(leave);
         entity.setTemporaryActing(temporaryActing);
		
         String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
         Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
         entity.setCreatedBy(createdBy);
         entity.setCreatedOn(createdOn);
		
         leaveImplementationDao.save(entity);
		
         if(leave.getIsQuotaReduction()){
         this.creditLeaveBalance(leaveDistribution, actualLeave);
         }*/
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(LeaveImplementation entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
        // check duplicate number filling
		/*long totalDuplicates = leaveImplementationDao.getTotalByNumberFillingAndNotId(entity.getNumberFilling(), entity.getId());
         if (totalDuplicates > 0) {
         throw new BussinessException("leaveimplementation.error_duplicate_filling_number");
         }		
		
         LeaveImplementation leaveImplementation = leaveImplementationDao.getEntiyByPK(entity.getId());		

         EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
         Leave leave = leaveDao.getEntiyByPK(entity.getLeave().getId());
         EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;

         leaveImplementation.setEmpData(empData);
         leaveImplementation.setLeave(leave);
         leaveImplementation.setTemporaryActing(temporaryActing);
         leaveImplementation.setNumberFilling(entity.getNumberFilling());
         leaveImplementation.setStartDate(entity.getStartDate());
         leaveImplementation.setEndDate(entity.getEndDate());
         leaveImplementation.setFillingDate(entity.getFillingDate());
         leaveImplementation.setAddress(entity.getAddress());
         leaveImplementation.setMobilePhone(entity.getMobilePhone());
         leaveImplementation.setMaterialJobsAbandoned(entity.getMaterialJobsAbandoned());
         leaveImplementation.setDescription(entity.getDescription());
         leaveImplementation.setUpdatedBy(UserInfoUtil.getUserName());
         leaveImplementation.setUpdatedOn(new Date());
		
         leaveImplementationDao.update(leaveImplementation);*/
    }

    @Override
    public void saveOrUpdate(LeaveImplementation enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation saveData(LeaveImplementation entity)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation updateData(LeaveImplementation entity)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation saveOrUpdateData(LeaveImplementation entity)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntityByPkIsActive(String id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntityByPkIsActive(String id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntityByPkIsActive(String id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntityByPkIsActive(Integer id,
            Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntityByPkIsActive(Integer id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntityByPkIsActive(Integer id,
            Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntityByPkIsActive(Long id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntityByPkIsActive(Long id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public LeaveImplementation getEntityByPkIsActive(Long id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(LeaveImplementation entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(LeaveImplementation entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<LeaveImplementation> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<LeaveImplementation> getAllData(Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<LeaveImplementation> getAllData(Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<LeaveImplementation> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<LeaveImplementation> getAllDataPageAble(int firstResult,
            int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<LeaveImplementation> getAllDataPageAbleIsActive(
            int firstResult, int maxResults, Order order, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<LeaveImplementation> getAllDataPageAbleIsActive(
            int firstResult, int maxResults, Order order, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<LeaveImplementation> getAllDataPageAbleIsActive(
            int firstResult, int maxResults, Order order, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

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
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            JsonObject jsonObject = gson.fromJson(pendingData, JsonObject.class);

            if (jsonObject.get("isCancellationProcess") != null) {
                Long leaveImplementationId = jsonObject.get("id").getAsLong();
                List<LeaveImplementationDate> cancellationDates = gson.fromJson(jsonObject.get("cancellationDates"), new TypeToken<List<LeaveImplementationDate>>() {
                }.getType());
                List<LeaveImplementationDate> actualDates = gson.fromJson(jsonObject.get("actualDates"), new TypeToken<List<LeaveImplementationDate>>() {
                }.getType());
                String cancellationDescription = jsonObject.get("cancellationDescription").getAsString();
                this.cancellationProcess(leaveImplementationId, actualDates, cancellationDates, cancellationDescription);

            } else {
                LeaveImplementation leaveImplementation = gson.fromJson(pendingData, LeaveImplementation.class);
                leaveImplementation.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose			
                this.save(leaveImplementation, true);
            }
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
             * kalau bukan cancellation berarti langsung insert ke database
             */
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            JsonObject jsonObject = gson.fromJson(pendingData, JsonObject.class);
            if (jsonObject.get("isCancellationProcess") == null) {
                LeaveImplementation leaveImplementation = gson.fromJson(pendingData, LeaveImplementation.class);
                leaveImplementation.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose			
                this.save(leaveImplementation, true);
            }
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
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            JsonObject jsonObject = gson.fromJson(pendingData, JsonObject.class);

            if (jsonObject.get("isCancellationProcess") != null) {
                Long leaveImplementationId = jsonObject.get("id").getAsLong();
                List<LeaveImplementationDate> cancellationDates = gson.fromJson(jsonObject.get("cancellationDates"), new TypeToken<List<LeaveImplementationDate>>() {
                }.getType());
                List<LeaveImplementationDate> actualDates = gson.fromJson(jsonObject.get("actualDates"), new TypeToken<List<LeaveImplementationDate>>() {
                }.getType());
                String cancellationDescription = jsonObject.get("cancellationDescription").getAsString();
                this.cancellationProcess(leaveImplementationId, actualDates, cancellationDates, cancellationDescription);

            } else {
                LeaveImplementation leaveImplementation = gson.fromJson(pendingData, LeaveImplementation.class);
                leaveImplementation.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose			
                this.save(leaveImplementation, true);
            }
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
    }

    @Override
    public void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
    	//send sms notification to approver if need approval OR
        //send sms notification to requester if need revision
		super.sendApprovalSmsnotif(appActivity);
		
		//initialization
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }

        //parsing object data to json, for email purpose
        LeaveImplementation leaveImplementation = gson.fromJson(appActivity.getPendingData(), LeaveImplementation.class);
        Leave leave = leaveDao.getEntiyByPK(leaveImplementation.getLeave().getId());
        String cancellationDate = StringUtils.EMPTY;
        JsonObject jsonObject = gson.fromJson(appActivity.getPendingData(), JsonObject.class);
        if (jsonObject.get("isCancellationProcess") != null) {
            List<LeaveImplementationDate> cancellations = gson.fromJson(jsonObject.get("cancellationDates"), new TypeToken<List<LeaveImplementationDate>>() {
            }.getType());
            StringBuffer sb = new StringBuffer();
            for (LeaveImplementationDate li : cancellations) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(dateFormat.format(li.getActualDate()));
            }
            cancellationDate = sb.toString();
        }

        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("proposeDate", dateFormat.format(leaveImplementation.getCreatedOn()));
            jsonObj.put("leaveName", leave.getName());
            jsonObj.put("startDate", dateFormat.format(leaveImplementation.getStartDate()));
            jsonObj.put("endDate", dateFormat.format(leaveImplementation.getEndDate()));
            jsonObj.put("fillingDate", dateFormat.format(leaveImplementation.getFillingDate()));
            jsonObj.put("materialJobsAbandoned", leaveImplementation.getMaterialJobsAbandoned());
            jsonObj.put("cancellationDate", cancellationDate);

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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LeaveImplementation> getByParam(LeaveImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return leaveImplementationDao.getByParam(parameter, firstResult, maxResults, orderable);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(LeaveImplementationSearchParameter parameter) throws Exception {
        return leaveImplementationDao.getTotalByParam(parameter);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LeaveImplementation getEntityByPkWithDetail(Long id) throws Exception {
        return leaveImplementationDao.getEntityByPkWithDetail(id);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LeaveImplementation getLatestEntityByEmpDataId(Long empDataId) throws Exception {
        LeaveImplementation latest = null;
        //order by tanggal pengajuan descending
        List<LeaveImplementation> leaveImplementations = leaveImplementationDao.getAllDataByEmpDataId(empDataId, Order.desc("fillingDate"));
        if (!leaveImplementations.isEmpty()) {
            latest = leaveImplementations.get(0);
        }
        return latest;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Double getTotalActualLeave(Long empDataId, Long leaveId, Date startDate, Date endDate) throws Exception {
        List<Date> actualLeaves = this.getAllActualLeave(empDataId, leaveId, startDate, endDate);
        return (double) actualLeaves.size();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<Date> getAllActualLeave(Long empDataId, Long leaveId, Date startDate, Date endDate) throws Exception {
        EmpData empData = empDataDao.getEntiyByPK(empDataId);
        Leave leave = leaveDao.getEntiyByPK(leaveId);
        List<Date> actualLeaves = new ArrayList<Date>();

        if (StringUtils.equals(leave.getDayType(), HRMConstant.LEAVE_DAY_TYPE_WORKING)) {
            actualLeaves = wtScheduleShiftService.getAllWorkingDaysBetween(empData.getId(), startDate, endDate);
        } else if(StringUtils.equals(leave.getDayType(), HRMConstant.LEAVE_DAY_TYPE_CALENDAR)) {
            for (int i = 1; i <= DateTimeUtil.getTotalDay(startDate, endDate); i++) {
                actualLeaves.add(DateUtils.addDays(startDate, i));
            }
        }

        return actualLeaves;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String save(LeaveImplementation entity, boolean isBypassApprovalChecking) throws Exception {
        String message = "error";

        // check duplicate number filling
        long totalDuplicates = leaveImplementationDao.getTotalByNumberFilling(entity.getNumberFilling());
        if (totalDuplicates > 0) {
            throw new BussinessException("leaveimplementation.error_duplicate_filling_number");
        }

        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
        Leave leave = leaveDao.getEntiyByPK(entity.getLeave().getId());
        EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;
        LeaveDistribution leaveDistribution = leaveDistributionDao.getEntityByLeaveIdAndEmpDataId(leave.getId(), empData.getId());

        // check submitted leave tidak boleh lebih besar dari batasPengajuan di leaveDefinition
        long differenceDaysOfFilling = DateTimeUtil.getTotalDayDifference(new Date(), entity.getStartDate());
        if (differenceDaysOfFilling > leave.getSubmittedLimit()) {
            throw new BussinessException("leaveimplementation.error_submitted_limit");
        }

        /** check actualLeave yg diambil, tidak boleh lebih besar dari balanceCuti yg tersedia 
         *  di cek juga kondisi di leaveDefinition apakah boleh minus atau tidak, kalo boleh maka balance tidak boleh lebih besar dari maxAllowedMinus  */
        List<Date> actualLeaves = this.getAllActualLeave(empData.getId(), leave.getId(), entity.getStartDate(), entity.getEndDate());
        int totalActualLeaves = actualLeaves.size();
        if (totalActualLeaves > leaveDistribution.getBalance()) {
        	if(leave.getIsAllowedMinus()){
        		if((totalActualLeaves - leaveDistribution.getBalance()) > leave.getMaxAllowedMinus()){
        			throw new BussinessException("leaveimplementation.error_leave_balance_is_insufficient");
        		}
        	} else {
        		throw new BussinessException("leaveimplementation.error_leave_balance_is_insufficient");
        	}
        }

        entity.setEmpData(empData);
        entity.setLeave(leave);
        entity.setTemporaryActing(temporaryActing);
        //Set Kodefikasi pada nomor
    	TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.LEAVE_CODE);
		Long currentMaxLoanId = leaveImplementationDao.getCurrentMaxId();
		if (currentMaxLoanId == null) {
			currentMaxLoanId = 0L;
		}
		entity.setNumberFilling(KodefikasiUtil.getKodefikasi(((int)currentMaxLoanId.longValue()), transactionCodefication.getCode()));
		

        String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);

        HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        List<ApprovalDefinition> appDefs = Lambda.extract(leave.getApprovalDefinitionLeaves(), Lambda.on(ApprovalDefinitionLeave.class).getApprovalDefinition());

        // check jika ada cuti yang masih diproses approval, hanya boleh mengajukan cuti jika tidak ada approval yang pending
        if (approvalActivityDao.isStillHaveWaitingStatus(appDefs, requestUser.getUserId())) {
            throw new BussinessException("leaveimplementation.error_still_have_waiting_status");
        }

        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());
        if (approvalActivity == null) {
            //save entity dan detail-nya
            leaveImplementationDao.save(entity);
            this.saveLeaveImplementationDate(entity, actualLeaves);
            this.creditLeaveBalance(leave, leaveDistribution, totalActualLeaves, createdBy);

            message = "success_without_approval";
        } else {
            //parsing object to json and save to approval activity 
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            approvalActivity.setPendingData(gson.toJson(entity));
            approvalActivityDao.save(approvalActivity);

            message = "success_need_approval";

            //sending email notification
            this.sendingApprovalNotification(approvalActivity);
        }

        return message;
    }

    private void creditLeaveBalance(Leave leave, LeaveDistribution leaveDistribution, double actualLeave, String userBy) {
        //cek di definisi cuti, jika isQuotaReduction is true, maka neraca cuti berkurang
        if (leave.getIsQuotaReduction()) {
            double balance = leaveDistribution.getBalance() - actualLeave;
            leaveDistribution.setBalance(balance);
            leaveDistribution.setUpdatedOn(new Date());
            leaveDistribution.setUpdatedBy(userBy);
            leaveDistributionDao.update(leaveDistribution);

            NeracaCuti neracaCuti = new NeracaCuti();
            neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            neracaCuti.setLeaveDistribution(leaveDistribution);
            neracaCuti.setKredit(actualLeave);
            neracaCuti.setCreatedBy(userBy);
            neracaCuti.setCreatedOn(new Date());
            neracaCutiDao.save(neracaCuti);
        }
    }

    private void debetLeaveBalance(Leave leave, LeaveDistribution leaveDistribution, double actualLeave, String userBy) {
        //cek di definisi cuti, jika isQuotaReduction is true, maka neraca cuti bertambah
        if (leave.getIsQuotaReduction()) {
            double balance = leaveDistribution.getBalance() + actualLeave;
            leaveDistribution.setBalance(balance);
            leaveDistribution.setUpdatedOn(new Date());
            leaveDistribution.setUpdatedBy(userBy);
            leaveDistributionDao.update(leaveDistribution);

            NeracaCuti neracaCuti = new NeracaCuti();
            neracaCuti.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            neracaCuti.setLeaveDistribution(leaveDistribution);
            neracaCuti.setDebet(actualLeave);
            neracaCuti.setCreatedBy(userBy);
            neracaCuti.setCreatedOn(new Date());
            neracaCutiDao.save(neracaCuti);
        }
    }

    private void saveLeaveImplementationDate(LeaveImplementation leaveImplementation, List<Date> actualLeaves) {
        for (Date actualLeave : actualLeaves) {
            LeaveImplementationDate entity = new LeaveImplementationDate();
            entity.setLeaveImplementation(leaveImplementation);
            entity.setActualDate(actualLeave);
            entity.setIsCancelled(Boolean.FALSE);
            leaveImplementationDateDao.save(entity);
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LeaveImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber) throws Exception {
        return leaveImplementationDao.getEntityByApprovalActivityNumberWithDetail(activityNumber);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String cancellation(Long leaveImplementationId, List<LeaveImplementationDate> actualLeaves, List<LeaveImplementationDate> cancellationLeaves, String cancellationDescription) throws Exception {
        String message = "success_without_approval";
        Date now = new Date();

        /**
         * jika tanggal pembatalan telah melewati tanggal pelaksanaan, maka
         * memerlukan approval/persetujuan process
         */
        Boolean isNeedApprovalChecking = Lambda.selectFirst(cancellationLeaves, Lambda.having(Lambda.on(LeaveImplementationDate.class).getActualDate().getTime(), Matchers.lessThan(now.getTime()))) != null;
        LeaveImplementation leaveImplementation = leaveImplementationDao.getEntiyByPK(leaveImplementationId);

        if (isNeedApprovalChecking) {
            //check approval process
            HrmUser requestUser = hrmUserDao.getByEmpDataId(leaveImplementation.getEmpData().getId());
            ApprovalActivity approvalActivity = super.checkApprovalProcess(HRMConstant.LEAVE_CANCELLATION, requestUser.getUserId());
            if (approvalActivity == null) {
                this.cancellationProcess(leaveImplementationId, actualLeaves, cancellationLeaves, cancellationDescription);

            } else {
                //parsing object to json and save to approval activity 
                JsonParser parser = new JsonParser();
                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
                JsonObject jsonObj = (JsonObject) parser.parse(gson.toJson(leaveImplementation));
                JsonArray cancellations = new JsonArray();
                for (LeaveImplementationDate ld : cancellationLeaves) {
                    JsonObject component = (JsonObject) parser.parse(gson.toJson(ld));
                    cancellations.add(component);
                }
                JsonArray actuals = new JsonArray();
                for (LeaveImplementationDate ld : actualLeaves) {
                    JsonObject component = (JsonObject) parser.parse(gson.toJson(ld));
                    actuals.add(component);
                }
                jsonObj.add("cancellationDates", cancellations);
                jsonObj.add("actualDates", actuals);
                jsonObj.addProperty("cancellationDescription", cancellationDescription);
                jsonObj.addProperty("isCancellationProcess", Boolean.TRUE);

                //save approval activity with previous activiy number, for approval historical purpose
                approvalActivity.setPendingData(gson.toJson(jsonObj));
                approvalActivity.setPreviousActivityNumber(leaveImplementation.getApprovalActivityNumber());
                approvalActivityDao.save(approvalActivity);

                message = "success_need_approval";

                //sending email notification
                this.sendingApprovalNotification(approvalActivity);
            }
        } else {
            this.cancellationProcess(leaveImplementationId, actualLeaves, cancellationLeaves, cancellationDescription);
        }

        return message;
    }

    private void cancellationProcess(Long leaveImplementationId, List<LeaveImplementationDate> actualLeaves, List<LeaveImplementationDate> cancellationLeaves, String cancellationDescription) throws Exception {
        double total = 0.0;

        for (LeaveImplementationDate entity : actualLeaves) {
        	//ini artinya ada perubahan, dari yang awalnya cuti sudah dicancel, dikembalikan ke actual(ambil cuti), sehingga harus ada pengurangan neraca cuti
            if (entity.getIsCancelled()) {
                total = total - 1;
                entity = leaveImplementationDateDao.getEntiyByPK(entity.getId());
                entity.setIsCancelled(Boolean.FALSE);
                entity.setDescription(cancellationDescription);
                entity.setUpdatedBy(UserInfoUtil.getUserName());
                entity.setUpdatedOn(new Date());
                leaveImplementationDateDao.update(entity);
            }
        }
        for (LeaveImplementationDate entity : cancellationLeaves) {
        	//ini artinya ada pembatalan cuti, sehingga harus ada penambahan neraca cuti
        	if (!entity.getIsCancelled()) {
                total = total + 1;
                entity = leaveImplementationDateDao.getEntiyByPK(entity.getId());
                entity.setIsCancelled(Boolean.TRUE);
                entity.setDescription(cancellationDescription);
                entity.setUpdatedBy(UserInfoUtil.getUserName());
                entity.setUpdatedOn(new Date());
                leaveImplementationDateDao.update(entity);
            }
        }

        //lakukan proses penambahan atau pengurangan neraca cuti, berdasarkan perhitungan di proses sebelumnya
        LeaveImplementation leaveImplementation = leaveImplementationDao.getEntiyByPK(leaveImplementationId);
        LeaveDistribution leaveDistribution = leaveDistributionDao.getEntityByLeaveIdAndEmpDataId(leaveImplementation.getLeave().getId(), leaveImplementation.getEmpData().getId());
        if (total > 0) {
            this.debetLeaveBalance(leaveImplementation.getLeave(), leaveDistribution, total, UserInfoUtil.getUserName());
        } else if (total < 0) {
            total = total * (-1);
            this.creditLeaveBalance(leaveImplementation.getLeave(), leaveDistribution, total, UserInfoUtil.getUserName());
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LeaveImplementation> getReportByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers , Long empDataId, int firstResult, int maxResults, Order orderable) throws Exception {
        return leaveImplementationDao.getReportByParam(parameter, activityNumbers, empDataId, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getReportTotalByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId ) throws Exception {
        return leaveImplementationDao.getReportTotalByParam(parameter, activityNumbers, empDataId);
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LeaveImplementation> getReportHistoryByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers , Long empDataId) throws Exception {
        return leaveImplementationDao.getReportHistoryByParam(parameter, activityNumbers, empDataId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<LeaveImplementation> getAllDataByEmpDataId(Long empDataId) throws Exception {
        return leaveImplementationDao.getAllDataByEmpDataId(empDataId);
    }

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		LeaveImplementation entity = gson.fromJson(appActivity.getPendingData(), LeaveImplementation.class);
		
		detail.append("Pengajuan cuti oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
		detail.append("Jenis: " + entity.getLeave().getName() + ". ");
		detail.append("Dari tanggal " + dateFormat.format(entity.getStartDate()) + " s/d " + dateFormat.format(entity.getEndDate()));
		return detail.toString();
	}

	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<ReportLeaveDataViewModel> getAllDataLeaveReport(ReportLeaveDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		
		List<ReportLeaveDataViewModel> listReport = leaveImplementationDao.getAllDataLeaveReport(parameter, firstResult, maxResults, orderable);
		for(ReportLeaveDataViewModel reportModel : listReport){
			
			if(null != reportModel.getActivityNumber()){
				List<ApprovalActivity> listApproval = approvalActivityDao.getAllDataByActivityNumberWithDetail(reportModel.getActivityNumber(), Order.desc("sequence"));
				if(listApproval.isEmpty()){
					reportModel.setLastApproverName("-");
				}else{
					HrmUser hrmUser = hrmUserDao.getUserWithDetailByUserId(listApproval.get(0).getApprovedBy());
					reportModel.setLastApproverNik(hrmUser.getEmpData().getNik());
					reportModel.setLastApproverName(hrmUser.getRealName());
				}
			}
			
		}
		return listReport;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalLeaveDataReport(ReportLeaveDataSearchParameter parameter) throws Exception {
		return leaveImplementationDao.getTotalLeaveDataReport(parameter);
	}
}
