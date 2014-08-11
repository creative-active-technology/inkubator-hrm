/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.criterion.Order;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.LoginHistoryDao;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.hrm.web.model.LoginHistoryPushMessageModel;
import com.inkubator.hrm.web.search.LoginHistorySearchParameter;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "loginHistoryService")
@Lazy
public class LoginHistoryServiceImpl extends IServiceImpl implements LoginHistoryService {

    @Autowired
    private LoginHistoryDao loginHistoryDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private DateFormatter dateFormatter;

    @Override
    public LoginHistory getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void save(LoginHistory entity) throws Exception {
        HrmUser hrmUser = this.hrmUserDao.getByUserId(entity.getHrmUser().getUserId());
        entity.setHrmUser(hrmUser);
        this.loginHistoryDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void update(LoginHistory entity) throws Exception {
        LoginHistory loginHistory = this.loginHistoryDao.getEntiyByPK(entity.getId());
        loginHistory.setLogOutDate(new Date());
        this.loginHistoryDao.update(loginHistory);
    }

    @Override
    public void saveOrUpdate(LoginHistory enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory saveData(LoginHistory entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory updateData(LoginHistory entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory saveOrUpdateData(LoginHistory entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginHistory getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(LoginHistory entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(LoginHistory entity) throws Exception {
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
    public List<LoginHistory> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoginHistory> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoginHistory> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoginHistory> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoginHistory> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoginHistory> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoginHistory> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoginHistory> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS,
            isolation = Isolation.REPEATABLE_READ, timeout = 50)
    public List<LoginHistory> getByParam(LoginHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.loginHistoryDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS,
            isolation = Isolation.READ_COMMITTED, timeout = 30)
    public Long getTotalLoginHistoryByParam(LoginHistorySearchParameter searchParameter) throws Exception {
        return this.loginHistoryDao.getTotalLoginHistoryByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void saveAndPushMessage(LoginHistory entity) {
        //saving loginHistory
        HrmUser hrmUser = this.hrmUserDao.getByUserIdOrEmail(entity.getHrmUser().getUserId());
        entity.setHrmUser(hrmUser);
        this.loginHistoryDao.save(entity);
        
        //push message
        FacesUtil.setSessionAttribute(HRMConstant.USER_LOGIN_ID, entity.getId());
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        String infoMessages = hrmUser.getRealName() + " " + messages.getString("loginhistory.successfully_logged_on") + " : " + dateFormatter.getDateFullAsStringsWithActiveLocale(entity.getLoginDate(), new Locale(entity.getLanguange()));
        
        LoginHistoryPushMessageModel model = new LoginHistoryPushMessageModel();
        model.setLoginName(entity.getHrmUser().getRealName());
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MMMM-yyyy");
        model.setLoginDate(format.format(entity.getLoginDate()));
        format.applyPattern("HH:mm aa");
        model.setLoginTime(format.format(entity.getLoginDate()));
        model.setGrowlTitle(messages.getString("loginhistory.login_information"));
        model.setGrowlMessage(infoMessages);
        model.setIsLogin(true);
        
        //FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information Login", infoMessages);
        pushContext.push(HRMConstant.NOTIFICATION_CHANEL_SOCKET, model);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void updateAndPushMessage(LoginHistory entity) {
        //saving loginHistory
        LoginHistory loginHistory = this.loginHistoryDao.getEntiyByPK(entity.getId());
        loginHistory.setLogOutDate(new Date());
        this.loginHistoryDao.update(loginHistory);

        //push message
        HrmUser hrmUser = this.hrmUserDao.getByUserIdOrEmail(loginHistory.getHrmUser().getUserId());
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        //set default to en
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale("en"));
        String infoMessages = hrmUser.getRealName() + " " + messages.getString("loginhistory.successfully_logged_out_in") + " : " + dateFormatter.getDateFullAsStringsWithActiveLocale(new Date(), new Locale(loginHistory.getLanguange()));
        
        LoginHistoryPushMessageModel model = new LoginHistoryPushMessageModel();
        model.setLoginName(loginHistory.getHrmUser().getRealName());
        model.setGrowlTitle(messages.getString("loginhistory.logout_information"));
        model.setGrowlMessage(infoMessages);
        model.setIsLogin(false);
        
        //FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information Logout", infoMessages);
        pushContext.push(HRMConstant.NOTIFICATION_CHANEL_SOCKET, model);
    }

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ, timeout = 50)
	public List<LoginHistory> getByParam(int firstResult, int maxResults, Order order) throws Exception {
		return loginHistoryDao.getByParam(firstResult, maxResults, order);
	}

}
