/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.dao.ApprovalDefinitionLeaveDao;
import com.inkubator.hrm.dao.ApprovalDefinitionOTDao;
import com.inkubator.hrm.dao.WtOverTimeDao;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import com.inkubator.hrm.entity.ApprovalDefinitionOT;
import com.inkubator.hrm.entity.ApprovalDefinitionOTId;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.web.search.WtOverTimeSearchParameter;
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
 * @author Deni Husni FR
 */
@Service(value = "wtOverTimeService")
@Lazy
public class WtOverTimeServiceImpl extends BaseApprovalConfigurationServiceImpl<WtOverTime> implements WtOverTimeService {
    
    @Autowired
    private WtOverTimeDao wtOverTimeDao;
    @Autowired
    private ApprovalDefinitionDao approvalDefinitionDao;
    @Autowired
    private ApprovalDefinitionOTDao approvalDefinitionOTDao;
    
    @Override
    public WtOverTime getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public WtOverTime getEntiyByPK(Long id) throws Exception {
        return this.wtOverTimeDao.getEntiyByPK(id);
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(WtOverTime entity) throws Exception {
        long totalDuplicates = wtOverTimeDao.getTotalDuplicateByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("over_time.error_code_duplicate");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.wtOverTimeDao.save(entity);
    }
    
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(WtOverTime entity) throws Exception {
        long totalDuplicates = wtOverTimeDao.getTotalDuplicaByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("over_time.error_code_duplicate");
        }
        
        WtOverTime overTime=this.wtOverTimeDao.getEntiyByPK(entity.getId());
        overTime.setCode(entity.getCode());
        overTime.setDescription(entity.getDescription());
        overTime.setFinishTimeFactor(entity.getFinishTimeFactor());
        overTime.setMaximumTime(entity.getMaximumTime());
        overTime.setMinimumTime(entity.getMinimumTime());
        overTime.setName(entity.getName());
        overTime.setOtRounding(entity.getOtRounding());
        overTime.setOverTimeCalculation(entity.getOverTimeCalculation());
        overTime.setStartTimeFactor(entity.getStartTimeFactor());
        overTime.setValuePrice(entity.getValuePrice());
        overTime.setUpdatedBy(UserInfoUtil.getUserName());
        overTime.setUpdatedOn(new Date());
        wtOverTimeDao.update(overTime);
        
    }
    
    @Override
    public void saveOrUpdate(WtOverTime enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime saveData(WtOverTime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime updateData(WtOverTime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime saveOrUpdateData(WtOverTime entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public WtOverTime getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(WtOverTime entity) throws Exception {
        this.wtOverTimeDao.delete(entity);
    }
    
    @Override
    public void softDelete(WtOverTime entity) throws Exception {
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
    public List<WtOverTime> getAllData() throws Exception {
     return wtOverTimeDao.getAllData();
    }
    
    @Override
    public List<WtOverTime> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<WtOverTime> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<WtOverTime> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<WtOverTime> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<WtOverTime> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<WtOverTime> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<WtOverTime> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<WtOverTime> getByParam(WtOverTimeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.wtOverTimeDao.getByParam(searchParameter, firstResult, maxResults, order);
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalWtOverTimeByParam(WtOverTimeSearchParameter searchParameter) throws Exception {
        return this.wtOverTimeDao.getTotalWtOverTimeByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(WtOverTime entity, List<ApprovalDefinition> appDefs) throws Exception {
        long totalDuplicates = wtOverTimeDao.getTotalDuplicateByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("over_time.error_code_duplicate");
        }
        /** validasi approval definition conf */
        super.validateApprovalConf(appDefs);
        
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        wtOverTimeDao.save(entity);
        /** saving approval definition conf manyToMany */
        super.saveApprovalConf(appDefs, entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(WtOverTime entity, List<ApprovalDefinition> appDefs) throws Exception {
        long totalDuplicates = wtOverTimeDao.getTotalDuplicaByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("over_time.error_code_duplicate");
        }
        
        WtOverTime overTime=this.wtOverTimeDao.getEntiyByPK(entity.getId());
        overTime.setCode(entity.getCode());
        overTime.setDescription(entity.getDescription());
        overTime.setFinishTimeFactor(entity.getFinishTimeFactor());
        overTime.setMaximumTime(entity.getMaximumTime());
        overTime.setMinimumTime(entity.getMinimumTime());
        overTime.setName(entity.getName());
        overTime.setOtRounding(entity.getOtRounding());
        overTime.setOverTimeCalculation(entity.getOverTimeCalculation());
        overTime.setStartTimeFactor(entity.getStartTimeFactor());
        overTime.setValuePrice(entity.getValuePrice());
        overTime.setUpdatedBy(UserInfoUtil.getUserName());
        overTime.setUpdatedOn(new Date());
        wtOverTimeDao.update(overTime);
        
        /** updating approval definition conf manyToMany */
        super.updateApprovalConf(appDefs, overTime.getApprovalDefinitionOTs().iterator(), overTime);
    }
    
    @Override
    protected void saveManyToMany(ApprovalDefinition appDef, WtOverTime entity) {
        ApprovalDefinitionOT approvalDefinitionOT =  new ApprovalDefinitionOT();
        approvalDefinitionOT.setId(new ApprovalDefinitionOTId(appDef.getId(), entity.getId()));
        approvalDefinitionOT.setApprovalDefinition(appDef);
        approvalDefinitionOT.setWtOverTime(entity);
        approvalDefinitionOTDao.save(approvalDefinitionOT);
    }

    @Override
    protected void deleteManyToMany(Object entity) {
        approvalDefinitionOTDao.delete((ApprovalDefinitionOT) entity);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public WtOverTime getEntityByPkFetchApprovalDefinition(Long id) throws Exception {
        return wtOverTimeDao.getEntityByPkFetchApprovalDefinition(id);
    }

    
    
}
