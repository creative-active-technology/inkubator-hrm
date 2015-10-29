/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.RecruitLetterComChannelDao;
import com.inkubator.hrm.dao.RecruitLetterSelectionDao;
import com.inkubator.hrm.dao.RecruitLettersDao;
import com.inkubator.hrm.entity.RecruitCommChannels;
import com.inkubator.hrm.entity.RecruitLetterComChannel;
import com.inkubator.hrm.entity.RecruitLetterSelection;
import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.service.RecruitLettersService;
import com.inkubator.hrm.web.search.RecrutimentLetterSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author denifahri
 */
@Service(value = "recruitLettersService")
@Lazy
public class RecruitLettersServiceImpl extends IServiceImpl implements RecruitLettersService {

    @Autowired
    private RecruitLettersDao recruitLettersDao;
    @Autowired
    private RecruitLetterSelectionDao recruitLetterSelectionDao;
    @Autowired
    private RecruitLetterComChannelDao recruitLetterComChannelDao;

    @Override
    public RecruitLetters getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitLetters entity) throws Exception {
        long totalDuplicates = recruitLettersDao.getTotalByCode(entity.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("race.error_duplicate_race_code");
        }
        if (entity.getIsActive()) {
            List<RecruitLetters> dataToUpdateIsActive = recruitLettersDao.getAllWithSpecificLetterType(entity.getLeterTypeId());
            for (RecruitLetters recruitLetters : dataToUpdateIsActive) {
                recruitLetters.setIsActive(Boolean.FALSE);
                recruitLettersDao.update(recruitLetters);
            }
        }

        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        recruitLettersDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitLetters entity) throws Exception {
        long totalDuplicates = recruitLettersDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("race.error_duplicate_race_code");
        }
        if (entity.getIsActive()) {
            List<RecruitLetters> dataToUpdateIsActive = recruitLettersDao.getAllWithSpecificLetterType(entity.getLeterTypeId());
            for (RecruitLetters recruitLetters : dataToUpdateIsActive) {
                recruitLetters.setIsActive(Boolean.FALSE);
                recruitLettersDao.update(recruitLetters);
            }
        }
        RecruitLetters recruitLetters = recruitLettersDao.getEntiyByPK(entity.getId());
        recruitLetters.getRecruitLetterComChannels().clear();
        recruitLetters.getRecruitLetterSelections().clear();
        recruitLetters.setCode(entity.getCode());
        recruitLetters.setContentHtml(entity.getContentHtml());
        recruitLetters.setEmpData(entity.getEmpData());
        recruitLetters.setExpiryDays(entity.getExpiryDays());
        recruitLetters.setFormatNumber(entity.getFormatNumber());
        recruitLetters.setIsActive(entity.getIsActive());
        recruitLetters.setLeterTypeId(entity.getLeterTypeId());
        recruitLetters.setSmsNotif(entity.getSmsNotif());
        recruitLetters.setUpdatedBy(UserInfoUtil.getUserName());
        recruitLetters.setUpdatedOn(new Date());
        recruitLettersDao.saveAndMerge(recruitLetters);
        Set<RecruitLetterSelection> recruitLetterSelections = entity.getRecruitLetterSelections();
        for (RecruitLetterSelection recruitLetterSelection : recruitLetterSelections) {
            recruitLetterSelection.setRecruitLetters(recruitLetters);
            recruitLetterSelectionDao.save(recruitLetterSelection);
        }
        Set<RecruitLetterComChannel> recruitLetterComChannels = entity.getRecruitLetterComChannels();
        for (RecruitLetterComChannel recruitLetterComChannel : recruitLetterComChannels) {
            recruitLetterComChannel.setRecruitLetters(recruitLetters);
            recruitLetterComChannelDao.save(recruitLetterComChannel);
        }

    }

    @Override
    public void saveOrUpdate(RecruitLetters enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters saveData(RecruitLetters entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters updateData(RecruitLetters entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters saveOrUpdateData(RecruitLetters entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitLetters getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false , isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(RecruitLetters entity) throws Exception {
       this.recruitLettersDao.delete(entity);
    }

    @Override
    public void softDelete(RecruitLetters entity) throws Exception {
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
    public List<RecruitLetters> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitLetters> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitLetters> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitLetters> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitLetters> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitLetters> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitLetters> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitLetters> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitLetters getByPkWithDetail(long id) throws Exception {
        RecruitLetters recruitLetters = recruitLettersDao.getByPkWithDetail(id);
        List<RecruitSelectionType> recruitSelectionTypes = new ArrayList<>();
        List<RecruitCommChannels> recruitCommChannelss = new ArrayList<>();
        Set<RecruitLetterComChannel> recruitLetterComChannels = recruitLetters.getRecruitLetterComChannels();
        for (RecruitLetterComChannel recruitLetterComChannel : recruitLetterComChannels) {
            recruitCommChannelss.add(recruitLetterComChannel.getRecruitCommChannels());
        }
        Set<RecruitLetterSelection> recruitLetterSelections = recruitLetters.getRecruitLetterSelections();
        for (RecruitLetterSelection recruitLetterSelection : recruitLetterSelections) {
            recruitSelectionTypes.add(recruitLetterSelection.getRecruitSelectionType());
        }
        recruitLetters.setRecruitSelectionTypes(recruitSelectionTypes);
        recruitLetters.setRecruitCommChannelss(recruitCommChannelss);
        return recruitLetters;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ, timeout = 50)
    public List<RecruitLetters> getByParam(RecrutimentLetterSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return recruitLettersDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, timeout = 30)
    public Long getTotalByParam(RecrutimentLetterSearchParameter parameter) throws Exception {
        return recruitLettersDao.getTotalByParam(parameter);
    }

}
