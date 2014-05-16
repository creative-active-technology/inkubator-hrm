/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.HrmUserRoleDao;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.HrmUserRole;
import com.inkubator.hrm.entity.PasswordHistory;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.web.search.HrmUserSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Deni Husni FR
 */
@Service(value = "hrmUserService")
@Lazy
public class HrmUserServiceImpl extends IServiceImpl implements HrmUserService {

    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private HrmUserRoleDao hrmUserRoleDao;
    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private JmsTemplate jmsTemplate1;

    @Override
    public HrmUser getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmUser getEntiyByPK(Long id) throws Exception {
        return this.hrmUserDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(HrmUser entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(HrmUser entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(HrmUser enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser saveData(HrmUser entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser updateData(HrmUser entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser saveOrUpdateData(HrmUser entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HrmUser getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(HrmUser entity) throws Exception {
        this.hrmUserDao.delete(entity);
    }

    @Override
    public void softDelete(HrmUser entity) throws Exception {
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
    public List<HrmUser> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmUser> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmUser> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmUser> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmUser> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmUser> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmUser> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HrmUser> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmUser> getByParam(HrmUserSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.hrmUserDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalHrmUserByParam(HrmUserSearchParameter searchParameter) throws Exception {
        return this.hrmUserDao.getTotalHrmUserByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmUser getEntiyByPkWithDetail(long id) throws Exception {
        HrmUser hrmUser = hrmUserDao.getEntiyByPK(id);
        List<HrmRole> hrmRoles = new ArrayList<>();
        for (HrmUserRole hrmRole : this.hrmUserRoleDao.getByUserId(id)) {
            hrmRoles.add(hrmRole.getHrmRole());
        }
        hrmUser.setRoles(hrmRoles);
        return hrmUser;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 30)
    public HrmUser getByUserId(String userId) throws Exception {
        return this.hrmUserDao.getByUserName(userId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 30)
    public HrmUser getByEmailAddress(String emailAddress) throws Exception {
        return this.hrmUserDao.getByEmailAddress(emailAddress);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveAndNotification(HrmUser hrmUser) throws Exception {
        PasswordHistory passwordHistory = new PasswordHistory();
        passwordHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        passwordHistory.setPassword(AESUtil.getAESEncription(hrmUser.getPassword(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO));
        passwordHistory.setEmailNotification(HRMConstant.EMAIL_NOTIFICATION_NOT_YET_SEND);
        passwordHistory.setSmsNotification(HRMConstant.SMA_NOTIFICATION_NOT_SEND);
        passwordHistory.setEmailAddress(hrmUser.getEmailAddress());
        passwordHistory.setRequestType(HRMConstant.USER_NEW);
        passwordHistory.setPhoneNumber(hrmUser.getPhoneNumber());
        passwordHistory.setRealName(hrmUser.getRealName());
        passwordHistory.setUserName(hrmUser.getUserId());
        passwordHistory.setLocalId(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        passwordHistory.setCreatedBy(UserInfoUtil.getUserName());
        passwordHistory.setCreatedOn(new Date());
        List<String> dataRole = new ArrayList<>();
        List<HrmRole> hrmRoles = new ArrayList<>();
        for (HrmUserRole hrmUserRole : hrmUserRoleDao.getByUserId(hrmUser.getId())) {
            hrmRoles.add(hrmUserRole.getHrmRole());
        }
        for (HrmRole hrmRole : hrmRoles) {
            dataRole.add(hrmRole.getRoleName());
        }
        passwordHistory.setListRole(jsonConverter.getJson(dataRole.toArray(new String[dataRole.size()])));
        System.out.println(passwordHistory);
       

    }
}
