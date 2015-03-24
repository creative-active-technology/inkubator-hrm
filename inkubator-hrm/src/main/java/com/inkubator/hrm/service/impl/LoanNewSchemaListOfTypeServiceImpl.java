/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.LoanNewSchemaDao;
import com.inkubator.hrm.dao.LoanNewSchemaListOfTypeDao;
import com.inkubator.hrm.dao.LoanNewTypeDao;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewSchemaListOfTypeId;
import com.inkubator.hrm.service.LoanNewSchemaListOfTypeService;
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
@Service(value = "loanNewSchemaListOfTypeService")
@Lazy
public class LoanNewSchemaListOfTypeServiceImpl extends IServiceImpl implements LoanNewSchemaListOfTypeService {

    @Autowired
    private LoanNewSchemaListOfTypeDao loanNewSchemaListOfTypeDao;
    @Autowired
    private LoanNewSchemaDao loanNewSchemaDao;
    @Autowired
    private LoanNewTypeDao loanNewTypeDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewSchemaListOfType> getEntityByLoanNewSchema(Long loanNewSchema) throws Exception {
        return loanNewSchemaListOfTypeDao.getEntityByLoanNewSchema(loanNewSchema);
    }

    @Override
    public LoanNewSchemaListOfType getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
    public LoanNewSchemaListOfType getEntiyByPK(Long id) throws Exception {
        return loanNewSchemaListOfTypeDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(LoanNewSchemaListOfType entity) throws Exception {
        long totalDuplicates = loanNewSchemaListOfTypeDao.getTotalByLoanTypeAndSchema(entity.getLoanNewType().getId(), entity.getLoanNewSchema().getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("loanNewSchema.error_duplicate_type");
        }
        entity.setLoanNewSchema(loanNewSchemaDao.getEntiyByPK(entity.getLoanNewSchema().getId()));
        entity.setLoanNewType(loanNewTypeDao.getEntiyByPK(entity.getLoanNewType().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.loanNewSchemaListOfTypeDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(LoanNewSchemaListOfType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(LoanNewSchemaListOfType enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType saveData(LoanNewSchemaListOfType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType updateData(LoanNewSchemaListOfType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType saveOrUpdateData(LoanNewSchemaListOfType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfType getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(LoanNewSchemaListOfType entity) throws Exception {
        LoanNewSchemaListOfType loanNewSchemaListOfType = loanNewSchemaListOfTypeDao.getEntityByLoanNewSchemaListOfTypeId(new LoanNewSchemaListOfTypeId(entity.getLoanNewType().getId(), entity.getLoanNewSchema().getId()));
        this.loanNewSchemaListOfTypeDao.delete(loanNewSchemaListOfType);
    }

    @Override
    public void softDelete(LoanNewSchemaListOfType entity) throws Exception {
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
    public List<LoanNewSchemaListOfType> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfType> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfType> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfType> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfType> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewSchemaListOfType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeIdWithDetail(LoanNewSchemaListOfTypeId loanNewSchemaListOfTypeId) throws Exception {
        return loanNewSchemaListOfTypeDao.getEntityByLoanNewSchemaListOfTypeIdWithDetail(loanNewSchemaListOfTypeId);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(LoanNewSchemaListOfType entity, Long oldId) throws Exception {
        long totalDuplicates = loanNewSchemaListOfTypeDao.getTotalByNotLoanTypeAndSchema(entity.getLoanNewType().getId(), entity.getLoanNewSchema().getId(), new LoanNewSchemaListOfTypeId(entity.getLoanNewType().getId(), oldId));
        if (totalDuplicates > 0) {
            throw new BussinessException("loanNewSchema.error_duplicate_type");
        }
        LoanNewSchemaListOfType loanNewSchemaListOfType = loanNewSchemaListOfTypeDao.getEntityByLoanNewSchemaListOfTypeId(new LoanNewSchemaListOfTypeId(oldId, entity.getLoanNewSchema().getId()));
        this.loanNewSchemaListOfTypeDao.delete(loanNewSchemaListOfType);
        entity.setLoanNewSchema(loanNewSchemaDao.getEntiyByPK(entity.getLoanNewSchema().getId()));
        entity.setLoanNewType(loanNewTypeDao.getEntiyByPK(entity.getLoanNewType().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.loanNewSchemaListOfTypeDao.save(entity);
    }
    
}
