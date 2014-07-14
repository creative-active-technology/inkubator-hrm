/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.JabatanSpesifikasiDao;
import com.inkubator.hrm.dao.SpecificationAbilityDao;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.service.JabatanSpesifikasiService;
import com.inkubator.hrm.web.search.JabatanSpesifikasiSearchParameter;
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
@Service(value = "jabatanSpesifikasiService")
@Lazy
public class JabatanSpesifikasiServiceImpl extends IServiceImpl implements JabatanSpesifikasiService{
    
    @Autowired
    private JabatanSpesifikasiDao jabatanSpesifikasiDao;
    @Autowired
    private JabatanDao jabatanDao;
    @Autowired
    private SpecificationAbilityDao specAbilityDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<JabatanSpesifikasi> getByParam(JabatanSpesifikasiSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return jabatanSpesifikasiDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalJabatanSpesifikasiByParam(JabatanSpesifikasiSearchParameter searchParameter) throws Exception {
        return jabatanSpesifikasiDao.getTotalJabatanSpesifikasiByParam(searchParameter);
    }

    @Override
    public JabatanSpesifikasi getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public JabatanSpesifikasi getEntiyByPK(Long id) throws Exception {
        return jabatanSpesifikasiDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(JabatanSpesifikasi entity) throws Exception {
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setJabatan(jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
        entity.setSpecificationAbility(specAbilityDao.getEntiyByPK(entity.getSpecificationAbility().getId()));
        entity.setValue(entity.getValue());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.jabatanSpesifikasiDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(JabatanSpesifikasi entity) throws Exception {
        JabatanSpesifikasi jobSpek=this.jabatanSpesifikasiDao.getEntiyByPK(entity.getId());
        jobSpek.setJabatan(jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
        jobSpek.setSpecificationAbility(specAbilityDao.getEntiyByPK(entity.getSpecificationAbility().getId()));
        jobSpek.setValue(entity.getValue());
        jobSpek.setUpdatedBy(UserInfoUtil.getUserName());
        jobSpek.setUpdatedOn(new Date());
        jobSpek.setOptionAbility(entity.getOptionAbility());
        this.jabatanSpesifikasiDao.update(jobSpek);
    }

    @Override
    public void saveOrUpdate(JabatanSpesifikasi enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi saveData(JabatanSpesifikasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi updateData(JabatanSpesifikasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi saveOrUpdateData(JabatanSpesifikasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
    public void delete(JabatanSpesifikasi entity) throws Exception {
        this.jabatanSpesifikasiDao.delete(entity);
    }

    @Override
    public void softDelete(JabatanSpesifikasi entity) throws Exception {
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
    public List<JabatanSpesifikasi> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<JabatanSpesifikasi> getByJabatan(JabatanSpesifikasiSearchParameter searchParameter, Long id, int firstResult, int maxResults, Order order) throws Exception{
        return jabatanSpesifikasiDao.getByJabatan(searchParameter, id, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalJabatanSpesifikasiByJabatan(JabatanSpesifikasiSearchParameter searchParameter, Long id) throws Exception{
        return jabatanSpesifikasiDao.getTotalJabatanSpesifikasiByJabatan(searchParameter, id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public JabatanSpesifikasi getDataByPK(Long id) throws Exception {
        return jabatanSpesifikasiDao.getDataByPK(id);
    }

    @Override
    public JabatanSpesifikasi getDataBySpecAbility(Long specId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
