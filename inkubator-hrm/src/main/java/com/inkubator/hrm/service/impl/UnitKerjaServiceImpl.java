/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.UnitKerjaDao;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.web.search.UnitKerjaSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.math.RandomUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author deniarianto
 */
@Service(value = "unitKerjaService")
@Lazy
public class UnitKerjaServiceImpl extends IServiceImpl implements UnitKerjaService{

    @Autowired
    private UnitKerjaDao unitKerjaDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<UnitKerja> getByParam(UnitKerjaSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        return this.unitKerjaDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalUnitKerjaByParam(UnitKerjaSearchParameter searchParameter) {
        return this.unitKerjaDao.getTotalUnitKerjaByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getByUnitKerjaName(String name) {
        return this.unitKerjaDao.getByUnitKerjaCode(name);
    }

    @Override
    public UnitKerja getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public UnitKerja getEntiyByPK(Long id) throws Exception {
        return this.unitKerjaDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(UnitKerja entity) throws Exception {
        long totalDuplicates = unitKerjaDao.getByUnitKerjaCode(entity.getCode());
        if(totalDuplicates > 0){
            throw new BussinessException("unitkerja.error_duplicate_unit_kerja_name");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCode(entity.getCode());
        entity.setName(entity.getName());
        entity.setLocation(entity.getLocation());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.unitKerjaDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(UnitKerja entity) throws Exception {
        long totalDuplicates = unitKerjaDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("unitkerja.error_duplicate_unit_kerja_name");
        }
        UnitKerja unitKerjaUpdate = this.unitKerjaDao.getEntiyByPK(entity.getId());
        unitKerjaUpdate.setCode(entity.getCode());
        unitKerjaUpdate.setName(entity.getName());
        unitKerjaUpdate.setLocation(entity.getLocation());
        unitKerjaUpdate.setUpdatedBy(UserInfoUtil.getUserName());
        unitKerjaUpdate.setUpdatedOn(new Date());
        this.unitKerjaDao.update(unitKerjaUpdate);
    }

    @Override
    public void saveOrUpdate(UnitKerja enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja saveData(UnitKerja entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja updateData(UnitKerja entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja saveOrUpdateData(UnitKerja entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnitKerja getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
    public void delete(UnitKerja entity) throws Exception {
        this.unitKerjaDao.delete(entity);
    }

    @Override
    public void softDelete(UnitKerja entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 30)
    public List<UnitKerja> getAllData() throws Exception {
        return this.unitKerjaDao.getAllData();
    }

    @Override
    public List<UnitKerja> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnitKerja> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnitKerja> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnitKerja> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnitKerja> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnitKerja> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnitKerja> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
