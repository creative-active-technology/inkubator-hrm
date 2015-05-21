/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.RecruitSelectionTypeDao;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDao;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDetailDao;
import com.inkubator.hrm.dao.SystemLetterReferenceDao;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetailId;
import com.inkubator.hrm.service.RecruitmenSelectionSeriesDetailService;
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
@Service(value = "recruitmenSelectionSeriesDetailService")
@Lazy
public class RecruitmenSelectionSeriesDetailServiceImpl extends IServiceImpl implements RecruitmenSelectionSeriesDetailService {

    @Autowired
    private RecruitmenSelectionSeriesDetailDao recruitmenSelectionSeriesDetailDao;
    @Autowired
    private RecruitmenSelectionSeriesDao recruitmenSelectionSeriesDao;
    @Autowired
    private RecruitSelectionTypeDao recruitSelectionTypeDao;
    @Autowired
    private SystemLetterReferenceDao systemLetterReferenceDao;
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitmenSelectionSeriesDetail> getByParam(int firstResult, int maxResults, Order order) throws Exception {
        return recruitmenSelectionSeriesDetailDao.getByParam(firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalBySelectionSeriesId(Long id) throws Exception {
        return recruitmenSelectionSeriesDetailDao.getTotalBySelectionSeriesId(id);
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitmenSelectionSeriesDetail getEntiyByPK(Long id) throws Exception {
        return recruitmenSelectionSeriesDetailDao.getEntiyByPK(id);
    }

    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitmenSelectionSeriesDetail entity) throws Exception {
        Long totalDuplicates = recruitmenSelectionSeriesDetailDao.getTotalByPk(new RecruitmenSelectionSeriesDetailId(entity.getRecruitmenSelectionSeries().getId(), entity.getRecruitSelectionType().getId()));
        if(totalDuplicates > 0){
            throw new BussinessException("global.error_duplicate_code");
        }
        //get last index order
        Integer lastIndex = recruitmenSelectionSeriesDetailDao.getLastIndexBySelectionSeriesId(entity.getRecruitmenSelectionSeries().getId());
        if(lastIndex != null){
            lastIndex = lastIndex + 1;
        }else{
            lastIndex = 1;
        }
        entity.setId(new RecruitmenSelectionSeriesDetailId(entity.getRecruitmenSelectionSeries().getId(), entity.getRecruitSelectionType().getId()));
        entity.setRecruitSelectionType(recruitSelectionTypeDao.getEntiyByPK(entity.getRecruitSelectionType().getId()));
        entity.setRecruitmenSelectionSeries(recruitmenSelectionSeriesDao.getEntiyByPK(entity.getRecruitmenSelectionSeries().getId()));
        entity.setSystemLetterReferenceByAcceptLetterId(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByAcceptLetterId().getId()));
        entity.setSystemLetterReferenceByRejectLetterId(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByRejectLetterId().getId()));
        entity.setNote(entity.getNote());
        entity.setListOrder(lastIndex);
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.recruitmenSelectionSeriesDetailDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitmenSelectionSeriesDetail entity) throws Exception {
        RecruitmenSelectionSeriesDetail update = recruitmenSelectionSeriesDetailDao.getEntityByPk(new RecruitmenSelectionSeriesDetailId(entity.getRecruitmenSelectionSeries().getId(), entity.getRecruitSelectionType().getId()));
        update.setSystemLetterReferenceByAcceptLetterId(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByAcceptLetterId().getId()));
        update.setSystemLetterReferenceByRejectLetterId(systemLetterReferenceDao.getEntiyByPK(entity.getSystemLetterReferenceByRejectLetterId().getId()));
        update.setNote(entity.getNote());
        update.setUpdatedBy(new Date());
        update.setUpdatedOn(UserInfoUtil.getUserName());
        this.recruitmenSelectionSeriesDetailDao.update(update);
    }

    @Override
    public void saveOrUpdate(RecruitmenSelectionSeriesDetail enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail saveData(RecruitmenSelectionSeriesDetail entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail updateData(RecruitmenSelectionSeriesDetail entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail saveOrUpdateData(RecruitmenSelectionSeriesDetail entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(RecruitmenSelectionSeriesDetail entity) throws Exception {
        this.recruitmenSelectionSeriesDetailDao.delete(entity);
    }

    @Override
    public void softDelete(RecruitmenSelectionSeriesDetail entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalData() throws Exception {
        return recruitmenSelectionSeriesDetailDao.getTotalData();
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
    public List<RecruitmenSelectionSeriesDetail> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitmenSelectionSeriesDetail> getAllDataBySelectionSeriesId(Long id, int firstResult, int maxResults, Order order) throws Exception {
        return recruitmenSelectionSeriesDetailDao.getAllDataBySelectionSeriesId(id, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doChangerListOrder(int newGradeLevel, RecruitmenSelectionSeriesDetailId id, Long recSelectionSeriesId) throws Exception {
        RecruitmenSelectionSeriesDetail targetChage = this.recruitmenSelectionSeriesDetailDao.getByListOrderAndRecSelectionSeriesId(newGradeLevel, recSelectionSeriesId);
        targetChage.setListOrder(0);
        targetChage.setUpdatedOn(UserInfoUtil.getUserName());
        targetChage.setUpdatedBy(new Date());
        this.recruitmenSelectionSeriesDetailDao.update(targetChage);

        RecruitmenSelectionSeriesDetail newChange = this.recruitmenSelectionSeriesDetailDao.getEntityByPk(id);
        int gradeNumberOld = newChange.getListOrder();
        newChange.setListOrder(newGradeLevel);
        newChange.setUpdatedOn(UserInfoUtil.getUserName());
        newChange.setUpdatedBy(new Date());
        this.recruitmenSelectionSeriesDetailDao.update(newChange);

        RecruitmenSelectionSeriesDetail targetChageLast = this.recruitmenSelectionSeriesDetailDao.getByListOrderAndRecSelectionSeriesId(0, recSelectionSeriesId);
        targetChageLast.setListOrder(gradeNumberOld);
        targetChageLast.setUpdatedOn(UserInfoUtil.getUserName());
        targetChageLast.setUpdatedBy(new Date());
        this.recruitmenSelectionSeriesDetailDao.update(targetChageLast);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitmenSelectionSeriesDetail getEntityByPk(RecruitmenSelectionSeriesDetailId id) throws Exception {
        return recruitmenSelectionSeriesDetailDao.getEntityByPk(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitmenSelectionSeriesDetail> getEntityBySelectionTypeId(Long id) throws Exception {
        return recruitmenSelectionSeriesDetailDao.getEntityBySelectionTypeId(id);
    }
    
}
