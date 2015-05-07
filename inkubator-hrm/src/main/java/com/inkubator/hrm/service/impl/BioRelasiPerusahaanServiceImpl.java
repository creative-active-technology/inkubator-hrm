/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioRelasiPerusahaanDao;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.entity.BioRelasiPerusahaan;
import com.inkubator.hrm.service.BioRelasiPerusahaanService;
import com.inkubator.hrm.web.search.BioRelasiPerusahaanSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;
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
@Service(value = "bioRelasiPerusahaanService")
@Lazy
public class BioRelasiPerusahaanServiceImpl extends IServiceImpl implements BioRelasiPerusahaanService {

    @Autowired
    private BioRelasiPerusahaanDao bioRelasiPerusahaanDao;
    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private CityDao cityDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioRelasiPerusahaan> getByParam(BioRelasiPerusahaanSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return bioRelasiPerusahaanDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(BioRelasiPerusahaanSearchParameter searchParameter) throws Exception {
        return bioRelasiPerusahaanDao.getTotalByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<BioRelasiPerusahaan> getAllDataByBioDataId(Long bioDataId) throws Exception {
        return bioRelasiPerusahaanDao.getAllDataByBioDataId(bioDataId);
    }

    @Override
    public BioRelasiPerusahaan getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioRelasiPerusahaan getEntiyByPK(Long id) throws Exception {
        return bioRelasiPerusahaanDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(BioRelasiPerusahaan entity) throws Exception {

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioRelasiPerusahaan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(BioRelasiPerusahaan enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan saveData(BioRelasiPerusahaan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan updateData(BioRelasiPerusahaan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan saveOrUpdateData(BioRelasiPerusahaan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(BioRelasiPerusahaan entity) throws Exception {
        this.bioRelasiPerusahaanDao.delete(entity);
    }

    @Override
    public void softDelete(BioRelasiPerusahaan entity) throws Exception {
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
    public List<BioRelasiPerusahaan> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioRelasiPerusahaan> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioRelasiPerusahaan> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioRelasiPerusahaan> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioRelasiPerusahaan> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioRelasiPerusahaan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioRelasiPerusahaan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioRelasiPerusahaan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(BioRelasiPerusahaan entity, UploadedFile fileUpload) throws Exception {
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        entity.setCity(cityDao.getEntiyByPK(entity.getCity().getId()));
        entity.setRelasiAddress(entity.getRelasiAddress());
        entity.setRelasiCompany(entity.getRelasiCompany());
        entity.setRelasiEmail(entity.getRelasiEmail());
        entity.setRelasiJabatan(entity.getRelasiJabatan());
        entity.setRelasiMobilePhone(entity.getRelasiMobilePhone());
        entity.setRelasiName(entity.getRelasiName());
        entity.setRelasiPhoneNumber(entity.getRelasiPhoneNumber());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        if (fileUpload != null) {
            InputStream inputStream = null;
            byte[] buffer = null;
            inputStream = fileUpload.getInputstream();
            buffer = IOUtils.toByteArray(inputStream);
            entity.setRelasiAttachment(buffer);
        }
        this.bioRelasiPerusahaanDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioRelasiPerusahaan entity, UploadedFile fileUpload) throws Exception {
        BioRelasiPerusahaan update = bioRelasiPerusahaanDao.getEntiyByPK(entity.getId());
        update.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        update.setCity(cityDao.getEntiyByPK(entity.getCity().getId()));
        update.setRelasiAddress(entity.getRelasiAddress());
        update.setRelasiCompany(entity.getRelasiCompany());
        update.setRelasiEmail(entity.getRelasiEmail());
        update.setRelasiJabatan(entity.getRelasiJabatan());
        update.setRelasiMobilePhone(entity.getRelasiMobilePhone());
        update.setRelasiName(entity.getRelasiName());
        update.setRelasiPhoneNumber(entity.getRelasiPhoneNumber());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setPostalCode(entity.getPostalCode());
        update.setUpdatedOn(new Date());
        if (fileUpload != null) {
            InputStream inputStream = null;
            byte[] buffer = null;
            inputStream = fileUpload.getInputstream();
            buffer = IOUtils.toByteArray(inputStream);
            update.setRelasiAttachment(buffer);
            update.setRelasiAttachmentName(entity.getRelasiAttachmentName());
        }
        this.bioRelasiPerusahaanDao.update(update);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioRelasiPerusahaan getEntityByPkWithDetail(Long id) throws Exception {
        return bioRelasiPerusahaanDao.getEntityByPkWithDetail(id);
    }

}
