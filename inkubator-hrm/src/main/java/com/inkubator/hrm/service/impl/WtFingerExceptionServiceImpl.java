/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.WtFingerExceptionDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.WtFingerException;
import com.inkubator.hrm.service.WtFingerExceptionService;
import com.inkubator.hrm.web.search.WtFingerExceptionSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni
 */
@Service(value = "wtFingerExceptionService")
@Lazy
public class WtFingerExceptionServiceImpl extends IServiceImpl implements WtFingerExceptionService{
    
    @Autowired
    private WtFingerExceptionDao wtFingerExceptionDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<WtFingerException> getByParamWithDetail(WtFingerExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return wtFingerExceptionDao.getByParamWithDetail(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalWtFingerExceptionByParam(WtFingerExceptionSearchParameter searchParameter) throws Exception {
        return wtFingerExceptionDao.getTotalWtFingerExceptionByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public WtFingerException getEntityByParamWithDetail(Long id) throws Exception {
        return wtFingerExceptionDao.getEntityByParamWithDetail(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<WtFingerException> getAllDataByEmpIdWithDetail() throws Exception {
        return wtFingerExceptionDao.getAllDataByEmpIdWithDetail();
    }

    @Override
    public WtFingerException getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(WtFingerException entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(WtFingerException entity) throws Exception {
        WtFingerException update = wtFingerExceptionDao.getEntityByParamWithDetail(entity.getId());
        update.setStartDate(entity.getStartDate());
        update.setEndDate(entity.getEndDate());
        update.setExtendException(entity.getExtendException());
        update.setUpdatedBy(entity.getUpdatedBy());
        update.setUpdatedOn(new Date());
        this.wtFingerExceptionDao.update(update);
    }

    @Override
    public void saveOrUpdate(WtFingerException enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException saveData(WtFingerException entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException updateData(WtFingerException entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException saveOrUpdateData(WtFingerException entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtFingerException getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = DataIntegrityViolationException.class, noRollbackFor = IOException.class)
    public void delete(WtFingerException entity) throws Exception {
        this.wtFingerExceptionDao.delete(entity);
    }

    @Override
    public void softDelete(WtFingerException entity) throws Exception {
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
    public List<WtFingerException> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtFingerException> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtFingerException> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtFingerException> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtFingerException> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtFingerException> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtFingerException> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtFingerException> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveMassFingerException(List<EmpData> data, Date startDate, Date endDate, Boolean extendException) throws Exception {
        List<WtFingerException> listFingerException = new ArrayList<>();
        WtFingerException wtFingerException;
        for(EmpData empData : data){
            wtFingerException = new WtFingerException();
            wtFingerException.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            wtFingerException.setStartDate(startDate);
            wtFingerException.setEndDate(endDate);
            wtFingerException.setEmpData(empData);
            wtFingerException.setExtendException(extendException);
            wtFingerException.setCreatedBy(UserInfoUtil.getUserName());
            wtFingerException.setCreatedOn(new Date());
            listFingerException.add(wtFingerException);
        }
        wtFingerExceptionDao.saveBatch(listFingerException);
    }
    
}
