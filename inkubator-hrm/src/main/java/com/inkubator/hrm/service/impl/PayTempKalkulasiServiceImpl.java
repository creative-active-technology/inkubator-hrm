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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

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
import com.inkubator.hrm.dao.PayTempUploadDataDao;
import com.inkubator.hrm.dao.ReimbursmentDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.BenefitGroupRate;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;

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
    public List<PayTempKalkulasiModel> getByParam(String searchParameter, int firstResult, int maxResults, Order order) {
        return payTempKalkulasiDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalPayTempKalkulasiByParam(String searchParameter) {
        return payTempKalkulasiDao.getTotalPayTempKalkulasiByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public PayTempKalkulasi getEntityByPkWithDetail(Long id) {
        return payTempKalkulasiDao.getEntityByPkWithDetail(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<PayTempKalkulasi> getAllDataCalculatedPayment(Date payrollCalculationDate, String createdBy) throws Exception {
    	System.out.println("=============================================START " + new Date());

        //initial
        WtPeriode periode = wtPeriodeDao.getEntityByStatusActive();
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
        List<EmpData> totalEmployee = empDataDao.getAllDataNotTerminateAndJoinDateLowerThan(payrollCalculationDate);
       /* List<EmpData> totalEmployee = new ArrayList<EmpData>();
        EmpData emp = empDataDao.getEntiyByPK((long)112);
        totalEmployee.add(emp);*/
        System.out.println(" Total Employee " + totalEmployee.size());
        for (EmpData empData : totalEmployee) {
            LOGGER.info(" ============= EMPLOYEE : " + empData.getBioData().getFirstName() + " =====================");

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

                LOGGER.info("Save By ComponentDataException - " + dataException.getPaySalaryComponent().getName() + ", nominal : " + dataException.getNominal());
            }

            int timeTmb = DateTimeUtil.getTotalDay(empData.getJoinDate(), payrollCalculationDate);
            List<Long> componentIds = Lambda.extract(payComponentExceptions, Lambda.on(PayComponentDataException.class).getPaySalaryComponent().getId());
            List<PaySalaryComponent> totalPayComponetNotExcp = paySalaryComponentDao.getAllDataByEmpTypeIdAndActiveFromTmAndIdNotIn(empData.getEmployeeType().getId(), timeTmb, componentIds);
            for (PaySalaryComponent paySalaryComponent : totalPayComponetNotExcp) {
                if (paySalaryComponent.getModelComponent().getSpesific().equals(HRMConstant.MODEL_COMP_UPLOAD)) {
                    List<PayTempUploadData> payTempUploadDatas = this.payTempUploadDataDao.getAllbyEmpIdAndComponentId(empData.getId(), paySalaryComponent.getId());
                    for (PayTempUploadData payUpload : payTempUploadDatas) {
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

                        kalkulasi.setCreatedBy(createdBy);
                        kalkulasi.setCreatedOn(payrollCalculationDate);
                        datas.add(kalkulasi);

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

                        kalkulasi.setCreatedBy(createdBy);
                        kalkulasi.setCreatedOn(payrollCalculationDate);
                        datas.add(kalkulasi);

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

                        kalkulasi.setCreatedBy(createdBy);
                        kalkulasi.setCreatedOn(payrollCalculationDate);
                        datas.add(kalkulasi);

                        LOGGER.info("Save By Benefit - " + paySalaryComponent.getName() + ", nominal : " + nominal);
                    }
                }
            }
        }

        System.out.println("=============================================End " + new Date());
        return datas;
    }
    
    private BigDecimal getMultiplierFromMeasurement(Integer measurement){
    	BigDecimal multiplier;
    	if(ObjectUtils.equals(HRMConstant.BENEFIT_GROUP_MEASUREMENT_HOUR, measurement)){
    		multiplier = new BigDecimal(HRMConstant.BENEFIT_GROUP_MEASUREMENT_HOUR_MULTIPLIER);
    	} else if (ObjectUtils.equals(HRMConstant.BENEFIT_GROUP_MEASUREMENT_DAY, measurement)){
    		multiplier = new BigDecimal(HRMConstant.BENEFIT_GROUP_MEASUREMENT_DAY_MULTIPLIER);
    	} else {
    		multiplier = new BigDecimal(1);
    	}
    	
    	return multiplier;
    }
    
    private Integer getFactorBasedCategory(Integer componentStrategy){
    	Integer factor = 1;
    	
    	if(ObjectUtils.equals(componentStrategy, HRMConstant.PAY_SALARY_COMPONENT_POTONGAN)){
    		factor = -1;
    	} else if(ObjectUtils.equals(componentStrategy, HRMConstant.PAY_SALARY_COMPONENT_SUBSIDI)){
    		factor = 0;
    	} else if(ObjectUtils.equals(componentStrategy, HRMConstant.PAY_SALARY_COMPONENT_TUNJANGAN)){
    		factor = 1;
    	}
    	
    	return factor;
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
	public List<PayTempKalkulasi> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId) {
		return payTempKalkulasiDao.getAllDataByEmpDataIdAndTaxNotNull(empDataId);
		
	}

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public PayTempKalkulasi getEntityByEmpIdAndModelTakeHomePayId(Long empId) throws Exception {
        return payTempKalkulasiDao.getEntityByEmpIdAndModelTakeHomePayId(empId);
    }
    
}
