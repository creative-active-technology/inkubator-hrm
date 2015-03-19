package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.EmpDataDao;

import com.inkubator.hrm.dao.OhsaEmpInvolveDao;
import com.inkubator.hrm.dao.OhsaIncidentDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.OhsaEmpInvolve;
import com.inkubator.hrm.entity.OhsaIncident;

import com.inkubator.hrm.service.OhsaEmpInvolveService;
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
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "ohsaEmpInvolveService")
@Lazy
public class OhsaEmpInvolveServiceImpl extends IServiceImpl implements OhsaEmpInvolveService {

    @Autowired
    private OhsaEmpInvolveDao ohsaEmpInvolveDao;   
    
    @Autowired
    private OhsaIncidentDao ohsaIncidentDao;
    
    @Autowired
    private EmpDataDao empDataDao;

    @Override
    public OhsaEmpInvolve getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntiyByPK(Long l) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(OhsaEmpInvolve t) throws Exception {        
        t.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(5)));
        t.setCreatedBy(UserInfoUtil.getUserName());
        t.setCreatedOn(new Date());
       
        this.ohsaEmpInvolveDao.save(t);
        
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(OhsaEmpInvolve t) throws Exception {
        OhsaEmpInvolve ohsaEmpInvolve = ohsaEmpInvolveDao.getEntiyByPK(t.getId());
        
        OhsaIncident ohsaIncident = ohsaIncidentDao.getEntiyByPK(t.getOhsaIncident().getId());
        ohsaEmpInvolve.setOhsaIncident(ohsaIncident);
        
        EmpData empData = empDataDao.getEntiyByPK(t.getEmpData().getId());
        ohsaEmpInvolve.setEmpData(empData);     
        
        
        this.ohsaEmpInvolveDao.update(ohsaEmpInvolve);
    }

    @Override
    public void saveOrUpdate(OhsaEmpInvolve t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve saveData(OhsaEmpInvolve t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve updateData(OhsaEmpInvolve t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve saveOrUpdateData(OhsaEmpInvolve t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaEmpInvolve getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(OhsaEmpInvolve t) throws Exception {
        ohsaEmpInvolveDao.delete(t);
    }

    @Override
    public void softDelete(OhsaEmpInvolve t) throws Exception {
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
    public List<OhsaEmpInvolve> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaEmpInvolve> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaEmpInvolve> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaEmpInvolve> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaEmpInvolve> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaEmpInvolve> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaEmpInvolve> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaEmpInvolve> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalEmpInvolveByIdOhsaIncident(Long idOhsaIncident) {
        return this.ohsaEmpInvolveDao.getTotalEmpInvolveByIdOhsaIncident(idOhsaIncident);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<OhsaEmpInvolve> getListByOhsaIncidentId(Long ohsaIncidentId) {
        return this.ohsaEmpInvolveDao.getListByOhsaIncidentId(ohsaIncidentId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public OhsaEmpInvolve getEntityByPKWithDetail(Long id) {
        return this.ohsaEmpInvolveDao.getEntityByPKWithDetail(id);
    }

    

}
