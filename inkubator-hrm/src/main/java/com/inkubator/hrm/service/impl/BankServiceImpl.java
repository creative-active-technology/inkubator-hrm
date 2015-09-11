package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.BankDao;
import com.inkubator.hrm.dao.BankGroupDao;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.service.BankService;
import com.inkubator.hrm.web.search.BankSearchParameter;
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
 * @author Taufik Hidayat
 */
@Service(value = "bankService")
@Lazy
public class BankServiceImpl extends IServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;
    @Autowired
    private BankGroupDao bankGroupDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Bank bank) throws Exception {
        bankDao.delete(bank);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Bank> getAllData() throws Exception {
        return this.bankDao.getAllData();
    }

    @Override
    public List<Bank> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Bank> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Bank> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Bank> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Bank> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Bank> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<Bank> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Bank getEntiyByPK(Long id) throws Exception {
        return bankDao.getEntiyByPK(id);
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Bank bank) throws Exception {
        // check duplicate name
        long totalDuplicates = bankDao.getTotalByCode(bank.getBankCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("bank.error_duplicate_bank_code");
        }
        long swiftCodeDuplicates = bankDao.getTotalBySwiftCode(bank.getSwiftCode());
        if (swiftCodeDuplicates > 0) {
            throw new BussinessException("bank.error_duplicate_swift_code");
        }
        long identificationNumberDuplicates = bankDao.getTotalBySwiftCode(bank.getBankIdentificationNo());
        if (identificationNumberDuplicates > 0) {
            throw new BussinessException("bank.error_duplicate_identification_number");
        }

        bank.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
//        bank.setSwiftCcode(bank.getSwiftCcode());
//        bank.setBankIdentificationNo(bank.getBankIdentificationNo());
//        bank.setIban(bank.getIban());
        bank.setCreatedBy(UserInfoUtil.getUserName());
        bank.setCreatedOn(new Date());
        bank.setBank(bankDao.getEntiyByPK(bank.getBank().getId()));
        bank.setBankGroup(bankGroupDao.getEntiyByPK(bank.getBankGroup().getId()));
//        bank.setBranchCode(bank.getBranchCode());
//        bank.setBranchName(bank.getBranchName());
//        bank.setAddress(bank.getAddress());
//        bank.setBankPhone(bank.getBankPhone());
//        bank.setBankFax(bank.getBankFax());
//        bank.setBankGroup(bank.getBankGroup());
        bankDao.save(bank);
    }

    @Override
    public Bank saveData(Bank arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(Bank arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Bank saveOrUpdateData(Bank arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(Bank arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Bank b) throws Exception {
        // check duplicate name
        long totalDuplicates = bankDao.getTotalByCodeAndNotId(b.getBankCode(), b.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("bank.error_duplicate_bank_code");
        }
        long swiftDuplicates = bankDao.getTotalBySwiftCodeAndNotId(b.getSwiftCode(), b.getId());
        if (swiftDuplicates > 0) {
            throw new BussinessException("bank.error_duplicate_swift_code");
        }
        long identificationNumberDuplicates = bankDao.getTotalByIdentificationNumberAndNotId(b.getBankIdentificationNo(), b.getId());
        if (identificationNumberDuplicates > 0) {
            throw new BussinessException("bank.error_duplicate_identification_number");
        }

        Bank bank = bankDao.getEntiyByPK(b.getId());
        bank.setBankCode(b.getBankCode());
        bank.setBankName(b.getBankName());
        bank.setSwiftCode(b.getSwiftCode());
        bank.setBank(bankDao.getEntiyByPK(b.getBank().getId()));
        bank.setBankGroup(bankGroupDao.getEntiyByPK(b.getBankGroup().getId()));
        bank.setBankIdentificationNo(b.getBankIdentificationNo());
        bank.setIban(b.getIban());
        bank.setDescription(b.getDescription());
        bank.setUpdatedBy(UserInfoUtil.getUserName());
        bank.setUpdatedOn(new Date());
        bank.setBranchCode(b.getBranchCode());
        bank.setBranchName(b.getBranchName());
        bank.setAddress(b.getAddress());
        bank.setBankPhone(b.getBankPhone());
        bank.setBankFax(b.getBankFax());
        bankDao.update(bank);
    }

    @Override
    public Bank updateData(Bank arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Bank> getByParam(BankSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.bankDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(BankSearchParameter parameter) throws Exception {
        return this.bankDao.getTotalBankByParam(parameter);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    @Override
    public Bank getEntityWithDetail(Long id) throws Exception {
        return bankDao.getEntityWithDetail(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<Bank> getAllWithparent() throws Exception {
        return this.bankDao.getAllWithparent();
    }

}
