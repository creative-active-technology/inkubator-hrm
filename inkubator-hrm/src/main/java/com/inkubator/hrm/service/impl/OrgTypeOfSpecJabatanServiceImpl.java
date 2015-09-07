/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.OrgTypeOfSpecJabatanDao;
import com.inkubator.hrm.dao.OrgTypeOfSpecListDao;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatanId;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.service.OrgTypeOfSpecJabatanService;
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
@Service(value = "orgTypeOfSpecJabatanService")
@Lazy
public class OrgTypeOfSpecJabatanServiceImpl extends IServiceImpl implements OrgTypeOfSpecJabatanService{

    @Autowired
    private OrgTypeOfSpecJabatanDao orgTypeOfSpecJabatanDao;
    @Autowired
    private OrgTypeOfSpecListDao orgTypeOfSpecListDao;
    @Autowired
    private JabatanDao jabatanDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<OrgTypeOfSpecJabatan> getAllDataByJabatanId(Long id, int firstResult, int maxResults, Order order) throws Exception {
        return orgTypeOfSpecJabatanDao.getAllDataByJabatanId(id, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalDataByJabatanId(Long id) throws Exception {
        return orgTypeOfSpecJabatanDao.getTotalDataByJabatanId(id);
    }

    @Override
    public OrgTypeOfSpecJabatan getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public OrgTypeOfSpecJabatan getEntiyByPK(Long id) throws Exception {
        return orgTypeOfSpecJabatanDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(OrgTypeOfSpecJabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(OrgTypeOfSpecJabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(OrgTypeOfSpecJabatan enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan saveData(OrgTypeOfSpecJabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan updateData(OrgTypeOfSpecJabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan saveOrUpdateData(OrgTypeOfSpecJabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(OrgTypeOfSpecJabatan entity) throws Exception {
        this.orgTypeOfSpecJabatanDao.delete(entity);
    }

    @Override
    public void softDelete(OrgTypeOfSpecJabatan entity) throws Exception {
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
    public List<OrgTypeOfSpecJabatan> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecJabatan> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecJabatan> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecJabatan> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecJabatan> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecJabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecJabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrgTypeOfSpecJabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(OrgTypeOfSpecJabatan entity, List<OrgTypeOfSpecList> listTypeSpec) throws Exception {
        OrgTypeOfSpecJabatan orgTypeOfSpecJabatan;
        for (OrgTypeOfSpecList orgTypeOfSpecList : listTypeSpec) {
            Long totalDuplicates = orgTypeOfSpecJabatanDao.getTotalByPK(new OrgTypeOfSpecJabatanId(orgTypeOfSpecList.getId(), entity.getJabatan().getId()));
            if(totalDuplicates > 0){
                throw new BussinessException("global.error_duplicate_code");
            }
            orgTypeOfSpecJabatan = new OrgTypeOfSpecJabatan();
            orgTypeOfSpecJabatan.setId(new OrgTypeOfSpecJabatanId(orgTypeOfSpecList.getId(), entity.getJabatan().getId()));
            orgTypeOfSpecJabatan.setOrgTypeOfSpecList(orgTypeOfSpecListDao.getEntiyByPK(orgTypeOfSpecList.getId()));
            orgTypeOfSpecJabatan.setJabatan(jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
            orgTypeOfSpecJabatan.setCreatedBy(UserInfoUtil.getUserName());
            orgTypeOfSpecJabatan.setCreatedOn(new Date());
            this.orgTypeOfSpecJabatanDao.save(orgTypeOfSpecJabatan);
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public OrgTypeOfSpecJabatan getEntityByPK(OrgTypeOfSpecJabatanId id) throws Exception {
        return orgTypeOfSpecJabatanDao.getEntityByPK(id);
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<OrgTypeOfSpecJabatan> getAllDataByJabatanId(Long id) throws Exception {
		return orgTypeOfSpecJabatanDao.getAllDataByJabatanId(id);
	}
    
}
