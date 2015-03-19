/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.CurrencyDao;
import com.inkubator.hrm.dao.LoanNewTypeDao;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.web.search.LoanNewTypeSearchParameter;
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
@Service(value = "loanNewTypeService")
@Lazy
public class LoanNewTypeServiceImpl extends IServiceImpl implements LoanNewTypeService {

    @Autowired
    private LoanNewTypeDao loanNewTypeDao;

    @Autowired
    private CurrencyDao currencyDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanNewType> getByParam(LoanNewTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        return loanNewTypeDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalLoanTypeByParam(LoanNewTypeSearchParameter parameter) {
        return loanNewTypeDao.getTotalLoanTypeByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LoanNewType getEntityWithRelationByPk(Long id) {
        return loanNewTypeDao.getEntityByPkWithDetail(id);
    }

    @Override
    public LoanNewType getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LoanNewType getEntiyByPK(Long id) throws Exception {
        return loanNewTypeDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(LoanNewType entity) throws Exception {
        long totalLoanCodeDuplicates = loanNewTypeDao.getTotalByLoanNewTypeCode(entity.getLoanTypeCode());
        if (totalLoanCodeDuplicates > 0) {
            throw new BussinessException("loantype.error_duplicate_loan_type_code");
        }
        long totalLoanNameDuplicates = loanNewTypeDao.getTotalByLoanNewTypeName(entity.getLoanTypeName());
        if (totalLoanNameDuplicates > 0) {
            throw new BussinessException("loantype.error_duplicate_loan_type_name");
        }

        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        loanNewTypeDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(LoanNewType entity) throws Exception {
        long totalLoanCodeDuplicates = loanNewTypeDao.getTotalByLoanNewTypeCodeAndNotId(entity.getLoanTypeCode(), entity.getId());
        if (totalLoanCodeDuplicates > 0) {
            throw new BussinessException("loantype.error_duplicate_loan_type_code");
        }
        long totalLoanNameDuplicates = loanNewTypeDao.getTotalByLoanNewTypeNameAndNotId(entity.getLoanTypeName(), entity.getId());
        if (totalLoanNameDuplicates > 0) {
            throw new BussinessException("loantype.error_duplicate_loan_type_name");
        }
        LoanNewType update = loanNewTypeDao.getEntiyByPK(entity.getId());
        update.setLoanTypeCode(entity.getLoanTypeCode());
        update.setLoanTypeName(entity.getLoanTypeName());
        update.setRoundingStatus(entity.getRoundingStatus());
        update.setInterest(entity.getInterest());
        update.setInterestMethod(entity.getInterestMethod());
        if(null != entity.getCurrency()){
            Currency currency = currencyDao.getEntiyByPK(entity.getCurrency().getId());
            update.setCurrency(currency);
        }
        update.setDescription(entity.getDescription());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        loanNewTypeDao.update(update);
    }

    @Override
    public void saveOrUpdate(LoanNewType enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType saveData(LoanNewType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType updateData(LoanNewType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType saveOrUpdateData(LoanNewType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewType getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(LoanNewType entity) throws Exception {
        loanNewTypeDao.delete(entity);
    }

    @Override
    public void softDelete(LoanNewType entity) throws Exception {
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
    public List<LoanNewType> getAllData() throws Exception {
        return loanNewTypeDao.getAllData();
    }

    @Override
    public List<LoanNewType> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewType> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewType> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewType> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
