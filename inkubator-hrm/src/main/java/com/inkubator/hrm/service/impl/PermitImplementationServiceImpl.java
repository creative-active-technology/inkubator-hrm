package com.inkubator.hrm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.criterion.Order;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
//import com.inkubator.hrm.dao.PermitImplementationDateDao;
import com.inkubator.hrm.dao.NeracaPermitDao;
import com.inkubator.hrm.dao.PermitClassificationDao;
import com.inkubator.hrm.dao.PermitDistributionDao;
import com.inkubator.hrm.dao.PermitImplementationDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionPermit;
//import com.inkubator.hrm.entity.ApprovalDefinitionPermit;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
//import com.inkubator.hrm.entity.PermitImplementationDate;
import com.inkubator.hrm.entity.NeracaPermit;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.hrm.service.WtScheduleShiftService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.ReportPermitHistoryModel;
import com.inkubator.hrm.web.search.PermitImplementationSearchParameter;
import com.inkubator.hrm.web.search.ReportPermitHistorySearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author Taufik
 */
@Service(value = "permitImplementationService")
@Lazy
public class PermitImplementationServiceImpl extends BaseApprovalServiceImpl implements PermitImplementationService {

    @Autowired
    private PermitImplementationDao permitImplementationDao;
    @Autowired
    private PermitClassificationDao permitDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private WtScheduleShiftService wtScheduleShiftService;
    @Autowired
    private PermitDistributionDao permitDistributionDao;
    @Autowired
    private NeracaPermitDao neracaPermitDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private FacesIO facesIO;
    @Autowired
    private TransactionCodeficationDao transactionCodeficationDao;
//    @Autowired
//    private PermitImplementationDateDao permitImplementationDateDao;

    @Override
    public PermitImplementation getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PermitImplementation getEntiyByPK(Long id) throws Exception {
        return permitImplementationDao.getEntiyByPK(id);

    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String save(PermitImplementation entity, UploadedFile documentFile) throws Exception {
        String message = "";
        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
        PermitClassification permit = permitDao.getEntiyByPK(entity.getPermitClassification().getId());
        PermitDistribution permitDistribution = permitDistributionDao.getEntityByPermitClassificationIdAndEmpDataId(permit.getId(), empData.getId());
        Boolean isBypassApprovalChecking = Boolean.FALSE;
        // check actualPermit yg diambil, tidak boleh lebih besar dari balancePermit yg tersedia
        Double actualPermit = this.getTotalActualPermit(empData.getId(), permit.getId(), entity.getStartDate(), entity.getEndDate());
        if (actualPermit > permitDistribution.getBalance()) {
            throw new BussinessException("permitimplementation.error_permit_balance_is_insufficient");
        }

        HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        List<ApprovalDefinition> appDefs = Lambda.extract(permit.getApprovalDefinitionPermits(), Lambda.on(ApprovalDefinitionPermit.class).getApprovalDefinition());
        // check jika ada ijin yang masih diproses approval, hanya boleh mengajukan ijin jika tidak ada approval yang pending
        if (approvalActivityDao.isStillHaveWaitingStatus(appDefs, requestUser.getUserId())) {
            throw new BussinessException("permitimplementation.error_still_have_waiting_status");
        }
        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());

        String createdBy = org.apache.commons.lang.StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
        if (approvalActivity == null) {
            entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            //Set Kodefikasi pada nomor
            TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.PERMIT_KODE);
            Long currentMaxLoanId = permitImplementationDao.getCurrentMaxId();
            if (currentMaxLoanId == null) {
                currentMaxLoanId = 0L;
            }
            entity.setNumberFilling(KodefikasiUtil.getKodefikasi(((int) currentMaxLoanId.longValue()), transactionCodefication.getCode()));
            entity.setEmpData(empData);
            entity.setPermitClassification(permit);

            entity.setCreatedBy(createdBy);
            entity.setCreatedOn(createdOn);

            permitImplementationDao.save(entity);

            if (documentFile != null) {
                String uploadPath = getUploadPath(entity.getId(), documentFile);
                facesIO.transferFile(documentFile);
                File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
                file.renameTo(new File(uploadPath));

                entity.setUploadPath(uploadPath);
                permitImplementationDao.update(entity);
            }

            this.creditPermitBalance(permitDistribution, actualPermit);
            message = "success_without_approvl";
        } else {
            String uploadPath = null;
            if (documentFile != null) {
                uploadPath = getUploadPath(Long.parseLong(RandomNumberUtil.getRandomNumber(9)), documentFile);
                facesIO.transferFile(documentFile);
                File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
                file.renameTo(new File(uploadPath));
            }
            entity.setCreatedBy(createdBy);
            entity.setCreatedOn(createdOn);
            JsonParser parser = new JsonParser();
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));
            jsonObject.addProperty("permitImplementationFile", uploadPath);
            //save to approval activity
            approvalActivity.setPendingData(gson.toJson(jsonObject));
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingApprovalNotification(approvalActivity);
            message = "success_need_approval";
        }
        return message;
    }

    @Override
    public void save(PermitImplementation t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
     @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
     public void save(PermitImplementation entity, UploadedFile documentFile) throws Exception {
     //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
     // check duplicate number filling
     //        long totalDuplicates = permitImplementationDao.getTotalByNumberFilling(entity.getNumberFilling());
     //        if (totalDuplicates > 0) {
     //            throw new BussinessException("permitimplementation.error_duplicate_filling_number");
     //        }

     EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
     PermitClassification permit = permitDao.getEntiyByPK(entity.getPermitClassification().getId());
     //        EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;
     PermitDistribution permitDistribution = permitDistributionDao.getEntityByPermitClassificationIdAndEmpDataId(permit.getId(), empData.getId());

     // check submitted permit tidak boleh lebih besar dari batasPengajuan di permitDefinition
     //        long differenceDaysOfFilling = DateTimeUtil.getTotalDayDifference(new Date(), entity.getStartDate());
     //        if (differenceDaysOfFilling > permit.getSubmittedLimit()) {
     //            throw new BussinessException("permitimplementation.error_submitted_limit");
     //        }
     // check actualPermit yg diambil, tidak boleh lebih besar dari balancePermit yg tersedia
     Double actualPermit = this.getTotalActualPermit(empData.getId(), permit.getId(), entity.getStartDate(), entity.getEndDate());
     if (actualPermit > permitDistribution.getBalance()) {
     throw new BussinessException("permitimplementation.error_permit_balance_is_insufficient");
     }

     //        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
     //Set Kodefikasi pada nomor
     TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.PERMIT_KODE);
     Long currentMaxLoanId = permitImplementationDao.getCurrentMaxId();
     if (currentMaxLoanId == null) {
     currentMaxLoanId = 0L;
     }
     entity.setNumberFilling(KodefikasiUtil.getKodefikasi(((int)currentMaxLoanId.longValue()), transactionCodefication.getCode()));
     entity.setEmpData(empData);
     entity.setPermitClassification(permit);
     //        entity.setTemporaryActing(temporaryActing);

     String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
     Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
     entity.setCreatedBy(createdBy);
     entity.setCreatedOn(createdOn);

     permitImplementationDao.save(entity);

     if (documentFile != null) {
     String uploadPath = getUploadPath(entity.getId(), documentFile);
     facesIO.transferFile(documentFile);
     File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
     file.renameTo(new File(uploadPath));

     entity.setUploadPath(uploadPath);
     permitImplementationDao.update(entity);
     }
     //        if (permit.getIsQuotaReduction()) {
     this.creditPermitBalance(permitDistribution, actualPermit);
     //        }
     }*/
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(PermitImplementation entity, UploadedFile documentFile) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
        Long overTimeId = entity.getPermitClassification().getId();
        PermitClassification selectedPermitClass = permitDao.getEntiyByPK(overTimeId);
        Date currentDate = new Date();

        Integer selisihWaktu = DateTimeUtil.getTotalDayDifference(currentDate, entity.getStartDate());

        if (selisihWaktu > 0) {
            if (selisihWaktu > selectedPermitClass.getBatasMaju()) {
                throw new BussinessException("permitimplementation.error_out_off_range");
            }
        }

        if (selisihWaktu < 0) {
            selisihWaktu = Math.abs(selisihWaktu);
            if (selisihWaktu > selectedPermitClass.getBatasMudur()) {
                throw new BussinessException("permitimplementation.error_out_off_range");
            }
        }
        long totalDuplicates = permitImplementationDao.getTotalByNumberFillingAndNotId(entity.getNumberFilling(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("permitimplementation.error_duplicate_filling_number");
        }

        PermitImplementation permitImplementation = permitImplementationDao.getEntiyByPK(entity.getId());

        // check duplicate number filling
        String uploadPath = permitImplementation.getUploadPath();

        if (documentFile != null) {
            //remove old file
            File oldFile = new File(permitImplementation.getUploadPath());
            oldFile.delete();

            //added new file
            uploadPath = getUploadPath(permitImplementation.getId(), documentFile);
            facesIO.transferFile(documentFile);
            File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
            file.renameTo(new File(uploadPath));
        }

        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
        PermitClassification permit = permitDao.getEntiyByPK(entity.getPermitClassification().getId());
//        EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;

        permitImplementation.setEmpData(empData);
        permitImplementation.setPermitClassification(permit);
//        permitImplementation.setTemporaryActing(temporaryActing);
        permitImplementation.setNumberFilling(entity.getNumberFilling());
        permitImplementation.setStartDate(entity.getStartDate());
        permitImplementation.setEndDate(entity.getEndDate());
        permitImplementation.setFillingDate(entity.getFillingDate());
        permitImplementation.setUploadPath(uploadPath);
//        permitImplementation.setMobilePhone(entity.getMobilePhone());
//        permitImplementation.setMaterialJobsAbandoned(entity.getMaterialJobsAbandoned());
        permitImplementation.setDescription(entity.getDescription());
        permitImplementation.setUpdatedBy(UserInfoUtil.getUserName());
        permitImplementation.setUpdatedOn(new Date());

        permitImplementationDao.update(permitImplementation);
    }

    @Override
    public void saveOrUpdate(PermitImplementation enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation saveData(PermitImplementation entity)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation updateData(PermitImplementation entity)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation saveOrUpdateData(PermitImplementation entity)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntityByPkIsActive(String id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntityByPkIsActive(String id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntityByPkIsActive(String id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntityByPkIsActive(Integer id,
            Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntityByPkIsActive(Integer id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntityByPkIsActive(Integer id,
            Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntityByPkIsActive(Long id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntityByPkIsActive(Long id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PermitImplementation getEntityByPkIsActive(Long id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(PermitImplementation entity) throws Exception {
        //remove physical file
        try {
            File oldFile = new File(entity.getUploadPath());
            oldFile.delete();
        } catch (Exception e) {
            //if any error when removing file, system will continue deleting the record
        }

        //remove entity
        permitImplementationDao.delete(entity);
    }

    @Override
    public void softDelete(PermitImplementation entity) throws Exception {
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
    public List<PermitImplementation> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PermitImplementation> getAllData(Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PermitImplementation> getAllData(Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PermitImplementation> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PermitImplementation> getAllDataPageAble(int firstResult,
            int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PermitImplementation> getAllDataPageAbleIsActive(
            int firstResult, int maxResults, Order order, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PermitImplementation> getAllDataPageAbleIsActive(
            int firstResult, int maxResults, Order order, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PermitImplementation> getAllDataPageAbleIsActive(
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

            //parsing from json to entity
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            PermitImplementation permitImplementation = gson.fromJson(pendingData, PermitImplementation.class);
            permitImplementation.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose 

            //convert to UploadedFile before saving
            UploadedFile uploadedFile = null;
            JsonElement permitImplementationFile = gson.fromJson(pendingData, JsonObject.class).get("permitImplementationFile");
            if (!permitImplementationFile.isJsonNull()) {
                String documentFile = permitImplementationFile.getAsString();
                File file = new File(documentFile);
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
            this.save(permitImplementation, uploadedFile, true);
        }
//
//        //if there is no error, then sending the email notification
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
                PermitImplementation permitImplementation = gson.fromJson(pendingData, PermitImplementation.class);
                permitImplementation.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose			
                //convert to UploadedFile before saving
                UploadedFile uploadedFile = null;
                JsonElement permitImplementationFile = gson.fromJson(pendingData, JsonObject.class).get("permitImplementationFile");
                if (!permitImplementationFile.isJsonNull()) {
                    String reimbursmentFilePath = permitImplementationFile.getAsString();
                    File file = new File(reimbursmentFilePath);
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

                this.save(permitImplementation, uploadedFile, true);
            }
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void diverted(long approvalActivityId) throws Exception {
//        Map<String, Object> result = super.divertedAndCheckNextApproval(approvalActivityId);
//        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
//        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
//            /**
//             * kalau status akhir sudah di approved dan tidak ada next approval,
//             * berarti langsung insert ke database
//             */
//            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
//            String pendingData = appActivity.getPendingData();
//            JsonObject jsonObject = gson.fromJson(pendingData, JsonObject.class);
//
//            if (jsonObject.get("isCancellationProcess") != null) {
//                Long permitImplementationId = jsonObject.get("id").getAsLong();
//                List<PermitImplementationDate> cancellationDates = gson.fromJson(jsonObject.get("cancellationDates"), new TypeToken<List<PermitImplementationDate>>() {
//                }.getType());
//                List<PermitImplementationDate> actualDates = gson.fromJson(jsonObject.get("actualDates"), new TypeToken<List<PermitImplementationDate>>() {
//                }.getType());
//                String cancellationDescription = jsonObject.get("cancellationDescription").getAsString();
//                this.cancellationProcess(permitImplementationId, actualDates, cancellationDates, cancellationDescription);
//
//            } else {
//                PermitImplementation permitImplementation = gson.fromJson(pendingData, PermitImplementation.class);
//                permitImplementation.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose			
//                this.save(permitImplementation, true);
//            }
//        }
//
//        //if there is no error, then sending the email notification
//        sendingApprovalNotification(appActivity);
    }

    @Override
    public void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
        //send sms notification to approver if need approval OR
        //send sms notification to requester if need revision
        super.sendApprovalSmsnotif(appActivity);

//        //initialization
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
//
//        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }
//
//        //parsing object data to json, for email purpose
        PermitImplementation permitImplementation = gson.fromJson(appActivity.getPendingData(), PermitImplementation.class);
        PermitClassification permit = permitDao.getEntiyByPK(permitImplementation.getPermitClassification().getId());
        String cancellationDate = StringUtils.EMPTY;
        JsonObject jsonObject = gson.fromJson(appActivity.getPendingData(), JsonObject.class);
//        if (jsonObject.get("isCancellationProcess") != null) {
//            List<PermitImplementationDate> cancellations = gson.fromJson(jsonObject.get("cancellationDates"), new TypeToken<List<PermitImplementationDate>>() {
//            }.getType());
//            StringBuffer sb = new StringBuffer();
//            for (PermitImplementationDate li : cancellations) {
//                if (sb.length() != 0) {
//                    sb.append(", ");
//                }
//                sb.append(dateFormat.format(li.getActualDate()));
//            }
//            cancellationDate = sb.toString();
//        }
//
        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("proposeDate", dateFormat.format(permitImplementation.getCreatedOn()));
            jsonObj.put("startDate", dateFormat.format(permitImplementation.getStartDate()));
            jsonObj.put("endDate", dateFormat.format(permitImplementation.getEndDate()));
            jsonObj.put("fillingDate", dateFormat.format(permitImplementation.getFillingDate()));
            jsonObj.put("permitClassification", permit.getName());

        } catch (JSONException e) {
            LOGGER.error("Error when create json Object ", e);
        }

//        //send messaging, to trigger sending email
        super.jmsTemplateApproval.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(jsonObj.toString());
            }
        });
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PermitImplementation> getByParam(PermitImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return permitImplementationDao.getByParam(parameter, firstResult, maxResults, orderable);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(PermitImplementationSearchParameter parameter) throws Exception {
        return permitImplementationDao.getTotalByParam(parameter);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PermitImplementation getEntityByPkWithDetail(Long id) throws Exception {
        return permitImplementationDao.getEntityByPkWithDetail(id);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PermitImplementation getLatestEntityByEmpDataId(Long empDataId) throws Exception {
        PermitImplementation latest = null;
        //order by tanggal pengajuan descending
        List<PermitImplementation> permitImplementations = permitImplementationDao.getAllDataByEmpDataId(empDataId, Order.desc("fillingDate"));
        if (!permitImplementations.isEmpty()) {
            latest = permitImplementations.get(0);
        }
        return latest;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Double getTotalActualPermit(Long empDataId, Long permitId, Date startDate, Date endDate) throws Exception {
        List<Date> actualPermits = this.getAllActualPermit(empDataId, permitId, startDate, endDate);
        return (double) actualPermits.size();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<Date> getAllActualPermit(Long empDataId, Long permitId, Date startDate, Date endDate) throws Exception {
        EmpData empData = empDataDao.getEntiyByPK(empDataId);
        PermitClassification permit = permitDao.getEntiyByPK(permitId);
        List<Date> actualPermits = new ArrayList<Date>();

//        if (permit.getDayType() == HRMConstant.LEAVE_DAY_TYPE_WORKING) {
//            actualPermits = wtScheduleShiftService.getAllWorkingDaysBetween(empData.getId(), startDate, endDate);
//        } else {
        for (int i = 1; i <= DateTimeUtil.getTotalDay(startDate, endDate); i++) {
            actualPermits.add(DateUtils.addDays(startDate, i));
        }
//        }

        return actualPermits;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String save(PermitImplementation entity, boolean isBypassApprovalChecking) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
//        String message = "error";
//
//        // check duplicate number filling
//        long totalDuplicates = permitImplementationDao.getTotalByNumberFilling(entity.getNumberFilling());
//        if (totalDuplicates > 0) {
//            throw new BussinessException("permitimplementation.error_duplicate_filling_number");
//        }
//
//        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
//        PermitClassification permit = permitDao.getEntiyByPK(entity.getPermitClassification().getId());
////        EmpData temporaryActing = entity.getTemporaryActing() != null ? empDataDao.getEntiyByPK(entity.getTemporaryActing().getId()) : null;
//        PermitDistribution permitDistribution = permitDistributionDao.getEntityByPermitClassificationIdAndEmpDataId(permit.getId(), empData.getId());
//
//        // check submitted permit tidak boleh lebih besar dari batasPengajuan di permitDefinition
//        long differenceDaysOfFilling = DateTimeUtil.getTotalDayDifference(new Date(), entity.getStartDate());
//        if (differenceDaysOfFilling > permit.getSubmittedLimit()) {
//            throw new BussinessException("permitimplementation.error_submitted_limit");
//        }
//
//        // check actualPermit yg diambil, tidak boleh lebih besar dari balancePermit yg tersedia
//        List<Date> actualPermits = this.getAllActualPermit(empData.getId(), permit.getId(), entity.getStartDate(), entity.getEndDate());
//        int totalActualPermits = actualPermits.size();
//        if (totalActualPermits > permitDistribution.getBalance()) {
//            throw new BussinessException("permitimplementation.error_permit_balance_is_insufficient");
//        }
//
//        entity.setEmpData(empData);
//        entity.setPermitClassification(permit);
////        entity.setTemporaryActing(temporaryActing);
//
//        String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
//        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
//        entity.setCreatedBy(createdBy);
//        entity.setCreatedOn(createdOn);
//
//        HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
//        List<ApprovalDefinition> appDefs = Lambda.extract(permit.getApprovalDefinitionPermits(), Lambda.on(ApprovalDefinitionPermit.class).getApprovalDefinition());
//
//        // check jika ada cuti yang masih diproses approval, hanya boleh mengajukan cuti jika tidak ada approval yang pending
//        if (approvalActivityDao.isStillHaveWaitingStatus(appDefs, requestUser.getUserId())) {
//            throw new BussinessException("permitimplementation.error_still_have_waiting_status");
//        }
//
//        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());
//        if (approvalActivity == null) {
//            //save entity dan detail-nya
//            permitImplementationDao.save(entity);
//            this.savePermitImplementationDate(entity, actualPermits);
//            this.creditPermitBalance(permit, permitDistribution, totalActualPermits);
//
//            message = "success_without_approval";
//        } else {
//            //parsing object to json and save to approval activity 
//            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
//            approvalActivity.setPendingData(gson.toJson(entity));
//            approvalActivityDao.save(approvalActivity);
//
//            message = "success_need_approval";
//
//            //sending email notification
//            this.sendingEmailApprovalNotif(approvalActivity);
//        }
//
//        return message;
    }

    private void creditPermitBalance(PermitDistribution permitDistribution, double actualPermit) {
        //cek di definisi cuti, jika isQuotaReduction is true, maka neraca cuti berkurang
//        if (permit.getIsQuotaReduction()) {
        double balance = permitDistribution.getBalance() - actualPermit;
        permitDistribution.setBalance(balance);
        permitDistribution.setUpdatedOn(new Date());
        permitDistribution.setUpdatedBy(UserInfoUtil.getUserName());
        permitDistributionDao.update(permitDistribution);

        NeracaPermit neracaPermit = new NeracaPermit();
        neracaPermit.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        neracaPermit.setPermitDistribution(permitDistribution);
        neracaPermit.setKredit(actualPermit);
        neracaPermit.setCreatedBy(UserInfoUtil.getUserName());
        neracaPermit.setCreatedOn(new Date());
        neracaPermitDao.save(neracaPermit);
//        }
    }

    private void debetPermitBalance(PermitClassification permit, PermitDistribution permitDistribution, double actualPermit) {
        //cek di definisi cuti, jika isQuotaReduction is true, maka neraca cuti bertambah
//        if (permit.getIsQuotaReduction()) {
        double balance = permitDistribution.getBalance() + actualPermit;
        permitDistribution.setBalance(balance);
        permitDistribution.setUpdatedOn(new Date());
        permitDistribution.setUpdatedBy(UserInfoUtil.getUserName());
        permitDistributionDao.update(permitDistribution);

        NeracaPermit neracaPermit = new NeracaPermit();
        neracaPermit.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        neracaPermit.setPermitDistribution(permitDistribution);
        neracaPermit.setDebet(actualPermit);
        neracaPermit.setCreatedBy(UserInfoUtil.getUserName());
        neracaPermit.setCreatedOn(new Date());
        neracaPermitDao.save(neracaPermit);
//        }
    }

//    private void savePermitImplementationDate(PermitImplementation permitImplementation, List<Date> actualPermits) {
//        for (Date actualPermit : actualPermits) {
//            PermitImplementationDate entity = new PermitImplementationDate();
//            entity.setPermitImplementation(permitImplementation);
//            entity.setActualDate(actualPermit);
//            entity.setIsCancelled(Boolean.FALSE);
//            permitImplementationDateDao.save(entity);
//        }
//    }
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PermitImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber) throws Exception {
        return permitImplementationDao.getEntityByApprovalActivityNumberWithDetail(activityNumber);

    }

//    @Override
//    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public String cancellation(Long permitImplementationId, List<PermitImplementationDate> actualPermits, List<PermitImplementationDate> cancellationPermits, String cancellationDescription) throws Exception {
//        String message = "success_without_approval";
//        Date now = new Date();
//
//        /**
//         * jika tanggal pembatalan telah melewati tanggal pelaksanaan, maka
//         * memerlukan approval/persetujuan process
//         */
//        Boolean isNeedApprovalChecking = Lambda.selectFirst(cancellationPermits, Lambda.having(Lambda.on(PermitImplementationDate.class).getActualDate().getTime(), Matchers.lessThan(now.getTime()))) != null;
//        PermitImplementation permitImplementation = permitImplementationDao.getEntiyByPK(permitImplementationId);
//
//        if (isNeedApprovalChecking) {
//            //check approval process
//            HrmUser requestUser = hrmUserDao.getByEmpDataId(permitImplementation.getEmpData().getId());
//            ApprovalActivity approvalActivity = super.checkApprovalProcess(HRMConstant.LEAVE_CANCELLATION, requestUser.getUserId());
//            if (approvalActivity == null) {
//                this.cancellationProcess(permitImplementationId, actualPermits, cancellationPermits, cancellationDescription);
//
//            } else {
//                //parsing object to json and save to approval activity 
//                JsonParser parser = new JsonParser();
//                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
//                JsonObject jsonObj = (JsonObject) parser.parse(gson.toJson(permitImplementation));
//                JsonArray cancellations = new JsonArray();
//                for (PermitImplementationDate ld : cancellationPermits) {
//                    JsonObject component = (JsonObject) parser.parse(gson.toJson(ld));
//                    cancellations.add(component);
//                }
//                JsonArray actuals = new JsonArray();
//                for (PermitImplementationDate ld : actualPermits) {
//                    JsonObject component = (JsonObject) parser.parse(gson.toJson(ld));
//                    actuals.add(component);
//                }
//                jsonObj.add("cancellationDates", cancellations);
//                jsonObj.add("actualDates", actuals);
//                jsonObj.addProperty("cancellationDescription", cancellationDescription);
//                jsonObj.addProperty("isCancellationProcess", Boolean.TRUE);
//
//                //save approval activity with previous activiy number, for approval historical purpose
//                approvalActivity.setPendingData(gson.toJson(jsonObj));
//                approvalActivity.setPreviousActivityNumber(permitImplementation.getApprovalActivityNumber());
//                approvalActivityDao.save(approvalActivity);
//
//                message = "success_need_approval";
//
//                //sending email notification
//                this.sendingEmailApprovalNotif(approvalActivity);
//            }
//        } else {
//            this.cancellationProcess(permitImplementationId, actualPermits, cancellationPermits, cancellationDescription);
//        }
//        return message;
//    }
//    private void cancellationProcess(Long permitImplementationId, List<PermitImplementationDate> actualPermits, List<PermitImplementationDate> cancellationPermits, String cancellationDescription) throws Exception {
//        double total = 0.0;
//
//        for (PermitImplementationDate entity : actualPermits) {
//            if (entity.getIsCancelled()) {
//                total = total - 1;
//                entity = permitImplementationDateDao.getEntiyByPK(entity.getId());
//                entity.setIsCancelled(Boolean.FALSE);
//                entity.setDescription(cancellationDescription);
//                entity.setUpdatedBy(UserInfoUtil.getUserName());
//                entity.setUpdatedOn(new Date());
//                permitImplementationDateDao.update(entity);
//            }
//        }
//        for (PermitImplementationDate entity : cancellationPermits) {
//            if (!entity.getIsCancelled()) {
//                total = total + 1;
//                entity = permitImplementationDateDao.getEntiyByPK(entity.getId());
//                entity.setIsCancelled(Boolean.TRUE);
//                entity.setDescription(cancellationDescription);
//                entity.setUpdatedBy(UserInfoUtil.getUserName());
//                entity.setUpdatedOn(new Date());
//                permitImplementationDateDao.update(entity);
//            }
//        }
//
//        PermitImplementation permitImplementation = permitImplementationDao.getEntiyByPK(permitImplementationId);
//        PermitDistribution permitDistribution = permitDistributionDao.getEntityByPermitIdAndEmpDataId(permitImplementation.getPermit().getId(), permitImplementation.getEmpData().getId());
//        if (total > 0) {
//            this.debetPermitBalance(permitImplementation.getPermit(), permitDistribution, total);
//        } else if (total < 0) {
//            total = total * (-1);
//            this.creditPermitBalance(permitImplementation.getPermit(), permitDistribution, total);
//        }
//    }
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ReportPermitHistoryModel> getReportPermitHistoryByParam(ReportPermitHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        List<PermitImplementation> listPermit = permitImplementationDao.getReportPermitHistoryByParam(parameter, firstResult, maxResults, orderable);
        List<ReportPermitHistoryModel> listModel = new ArrayList<ReportPermitHistoryModel>();
        for (PermitImplementation permit : listPermit) {
            ReportPermitHistoryModel model = new ReportPermitHistoryModel();
            model.setNikWithFullName(permit.getEmpData().getNikWithFullName());
            model.setStartDate(permit.getStartDate());
            model.setEndDate(permit.getEndDate());
            model.setPermitClassification(permit.getPermitClassification().getName());
            model.setNumberFilling(permit.getNumberFilling());
            List<ApprovalActivity> approvalActivities = approvalActivityDao.getAllDataByActivityNumberWithDetail(permit.getApprovalActivityNumber(), Order.desc("sequence"));
            if (!approvalActivities.isEmpty()) {
                HrmUser approver = hrmUserDao.getByUserId(approvalActivities.get(0).getApprovedBy());
                model.setApprovedBy(approver.getEmpData().getNikWithFullName());

            }
            listModel.add(model);
        }
        return listModel;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalReportPermitHistoryByParam(ReportPermitHistorySearchParameter parameter) throws Exception {
        return permitImplementationDao.getReportPermitHistoryTotalByParam(parameter);
    }

    private String getUploadPath(Long id, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "permit_" + id + "." + extension;
        return uploadPath;
    }

    @Override
    public void update(PermitImplementation t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String save(PermitImplementation entity, UploadedFile documentFile, boolean isBypassApprovalChecking) throws Exception {
        String message = "";
        // check duplicate number filling

        Long overTimeId = entity.getPermitClassification().getId();
        PermitClassification selectedPermitClass = permitDao.getEntiyByPK(overTimeId);
        Date currentDate = new Date();

        Integer selisihWaktu = DateTimeUtil.getTotalDayDifference(currentDate, entity.getStartDate());

        if (selisihWaktu > 0) {
            if (selisihWaktu > selectedPermitClass.getBatasMaju()) {
                throw new BussinessException("permitimplementation.error_out_off_range");
            }
        }

        if (selisihWaktu < 0) {
            selisihWaktu = Math.abs(selisihWaktu);
            if (selisihWaktu > selectedPermitClass.getBatasMudur()) {
                throw new BussinessException("permitimplementation.error_out_off_range");
            }
        }

        long totalDuplicates = permitImplementationDao.getTotalByNumberFilling(entity.getNumberFilling());
        if (totalDuplicates > 0) {
            throw new BussinessException("permitimplementation.error_duplicate_filling_number");
        }

        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
        PermitClassification permit = permitDao.getEntiyByPK(entity.getPermitClassification().getId());
        PermitDistribution permitDistribution = permitDistributionDao.getEntityByPermitClassificationIdAndEmpDataId(permit.getId(), empData.getId());

        // check actualPermit yg diambil, tidak boleh lebih besar dari balancePermit yg tersedia
        Double actualPermit = this.getTotalActualPermit(empData.getId(), permit.getId(), entity.getStartDate(), entity.getEndDate());
        if (actualPermit > permitDistribution.getBalance()) {
            throw new BussinessException("permitimplementation.error_permit_balance_is_insufficient");
        }

        HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        List<ApprovalDefinition> appDefs = Lambda.extract(permit.getApprovalDefinitionPermits(), Lambda.on(ApprovalDefinitionPermit.class).getApprovalDefinition());
        // check jika ada ijin yang masih diproses approval, hanya boleh mengajukan ijin jika tidak ada approval yang pending
        if (approvalActivityDao.isStillHaveWaitingStatus(appDefs, requestUser.getUserId())) {
            throw new BussinessException("permitimplementation.error_still_have_waiting_status");
        }
        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());

        String createdBy = org.apache.commons.lang.StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);
        if (approvalActivity == null) {
            entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            //Set Kodefikasi pada nomor
            TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.PERMIT_KODE);
            Long currentMaxId = permitImplementationDao.getCurrentMaxId();
            if (currentMaxId == null) {
                currentMaxId = 0L;
            }
            entity.setNumberFilling(KodefikasiUtil.getKodefikasi(((int) currentMaxId.longValue()), transactionCodefication.getCode()));

            entity.setEmpData(empData);
            entity.setPermitClassification(permit);
            if (documentFile != null) {
                String uploadPath = getUploadPath(entity.getId(), documentFile);
                facesIO.transferFile(documentFile);
                File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
                file.renameTo(new File(uploadPath));
                entity.setUploadPath(uploadPath);
            }

            permitImplementationDao.save(entity);
            message = "save_without_approval";

            this.creditPermitBalance(permitDistribution, actualPermit);
        } else {
            String uploadPath = null;
            if (documentFile != null) {
                uploadPath = getUploadPath(entity.getId(), documentFile);
                facesIO.transferFile(documentFile);
                File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
                file.renameTo(new File(uploadPath));
                entity.setUploadPath(uploadPath);
            }

            JsonParser parser = new JsonParser();
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));
            jsonObject.addProperty("permitImplementationFile", uploadPath);
            //save to approval activity
            approvalActivity.setPendingData(gson.toJson(jsonObject));
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingApprovalNotification(approvalActivity);
            message = "success_need_approval";
        }

        return message;
    }

    @Override
    protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        StringBuffer detail = new StringBuffer();
        HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        PermitImplementation entity = gson.fromJson(appActivity.getPendingData(), PermitImplementation.class);

        detail.append("Pengajuan ijin oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
        detail.append("Jenis: " + entity.getPermitClassification().getName() + ". ");
        detail.append("Dari tanggal " + dateFormat.format(entity.getStartDate()) + " s/d " + dateFormat.format(entity.getEndDate()));
        return detail.toString();
    }
}
