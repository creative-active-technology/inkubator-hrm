package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.BankDao;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioBankAccountDao;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.dao.CurrencyDao;
import com.inkubator.hrm.dao.BankDao;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioBankAccount;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.service.BioBankAccountService;
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
@Service(value = "bioBankAccountService")
@Lazy
public class BioBankAccountServiceImpl extends IServiceImpl implements BioBankAccountService {

    @Autowired
    private BioBankAccountDao bioBankAccountDao;
    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private BankDao bankDao;
    @Autowired
    private CurrencyDao currencyDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(BioBankAccount bioBankAccount) throws Exception {
        bioBankAccountDao.delete(bioBankAccount);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioBankAccount> getAllData() throws Exception {
        return this.bioBankAccountDao.getAllData();
    }

    @Override
    public List<BioBankAccount> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioBankAccount> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioBankAccount> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioBankAccount> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioBankAccount> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioBankAccount> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioBankAccount> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioBankAccount getEntiyByPK(Long id) throws Exception {
        return bioBankAccountDao.getEntiyByPK(id);
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
    public void save(BioBankAccount bioBankAccount) throws Exception {
        long swiftCodeDuplicates = bioBankAccountDao.getTotalBySwiftCode(bioBankAccount.getSwiftCode());
        if(swiftCodeDuplicates > 0){
            throw new BussinessException("bioBankAccount.error_duplicate_swift_code");
        }
        
        long accountNumberDuplicates = bioBankAccountDao.getTotalByAccountNumber(bioBankAccount.getAccountNumber());
        if(accountNumberDuplicates > 0){
            throw new BussinessException("bioBankAccount.error_duplicate_account_number");
        }
        
        BioData biodata = bioDataDao.getEntiyByPK(bioBankAccount.getBioData().getId());
        City city = cityDao.getEntiyByPK(bioBankAccount.getCity().getId());
        Bank bank = bankDao.getEntiyByPK(bioBankAccount.getBank().getId());
        Currency currency = currencyDao.getEntiyByPK(bioBankAccount.getCurrency().getId());
        bioBankAccount.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        bioBankAccount.setBioData(biodata);
        bioBankAccount.setCity(city);
        bioBankAccount.setBank(bank);
        bioBankAccount.setCurrency(currency);
        bioBankAccount.setCreatedBy(UserInfoUtil.getUserName());
        bioBankAccount.setCreatedOn(new Date());
        bioBankAccountDao.save(bioBankAccount);
    }

    @Override
    public BioBankAccount saveData(BioBankAccount arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(BioBankAccount arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioBankAccount saveOrUpdateData(BioBankAccount arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(BioBankAccount arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioBankAccount b) throws Exception {
        long swiftCodeDuplicates = bioBankAccountDao.getTotalBySwiftCodeAndNotId(b.getSwiftCode(), b.getId());
        if(swiftCodeDuplicates > 0){
            throw new BussinessException("bioBankAccount.error_duplicate_swift_code");
        }
        
        long accountNumberDuplicates = bioBankAccountDao.getTotalByAccountNumberAndNotId(b.getAccountNumber(), b.getId());
        if(accountNumberDuplicates > 0){
            throw new BussinessException("bioBankAccount.error_duplicate_account_number");
        }
        
        BioData biodata = bioDataDao.getEntiyByPK(b.getBioData().getId());
        City city = cityDao.getEntiyByPK(b.getCity().getId());
        Bank bank = bankDao.getEntiyByPK(b.getBank().getId());
        Currency currency = currencyDao.getEntiyByPK(b.getCurrency().getId());
        BioBankAccount bioBankAccount = bioBankAccountDao.getEntiyByPK(b.getId());
        bioBankAccount.setBioData(biodata);
        bioBankAccount.setCity(city);
        bioBankAccount.setBank(bank);
        bioBankAccount.setCurrency(currency);
        bioBankAccount.setOwnerName(b.getOwnerName());
        bioBankAccount.setAccountNumber(b.getAccountNumber());
        bioBankAccount.setBranch(b.getBranch());
        bioBankAccount.setAddress(b.getAddress());
        bioBankAccount.setSavingType(b.getSavingType());
        bioBankAccount.setSwiftCode(b.getSwiftCode());
        bioBankAccount.setDefaultAccount(b.getDefaultAccount());
        bioBankAccount.setUpdatedBy(UserInfoUtil.getUserName());
        bioBankAccount.setUpdatedOn(new Date());
        bioBankAccountDao.update(bioBankAccount);
    }

    @Override
    public BioBankAccount updateData(BioBankAccount arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioBankAccount> getAllDataByBioDataId(Long bioDataId) throws Exception {
        return bioBankAccountDao.getAllDataByBioDataId(bioDataId);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioBankAccount getEntityByPKWithDetail(Long id) throws Exception {
        return bioBankAccountDao.getEntityByPKWithDetail(id);
    }
    
}
