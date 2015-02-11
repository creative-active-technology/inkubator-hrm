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
import com.inkubator.hrm.dao.LoanTypeDao;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.service.LoanTypeService;
import com.inkubator.hrm.web.search.LoanTypeSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
@Service(value = "loanTypeService")
@Lazy
public class LoanTypeServiceImpl extends IServiceImpl implements LoanTypeService{
    
    @Autowired
    private LoanTypeDao loanTypeDao;
    
    @Autowired
    private CurrencyDao currencyDao;
            
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<LoanType> getByParam(LoanTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        return loanTypeDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalLoanTypeByParam(LoanTypeSearchParameter parameter) {
        return loanTypeDao.getTotalLoanTypeByParam(parameter);
    }

    @Override
    public LoanType getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LoanType getEntiyByPK(Long l) throws Exception {
        return loanTypeDao.getEntiyByPK(l);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(LoanType t) throws Exception {
        long totalLoanCodeDuplicates = loanTypeDao.getTotalByLoanTypeCode(t.getLoanTypeCode());
        if (totalLoanCodeDuplicates > 0) {
            throw new BussinessException("loantype.error_duplicate_loan_type_code");
        }
        long totalLoanNameDuplicates = loanTypeDao.getTotalByLoanTypeName(t.getLoanTypeName());
        if(totalLoanNameDuplicates > 0){
            throw new BussinessException("loantype.error_duplicate_loan_type_name");
        }      
        
       t.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
       t.setCreatedBy(UserInfoUtil.getUserName());
       t.setCreatedOn(new Date());
       loanTypeDao.save(t);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(LoanType t) throws Exception {
        
        /*LoanType prevLoanType = loanTypeDao.getEntiyByPK(t.getId());
        String prevLoanTypeCode = prevLoanType.getLoanTypeCode();
        String prevLoanTypeName = prevLoanType.getLoanTypeName();
        
        if(!StringUtils.equals(prevLoanTypeCode, t.getLoanTypeCode())){
            long totalLoanCodeDuplicates = loanTypeDao.getTotalByLoanTypeCode(t.getLoanTypeCode());
            if (totalLoanCodeDuplicates > 0) {
                throw new BussinessException("loantype.error_duplicate_loan_type_code");
            }
        }
        
        if(!StringUtils.equals(prevLoanTypeName, t.getLoanTypeName())){
            long totalLoanNameDuplicates = loanTypeDao.getTotalByLoanTypeName(t.getLoanTypeName());
            if(totalLoanNameDuplicates > 0){
                throw new BussinessException("loantype.error_duplicate_loan_type_name");
            }
        }*/
                
        LoanType loanTypeUpdate = loanTypeDao.getEntiyByPK(t.getId());
        
        loanTypeUpdate.setLoanTypeCode(t.getLoanTypeCode());
        loanTypeUpdate.setLoanTypeName(t.getLoanTypeName());
        loanTypeUpdate.setRoundingStatus(t.getRoundingStatus());
        loanTypeUpdate.setInterest(t.getInterest());
        loanTypeUpdate.setInterestMethod(t.getInterestMethod());
        if(null != t.getCurrency()){
            Currency currency = currencyDao.getEntiyByPK(t.getCurrency().getId());
            loanTypeUpdate.setCurrency(currency);
        }
        
        loanTypeUpdate.setUpdatedBy(UserInfoUtil.getUserName());
        loanTypeUpdate.setUpdatedOn(new Date());
        loanTypeDao.saveAndMerge(loanTypeUpdate);
    }

    @Override
    public void saveOrUpdate(LoanType t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType saveData(LoanType t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType updateData(LoanType t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType saveOrUpdateData(LoanType t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanType getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(LoanType t) throws Exception {
        loanTypeDao.delete(t);
    }

    @Override
    public void softDelete(LoanType t) throws Exception {
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
    public List<LoanType> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanType> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanType> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanType> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanType> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanType> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanType> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanType> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public LoanType getEntityWithRelationByPk(Long id) {
        return loanTypeDao.getEntityWithRelationByPk(id);
    }
    
}
