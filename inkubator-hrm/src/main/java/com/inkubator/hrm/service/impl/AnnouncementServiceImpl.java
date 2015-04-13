package com.inkubator.hrm.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.dao.AnnouncementDao;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.CompanyDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.web.model.AnnouncementModelJson;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.jms.core.MessageCreator;

/**
 *
 * @author WebGenX
 */
@Service(value = "announcementService")
@Lazy
public class AnnouncementServiceImpl extends BaseApprovalServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmpDataDao empDataDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Announcement announcement) throws Exception {
        announcementDao.delete(announcement);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Announcement announcement) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = announcementDao.getTotalByCode(announcement.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("announcement.error_duplicate_code")
//}
        announcement.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        announcement.setCreatedBy(UserInfoUtil.getUserName());
        announcement.setCreatedOn(new Date());
        announcementDao.save(announcement);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Announcement announcement) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = announcementDao.getTotalByCodeAndNotId(announcement.getCode(),announcement.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("announcement.error_duplicate_code")
//}
        Announcement announcement1 = announcementDao.getEntiyByPK(announcement.getId());
        announcement1.setUpdatedBy(UserInfoUtil.getUserName());
        announcement1.setUpdatedOn(new Date());
        announcement1.setAnnouncementContent(announcement.getAnnouncementContent());
        announcement1.setAttachmentPath(announcement.getAttachmentPath());
        announcement1.setViewModel(announcement.getViewModel());
        announcement1.setSubject(announcement.getSubject());
        announcement1.setIsAlreadyShow(announcement.getIsAlreadyShow());
        announcement1.setPeriodeEndDate(announcement.getPeriodeEndDate());
        announcement1.setPeriodeStartDate(announcement.getPeriodeStartDate());
        announcement1.setEmailIsSend(announcement.getEmailIsSend());
        announcement1.setCompany(announcement.getCompany());
        announcement1.setTimeModel(announcement.getTimeModel());
        announcement1.setInternetPublish(announcement.getInternetPublish());
        announcementDao.update(announcement1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Announcement getEntiyByPK(Long id) {
        return this.announcementDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Announcement> getByParam(AnnouncementSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.announcementDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalAnnouncementByParam(AnnouncementSearchParameter searchParameter) throws Exception {
        return this.announcementDao.getTotalAnnouncementByParam(searchParameter);
    }

    @Override
    public Announcement getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(Announcement enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement saveData(Announcement entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement updateData(Announcement entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement saveOrUpdateData(Announcement entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Announcement getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(Announcement entity) throws Exception {
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
    public List<Announcement> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Announcement> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String save(AnnouncementModelJson announcementModelJson, boolean isBypassApprovalChecking) throws Exception {
        String message = "error";
        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.ANNOUNCEMENT, UserInfoUtil.getUserName());
        String createdBy = org.apache.commons.lang.StringUtils.isEmpty(announcementModelJson.getCreatedBy()) ? UserInfoUtil.getUserName() : announcementModelJson.getCreatedBy();
        Date createdOn = announcementModelJson.getCreatedOn() == null ? new Date() : announcementModelJson.getCreatedOn();

        if (approvalActivity == null) {
            Announcement announcement = new Announcement();
            announcement.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            announcement.setCompany(companyDao.getEntiyByPK(announcementModelJson.getCompanyId()));
            announcement.setInternetPublish(announcementModelJson.getIsInternetPublish());
            announcement.setAnnouncementContent(announcementModelJson.getAnnouncementContent());
            announcement.setSubject(announcementModelJson.getAnnouncementSubject());
            announcement.setNomor(announcementModelJson.getNomor());
            announcement.setViewModel(announcementModelJson.getViewModel());
            announcement.setCreatedBy(UserInfoUtil.getUserName());
            announcement.setCreatedOn(new Date());
            announcementDao.save(announcement);
            
            //LOG
        } else {

            System.out.println("butuh approval");
            /**
             * proceed of saving approval activity
             */
            announcementModelJson.setCreatedBy(createdBy);
            announcementModelJson.setCreatedOn(createdOn);
            JsonParser parser = new JsonParser();
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(announcementModelJson));
//            jsonObject.addProperty("reimbursmentFileName", uploadPath);
            //save to approval activity
            approvalActivity.setPendingData(JsonConverter.getJson(announcementModelJson, "dd-MM-yyyy"));
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingEmailApprovalNotif(approvalActivity);

            message = "success_need_approval";
        }
        return message;
    }

    @Override
    protected void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception {
        AnnouncementModelJson announcementModelJson = (AnnouncementModelJson) JsonConverter.getClassFromJson(appActivity.getPendingData(), AnnouncementModelJson.class, "dd-MM-yyyy");
        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("subjek", announcementModelJson.getAnnouncementSubject());
            jsonObj.put("content", announcementModelJson.getAnnouncementContent());
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("listEmployeeType", announcementModelJson.getListEmpTypeName());
            jsonObj.put("listUnitKerja", announcementModelJson.getListUnitKerjaName());
            jsonObj.put("listGolonganJabatan", announcementModelJson.getListGolJabName());

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
    public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
        Map<String, Object> result = super.approvedAndCheckNextApproval(approvalActivityId, pendingDataUpdate, comment);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
            /**
             * kalau status akhir sudah di approved dan tidak ada next approval,
             * berarti langsung insert ke database
             */
            AnnouncementModelJson announcementModelJson = (AnnouncementModelJson) JsonConverter.getClassFromJson(appActivity.getPendingData(), AnnouncementModelJson.class, "dd-MM-yyyy");
            //announcementModelJson.setApplicationStatus(HRMConstant.RMBS_STATUS_UNDISBURSED); // set default application status
            //entity.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose

            /**
             * convert to UploadedFile before saving
             */
//            UploadedFile uploadedFile = this.convertFileToUploadedFile(appActivity.getPendingData());

            /**
             * saving to DB
             */
            this.save(announcementModelJson, Boolean.TRUE);
        }
    }

    @Override
    public void rejected(long approvalActivityId, String comment) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void diverted(long approvalActivityId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
