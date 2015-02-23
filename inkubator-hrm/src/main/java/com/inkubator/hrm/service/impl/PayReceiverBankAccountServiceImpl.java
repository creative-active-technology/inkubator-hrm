/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.PayReceiverBankAccountDao;
import com.inkubator.hrm.dao.PayTempKalkulasiDao;
import com.inkubator.hrm.entity.PayReceiverBankAccount;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.PayReceiverBankAccountService;
import com.inkubator.hrm.web.model.PayReceiverAccountModel;
import com.inkubator.hrm.web.model.PayReceiverBankAccountModel;
import com.inkubator.hrm.web.search.PayReceiverBankAccountSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author denifahri
 */
@Service(value = "payReceiverBankAccountService")
@Lazy
public class PayReceiverBankAccountServiceImpl extends IServiceImpl implements PayReceiverBankAccountService {

    @Autowired
    private PayReceiverBankAccountDao payReceiverBankAccountDao;
    @Autowired
    private PayTempKalkulasiDao payTempKalkulasiDao;

    @Override
    public PayReceiverBankAccount getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(PayReceiverBankAccount entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PayReceiverBankAccount entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(PayReceiverBankAccount enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount saveData(PayReceiverBankAccount entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount updateData(PayReceiverBankAccount entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount saveOrUpdateData(PayReceiverBankAccount entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PayReceiverBankAccount getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PayReceiverBankAccount entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(PayReceiverBankAccount entity) throws Exception {
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
    public List<PayReceiverBankAccount> getAllData() throws Exception {
        return payReceiverBankAccountDao.getAllData();
    }

    @Override
    public List<PayReceiverBankAccount> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayReceiverBankAccount> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayReceiverBankAccount> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayReceiverBankAccount> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayReceiverBankAccount> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayReceiverBankAccount> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PayReceiverBankAccount> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PayReceiverBankAccountModel> getByParam(PayReceiverBankAccountSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return payReceiverBankAccountDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(PayReceiverBankAccountSearchParameter searchParameter) throws Exception {
        return this.payReceiverBankAccountDao.getTotalByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void saveList(List<PayReceiverBankAccount> list) throws Exception {
        for (PayReceiverBankAccount account : list) {
            account.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            account.setCreatedBy(UserInfoUtil.getUserName());
            account.setCreatedOn(new Date());
            payReceiverBankAccountDao.save(account);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ, timeout = 50)
    public List<PayReceiverBankAccount> getAllByEmpId(long id) throws Exception {
        return this.payReceiverBankAccountDao.getAllByEmpId(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void updateList(List<PayReceiverBankAccount> list) throws Exception {
        for (PayReceiverBankAccount account : list) {
            account.setUpdatedBy(UserInfoUtil.getUserName());
            account.setUpdatedOn(new Date());
            payReceiverBankAccountDao.update(account);
        }
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<PayReceiverBankAccount> getAllDataWithDetail() throws Exception {
		return this.payReceiverBankAccountDao.getAllDataWithDetail();
		
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ, timeout = 50)
	public List<PayReceiverAccountModel> getAllDataByEmpDataId(Long empDataId) throws Exception {
		List<PayReceiverBankAccount> listAccount = payReceiverBankAccountDao.getAllByEmpId(empDataId);
		PayTempKalkulasi takeHomePay = payTempKalkulasiDao.getEntityByEmpDataIdAndSpecificModelComponent(empDataId, HRMConstant.MODEL_COMP_TAKE_HOME_PAY);
		List<PayReceiverAccountModel> listModel = new ArrayList<>();
		for(PayReceiverBankAccount account : listAccount){
			PayReceiverAccountModel model = new PayReceiverAccountModel();
			model.setBankName(account.getBioBankAccount().getBank().getBankName());
			model.setAccountNumber(account.getBioBankAccount().getAccountNumber());
			model.setAccountName(account.getBioBankAccount().getOwnerName());
			model.setPercent(account.getPersen());
			BigDecimal transferNominal = takeHomePay.getNominal();
			transferNominal = transferNominal.multiply(new BigDecimal(account.getPersen())).divide(new BigDecimal(100));
			model.setNominal(transferNominal.doubleValue());
			listModel.add(model);
		}
		return listModel;
	}

}
