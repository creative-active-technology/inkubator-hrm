/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.google.gson.JsonParser;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.LoanNewApplicationDao;
import com.inkubator.hrm.dao.LoanNewSchemaDao;
import com.inkubator.hrm.dao.LoanNewTypeDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.util.HRMFinanceLib;
import com.inkubator.hrm.util.JadwalPembayaran;
import com.inkubator.hrm.util.LoanPayment;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.io.IOException;
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
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;

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

        //convert jadwalPembayaran to loanNewApplicationInstallment
        List<LoanNewApplicationInstallment> listLoanNewApplicationInstallments = new ArrayList<>();
        for (JadwalPembayaran jadwalPembayaran : loanPayment.getJadwalPembayarans()) {

            LoanNewApplicationInstallment loanNewApplicationInstallment = new LoanNewApplicationInstallment();
            loanNewApplicationInstallment.setInstallmentDate(jadwalPembayaran.getTanggalPembayaran());
            loanNewApplicationInstallment.setInterestNominal(jadwalPembayaran.getBunga());
            loanNewApplicationInstallment.setBasicNominal(jadwalPembayaran.getPokok());
            loanNewApplicationInstallment.setTotalPayment(jadwalPembayaran.getAngsuran());
            loanNewApplicationInstallment.setRemainingBasic(jadwalPembayaran.getSisaUtang());
            listLoanNewApplicationInstallments.add(loanNewApplicationInstallment);
        }

        return listLoanNewApplicationInstallments;
    }

    @Override
    protected void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception {
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
            this.save(entity, Boolean.FALSE, null);
        }

        //if there is no error, then sending the email notification
        sendingEmailApprovalNotif(appActivity);
    }

    private LoanNewApplication convertJsonToEntity(String json) {
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        LoanNewApplication entity = gson.fromJson(json, LoanNewApplication.class);
        return entity;
    }

    @Override
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
        sendingEmailApprovalNotif(appActivity);
    }

    @Override
    public void diverted(long approvalActivityId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getListApproverByListAppDefintion(List<ApprovalDefinition> listAppDef) throws Exception {
        return super.getListApproverByListAppDef(listAppDef);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getCurrentMaxId() {
        return this.loanNewApplicationDao.getCurrentMaxId();
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String saveWithApproval(LoanNewApplication entity) throws Exception {

        String result = isLoanAllowed(entity.getLoanNewType().getId(), entity.getLoanNewSchema().getId(), entity.getEmpData(), entity.getNominalPrincipal(), Boolean.FALSE, null);

        if (!StringUtils.equals("yes", result)) {
            throw new BussinessException(result);
        }

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

    private String save(LoanNewApplication entity, Boolean isBypassApprovalChecking, Long revisedApprActivityId) throws Exception {
        String result = "error";

        EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
        LoanNewType loanNewType = loanNewTypeDao.getEntiyByPK(entity.getLoanNewType().getId());
        String createdBy = StringUtils.isEmpty(entity.getCreatedBy()) ? UserInfoUtil.getUserName() : entity.getCreatedBy();
        Date createdOn = entity.getCreatedOn() == null ? new Date() : entity.getCreatedOn();

        entity.setEmpData(empData);
        entity.setLoanNewType(loanNewType);
        entity.setCreatedBy(createdBy);
        entity.setCreatedOn(createdOn);

        HrmUser requestUser = hrmUserDao.getByEmpDataId(empData.getId());
        System.out.println("requestUser.getUserId() : " + requestUser.getUserId());
        ApprovalActivity approvalActivity = isBypassApprovalChecking ? null : super.checkApprovalProcess(HRMConstant.LOAN, requestUser.getUserId());
        System.out.println("approvalActivity == null ? " + (approvalActivity == null));
        
        if (approvalActivity == null) {

            entity.setId(Integer.parseInt(RandomNumberUtil.getRandomNumber(9)));
            entity.setLoanStatus(HRMConstant.LOAN_UNDISBURSED);
            loanNewApplicationDao.save(entity);

            result = "success_without_approval";

        } else {
            approvalActivity.setPendingData(getJsonPendingData(entity));
            approvalActivity.setTypeSpecific(entity.getLoanNewType().getId());
            approvalActivityDao.save(approvalActivity);

            result = "success_need_approval";

            //sending email notification
            this.sendingEmailApprovalNotif(approvalActivity);
        }

        return result;
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
     allow only if : 
     - no previos loan with same loanType which still in approval process, or still have outstanding.
     - Total loan amount from existing loan application and previos loans not exceed maximum loan amount of selected employee loan schema.
     */
    private String isLoanAllowed(Long loanNewTypeId, Long loanNewSchemaId, EmpData empData, Double loanPrincipal, Boolean isRevised, String activityNumber) {

        HrmUser hrmUser = hrmUserDao.getByEmpDataId(empData.getId());
        LoanNewSchema loanNewSchema = loanNewSchemaDao.getEntiyByPK(loanNewSchemaId);

        //Get All pending request of employee
        List<ApprovalActivity> listPendingRequest = approvalActivityDao.getPendingRequest(hrmUser.getUserId());

        //filter only activity that comes from LOAN 
        listPendingRequest = Lambda.select(listPendingRequest, Lambda.having(Lambda.on(ApprovalActivity.class).getApprovalDefinition().getName(), Matchers.equalTo(HRMConstant.LOAN)));

        //grouping list by ActivityNumber
        Group<ApprovalActivity> groupPendingActivity = Lambda.group(listPendingRequest, Lambda.by(Lambda.on(ApprovalActivity.class).getActivityNumber()));

        //iterate each group list element
        for (String key : groupPendingActivity.keySet()) {
            List<ApprovalActivity> listGroupedActivity = groupPendingActivity.find(key);

            // Get activity with highest sequence from each grouped list activities
            ApprovalActivity approvalActivityWithHighestSequence = Lambda.selectMax(listGroupedActivity, Lambda.on(ApprovalDefinition.class).getSequence());

            //if previous loan approval activity with same loanNewType and still in approval process found,  return Exception Message
            if (approvalActivityWithHighestSequence.getTypeSpecific() == loanNewSchemaId) {
                return "loan.error_loan_with_same_type_found";
            }
        }

        //if previous loan with same loanNewType and still in approval process found,  return Exception Message
//        if (Lambda.extract(listPendingRequest, Lambda.on(ApprovalActivity.class).getTypeSpecific()).contains(loanNewTypeId)) {
//            return "loan.error_loan_with_same_type_found";
//        }
        //Check whether employee still have outstanding loan with same loanType
        List<LoanNewApplication> listPreviosUnpaidLoanWithSameLoanTypeId = loanNewApplicationDao.getListUnpaidLoanByEmpDataIdAndLoanNewTypeId(empData.getId(), loanNewTypeId);

        //if previous loan with same type and still remain outstanding found,  return Exception Message
        if (!listPreviosUnpaidLoanWithSameLoanTypeId.isEmpty()) {
            return "loan.error_loan_with_same_type_found";
        }

        /* Begin Calculate Total loan of exisiting loan application and previous loan of selected employee*/
        Double totalLoanByUser = loanPrincipal;

//        if (!listPendingRequest.isEmpty()) {
//            for (ApprovalActivity pendingRequest : listPendingRequest) {
//                String pendingData = pendingRequest.getPendingData();
//                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
//                LoanNewApplication loanNewApplication = gson.fromJson(pendingData, LoanNewApplication.class);
//                totalLoanByUser += loanNewApplication.getNominalPrincipal();
//            }
//        }
        //iterate each group list element
        for (String key : groupPendingActivity.keySet()) {
            List<ApprovalActivity> listGroupedActivity = groupPendingActivity.find(key);

            // Get activity with highest sequence from each grouped list activities
            ApprovalActivity approvalActivityWithHighestSequence = Lambda.selectMax(listGroupedActivity, Lambda.on(ApprovalDefinition.class).getSequence());

            if (!StringUtils.equals(activityNumber, approvalActivityWithHighestSequence.getActivityNumber())) {
                String pendingData = approvalActivityWithHighestSequence.getPendingData();
                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
                LoanNewApplication loanNewApplication = gson.fromJson(pendingData, LoanNewApplication.class);
                totalLoanByUser += loanNewApplication.getNominalPrincipal();
            }

        }

        if (!listPreviosUnpaidLoanWithSameLoanTypeId.isEmpty()) {
            for (LoanNewApplication prevLoan : listPreviosUnpaidLoanWithSameLoanTypeId) {
                totalLoanByUser += prevLoan.getNominalPrincipal();
            }
        }

        /* End Calculate Total loan of exisiting loan application and previous loan of selected employee*/
        //if total All Loan exceed maximum loan from employee loan schema, return Exception Message
        if (totalLoanByUser > loanNewSchema.getTotalMaximumLoan()) {
            return "loan.error_total_loan_exceed_max_loan_on_loan_scheme";
        }

        // if no rule violation found, return yes
        return "yes";
    }
}
