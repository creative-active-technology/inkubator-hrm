package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.hrm.web.search.SystemLetterReferenceSearchParameter;
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
import com.inkubator.common.util.RandomNumberUtil;

/**
 *
 * @author WebGenX
 */
@Service(value = "systemLetterReferenceService")
@Lazy
public class SystemLetterReferenceServiceImpl extends IServiceImpl implements SystemLetterReferenceService {

    @Autowired
    private SystemLetterReferenceDao systemLetterReferenceDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(SystemLetterReference systemLetterReference) throws Exception {
        systemLetterReferenceDao.delete(systemLetterReference);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(SystemLetterReference systemLetterReference) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = systemLetterReferenceDao.getTotalByCode(systemLetterReference.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("systemLetterReference.error_duplicate_code")
//}
        systemLetterReference.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        systemLetterReference.setCreatedBy(UserInfoUtil.getUserName());
        systemLetterReference.setCreatedOn(new Date());
        systemLetterReferenceDao.save(systemLetterReference);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(SystemLetterReference systemLetterReference) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = systemLetterReferenceDao.getTotalByCodeAndNotId(systemLetterReference.getCode(),systemLetterReference.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("systemLetterReference.error_duplicate_code")
//}
        SystemLetterReference systemLetterReference1 = systemLetterReferenceDao.getEntiyByPK(systemLetterReference.getId());
        systemLetterReference1.setUpdatedBy(UserInfoUtil.getUserName());
        systemLetterReference1.setUpdatedOn(new Date());
        systemLetterReference1.setLetterSumary(systemLetterReference.getLetterSumary());
        systemLetterReference1.setDescription(systemLetterReference.getDescription());
        systemLetterReference1.setName(systemLetterReference.getName());
        systemLetterReference1.setUploadData(systemLetterReference.getUploadData());
        systemLetterReference1.setCode(systemLetterReference.getCode());
        systemLetterReferenceDao.update(systemLetterReference1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public SystemLetterReference getEntiyByPK(Long id) {
        return this.systemLetterReferenceDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<SystemLetterReference> getByParam(SystemLetterReferenceSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.systemLetterReferenceDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalSystemLetterReferenceByParam(SystemLetterReferenceSearchParameter searchParameter) throws Exception {
        return this.systemLetterReferenceDao.getTotalSystemLetterReferenceByParam(searchParameter);
    }

    @Override
    public SystemLetterReference getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(SystemLetterReference enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference saveData(SystemLetterReference entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference updateData(SystemLetterReference entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference saveOrUpdateData(SystemLetterReference entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemLetterReference getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(SystemLetterReference entity) throws Exception {
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
    public List<SystemLetterReference> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemLetterReference> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemLetterReference> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemLetterReference> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemLetterReference> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemLetterReference> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemLetterReference> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemLetterReference> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
