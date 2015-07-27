package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AnnouncementDao;
import com.inkubator.hrm.dao.AnnouncementEmpTypeDao;
import com.inkubator.hrm.dao.AnnouncementGoljabDao;
import com.inkubator.hrm.dao.AnnouncementUnitDao;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.CompanyDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.RecruitMppApplyDao;
import com.inkubator.hrm.dao.RecruitMppApplyDetailDao;
import com.inkubator.hrm.dao.RecruitMppPeriodDao;
import com.inkubator.hrm.dao.UnitKerjaDao;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.AnnouncementEmpType;
import com.inkubator.hrm.entity.AnnouncementEmpTypeId;
import com.inkubator.hrm.entity.AnnouncementGoljab;
import com.inkubator.hrm.entity.AnnouncementGoljabId;
import com.inkubator.hrm.entity.AnnouncementUnit;
import com.inkubator.hrm.entity.AnnouncementUnitId;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.web.model.RecruitMppApplyViewModel;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.hamcrest.Matchers;
import org.primefaces.model.DefaultUploadedFile;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "recruitMppApplyService")
@Lazy
public class RecruitMppApplyServiceImpl extends BaseApprovalServiceImpl implements RecruitMppApplyService {

    @Autowired
    private RecruitMppApplyDao recruitMppApplyDao;
    @Autowired
    private RecruitMppApplyDetailDao recruitMppApplyDetailDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private ApprovalDefinitionDao approvalDefinitionDao;
    @Autowired
    private RecruitMppPeriodDao recruitMppPeriodDao;
    @Autowired
    private JabatanDao jabatanDao;
    @Autowired
    private FacesIO facesIO;

    @Override
    public RecruitMppApply getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntiyByPK(Long l) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(RecruitMppApply t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(RecruitMppApply t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(RecruitMppApply t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply saveData(RecruitMppApply t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply updateData(RecruitMppApply t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply saveOrUpdateData(RecruitMppApply t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppApply getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(RecruitMppApply t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(RecruitMppApply t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApply> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApply> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApply> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApply> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApply> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApply> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApply> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppApply> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitMppApply> getByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        List<RecruitMppApply> listData = this.recruitMppApplyDao.getByParam(parameter, firstResult, maxResults, orderable);
        for (RecruitMppApply recruitMppApply : listData) {
            Long totalDetail = recruitMppApplyDetailDao.getTotalByRecruitMppApplyId(recruitMppApply.getId());
            recruitMppApply.setTotalDetailJabatan(totalDetail);
        }
        return listData;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(RecruitMppApplySearchParameter parameter) throws Exception {
        return this.recruitMppApplyDao.getTotalByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitMppApply getEntityWithDetailById(Long id) {
        return this.recruitMppApplyDao.getEntityWithDetailById(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String saveRecruitMppApplytWithApproval(RecruitMppApply entity, List<RecruitMppApplyDetail> listDetailRecruitMppApply, UploadedFile recruitMppApplyFile) throws Exception {
        Long totalRecruitApprovalDef = approvalDefinitionDao.getTotalApprovalExistWithSequenceOne(HRMConstant.RECRUIT_MPP_APPLY);

        //If Approval Defintion for MPP Process have not been created, throw Exception
        if (totalRecruitApprovalDef <= 0) {
            throw new BussinessException("mpp_recruitment.error_approval_def_not_found");
        }

        //If MPP Implementation code duplicate, throw Exception
        if (isMppCodeDuplicate(entity.getRecruitMppApplyCode(), null)) {
            throw new BussinessException("mpp_recruitment.error_mpp_code_duplicate");
        }

        String result = "error";

        String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();

        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);
        entity.setApplicationStatus(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL);

        ApprovalActivity approvalActivity = super.checkApprovalProcess(HRMConstant.RECRUIT_MPP_APPLY, createdBy);
        approvalActivity.setPendingData(convertToJsonData(entity, recruitMppApplyFile, listDetailRecruitMppApply));
        approvalActivityDao.save(approvalActivity);

        result = "success_need_approval";

        return result;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateRecruitMppApplytWithApproval(RecruitMppApply entity, List<RecruitMppApplyDetail> listDetailRecruitMppApply, UploadedFile recruitMppApplyFile, String activityNumber) throws Exception {

        Long totalRecruitApprovalDef = approvalDefinitionDao.getTotalApprovalExistWithSequenceOne(HRMConstant.RECRUIT_MPP_APPLY);

        //If Approval Defintion for MPP Process have not been created, throw Exception
        if (totalRecruitApprovalDef <= 0) {
            throw new BussinessException("mpp_recruitment.error_approval_def_not_found");
        }

        //If MPP Implementation code duplicate, throw Exception
        if (isMppCodeDuplicate(entity.getRecruitMppApplyCode(), activityNumber)) {
            throw new BussinessException("mpp_recruitment.error_mpp_code_duplicate");
        }

        ApprovalActivity selectedApprovalActivity = approvalActivityDao.getEntityByActivityNumberAndSequence(activityNumber, 1);

        if (selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) {
            throw new BussinessException("mpp_recruitment.error_activity_already_approved");
        }

        String updatedBy = UserInfoUtil.getUserName();
        Date updatedOn = new Date();
        entity.setUpdatedBy(updatedBy);
        entity.setUpdatedOn(updatedOn);
        entity.setApplicationStatus(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL);

        selectedApprovalActivity.setPendingData(convertToJsonData(entity, recruitMppApplyFile, listDetailRecruitMppApply));

        String result = "error";

        approvalActivityDao.update(selectedApprovalActivity);
        result = "success_need_approval";
        return result;
    }

    private String convertToJsonData(RecruitMppApply entity, UploadedFile recruitMppFile, List<RecruitMppApplyDetail> listMppDetail) throws IOException {
        //saving file uploaded temporary
        String uploadPath = null;
        if (recruitMppFile != null) {
            uploadPath = getUploadPath(entity.getRecruitMppApplyCode(), recruitMppFile);
            entity.setAttachmentDocPath(uploadPath);
            
            //remove old file        	
            File oldFile = new File(uploadPath);
            FileUtils.deleteQuietly(oldFile);

            //added new file
            facesIO.transferFile(recruitMppFile);
            File file = new File(facesIO.getPathUpload() + recruitMppFile.getFileName());
            file.renameTo(new File(uploadPath));
        }

        //parsing object to json 
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));

        JsonArray jsonDetailMpp = (JsonArray) parser.parse(gson.toJson(listMppDetail));
        jsonObject.add("listMppDetail", jsonDetailMpp);

        return gson.toJson(jsonObject);
    }

    private String getUploadPath(String mppCode, UploadedFile documentFile) {
        String extension = org.apache.commons.lang.StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "mppRecruitApply_" + mppCode + "." + extension;
        return uploadPath;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitMppApplyViewModel> getUndisbursedActivityByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        List<RecruitMppApplyViewModel> listRecruitMppApplyViewModel = this.recruitMppApplyDao.getUndisbursedActivityByParam(parameter, firstResult, maxResults, orderable);
        setRecruitMppApplyComplexData(listRecruitMppApplyViewModel);
        return listRecruitMppApplyViewModel;
    }

    private void setRecruitMppApplyComplexData(List<RecruitMppApplyViewModel> listRecruitMppApplyModel) {
        for (RecruitMppApplyViewModel recruitMppApplyViewModel : listRecruitMppApplyModel) {

            if (recruitMppApplyViewModel.getRecruitMppApplyId() == null) {

                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
                String jsonData = recruitMppApplyViewModel.getJsonData();
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = (JsonObject) parser.parse(jsonData);

                RecruitMppApply recruitMppApplyTemp = gson.fromJson(jsonObject, RecruitMppApply.class);
                JsonArray arrayDetailMpp = jsonObject.getAsJsonArray("listMppDetail");

                recruitMppApplyViewModel.setRecruitMppApplyCode(recruitMppApplyTemp.getRecruitMppApplyCode());
                recruitMppApplyViewModel.setRecruitMppApplyName(recruitMppApplyTemp.getRecruitMppApplyName());
                recruitMppApplyViewModel.setApplyDate(recruitMppApplyTemp.getApplyDate());
                recruitMppApplyViewModel.setJobPositionTotal(Long.parseLong(String.valueOf(arrayDetailMpp.size())));

            } else {
                RecruitMppApply recruitMppApply = recruitMppApplyDao.getEntiyByPK(recruitMppApplyViewModel.getRecruitMppApplyId().longValue());
                recruitMppApplyViewModel.setApplyDate(recruitMppApply.getApplyDate());
                recruitMppApplyViewModel.setJobPositionTotal(Long.parseLong(String.valueOf(recruitMppApply.getRecruitMppApplyDetails().size())));

            }
        }
    }

    private Boolean isMppCodeDuplicate(String mppCode, String activityNumber) {
        Boolean result = Boolean.FALSE;

        Long totalDuplicateFromEntity = this.recruitMppApplyDao.getTotalDataByMppCode(mppCode);
        Long totalDuplicateFormWaitingApprovalActivity = 0l;

        //Get List waiting approval activity
        List<ApprovalActivity> listWaitingApproval = this.approvalActivityDao.getAllDataWaitingStatusApprovalFetchedApprovalDef();

        //Filter only activity that comes from RECRUIT_MPP_APPLY process
        listWaitingApproval = Lambda.select(listWaitingApproval, Lambda.having(Lambda.on(ApprovalActivity.class).getApprovalDefinition().getName(), Matchers.equalTo(HRMConstant.RECRUIT_MPP_APPLY)));

        //grouping list by ActivityNumber
        Group<ApprovalActivity> groupWaitingApprovalActivity = Lambda.group(listWaitingApproval, Lambda.by(Lambda.on(ApprovalActivity.class).getActivityNumber()));

        //iterate each group list element
        for (String key : groupWaitingApprovalActivity.keySet()) {
            List<ApprovalActivity> listGroupedActivity = groupWaitingApprovalActivity.find(key);

            // Get activity with highest sequence from each grouped list activities
            ApprovalActivity approvalActivityWithHighestSequence = Lambda.selectMax(listGroupedActivity, Lambda.on(ApprovalDefinition.class).getSequence());

            // convert json pendingData into RecruitMppApply
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String jsonData = approvalActivityWithHighestSequence.getPendingData();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
            RecruitMppApply recruitMppApplyTemp = gson.fromJson(jsonObject, RecruitMppApply.class);

            // chek whether mppCode already used
            if (StringUtils.equals(mppCode, recruitMppApplyTemp.getRecruitMppApplyCode())
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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalUndisbursedActivityByParam(RecruitMppApplySearchParameter parameter) throws Exception {
        return this.recruitMppApplyDao.getTotalUndisbursedActivityByParam(parameter);
    }

    @Override
    protected void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitMppApply getEntityWithDetailByActivityNumber(String activityNumber) throws Exception {
        return this.recruitMppApplyDao.getEntityWithDetailByActivityNumber(activityNumber);
    }

    @Override
    public UploadedFile convertFileToUploadedFile(String path) {
        UploadedFile uploadedFile = null;

        try {
            /**
             * converting process from file physics (which is get the path file
             * from path) to UploadedFile object
             */
            if (StringUtils.isNotEmpty(path)) {
                File file = new File(path);
                DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("fileData", "text/plain", true, file.getName());
                InputStream input = new FileInputStream(file);
                OutputStream os = fileItem.getOutputStream();
                int ret = input.read();
                while (ret != -1) {
                    os.write(ret);
                    ret = input.read();
                }
                os.flush();
                uploadedFile = new DefaultUploadedFile(fileItem);
            }
        } catch (IOException e) {
            LOGGER.error("Error", e);
        }

        return uploadedFile;
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
            RecruitMppApply entity = this.convertJsonToEntity(appActivity.getPendingData());

            String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
            Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();

            RecruitMppPeriod recruitMppPeriod = recruitMppPeriodDao.getEntiyByPK(entity.getRecruitMppPeriod().getId());
            entity.setRecruitMppPeriod(recruitMppPeriod);

            entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
            entity.setCreatedOn(createdOn);
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());
            entity.setApplicationStatus(HRMConstant.APPROVAL_STATUS_APPROVED);

            // file attachment was already uploaded at first apply (at method convertToJsonData),
            //so no need to re-upload the file, just save it into related table
            List<RecruitMppApplyDetail> listDetail = new ArrayList<>(entity.getRecruitMppApplyDetails());
            entity = recruitMppApplyDao.saveData(entity);
            

            for (RecruitMppApplyDetail detail : listDetail) {
                Jabatan jabatan = jabatanDao.getEntiyByPK(detail.getJabatan().getId());
                detail.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                detail.setRecruitMppApply(entity);
                detail.setJabatan(jabatan);
                detail.setCreatedBy(createdBy);
                detail.setCreatedOn(createdOn);
                recruitMppApplyDetailDao.save(detail);
            }
        }
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
            RecruitMppApply entity = this.convertJsonToEntity(appActivity.getPendingData());

            String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
            Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();

            RecruitMppPeriod recruitMppPeriod = recruitMppPeriodDao.getEntiyByPK(entity.getRecruitMppPeriod().getId());
            entity.setRecruitMppPeriod(recruitMppPeriod);

            entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
            entity.setCreatedOn(createdOn);
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());
            entity.setApplicationStatus(HRMConstant.APPROVAL_STATUS_REJECTED);

            // file attachment was already uploaded at first apply (at method convertToJsonData),
            //so no need to re-upload the file, just save it into related table
            List<RecruitMppApplyDetail> listDetail = new ArrayList<>(entity.getRecruitMppApplyDetails());
            entity = recruitMppApplyDao.saveData(entity);            

            for (RecruitMppApplyDetail detail : listDetail) {
                Jabatan jabatan = jabatanDao.getEntiyByPK(detail.getJabatan().getId());
                detail.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                detail.setRecruitMppApply(entity);
                detail.setJabatan(jabatan);
                detail.setCreatedBy(createdBy);
                detail.setCreatedOn(createdOn);
                recruitMppApplyDetailDao.save(detail);
            }
        }
    }

    @Override
    public void diverted(long approvalActivityId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private RecruitMppApply convertJsonToEntity(String jsonPendingData) {
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(jsonPendingData);
        RecruitMppApply recruitMppApply = gson.fromJson(jsonObject, RecruitMppApply.class);

        JsonArray arrayDetailRecruitmentRequest = jsonObject.getAsJsonArray("listMppDetail");
        HashSet<RecruitMppApplyDetail> setRecruitMppApplyDetail = new HashSet<RecruitMppApplyDetail>();
        if (null != arrayDetailRecruitmentRequest) {
            for (int i = 0; i < arrayDetailRecruitmentRequest.size(); i++) {
                RecruitMppApplyDetail detail = gson.fromJson(arrayDetailRecruitmentRequest.get(i), RecruitMppApplyDetail.class);
                setRecruitMppApplyDetail.add(detail);
            }
        }

        recruitMppApply.setRecruitMppApplyDetails(setRecruitMppApplyDetail);
        return recruitMppApply;
    }

}