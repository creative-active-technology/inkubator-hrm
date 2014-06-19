package com.inkubator.hrm.web.account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.common.util.HashingUtils;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.util.StringsUtils;
import com.inkubator.hrm.web.model.UserModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.WebCoreConstant;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "userInfoController")
@ViewScoped
public class UserInfoController extends BaseController {

    private UserModel userModel;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService userService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        userModel = new UserModel();
    }

    @PreDestroy
    public void cleanAndExit() {
        userModel = null;
        userService = null;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setUserService(HrmUserService userService) {
		this.userService = userService;
	}

	public void doUpdatePassword() {
        Boolean isUpdateSucceed = Boolean.FALSE;
        RequestContext context = FacesUtil.getRequestContext();
        
        //cek password lama tidak boleh sama dengan password yang baru
        if(StringsUtils.equals(userModel.getPassword(), userModel.getOldPassword())){
        	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "error.oldpassword_and_newpassword_should_different",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        	
        } else {
        	//proses pengecekan password yg lama apakah sesuai dengan yang di database
	        try {
	            HrmUser user = userService.getByUserId(UserInfoUtil.getUserName());
	            boolean isOldPasswordMatched = StringUtils.equals(HashingUtils.getHashSHA256(userModel.getOldPassword()), user.getPassword());
	            if (isOldPasswordMatched) {
	                userService.updatePassword(user.getId(), userModel.getPassword());
	                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
	                        FacesUtil.getSessionAttribute(WebCoreConstant.BAHASA_ACTIVE).toString());
	                isUpdateSucceed = true;
	            } else {
	                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "error.old_password_not_match",
	                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	            }	
	        } catch (Exception ex) {
	            LOGGER.error("Error when doSavePassword spiRole ", ex);
	        }
        }
        context.addCallbackParam("isUpdateSucceed", isUpdateSucceed);
    }

    public void doUpdateUserInfo() {
        Boolean isUpdateSucceed = Boolean.FALSE;
        RequestContext context = FacesUtil.getRequestContext();
        try {
            HrmUser user = new HrmUser();
            user.setUserId(UserInfoUtil.getUserName());
            user.setRealName(userModel.getRealName());
            user.setEmailAddress(userModel.getEmailAddress());
            user.setPhoneNumber(userModel.getPhoneNumber());
            userService.updateUserInfo(user);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                    FacesUtil.getSessionAttribute(WebCoreConstant.BAHASA_ACTIVE).toString());
            isUpdateSucceed = Boolean.TRUE;
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            isUpdateSucceed = Boolean.FALSE;
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doUpdateUserInfo spiRole ", ex);
        }
        context.addCallbackParam("isUpdateSucceed", isUpdateSucceed);
    }

    public void onRenderUserInfo() {
        try {
            HrmUser user = userService.getByUserId(UserInfoUtil.getUserName());
            userModel.setEmailAddress(user.getEmailAddress());
            userModel.setPhoneNumber(user.getPhoneNumber());
            userModel.setRealName(user.getRealName());
            userModel.setUserId(user.getUserId());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error("Error", e);
        }
    }
    
}
