/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.PangkatDao;
import com.inkubator.hrm.dao.UnregGoljabDao;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Pangkat;
import com.inkubator.hrm.entity.UnregGoljab;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.web.search.GolonganJabatanSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.ArrayList;
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
 * @author Deni Husni FR,rizkykojek
 */
@Service(value = "golonganJabatanService")
@Lazy
public class GolonganJabatanServiceImpl extends IServiceImpl implements GolonganJabatanService {

    @Autowired
    private GolonganJabatanDao golJabatanDao;
    @Autowired
    private PangkatDao pangkatDao;
    @Autowired
    private UnregGoljabDao unregGoljabDao;

    @Override
    public GolonganJabatan getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public GolonganJabatan getEntiyByPK(Long id) throws Exception {
        return golJabatanDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public GolonganJabatan getEntityByPkFetchAttendPangkat(Long id) throws Exception {
        return golJabatanDao.getEntityByPkFetchPangkat(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(GolonganJabatan entity) throws Exception {
        // check duplicate code
        Long totalDuplicates = golJabatanDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("functiongroup.error_duplicate_code");
        }

        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        Pangkat pangkat = pangkatDao.getEntiyByPK(entity.getPangkat().getId());
        entity.setPangkat(pangkat);
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        golJabatanDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(GolonganJabatan entity) throws Exception {
        // check duplicate code
        Long totalDuplicates = golJabatanDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("functiongroup.error_duplicate_code");
        }

        GolonganJabatan golonganJabatan = golJabatanDao.getEntiyByPK(entity.getId());
        golonganJabatan.setCode(entity.getCode());
        Pangkat pangkat = pangkatDao.getEntiyByPK(entity.getPangkat().getId());
        golonganJabatan.setPangkat(pangkat);
        golonganJabatan.setOvertime(entity.getOvertime());
        golonganJabatan.setUpdatedBy(UserInfoUtil.getUserName());
        golonganJabatan.setUpdatedOn(new Date());
        golJabatanDao.update(golonganJabatan);
    }

    @Override
    public void saveOrUpdate(GolonganJabatan enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan saveData(GolonganJabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan updateData(GolonganJabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan saveOrUpdateData(GolonganJabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GolonganJabatan getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(GolonganJabatan entity) throws Exception {
        golJabatanDao.delete(entity);
    }

    @Override
    public void softDelete(GolonganJabatan entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<GolonganJabatan> getAllData() throws Exception {
        return golJabatanDao.getAllData();
    }

    @Override
    public List<GolonganJabatan> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GolonganJabatan> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GolonganJabatan> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GolonganJabatan> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GolonganJabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GolonganJabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GolonganJabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<GolonganJabatan> getByParam(GolonganJabatanSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return golJabatanDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(GolonganJabatanSearchParameter parameter) throws Exception {
        return golJabatanDao.getTotalByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<GolonganJabatan> getAllWithDetail() throws Exception {
        return golJabatanDao.getAllWithDetail();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public GolonganJabatan getEntityByUnregSalaryIdWithDetail(Long unregSalaryId) throws Exception {
        GolonganJabatan golonganJabatan = new GolonganJabatan();
        List<GolonganJabatan> golonganJabatans = new ArrayList<>();
        for (UnregGoljab unregGoljab : this.unregGoljabDao.getAllDataByUnregSalaryId(unregSalaryId)) {
            golonganJabatans.add(unregGoljab.getGolonganJabatan());
        }
     
        golonganJabatan.setListGolonganJabatans(golonganJabatans);
        return golonganJabatan;
    }

}
