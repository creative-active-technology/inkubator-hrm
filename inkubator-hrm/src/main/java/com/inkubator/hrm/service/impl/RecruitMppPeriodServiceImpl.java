package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.dao.RecruitMppPeriodDao;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.web.search.RecruitMppPeriodSearchParameter;
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
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.common.util.RandomNumberUtil;

/**
 *
 * @author WebGenX
 */
@Service(value = "recruitMppPeriodService")
@Lazy
public class RecruitMppPeriodServiceImpl extends IServiceImpl implements RecruitMppPeriodService {

    @Autowired
    private RecruitMppPeriodDao recruitMppPeriodDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(RecruitMppPeriod recruitMppPeriod) throws Exception {
        recruitMppPeriodDao.delete(recruitMppPeriod);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitMppPeriod recruitMppPeriod) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = recruitMppPeriodDao.getTotalByCode(recruitMppPeriod.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("recruitMppPeriod.error_duplicate_code")
//}
        recruitMppPeriod.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        recruitMppPeriod.setCreatedBy(UserInfoUtil.getUserName());
        recruitMppPeriod.setCreatedOn(new Date());
        recruitMppPeriodDao.save(recruitMppPeriod);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitMppPeriod recruitMppPeriod) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = recruitMppPeriodDao.getTotalByCodeAndNotId(recruitMppPeriod.getCode(),recruitMppPeriod.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("recruitMppPeriod.error_duplicate_code")
//}
        RecruitMppPeriod recruitMppPeriod1 = recruitMppPeriodDao.getEntiyByPK(recruitMppPeriod.getId());
        recruitMppPeriod1.setUpdatedBy(UserInfoUtil.getUserName());
        recruitMppPeriod1.setUpdatedOn(new Date());
        recruitMppPeriod1.setPeriodeStart(recruitMppPeriod.getPeriodeStart());
        recruitMppPeriod1.setName(recruitMppPeriod.getName());
        recruitMppPeriod1.setCode(recruitMppPeriod.getCode());
        recruitMppPeriod1.setPeriodeEnd(recruitMppPeriod.getPeriodeEnd());
        recruitMppPeriodDao.update(recruitMppPeriod1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitMppPeriod getEntiyByPK(Long id) {
        return this.recruitMppPeriodDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitMppPeriod> getByParam(RecruitMppPeriodSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.recruitMppPeriodDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalRecruitMppPeriodByParam(RecruitMppPeriodSearchParameter searchParameter) throws Exception {
        return this.recruitMppPeriodDao.getTotalRecruitMppPeriodByParam(searchParameter);
    }

    @Override
    public RecruitMppPeriod getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(RecruitMppPeriod enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod saveData(RecruitMppPeriod entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod updateData(RecruitMppPeriod entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod saveOrUpdateData(RecruitMppPeriod entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitMppPeriod getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(RecruitMppPeriod entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<RecruitMppPeriod> getAllData() throws Exception {
        return this.recruitMppPeriodDao.getAllData();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<RecruitMppPeriod> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppPeriod> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppPeriod> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppPeriod> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppPeriod> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppPeriod> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitMppPeriod> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
