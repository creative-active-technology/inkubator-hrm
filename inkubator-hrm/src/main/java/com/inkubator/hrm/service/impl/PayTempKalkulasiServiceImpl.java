/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BenefitGroupRateDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.LoanPaymentDetailDao;
import com.inkubator.hrm.dao.PayComponentDataExceptionDao;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.dao.PayTempKalkulasiDao;
import com.inkubator.hrm.dao.PayTempKalkulasiEmpPajakDao;
import com.inkubator.hrm.dao.PayTempUploadDataDao;
import com.inkubator.hrm.dao.ReimbursmentDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.BenefitGroupRate;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.model.SalaryJournalModel;

/**
 *
 * @author denifahri
 */
@Service(value = "payTempKalkulasiService")
@Lazy
public class PayTempKalkulasiServiceImpl extends IServiceImpl implements PayTempKalkulasiService {

    @Autowired
    private PayTempKalkulasiDao payTempKalkulasiDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private PaySalaryComponentDao paySalaryComponentDao;
    @Autowired
    private PayComponentDataExceptionDao payComponentDataExceptionDao;
    @Autowired
    private PayTempUploadDataDao payTempUploadDataDao;
    @Autowired
    private WtPeriodeDao wtPeriodeDao;
    @Autowired
    private LoanPaymentDetailDao loanPaymentDetailDao;
    @Autowired
    private ReimbursmentDao reimbursmentDao;
    @Autowired
    private BenefitGroupRateDao benefitGroupRateDao;
    @Autowired
    private PayTempKalkulasiEmpPajakDao payTempKalkulasiEmpPajakDao;

    @Override
    public PayTempKalkulasi getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(PayTempKalkulasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PayTempKalkulasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(PayTempKalkulasi enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi saveData(PayTempKalkulasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi updateData(PayTempKalkulasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi saveOrUpdateData(PayTempKalkulasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayTempKalkulasi getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PayTempKalkulasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(PayTempKalkulasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalData() throws Exception {
        return payTempKalkulasiDao.getTotalData();
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
    public List<PayTempKalkulasi> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempKalkulasi> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempKalkulasi> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempKalkulasi> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempKalkulasi> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempKalkulasi> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempKalkulasi> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayTempKalkulasi> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
     @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
     public void calculatePayRoll() throws Exception {
     List<EmpData> totalEmployee = empDataDao.getAllDataNotTerminate();
     List<PaySalaryComponent> totalPayComponet = paySalaryComponentDao.getAllData();
     ScriptEngineManager mgr = new ScriptEngineManager();
     ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
     Double basicSalary = null;
     Double workingDay = null;
     Double lessTime = null;
     Double moreTime = null;
     Double overTIme = null;
     Double totalDay = null;
     Double outPut;
     System.out.println(" Jumlah Component " + totalPayComponet.size());
     System.out.println(" Jumlah Employye " + totalEmployee.size());
     int i = 1;
     for (EmpData empData : totalEmployee) {
     List<PayComponentDataException> totalPayComponentException = payComponentDataExceptionDao.getAllByEmpId(empData.getId());
     List<PayTempKalkulasi> dataToSave = new ArrayList();
     for (PayComponentDataException dataException : totalPayComponentException) {
     PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
     kalkulasi.setEmpData(empData);
     kalkulasi.setNominal(dataException.getNominal());
     kalkulasi.setPaySalaryComponent(dataException.getPaySalaryComponent());
     kalkulasi.setCreatedBy(UserInfoUtil.getUserName());
     kalkulasi.setCreatedOn(new Date());
     kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
     dataToSave.add(kalkulasi);
     }
     this.payTempKalkulasiDao.saveBatch(dataToSave);
     int timeTmb = 0;
     timeTmb = DateTimeUtil.getTotalDay(empData.getJoinDate(), new Date());
     System.out.println(" Total Waktu nya " + timeTmb);
     List<PaySalaryComponent> totalPayComponetNotExcp = paySalaryComponentDao.getAllDataByEmpTypeIdAndActiveFromTmAndModelCompNotIn(empData.getEmployeeType().getId(), timeTmb);
     System.out.println(" Employee Name " + empData.getBioData().getFirstName());
     System.out.println(" Ukuran Hak nya " + totalPayComponetNotExcp.size());

     for (PaySalaryComponent paySalaryComponent : totalPayComponetNotExcp) {
     if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_UPLOAD)) {
     List<PayTempUploadData> dataTosaveByUpload = this.payTempUploadDataDao.getAllbyEmpIdAndComponentId(empData.getId(), paySalaryComponent.getId());
     List<PayTempKalkulasi> dataToSaveByUpload = new ArrayList();
     for (PayTempUploadData payUpload : dataTosaveByUpload) {
     PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
     kalkulasi.setEmpData(empData);
     kalkulasi.setNominal(new BigDecimal(payUpload.getNominalValue()));
     kalkulasi.setPaySalaryComponent(payUpload.getPaySalaryComponent());
     kalkulasi.setCreatedBy(UserInfoUtil.getUserName());
     kalkulasi.setCreatedOn(new Date());
     kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
     dataToSaveByUpload.add(kalkulasi);
     LOGGER.info("Save By Upload");
     LOGGER.info("Nama " + empData.getBioData().getFirstName());
     }
     this.payTempKalkulasiDao.saveBatch(dataToSaveByUpload);

     }
     if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_BASIC_SALARY)) {
     PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
     kalkulasi.setEmpData(empData);
     kalkulasi.setPaySalaryComponent(paySalaryComponent);
     kalkulasi.setCreatedBy(UserInfoUtil.getUserName());
     kalkulasi.setCreatedOn(new Date());
     kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
     String basicSalaryEncripted = empData.getBasicSalaryDecrypted();
     if ((timeTmb / 30) >= 1) {
     kalkulasi.setNominal(new BigDecimal(basicSalaryEncripted));
     LOGGER.info("Save By Basic Salary Full");
     LOGGER.info("Save By Basic Nominal " + kalkulasi.getNominal().toString());
     } else {
     BigDecimal value = new BigDecimal(basicSalaryEncripted).divide(new BigDecimal(timeTmb), RoundingMode.UP);
     kalkulasi.setNominal(value);
     LOGGER.info("Save By Basic Salary Not Full");
     LOGGER.info("Save By Basic Nominal " + kalkulasi.getNominal().toString());
     }
     payTempKalkulasiDao.save(kalkulasi);
     }
     if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_LOAN)) {
     //Kalkulasi untuk table loan_payment_detail dengan syarat Query pada loan_id. emp_1d=emp_id berdasarkan data yang di dapat dari loop dan tanggal due_date berada pada rentang
     //                    tanggal from_period dan until_perion yang nilai absennya active.
     }

     if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_REIMBURSEMENT)) {
     //Kalkulasi unutk reimbusrment
     }

     if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_FORMULA)) {
     //Kalkulasi unutk Rumus dari perhitungan dari database
     String formulaOne = paySalaryComponent.getFormula();
     if (formulaOne != null) {
     basicSalary = Double.parseDouble(empData.getBasicSalaryDecrypted());
     jsEngine.put("bS", basicSalary);
     jsEngine.put("wD", 0);// Kedepannya akandi isin dari database setelah proses absensi karyawan beres
     jsEngine.put("lT", 0);//Kedepannya akandi isin dari database setelah proses absensi karyawan beres
     jsEngine.put("mT", 0);//Kedepannya akandi isin dari database setelah proses absensi karyawan beres
     jsEngine.put("oT", 0);//Kedepannya akandi isin dari database setelah proses absensi karyawan beres
     jsEngine.put("tD", 0);//Kedepannya akandi isin dari database setelah proses absensi karyawan beres
     try {
     outPut = (Double) jsEngine.eval(formulaOne);
     PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
     kalkulasi.setEmpData(empData);
     kalkulasi.setPaySalaryComponent(paySalaryComponent);
     kalkulasi.setCreatedBy(UserInfoUtil.getUserName());
     kalkulasi.setCreatedOn(new Date());
     kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
     kalkulasi.setNominal(new BigDecimal(outPut));
     payTempKalkulasiDao.save(kalkulasi);
     //            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.ok", "formula_ok", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
     } catch (ScriptException ex) {
     LOGGER.error(ex, ex);

     }

     }

     }

     System.out.println("Procecss " + i);
     i++;

     }
     }
     }*/
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PayTempKalkulasiModel> getByParam(String searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return payTempKalkulasiDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalPayTempKalkulasiByParam(String searchParameter) throws Exception {
        return payTempKalkulasiDao.getTotalPayTempKalkulasiByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public PayTempKalkulasi getEntityByPkWithDetail(Long id) throws Exception {
        return payTempKalkulasiDao.getEntityByPkWithDetail(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<PayTempKalkulasi> getAllDataCalculatedPayment(Date payrollCalculationDate, String createdBy) throws Exception {
        System.out.println("=============================================START " + new Date());

        //initial
        WtPeriode periode = wtPeriodeDao.getEntityByPayrollTypeActive();
        PaySalaryComponent totalIncomeComponent = paySalaryComponentDao.getEntityBySpecificModelComponent(HRMConstant.MODEL_COMP_TAKE_HOME_PAY);
        PaySalaryComponent taxComponent = paySalaryComponentDao.getEntityBySpecificModelComponent(HRMConstant.MODEL_COMP_TAX);
        PaySalaryComponent ceilComponent = paySalaryComponentDao.getEntityBySpecificModelComponent(HRMConstant.MODEL_COMP_CEIL);
        List<PayTempKalkulasi> datas = new ArrayList<PayTempKalkulasi>();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
        Double basicSalary = null;
        Double workingDay = null;
        Double lessTime = null;
        Double moreTime = null;
        Double overTIme = null;
        Double totalDay = null;
        Double outPut = null;

        //Start calculation
        List<EmpData> totalEmployee = empDataDao.getAllDataNotTerminateAndJoinDateLowerThan(periode.getUntilPeriode());
        /*List<EmpData> totalEmployee = new ArrayList<EmpData>();
         EmpData emp = empDataDao.getEntiyByPK((long)112);
         totalEmployee.add(emp);*/
        System.out.println(" Total Employee " + totalEmployee.size());
        for (EmpData empData : totalEmployee) {
            LOGGER.info(" ============= EMPLOYEE : " + empData.getBioData().getFirstName() + " =====================");

            /**
             * Saat ini totalIncome masih temporary, karena belum dikurangi
             * pajak dan pembulatan CSR Sedangkan untuk final totalIncome (take
             * home pay) ada di proses(step) selanjutnya di batch proses,
             * silahkan lihat batch-config.xml
             */
            BigDecimal totalIncome = new BigDecimal(0);

            List<PayComponentDataException> payComponentExceptions = payComponentDataExceptionDao.getAllByEmpId(empData.getId());
            for (PayComponentDataException dataException : payComponentExceptions) {
                PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
                kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                kalkulasi.setEmpData(empData);
                kalkulasi.setPaySalaryComponent(dataException.getPaySalaryComponent());
                kalkulasi.setFactor(this.getFactorBasedCategory(dataException.getPaySalaryComponent().getComponentCategory()));
                kalkulasi.setNominal(dataException.getNominal());

                kalkulasi.setCreatedBy(createdBy);
                kalkulasi.setCreatedOn(payrollCalculationDate);
                datas.add(kalkulasi);

                totalIncome = this.calculateTotalIncome(totalIncome, kalkulasi); //calculate totalIncome temporary
                LOGGER.info("Save By ComponentDataException - " + dataException.getPaySalaryComponent().getName() + ", nominal : " + dataException.getNominal());
            }

            int timeTmb = DateTimeUtil.getTotalDay(empData.getJoinDate(), payrollCalculationDate);
            List<Long> componentIds = Lambda.extract(payComponentExceptions, Lambda.on(PayComponentDataException.class).getPaySalaryComponent().getId());
            List<PaySalaryComponent> totalPayComponetNotExcp = paySalaryComponentDao.getAllDataByEmpTypeIdAndActiveFromTmAndIdNotIn(empData.getEmployeeType().getId(), timeTmb, componentIds);
            for (PaySalaryComponent paySalaryComponent : totalPayComponetNotExcp) {
                if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_UPLOAD)) {
                	PayTempUploadData payUpload = this.payTempUploadDataDao.getEntityByEmpIdAndComponentId(empData.getId(), paySalaryComponent.getId());
                    if(payUpload != null) {
                        PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
                        kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                        kalkulasi.setEmpData(empData);
                        kalkulasi.setPaySalaryComponent(payUpload.getPaySalaryComponent());
                        kalkulasi.setFactor(this.getFactorBasedCategory(payUpload.getPaySalaryComponent().getComponentCategory()));
                        BigDecimal nominal = new BigDecimal(payUpload.getNominalValue());
                        kalkulasi.setNominal(nominal);

                        kalkulasi.setCreatedBy(createdBy);
                        kalkulasi.setCreatedOn(payrollCalculationDate);
                        datas.add(kalkulasi);

                        totalIncome = this.calculateTotalIncome(totalIncome, kalkulasi); //calculate totalIncome temporary
                        LOGGER.info("Save By Upload - " + payUpload.getPaySalaryComponent().getName() + ", nominal : " + nominal);
                    }

                } else if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_BASIC_SALARY)) {
                    PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
                    kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                    kalkulasi.setEmpData(empData);
                    kalkulasi.setPaySalaryComponent(paySalaryComponent);
                    kalkulasi.setFactor(this.getFactorBasedCategory(paySalaryComponent.getComponentCategory()));
                    BigDecimal nominal = new BigDecimal(empData.getBasicSalaryDecrypted());
                    if ((timeTmb / 30) < 1) {
                        //jika TMB belum memenuhi satu bulan, jadi basic salary dibagi pro-rate
                        nominal = nominal.divide(new BigDecimal(timeTmb), RoundingMode.UP);
                    }
                    kalkulasi.setNominal(nominal);

                    kalkulasi.setCreatedBy(createdBy);
                    kalkulasi.setCreatedOn(payrollCalculationDate);
                    datas.add(kalkulasi);

                    totalIncome = this.calculateTotalIncome(totalIncome, kalkulasi); //calculate totalIncome temporary
                    LOGGER.info("Save By Basic Salary " + (((timeTmb / 30) < 1) ? "Not Full" : "Full") + ", nominal : " + nominal);

                } else if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_LOAN)) {
                    List<LoanPaymentDetail> loanPaymentDetails = loanPaymentDetailDao.getAllDataByEmpDataIdAndLoanSchemaIdAndPeriodTime(
                            empData.getId(), (long) paySalaryComponent.getModelReffernsil(), periode.getFromPeriode(), periode.getUntilPeriode());
                    for (LoanPaymentDetail payDetail : loanPaymentDetails) {
                        PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
                        kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                        kalkulasi.setEmpData(empData);
                        kalkulasi.setPaySalaryComponent(paySalaryComponent);
                        kalkulasi.setFactor(this.getFactorBasedCategory(paySalaryComponent.getComponentCategory()));
                        BigDecimal nominal = new BigDecimal(payDetail.getTotalPayment());
                        nominal = nominal.setScale(0, RoundingMode.UP);
                        kalkulasi.setNominal(nominal);
                        
                        //set detail loan
                        int termin = payDetail.getLoan().getTermin();
                        long cicilanKe = termin - loanPaymentDetailDao.getTotalUnPaidLoanByLoanId(payDetail.getLoan().getId(), periode.getUntilPeriode());                        
                        kalkulasi.setDetail(cicilanKe + "/" + termin);

                        kalkulasi.setCreatedBy(createdBy);
                        kalkulasi.setCreatedOn(payrollCalculationDate);
                        datas.add(kalkulasi);

                        totalIncome = this.calculateTotalIncome(totalIncome, kalkulasi); //calculate totalIncome temporary
                        LOGGER.info("Save By Loan - " + paySalaryComponent.getName() + ", nominal : " + nominal);
                    }

                } else if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_REIMBURSEMENT)) {
                    List<Reimbursment> reimbursments = reimbursmentDao.getAllDataByEmpDataIdAndReimbursmentSchemaIdAndPeriodTime(
                            empData.getId(), (long) paySalaryComponent.getModelReffernsil(), periode.getFromPeriode(), periode.getUntilPeriode());
                    for (Reimbursment reimbursment : reimbursments) {
                        PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
                        kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                        kalkulasi.setEmpData(empData);
                        kalkulasi.setPaySalaryComponent(paySalaryComponent);
                        kalkulasi.setFactor(this.getFactorBasedCategory(paySalaryComponent.getComponentCategory()));
                        kalkulasi.setNominal(reimbursment.getNominal());

                        //set detail reimbursement
                        kalkulasi.setDetail(reimbursment.getCode());
                        
                        kalkulasi.setCreatedBy(createdBy);
                        kalkulasi.setCreatedOn(payrollCalculationDate);
                        datas.add(kalkulasi);

                        totalIncome = this.calculateTotalIncome(totalIncome, kalkulasi); //calculate totalIncome temporary
                        LOGGER.info("Save By Reimbursment, nominal : " + reimbursment.getNominal());
                    }

                } else if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_FORMULA)) {
                    String formulaOne = paySalaryComponent.getFormula();
                    if (formulaOne != null) {
                        basicSalary = Double.parseDouble(empData.getBasicSalaryDecrypted());
                        jsEngine.put("bS", basicSalary);
                        jsEngine.put("wD", 0);// Kedepannya akandi isin dari database setelah proses absensi karyawan beres
                        jsEngine.put("lT", 0);//Kedepannya akandi isin dari database setelah proses absensi karyawan beres
                        jsEngine.put("mT", 0);//Kedepannya akandi isin dari database setelah proses absensi karyawan beres
                        jsEngine.put("oT", 0);//Kedepannya akandi isin dari database setelah proses absensi karyawan beres
                        jsEngine.put("tD", 0);//Kedepannya akandi isin dari database setelah proses absensi karyawan beres
                        outPut = (Double) jsEngine.eval(formulaOne);

                        PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
                        kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                        kalkulasi.setEmpData(empData);
                        kalkulasi.setPaySalaryComponent(paySalaryComponent);
                        kalkulasi.setFactor(this.getFactorBasedCategory(paySalaryComponent.getComponentCategory()));
                        BigDecimal nominal = new BigDecimal(outPut);
                        kalkulasi.setNominal(nominal);

                        kalkulasi.setCreatedBy(createdBy);
                        kalkulasi.setCreatedOn(payrollCalculationDate);
                        datas.add(kalkulasi);

                        totalIncome = this.calculateTotalIncome(totalIncome, kalkulasi); //calculate totalIncome temporary
                        LOGGER.info("Save By Formula, nominal : " + nominal);
                    }

                } else if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_BENEFIT_TABLE)) {
                    List<BenefitGroupRate> benefitGroupRates = benefitGroupRateDao.getAllDataByBenefitGroupIdAndGolJabatanId((long) paySalaryComponent.getModelReffernsil(), empData.getGolonganJabatan().getId());
                    for (BenefitGroupRate benefitGroupRate : benefitGroupRates) {
                        PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
                        kalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                        kalkulasi.setEmpData(empData);
                        kalkulasi.setPaySalaryComponent(paySalaryComponent);
                        kalkulasi.setFactor(this.getFactorBasedCategory(paySalaryComponent.getComponentCategory()));
                        //nominal untuk benefit dikali nilai dari measurement                        
                        BigDecimal nominal = new BigDecimal(benefitGroupRate.getNominal()).multiply(this.getMultiplierFromMeasurement(benefitGroupRate.getBenefitGroup().getMeasurement()));
                        kalkulasi.setNominal(nominal);

                        //set detail benefit
                        kalkulasi.setDetail(benefitGroupRate.getGolonganJabatan().getCode());
                        
                        kalkulasi.setCreatedBy(createdBy);
                        kalkulasi.setCreatedOn(payrollCalculationDate);
                        datas.add(kalkulasi);

                        totalIncome = this.calculateTotalIncome(totalIncome, kalkulasi); //calculate totalIncome temporary
                        LOGGER.info("Save By Benefit - " + paySalaryComponent.getName() + ", nominal : " + nominal);
                    }
                }
            }

            //create totalIncome Kalkulasi, hasil penjumlahan nominal dari semua component di atas
            PayTempKalkulasi totalIncomeKalkulasi = new PayTempKalkulasi();
            totalIncomeKalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
            totalIncomeKalkulasi.setEmpData(empData);
            totalIncomeKalkulasi.setPaySalaryComponent(totalIncomeComponent);
            totalIncomeKalkulasi.setFactor(this.getFactorBasedCategory(totalIncomeComponent.getComponentCategory()));
            totalIncomeKalkulasi.setNominal(totalIncome);
            totalIncomeKalkulasi.setCreatedBy(createdBy);
            totalIncomeKalkulasi.setCreatedOn(payrollCalculationDate);
            datas.add(totalIncomeKalkulasi);

            //create initial tax Kalkulasi, set nominal 0. Akan dibutuhkan di batch proses step selanjutnya
            PayTempKalkulasi taxKalkulasi = new PayTempKalkulasi();
            taxKalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
            taxKalkulasi.setEmpData(empData);
            taxKalkulasi.setPaySalaryComponent(taxComponent);
            taxKalkulasi.setFactor(this.getFactorBasedCategory(taxComponent.getComponentCategory()));
            taxKalkulasi.setNominal(new BigDecimal(0));
            taxKalkulasi.setCreatedBy(createdBy);
            taxKalkulasi.setCreatedOn(payrollCalculationDate);
            datas.add(taxKalkulasi);

            //create initial ceil Kalkulasi, set nominal 0. Akan dibutuhkan di batch proses step selanjutnya 
            PayTempKalkulasi ceilKalkulasi = new PayTempKalkulasi();
            ceilKalkulasi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
            ceilKalkulasi.setEmpData(empData);
            ceilKalkulasi.setPaySalaryComponent(ceilComponent);
            ceilKalkulasi.setFactor(this.getFactorBasedCategory(ceilComponent.getComponentCategory()));
            ceilKalkulasi.setNominal(new BigDecimal(0));
            ceilKalkulasi.setCreatedBy(createdBy);
            ceilKalkulasi.setCreatedOn(payrollCalculationDate);
            datas.add(ceilKalkulasi);
        }

        System.out.println("=============================================End " + new Date());
        return datas;
    }

    private BigDecimal getMultiplierFromMeasurement(Integer measurement) {
        BigDecimal multiplier;
        if (ObjectUtils.equals(HRMConstant.BENEFIT_GROUP_MEASUREMENT_HOUR, measurement)) {
            multiplier = new BigDecimal(HRMConstant.BENEFIT_GROUP_MEASUREMENT_HOUR_MULTIPLIER);
        } else if (ObjectUtils.equals(HRMConstant.BENEFIT_GROUP_MEASUREMENT_DAY, measurement)) {
            multiplier = new BigDecimal(HRMConstant.BENEFIT_GROUP_MEASUREMENT_DAY_MULTIPLIER);
        } else {
            multiplier = new BigDecimal(1);
        }

        return multiplier;
    }

    private Integer getFactorBasedCategory(Integer componentStrategy) {
        Integer factor = 1;

        if (ObjectUtils.equals(componentStrategy, HRMConstant.PAY_SALARY_COMPONENT_POTONGAN)) {
            factor = -1;
        } else if (ObjectUtils.equals(componentStrategy, HRMConstant.PAY_SALARY_COMPONENT_SUBSIDI)) {
            factor = 0;
        } else if (ObjectUtils.equals(componentStrategy, HRMConstant.PAY_SALARY_COMPONENT_TUNJANGAN)) {
            factor = 1;
        }

        return factor;
    }

    private BigDecimal calculateTotalIncome(BigDecimal totalIncome, PayTempKalkulasi payTempKalkulasi) {

        return totalIncome.add((payTempKalkulasi.getNominal().multiply(new BigDecimal(payTempKalkulasi.getFactor()))));
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteAllData() throws Exception {
        payTempKalkulasiDao.deleteAllData();

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalKaryawan() throws Exception {
        return payTempKalkulasiDao.getTotalKaryawan();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PayTempKalkulasi> getByParamForDetail(String searchParameter, int firstResult, int maxResults, Order order, Long paySalaryComponentId) throws Exception {
        return payTempKalkulasiDao.getByParamForDetail(searchParameter, firstResult, maxResults, order, paySalaryComponentId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalPayTempKalkulasiByParamForDetail(String searchParameter, Long paySalaryComponentId) throws Exception {
        return payTempKalkulasiDao.getTotalPayTempKalkulasiByParamForDetail(searchParameter, paySalaryComponentId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PayTempKalkulasi> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId) throws Exception {
        return payTempKalkulasiDao.getAllDataByEmpDataIdAndTaxNotNull(empDataId);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executeBatchFinalSalaryCalculation(EmpData empData) throws ScriptException {
        PayTempKalkulasiEmpPajak tax_23 = payTempKalkulasiEmpPajakDao.getEntityByEmpDataIdAndTaxComponentId(empData.getId(), 23L);
        PayTempKalkulasi taxKalkulasi = payTempKalkulasiDao.getEntityByEmpDataIdAndSpecificModelComponent(empData.getId(), HRMConstant.MODEL_COMP_TAX);
        taxKalkulasi.setNominal(new BigDecimal(tax_23.getNominal()));
        payTempKalkulasiDao.update(taxKalkulasi);

        PayTempKalkulasi ceilKalkulasi = payTempKalkulasiDao.getEntityByEmpDataIdAndSpecificModelComponent(empData.getId(), HRMConstant.MODEL_COMP_CEIL);
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
        double ceiling = (Double) jsEngine.eval(ceilKalkulasi.getPaySalaryComponent().getFormula());

        PayTempKalkulasi totalIncomeKalkulasi = payTempKalkulasiDao.getEntityByEmpDataIdAndSpecificModelComponent(empData.getId(), HRMConstant.MODEL_COMP_TAKE_HOME_PAY);
        BigDecimal totalIncome = totalIncomeKalkulasi.getNominal();
        totalIncome = this.calculateTotalIncome(totalIncome, taxKalkulasi);

        //dapatkan nilai sisa/pembulatan
        BigDecimal val[] = totalIncome.divideAndRemainder(new BigDecimal(ceiling));

        ceilKalkulasi.setNominal(val[1]);
        payTempKalkulasiDao.update(ceilKalkulasi);

        totalIncome = this.calculateTotalIncome(totalIncome, ceilKalkulasi);
        totalIncomeKalkulasi.setNominal(totalIncome);
        payTempKalkulasiDao.update(totalIncomeKalkulasi);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public PayTempKalkulasi getEntityByEmpIdAndModelTakeHomePayId(Long empId) throws Exception {
        return payTempKalkulasiDao.getEntityByEmpIdAndModelTakeHomePayId(empId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<PayTempKalkulasi> getAllDataByEmpDataId(Long empDataId) throws Exception {
        return payTempKalkulasiDao.getAllDataByEmpDataId(empDataId);
    }

    private Integer sisaData;
    private Integer parameterLoop;
    private Integer previousMaxResult;
    private Integer indexDataTerakhir;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<SalaryJournalModel> getByParamForSalaryJournal(String searchParameter, int firstResult, int maxResults, Order order, String locale) throws Exception {
        System.out.println(sisaData + " sisaData atas" + parameterLoop);
        Integer jumlahRowsTambahan = Integer.valueOf(String.valueOf(payTempKalkulasiDao.getTotalPayTempKalkulasiForSalaryJournalDebetAndKredit(searchParameter)));
        //reset sisaData biar bisa bulak balik page akhir + 1 halaman page akhir
        Integer hitLastPage = 0;
        if (parameterLoop == null) {
            parameterLoop = 0;
        }
        Integer currentResult = maxResults;
        Long totalData = payTempKalkulasiDao.getTotalPayTempKalkulasiForSalaryJournal(searchParameter);
        Integer totalPage = Integer.valueOf(String.valueOf(totalData)) / Integer.valueOf(String.valueOf(maxResults));
        //get list debet and credit
        List<SalaryJournalModel> listDebet = payTempKalkulasiDao.getByParamForSalaryJournalDebet(searchParameter);
        List<SalaryJournalModel> listKredit = payTempKalkulasiDao.getByParamForSalaryJournalKredit(searchParameter);
        List<SalaryJournalModel> listSalaryJournal = new ArrayList<>();

        if (totalData % maxResults != 0) {
            totalPage = totalPage + 1;
        }
        //jika halaman terakhir, total recordnya cukup untuk data tambahan
        Long spaceKosong = (totalPage * maxResults) - totalData;
        Boolean isSpaceKosongCukup = (spaceKosong < jumlahRowsTambahan) ? Boolean.FALSE : Boolean.TRUE;
        Boolean isLastPage = (firstResult == (totalPage * maxResults) - maxResults) ? Boolean.TRUE : Boolean.FALSE;

        listSalaryJournal = payTempKalkulasiDao.getByParamForSalaryJournal(searchParameter, firstResult, maxResults, order);
        SalaryJournalModel salaryJournalModel;
        if (sisaData == null) {
            sisaData = 0;
        }
        if (indexDataTerakhir == null) {
            indexDataTerakhir = jumlahRowsTambahan;
        }
        Integer sisaDataTerakhir = 0;
        if (isLastPage) {
            sisaData = 0;
            parameterLoop = Integer.valueOf(String.valueOf(maxResults)) - listSalaryJournal.size();
            sisaDataTerakhir = jumlahRowsTambahan - parameterLoop;
            hitLastPage = hitLastPage + 1;
        }
        Double totalUang = 0.0;
        int j = 0;
        if (hitLastPage == 0 && spaceKosong != 0) {
            indexDataTerakhir = indexDataTerakhir - Integer.valueOf(String.valueOf(spaceKosong));
            sisaData = jumlahRowsTambahan - Integer.valueOf(String.valueOf(spaceKosong));
            parameterLoop = jumlahRowsTambahan;
            int temp = sisaData;
            sisaData = parameterLoop - temp;
            System.out.println("space Kosong :" + spaceKosong);
            System.out.println("sisa Data : " + sisaData);
        } else if (isSpaceKosongCukup || totalData % maxResults == 0) {
            parameterLoop = jumlahRowsTambahan;
            sisaData = 0;
        }
        for (j = sisaData; j < parameterLoop; j++) {
            indexDataTerakhir = indexDataTerakhir - 1;
            totalUang = listDebet.get(j).getJumlahDebet().doubleValue() - listKredit.get(j).getJumlahKredit().doubleValue();
            salaryJournalModel = new SalaryJournalModel();
            salaryJournalModel.setCostCenterCode(listKredit.get(j).getCostCenterCodeKredit());
            salaryJournalModel.setCostCenterName(listKredit.get(j).getCostCenterNameKredit());
            salaryJournalModel.setJurnalCode("000000");
            ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(locale));
            salaryJournalModel.setJurnalName(messages.getString("salaryJournal.paySalaryEmployee"));
            //debet > kredit => hasil ditaro di kredit
            //debet < kredit => hasil ditaro di debet
            if (listDebet.get(j).getJumlahDebet().doubleValue() > listKredit.get(j).getJumlahKredit().doubleValue()) {
                salaryJournalModel.setDebet2(new BigDecimal(0));
                salaryJournalModel.setKredit(totalUang);
            } else {
            	totalUang = -totalUang;
                salaryJournalModel.setDebet2(new BigDecimal(totalUang));
                salaryJournalModel.setKredit(0.0);
            }
            listSalaryJournal.add(salaryJournalModel);
            if (j == 0) {
                sisaData = 0;
            } else {
                sisaData = sisaData + 1;
            }
        }
        System.out.println(indexDataTerakhir + "index data terakhir");
        if (isLastPage) {
            sisaData = sisaData + 1;
            parameterLoop = parameterLoop + sisaDataTerakhir;
        }
        System.out.println(sisaData + " sisaData bawah" + parameterLoop);
        //reset sisaData dan hitLastPage jika terjadi pergantian maxResult
        if (previousMaxResult != currentResult) {
            sisaData = null;
            hitLastPage = 0;
        }
        previousMaxResult = currentResult;
        System.out.println("sisa Data bawah : " + sisaData);
        return listSalaryJournal;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalPayTempKalkulasiForSalaryJournal(String searchParameter) throws Exception {
        Long totalData = payTempKalkulasiDao.getTotalPayTempKalkulasiForSalaryJournal(searchParameter) + payTempKalkulasiDao.getTotalPayTempKalkulasiForSalaryJournalDebetAndKredit(searchParameter);
        return totalData;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public PayTempKalkulasi getEntityByEmpDataIdAndSpecificModelComponent(Long empDataId, Integer specific) {
        return payTempKalkulasiDao.getEntityByEmpDataIdAndSpecificModelComponent(empDataId, specific);

    }

}
