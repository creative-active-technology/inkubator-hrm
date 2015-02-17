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
import com.inkubator.hrm.dao.LogListOfTransferDao;
import com.inkubator.hrm.dao.PayReceiverBankAccountDao;
import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.entity.PayReceiverBankAccount;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogListOfTransferService;
import com.inkubator.hrm.web.model.BankTransferDistributionReportModel;

/**
*
* @author rizkykojek
*/
@Service(value = "logListOfTransferService")
@Lazy
public class LogListOfTransferServiceImpl extends IServiceImpl implements LogListOfTransferService {

	@Autowired
	private LogListOfTransferDao logListOfTransferDao;
	@Autowired
	private PayReceiverBankAccountDao payReceiverBankAccountDao;
	
	@Override
	public LogListOfTransfer getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(LogListOfTransfer entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LogListOfTransfer entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(LogListOfTransfer enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public LogListOfTransfer saveData(LogListOfTransfer entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer updateData(LogListOfTransfer entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer saveOrUpdateData(LogListOfTransfer entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogListOfTransfer getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(LogListOfTransfer entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(LogListOfTransfer entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogListOfTransfer> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogListOfTransfer> getAllData(Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogListOfTransfer> getAllData(Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogListOfTransfer> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogListOfTransfer> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogListOfTransfer> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogListOfTransfer> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogListOfTransfer> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByPeriodId(Long periodId) throws Exception {
		logListOfTransferDao.deleteByPeriodId(periodId);		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void executeBatchMonthEndPayroll(PayTempKalkulasi payTempKalkulasi, WtPeriode periode, String createdBy, Date createdOn) throws Exception {
		List<PayReceiverBankAccount> listPayReceiverBankAccount = payReceiverBankAccountDao.getAllByEmpId(payTempKalkulasi.getEmpData().getId());
		if(listPayReceiverBankAccount.isEmpty()){
			throw new Exception("User "+ payTempKalkulasi.getEmpData().getNikWithFullName() +" doesn't have BankAccount to transferred.");
		} else if(Lambda.sum(listPayReceiverBankAccount, Lambda.on(PayReceiverBankAccount.class).getPersen()) !=100){
			throw new Exception("Transfer Percent value of User "+ payTempKalkulasi.getEmpData().getNikWithFullName() +" is below than 100%.");
		}		
		
		for(PayReceiverBankAccount payReceiverBankAccount : listPayReceiverBankAccount){
			LogListOfTransfer logListOfTransfer = new LogListOfTransfer();
			logListOfTransfer.setPeriodeId(periode.getId());
			logListOfTransfer.setPayrollDate(periode.getPayrollDate());
			logListOfTransfer.setEmpDataId(payTempKalkulasi.getEmpData().getId());
			logListOfTransfer.setEmpNik(payTempKalkulasi.getEmpData().getNik());
			logListOfTransfer.setEmpName(payTempKalkulasi.getEmpData().getBioData().getFullName());
			logListOfTransfer.setBankName(payReceiverBankAccount.getBioBankAccount().getBank().getBankName());
			logListOfTransfer.setAccountName(payReceiverBankAccount.getBioBankAccount().getOwnerName());
			logListOfTransfer.setAccountNumber(payReceiverBankAccount.getBioBankAccount().getAccountNumber());
			logListOfTransfer.setTransferPercent(payReceiverBankAccount.getPersen());
			BigDecimal transferNominal = payTempKalkulasi.getNominal();
			transferNominal = transferNominal.multiply(new BigDecimal(payReceiverBankAccount.getPersen())).divide(new BigDecimal(100));
			logListOfTransfer.setTransferNominal(transferNominal);
			logListOfTransfer.setCreatedBy(createdBy);
			logListOfTransfer.setCreatedOn(createdOn);
			logListOfTransferDao.save(logListOfTransfer);
		}
	}

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BankTransferDistributionReportModel> getBankTransferDistributionByPayrollHistoryReport(Long periodeId) {
        return logListOfTransferDao.getBankTransferDistributionByPayrollHistoryReport(periodeId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalBankTransferByPayrollHistoryReport(Long periodeId) {
        return logListOfTransferDao.getTotalBankTransferByPayrollHistoryReport(periodeId);
    }

}
