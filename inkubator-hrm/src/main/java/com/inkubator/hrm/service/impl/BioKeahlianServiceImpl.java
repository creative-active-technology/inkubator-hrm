/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioKeahlianDao;
import com.inkubator.hrm.entity.BioKeahlian;
import com.inkubator.hrm.service.BioKeahlianService;
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
@Service(value = "bioKeahlianService")
@Lazy
public class BioKeahlianServiceImpl extends IServiceImpl implements BioKeahlianService{
    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private BioKeahlianDao bioKeahlianDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public BioKeahlian getAllDataByPK(Long id) throws Exception {
        return bioKeahlianDao.getAllDataByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioKeahlian> getAllDataByBioDataId(Long bioDataId) throws Exception {
        return bioKeahlianDao.getAllDataByBioDataId(bioDataId);
    }

    @Override
    public BioKeahlian getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioKeahlian getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(BioKeahlian entity) throws Exception {
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setBiodata(bioDataDao.getEntiyByPK(entity.getBiodata().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.bioKeahlianDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioKeahlian entity) throws Exception {
        BioKeahlian update = bioKeahlianDao.getEntiyByPK(entity.getId());
        update.setBiodata(bioDataDao.getEntiyByPK(entity.getBiodata().getId()));
        update.setName(entity.getName());
        update.setTingkatKeahlian(entity.getTingkatKeahlian());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.bioKeahlianDao.update(update);
    }

    @Override
    public void saveOrUpdate(BioKeahlian enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian saveData(BioKeahlian entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian updateData(BioKeahlian entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian saveOrUpdateData(BioKeahlian entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioKeahlian getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
    public void delete(BioKeahlian entity) throws Exception {
        this.bioKeahlianDao.delete(entity);
    }

    @Override
    public void softDelete(BioKeahlian entity) throws Exception {
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
    public List<BioKeahlian> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioKeahlian> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioKeahlian> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioKeahlian> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioKeahlian> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioKeahlian> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioKeahlian> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioKeahlian> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
