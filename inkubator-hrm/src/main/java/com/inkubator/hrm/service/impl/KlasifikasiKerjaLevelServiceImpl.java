/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.KlasifikasiKerjaDao;
import com.inkubator.hrm.dao.KlasifikasiKerjaLevelDao;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.KlasifikasiKerjaLevel;
import com.inkubator.hrm.service.KlasifikasiKerjaLevelService;
import com.inkubator.hrm.web.search.KlasifikasiKerjaLevelSearchParameter;
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
 * @author EKA
 */
@Service(value="klasifikasiKerjaLevelService")
@Lazy
public class KlasifikasiKerjaLevelServiceImpl extends IServiceImpl implements KlasifikasiKerjaLevelService{

    @Autowired
    private KlasifikasiKerjaLevelDao klasifikasiKerjaLevelDao;
    
    @Autowired
    private KlasifikasiKerjaDao klasifikasiKerjaDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<KlasifikasiKerjaLevel> getByParam(KlasifikasiKerjaLevelSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception{
        return klasifikasiKerjaLevelDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalKlasifikasiKerjaLevelByParam(KlasifikasiKerjaLevelSearchParameter searchParameter) throws Exception{
        return klasifikasiKerjaLevelDao.getTotalKlasifikasiKerjaLevelByParam(searchParameter);
    }

//    @Override
//    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
//    public KlasifikasiKerjaLevel getKlasifKerjaNameByKlasifKerjaLevelId(Long id) {
//        return this.klasifikasiKerjaLevelDao.getKlasifKerjaNameByKlasifKerjaLevelId(id);
//    }

    @Override
    public KlasifikasiKerjaLevel getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public KlasifikasiKerjaLevel getEntiyByPK(Long id) throws Exception {
        return klasifikasiKerjaLevelDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(KlasifikasiKerjaLevel entity) throws Exception {
        long totalDuplicates = klasifikasiKerjaLevelDao.getTotalByCode(entity.getCode());
        if(totalDuplicates > 0){
            throw new BussinessException("marital.error_duplicate_marital_code");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setKlasifikasiKerja(klasifikasiKerjaDao.getEntiyByPK(entity.getKlasifikasiKerja().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.klasifikasiKerjaLevelDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(KlasifikasiKerjaLevel entity) throws Exception {
        long totalDuplicates = klasifikasiKerjaLevelDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if(totalDuplicates > 0){
            throw new BussinessException("marital.error_duplicate_marital_code");
        }
        KlasifikasiKerjaLevel update = klasifikasiKerjaLevelDao.getEntiyByPK(entity.getId());
        update.setCode(entity.getCode());
        update.setName(entity.getName());
        update.setDescription(entity.getDescription());
        KlasifikasiKerja kl = klasifikasiKerjaDao.getEntiyByPK(entity.getKlasifikasiKerja().getId());
        update.setKlasifikasiKerja(kl);
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.klasifikasiKerjaLevelDao.update(update);
    }

    @Override
    public void saveOrUpdate(KlasifikasiKerjaLevel t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel saveData(KlasifikasiKerjaLevel t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel updateData(KlasifikasiKerjaLevel t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel saveOrUpdateData(KlasifikasiKerjaLevel t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KlasifikasiKerjaLevel getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(KlasifikasiKerjaLevel entity) throws Exception {
        this.klasifikasiKerjaLevelDao.delete(entity);
    }

    @Override
    public void softDelete(KlasifikasiKerjaLevel t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<KlasifikasiKerjaLevel> getAllData() throws Exception {
        return klasifikasiKerjaLevelDao.getAllData();
    }

    @Override
    public List<KlasifikasiKerjaLevel> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KlasifikasiKerjaLevel> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KlasifikasiKerjaLevel> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KlasifikasiKerjaLevel> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KlasifikasiKerjaLevel> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KlasifikasiKerjaLevel> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KlasifikasiKerjaLevel> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public KlasifikasiKerjaLevel getEntityWithDetail(Long id) throws Exception{
        return klasifikasiKerjaLevelDao.getEntityWithDetail(id);
    }
    
}
