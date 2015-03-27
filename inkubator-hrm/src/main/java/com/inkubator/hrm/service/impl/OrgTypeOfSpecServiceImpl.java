package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.OrgTypeOfSpecDao;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.service.OrgTypeOfSpecService;
import com.inkubator.hrm.web.search.OrgTypeOfSpecSearchParameter;
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
 * @author WebGenX
 */
@Service(value = "orgTypeOfSpecService")
@Lazy
public class OrgTypeOfSpecServiceImpl extends IServiceImpl implements OrgTypeOfSpecService {

    @Autowired
    private OrgTypeOfSpecDao orgTypeOfSpecDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(OrgTypeOfSpec orgTypeOfSpec) throws Exception {
        orgTypeOfSpecDao.delete(orgTypeOfSpec);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(OrgTypeOfSpec orgTypeOfSpec) throws Exception {
//Uncomment below if u have unik code
        long totalDuplicates = orgTypeOfSpecDao.getTotalByCode(orgTypeOfSpec.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("orgTypeOfSpec.error_duplicate_code");
        }
        orgTypeOfSpec.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        orgTypeOfSpec.setCreatedBy(UserInfoUtil.getUserName());
        orgTypeOfSpec.setCreatedOn(new Date());
        orgTypeOfSpecDao.save(orgTypeOfSpec);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(OrgTypeOfSpec orgTypeOfSpec) throws Exception {
//Uncomment below if u have unik code
        long totalDuplicates = orgTypeOfSpecDao.getTotalByCodeAndNotId(orgTypeOfSpec.getCode(), orgTypeOfSpec.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("marital.error_duplicate_marital_code");
        }
        OrgTypeOfSpec orgTypeOfSpec1 = orgTypeOfSpecDao.getEntiyByPK(orgTypeOfSpec.getId());
        orgTypeOfSpec1.setUpdatedBy(UserInfoUtil.getUserName());
        orgTypeOfSpec1.setUpdatedOn(new Date());
        orgTypeOfSpec1.setId(orgTypeOfSpec.getId());
        orgTypeOfSpec1.setDescription(orgTypeOfSpec.getDescription());
        orgTypeOfSpec1.setName(orgTypeOfSpec.getName());
        orgTypeOfSpec1.setCode(orgTypeOfSpec.getCode());
        orgTypeOfSpec1.setVersion(orgTypeOfSpec.getVersion());
        orgTypeOfSpecDao.update(orgTypeOfSpec1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public OrgTypeOfSpec getEntiyByPK(Long id) {
        return this.orgTypeOfSpecDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<OrgTypeOfSpec> getByParam(OrgTypeOfSpecSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.orgTypeOfSpecDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalOrgTypeOfSpecByParam(OrgTypeOfSpecSearchParameter searchParameter) throws Exception {
        return this.orgTypeOfSpecDao.getTotalOrgTypeOfSpecByParam(searchParameter);
    }

    @Override
    public OrgTypeOfSpec getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(OrgTypeOfSpec enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec saveData(OrgTypeOfSpec entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec updateData(OrgTypeOfSpec entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec saveOrUpdateData(OrgTypeOfSpec entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpec getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(OrgTypeOfSpec entity) throws Exception {
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
    public List<OrgTypeOfSpec> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpec> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpec> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpec> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpec> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpec> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpec> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpec> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
