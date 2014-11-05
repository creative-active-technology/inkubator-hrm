/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.CheckInAttendanceDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.CheckInAttendance;
import com.inkubator.hrm.service.CheckInAttendanceService;
import com.inkubator.hrm.web.search.CheckInAttendanceSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import org.hibernate.criterion.Order;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
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
@Service(value = "checkInAttendanceService")
@Lazy
public class CheckInAttendanceServiceImpl extends IServiceImpl implements CheckInAttendanceService {

    @Autowired
    private CheckInAttendanceDao checkInAttendanceDao;
    @Autowired
    private EmpDataDao empDataDao;

    @Override
    public CheckInAttendance getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void save(CheckInAttendance entity) throws Exception {
        entity.setCheckInTime(new Date());
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setEmpData(empDataDao.getEntiyByPK(entity.getEmpData().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.checkInAttendanceDao.save(entity);
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        String waktuCheckIn = new SimpleDateFormat("EEEE, dd-MMMM-yyyy hh:mm:ss").format(entity.getCheckInTime());
        String infoMessages = entity.getEmpData().getBioData().getFullName() + " " + messages.getString("ceckinout.checkin_success_socket") + " : " + waktuCheckIn + "=== Status :" + entity.getNote() + " ===";
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information Check In/Out", infoMessages);
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        pushContext.push(HRMConstant.CHECK_IN_OUT_CHANEL_SOCKET, facesMessage);

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void update(CheckInAttendance entity) throws Exception {
        CheckInAttendance attendance = checkInAttendanceDao.getEntiyByPK(entity.getId());
        attendance.setUpdatedBy(UserInfoUtil.getUserName());
        attendance.setUpdatedOn(new Date());
        attendance.setCheckOutTime(entity.getCheckOutTime());
        checkInAttendanceDao.update(attendance);
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        String waktuCheckOut = new SimpleDateFormat("EEEE, dd-MMMM-yyyy hh:mm:ss").format(attendance.getCheckOutTime());
        String infoMessages = attendance.getEmpData().getBioData().getFullName() + " " + messages.getString("ceckinout.checkout_success_socket") + " : " + waktuCheckOut + "=== Status :" + attendance.getNote() + " ===";
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information Check In/Out", infoMessages);
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        pushContext.push(HRMConstant.CHECK_IN_OUT_CHANEL_SOCKET, facesMessage);
    }

    @Override
    public void saveOrUpdate(CheckInAttendance enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance saveData(CheckInAttendance entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance updateData(CheckInAttendance entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance saveOrUpdateData(CheckInAttendance entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckInAttendance getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CheckInAttendance entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(CheckInAttendance entity) throws Exception {
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
    public List<CheckInAttendance> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckInAttendance> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckInAttendance> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckInAttendance> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckInAttendance> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckInAttendance> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckInAttendance> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckInAttendance> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<CheckInAttendance> getByParamWithDetail(CheckInAttendanceSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return checkInAttendanceDao.getByParamWithDetail(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalCheckInAttendanceByParam(CheckInAttendanceSearchParameter searchParameter) throws Exception {
        return checkInAttendanceDao.getTotalCheckInAttendanceByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public CheckInAttendance getByEmpIdAndCheckIn(long id, Date checkInDate) throws Exception {
        return this.checkInAttendanceDao.getByEmpIdAndCheckIn(id, checkInDate);
    }
}
