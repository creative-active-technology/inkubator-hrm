package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
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
@Service(value = "recruitHireApplyService")
@Lazy
public class RecruitHireApplyServiceImpl extends IServiceImpl implements RecruitHireApplyService {

    @Autowired
    private RecruitHireApplyDao recruitHireApplyDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(RecruitHireApply recruitHireApply) throws Exception {
        recruitHireApplyDao.delete(recruitHireApply);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(RecruitHireApply recruitHireApply) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = recruitHireApplyDao.getTotalByCode(recruitHireApply.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("recruitHireApply.error_duplicate_code")
//}
        recruitHireApply.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        recruitHireApply.setCreatedBy(UserInfoUtil.getUserName());
        recruitHireApply.setCreatedOn(new Date());
        recruitHireApplyDao.save(recruitHireApply);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(RecruitHireApply recruitHireApply) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = recruitHireApplyDao.getTotalByCodeAndNotId(recruitHireApply.getCode(),recruitHireApply.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("recruitHireApply.error_duplicate_code")
//}
        RecruitHireApply recruitHireApply1 = recruitHireApplyDao.getEntiyByPK(recruitHireApply.getId());
        recruitHireApply1.setUpdatedBy(UserInfoUtil.getUserName());
        recruitHireApply1.setUpdatedOn(new Date());
        recruitHireApply1.setEmployeeType(recruitHireApply.getEmployeeType());
        recruitHireApply1.setSalaryMax(recruitHireApply.getSalaryMax());
        recruitHireApply1.setReason(recruitHireApply.getReason());
        recruitHireApply1.setProposeDate(recruitHireApply.getProposeDate());
        recruitHireApply1.setReqHireCode(recruitHireApply.getReqHireCode());
        recruitHireApply1.setAgeMax(recruitHireApply.getAgeMax());
        recruitHireApply1.setRecruitMppPeriod(recruitHireApply.getRecruitMppPeriod());
        recruitHireApply1.setMaritalStatus(recruitHireApply.getMaritalStatus());
        recruitHireApply1.setGpaMin(recruitHireApply.getGpaMin());
        recruitHireApply1.setGpaMax(recruitHireApply.getGpaMax());
        recruitHireApply1.setCurrency(recruitHireApply.getCurrency());
        recruitHireApply1.setJabatan(recruitHireApply.getJabatan());
        recruitHireApply1.setAgeMin(recruitHireApply.getAgeMin());
        recruitHireApply1.setEfectiveDate(recruitHireApply.getEfectiveDate());
        recruitHireApply1.setCandidateCountRequest(recruitHireApply.getCandidateCountRequest());
        recruitHireApply1.setGender(recruitHireApply.getGender());
        recruitHireApply1.setSalaryMin(recruitHireApply.getSalaryMin());
        recruitHireApplyDao.update(recruitHireApply1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public RecruitHireApply getEntiyByPK(Long id) {
        return this.recruitHireApplyDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.recruitHireApplyDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter searchParameter) throws Exception {
        return this.recruitHireApplyDao.getTotalRecruitHireApplyByParam(searchParameter);
    }

    @Override
    public RecruitHireApply getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(RecruitHireApply enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply saveData(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply updateData(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply saveOrUpdateData(RecruitHireApply entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecruitHireApply getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(RecruitHireApply entity) throws Exception {
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
    public List<RecruitHireApply> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecruitHireApply> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
