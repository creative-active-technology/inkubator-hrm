/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.PayComponentDataExceptionDao;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.dao.PayTempKalkulasiDao;
import com.inkubator.hrm.dao.PayTempUploadDataDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PaySalaryEmpType;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
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
            List<PaySalaryComponent> totalPayComponetNotExcp = paySalaryComponentDao.getAllNotInExceptAndEmpTyeAndTmb(empData.getEmployeeType().getId(), timeTmb);
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
    }
}
