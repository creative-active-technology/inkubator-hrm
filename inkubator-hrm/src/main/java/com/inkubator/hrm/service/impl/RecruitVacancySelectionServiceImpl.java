/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.dao.RecruitSelectionTypeDao;
import com.inkubator.hrm.dao.RecruitVacancySelectionDao;
import com.inkubator.hrm.dao.RecruitVacancySelectionDetailDao;
import com.inkubator.hrm.dao.RecruitVacancySelectionDetailPicDao;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitVacancySelection;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetail;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetailPic;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetailPicId;
import com.inkubator.hrm.service.RecruitVacancySelectionService;
import com.inkubator.hrm.web.model.RecruitVacancySelectionDetailModel;
import com.inkubator.hrm.web.model.RecruitVacancySelectionModel;
import com.inkubator.hrm.web.search.RecruitVacancySelectionSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author Deni
 */
@Service(value = "recruitVacancySelectionService")
@Lazy
public class RecruitVacancySelectionServiceImpl extends IServiceImpl implements RecruitVacancySelectionService {

    @Autowired
    private RecruitVacancySelectionDao recruitVacancySelectionDao;
    @Autowired
    private RecruitHireApplyDao recruitHireApplyDao;
    @Autowired
    private RecruitmenSelectionSeriesDao recruitmenSelectionSeriesDao;
    @Autowired
    private RecruitSelectionTypeDao recruitSelectionTypeDao;
    @Autowired
    private RecruitVacancySelectionDetailDao recruitVacancySelectionDetailDao;
    @Autowired
    private RecruitVacancySelectionDetailPicDao recruitVacancySelectionDetailPicDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitVacancySelection> getByParam(RecruitVacancySelectionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return recruitVacancySelectionDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public Long getTotalByParam(RecruitVacancySelectionSearchParameter searchParameter) throws Exception {
        return recruitVacancySelectionDao.getTotalByParam(searchParameter);
    }

    @Override
    public RecruitVacancySelection getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public RecruitVacancySelection getEntiyByPK(Long id) throws Exception {
        return recruitVacancySelectionDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitVacancySelection entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitVacancySelection entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(RecruitVacancySelection enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection saveData(RecruitVacancySelection entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection updateData(RecruitVacancySelection entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection saveOrUpdateData(RecruitVacancySelection entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitVacancySelection getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(RecruitVacancySelection entity) throws Exception {
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
    public List<RecruitVacancySelection> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitVacancySelection> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitVacancySelection> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitVacancySelection> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitVacancySelection> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitVacancySelection> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitVacancySelection> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitVacancySelection> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveRecruitVacancySelectionSeries(RecruitVacancySelectionModel modelEntity) throws Exception {
        //save entity form RecruitVacancySelection
        RecruitVacancySelection entity = new RecruitVacancySelection();
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCode(modelEntity.getCode());
        entity.setRecruitHireApply(recruitHireApplyDao.getEntiyByPK(modelEntity.getRecruitHireApplyId()));
        entity.setRecruitmenSelectionSeries(recruitmenSelectionSeriesDao.getEntiyByPK(modelEntity.getRecruitSelectionSeriesId()));
        entity.setRecruitVacancySelectionDate(modelEntity.getRecruitVacancySelectionDate());
        entity.setExtraBudget(modelEntity.getExtraBudget());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.recruitVacancySelectionDao.save(entity);
        //save entity form RecruitVacancySelectionDetail
        RecruitVacancySelectionDetail entityDetail;
        RecruitVacancySelectionDetailPic recruitVacancySelectionDetailPic;
        RecruitSelectionType recruitSelectionType;
        for (RecruitVacancySelectionDetailModel data : modelEntity.getListVacancySelectionDetail()) {
            recruitSelectionType = recruitSelectionTypeDao.getEntityByName(data.getRecruitSelectionTypeName());
            entityDetail = new RecruitVacancySelectionDetail();
            entityDetail.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            entityDetail.setRecruitVacancySelection(entity);
            entityDetail.setRecruitSelectionType(recruitSelectionType);
            entityDetail.setStartDate(data.getStartDate());
            entityDetail.setEndDate(data.getEndDate());
            entityDetail.setTime(data.getTime());
            entityDetail.setPlace(data.getPlace());
            entityDetail.setBasicCost(data.getBasicCost());
            entityDetail.setIndividualCost(data.getIndividualCost());
            this.recruitVacancySelectionDetailDao.save(entityDetail);

            //save entity form RecruitVacancySelectionDetail for List Employee
            for (EmpData empData : data.getListEmpData()) {
                recruitVacancySelectionDetailPic = new RecruitVacancySelectionDetailPic();
                recruitVacancySelectionDetailPic.setId(new RecruitVacancySelectionDetailPicId(entityDetail.getId(), empData.getId()));
                recruitVacancySelectionDetailPic.setRecruitVacancySelectionDetail(entityDetail);
                recruitVacancySelectionDetailPic.setEmpData(empData);
                recruitVacancySelectionDetailPic.setCreatedBy(UserInfoUtil.getUserName());
                recruitVacancySelectionDetailPic.setCreatedOn(new Date());
                this.recruitVacancySelectionDetailPicDao.save(recruitVacancySelectionDetailPic);
            }
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateRecruitVacancySelectionSeries(RecruitVacancySelectionModel modelEntity) throws Exception {
        //save entity form RecruitVacancySelection
        RecruitVacancySelection updateRecruitVacancySelection = recruitVacancySelectionDao.getEntityByPkWithDetail(modelEntity.getId());
        updateRecruitVacancySelection.setCode(modelEntity.getCode());
        updateRecruitVacancySelection.setRecruitHireApply(recruitHireApplyDao.getEntiyByPK(modelEntity.getRecruitHireApplyId()));
        updateRecruitVacancySelection.setRecruitmenSelectionSeries(recruitmenSelectionSeriesDao.getEntiyByPK(modelEntity.getRecruitSelectionSeriesId()));
        updateRecruitVacancySelection.setRecruitVacancySelectionDate(modelEntity.getRecruitVacancySelectionDate());
        updateRecruitVacancySelection.setExtraBudget(modelEntity.getExtraBudget());
        updateRecruitVacancySelection.setCreatedBy(UserInfoUtil.getUserName());
        updateRecruitVacancySelection.setCreatedOn(new Date());
		//this.recruitVacancySelectionDao.update(updateRecruitVacancySelection);
        //save entity form RecruitVacancySelectionDetail
        RecruitVacancySelectionDetail updateDetail;
        RecruitVacancySelectionDetailPic recruitVacancySelectionDetailPic;
        RecruitSelectionType recruitSelectionType;
        if (modelEntity.getIsUpdatedNewTypeSelection()) {

            //delete detailPic data yang lama
            List<RecruitVacancySelectionDetail> listOldData = recruitVacancySelectionDetailDao.getAllDataByRecruitVacancySelection(modelEntity.getId());
            for (RecruitVacancySelectionDetail oldData : listOldData) {

                //delete all existing data recruitVacancySelectionDetailPic
                recruitVacancySelectionDetailPicDao.deleteAllDataByVacancySelectionDetailId(oldData.getId());
                recruitVacancySelectionDetailDao.delete(oldData);
            }
			//save new updated data
            //save entity form RecruitVacancySelectionDetail
            RecruitVacancySelectionDetail entityDetail;
            for (RecruitVacancySelectionDetailModel data : modelEntity.getListVacancySelectionDetail()) {

                recruitSelectionType = recruitSelectionTypeDao.getEntityByName(data.getRecruitSelectionTypeName());
                entityDetail = new RecruitVacancySelectionDetail();
                entityDetail.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
                entityDetail.setRecruitVacancySelection(updateRecruitVacancySelection);
                entityDetail.setRecruitSelectionType(recruitSelectionType);
                entityDetail.setStartDate(data.getStartDate());
                entityDetail.setEndDate(data.getEndDate());
                entityDetail.setTime(data.getTime());
                entityDetail.setPlace(data.getPlace());
                entityDetail.setBasicCost(data.getBasicCost());
                entityDetail.setIndividualCost(data.getIndividualCost());
                this.recruitVacancySelectionDetailDao.save(entityDetail);

                //save entity form RecruitVacancySelectionDetail for List Employee
                for (EmpData empData : data.getListEmpData()) {
                    recruitVacancySelectionDetailPic = new RecruitVacancySelectionDetailPic();
                    recruitVacancySelectionDetailPic.setId(new RecruitVacancySelectionDetailPicId(entityDetail.getId(), empData.getId()));
                    recruitVacancySelectionDetailPic.setRecruitVacancySelectionDetail(entityDetail);
                    recruitVacancySelectionDetailPic.setEmpData(empData);
                    recruitVacancySelectionDetailPic.setCreatedBy(UserInfoUtil.getUserName());
                    recruitVacancySelectionDetailPic.setCreatedOn(new Date());
                    this.recruitVacancySelectionDetailPicDao.save(recruitVacancySelectionDetailPic);
                }
            }
        } else {

            for (RecruitVacancySelectionDetailModel data : modelEntity.getListVacancySelectionDetail()) {

                recruitSelectionType = recruitSelectionTypeDao.getEntityByName(data.getRecruitSelectionTypeName());
                updateDetail = recruitVacancySelectionDetailDao.getEntiyByPK(data.getId());
                updateDetail.setRecruitVacancySelection(updateRecruitVacancySelection);
                updateDetail.setRecruitSelectionType(recruitSelectionType);
                updateDetail.setStartDate(data.getStartDate());
                updateDetail.setEndDate(data.getEndDate());
                updateDetail.setTime(data.getTime());
                updateDetail.setPlace(data.getPlace());
                updateDetail.setBasicCost(data.getBasicCost());
                updateDetail.setIndividualCost(data.getIndividualCost());
                this.recruitVacancySelectionDetailDao.update(updateDetail);

                //delete all existing data recruitVacancySelectionDetailPic
                recruitVacancySelectionDetailPicDao.deleteAllDataByVacancySelectionDetailId(updateDetail.getId());

                //then save all new data recruitVacancySelectionDetailPic
                for (EmpData empData : data.getListEmpData()) {
                    recruitVacancySelectionDetailPic = new RecruitVacancySelectionDetailPic();
                    recruitVacancySelectionDetailPic.setId(new RecruitVacancySelectionDetailPicId(updateDetail.getId(), empData.getId()));
                    recruitVacancySelectionDetailPic.setRecruitVacancySelectionDetail(updateDetail);
                    recruitVacancySelectionDetailPic.setEmpData(empData);
                    recruitVacancySelectionDetailPic.setCreatedBy(UserInfoUtil.getUserName());
                    recruitVacancySelectionDetailPic.setCreatedOn(new Date());
                    this.recruitVacancySelectionDetailPicDao.save(recruitVacancySelectionDetailPic);
                }
            }
        }

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(RecruitVacancySelection entity) throws Exception {
        //delete detailPic data yang lama
        List<RecruitVacancySelectionDetail> listOldData = recruitVacancySelectionDetailDao.getAllDataByRecruitVacancySelection(entity.getId());
        for (RecruitVacancySelectionDetail oldData : listOldData) {

            //delete all existing data recruitVacancySelectionDetailPic
            recruitVacancySelectionDetailPicDao.deleteAllDataByVacancySelectionDetailId(oldData.getId());
            recruitVacancySelectionDetailDao.delete(oldData);
        }
        this.recruitVacancySelectionDao.delete(entity);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public RecruitVacancySelection getEntityByPkWithDetail(Long id) throws Exception {
        return recruitVacancySelectionDao.getEntityByPkWithDetail(id);
    }

}
