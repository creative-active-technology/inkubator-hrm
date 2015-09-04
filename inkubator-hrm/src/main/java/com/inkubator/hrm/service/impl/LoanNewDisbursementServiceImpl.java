/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanNewApplicationDao;
import com.inkubator.hrm.dao.LoanNewDisbursementDao;
import com.inkubator.hrm.dao.LoanNewDisbursementListDao;
import com.inkubator.hrm.dao.TransactionCodeficationDao;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewDisbursement;
import com.inkubator.hrm.entity.LoanNewDisbursementList;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.service.LoanNewDisbursementService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "loanNewDisbursementService")
@Lazy
public class LoanNewDisbursementServiceImpl extends IServiceImpl implements LoanNewDisbursementService {
    
    @Autowired
    private LoanNewDisbursementDao loanNewDisbursementDao;
    
    @Autowired
    private LoanNewDisbursementListDao loanNewDisbursementListDao;
    
    @Autowired
    private TransactionCodeficationDao transactionCodeficationDao;
    
    @Autowired
    private LoanNewApplicationDao loanNewApplicationDao;
    

    @Override
    public LoanNewDisbursement getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntiyByPK(Long l) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(LoanNewDisbursement t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LoanNewDisbursement t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(LoanNewDisbursement t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement saveData(LoanNewDisbursement t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement updateData(LoanNewDisbursement t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement saveOrUpdateData(LoanNewDisbursement t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewDisbursement getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(LoanNewDisbursement t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(LoanNewDisbursement t) throws Exception {
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
    public List<LoanNewDisbursement> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewDisbursement> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewDisbursement> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewDisbursement> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewDisbursement> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewDisbursement> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewDisbursement> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoanNewDisbursement> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void disburseLoanApplication(LoanNewDisbursement loanNewDisbursement, List<Integer> listLoanNewApplicationid) throws Exception {
        
        if(listLoanNewApplicationid.isEmpty()){
            throw new BussinessException("loan_disbursement.error_no_application_selected");
        }
        
        Long id = Long.parseLong(RandomNumberUtil.getRandomNumber(9));
        loanNewDisbursement.setId(id);   
        loanNewDisbursement.setDibursementCode(this.generateLoanDisbursementNumber());
        loanNewDisbursement.setCreatedBy(UserInfoUtil.getUserName());
        loanNewDisbursement.setCreatedOn(new Date());
        
        //Save Disbursement header
       loanNewDisbursementDao.save(loanNewDisbursement);
        
       LoanNewDisbursement lnd = loanNewDisbursementDao.getEntiyByPK(id);
      
        // iterate each Loan
        for (Integer loanNewApplicationId : listLoanNewApplicationid){
            
           
            //Update Loan Status to HRMConstant.LOAN_DISBURSED
            LoanNewApplication loanToUpdate = loanNewApplicationDao.getEntiyByPK(loanNewApplicationId);
            loanToUpdate.setLoanStatus(HRMConstant.LOAN_DISBURSED);
            loanNewApplicationDao.update(loanToUpdate);
            
             LoanNewDisbursementList loanNewDisbursementList = new LoanNewDisbursementList();
            loanNewDisbursementList.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            loanNewDisbursementList.setLoanNewApplication(loanToUpdate);
            loanNewDisbursementList.setLoanNewDisbursement(lnd);
            loanNewDisbursementList.setCreatedBy(UserInfoUtil.getUserName());
            loanNewDisbursementList.setCreatedOn(new Date());
            //Save each detail disbursement
            loanNewDisbursementListDao.save(loanNewDisbursementList);
        }
    }
    
    private String generateLoanDisbursementNumber(){
		/** generate number form codification, from loan module */
		TransactionCodefication transactionCodefication = transactionCodeficationDao.getEntityByModulCode(HRMConstant.LOAN_DISBURSEMENT_KODE);
        Long currentMaxId = loanNewDisbursementDao.getCurrentMaxId();
        currentMaxId = currentMaxId != null ? currentMaxId : 0;
        String nomor  = KodefikasiUtil.getKodefikasi(((int)currentMaxId.longValue()), transactionCodefication.getCode());
        return nomor;
	}

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getCurrentMaxId() throws Exception {
        return loanNewDisbursementDao.getCurrentMaxId();
    }
    
}
