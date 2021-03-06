/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.AppraisalProgramDao;
import com.inkubator.hrm.dao.AppraisalProgramEmpAssesorDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.AppraisalProgramEmpAssesor;
import com.inkubator.hrm.service.AppraisalProgramEmpAssesorService;
import com.inkubator.securitycore.util.UserInfoUtil;
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
@Service(value = "appraisalProgramEmpAssesorService")
@Lazy
public class AppraisalProgramEmpAssesorServiceImpl extends IServiceImpl implements AppraisalProgramEmpAssesorService {

    @Autowired
    private AppraisalProgramEmpAssesorDao appraisalProgramEmpAssesorDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private AppraisalProgramDao appraisalProgramDao;

    @Override
    public AppraisalProgramEmpAssesor getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntiyByPK(Long l) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void save(AppraisalProgramEmpAssesor t) throws Exception {
        t.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        t.setEmpDataByEmpId(empDataDao.getEntiyByPK(t.getEmpDataByEmpId().getId()));
        t.setAppraisalProgram(appraisalProgramDao.getEntiyByPK(t.getAppraisalProgram().getId()));
        t.setCreatedBy(UserInfoUtil.getUserName());
        t.setCreatedOn(new Date());
        appraisalProgramEmpAssesorDao.save(t);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(AppraisalProgramEmpAssesor t) throws Exception {
        AppraisalProgramEmpAssesor apea = appraisalProgramEmpAssesorDao.getEntiyByPK(t.getId());
        apea.setEmpDataByEmpId(empDataDao.getEntiyByPK(t.getEmpDataByEmpId().getId()));
        apea.setAppraisalProgram(appraisalProgramDao.getEntiyByPK(t.getAppraisalProgram().getId()));
        apea.setEmpDataByAssesorEmpId(t.getEmpDataByAssesorEmpId());
        apea.setScala(t.getScala());
        apea.setUpdatedBy(UserInfoUtil.getUserName());
        apea.setUpdatedOn(new Date());
        appraisalProgramEmpAssesorDao.update(apea);
    }

    @Override
    public void saveOrUpdate(AppraisalProgramEmpAssesor t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor saveData(AppraisalProgramEmpAssesor t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor updateData(AppraisalProgramEmpAssesor t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor saveOrUpdateData(AppraisalProgramEmpAssesor t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppraisalProgramEmpAssesor getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(AppraisalProgramEmpAssesor t) throws Exception {
        appraisalProgramEmpAssesorDao.delete(t);
    }

    @Override
    public void softDelete(AppraisalProgramEmpAssesor t) throws Exception {
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
    public List<AppraisalProgramEmpAssesor> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppraisalProgramEmpAssesor> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppraisalProgramEmpAssesor> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppraisalProgramEmpAssesor> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppraisalProgramEmpAssesor> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppraisalProgramEmpAssesor> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppraisalProgramEmpAssesor> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppraisalProgramEmpAssesor> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalAssesorByAppraisalIAndEmpId(Long appraisalId, Long empId) throws Exception {
        return this.appraisalProgramEmpAssesorDao.getTotalAssesorByAppraisalIAndEmpId(appraisalId, empId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<AppraisalProgramEmpAssesor> getAllBy(Long appraisalId, Long empId) throws Exception {
        return appraisalProgramEmpAssesorDao.getAllBy(appraisalId, empId);
    }

}
