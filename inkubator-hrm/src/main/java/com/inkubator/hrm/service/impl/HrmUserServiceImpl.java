/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.notification.model.SMSSend;
import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.HashingUtils;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.HrmUserRoleDao;
import com.inkubator.hrm.dao.PasswordComplexityDao;
import com.inkubator.hrm.dao.PasswordHistoryDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.HrmUserRole;
import com.inkubator.hrm.entity.PasswordComplexity;
import com.inkubator.hrm.entity.PasswordHistory;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.web.search.HrmUserSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
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
    private JmsTemplate jmsTemplate;
    @Autowired
    private PasswordHistoryDao passwordHistoryDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private JmsTemplate jmsTemplateSMS;
    @Autowired
    private PasswordComplexityDao passwordComplexityDao;

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
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(HrmUser entity) throws Exception {
        HrmUser hrmUser = this.hrmUserDao.getEntiyByPK(entity.getId());
        hrmUser.getHrmUserRoles().clear();
        if (entity.getEmpData() != null) {
            EmpData empData = empDataDao.getEntiyByPK(entity.getEmpData().getId());
            hrmUser.setEmpData(empData);
        }
        hrmUser.setEmailAddress(entity.getEmailAddress());
        hrmUser.setIsActive(entity.getIsActive());
        hrmUser.setIsExpired(entity.getIsExpired());
        hrmUser.setIsLock(entity.getIsLock());
        hrmUser.setPhoneNumber(entity.getPhoneNumber());
        hrmUser.setRealName(entity.getRealName());
        hrmUser.setUpdatedBy(UserInfoUtil.getUserName());
        hrmUser.setUpdatedOn(new Date());
        hrmUser.setUserId(entity.getUserId());
        hrmUser.setRealName(entity.getRealName());
        this.hrmUserDao.saveAndMerge(hrmUser);
        Set<HrmUserRole> dataToSave = entity.getHrmUserRoles();
        for (HrmUserRole hrmUserRole : dataToSave) {
            hrmUserRole.setHrmUser(hrmUser);
            this.hrmUserRoleDao.save(hrmUserRole);
        }
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
        HrmUser hrmUser = hrmUserDao.getEntityByPkWithDetail(id);
        List<HrmRole> hrmRoles = new ArrayList<>();
        for (HrmUserRole hrmRole : this.hrmUserRoleDao.getByUserId(id)) {
            hrmRoles.add(hrmRole.getHrmRole());
        }

        hrmUser.setRoles(hrmRoles);
        return hrmUser;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmUser getByUserId(String userId) throws Exception {
        return this.hrmUserDao.getByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmUser getByEmailAddress(String emailAddress) throws Exception {
        return this.hrmUserDao.getByEmailAddress(emailAddress);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveAndNotification(HrmUser hrmUser) throws Exception {
        String pass = hrmUser.getPassword();
        if (hrmUser.getEmpData() != null) {
            EmpData empData = empDataDao.getEntiyByPK(hrmUser.getEmpData().getId());
            hrmUser.setEmpData(empData);
        }
        final PasswordHistory passwordHistory = new PasswordHistory();
        passwordHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        passwordHistory.setPassword(AESUtil.getAESEncription(hrmUser.getPassword(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO));
        hrmUser.setPassword(HashingUtils.getHashSHA256(hrmUser.getPassword()));
        hrmUser.setCreatedBy(UserInfoUtil.getUserName());
        hrmUser.setCreatedOn(new Date());
        this.hrmUserDao.save(hrmUser);
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

        this.passwordHistoryDao.save(passwordHistory);
        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(jsonConverter.getJson(passwordHistory));
            }
        });
        PasswordComplexity complexity = passwordComplexityDao.getByCode(HRMConstant.PASSWORD_CONFIG_CODE);
        if (complexity.getSmsNotification()) {
            final SMSSend mSSend = new SMSSend();
//        String decriptPass=AESUtil.getAESDescription(u.getPassword(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
            mSSend.setFrom(HRMConstant.SYSTEM_ADMIN);
            mSSend.setDestination(hrmUser.getPhoneNumber());
            mSSend.setContent("Dear " + passwordHistory.getRealName() + " You has registered in HR Application with User Name :" + hrmUser.getUserId() + " and Password :" + pass);
            System.out.println("step 2");
            // Send notificatin SMS
            this.jmsTemplateSMS.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                        throws JMSException {
                    return session.createTextMessage(jsonConverter.getJson(mSSend));
                }
            });
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmUser getByUserIdOrEmail(String param) throws Exception {
        return this.hrmUserDao.getByUserIdOrEmail(param);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void resetPassword(HrmUser u) throws Exception {
        System.out.println(" Step 1");
        HrmUser user = this.hrmUserDao.getEntiyByPK(u.getId());
        user.setPassword(HashingUtils.getHashSHA256(u.getPassword()));
        user.setUpdatedBy(HRMConstant.INKUBA_SYSTEM);
        user.setUpdatedOn(new Date());
        this.hrmUserDao.update(user);
        final PasswordHistory passwordHistory = new PasswordHistory();
        passwordHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        passwordHistory.setCreatedBy(HRMConstant.INKUBA_SYSTEM);
        passwordHistory.setCreatedOn(new Date());
        passwordHistory.setEmailAddress(user.getEmailAddress());
        passwordHistory.setEmailNotification(0);
        passwordHistory.setRequestType(HRMConstant.USER_RESET);
        passwordHistory.setSmsNotification(-1);
        passwordHistory.setRealName(user.getRealName());
        passwordHistory.setPhoneNumber(user.getPhoneNumber());
        passwordHistory.setPassword(AESUtil.getAESEncription(u.getPassword(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO));
        passwordHistory.setUserName(user.getUserId());
        passwordHistory.setLocalId("en");
        List<String> roleNames = new ArrayList<>();
        for (HrmUserRole userRole : hrmUserRoleDao.getByUserId(u.getId())) {
            roleNames.add(userRole.getHrmRole().getRoleName());
        }
        passwordHistory.setListRole(jsonConverter.getJson(roleNames.toArray(new String[roleNames.size()])));
        this.passwordHistoryDao.save(passwordHistory);

        //send messaging, for processing sending email
        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(jsonConverter.getJson(passwordHistory));
            }
        });
        PasswordComplexity complexity = passwordComplexityDao.getByCode(HRMConstant.PASSWORD_CONFIG_CODE);
        if (complexity.getSmsNotification()) {
            final SMSSend mSSend = new SMSSend();
//        String decriptPass=AESUtil.getAESDescription(u.getPassword(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
            mSSend.setFrom(HRMConstant.SYSTEM_ADMIN);
            mSSend.setDestination(user.getPhoneNumber());
            mSSend.setContent("Dear " + passwordHistory.getRealName() + " your password in HR Application has been reset with :" + u.getPassword());
            System.out.println("step 2");
            // Send notificatin SMS
            this.jmsTemplateSMS.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                        throws JMSException {
                    return session.createTextMessage(jsonConverter.getJson(mSSend));
                }
            });
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updatePassword(Long id, String newPassword) throws Exception {
        String userBy = UserInfoUtil.getUserName();
        Date now = new Date();
        String aesEncryptedPass = AESUtil.getAESEncription(newPassword, HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
        String shaEncryptedPass = HashingUtils.getHashSHA256(newPassword);

        HrmUser user = hrmUserDao.getEntiyByPK(id);
        user.setPassword(shaEncryptedPass);
        user.setUpdatedBy(userBy);
        user.setUpdatedOn(now);
        hrmUserDao.update(user);

        final PasswordHistory passwordHistory = new PasswordHistory();
        passwordHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        passwordHistory.setEmailAddress(user.getEmailAddress());
        passwordHistory.setEmailNotification(0);
        passwordHistory.setRequestType(HRMConstant.USER_UPDATE);
        passwordHistory.setSmsNotification(-1);
        passwordHistory.setRealName(user.getRealName());
        passwordHistory.setPhoneNumber(user.getPhoneNumber());
        passwordHistory.setPassword(aesEncryptedPass);
        passwordHistory.setUserName(user.getUserId());
        passwordHistory.setCreatedBy(userBy);
        passwordHistory.setCreatedOn(now);
        passwordHistory.setLocalId("en");
        List<String> dataRole = new ArrayList<>();
        List<HrmRole> roles = new ArrayList<>();
        for (HrmUserRole userRole : hrmUserRoleDao.getByUserId(user.getId())) {
            roles.add(userRole.getHrmRole());
        }
        for (HrmRole role : roles) {
            dataRole.add(role.getRoleName());
        }
        passwordHistory.setListRole(jsonConverter.getJson(dataRole.toArray(new String[dataRole.size()])));
        passwordHistoryDao.save(passwordHistory);

        //send messaging, for processing sending email
        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(jsonConverter.getJson(passwordHistory));
            }
        });

        PasswordComplexity complexity = passwordComplexityDao.getByCode(HRMConstant.PASSWORD_CONFIG_CODE);
        if (complexity.getSmsNotification()) {
            final SMSSend mSSend = new SMSSend();
//        String decriptPass=AESUtil.getAESDescription(u.getPassword(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
            mSSend.setFrom(HRMConstant.SYSTEM_ADMIN);
            mSSend.setDestination(user.getPhoneNumber());
            mSSend.setContent("Dear " + passwordHistory.getRealName() + " your password in HR Application has been update with :" + newPassword);
            System.out.println("step 2");
            // Send notificatin SMS
            this.jmsTemplateSMS.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                        throws JMSException {
                    return session.createTextMessage(jsonConverter.getJson(mSSend));
                }
            });
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserInfo(HrmUser u) throws Exception {
        // check duplicate email
        long totalDuplicates = hrmUserDao.getTotalByEmailAndNotUserId(u.getEmailAddress(), u.getUserId());
        if (totalDuplicates > 0) {
            throw new BussinessException("master_layout.error_duplicate_email");
        }

        String userBy = UserInfoUtil.getUserName();
        Date now = new Date();

        HrmUser user = hrmUserDao.getByUserId(u.getUserId());
        user.setRealName(u.getRealName());
        user.setPhoneNumber(u.getPhoneNumber());
        user.setEmailAddress(u.getEmailAddress());
        user.setUpdatedBy(userBy);
        user.setUpdatedOn(now);
        hrmUserDao.update(user);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmUser> getByName(String name) throws Exception {
        return this.hrmUserDao.getByName(name);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<HrmUser> getAllDataByNameOrNik(String param) throws Exception {
        return this.hrmUserDao.getAllDataByNameOrNik(param);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmUser getUserWithDetail(String userName) throws Exception {
        HrmUser hrmUser = this.hrmUserDao.getByUserId(userName);
        hrmUser.getEmpData().getId();
        return hrmUser;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public HrmUser getByEmailAddressInNotLock(String emailAddress) throws Exception {
        return this.hrmUserDao.getByEmailAddressInNotLock(emailAddress);
    }
}
