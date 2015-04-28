/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.RecruitSelectionTypeDao;
import com.inkubator.hrm.dao.RecruitSelectionTypeFieldDao;
import com.inkubator.hrm.entity.RecruitDynamicField;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitSelectionTypeField;
import com.inkubator.hrm.entity.RecruitSelectionTypeFieldId;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.hrm.web.search.RecruitSelectionTypeSearchParameter;
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
@Service(value = "recruitSelectionTypeService")
@Lazy
public class RecruitSelectionTypeServiceImpl extends IServiceImpl implements RecruitSelectionTypeService {

    @Autowired
    private RecruitSelectionTypeDao recruitSelectionTypeDao;
    @Autowired
    private RecruitSelectionTypeFieldDao recruitSelectionTypeFieldDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitSelectionType> getByParam(RecruitSelectionTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return recruitSelectionTypeDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(RecruitSelectionTypeSearchParameter searchParameter) throws Exception {
        return recruitSelectionTypeDao.getTotalByParam(searchParameter);
    }

    @Override
    public RecruitSelectionType getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitSelectionType getEntiyByPK(Long id) throws Exception {
        return recruitSelectionTypeDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitSelectionType entity) throws Exception {
        // check duplicate code
        long totalDuplicateCodes = recruitSelectionTypeDao.getTotalByCode(entity.getCode());
        if (totalDuplicateCodes > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }
        // check duplicate name
        long totalDuplicateNames = recruitSelectionTypeDao.getTotalByName(entity.getName());
        if (totalDuplicateNames > 0) {
            throw new BussinessException("global.error_duplicate_name");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.recruitSelectionTypeDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitSelectionType entity) throws Exception {
        // check duplicate code
        long totalDuplicateCodes = recruitSelectionTypeDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicateCodes > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }
        // check duplicate name
        long totalDuplicateNames = recruitSelectionTypeDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
        if (totalDuplicateNames > 0) {
            throw new BussinessException("global.error_duplicate_name");
        }

        RecruitSelectionType update = recruitSelectionTypeDao.getEntiyByPK(entity.getId());
        update.setName(entity.getName());
        update.setCode(entity.getCode());
        update.setCost(entity.getCost());
        update.setUseLibrary(entity.getUseLibrary());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.recruitSelectionTypeDao.update(update);
    }

    @Override
    public void saveOrUpdate(RecruitSelectionType enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType saveData(RecruitSelectionType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType updateData(RecruitSelectionType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType saveOrUpdateData(RecruitSelectionType entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitSelectionType getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(RecruitSelectionType entity) throws Exception {
        this.recruitSelectionTypeDao.delete(entity);
    }

    @Override
    public void softDelete(RecruitSelectionType entity) throws Exception {
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
    public List<RecruitSelectionType> getAllData() throws Exception {
        return recruitSelectionTypeDao.getAllData();
    }

    @Override
    public List<RecruitSelectionType> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionType> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionType> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionType> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitSelectionType> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitSelectionType entity, List<RecruitDynamicField> listRecruitDynamicField) throws Exception {
        // check duplicate code
        long totalDuplicateCodes = recruitSelectionTypeDao.getTotalByCode(entity.getCode());
        if (totalDuplicateCodes > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }
        // check duplicate name
        long totalDuplicateNames = recruitSelectionTypeDao.getTotalByName(entity.getName());
        if (totalDuplicateNames > 0) {
            throw new BussinessException("global.error_duplicate_name");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.recruitSelectionTypeDao.save(entity);

        //save table tengah
        RecruitSelectionTypeField recruitSelectionTypeField;
        for (RecruitDynamicField recruitDynamicField : listRecruitDynamicField) {
            recruitSelectionTypeField = new RecruitSelectionTypeField();
            recruitSelectionTypeField.setId(new RecruitSelectionTypeFieldId(entity.getId(), recruitDynamicField.getId()));
            recruitSelectionTypeField.setRecruitDynamicField(recruitDynamicField);
            recruitSelectionTypeField.setRecruitSelectionType(entity);
            recruitSelectionTypeField.setFieldNewLabel(recruitDynamicField.getFieldNewLabel());
            this.recruitSelectionTypeFieldDao.save(recruitSelectionTypeField);
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitSelectionType entity, List<RecruitDynamicField> listRecruitDynamicField) throws Exception {
        // check duplicate code
        long totalDuplicateCodes = recruitSelectionTypeDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicateCodes > 0) {
            throw new BussinessException("global.error_duplicate_code");
        }
        // check duplicate name
        long totalDuplicateNames = recruitSelectionTypeDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
        if (totalDuplicateNames > 0) {
            throw new BussinessException("global.error_duplicate_name");
        }
        RecruitSelectionType update = recruitSelectionTypeDao.getEntiyByPK(entity.getId());
        update.setCode(entity.getCode());
        update.setName(entity.getName());
        update.setCost(entity.getCost());
        update.setUseLibrary(entity.getUseLibrary());
        this.recruitSelectionTypeDao.update(update);

        //save table tengah
        this.recruitSelectionTypeFieldDao.deleteAllDataBySelectionTypeId(entity.getId());
        RecruitSelectionTypeField recruitSelectionTypeField;
        for (RecruitDynamicField recruitDynamicField : listRecruitDynamicField) {
            recruitSelectionTypeField = new RecruitSelectionTypeField();
            recruitSelectionTypeField.setId(new RecruitSelectionTypeFieldId(entity.getId(), recruitDynamicField.getId()));
            recruitSelectionTypeField.setRecruitDynamicField(recruitDynamicField);
            recruitSelectionTypeField.setRecruitSelectionType(update);
            recruitSelectionTypeField.setFieldNewLabel(recruitDynamicField.getFieldNewLabel());
            this.recruitSelectionTypeFieldDao.save(recruitSelectionTypeField);
        }
    }

}
