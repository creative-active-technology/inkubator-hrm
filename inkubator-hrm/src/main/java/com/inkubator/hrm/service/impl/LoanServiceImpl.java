package com.inkubator.hrm.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.LoanCanceledDao;
import com.inkubator.hrm.dao.LoanDao;
import com.inkubator.hrm.dao.LoanPaymentDetailDao;
import com.inkubator.hrm.dao.LoanSchemaDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanCanceled;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.util.HRMFinanceLib;
import com.inkubator.hrm.util.JadwalPembayaran;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.util.LoanPayment;
import com.inkubator.hrm.web.model.LoanCanceledModel;
import com.inkubator.hrm.web.model.LoanModel;
import com.inkubator.hrm.web.search.LoanSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author rizkykojek
 */
@Service(value = "loanService")
@Lazy
public class LoanServiceImpl extends BaseApprovalServiceImpl implements LoanService {

    @Autowired
    private LoanDao loanDao;
    @Autowired
    private LoanPaymentDetailDao loanPaymentDetailDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private LoanSchemaDao loanSchemaDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private LoanCanceledDao loanCanceledDao;
    @Autowired
    private TransactionCodeficationDao transactionCodeficationDao;
     @Autowired
    private FacesIO facesIO;
     
    @Override
    public Loan getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void save(Loan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Loan entity) throws Exception {
        Loan loan = loanDao.getEntiyByPK(entity.getId());
        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
        LoanSchema loanSchema = loanSchemaDao.getEntiyByPK(entity.getLoanSchema().getId());
        loan.setEmpData(empData);
        loan.setLoanSchema(loanSchema);
        loan.setNominalPrincipal(entity.getNominalPrincipal());
        loan.setLoanDate(entity.getLoanDate());
        loan.setLoanPaymentDate(entity.getLoanPaymentDate());
        loan.setInterestRate(entity.getInterestRate());
        loan.setTypeOfInterest(entity.getTypeOfInterest());
        loan.setTermin(entity.getTermin());
        loan.setUpdatedBy(UserInfoUtil.getUserName());
        loan.setUpdatedOn(new Date());
        loan.getLoanPaymentDetails().clear(); //clear list one to many
        loanDao.save(loan);

        /**
         * mekanismenya, clear list child-nya, lalu create ulang child-nya using
         * batch hibernate
         */
        List<LoanPaymentDetail> loanPaymentDetails = calculateLoanPaymentDetails(loan.getInterestRate(), loan.getTermin(), loan.getLoanPaymentDate(),
                loan.getNominalPrincipal(), loan.getTypeOfInterest());
        loanPaymentDetailDao.save(loanPaymentDetails, loan);
    }

    @Override
    public void saveOrUpdate(Loan enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan saveData(Loan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan updateData(Loan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan saveOrUpdateData(Loan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntityByPkIsActive(String id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntityByPkIsActive(String id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntityByPkIsActive(String id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntityByPkIsActive(Integer id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntityByPkIsActive(Integer id, Byte isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntityByPkIsActive(Integer id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntityByPkIsActive(Long id, Integer isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Loan getEntityByPkIsActive(Long id, Boolean isActive)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Loan entity) throws Exception {
        Loan loan = loanDao.getEntiyByPK(entity.getId());
        loanDao.delete(loan);

    }

    @Override
    public void softDelete(Loan entity) throws Exception {
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
    public List<Loan> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Loan> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Loan> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Loan> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Loan> getAllDataPageAble(int firstResult, int maxResults,
            Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Loan> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Loan> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Loan> getAllDataPageAbleIsActive(int firstResult,
            int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Loan> getByParam(LoanSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return loanDao.getByParam(parameter, firstResult, maxResults, orderable);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(LoanSearchParameter parameter) throws Exception {
        return loanDao.getTotalByParam(parameter);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Loan getEntityByPkWithDetail(Long id) throws Exception {
        return loanDao.getEntityByPkWithDetail(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String save(Loan entity, boolean isBypassApprovalChecking) throws Exception {
        String message = "error";

        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
        LoanSchema loanSchema = loanSchemaDao.getEntiyByPK(entity.getLoanSchema().getId());
        
        //Set Kodefikasi pada nomor
        TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.LOAN_KODE);
        Long currentMaxLoanId = loanDao.getCurrentMaxId();
        entity.setNomor(KodefikasiUtil.getKodefikasi(((int)currentMaxLoanId.longValue()), transactionCodefication.getCode()));
            
        entity.setEmpData(empData);
        entity.setLoanSchema(loanSchema);        

        String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);

        HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.LOAN, requestUser.getUserId());
        if (approvalActivity == null) {
            List<LoanPaymentDetail> loanPaymentDetails = calculateLoanPaymentDetails(entity.getInterestRate(), entity.getTermin(),
                    entity.getLoanPaymentDate(), entity.getNominalPrincipal(), entity.getTypeOfInterest());
            for (LoanPaymentDetail lpd : loanPaymentDetails) {
                lpd.setLoan(entity);
                lpd.setCreatedBy(createdBy);
                lpd.setCreatedOn(createdOn);
            }
            entity.setLoanPaymentDetails(ImmutableSet.copyOf(loanPaymentDetails));
            loanDao.save(entity);

            message = "success_without_approval";
        } else {
            //parsing object to json and save to approval activity 
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            approvalActivity.setPendingData(gson.toJson(entity));
            approvalActivityDao.save(approvalActivity);

            message = "success_need_approval";

            //sending email notification
            this.sendingEmailApprovalNotif(approvalActivity);
        }

        return message;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanPaymentDetail> getAllDataLoanPaymentDetails(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) throws Exception {
        return calculateLoanPaymentDetails(interestRate, termin, loanPaymentDate, nominalPrincipal, typeOfInterest);

    }

    private List<LoanPaymentDetail> calculateLoanPaymentDetails(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) {
        //set parameter for calculating jadwalPembayaran
        LoanPayment loanPayment = new LoanPayment();
        loanPayment.setBungaPertahun(interestRate);
        loanPayment.setLamaPinjaman(termin);
        loanPayment.setPaymentPeriod(1);
        loanPayment.setTanggalBayar(loanPaymentDate);
        loanPayment.setTotalPinjaman(nominalPrincipal);

        //calculate jadwalPembayaran
        if (typeOfInterest.equals(HRMConstant.ANNUITY)) {
            loanPayment = HRMFinanceLib.getLoanPaymentAnuitas(loanPayment);
        } else if (typeOfInterest.equals(HRMConstant.FLAT)) {
            loanPayment = HRMFinanceLib.getLoanPaymentFlateMode(loanPayment);
        } else if (typeOfInterest.equals(HRMConstant.FLOATING)) {
            loanPayment = HRMFinanceLib.getLoanPaymentEffectiveMode(loanPayment);
        }

        //convert jadwalPembayaran to loanPaymentDetails
        List<LoanPaymentDetail> listLoanPaymentDetails = new ArrayList<LoanPaymentDetail>();
        for (JadwalPembayaran jadwalPembayaran : loanPayment.getJadwalPembayarans()) {
            LoanPaymentDetail lpDetail = new LoanPaymentDetail();
            lpDetail.setDueDate(jadwalPembayaran.getTanggalPembayaran());
            lpDetail.setTotalPayment(jadwalPembayaran.getAngsuran());
            lpDetail.setInterest(jadwalPembayaran.getBunga());
            lpDetail.setPrincipal(jadwalPembayaran.getPokok());
            lpDetail.setRemainingPrincipal(jadwalPembayaran.getSisaUtang());
            listLoanPaymentDetails.add(lpDetail);
        }

        return listLoanPaymentDetails;
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
            Loan loan = gson.fromJson(pendingData, Loan.class);
            loan.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            this.save(loan, true);
        }

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
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
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            Loan loan = gson.fromJson(pendingData, Loan.class);
            loan.setStatusPencairan(HRMConstant.LOAN_REJECTED);
            loan.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            this.save(loan, true);
        }

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
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            String pendingData = appActivity.getPendingData();
            Loan loan = gson.fromJson(pendingData, Loan.class);
            loan.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            this.save(loan, true);
        }

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
    }

    @Override
    public void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception {
        //initialization
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }

        //parsing object data to json, for email purpose
        Loan loan = gson.fromJson(appActivity.getPendingData(), Loan.class);
        List<LoanPaymentDetail> loanPaymentDetails = calculateLoanPaymentDetails(loan.getInterestRate(), loan.getTermin(), loan.getLoanPaymentDate(),
                loan.getNominalPrincipal(), loan.getTypeOfInterest());
        double nominalInstallment = 0.0;
        double interestInstallment = 0.0;
        double totalNominalInstallment = 0.0;
        for (LoanPaymentDetail loanPaymentDetail : loanPaymentDetails) {
            //angsuran pertama
            nominalInstallment = loanPaymentDetail.getPrincipal();
            interestInstallment = loanPaymentDetail.getInterest();
            totalNominalInstallment = nominalInstallment + interestInstallment;
            break;
        }

        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("approvalActivityId", appActivity.getId());
            jsonObj.put("ccEmailAddresses", ccEmailAddresses);
            jsonObj.put("locale", appActivity.getLocale());
            jsonObj.put("proposeDate", dateFormat.format(loan.getCreatedOn()));
            jsonObj.put("loanSchemaName", loan.getLoanSchema().getName());
            jsonObj.put("nominalPrincipal", decimalFormat.format(loan.getNominalPrincipal()));
            jsonObj.put("interestRate", loan.getInterestRate() + " %");
            jsonObj.put("nominalInstallment", decimalFormat.format(nominalInstallment));
            jsonObj.put("interestInstallment", decimalFormat.format(interestInstallment));
            jsonObj.put("totalNominalInstallment", decimalFormat.format(totalNominalInstallment));

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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Loan getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber) throws Exception {
        return loanDao.getEntityByApprovalActivityNumberWithDetail(approvalActivityNumber);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void UpdateLoanAndsaveLoanCanceled(LoanCanceledModel loanCanceledModel) throws Exception {
        //change status pencairan ke cancel atau = 2
        Loan loanUpdate = loanDao.getEntiyByPK(loanCanceledModel.getId());
        loanUpdate.setStatusPencairan(HRMConstant.LOAN_CANCELED);
        loanDao.update(loanUpdate);
        //save data to loan_canceled table
        LoanCanceled loanCanceled = new LoanCanceled();
        loanCanceled.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        loanCanceled.setApprovalActivityNumber(loanCanceledModel.getApprovalActivityNumber());
        loanCanceled.setCreatedBy(UserInfoUtil.getUserName());
        loanCanceled.setCreatedOn(new Date());
        loanCanceled.setInterestRate(loanCanceledModel.getInterestRate());
        loanCanceled.setLoanDate(loanCanceledModel.getLoanDate());
        loanCanceled.setNominalPrincipal(loanCanceledModel.getNominalPrincipal());
        loanCanceled.setTermin(loanCanceledModel.getTermin());
        loanCanceled.setTypeOfInterest(loanCanceledModel.getTypeOfInterest());
        loanCanceled.setEmpData(empDataDao.getEntiyByPK(loanCanceledModel.getEmpData()));
        loanCanceled.setLoanSchema(loanSchemaDao.getEntiyByPK(loanCanceledModel.getLoanSchema()));
        loanCanceled.setDescription(loanCanceledModel.getKeterangan());
        loanCanceled.setCancelationDate(new Date());
        loanCanceledDao.save(loanCanceled);
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executeBatchFileUpload(LoanModel model) throws Exception {  
        
        LoanSchema loanSchema = loanSchemaDao.getEntiyByPK(model.getLoanSchemaId()); 
        EmpData empData = empDataDao.getEntityByNik(model.getNik());
        
        model.setInterestRate(loanSchema.getInterestRate());
        model.setTypeOfInterest(loanSchema.getTypeOfInterest());          
        
        if (empData != null) {         
            
            TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.LOAN_KODE);
            Long currentMaxLoanId = loanDao.getCurrentMaxId();           
            Date now = new Date();
            
            Loan loan = new Loan();
            loan.setId(model.getId());
            loan.setEmpData(empData);
            loan.setLoanSchema(loanSchema);
            loan.setNominalPrincipal(model.getNominalPrincipal());            
            loan.setLoanDate(now);
            loan.setLoanPaymentDate(DateTimeUtil.getDateFrom(now, 1, CommonUtilConstant.DATE_FORMAT_MONTH));
            loan.setInterestRate(loanSchema.getInterestRate());
            loan.setTypeOfInterest(loanSchema.getTypeOfInterest());
            loan.setTermin(Integer.valueOf(org.apache.commons.lang3.StringUtils.substringBeforeLast(model.getTermins().trim(), ".")));
            loan.setStatusPencairan(HRMConstant.LOAN_UNDISBURSED);
            loan.setNomor(KodefikasiUtil.getKodefikasi(((int)currentMaxLoanId.longValue()), transactionCodefication.getCode()));           
            loan.setCreatedBy(model.getCreatedBy());
            loan.setCreatedOn(now);
            
            List<LoanPaymentDetail> loanPaymentDetails = calculateLoanPaymentDetails(loan.getInterestRate(), loan.getTermin(),
                    loan.getLoanPaymentDate(), loan.getNominalPrincipal(), loan.getTypeOfInterest());
            for (LoanPaymentDetail lpd : loanPaymentDetails) {
                lpd.setLoan(loan);
                lpd.setCreatedBy(model.getCreatedBy());
                lpd.setCreatedOn(now);
            }
            loan.setLoanPaymentDetails(ImmutableSet.copyOf(loanPaymentDetails));            
            loanDao.save(loan);
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateFileAndDeleteData(UploadedFile documentFile) throws Exception {
        String uploadPath = this.getUploadPath(documentFile);        
        if (documentFile != null) {
            //remove old file
            Files.deleteIfExists(Paths.get(uploadPath));
            //added new file            
            facesIO.transferFile(documentFile);
            File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
            file.renameTo(new File(uploadPath));
        }

        return uploadPath;
    }
    
    private String getUploadPath( UploadedFile documentFile) {
        String extension = org.apache.commons.lang3.StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "loanupload." + extension;
        return uploadPath;
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Loan> getByParamByStatusUndisbursed(LoanSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        List<Loan> listLoan = loanDao.getByParamByStatusUndisbursed(parameter, firstResult, maxResults, orderable);       
        return listLoan;
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParamByStatusUndisbursed(LoanSearchParameter parameter) throws Exception {
        return loanDao.getTotalByParamByStatusUndisbursed(parameter);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateStatusAndDateDisbursementPaid(Long loanId,  Date dateDisbursement) throws Exception {
        Loan loan = loanDao.getEntiyByPK(loanId);     
        Date currentLoanPaymentDate = loan.getLoanPaymentDate();
        
        loan.setStatusPencairan(HRMConstant.LOAN_DISBURSED);         
        loan.setUpdatedBy(UserInfoUtil.getUserName());
        loan.setUpdatedOn(new Date());       
        loanDao.update(loan);                 
        
        //Jika Tgl Payment berbeda dari tanggal payment awal ketika pengajuan, maka create ulang jadwal pembayaran
        if(!DateTimeUtil.isSameDateWithTimeIgnore(currentLoanPaymentDate, dateDisbursement)){
            loan.setLoanPaymentDate(dateDisbursement);
            
            List<LoanPaymentDetail> loanPaymentDetails = calculateLoanPaymentDetails(loan.getInterestRate(), loan.getTermin(), dateDisbursement,
            loan.getNominalPrincipal(), loan.getTypeOfInterest());
            loanPaymentDetailDao.save(loanPaymentDetails, loan);
        
//            List<LoanPaymentDetail> loanPaymentDetails = calculateLoanPaymentDetails(loan.getInterestRate(), loan.getTermin(),
//                    dateDisbursement, loan.getNominalPrincipal(), loan.getTypeOfInterest());
//            for (LoanPaymentDetail lpd : loanPaymentDetails) {
//                lpd.setLoan(loan);
//                lpd.setCreatedBy(UserInfoUtil.getUserName());
//                lpd.setCreatedOn(new Date());
//            }
//            loan.setLoanPaymentDetails(ImmutableSet.copyOf(loanPaymentDetails));
        }
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<Loan> getAllDataByEmpDataIdAndStatusDisbursed(Long empDataId) throws Exception {
		List<Loan> listLoan = loanDao.getAllDataByEmpDataIdAndStatusDisbursed(empDataId);
		listLoan = Lambda.sort(listLoan, Lambda.on(Loan.class).getLoanDate(), Collections.reverseOrder());
		for(Loan loan : listLoan){
			loan.setMonthlyInstallment(loan.getLoanPaymentDetails().iterator().next().getTotalPayment());
		}
		return listLoan;
	}
}
