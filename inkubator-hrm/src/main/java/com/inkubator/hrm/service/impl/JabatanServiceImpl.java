/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.CostCenterDao;
import com.inkubator.hrm.dao.DepartmentDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.UnitKerjaDao;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
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
 * @author Deni Husni FR
 */
@Service(value = "jabatanService")
@Lazy
public class JabatanServiceImpl extends IServiceImpl implements JabatanService {

    @Autowired
    private JabatanDao jabatanDao;
    @Autowired
    private CostCenterDao costCenterDao;
    @Autowired
    private UnitKerjaDao unitKerjaDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private GolonganJabatanDao golonganJabatanDao;

    @Override
    public Jabatan getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Jabatan getEntiyByPK(Long id) throws Exception {
        return this.jabatanDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Jabatan entity) throws Exception {
        long totalDuplicate = this.jabatanDao.getTotalByCode(entity.getCode());
        if (totalDuplicate > 0) {
            throw new BussinessException("jabatan.jabatan_duplicate_code");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCostCenter(this.costCenterDao.getEntiyByPK(entity.getCostCenter().getId()));
        entity.setDepartment(this.departmentDao.getEntiyByPK(entity.getDepartment().getId()));
        entity.setGolonganJabatan(this.golonganJabatanDao.getEntiyByPK(entity.getGolonganJabatan().getId()));
        if (entity.getJabatan() != null) {
            entity.setJabatan(this.jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
        }
        entity.setUnitKerja(this.unitKerjaDao.getEntiyByPK(entity.getUnitKerja().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.jabatanDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Jabatan entity) throws Exception {
        long totalDuplicate = this.jabatanDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicate > 0) {
            throw new BussinessException("jabatan.jabatan_duplicate_code");
        }
        Jabatan jabatan = this.jabatanDao.getEntiyByPK(entity.getId());
        jabatan.setCode(entity.getCode());
        jabatan.setCostCenter(this.costCenterDao.getEntiyByPK(entity.getCostCenter().getId()));
        jabatan.setDepartment(this.departmentDao.getEntiyByPK(entity.getDepartment().getId()));
        jabatan.setGolonganJabatan(this.golonganJabatanDao.getEntiyByPK(entity.getGolonganJabatan().getId()));
        if (jabatan.getJabatan() != null) {
            jabatan.setJabatan(this.jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
        }

        jabatan.setUnitKerja(this.unitKerjaDao.getEntiyByPK(entity.getUnitKerja().getId()));
        jabatan.setLevelJabatan(entity.getLevelJabatan());
        jabatan.setName(entity.getName());
        jabatan.setTujuanJabatan(entity.getTujuanJabatan());
        jabatan.setUpdatedBy(UserInfoUtil.getUserName());
        jabatan.setUpdatedOn(new Date());
        this.jabatanDao.update(jabatan);

    }

    @Override
    public void saveOrUpdate(Jabatan enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan saveData(Jabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan updateData(Jabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan saveOrUpdateData(Jabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Jabatan entity) throws Exception {
        this.jabatanDao.delete(entity);
    }

    @Override
    public void softDelete(Jabatan entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<Jabatan> getAllData() throws Exception {
        return jabatanDao.getAllData();
    }

    @Override
    public List<Jabatan> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Jabatan> getByParam(JabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return jabatanDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalJabatanByParam(JabatanSearchParameter searchParameter) throws Exception {
        return this.jabatanDao.getTotalJabatanByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Jabatan getJabatanByLevelOne(Integer level) throws Exception {
        return this.jabatanDao.getJabatanByLevelOne(level);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Jabatan> getJabatanByParentCode(String parentCode) throws Exception {
        return this.jabatanDao.getJabatanByParentCode(parentCode);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public Jabatan getJabatanByIdWithDetail(Long id) throws Exception {
        Jabatan jabatan = jabatanDao.getJabatanByIdWithDetail(id);
        jabatan.getGolonganJabatan().getPangkat().getPangkatName();
        return jabatan;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Jabatan> getJabatansByLevel(Integer level) throws Exception {
        return this.jabatanDao.getJabatansByLevel(level);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Jabatan getByIdWithJobDeskripsi(long id) throws Exception {
        Jabatan jabatan = jabatanDao.getByIdWithJobDeskripsi(id);
        jabatan.getGolonganJabatan().getPangkat().getPangkatName();
        return jabatan;
    }

}