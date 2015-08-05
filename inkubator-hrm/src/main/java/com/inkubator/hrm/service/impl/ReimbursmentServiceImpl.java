/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.inkubator.hrm.dao.ReimbursmentDao;
import com.inkubator.hrm.dao.ReimbursmentSchemaDao;
import com.inkubator.hrm.dao.ReimbursmentSchemaEmployeeTypeDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ReimbursmentService;
import com.inkubator.hrm.web.search.ReimbursmentSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author Deni
 */
@Service(value = "reimbursmentService")
@Lazy
public class ReimbursmentServiceImpl extends BaseApprovalServiceImpl implements ReimbursmentService {

    @Autowired
    private ReimbursmentDao reimbursmentDao;
    @Autowired
    private ReimbursmentSchemaDao reimbursmentSchemaDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private ReimbursmentSchemaEmployeeTypeDao reimbursmentSchemaEmployeeTypeDao;
    @Autowired
    private FacesIO facesIO;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Reimbursment> getAllDataWithDetail(ReimbursmentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return reimbursmentDao.getAllDataWithDetail(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalReimbursmentByParam(ReimbursmentSearchParameter searchParameter) throws Exception {
        return reimbursmentDao.getTotalReimburstByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Reimbursment getEntityByPkWithDetail(Long id) throws Exception {
        return reimbursmentDao.getEntityByPkWithDetail(id);
    }

    @Override
    public Reimbursment getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Reimbursment getEntiyByPK(Long id) throws Exception {
        return reimbursmentDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Reimbursment entity) throws Exception {
        long totalDuplicates = reimbursmentDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("reimbursement.error_duplicate_reimbursement_code");
        }
        entity.setEmpData(empDataDao.getEntiyByPK(entity.getEmpData().getId()));
        entity.setReimbursmentSchema(reimbursmentSchemaDao.getEntiyByPK(entity.getReimbursmentSchema().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.reimbursmentDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Reimbursment entity) throws Exception {
        long totalDuplicates = reimbursmentDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("reimbursement.error_duplicate_reimbursement_code");
        }
        Reimbursment update = reimbursmentDao.getEntiyByPK(entity.getId());
        update.setCode(entity.getCode());
        update.setEmpData(empDataDao.getEntiyByPK(entity.getEmpData().getId()));
        update.setReimbursmentSchema(reimbursmentSchemaDao.getEntiyByPK(entity.getReimbursmentSchema().getId()));
        update.setNominal(entity.getNominal());
        update.setClaimDate(entity.getClaimDate());
        update.setQuantity(entity.getQuantity());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setReimbursmentDocument(entity.getReimbursmentDocument());
        update.setUpdatedOn(new Date());
        this.reimbursmentDao.update(update);
    }

    @Override
    public void saveOrUpdate(Reimbursment enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment saveData(Reimbursment entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment updateData(Reimbursment entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment saveOrUpdateData(Reimbursment entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reimbursment getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Reimbursment entity) throws Exception {
        this.reimbursmentDao.delete(entity);
    }

    @Override
    public void softDelete(Reimbursment entity) throws Exception {
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
    public List<Reimbursment> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reimbursment> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reimbursment> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reimbursment> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reimbursment> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reimbursment> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reimbursment> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reimbursment> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String save(Reimbursment reimbursment, UploadedFile reimbursmentFile, boolean isBypassApprovalChecking) throws Exception {
        String message = "error";
        int result;
        int isNotEmployee = 0;
        BigDecimal jmlNominalReimbursment = new BigDecimal(0);
        int jumlahQuantity = 0;
        //cek duplicat kode
        long totalDuplicates = reimbursmentDao.getTotalByCode(reimbursment.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("reimbursement.error_duplicate_reimbursement_code");
        }
        EmpData empData = empDataDao.getEntiyByPK(reimbursment.getEmpData().getId());
        ReimbursmentSchema reimbursmentSchema = reimbursmentSchemaDao.getEntiyByPK(reimbursment.getReimbursmentSchema().getId());

        //validate if not employee term
        for (ReimbursmentSchemaEmployeeType reimbursmentSchemaEmployeeType : this.reimbursmentSchemaEmployeeTypeDao.getByUserId(reimbursmentSchema.getId())) {
            if (empData.getEmployeeType().getName().equals(reimbursmentSchemaEmployeeType.getEmployeeType().getName())) {
                isNotEmployee += 1;
            }
        }

        if (isNotEmployee == 0) {
            throw new BussinessException("reimbursment.is_not_employee_type_term");
        }

        //validate basic value
        
        List<Reimbursment> totalNominalSalary = reimbursmentDao.getAllDataWithEmpIdAndReimbursmentSchemaId(empData.getId(), reimbursmentSchema.getId());
        //hitung jumlah rembesan yg sudah tersimpan didatabase
        for (Reimbursment jmhSalary : totalNominalSalary) {
            if (jmhSalary.getNominal() != null) {
                jmlNominalReimbursment = jmlNominalReimbursment.add(jmhSalary.getNominal());
            }
            if (jmhSalary.getQuantity() != null) {
                jumlahQuantity = jumlahQuantity + jmhSalary.getQuantity();
            }
        }
        //hitung jumlah rembesan saat ini
        if (reimbursment.getNominal() != null) {
            jmlNominalReimbursment = jmlNominalReimbursment.add(reimbursment.getNominal());
        }
        String decryptSalary = empData.getBasicSalaryDecrypted();
        
        //replace "." dan convert ke decimal
        BigDecimal salary = new BigDecimal(decryptSalary);
        if (reimbursmentSchema.getBasicValue() != null) {
            if (reimbursmentSchema.getBasicValue().equals(HRMConstant.BASIC_VALUE_NOMINAL)) {
                //validasi nominal
                result = jmlNominalReimbursment.compareTo(reimbursmentSchema.getNominalUnit());
                if (result == 1) {
                    throw new BussinessException("reimbursment.nominal_unit_is_greater");
                }
            } else {
                //bandingkan jumlah rembesment dengan gaji
                BigDecimal ratioSalary = new BigDecimal(reimbursmentSchema.getRatioSalary());
                result = jmlNominalReimbursment.compareTo(ratioSalary.multiply(salary));
                if (result == 1) {
                    throw new BussinessException("reimbursment.reimbersment_is_greater_than_salary");
                }
            }
        }

        //validate quantity
        if (reimbursment.getQuantity() != null) {
            jumlahQuantity = jumlahQuantity + reimbursment.getQuantity();
        }
        if (reimbursmentSchema.getQuantity() != null) {
            if (jumlahQuantity > reimbursmentSchema.getQuantity()) {
                throw new BussinessException("reimbursment.reimbersment_quantity_is_greater");
            }
        }

        //validate tanggal mulai bekerja karyawan
        Integer effectiveSince = Integer.valueOf(DateTimeUtil.getTotalMonthDifference(reimbursment.getEmpData().getJoinDate(), new Date()));
        if (effectiveSince < reimbursmentSchema.getEffectiveDate()) {
            throw new BussinessException("reimbursment.reimbersment_effective_since_lower_than_effective_since_term");
        }

        HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.REIMBURSEMENT, requestUser.getUserId());
        
        String createdBy = org.apache.commons.lang.StringUtils.isEmpty(reimbursment.getCreatedBy()) ? UserInfoUtil.getUserName() : reimbursment.getCreatedBy();
        Date createdOn = reimbursment.getCreatedOn() == null ? new Date() : reimbursment.getCreatedOn();
            
        if (approvalActivity == null) {
            reimbursment.setEmpData(empData);
            reimbursment.setReimbursmentSchema(reimbursmentSchema);
            
            
            reimbursment.setCreatedBy(createdBy);
            reimbursment.setCreatedOn(createdOn);
            if (reimbursmentFile != null) {
                InputStream inputStream = null;
                byte[] buffer = null;
                inputStream = reimbursmentFile.getInputstream();
                buffer = IOUtils.toByteArray(inputStream);
                reimbursment.setReimbursmentDocument(buffer);
            }
            reimbursmentDao.save(reimbursment);
            message = "success_without_approvl";
        } else {
            String uploadPath = null;
            if (reimbursmentFile != null) {
                uploadPath = getUploadPath(Long.parseLong(RandomNumberUtil.getRandomNumber(9)), reimbursmentFile);
                facesIO.transferFile(reimbursmentFile);
                File file = new File(facesIO.getPathUpload() + reimbursmentFile.getFileName());
                file.renameTo(new File(uploadPath));
            }
            /*ReimbursmentModelJsonParsing reimbursmentModelJsonParsing = new ReimbursmentModelJsonParsing();
             //isi data reimbursment model untuk diparsing ke json
             reimbursmentModelJsonParsing.setCode(reimbursment.getCode());
             reimbursmentModelJsonParsing.setClaimDate(reimbursment.getClaimDate());
             reimbursmentModelJsonParsing.setQuantity(reimbursment.getQuantity());
             reimbursmentModelJsonParsing.setNominal(reimbursment.getNominal());
             reimbursmentModelJsonParsing.setReimbursmentSchemaId(reimbursmentSchema.getId());
             reimbursmentModelJsonParsing.setEmpDataId(empData.getId());
             reimbursmentModelJsonParsing.setCreateBy(UserInfoUtil.getUserName());
             reimbursmentModelJsonParsing.setReimbursmentDocument(reimbursment.getReimbursmentDocument());
             reimbursmentModelJsonParsing.setCreateDate(new Date());
             reimbursmentModelJsonParsing.setReimbursmentFileName(uploadPath);*/
            //convert reimbursmentModelJson ke json
            //String json = JsonConverter.getJson(reimbursmentModelJsonParsing, "dd-MM-yyyy");
            reimbursment.setCreatedBy(createdBy);
            reimbursment.setCreatedOn(createdOn);
            JsonParser parser = new JsonParser();
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(reimbursment));
            jsonObject.addProperty("reimbursmentFileName", uploadPath);
            //save to approval activity
            approvalActivity.setPendingData(gson.toJson(jsonObject));
            approvalActivityDao.save(approvalActivity);

            //sending email notification
            this.sendingApprovalNotification(approvalActivity);
            message = "success_need_approval";
        }
        return message;
    }

    public void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
    	//send sms notification to approver if need approval OR
        //send sms notification to requester if need revision
		super.sendApprovalSmsnotif(appActivity);
		
        //initialization
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        double totalAmount = 0;

        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }

        //parsing object data to json, for email purpose

//		Reimbursment reimbursment = gson.fromJson(appActivity.getPendingData(), Reimbursment.class);   	
        /*ReimbursmentModelJsonParsing reimbursment = (ReimbursmentModelJsonParsing) JsonConverter.getClassFromJson(appActivity.getPendingData(), ReimbursmentModelJsonParsing.class, "dd-MM-yyyy");
         ReimbursmentSchema reimbursmentSchema = reimbursmentSchemaDao.getEntiyByPK(reimbursment.getReimbursmentSchemaId());
        */
        Reimbursment reimbursment = gson.fromJson(appActivity.getPendingData(), Reimbursment.class);
        ReimbursmentSchema reimbursmentSchema = reimbursmentSchemaDao.getEntiyByPK(reimbursment.getReimbursmentSchema().getId());
        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("reimbursment_schema", reimbursmentSchema.getName());
            jsonObj.put("proposeDate", dateFormat.format(reimbursment.getCreatedOn()));
            jsonObj.put("claim_date", dateFormat.format(reimbursment.getClaimDate()));
            if (reimbursment.getNominal() != null) {
                jsonObj.put("nominalOrUnit", new DecimalFormat("###,###").format(reimbursment.getNominal()));
            } else {
                jsonObj.put("nominalOrUnit", reimbursment.getQuantity() + " Unit");
            }
            jsonObj.put("reimbursmentNo", reimbursment.getCode());
            Date deadline = DateUtils.addDays(appActivity.getCreatedTime(), appActivity.getApprovalDefinition().getDelayTime());  
            jsonObj.put("deadline", dateFormat.format(deadline));

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

    private String getUploadPath(Long id, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "reimburstment_" + id + "." + extension;
        return uploadPath;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception {
        Map<String, Object> result = super.approvedAndCheckNextApproval(approvalActivityId, null, comment);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {

            //parsing from json to entity
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            Reimbursment reimbursment = gson.fromJson(pendingData, Reimbursment.class);
            reimbursment.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose 

            //convert to UploadedFile before saving
            UploadedFile uploadedFile = null;
            JsonElement elReimbursment = gson.fromJson(pendingData, JsonObject.class).get("reimbursmentFileName");
            if (!elReimbursment.isJsonNull()) {
                String reimbursmentFilePath = elReimbursment.getAsString();
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

            this.save(reimbursment, uploadedFile, true);
            //saveModelJson(reimbursment, , appActivity, true);
        }
        sendingApprovalNotification(appActivity);
    }

    /*private void saveModelJson(Reimbursment reimbursment, String fileName, ApprovalActivity appActivity, boolean isBypassApprovalChecking) throws Exception {
     // check duplicate business travel number
     long totalDuplicates = reimbursmentDao.getTotalByCode(reimbursment.getCode());
     if (totalDuplicates > 0) {
     throw new BussinessException("businesstravel.error_duplicate_business_travel_no");
     }

     EmpData empData = empDataDao.getByEmpIdWithDetail(reimbursment.getEmpData().getId());
     ReimbursmentSchema reimbursmentSchema = reimbursmentSchemaDao.getEntiyByPK(reimbursment.getReimbursmentSchema().getId());
     Reimbursment reimbursment = new Reimbursment();
     reimbursment.setClaimDate(reimbursmentModelJsonParsing.getClaimDate());
     reimbursment.setCode(reimbursmentModelJsonParsing.getCode());
     reimbursment.setCreatedBy(reimbursmentModelJsonParsing.getCreateBy());
     reimbursment.setCreatedOn(reimbursmentModelJsonParsing.getCreateDate());
        
     reimbursment.setApprovalActivityNumber(appActivity.getActivityNumber());
     if (reimbursmentModelJsonParsing.getNominal() != null) {
     reimbursment.setNominal(reimbursmentModelJsonParsing.getNominal());
     }
     if (reimbursmentModelJsonParsing.getQuantity() != null) {
     reimbursment.setQuantity(reimbursmentModelJsonParsing.getQuantity());
     }
     reimbursment.setEmpData(empData);
     reimbursment.setReimbursmentSchema(reimbursmentSchema);
     this.reimbursmentDao.save(reimbursment);

     HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
     ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.BUSINESS_TRAVEL, requestUser.getUserId());
     if (approvalActivity == null) {
     
     reimbursment.setEmpData(empData);
     reimbursment.setReimbursmentSchema(reimbursmentSchema);
     reimbursment.setCreatedBy(UserInfoUtil.getUserName());
     reimbursment.setCreatedOn(new Date());
     if (StringUtils.isNotEmpty(fileName)) {
     InputStream inputStream = null;
     byte[] buffer = null;
     File reimbursmentFileDelete = new File(fileName);
     inputStream = new FileInputStream(fileName);
     buffer = IOUtils.toByteArray(inputStream);
     reimbursment.setReimbursmentDocument(buffer);
     reimbursmentFileDelete.delete();
     }
     reimbursmentDao.save(reimbursment);
     } else {
     
     //convert reimbursmentModelJson ke json
     String json = JsonConverter.getJson(reimbursmentModelJsonParsing, "dd-MM-yyyy");
     //save to approval activity
     approvalActivity.setPendingData(json);
     approvalActivityDao.save(approvalActivity);

     //sending email notification
     this.sendingEmailApprovalNotif(approvalActivity);
     }

     }*/
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
            /*ReimbursmentModelJsonParsing reimbursmentModelJsonParsing = (ReimbursmentModelJsonParsing) JsonConverter.getClassFromJson(appActivity.getPendingData(), ReimbursmentModelJsonParsing.class, "dd-MM-yyyy");
             ReimbursmentSchema reimbursmentSchema = reimbursmentSchemaDao.getEntiyByPK(reimbursmentModelJsonParsing.getReimbursmentSchemaId());
             Reimbursment reimbursment = new Reimbursment();
             EmpData empData = empDataDao.getByEmpIdWithDetail(reimbursmentModelJsonParsing.getEmpDataId());
             reimbursment.setClaimDate(reimbursmentModelJsonParsing.getClaimDate());
             reimbursment.setCode(reimbursmentModelJsonParsing.getCode());
             reimbursment.setCreatedBy(reimbursmentModelJsonParsing.getCreateBy());
             reimbursment.setCreatedOn(reimbursmentModelJsonParsing.getCreateDate());
             reimbursment.setEmpData(empData);
             reimbursment.setApprovalActivityNumber(appActivity.getActivityNumber());
             if (reimbursmentModelJsonParsing.getNominal() != null) {
             reimbursment.setNominal(reimbursmentModelJsonParsing.getNominal());
             }
             if (reimbursmentModelJsonParsing.getQuantity() != null) {
             reimbursment.setQuantity(reimbursmentModelJsonParsing.getQuantity());
             }
             reimbursment.setReimbursmentSchema(reimbursmentSchema);*/
            //parsing from json to entity
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            Reimbursment reimbursment = gson.fromJson(pendingData, Reimbursment.class);
            reimbursment.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose 

            //convert to UploadedFile before saving
            UploadedFile uploadedFile = null;
            JsonElement elReimbursment = gson.fromJson(pendingData, JsonObject.class).get("reimbursmentFileName");
            if (!elReimbursment.isJsonNull()) {
                String reimbursmentFilePath = elReimbursment.getAsString();
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
            this.save(reimbursment, uploadedFile, true);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Reimbursment getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber) throws Exception {
        return reimbursmentDao.getEntityByApprovalActivityNumberWithDetail(approvalActivityNumber);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Reimbursment getEntityByReimbursmentNoWithDetail(String reimbursmentNo) throws Exception {
        return reimbursmentDao.getEntityByReimbursmentNoWithDetail(reimbursmentNo);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void diverted(long approvalActivityId) throws Exception {
        Map<String, Object> result = super.divertedAndCheckNextApproval(approvalActivityId);
        ApprovalActivity appActivity = (ApprovalActivity) result.get("approvalActivity");
        if (StringUtils.equals((String) result.get("isEndOfApprovalProcess"), "true")) {
            //parsing from json to entity
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            Reimbursment reimbursment = gson.fromJson(pendingData, Reimbursment.class);
            reimbursment.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose 

            //convert to UploadedFile before saving
            UploadedFile uploadedFile = null;
            JsonElement elReimbursment = gson.fromJson(pendingData, JsonObject.class).get("reimbursmentFileName");
            if (!elReimbursment.isJsonNull()) {
                String reimbursmentFilePath = elReimbursment.getAsString();
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
            this.save(reimbursment, uploadedFile, true);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
    }

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###");
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
		Reimbursment entity = gson.fromJson(appActivity.getPendingData(), Reimbursment.class);
		
		detail.append("Pengajuan penggantian oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
		detail.append("Skema " + entity.getReimbursmentSchema().getName() + ". ");
		detail.append("Jumlah " + entity.getQuantity() + ". ");
		detail.append("Nominal Rp. " + decimalFormat.format(entity.getNominal()));
		return detail.toString();
	}
}
