package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.dao.TempAttendanceRealizationDao;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;
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
import com.inkubator.hrm.web.model.RealizationAttendanceModel;

/**
 *
 * @author WebGenX
 */
@Service(value = "tempAttendanceRealizationService")
@Lazy
public class TempAttendanceRealizationServiceImpl extends IServiceImpl implements TempAttendanceRealizationService {

    @Autowired
    private TempAttendanceRealizationDao tempAttendanceRealizationDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(TempAttendanceRealization tempAttendanceRealization) throws Exception {
        tempAttendanceRealizationDao.delete(tempAttendanceRealization);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(TempAttendanceRealization tempAttendanceRealization) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = tempAttendanceRealizationDao.getTotalByCode(tempAttendanceRealization.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("tempAttendanceRealization.error_duplicate_code")
//}
        tempAttendanceRealization.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        tempAttendanceRealization.setCreatedBy(UserInfoUtil.getUserName());
        tempAttendanceRealization.setCreatedOn(new Date());
        tempAttendanceRealizationDao.save(tempAttendanceRealization);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(TempAttendanceRealization tempAttendanceRealization) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = tempAttendanceRealizationDao.getTotalByCodeAndNotId(tempAttendanceRealization.getCode(),tempAttendanceRealization.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("tempAttendanceRealization.error_duplicate_code")
//}
        TempAttendanceRealization tempAttendanceRealization1 = tempAttendanceRealizationDao.getEntiyByPK(tempAttendanceRealization.getId());
//        tempAttendanceRealization1.setUpdatedBy(UserInfoUtil.getUserName());
//        tempAttendanceRealization1.setUpdatedOn(new Date());
        tempAttendanceRealization1.setWtGroupWorking(tempAttendanceRealization.getWtGroupWorking());
        tempAttendanceRealization1.setLeave(tempAttendanceRealization.getLeave());
        tempAttendanceRealization1.setEmpData(tempAttendanceRealization.getEmpData());
        tempAttendanceRealization1.setDuty(tempAttendanceRealization.getDuty());
        tempAttendanceRealization1.setAttendanceDaysPresent(tempAttendanceRealization.getAttendanceDaysPresent());
        tempAttendanceRealization1.setPermit(tempAttendanceRealization.getPermit());
        tempAttendanceRealization1.setAttendanceDaysSchedule(tempAttendanceRealization.getAttendanceDaysSchedule());
        tempAttendanceRealization1.setSick(tempAttendanceRealization.getSick());
        tempAttendanceRealizationDao.update(tempAttendanceRealization1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public TempAttendanceRealization getEntiyByPK(Long id) {
        return this.tempAttendanceRealizationDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<TempAttendanceRealization> getByParam(TempAttendanceRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.tempAttendanceRealizationDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalTempAttendanceRealizationByParam(TempAttendanceRealizationSearchParameter searchParameter) throws Exception {
        return this.tempAttendanceRealizationDao.getTotalTempAttendanceRealizationByParam(searchParameter);
    }

    @Override
    public TempAttendanceRealization getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(TempAttendanceRealization enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization saveData(TempAttendanceRealization entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization updateData(TempAttendanceRealization entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization saveOrUpdateData(TempAttendanceRealization entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(TempAttendanceRealization entity) throws Exception {
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
    public List<TempAttendanceRealization> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public RealizationAttendanceModel getStatisticEmpAttendaceRealization() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
