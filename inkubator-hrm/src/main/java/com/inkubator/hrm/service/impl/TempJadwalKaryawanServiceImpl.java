/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.dao.WtWorkingHourDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "tempJadwalKaryawanService")
@Lazy
public class TempJadwalKaryawanServiceImpl extends BaseApprovalServiceImpl implements TempJadwalKaryawanService {

    @Autowired
    private TempJadwalKaryawanDao tempJadwalKaryawanDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private WtGroupWorkingDao wtGroupWorkingDao;
    @Autowired
    private WtHolidayDao wtHolidayDao;
    @Autowired
    private WtWorkingHourDao wtWorkingHourDao;
    @Autowired
    private JmsTemplate jmsTemplateMassJadwalKerja;
    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;

    @Override
    public TempJadwalKaryawan getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(TempJadwalKaryawan enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan saveData(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan updateData(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan saveOrUpdateData(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(TempJadwalKaryawan entity) throws Exception {
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
    public List<TempJadwalKaryawan> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ, timeout = 50)
    public List<TempJadwalKaryawan> getAllByEmpIdWithDetail(long empId) throws Exception {
        return this.tempJadwalKaryawanDao.getAllByEmpIdWithDetail(empId);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void savePenempatanJadwal(EmpData empData) throws Exception {
//        List<TempJadwalKaryawan> dataMustDelete = this.tempJadwalKaryawanDao.getAllByEmpId(empData.getId());
//        if (!dataMustDelete.isEmpty()) {
//            for (TempJadwalKaryawan dataMustDelete1 : dataMustDelete) {
//                tempJadwalKaryawanDao.delete(dataMustDelete1);
//            }
//        }
        EmpData data = empDataDao.getEntiyByPK(empData.getId());
        Date now = new Date();
        WtGroupWorking groupWorking = this.wtGroupWorkingDao.getByCode(empData.getWtGroupWorking().getCode());
        groupWorking.setIsActive(Boolean.TRUE);
        wtGroupWorkingDao.update(groupWorking);
        data.setWtGroupWorking(groupWorking);
        empDataDao.update(data);
        List<WtScheduleShift> list = new ArrayList<>(groupWorking.getWtScheduleShifts());
        Collections.sort(list, shortByDate1);
        Date startDate = groupWorking.getBeginTime();
        Date endDate = groupWorking.getEndTime();
        int numberOfDay = DateTimeUtil.getTotalDayDifference(startDate, endDate);
        int totalDateDif = DateTimeUtil.getTotalDayDifference(startDate, now) + 1;
        int num = numberOfDay + 1;
        int hasilBagi = (totalDateDif) / (num);
        Date tanggalAkhirJadwal = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num) - 1, CommonUtilConstant.DATE_FORMAT_DAY);
//        String dayBegin = new SimpleDateFormat("EEEE").format(endDate);
//        String dayNow = new SimpleDateFormat("EEEE").format(now);
        Date beginScheduleDate;
        if (new SimpleDateFormat("ddMMyyyy").format(tanggalAkhirJadwal).equals(new SimpleDateFormat("ddMMyyyy").format(new Date()))) {
            beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num) - num, CommonUtilConstant.DATE_FORMAT_DAY);
        } else {
            beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num), CommonUtilConstant.DATE_FORMAT_DAY);
        }
        int i = 0;
        TempJadwalKaryawan jadwalKaryawan;
        for (WtScheduleShift list1 : list) {
            String onlyDate = new SimpleDateFormat("yyyy-MM-dd").format(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
            Date olnyDate = new SimpleDateFormat("yyyy-MM-dd").parse(onlyDate);
            jadwalKaryawan = tempJadwalKaryawanDao.getEntityByEmpDataIdAndTanggalWaktuKerja(empData.getId(), olnyDate);
            if (jadwalKaryawan != null) {
                jadwalKaryawan.setUpdatedBy(UserInfoUtil.getUserName());
                jadwalKaryawan.setUpdatedOn(new Date());
//                jadwalKaryawan = tempJadwalKaryawanDao.getByEmpId(empData.getId(), olnyDate);
            } else {
                jadwalKaryawan = new TempJadwalKaryawan();
                jadwalKaryawan.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                jadwalKaryawan.setEmpData(empData);
                jadwalKaryawan.setTanggalWaktuKerja(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
                jadwalKaryawan.setCreatedBy(UserInfoUtil.getUserName());
                jadwalKaryawan.setCreatedOn(new Date());
            }
            WtHoliday holiday = wtHolidayDao.getWtHolidayByDate(olnyDate);
            if (holiday != null && groupWorking.getTypeSequeace().equals(HRMConstant.NORMAL_SCHEDULE)) {
                jadwalKaryawan.setWtWorkingHour(wtWorkingHourDao.getByCode("OFF"));
            } else {
                jadwalKaryawan.setWtWorkingHour(list1.getWtWorkingHour());
            }
            jadwalKaryawan.setIsCollectiveLeave(Boolean.FALSE);

            this.tempJadwalKaryawanDao.saveOrUpdateAndMerge(jadwalKaryawan);
            i++;
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String saveMassPenempatanJadwal(List<EmpData> data, long groupWorkingId) throws Exception {
        String message = "error";

        List<Long> listIdEmp = Lambda.extract(data, Lambda.on(EmpData.class).getId());
        HrmUser requestUser = hrmUserDao.getByUserId(UserInfoUtil.getUserName());
        ApprovalActivity approvalActivity = super.checkApprovalProcess(HRMConstant.SHIFT_SCHEDULE, requestUser.getUserId());
//        if (approvalActivity == null) {
        System.out.println(" hehhehererhehr");
        this.saveMassPenempatanJadwal(listIdEmp, groupWorkingId, new Date(), UserInfoUtil.getUserName());
        message = "success_without_approval";

//        } else {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
//            String dataToJson = jsonConverter.getJson(listIdEmp.toArray(new Long[listIdEmp.size()]));
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("listEmpId", dataToJson);
//            jsonObject.addProperty("groupWorkingId", groupWorkingId);
//            jsonObject.addProperty("createDate", dateFormat.format(new Date()));
//            jsonObject.addProperty("createBy", UserInfoUtil.getUserName());
//
//            approvalActivity.setPendingData(jsonObject.toString());
//            approvalActivityDao.save(approvalActivity);
//
//            message = "success_need_approval";
//
//            //sending email notification
//            this.sendingEmailApprovalNotif(approvalActivity);
//        }
        return message;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveMassPenempatanJadwal(List<Long> data, long groupWorkingId, Date createdOn, String createdBy) throws Exception {
        WtGroupWorking groupWorking = wtGroupWorkingDao.getEntiyByPK(groupWorkingId);
        groupWorking.setIsActive(Boolean.TRUE);
        wtGroupWorkingDao.update(groupWorking);

        for (Long empDataId : data) {
            EmpData empData = empDataDao.getEntiyByPK(empDataId);
            empData.setWtGroupWorking(wtGroupWorkingDao.getEntiyByPK(groupWorkingId));
            this.empDataDao.update(empData);
        }
        System.out.println(" masuk sini jugaaaa");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String dataToJson = jsonConverter.getJson(data.toArray(new Long[data.size()]));
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("listEmpId", dataToJson);
        jsonObject.addProperty("groupWorkingId", groupWorkingId);
        jsonObject.addProperty("createDate", dateFormat.format(createdOn));
        jsonObject.addProperty("createBy", createdBy);

        this.jmsTemplateMassJadwalKerja.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(jsonObject.toString());
            }
        });
    }

    private final Comparator<WtScheduleShift> shortByDate1 = new Comparator<WtScheduleShift>() {
        @Override
        public int compare(WtScheduleShift o1, WtScheduleShift o2) {
            return o1.getScheduleDate().compareTo(o2.getScheduleDate());
        }
    };

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
            String pendingData = appActivity.getPendingData();
            JsonObject jsonObject = (JsonObject) jsonConverter.getClassFromJson(pendingData, JsonObject.class);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            Date createdOn = dateFormat.parse(jsonObject.get("createDate").getAsString());
            Gson gson = new GsonBuilder().create();
            List<Long> listEmpId = new GsonBuilder().create().fromJson(jsonObject.get("listEmpId").getAsString(), new TypeToken<List<Long>>() {
            }.getType());
            this.saveMassPenempatanJadwal(listEmpId, jsonObject.get("groupWorkingId").getAsLong(), createdOn, jsonObject.get("createBy").getAsString());
        }

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void rejected(long approvalActivityId, String comment) throws Exception {
        Map<String, Object> result = super.rejectedAndCheckNextApproval(approvalActivityId, comment);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
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
            String pendingData = appActivity.getPendingData();
            JsonObject jsonObject = (JsonObject) jsonConverter.getClassFromJson(pendingData, JsonObject.class);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            Date createdOn = dateFormat.parse(jsonObject.get("createDate").getAsString());
            List<Long> listEmpId = new GsonBuilder().create().fromJson(jsonObject.get("listEmpId").getAsString(), new TypeToken<List<Long>>() {
            }.getType());
            this.saveMassPenempatanJadwal(listEmpId, jsonObject.get("groupWorkingId").getAsLong(), createdOn, jsonObject.get("createBy").getAsString());
        }

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
    }

    @Override
    public void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception {
        //initialization
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        JsonObject jsonObject = (JsonObject) jsonConverter.getClassFromJson(appActivity.getPendingData(), JsonObject.class);
        Date createdOn = jsonDateFormat.parse(jsonObject.get("createDate").getAsString());

        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }

        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("proposeDate", new SimpleDateFormat("dd-MMMM-yyyy").format(createdOn));

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
        
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED,propagation = Propagation.SUPPORTS, timeout = 30)
    public TempJadwalKaryawan getEntityByEmpDataIdAndTanggalWaktuKerja(Long id, Date implementationDate) throws Exception {
        return tempJadwalKaryawanDao.getEntityByEmpDataIdAndTanggalWaktuKerja(id, implementationDate);
    }

}
