/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.OhsaCategoryDao;
import com.inkubator.hrm.dao.OhsaIncidentDao;
import com.inkubator.hrm.entity.OhsaCategory;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.service.OhsaIncidentService;
import com.inkubator.hrm.web.search.OhsaIncidentSearchParameter;
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
@Service(value = "ohsaIncidentService")
@Lazy
public class OhsaIncidentServiceImpl extends IServiceImpl implements OhsaIncidentService {
    
    @Autowired
    private OhsaIncidentDao ohsaIncidentDao;
     @Autowired
    private OhsaCategoryDao ohsaCategoryDao;
    
    @Override
    public OhsaIncident getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public OhsaIncident getEntiyByPK(Long l) throws Exception {
        return ohsaIncidentDao.getEntiyByPK(l);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(OhsaIncident t) throws Exception {
        t.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(5)));
        t.setCreatedBy(UserInfoUtil.getUserName());
        t.setCreatedOn(new Date());
        this.ohsaIncidentDao.save(t);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(OhsaIncident t) throws Exception {
        OhsaIncident ohsaIncident = ohsaIncidentDao.getEntiyByPK(t.getId());        
        OhsaCategory category = ohsaCategoryDao.getEntiyByPK(t.getOhsaCategory().getId());
        ohsaIncident.setOhsaCategory(category);
        
        ohsaIncident.setIncidentTime(t.getIncidentTime());
        ohsaIncident.setSubject(t.getSubject());
        ohsaIncident.setDescription(t.getDescription());
        ohsaIncident.setLocation(t.getLocation());
        ohsaIncident.setSeverityLevel(t.getSeverityLevel());
        ohsaIncident.setUpdatedBy(UserInfoUtil.getUserName());
        ohsaIncident.setUpdatedOn(new Date());
        
        this.ohsaIncidentDao.update(ohsaIncident);
    }

    @Override
    public void saveOrUpdate(OhsaIncident t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident saveData(OhsaIncident t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident updateData(OhsaIncident t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident saveOrUpdateData(OhsaIncident t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OhsaIncident getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(OhsaIncident t) throws Exception {
        ohsaIncidentDao.delete(t);
    }

    @Override
    public void softDelete(OhsaIncident t) throws Exception {
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
    public List<OhsaIncident> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaIncident> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaIncident> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaIncident> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaIncident> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaIncident> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaIncident> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OhsaIncident> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<OhsaIncident> getByParam(OhsaIncidentSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        return this.ohsaIncidentDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(OhsaIncidentSearchParameter parameter) {
        return this.ohsaIncidentDao.getTotalByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public OhsaIncident getEntityByPKWithDetail(Long id) {
        return this.ohsaIncidentDao.getEntityByPKWithDetail(id);
    }
    
}
