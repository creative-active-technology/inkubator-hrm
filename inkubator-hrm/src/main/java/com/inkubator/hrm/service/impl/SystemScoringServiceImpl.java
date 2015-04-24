/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.SystemScoringDao;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.service.SystemScoringService;
import com.inkubator.hrm.web.search.SystemScoringSearchParameter;
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
 * @author Deni
 */
@Service(value = "systemScoringService")
@Lazy
public class SystemScoringServiceImpl extends IServiceImpl implements SystemScoringService {

    @Autowired
    private SystemScoringDao systemScoringDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<SystemScoring> getByParam(SystemScoringSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return systemScoringDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(SystemScoringSearchParameter searchParameter) throws Exception {
        return systemScoringDao.getTotalByParam(searchParameter);
    }

    @Override
    public SystemScoring getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public SystemScoring getEntiyByPK(Long id) throws Exception {
        return systemScoringDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(SystemScoring entity) throws Exception {
        // check duplicate code
        long totalDuplicateCodes = systemScoringDao.getTotalByCode(entity.getCode());
        if (totalDuplicateCodes > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }

        // check duplicate name
        long totalDuplicateNames = systemScoringDao.getTotalByName(entity.getName());
        if (totalDuplicateNames > 0) {
            throw new BussinessException("global.error_duplicate_name");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.systemScoringDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(SystemScoring entity) throws Exception {
        // check duplicate code
        long totalDuplicateCodes = systemScoringDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicateCodes > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }

        // check duplicate name
        long totalDuplicateNames = systemScoringDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
        if (totalDuplicateNames > 0) {
            throw new BussinessException("global.error_duplicate_name");
        }
        SystemScoring update = systemScoringDao.getEntiyByPK(entity.getId());
        update.setCode(entity.getCode());
        update.setName(entity.getName());
        update.setDescription(entity.getDescription());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.systemScoringDao.update(update);
    }

    @Override
    public void saveOrUpdate(SystemScoring enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring saveData(SystemScoring entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring updateData(SystemScoring entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring saveOrUpdateData(SystemScoring entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoring getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(SystemScoring entity) throws Exception {
        this.systemScoringDao.delete(entity);
    }

    @Override
    public void softDelete(SystemScoring entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<SystemScoring> getAllData() throws Exception {
        return this.systemScoringDao.getAllData();
    }

    @Override
    public List<SystemScoring> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoring> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoring> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoring> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoring> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoring> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoring> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
