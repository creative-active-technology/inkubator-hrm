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
import com.inkubator.hrm.dao.BankDao;
import com.inkubator.hrm.dao.CompanyBankAccountDao;
import com.inkubator.hrm.dao.CompanyDao;
import com.inkubator.hrm.dao.SavingTypeDao;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.CompanyBankAccount;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.CompanyBankAccountService;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "companyBankAccountService")
@Lazy
public class CompanyBankAccountServiceImpl extends IServiceImpl implements CompanyBankAccountService {

	@Autowired
	private CompanyBankAccountDao companyBankAccountDao;
	@Autowired
	private BankDao bankDao;
	@Autowired
	private SavingTypeDao savingTypeDao;
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public CompanyBankAccount getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public CompanyBankAccount getEntiyByPK(Long id) throws Exception {
		return companyBankAccountDao.getEntiyByPK(id);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(CompanyBankAccount entity) throws Exception {
		// check duplicate account number
		long totalDuplicates = companyBankAccountDao.getTotalByAccountNumber(entity.getAccountNumber());
		if (totalDuplicates > 0) {
			throw new BussinessException("organization.error_duplicate_account_number");
		}
		
		SavingType savingType = savingTypeDao.getEntiyByPK(entity.getSavingType().getId());
		Bank bank = bankDao.getEntiyByPK(entity.getBank().getId());
		Company company = companyDao.getEntiyByPK(entity.getCompany().getId());
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setBank(bank);
		entity.setSavingType(savingType);
		entity.setCompany(company);
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		this.companyBankAccountDao.save(entity);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(CompanyBankAccount entity) throws Exception {
		// check duplicate account number
		long totalDuplicates = companyBankAccountDao.getTotalByAccountNumberAndNotId(entity.getAccountNumber(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("organization.error_duplicate_account_number");
		}
		
		SavingType savingType = savingTypeDao.getEntiyByPK(entity.getSavingType().getId());
		Bank bank = bankDao.getEntiyByPK(entity.getBank().getId());
		Company company = companyDao.getEntiyByPK(entity.getCompany().getId());
				
		CompanyBankAccount companyBankAccount = companyBankAccountDao.getEntiyByPK(entity.getId());
		companyBankAccount.setSavingType(savingType);
		companyBankAccount.setBank(bank);
		companyBankAccount.setCompany(company);
		companyBankAccount.setAccountNumber(entity.getAccountNumber());
		companyBankAccount.setAccountName(entity.getAccountName());
		companyBankAccount.setIsDefault(entity.getIsDefault());
		companyBankAccount.setUpdatedBy(UserInfoUtil.getUserName());
		companyBankAccount.setUpdatedOn(new Date());
		this.companyBankAccountDao.update(companyBankAccount);
	}

	@Override
	public void saveOrUpdate(CompanyBankAccount enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount saveData(CompanyBankAccount entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount updateData(CompanyBankAccount entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount saveOrUpdateData(CompanyBankAccount entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public CompanyBankAccount getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(CompanyBankAccount entity) throws Exception {
		companyBankAccountDao.delete(entity);
		
	}

	@Override
	public void softDelete(CompanyBankAccount entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public List<CompanyBankAccount> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public List<CompanyBankAccount> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public List<CompanyBankAccount> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public List<CompanyBankAccount> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public List<CompanyBankAccount> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public List<CompanyBankAccount> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public List<CompanyBankAccount> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	public List<CompanyBankAccount> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<CompanyBankAccount> getAllDataByCompanyId(Long companyId) throws Exception {
		return companyBankAccountDao.getAllDataByCompanyId(companyId);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public CompanyBankAccount getEntityByPKWithDetail(Long id) {
		return companyBankAccountDao.getEntityByPKWithDetail(id);
		
	}

}
