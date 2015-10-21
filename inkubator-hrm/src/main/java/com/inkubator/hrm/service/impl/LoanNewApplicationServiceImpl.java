/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.LoanNewApplicationDao;
import com.inkubator.hrm.dao.LoanNewApplicationInstallmentDao;
import com.inkubator.hrm.dao.LoanNewCancelationDao;
import com.inkubator.hrm.dao.LoanNewSchemaDao;
import com.inkubator.hrm.dao.LoanNewSchemaListOfEmpDao;
import com.inkubator.hrm.dao.LoanNewTypeDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.LoanNewCancelation;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.util.HRMFinanceLib;
import com.inkubator.hrm.util.JadwalPembayaran;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.util.LoanPayment;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.web.model.LoanHistoryViewModel;
import com.inkubator.hrm.web.model.LoanNewApplicationBoxViewModel;
import com.inkubator.hrm.web.model.LoanNewApplicationStatusViewModel;
import com.inkubator.hrm.web.model.LoanNewCancellationFormModel;
import com.inkubator.hrm.web.search.LoanNewApplicationBoxSearchParameter;
import com.inkubator.hrm.web.search.LoanNewSearchParameter;
import com.inkubator.hrm.web.search.LoanStatusSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "loanNewApplicationService")
@Lazy
public class LoanNewApplicationServiceImpl extends BaseApprovalServiceImpl implements LoanNewApplicationService {

    @Autowired
    private LoanNewApplicationDao loanNewApplicationDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private LoanNewTypeDao loanNewTypeDao;
    @Autowired
    private LoanNewSchemaDao loanNewSchemaDao;
    @Autowired
    private LoanNewSchemaListOfEmpDao loanNewSchemaListOfEmpDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private LoanNewCancelationDao LoanNewCancelationDao;
    @Autowired
    private LoanNewApplicationInstallmentDao loanNewApplicationInstallmentDao;
    @Autowired
    private TransactionCodeficationDao transactionCodeficationDao;
    

    @Override
    public LoanNewApplication getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntiyByPK(Long l) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication saveData(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication updateData(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication saveOrUpdateData(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewApplication getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(LoanNewApplication t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(LoanNewApplication t) throws Exception {
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
    public List<LoanNewApplication> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewApplication> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewApplicationInstallment> getAllDataLoanNewApplicationInstallment(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) throws Exception {
        return calculateLoanNewApplicationInstallment(interestRate, termin, loanPaymentDate, nominalPrincipal, typeOfInterest);
    }

    private List<LoanNewApplicationInstallment> calculateLoanNewApplicationInstallment(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) {
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
        
        List<JadwalPembayaran> listjadwalPembayaran = loanPayment.getJadwalPembayarans();
        List<LoanNewApplicationInstallment> listLoanNewApplicationInstallments = new ArrayList<>();

        //convert jadwalPembayaran to loanNewApplicationInstallment
        for (JadwalPembayaran jadwalPembayaran : listjadwalPembayaran) {
        	int index = listjadwalPembayaran.indexOf(jadwalPembayaran);
            LoanNewApplicationInstallment loanNewApplicationInstallment = new LoanNewApplicationInstallment();
            loanNewApplicationInstallment.setInstallmentDate(jadwalPembayaran.getTanggalPembayaran());
            loanNewApplicationInstallment.setInterestNominal(jadwalPembayaran.getBunga());
            loanNewApplicationInstallment.setBasicNominal(jadwalPembayaran.getPokok());
            loanNewApplicationInstallment.setTotalPayment(jadwalPembayaran.getAngsuran());
            loanNewApplicationInstallment.setRemainingBasic(jadwalPembayaran.getSisaUtang());
            loanNewApplicationInstallment.setNumOfInstallment(index + 1);
            listLoanNewApplicationInstallments.add(loanNewApplicationInstallment);
        }

        return listLoanNewApplicationInstallments;
    }

    @Override
    protected void sendingApprovalNotification(ApprovalActivity appActivity) throws Exception {
    	//send sms notification to approver if need approval OR
        //send sms notification to requester if need revision
		super.sendApprovalSmsnotif(appActivity);
		
		//initialization
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy", new Locale(appActivity.getLocale()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        //get all sendCC email address on status approve OR reject
        List<String> ccEmailAddresses = new ArrayList<String>();
        if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
            ccEmailAddresses = super.getCcEmailAddressesOnApproveOrReject(appActivity);
        }

        //parsing object data to json, for email purpose
        LoanNewApplication loanNewApplication = gson.fromJson(appActivity.getPendingData(), LoanNewApplication.class);
        List<LoanPaymentDetail> loanPaymentDetails = calculateLoanPaymentDetails(loanNewApplication.getLoanNewType().getInterest().doubleValue(), loanNewApplication.getTermin(), loanNewApplication.getDibursementDate(),
                loanNewApplication.getNominalPrincipal(), loanNewApplication.getLoanNewType().getInterestMethod());

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
            jsonObj.put("proposeDate", dateFormat.format(loanNewApplication.getCreatedOn()));
            jsonObj.put("loanSchemaName", loanNewApplication.getLoanNewSchema().getLoanSchemaName());
            jsonObj.put("nominalPrincipal", decimalFormat.format(loanNewApplication.getNominalPrincipal()));
            jsonObj.put("interestRate", loanNewApplication.getLoanNewType().getInterest() + " %");
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
            LoanNewApplication entity = this.convertJsonToEntity(appActivity.getPendingData());
            entity.setLoanStatus(HRMConstant.LOAN_UNDISBURSED); // set default loan application status
            entity.setApprovalActivityNumber(appActivity.getActivityNumber()); //set approval activity number, for history approval purpose

            /**
             * saving to DB
             */
            this.save(entity, Boolean.TRUE, null);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
    }

    private LoanNewApplication convertJsonToEntity(String json) {
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        LoanNewApplication entity = gson.fromJson(json, LoanNewApplication.class);
        return entity;
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
            LoanNewApplication entity = this.convertJsonToEntity(appActivity.getPendingData());
            entity.setLoanStatus(HRMConstant.LOAN_REJECTED); //set rejected application status
            entity.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            /**
             * saving to DB
             */
            this.save(entity, Boolean.TRUE, null);
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
            LoanNewApplication loan = gson.fromJson(pendingData, LoanNewApplication.class);
            loan.setApprovalActivityNumber(appActivity.getActivityNumber());  //set approval activity number, for history approval purpose

            this.save(loan, Boolean.TRUE, null);
        }

        //if there is no error, then sending the email notification
        sendingApprovalNotification(appActivity);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getListApproverByListAppDefintion(List<ApprovalDefinition> listAppDef, Long empDataId) throws Exception {
        HrmUser requester = hrmUserDao.getByEmpDataId(empDataId);
    	return super.getListApproverByListAppDef(listAppDef,requester.getUserId());
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getCurrentMaxId() throws Exception {
        return this.loanNewApplicationDao.getCurrentMaxId();
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String saveWithApproval(LoanNewApplication entity) throws Exception {

        String result = isLoanAllowed(entity.getLoanNewType().getId(), entity.getLoanNewSchema().getId(), entity.getEmpData(), entity.getNominalPrincipal(), Boolean.FALSE, null);

        if (!StringUtils.equals("yes", result)) {
            throw new BussinessException(result);
        }
        entity.setLoanStatus(HRMConstant.LOAN_UNDISBURSED);//Default status loan undisbursed
        return this.save(entity, Boolean.FALSE, null);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String saveWithRevised(LoanNewApplication entity, Long approvalActivityId, String activityNumber) throws Exception {
        String message = "error";

        String result = isLoanAllowed(entity.getLoanNewType().getId(), entity.getLoanNewSchema().getId(), entity.getEmpData(), entity.getNominalPrincipal(), Boolean.TRUE, activityNumber);

        if (!StringUtils.equals("yes", result)) {
            throw new BussinessException(result);
        }

        /**
         * proceed of revising data
         */
        String pendingData = this.getJsonPendingData(entity);
        
        this.revised(approvalActivityId, pendingData);

        message = "success_need_approval";
        return message;
    }

    private String save(LoanNewApplication entity, Boolean isBypassApprovalChecking,  Long revisedApprActivityId) throws Exception {
        String result = "error";

        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
        LoanNewType loanNewType = loanNewTypeDao.getEntiyByPK(entity.getLoanNewType().getId());
        String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();

        entity.setEmpData(empData);
        entity.setLoanNewType(loanNewType);
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);

       // HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        ApprovalActivity approvalActivity = this.checkApprovalIfAny(entity.getEmpData(), isBypassApprovalChecking);
        //ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.LOAN, requestUser.getUserId());
       
        if (approvalActivity == null) {

            entity.setId(Integer.parseInt(RandomNumberUtil.getRandomNumber(9)));
          
            //generate number of code
			String nomor = this.generateLoanNumber();	        
            entity.setNomor(nomor);
            
            loanNewApplicationDao.save(entity);

            result = "success_without_approval";

        } else {
            approvalActivity.setPendingData(getJsonPendingData(entity));
            approvalActivity.setTypeSpecific(entity.getLoanNewType().getId());
            approvalActivityDao.save(approvalActivity);

            result = "success_need_approval";

            //sending email notification
            this.sendingApprovalNotification(approvalActivity);
        }

        return result;
    }
    
    private String generateLoanNumber(){
		/** generate number form codification, from loan module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.LOAN);
        Long currentMaxId = loanNewApplicationDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}
    
    private ApprovalActivity checkApprovalIfAny(EmpData empData, Boolean isBypassApprovalChecking) throws Exception{
		/** check approval process if any,
		 *  return null if no need approval process */
		HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        LoanNewSchema loanNewSchema = loanNewSchemaListOfEmpDao.getEntityByEmpDataId(empData.getId()).getLoanNewSchema();
        //RmbsSchema rmbsSchema = rmbsSchemaListOfEmpDao.getAllDataByEmpDataId(empData.getId()).get(0).getRmbsSchema();
        List<ApprovalDefinition> appDefs = Lambda.extract(loanNewSchema.getApprovalDefinitionLoanSchemas(), Lambda.on(ApprovalDefinitionLoan.class).getApprovalDefinition());
        
        return isBypassApprovalChecking ? null : super.checkApprovalProcess(appDefs, requestUser.getUserId());
	}

    private String getJsonPendingData(LoanNewApplication entity) throws IOException {

        //parsing object to json 
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(gson.toJson(entity));

        return gson.toJson(jsonObject);
    }

    /*
     Loan validation
     allow if and only if : 
     - no previos loan with same loanType which still in approval process, or still have outstanding.
     - Total loan amount from current loan application and previos loans not exceed maximum loan amount of selected employee loan schema.
     */
    private String isLoanAllowed(Long loanNewTypeId, Long loanNewSchemaId, EmpData empData, Double loanPrincipal, Boolean isRevised, String activityNumber) {

        HrmUser hrmUser = hrmUserDao.getByEmpDataId(empData.getId());
        LoanNewSchema loanNewSchema = loanNewSchemaDao.getEntiyByPK(loanNewSchemaId);

        //Dapatkan semua List pending Approval Activity dari Karyawan yang sedang mengajukan pinjaman
        List<ApprovalActivity> listPendingRequest = approvalActivityDao.getPendingRequest(hrmUser.getUserId());

        //filter hanya yang datang dari LOAN
        listPendingRequest = Lambda.select(listPendingRequest, Lambda.having(Lambda.on(ApprovalActivity.class).getApprovalDefinition().getName(), Matchers.equalTo(HRMConstant.LOAN)));
        
        // Cek Type Specificnya, jika sama dengan tipe yang sedang diajukan, return key exception
        for(ApprovalActivity pendingApprovalActivity : listPendingRequest){
            if (pendingApprovalActivity.getTypeSpecific().equals(loanNewTypeId)) {
                return "loan.error_loan_with_same_type_found";
            }
        }
        
        // Dapatkan List Loan yang status <> HRMConstant.LOAN_PAID
        List<LoanNewApplication> listPreviosUnpaidLoanWithSameLoanTypeId = loanNewApplicationDao.getListUnpaidLoanByEmpDataIdAndLoanNewTypeId(empData.getId(), loanNewTypeId);
        
        // filter jadi dua list : listUndisbursedLoan (List Loan yang belum di cairkan), dan listdisbursedLoan (List Loan yang sudah di cairkan)
        List<LoanNewApplication> listUndisbursedLoan = Lambda.select(listPreviosUnpaidLoanWithSameLoanTypeId, Lambda.having(Lambda.on(LoanNewApplication.class).getLoanStatus(), Matchers.equalTo(HRMConstant.LOAN_UNDISBURSED)));
        List<LoanNewApplication> listdisbursedLoan = Lambda.select(listPreviosUnpaidLoanWithSameLoanTypeId, Lambda.having(Lambda.on(LoanNewApplication.class).getLoanStatus(), Matchers.equalTo(HRMConstant.LOAN_DISBURSED)));
        
        // Jika di list loan yang belum di cairkan, sudah ada loan yang tipe nya sama dengan yang sedang di ajukan, return key exception
        if(!listUndisbursedLoan.isEmpty()){
        	return "loan.error_loan_with_same_type_found";
        }
        
        // jika tidak ada di list yang belum dicairkan, lanjut cek ke list yang sudah di cairkan, cek satu persatu,
        // dapatkan lastInstallmentDate (Tanggal terakhir cicilan) dari loan tsb, bandingkan dengan tgl hari ini, jika tgl hari ini sebelum tgl cicilan terakhir, berarti belum lunas
        // return key exception
        for(LoanNewApplication loanNewApplication : listdisbursedLoan){
        	List<LoanNewApplicationInstallment> listInstallments = loanNewApplicationInstallmentDao.getListByLoanNewApplicationId(loanNewApplication.getId());
        	if(!listInstallments.isEmpty()){
        		Date lastInstallment = Lambda.max(listInstallments, Lambda.on(LoanNewApplicationInstallment.class).getInstallmentDate());
            	if(new Date().before(lastInstallment)){
            		return "loan.error_loan_with_same_type_found";
            	}
        	}
        	
        }

        /* Mulai Hitung Total loan dari pengajuan yang sedang berlangsung dan pengajuan sebelumnya*/
        Double totalLoanByUser = loanPrincipal;
        
        for(ApprovalActivity pendingApprovalActivity : listPendingRequest){
            
            //calculate only from different activity number
            if (!StringUtils.equals(activityNumber, pendingApprovalActivity.getActivityNumber())) {
                String pendingData = pendingApprovalActivity.getPendingData();
                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
                LoanNewApplication loanNewApplication = gson.fromJson(pendingData, LoanNewApplication.class);
                totalLoanByUser += loanNewApplication.getNominalPrincipal();
            }
        }

        if (!listUndisbursedLoan.isEmpty()) {
            for (LoanNewApplication prevLoan : listUndisbursedLoan) {
                totalLoanByUser += prevLoan.getNominalPrincipal();
            }
        }
        
        if (!listdisbursedLoan.isEmpty()) {
            for (LoanNewApplication prevLoan : listdisbursedLoan) {
                totalLoanByUser += prevLoan.getNominalPrincipal();
            }
        }

        /* Akhir perhitungan */
        
        //if total Nominal melebihi maksimum pinjaman dari skema pinjaman karyawan terpilih, return Exception Message
        if (totalLoanByUser > loanNewSchema.getTotalMaximumLoan()) {
            return "loan.error_total_loan_exceed_max_loan_on_loan_scheme";
        }

        // jika tidak ada pelanggaran, return yes
        return "yes";
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewApplication> getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewSchemaId(Long empDataId, Long loanNewSchemaId) throws Exception {
        return this.loanNewApplicationDao.getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewSchemaId(empDataId, loanNewSchemaId);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void cancelLoanApplicationAndSaveToLoanNewCancellation(LoanNewCancellationFormModel loanNewCancellationFormModel) throws Exception {
        Long actvityId = loanNewCancellationFormModel.getLoanPendingActivity();
        ApprovalActivity approvalActivity = approvalActivityDao.getEntityByPkWithDetail(actvityId);
        
        //check if there is already approved by someone(approver), then it shouldn't be cancelled
    	if(approvalActivityDao.isAlreadyHaveApprovedStatus(approvalActivity.getActivityNumber())){                
    		throw new BussinessException("approval.error_cancelled_already_approved");
    	}
        
        String activityNumber = approvalActivity.getActivityNumber();
        
        /** saving entity LoanApplication to DB */
    	LoanNewApplication application = this.convertJsonToEntity(approvalActivity.getPendingData());
    	application.setLoanStatus(HRMConstant.LOAN_CANCELED); // set cancelled application status
    	application.setApprovalActivityNumber(activityNumber);  //set approval activity number, for history approval purpose
        this.save(application, Boolean.TRUE, null);
        
        
        //Save LoanNewCancellation log
        LoanNewCancelation loanNewCancelation = new LoanNewCancelation();
        loanNewCancelation.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        loanNewCancelation.setApprovalActivityNumber(activityNumber);
        loanNewCancelation.setLoanNewApplication(application);
        loanNewCancelation.setEmpData(empDataDao.getEntiyByPK(loanNewCancellationFormModel.getEmpData().getId()));
        loanNewCancelation.setLoanNewSchema(loanNewSchemaDao.getEntiyByPK(loanNewCancellationFormModel.getLoanNewSchema().getId()));
        loanNewCancelation.setLoanNewType(loanNewTypeDao.getEntiyByPK(loanNewCancellationFormModel.getLoanNewType().getId()));
        loanNewCancelation.setLoanCancellationNumber(this.generateLoanCancelationNumber());
        loanNewCancelation.setLoanNumber(loanNewCancellationFormModel.getLoanNumber());
        loanNewCancelation.setReason(loanNewCancellationFormModel.getReasonCancellation());
        loanNewCancelation.setCancelationDate(loanNewCancellationFormModel.getLoanCancellationDate());
        loanNewCancelation.setCreatedOn(new Date());
        loanNewCancelation.setCreatedBy(UserInfoUtil.getUserName());
        
        LoanNewCancelationDao.save(loanNewCancelation);
        
        /** cancel this approval activity and saving log approver history */
        LoanNewSchemaListOfEmp loanNewSchemaListOfEmp = loanNewSchemaListOfEmpDao.getEntityWithDetailByEmpDataId(application.getEmpData().getId());
        LoanNewSchema loanNewSchema = loanNewSchemaDao.getEntityByPkFetchApprovalDefinition(loanNewSchemaListOfEmp.getLoanNewSchema().getId());
        List<ApprovalDefinition> appDefs = Lambda.extract(loanNewSchema.getApprovalDefinitionLoanSchemas(), Lambda.on(ApprovalDefinitionLoan.class).getApprovalDefinition());        
    	super.cancelled(actvityId, loanNewCancellationFormModel.getReasonCancellation(), appDefs);
        
    }
    
    private String generateLoanCancelationNumber(){
		/** generate number form codification, from loan module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.LOAN_CANCELLATION_KODE);
        Long currentMaxId = LoanNewCancelationDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewApplication> getByParamByStatusUndisbursed(LoanNewSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.loanNewApplicationDao.getByParamByStatusUndisbursed(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParamByStatusUndisbursed(LoanNewSearchParameter parameter) throws Exception {
        return this.loanNewApplicationDao.getTotalByParamByStatusUndisbursed(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewApplicationBoxViewModel> getUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        
        List<LoanNewApplicationBoxViewModel> listUndisbursedModel = this.loanNewApplicationDao.getUndisbursedActivityByParam(parameter, firstResult, maxResults, orderable);
        setUndisbursedLoanComplexData(listUndisbursedModel);
        
        return listUndisbursedModel;
    }
    
    private void setUndisbursedLoanComplexData(List<LoanNewApplicationBoxViewModel> listLoanUdisbursedModel){
        for(LoanNewApplicationBoxViewModel loanModel : listLoanUdisbursedModel){
            
            ApprovalActivity approvalActivity = approvalActivityDao.getEntiyByPK(loanModel.getApprovalActivityId().longValue());
            loanModel.setApprovalDate(approvalActivity.getApprovalTime());
            
            if(loanModel.getLoanNewApplicationId() == null){
                 Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
                LoanNewApplication loanNewApplicationTemp = gson.fromJson(loanModel.getJsonData(), LoanNewApplication.class);                
                
                loanModel.setLoanNumber(loanNewApplicationTemp.getNomor());
                loanModel.setNominalPrincipal(new BigDecimal(loanNewApplicationTemp.getNominalPrincipal()));
                loanModel.setApplicationDate(loanNewApplicationTemp.getApplicationDate());
                loanModel.setDisbursementStatus(HRMConstant.LOAN_UNDISBURSED);
                
            }else{
                
                LoanNewApplication loanNewApplication = loanNewApplicationDao.getEntiyByPK(loanModel.getLoanNewApplicationId());
                loanModel.setLoanNumber(loanNewApplication.getNomor());
                loanModel.setNominalPrincipal(new BigDecimal(loanNewApplication.getNominalPrincipal()));
                loanModel.setApplicationDate(loanNewApplication.getApplicationDate());
                loanModel.setDisbursementStatus(loanNewApplication.getLoanStatus());
            }
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter) throws Exception {
        return this.loanNewApplicationDao.getTotalUndisbursedActivityByParam(parameter);
    }

	@Override
	protected String getDetailSmsContentOfActivity(ApprovalActivity appActivity) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###");
		StringBuffer detail = new StringBuffer();
		HrmUser requester = hrmUserDao.getByUserId(appActivity.getRequestBy());
		LoanNewApplication entity = this.convertJsonToEntity(appActivity.getPendingData());
		
		detail.append("Pengajuan pinjaman oleh " + requester.getEmpData().getBioData().getFullName() + ". ");
		detail.append("Jenis: " + entity.getLoanNewType().getLoanTypeName() + ". ");
		detail.append("Sejumlah Rp. " + decimalFormat.format(entity.getNominalPrincipal()) + ", dengan termin " + entity.getTermin() + " kali");
		return detail.toString();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LoanHistoryViewModel> getListLoanHistoryByEmpDataId(Long empDataId) throws Exception {
		List<LoanHistoryViewModel> listLoanHistory = loanNewApplicationDao.getListLoanHistoryByEmpDataId(empDataId);
		for(LoanHistoryViewModel loanHistModel : listLoanHistory){
			
			List<LoanNewApplicationInstallment> listInstallment = calculateLoanNewApplicationInstallment(loanHistModel.getLoanInterestRate().doubleValue(), loanHistModel.getTotalNumberOfInstallment(), DateTimeUtil.getDateFrom(loanHistModel.getLoanPaymentDate(), loanHistModel.getBuffer(), CommonUtilConstant.DATE_FORMAT_MONTH), loanHistModel.getLoanNominal().doubleValue(), loanHistModel.getTypeOfInterest());
			LoanNewApplicationInstallment installmentMax = Lambda.selectMax(listInstallment, Lambda.on(LoanNewApplicationInstallment.class).getTotalPayment());
			LoanNewApplicationInstallment lastInstallment = Lambda.selectMax(listInstallment, Lambda.on(LoanNewApplicationInstallment.class).getInstallmentDate());
			loanHistModel.setInstallmentNominal(installmentMax.getTotalPayment());
			loanHistModel.setLastPaymentDate(lastInstallment.getInstallmentDate());
			
			Long totalAlreadyPaidInstallment = loanNewApplicationInstallmentDao.getTotalInstallmentByLoanNewApplicationId(loanHistModel.getLoanNewApplicationId());
			loanHistModel.setTotalAlreadyPaidInstallment(totalAlreadyPaidInstallment == null ? 0 : totalAlreadyPaidInstallment.intValue());
		}
		return listLoanHistory;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<LoanNewApplicationStatusViewModel> getAllDataLoanNewApplicationStatus(LoanStatusSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		List<LoanNewApplicationStatusViewModel> listAllData = loanNewApplicationDao.getAllDataLoanNewApplicationStatus(parameter, firstResult, maxResults, orderable);
		for(LoanNewApplicationStatusViewModel loanNewApplicationStatusViewModel : listAllData){
			loanNewApplicationStatusViewModel.setIsRendered(Boolean.FALSE);
			//kondisi kalo ditunda
			if(loanNewApplicationStatusViewModel.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL || loanNewApplicationStatusViewModel.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_REVISED){
				if(loanNewApplicationStatusViewModel.getLoanSchemaName() == null){
					Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
	            	LoanNewApplication loanNewApplicationTemp = gson.fromJson(loanNewApplicationStatusViewModel.getJsonData(), LoanNewApplication.class);                
	            	loanNewApplicationStatusViewModel.setLoanSchemaName(loanNewApplicationTemp.getLoanNewSchema().getLoanSchemaName());
	            	loanNewApplicationStatusViewModel.setNominalPrincipal(loanNewApplicationTemp.getNominalPrincipal());
	            	loanNewApplicationStatusViewModel.setTotalTermin(loanNewApplicationTemp.getTermin());
	            	loanNewApplicationStatusViewModel.setApprovalMessage(ResourceBundleUtil.getAsString("loanStatusMessage.pending"));
	            	
				}
			}else if(loanNewApplicationStatusViewModel.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED){
            	if(loanNewApplicationStatusViewModel.getLoanStatus() == HRMConstant.LOAN_DISBURSED && !loanNewApplicationStatusViewModel.getTermin().equals(loanNewApplicationStatusViewModel.getTotalTermin())){
        			loanNewApplicationStatusViewModel.setApprovalMessage(ResourceBundleUtil.getAsString("loanStatusMessage.withdraw"));
        			loanNewApplicationStatusViewModel.setIsRendered(Boolean.TRUE);
        		}else if(loanNewApplicationStatusViewModel.getLoanStatus() == HRMConstant.LOAN_DISBURSED && loanNewApplicationStatusViewModel.getTermin().equals(loanNewApplicationStatusViewModel.getTotalTermin())){
        			loanNewApplicationStatusViewModel.setApprovalMessage(ResourceBundleUtil.getAsString("loanStatusMessage.paid"));
        		}else if(loanNewApplicationStatusViewModel.getLoanStatus() == HRMConstant.LOAN_UNDISBURSED){
        			loanNewApplicationStatusViewModel.setApprovalMessage(ResourceBundleUtil.getAsString("loanStatusMessage.withdraw"));
        			loanNewApplicationStatusViewModel.setIsRendered(Boolean.TRUE);
        		}
			}else if(loanNewApplicationStatusViewModel.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED){
            	loanNewApplicationStatusViewModel.setApprovalMessage(ResourceBundleUtil.getAsString("loanStatusMessage.reject"));
			}else if(loanNewApplicationStatusViewModel.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_CANCELLED){
            	loanNewApplicationStatusViewModel.setApprovalMessage(ResourceBundleUtil.getAsString("approvalactivity.cancelled"));
			}
		}
		return listAllData;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalDataLoanNewApplicationStatus(
			LoanStatusSearchParameter parameter) throws Exception {
		// TODO Auto-generated method stub
		return loanNewApplicationDao.getTotalDataLoanNewApplicationStatus(parameter);
	}
}
