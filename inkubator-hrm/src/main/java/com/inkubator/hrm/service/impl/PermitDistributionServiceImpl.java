/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.PermitClassificationDao;
import com.inkubator.hrm.dao.PermitDistributionDao;
import com.inkubator.hrm.dao.NeracaPermitDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.entity.NeracaPermit;
import com.inkubator.hrm.service.PermitDistributionService;
import com.inkubator.hrm.web.search.PermitDistributionSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @author Taufik
 */
@Service(value = "permitDistributionService")
@Lazy
public class PermitDistributionServiceImpl extends IServiceImpl implements PermitDistributionService {

    @Autowired
    private PermitDistributionDao permitDistributionDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private PermitClassificationDao permitDao;
    @Autowired
    private NeracaPermitDao neracaPermitDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PermitDistribution> getByParamWithDetail(PermitDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return permitDistributionDao.getByParamWithDetail(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalPermitDistributionByParam(PermitDistributionSearchParameter searchParameter) throws Exception {
        return permitDistributionDao.getTotalPermitDistributionByParam(searchParameter);
    }

    @Override
    public PermitDistribution getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(PermitDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(PermitDistribution entity) throws Exception {
        PermitDistribution update = permitDistributionDao.getEntiyByPK(entity.getId());
        update.setEmpData(empDataDao.getEntiyByPK(entity.getEmpData().getId()));
        update.setBalance(entity.getBalance());
        update.setPermitClassification(permitDao.getEntiyByPK(entity.getPermitClassification().getId()));
        permitDistributionDao.update(update);
    }

    @Override
    public void saveOrUpdate(PermitDistribution enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution saveData(PermitDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution updateData(PermitDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution saveOrUpdateData(PermitDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PermitDistribution getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PermitDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(PermitDistribution entity) throws Exception {
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
    public List<PermitDistribution> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PermitDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveMassPenempatanPermit(List<EmpData> data, long permitId, double startBalance) throws Exception {
        PermitClassification permit = this.permitDao.getEntiyByPK(permitId);
        List<PermitDistribution> listDistribution = new ArrayList<>();
        List<NeracaPermit> listNeracaPermit = new ArrayList<>();
//        if (startBalance > permit.getQuotaPerPeriod()) {
//            throw new BussinessException("permit_start_balance.error");
//        }

        for (EmpData empData : data) {
            PermitDistribution distribution = new PermitDistribution();
            

            distribution.setBalance(startBalance);
            distribution.setEmpData(empData);
            distribution.setPermitClassification(permit);
            distribution.setCreatedBy(UserInfoUtil.getUserName());
            distribution.setCreatedOn(new Date());
            distribution.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            listDistribution.add(distribution);
            NeracaPermit neracaPermit = new NeracaPermit();
            neracaPermit.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            neracaPermit.setDebet(startBalance);
            neracaPermit.setPermitDistribution(distribution);
            neracaPermit.setCreatedBy(UserInfoUtil.getUserName());
            neracaPermit.setCreatedOn(new Date());
            listNeracaPermit.add(neracaPermit);
//            permitDistributionDao.save(distribution);
//            distribution.set
        }
        permitDistributionDao.saveBatch(listDistribution);
        neracaPermitDao.saveBacth(listNeracaPermit);

    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    @Override
    public PermitDistribution getEntityByParamWithDetail(Long empId) throws Exception {
        return permitDistributionDao.getEntityByParamWithDetail(empId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PermitDistribution> getAllDataByEmpIdWithDetail() throws Exception {
        return permitDistributionDao.getAllDataByEmpIdWithDetail();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<PermitDistribution> getAllDataByEmpIdFetchPermit(Long empDataId) throws Exception {
        return permitDistributionDao.getAllDataByEmpIdFetchPermit(empDataId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public PermitDistribution getEntityByPermitClassificationIdAndEmpDataId(Long permitId, Long empDataId) throws Exception {
        return permitDistributionDao.getEntityByPermitClassificationIdAndEmpDataId(permitId, empDataId);

    }

}
