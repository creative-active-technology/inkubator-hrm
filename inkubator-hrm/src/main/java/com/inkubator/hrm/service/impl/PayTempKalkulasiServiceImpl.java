/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.PayComponentDataExceptionDao;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.dao.PayTempKalkulasiDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import java.util.Date;
import java.util.List;
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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void calcualtePayRoll() throws Exception {
        List<EmpData> totalEmployee = empDataDao.getAllDataNotTerminate();
        List<PaySalaryComponent> totalPayComponet = paySalaryComponentDao.getAllData();

        System.out.println(" Jumlah Component " + totalPayComponet.size());
        System.out.println(" Jumlah Employye " + totalEmployee.size());
        int i = 1;
        for (EmpData empData : totalEmployee) {
            System.out.println("Procecss " + i);
            i++;
            for (PaySalaryComponent psc : totalPayComponet) {
                PayTempKalkulasi kalkulasi = new PayTempKalkulasi();
                kalkulasi.setEmpData(empData);
                kalkulasi.setPaySalaryComponent(psc);
                PayComponentDataException pcde = this.payComponentDataExceptionDao.getByEmpIdAndComponentId(empData.getId(), psc.getId());
                if (pcde != null) {
                    kalkulasi.setNominal(pcde.getNominal());
                    LOGGER.info("Step Sebab Eksepion ");
                    LOGGER.info("Employe Name " + empData.getBioData().getFirstName());
                    LOGGER.info("Exception Type " + pcde.getPaySalaryComponent().getName());
                } else {
                    LOGGER.info("Step Sebab kecuali Eksepsion ");
                    Long typeKaryawan = empData.getEmployeeType().getId();
                    Date karyawanTmb = empData.getJoinDate();
                    Long payComponentId = psc.getId();
                    LOGGER.info("Employe Name " + empData.getBioData().getFirstName());
                    LOGGER.info("Employe Type " + empData.getEmployeeType().getName());
                    LOGGER.info("PayComponent name " + psc.getName());
                    LOGGER.info("===============================");
               
                    PaySalaryComponent pcToInput = paySalaryComponentDao.getByEployeeTypeIdComponentIdAndJoinDate(typeKaryawan, payComponentId, karyawanTmb);
                    if (pcToInput != null) {
                             LOGGER.info("=============================== Ada Dalam Type");
                        LOGGER.info("Employe Name " + empData.getBioData().getNickname());
                        LOGGER.info("Employe Type " + empData.getEmployeeType().getName());
                        LOGGER.info("Component Name " + pcToInput.getName());
                    }

                }

            }
        }
    }

}
