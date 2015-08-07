/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.RecruitAdvertisementCategoryDao;
import com.inkubator.hrm.dao.RecruitAdvertisementMediaDao;
import com.inkubator.hrm.entity.RecruitAdvertisementCategory;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.service.RecruitAdvertisementMediaService;
import com.inkubator.hrm.web.search.RecruitAdvertisementMediaSearchParameter;
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
@Service(value = "recruitAdvertisementMediaService")
@Lazy
public class RecruitAdvertisementMediaServiceImpl extends IServiceImpl implements RecruitAdvertisementMediaService {

    @Autowired
    private RecruitAdvertisementMediaDao recruitAdvertisementMediaDao;
    @Autowired
    private RecruitAdvertisementCategoryDao recruitAdvertisementCategoryDao;
    

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitAdvertisementMedia> getByParam(RecruitAdvertisementMediaSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        return recruitAdvertisementMediaDao.getByParam(searchParameter, firstResult, maxResults, order);
    }
    

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(RecruitAdvertisementMediaSearchParameter searchParameter) {
        return recruitAdvertisementMediaDao.getTotalByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitAdvertisementMedia getEntityByPkWithDetail(Long id) {
        return recruitAdvertisementMediaDao.getEntityByPkWithDetail(id);
    }

    @Override
    public RecruitAdvertisementMedia getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitAdvertisementMedia getEntiyByPK(Long id) throws Exception {
        return recruitAdvertisementMediaDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitAdvertisementMedia entity) throws Exception {
        // check duplicate code
        long totalDuplicateCodes = recruitAdvertisementMediaDao.getTotalByCode(entity.getCode());
        if (totalDuplicateCodes > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }
        // check duplicate name
        long totalDuplicateNames = recruitAdvertisementMediaDao.getTotalByName(entity.getName());
        if (totalDuplicateNames > 0) {
            throw new BussinessException("global.error_duplicate_name");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setRecruitAdvertisementCategory(recruitAdvertisementCategoryDao.getEntiyByPK(entity.getRecruitAdvertisementCategory().getId()));
        entity.setCreatedOn(new Date());
        this.recruitAdvertisementMediaDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitAdvertisementMedia entity) throws Exception {
        // check duplicate code
        long totalDuplicateCodes = recruitAdvertisementMediaDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicateCodes > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }
        // check duplicate name
        long totalDuplicateNames = recruitAdvertisementMediaDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
        if (totalDuplicateNames > 0) {
            throw new BussinessException("global.error_duplicate_name");
        }
        
        RecruitAdvertisementMedia update = recruitAdvertisementMediaDao.getEntiyByPK(entity.getId());
        update.setName(entity.getName());
        update.setCode(entity.getCode());
        update.setRecruitAdvertisementCategory(recruitAdvertisementCategoryDao.getEntiyByPK(entity.getRecruitAdvertisementCategory().getId()));
        update.setMediaAddress(entity.getMediaAddress());
        update.setContactPerson(entity.getContactPerson());
        update.setDescription(entity.getDescription());
        update.setAddress(entity.getAddress());
        update.setPhone(entity.getPhone());
        update.setTypeOfMedia(entity.getTypeOfMedia());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.recruitAdvertisementMediaDao.update(update);
    }

    @Override
    public void saveOrUpdate(RecruitAdvertisementMedia enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia saveData(RecruitAdvertisementMedia entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia updateData(RecruitAdvertisementMedia entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia saveOrUpdateData(RecruitAdvertisementMedia entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(RecruitAdvertisementMedia entity) throws Exception {
        this.recruitAdvertisementMediaDao.delete(entity);
    }

    @Override
    public void softDelete(RecruitAdvertisementMedia entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitAdvertisementMedia> getAllData() throws Exception {
        return recruitAdvertisementMediaDao.getAllData();
    }

    @Override
    public List<RecruitAdvertisementMedia> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitAdvertisementMedia> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitAdvertisementMedia> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitAdvertisementMedia> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitAdvertisementMedia> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitAdvertisementMedia> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitAdvertisementMedia> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
