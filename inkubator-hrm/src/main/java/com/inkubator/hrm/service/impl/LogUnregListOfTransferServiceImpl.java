package com.inkubator.hrm.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.LogUnregListOfTransferDao;
import com.inkubator.hrm.dao.PayReceiverBankAccountDao;
import com.inkubator.hrm.entity.LogUnregListOfTransfer;
import com.inkubator.hrm.entity.PayReceiverBankAccount;
import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.service.LogUnregListOfTransferService;

/**
 *
 * @author rizkykojek
 */
@Service(value = "logUnregListOfTransferService")
@Lazy
public class LogUnregListOfTransferServiceImpl extends IServiceImpl implements LogUnregListOfTransferService {

	@Autowired
	private LogUnregListOfTransferDao logUnregListOfTransferDao;
	@Autowired
	private PayReceiverBankAccountDao payReceiverBankAccountDao;
	
	@Override
	public LogUnregListOfTransfer getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntiyByPK(Long id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void save(LogUnregListOfTransfer entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(LogUnregListOfTransfer entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(LogUnregListOfTransfer enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer saveData(LogUnregListOfTransfer entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer updateData(LogUnregListOfTransfer entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer saveOrUpdateData(LogUnregListOfTransfer entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntityByPkIsActive(String id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntityByPkIsActive(String id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntityByPkIsActive(Integer id,
			Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntityByPkIsActive(Long id,
			Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public LogUnregListOfTransfer getEntityByPkIsActive(Long id,
			Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void delete(LogUnregListOfTransfer entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void softDelete(LogUnregListOfTransfer entity) throws Exception {
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
	public List<LogUnregListOfTransfer> getAllData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogUnregListOfTransfer> getAllData(Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogUnregListOfTransfer> getAllData(Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogUnregListOfTransfer> getAllData(Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogUnregListOfTransfer> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogUnregListOfTransfer> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogUnregListOfTransfer> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<LogUnregListOfTransfer> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByUnregSalaryId(Long unregSalaryId) throws Exception {
		logUnregListOfTransferDao.deleteByUnregSalaryId(unregSalaryId);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeBatchUnregPayroll(TempUnregPayroll tempUnregPayroll, String createdBy, Date createdOn) throws Exception {
		List<PayReceiverBankAccount> listPayReceiverBankAccount = payReceiverBankAccountDao.getAllByEmpId(tempUnregPayroll.getEmpData().getId());
		if(listPayReceiverBankAccount.isEmpty()){
			throw new BussinessException("global.error_user_does_not_have_account_bank", tempUnregPayroll.getEmpData().getNikWithFullName());
		} else if(Lambda.sum(listPayReceiverBankAccount, Lambda.on(PayReceiverBankAccount.class).getPersen()) !=100){
			throw new BussinessException("global.error_transfer_percent_below_100", tempUnregPayroll.getEmpData().getNikWithFullName());
		}		
		
		for(PayReceiverBankAccount payReceiverBankAccount : listPayReceiverBankAccount){
			LogUnregListOfTransfer logUnregListOfTransfer = new LogUnregListOfTransfer();
			logUnregListOfTransfer.setUnregSalaryId(tempUnregPayroll.getUnregSalary().getId());
			logUnregListOfTransfer.setUnregSalaryName(tempUnregPayroll.getUnregSalary().getName());
			logUnregListOfTransfer.setUnregSalaryPaymentDate(tempUnregPayroll.getUnregSalary().getSalaryDate());
			logUnregListOfTransfer.setUnregSalaryStartPeriod(tempUnregPayroll.getUnregSalary().getStartPeriodDate());
			logUnregListOfTransfer.setUnregSalaryEndPeriod(tempUnregPayroll.getUnregSalary().getEndPeriodDate());
			logUnregListOfTransfer.setPeriodeId(tempUnregPayroll.getUnregSalary().getWtPeriode().getId());
			logUnregListOfTransfer.setPeriodeStart(tempUnregPayroll.getUnregSalary().getWtPeriode().getFromPeriode());
			logUnregListOfTransfer.setPeriodeEnd(tempUnregPayroll.getUnregSalary().getWtPeriode().getUntilPeriode());			
			logUnregListOfTransfer.setEmpDataId(tempUnregPayroll.getEmpData().getId());
			logUnregListOfTransfer.setEmpNik(tempUnregPayroll.getEmpData().getNik());
			logUnregListOfTransfer.setEmpName(tempUnregPayroll.getEmpData().getBioData().getFullName());
			logUnregListOfTransfer.setEmpJabatanId(tempUnregPayroll.getEmpData().getJabatanByJabatanId().getId());
			logUnregListOfTransfer.setEmpJabatanCode(tempUnregPayroll.getEmpData().getJabatanByJabatanId().getCode());
			logUnregListOfTransfer.setEmpJabatanName(tempUnregPayroll.getEmpData().getJabatanByJabatanId().getName());
			logUnregListOfTransfer.setEmpGolJabatan(tempUnregPayroll.getEmpData().getGolonganJabatan().getCode());
			logUnregListOfTransfer.setDepartmentId(tempUnregPayroll.getEmpData().getJabatanByJabatanId().getDepartment().getId());
			logUnregListOfTransfer.setDepartmentName(tempUnregPayroll.getEmpData().getJabatanByJabatanId().getDepartment().getDepartmentName());
			logUnregListOfTransfer.setBankId(payReceiverBankAccount.getBioBankAccount().getBank().getId());
			logUnregListOfTransfer.setBankName(payReceiverBankAccount.getBioBankAccount().getBank().getBankName());
			logUnregListOfTransfer.setAccountName(payReceiverBankAccount.getBioBankAccount().getOwnerName());
			logUnregListOfTransfer.setAccountNumber(payReceiverBankAccount.getBioBankAccount().getAccountNumber());
			logUnregListOfTransfer.setTransferPercent(payReceiverBankAccount.getPersen());
			BigDecimal transferNominal = tempUnregPayroll.getNominal();
			transferNominal = transferNominal.multiply(new BigDecimal(payReceiverBankAccount.getPersen())).divide(new BigDecimal(100));
			logUnregListOfTransfer.setTransferNominal(transferNominal);
			logUnregListOfTransfer.setCreatedBy(createdBy);
			logUnregListOfTransfer.setCreatedOn(createdOn);
			logUnregListOfTransferDao.save(logUnregListOfTransfer);
		}
		
	}

}
