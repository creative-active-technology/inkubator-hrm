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
import com.inkubator.hrm.dao.SystemScoringIndexDao;
import com.inkubator.hrm.entity.SystemScoringIndex;
import com.inkubator.hrm.service.SystemScoringIndexService;
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
@Service(value = "systemScoringIndexService")
@Lazy
public class SystemScoringIndexServiceImpl extends IServiceImpl implements SystemScoringIndexService {

    @Autowired
    private SystemScoringIndexDao systemScoringIndexDao;
    @Autowired
    private SystemScoringDao systemScoringDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<SystemScoringIndex> getAllByParam(Long systemScoringId, int firstResult, int maxResults, Order order) throws Exception {
        return systemScoringIndexDao.getAllByParam(systemScoringId, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalByParam(Long systemScoringId) throws Exception {
        return systemScoringIndexDao.getTotalByParam(systemScoringId);
    }

    @Override
    public SystemScoringIndex getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public SystemScoringIndex getEntiyByPK(Long id) throws Exception {
        return systemScoringIndexDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(SystemScoringIndex entity) throws Exception {
        /*// check duplicate code
        long totalDuplicateLabelMask = systemScoringIndexDao.getTotalBylabelMask(entity.getLabelMask());
        if (totalDuplicateLabelMask > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }
        
        // check duplicate name
        long totalDuplicateValue = systemScoringIndexDao.getTotalByValue(entity.getValue());
        if (totalDuplicateValue > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }*/
        Integer lastOrderScala = systemScoringIndexDao.getLastOrderScala(entity.getSystemScoring().getId());
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        if(lastOrderScala != null){
            entity.setOrderScala(lastOrderScala + 1);
        }else{
            entity.setOrderScala(1);
        }
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        entity.setSystemScoring(systemScoringDao.getEntiyByPK(entity.getSystemScoring().getId()));
        this.systemScoringIndexDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(SystemScoringIndex entity) throws Exception {
        /*// check duplicate code
        long totalDuplicateLabelMask = systemScoringIndexDao.getTotalBylabelMaskAndNotId(entity.getLabelMask(), entity.getId());
        if (totalDuplicateLabelMask > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }
        
        // check duplicate name
        long totalDuplicateValue = systemScoringIndexDao.getTotalByValueAndNotId(entity.getValue(), entity.getId());
        if (totalDuplicateValue > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }*/
        SystemScoringIndex update = systemScoringIndexDao.getEntiyByPK(entity.getId());
        update.setLabelMask(entity.getLabelMask());
        update.setDescription(entity.getDescription());
        update.setValue(entity.getValue());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        update.setSystemScoring(systemScoringDao.getEntiyByPK(entity.getSystemScoring().getId()));
        this.systemScoringIndexDao.update(update);
    }

    @Override
    public void saveOrUpdate(SystemScoringIndex enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex saveData(SystemScoringIndex entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex updateData(SystemScoringIndex entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex saveOrUpdateData(SystemScoringIndex entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemScoringIndex getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(SystemScoringIndex entity) throws Exception {
    	List<SystemScoringIndex> listUpdateOrdering = systemScoringIndexDao.getAllDataBySystemScoringIdAndGreaterOrderScala(entity.getSystemScoring().getId(), entity.getOrderScala());

    	//delete
    	this.systemScoringIndexDao.delete(entity);
    	
    	//update scala up to 1
    	for(SystemScoringIndex systemScoringIndex : listUpdateOrdering){
    		systemScoringIndex.setOrderScala(systemScoringIndex.getOrderScala() - 1);
    		systemScoringIndex.setUpdatedBy(UserInfoUtil.getUserName());
    		systemScoringIndex.setUpdatedOn(new Date());
            this.systemScoringIndexDao.update(systemScoringIndex);
    	}
    }

    @Override
    public void softDelete(SystemScoringIndex entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalData() throws Exception {
        return this.systemScoringIndexDao.getTotalData();
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
    public List<SystemScoringIndex> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoringIndex> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoringIndex> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoringIndex> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoringIndex> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoringIndex> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoringIndex> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemScoringIndex> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doChangerOrderScala(Long systemScoringId, int newGradeLevel, long oldId) throws Exception {
        SystemScoringIndex targetChange = this.systemScoringIndexDao.getEntityBySystemScoringIdAndOrderScala(systemScoringId, newGradeLevel);
        targetChange.setOrderScala(0);
        targetChange.setUpdatedBy(UserInfoUtil.getUserName());
        targetChange.setUpdatedOn(new Date());
        this.systemScoringIndexDao.update(targetChange);

        SystemScoringIndex newChange = this.systemScoringIndexDao.getEntiyByPK(oldId);
        int gradeNumberOld = newChange.getOrderScala();
        newChange.setOrderScala(newGradeLevel);
        newChange.setUpdatedBy(UserInfoUtil.getUserName());
        newChange.setUpdatedOn(new Date());
        this.systemScoringIndexDao.update(newChange);

        SystemScoringIndex targetChangeLast = this.systemScoringIndexDao.getEntityBySystemScoringIdAndOrderScala(systemScoringId, 0);
        targetChangeLast.setOrderScala(gradeNumberOld);
        targetChangeLast.setUpdatedBy(UserInfoUtil.getUserName());
        targetChangeLast.setUpdatedOn(new Date());
        this.systemScoringIndexDao.update(targetChangeLast);
    }
    
}
