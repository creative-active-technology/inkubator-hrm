package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.CostCenterDao;
import com.inkubator.hrm.dao.PaySalaryJurnalDao;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.PaySalaryJurnal;
import com.inkubator.hrm.service.PaySalaryJurnalService;
import com.inkubator.hrm.web.search.PaySalaryJurnalSearchParameter;
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
@Service(value = "paySalaryJurnalService")
@Lazy
public class PaySalaryJurnalServiceImpl extends IServiceImpl implements PaySalaryJurnalService {

    @Autowired
    private PaySalaryJurnalDao paySalaryJurnalDao;
    @Autowired
    private CostCenterDao costCenterDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(PaySalaryJurnal paySalaryJurnal) throws Exception {
        paySalaryJurnalDao.delete(paySalaryJurnal);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PaySalaryJurnal> getAllData() throws Exception {
        return this.paySalaryJurnalDao.getAllData();
    }

    @Override
    public List<PaySalaryJurnal> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PaySalaryJurnal> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PaySalaryJurnal> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PaySalaryJurnal> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PaySalaryJurnal> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PaySalaryJurnal> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<PaySalaryJurnal> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PaySalaryJurnal getEntiyByPK(Long id) throws Exception {
        return paySalaryJurnalDao.getEntiyByPK(id);
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
    public void save(PaySalaryJurnal paySalaryJurnal) throws Exception {
        // check duplicate name
        long totalDuplicates = paySalaryJurnalDao.getTotalByCode(paySalaryJurnal.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("paySalaryJurnal.error_duplicate_paySalaryJurnal_code");
        }
        CostCenter costCenter = costCenterDao.getEntiyByPK(paySalaryJurnal.getCostCenter().getId());

        paySalaryJurnal.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        paySalaryJurnal.setCostCenter(costCenter);
        paySalaryJurnal.setCreatedBy(UserInfoUtil.getUserName());
        paySalaryJurnal.setCreatedOn(new Date());
        paySalaryJurnalDao.save(paySalaryJurnal);
    }

    @Override
    public PaySalaryJurnal saveData(PaySalaryJurnal arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(PaySalaryJurnal arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public PaySalaryJurnal saveOrUpdateData(PaySalaryJurnal arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(PaySalaryJurnal arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(PaySalaryJurnal b) throws Exception {
        // check duplicate name
        long totalDuplicates = paySalaryJurnalDao.getTotalByCodeAndNotId(b.getCode(), b.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("paySalaryJurnal.error_duplicate_paySalaryJurnal_code");
        }
        CostCenter costCenter = costCenterDao.getEntiyByPK(b.getCostCenter().getId());

        PaySalaryJurnal paySalaryJurnal = paySalaryJurnalDao.getEntiyByPK(b.getId());
        paySalaryJurnal.setCode(b.getCode());
        paySalaryJurnal.setName(b.getName());
        paySalaryJurnal.setCostCenter(costCenter);
        paySalaryJurnal.setTypeJurnal(b.getTypeJurnal());
        paySalaryJurnal.setModelJurnal(b.getModelJurnal());
        paySalaryJurnal.setDescription(b.getDescription());
        paySalaryJurnal.setUpdatedBy(UserInfoUtil.getUserName());
        paySalaryJurnal.setUpdatedOn(new Date());
        paySalaryJurnalDao.update(paySalaryJurnal);
    }

    @Override
    public PaySalaryJurnal updateData(PaySalaryJurnal arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PaySalaryJurnal> getByParam(PaySalaryJurnalSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.paySalaryJurnalDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(PaySalaryJurnalSearchParameter parameter) throws Exception {
        return this.paySalaryJurnalDao.getTotalPaySalaryJurnalByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PaySalaryJurnal getEntityByPKWithDetail(Long id) throws Exception {
        return paySalaryJurnalDao.getEntityByPKWithDetail(id);

    }

}
