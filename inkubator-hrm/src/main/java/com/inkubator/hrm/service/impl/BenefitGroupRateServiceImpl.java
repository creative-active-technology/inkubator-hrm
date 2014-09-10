package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.BenefitGroupDao;
import com.inkubator.hrm.dao.BenefitGroupRateDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.BenefitGroupRate;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.BenefitGroupRateService;
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
@Service(value = "benefitGroupRateService")
@Lazy
public class BenefitGroupRateServiceImpl extends IServiceImpl implements BenefitGroupRateService {

    @Autowired
    private BenefitGroupRateDao benefitGroupRateDao;
    @Autowired
    private BenefitGroupDao benefitGroupDao;
    @Autowired
    private GolonganJabatanDao golonganJabatanDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(BenefitGroupRate benefitGroupRate) throws Exception {
        benefitGroupRateDao.delete(benefitGroupRate);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BenefitGroupRate> getAllData() throws Exception {
        return this.benefitGroupRateDao.getAllData();
    }

    @Override
    public List<BenefitGroupRate> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroupRate> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroupRate> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroupRate> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroupRate> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroupRate> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BenefitGroupRate> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BenefitGroupRate getEntiyByPK(Long id) throws Exception {
        return benefitGroupRateDao.getEntiyByPK(id);
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
    public void save(BenefitGroupRate benefitGroupRate) throws Exception {
        long totalDuplicates = benefitGroupRateDao.getTotalByBenefitGroupAndGolonganJabatan(benefitGroupRate.getBenefitGroup().getId(), benefitGroupRate.getGolonganJabatan().getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("benefitGroupRate.error_duplicate_golongan");
        }
        
        
        BenefitGroup benefitGroup = benefitGroupDao.getEntiyByPK(benefitGroupRate.getBenefitGroup().getId());
        GolonganJabatan golonganJabatan = golonganJabatanDao.getEntiyByPK(benefitGroupRate.getGolonganJabatan().getId());

        benefitGroupRate.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        benefitGroupRate.setBenefitGroup(benefitGroup);
        benefitGroupRate.setGolonganJabatan(golonganJabatan);
        benefitGroupRate.setCreatedBy(UserInfoUtil.getUserName());
        benefitGroupRate.setCreatedOn(new Date());
        benefitGroupRateDao.save(benefitGroupRate);
    }

    @Override
    public BenefitGroupRate saveData(BenefitGroupRate arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(BenefitGroupRate arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BenefitGroupRate saveOrUpdateData(BenefitGroupRate arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(BenefitGroupRate arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BenefitGroupRate b) throws Exception {
        long totalDuplicates = benefitGroupRateDao.getTotalByBenefitGroupAndGolonganJabatanAndNotId(b.getBenefitGroup().getId(), b.getGolonganJabatan().getId(), b.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("benefitGroupRate.error_duplicate_golongan");
        }
        
        BenefitGroup benefitGroup = benefitGroupDao.getEntiyByPK(b.getBenefitGroup().getId());
        GolonganJabatan golonganJabatan = golonganJabatanDao.getEntiyByPK(b.getGolonganJabatan().getId());
        BenefitGroupRate benefitGroupRate = benefitGroupRateDao.getEntiyByPK(b.getId());
        benefitGroupRate.setBenefitGroup(benefitGroup);
        benefitGroupRate.setGolonganJabatan(golonganJabatan);
        benefitGroupRate.setNominal(b.getNominal());
        benefitGroupRate.setUpdatedBy(UserInfoUtil.getUserName());
        benefitGroupRate.setUpdatedOn(new Date());
        benefitGroupRateDao.update(benefitGroupRate);
    }

    @Override
    public BenefitGroupRate updateData(BenefitGroupRate arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BenefitGroupRate> getAllDataByBenefitGroupId(Long benefitGroupId) throws Exception {
        return benefitGroupRateDao.getAllDataByBenefitGroupId(benefitGroupId);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BenefitGroupRate getEntityByPKWithDetail(Long id) throws Exception {
        return benefitGroupRateDao.getEntityByPKWithDetail(id);
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BenefitGroupRate> getByGolonganJabatan(Long golonganId) throws Exception {
        return benefitGroupRateDao.getByGolonganJabatan(golonganId);

    }
    
}
