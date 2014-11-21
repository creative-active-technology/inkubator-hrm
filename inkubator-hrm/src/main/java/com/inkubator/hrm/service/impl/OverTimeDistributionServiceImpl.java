/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.OverTimeDistributionDao;
import com.inkubator.hrm.dao.WtOverTimeDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.entity.OverTimeDistributionId;
import com.inkubator.hrm.service.OverTimeDistributionService;
import com.inkubator.hrm.web.search.OverTimeDistributionSearchParameter;
import java.util.ArrayList;
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
 * @author Deni Husni FR
 */
@Service(value = "overTimeDistributionService")
@Lazy
public class OverTimeDistributionServiceImpl extends IServiceImpl implements OverTimeDistributionService {

    @Autowired
    private OverTimeDistributionDao overTimeDistributionDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private WtOverTimeDao wtOverTimeDao;

    @Override
    public OverTimeDistribution getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(OverTimeDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(OverTimeDistribution entity) throws Exception {
        //delete existing overtime distribution
        OverTimeDistribution update = overTimeDistributionDao.getById(entity.getId());
        this.overTimeDistributionDao.delete(update);
        //save new data overtime distribution
        OverTimeDistribution newData = new OverTimeDistribution();
        newData.setId(new OverTimeDistributionId(entity.getWtOverTime().getId(), entity.getEmpData().getId()));
        newData.setEmpData(empDataDao.getEntiyByPK(entity.getEmpData().getId()));
        newData.setWtOverTime(wtOverTimeDao.getEntiyByPK(entity.getWtOverTime().getId()));
        this.overTimeDistributionDao.save(newData);
    }

    
    
    protected void deleteManyToMany(Object entity) {
        overTimeDistributionDao.delete((OverTimeDistribution) entity);
    }
    
    @Override
    public void saveOrUpdate(OverTimeDistribution enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution saveData(OverTimeDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution updateData(OverTimeDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution saveOrUpdateData(OverTimeDistribution entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OverTimeDistribution getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(OverTimeDistribution entity) throws Exception {
        this.overTimeDistributionDao.delete(entity);
    }

    @Override
    public void softDelete(OverTimeDistribution entity) throws Exception {
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
    public List<OverTimeDistribution> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OverTimeDistribution> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OverTimeDistribution> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OverTimeDistribution> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OverTimeDistribution> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OverTimeDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OverTimeDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OverTimeDistribution> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<OverTimeDistribution> getByParamWithDetail(OverTimeDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return overTimeDistributionDao.getByParamWithDetail(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalOverTimeDistributionByParam(OverTimeDistributionSearchParameter searchParameter) throws Exception {
        return overTimeDistributionDao.getTotalOverTimeDistributionByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public OverTimeDistribution getEntityByParamWithDetail(Long empId, Long overTimeId) throws Exception {
        return overTimeDistributionDao.getEntityByParamWithDetail(empId, overTimeId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<OverTimeDistribution> getAllDataByIdWithDetail() throws Exception {
        return overTimeDistributionDao.getAllDataByIdWithDetail();
    }
    
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 50)
    public void savePenempatanOt(List<EmpData> data, long id) throws Exception {
        List<OverTimeDistribution> dataToSave=new ArrayList<>();
        for (EmpData empData : data) {
            OverTimeDistribution distribution=new OverTimeDistribution();
            distribution.setEmpData(empData);
            distribution.setWtOverTime(wtOverTimeDao.getEntiyByPK(id));
            distribution.setId(new OverTimeDistributionId(id, empData.getId()));
            dataToSave.add(distribution);
        }
        overTimeDistributionDao.saveBatch(dataToSave);
    }

}
