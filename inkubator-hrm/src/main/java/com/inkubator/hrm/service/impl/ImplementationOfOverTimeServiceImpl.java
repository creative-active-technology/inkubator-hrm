/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.ImplementationOfOverTimeDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.dao.WtOverTimeDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionOT;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.json.util.DateJsonDeserializer;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ImplementationOfOverTimeService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.search.ImplementationOfOvertimeSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
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

/**
 *
 * @author Deni
 */
@Service(value = "implementationOfOverTimeService")
@Lazy
public class ImplementationOfOverTimeServiceImpl extends BaseApprovalServiceImpl implements ImplementationOfOverTimeService {

    @Autowired
    private ImplementationOfOverTimeDao implementationOfOverTimeDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private WtOverTimeDao wtOverTimeDao;
    @Autowired
    private TempJadwalKaryawanService tempJadwalKaryawanService;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private TransactionCodeficationDao transactionCodeficationDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ImplementationOfOverTime> getAllDataWithDetail(ImplementationOfOvertimeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return implementationOfOverTimeDao.getAllDataWithDetail(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalImplementationOfOverTimeByParam(ImplementationOfOvertimeSearchParameter searchParameter) throws Exception {
        return implementationOfOverTimeDao.getTotalImplementationOfOvertimeByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public ImplementationOfOverTime getEntityByPkWithDetail(Long id) throws Exception {
        return implementationOfOverTimeDao.getEntityByPkWithDetail(id);
    }

    @Override
    public ImplementationOfOverTime getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(ImplementationOfOverTime entity) throws Exception {
        // check duplicate name
        long totalDuplicates = implementationOfOverTimeDao.getByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("implementOt.implementation_ot_error_duplicate_code");
        }
        TempJadwalKaryawan jadwalKaryawan = tempJadwalKaryawanService.getEntityByEmpDataIdAndTanggalWaktuKerja(entity.getEmpData().getId(), entity.getImplementationDate());
        if (jadwalKaryawan != null) {
            if (entity.getStartTime().after(jadwalKaryawan.getWtWorkingHour().getWorkingHourBegin()) && entity.getEndTime().before(jadwalKaryawan.getWtWorkingHour().getWorkingHourEnd())) {

                throw new BussinessException("implementationovertime.time_is_in_your_working_hours");
            }
        } else {
            throw new BussinessException("implementationovertime.your_implementation_date_far");
        }
        entity.setEmpData(entity.getEmpData());
        entity.setWtOverTime(wtOverTimeDao.getEntiyByPK(entity.getWtOverTime().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.implementationOfOverTimeDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(ImplementationOfOverTime entity) throws Exception {
        Long overTimeId = entity.getWtOverTime().getId();
        WtOverTime selectTedWtOverTime = wtOverTimeDao.getEntiyByPK(overTimeId);
        Date currentDate = new Date();
        Integer selisihWaktu = DateTimeUtil.getTotalDayDifference(currentDate, entity.getImplementationDate());
        if (selisihWaktu > 0) {
            if (selisihWaktu > selectTedWtOverTime.getBatasMaju()) {
                throw new BussinessException("implementOt.implementation_date_outofrange");
            }
        }

        if (selisihWaktu < 0) {
            if (selisihWaktu < selectTedWtOverTime.getBatasMudur()) {
                throw new BussinessException("implementOt.implementation_date_outofrange");
            }
        }
        long totalDuplicates = implementationOfOverTimeDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("implementOt.implementation_ot_error_duplicate_code");
        }
        ImplementationOfOverTime update = implementationOfOverTimeDao.getEntiyByPK(entity.getId());
        //update.setWtOverTime(wtOverTimeDao.getEntiyByPK(entity.getWtOverTime().getId()));
        //update.setCode(entity.getCode());
        //update.setEmpData(entity.getEmpData());
        //update.setImplementationDate(entity.getImplementationDate());
        update.setDescription(entity.getDescription());
        update.setStartTime(entity.getStartTime());
        update.setEndTime(entity.getEndTime());
        update.setRelativeHour(entity.getRelativeHour());
        update.setRelativeMinute(entity.getRelativeMinute());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.implementationOfOverTimeDao.update(update);
    }

    @Override
    public void saveOrUpdate(ImplementationOfOverTime enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime saveData(ImplementationOfOverTime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime updateData(ImplementationOfOverTime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime saveOrUpdateData(ImplementationOfOverTime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImplementationOfOverTime getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(ImplementationOfOverTime entity) throws Exception {
        this.implementationOfOverTimeDao.delete(entity);
    }

    @Override
    public void softDelete(ImplementationOfOverTime entity) throws Exception {
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
    public List<ImplementationOfOverTime> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImplementationOfOverTime> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImplementationOfOverTime> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImplementationOfOverTime> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImplementationOfOverTime> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImplementationOfOverTime> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImplementationOfOverTime> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImplementationOfOverTime> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
            String pendingData = appActivity.getPendingData();
            ImplementationOfOverTime implementationOfOverTime = gson.fromJson(pendingData, ImplementationOfOverTime.class);
            implementationOfOverTime.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            this.save(implementationOfOverTime, true);
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
             * kalau status akhir sudah di approved dan tidak ada next approval,
             * berarti langsung insert ke database
             */
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
            String pendingData = appActivity.getPendingData();
            ImplementationOfOverTime implementationOfOverTime = gson.fromJson(pendingData, ImplementationOfOverTime.class);
            implementationOfOverTime.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            this.save(implementationOfOverTime, true);
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
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
            String pendingData = appActivity.getPendingData();
            ImplementationOfOverTime implementationOfOverTime = gson.fromJson(pendingData, ImplementationOfOverTime.class);
            implementationOfOverTime.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            this.save(implementationOfOverTime, true);
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
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");

        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }

// cara manual        
//        JsonObject jsonObject = (JsonObject) jsonConverter.getClassFromJson(appActivity.getPendingData(), JsonObject.class);
//        String locale = jsonObject.get("locale").getAsString();
//        String empData = jsonObject.get("empData").getAsString();
//        TypeToken<List<Long>> token = new TypeToken<List<Long>>(){};
//        
//        List<EmpData> listEmpData = gson.fromJson(empData, token.getType());
        //parsing object data to json, for email purpose
        ImplementationOfOverTime implementationOfOverTime = gson.fromJson(appActivity.getPendingData(), ImplementationOfOverTime.class);
        final JSONObject jsonObj = new JSONObject();

        try {

            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("proposeDate", dateFormat.format(implementationOfOverTime.getCreatedOn()));
            jsonObj.put("overTimeName", implementationOfOverTime.getWtOverTime().getName());
            jsonObj.put("hour", implementationOfOverTime.getRelativeHour());
            jsonObj.put("minute", implementationOfOverTime.getRelativeMinute());
            jsonObj.put("overTimeDate", dateFormat.format(implementationOfOverTime.getImplementationDate()));
            jsonObj.put("implementationNumber", implementationOfOverTime.getCode());
            jsonObj.put("empName", implementationOfOverTime.getEmpData().getBioData().getFirstName() + " " + implementationOfOverTime.getEmpData().getBioData().getLastName());
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
    public String save(ImplementationOfOverTime entity, boolean isBypassApprovalChecking) throws Exception {
        String message = "error";
        Long overTimeId = entity.getWtOverTime().getId();
        WtOverTime selectTedWtOverTime = wtOverTimeDao.getEntiyByPK(overTimeId);
        Date currentDate = new Date();
        Integer selisihWaktu = DateTimeUtil.getTotalDayDifference(currentDate, entity.getImplementationDate());
      
        if (selisihWaktu > 0) {
            if (selisihWaktu > selectTedWtOverTime.getBatasMaju()) {
                throw new BussinessException("implementOt.implementation_date_outofrange");
            }
        }
        
        if (selisihWaktu < 0) {
            selisihWaktu=selisihWaktu*(-1);
            if (selisihWaktu >selectTedWtOverTime.getBatasMudur()) {
                throw new BussinessException("implementOt.implementation_date_outofrange");
            }
        }
        
        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
        HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        
        List<ApprovalActivity> listPendingRequest = approvalActivityDao.getPendingRequest(requestUser.getUserId());
        listPendingRequest = Lambda.select(listPendingRequest, Lambda.having(Lambda.on(ApprovalActivity.class).getApprovalDefinition().getName(), Matchers.equalTo(HRMConstant.OVERTIME)));
        
        if(!listPendingRequest.isEmpty()){
        	for(ApprovalActivity pendingRequest : listPendingRequest){
        		Gson gsonPending = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
        		ImplementationOfOverTime pendingEntity = gsonPending.fromJson(pendingRequest.getPendingData(), ImplementationOfOverTime.class);
        		if(DateUtils.isSameDay(pendingEntity.getImplementationDate(),entity.getImplementationDate() )){
        			throw new BussinessException("leaveimplementation.error_still_have_waiting_status");
        		}
        	}
        	
        }
        
        // check duplicate code
        long totalDuplicates = implementationOfOverTimeDao.getByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("implementOt.implementation_ot_error_duplicate_code");
        }

        TempJadwalKaryawan jadwalKaryawan = tempJadwalKaryawanService.getEntityByEmpDataIdAndTanggalWaktuKerja(entity.getEmpData().getId(), entity.getImplementationDate());
        // check apakah terdapat temporary jadwal karyawan atau tidak
        if (jadwalKaryawan != null) {
            // check jika request start time dan end timenya terdapat dalam waktu kerjanya
            if (entity.getStartTime() != null && entity.getEndTime() != null
                    && entity.getStartTime().after(jadwalKaryawan.getWtWorkingHour().getWorkingHourBegin())
                    && entity.getEndTime().before(jadwalKaryawan.getWtWorkingHour().getWorkingHourEnd())) {

                throw new BussinessException("implementationovertime.time_is_in_your_working_hours");
            }
        } else {
            throw new BussinessException("implementationovertime.your_implementation_date_far");
        }

        
        WtOverTime wtOverTime = wtOverTimeDao.getEntityByPkWithDetail(entity.getWtOverTime().getId());
        String createdBy = org.apache.commons.lang.StringUtils.isEmpty(wtOverTime.getCreatedBy()) ? UserInfoUtil.getUserName() : wtOverTime.getCreatedBy();
        Date createdOn = wtOverTime.getCreatedOn() == null ? new Date() : wtOverTime.getCreatedOn();

        entity.setEmpData(empData);
        entity.setWtOverTime(wtOverTime);
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);

        
        List<ApprovalDefinition> appDefs = Lambda.extract(wtOverTime.getApprovalDefinitionOTs(), Lambda.on(ApprovalDefinitionOT.class).getApprovalDefinition());
       
        // check jika ada cuti yang masih diproses approval, hanya boleh mengajukan cuti jika tidak ada approval yang pending
        if (approvalActivityDao.isStillHaveWaitingStatus(appDefs, requestUser.getUserId())) {
            throw new BussinessException("leaveimplementation.error_still_have_waiting_status");
        }

        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());
        if (approvalActivity == null) {
            //generate number of code
            String nomor = this.generateOvertimeNumber();
            entity.setCode(nomor);

            implementationOfOverTimeDao.save(entity);
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

    private String generateOvertimeNumber() {
        /**
         * generate number form codification, from OVERTIME module
         */
        TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.OVERTIME_KODE);
        Long currentMaxId = implementationOfOverTimeDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor = KodefikasiUtil.getKodefikasi(((int) currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public ImplementationOfOverTime getEntityByApprovalActivityNumberWithDetail(String activityNumber) throws Exception {
        return implementationOfOverTimeDao.getEntityByApprovalActivityNumberWithDetail(activityNumber);
    }

    @Override
    protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        StringBuffer detail = new StringBuffer();
        HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
        ImplementationOfOverTime entity = gson.fromJson(appActivity.getPendingData(), ImplementationOfOverTime.class);

        detail.append("Pengajuan lembur oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
        detail.append("Jenis: " + entity.getWtOverTime().getName() + ". ");
        detail.append("Tanggal " + dateFormat.format(entity.getImplementationDate()) + ". ");
        detail.append("Total " + entity.getRelativeHour() + " jam, " + entity.getRelativeMinute() + " menit");
        return detail.toString();
    }

}
