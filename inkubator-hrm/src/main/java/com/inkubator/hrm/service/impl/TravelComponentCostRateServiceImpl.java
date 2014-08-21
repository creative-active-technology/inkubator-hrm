/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.CostCenterDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.TravelComponentCostRateDao;
import com.inkubator.hrm.dao.TravelComponentDao;
import com.inkubator.hrm.dao.TravelZoneDao;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.service.TravelComponentCostRateService;
import com.inkubator.hrm.web.search.TravelComponentCostRateSearchParameter;
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
 * @author Deni
 */
@Service(value = "travelComponentCostRateService")
@Lazy
public class TravelComponentCostRateServiceImpl extends IServiceImpl implements TravelComponentCostRateService{
    
    @Autowired
    private CostCenterDao costCenterDao;
    @Autowired
    private GolonganJabatanDao golonganJabatanDao;
    @Autowired
    private TravelComponentDao travelComponentDao;
    @Autowired
    private TravelZoneDao travelZoneDao;
    @Autowired
    private TravelComponentCostRateDao travelComponentCostRateDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public List<TravelComponentCostRate> getAllDataWithAllRelation(TravelComponentCostRateSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return travelComponentCostRateDao.getAllDataWithAllRelation(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalTravelComponentRateByParam(TravelComponentCostRateSearchParameter searchParameter) throws Exception {
        return travelComponentCostRateDao.getTotalTravelComponentRateByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public TravelComponentCostRate getEntityByPkWithAllRelation(Long code) throws Exception {
        return travelComponentCostRateDao.getEntityByPkWithAllRelation(code);
    }

    @Override
    public TravelComponentCostRate getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public TravelComponentCostRate getEntiyByPK(Long id) throws Exception {
        return travelComponentCostRateDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(TravelComponentCostRate entity) throws Exception {
        // check duplicate name
        long totalDuplicates = travelComponentCostRateDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("costcenter.error_duplicate_cost_center_code");
        }
        entity.setCostCenter(costCenterDao.getEntiyByPK(entity.getCostCenter().getId()));
        entity.setGolonganJabatan(golonganJabatanDao.getEntiyByPK(entity.getGolonganJabatan().getId()));
        entity.setTravelComponent(travelComponentDao.getEntiyByPK(entity.getTravelComponent().getId()));
        entity.setTravelZone(travelZoneDao.getEntiyByPK(entity.getTravelZone().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.travelComponentCostRateDao.save(entity);
        
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(TravelComponentCostRate entity) throws Exception {
        // check duplicate name
        long totalDuplicates = travelComponentCostRateDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("costcenter.error_duplicate_cost_center_code");
        }
        TravelComponentCostRate update = travelComponentCostRateDao.getEntiyByPK(entity.getId());
        update.setCode(entity.getCode());
        update.setDescription(entity.getDescription());
        update.setDefaultRate(entity.getDefaultRate());
        update.setCostCenter(costCenterDao.getEntiyByPK(entity.getCostCenter().getId()));
        update.setGolonganJabatan(golonganJabatanDao.getEntiyByPK(entity.getGolonganJabatan().getId()));
        update.setTravelComponent(travelComponentDao.getEntiyByPK(entity.getTravelComponent().getId()));
        update.setTravelZone(travelZoneDao.getEntiyByPK(entity.getTravelZone().getId()));
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setCreatedOn(new Date());
        this.travelComponentCostRateDao.update(update);
    }

    @Override
    public void saveOrUpdate(TravelComponentCostRate enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate saveData(TravelComponentCostRate entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate updateData(TravelComponentCostRate entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate saveOrUpdateData(TravelComponentCostRate entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TravelComponentCostRate getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
    public void delete(TravelComponentCostRate entity) throws Exception {
        this.travelComponentCostRateDao.delete(entity);
    }

    @Override
    public void softDelete(TravelComponentCostRate entity) throws Exception {
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
    public List<TravelComponentCostRate> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TravelComponentCostRate> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TravelComponentCostRate> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TravelComponentCostRate> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TravelComponentCostRate> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TravelComponentCostRate> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TravelComponentCostRate> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TravelComponentCostRate> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
