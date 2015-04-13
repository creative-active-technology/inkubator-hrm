package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.entity.OrgJobFamilyLevel;
import com.inkubator.hrm.dao.OrgJobFamilyLevelDao;
import com.inkubator.hrm.service.OrgJobFamilyLevelService;
import com.inkubator.hrm.web.search.OrgJobFamilyLevelSearchParameter;
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
@Service(value = "orgJobFamilyLevelService")
@Lazy
public class OrgJobFamilyLevelServiceImpl extends IServiceImpl implements OrgJobFamilyLevelService {

    @Autowired
    private OrgJobFamilyLevelDao orgJobFamilyLevelDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(OrgJobFamilyLevel orgJobFamilyLevel) throws Exception {
        orgJobFamilyLevelDao.delete(orgJobFamilyLevel);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(OrgJobFamilyLevel orgJobFamilyLevel) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = orgJobFamilyLevelDao.getTotalByCode(orgJobFamilyLevel.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("orgJobFamilyLevel.error_duplicate_code")
//}
        orgJobFamilyLevel.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        orgJobFamilyLevel.setCreatedBy(UserInfoUtil.getUserName());
        orgJobFamilyLevel.setCreatedOn(new Date());
        orgJobFamilyLevelDao.save(orgJobFamilyLevel);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(OrgJobFamilyLevel orgJobFamilyLevel) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = orgJobFamilyLevelDao.getTotalByCodeAndNotId(orgJobFamilyLevel.getCode(),orgJobFamilyLevel.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("orgJobFamilyLevel.error_duplicate_code")
//}
        OrgJobFamilyLevel orgJobFamilyLevel1 = orgJobFamilyLevelDao.getEntiyByPK(orgJobFamilyLevel.getId());
        orgJobFamilyLevel1.setUpdatedBy(UserInfoUtil.getUserName());
        orgJobFamilyLevel1.setUpdatedOn(new Date());
        orgJobFamilyLevel1.setPaCompetencies(orgJobFamilyLevel.getPaCompetencies());
        orgJobFamilyLevel1.setGolonganJabatan(orgJobFamilyLevel.getGolonganJabatan());
        orgJobFamilyLevel1.setKlasifikasiKerja(orgJobFamilyLevel.getKlasifikasiKerja());
        orgJobFamilyLevel1.setPangkat(orgJobFamilyLevel.getPangkat());
        orgJobFamilyLevel1.setPointScore(orgJobFamilyLevel.getPointScore());
        orgJobFamilyLevelDao.update(orgJobFamilyLevel1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public OrgJobFamilyLevel getEntiyByPK(Long id) {
        return this.orgJobFamilyLevelDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<OrgJobFamilyLevel> getByParam(OrgJobFamilyLevelSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.orgJobFamilyLevelDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalOrgJobFamilyLevelByParam(OrgJobFamilyLevelSearchParameter searchParameter) throws Exception {
        return this.orgJobFamilyLevelDao.getTotalOrgJobFamilyLevelByParam(searchParameter);
    }

    @Override
    public OrgJobFamilyLevel getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(OrgJobFamilyLevel enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel saveData(OrgJobFamilyLevel entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel updateData(OrgJobFamilyLevel entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel saveOrUpdateData(OrgJobFamilyLevel entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgJobFamilyLevel getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(OrgJobFamilyLevel entity) throws Exception {
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
    public List<OrgJobFamilyLevel> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgJobFamilyLevel> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgJobFamilyLevel> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgJobFamilyLevel> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgJobFamilyLevel> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgJobFamilyLevel> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgJobFamilyLevel> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgJobFamilyLevel> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
