/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.HrmUserRole;
import com.inkubator.hrm.service.HrmUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author deni.fahri
 */
@Controller
@RequestMapping("user_rest")
public class UserRestController {

    protected transient Logger LOGGER = Logger.getLogger(getClass());
    @Autowired
    private HrmUserService hrmUserService;

    @RequestMapping(method = RequestMethod.GET, value = "/getByUserName/{userName:.+}")
    public @ResponseBody
    UserModel getByUserNamePassword(@PathVariable String userName) {
        UserModel model = new UserModel();
        try {
            LOGGER.info("searching user by username " + userName);

            HrmUser user = hrmUserService.getByNameWithRoles(userName);
        
//            if (user != null) {
            model.setUserName(user.getUserId());
            model.setPhoneNumber(user.getPhoneNumber());
            model.setRealName(user.getRealName());
            model.setEmailAddress(user.getEmailAddress());
            model.setIsActive(user.getIsActive());
            model.setIsExpired(user.getIsExpired());
            model.setIsLock(user.getIsLock());
            List<HrmRole> list = user.getRoles();
            List<RoleModel> dataToSend=new ArrayList<>();
            for (HrmRole role : list) {
                RoleModel roleModel = new RoleModel();
                roleModel.setDescription(role.getDescription());
                roleModel.setRoleName(role.getRoleName());
                dataToSend.add(roleModel);
            }
            model.setRoles(dataToSend);
       
//            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return model;
    }

    public class UserModel {

        private String userName;
        private String phoneNumber;
        private String realName;
        private String emailAddress;
        private Integer isActive;
        private Integer isLock;
        private Integer isExpired;
        private List<RoleModel> roles = new ArrayList<>();

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public Integer getIsActive() {
            return isActive;
        }

        public void setIsActive(Integer isActive) {
            this.isActive = isActive;
        }

        public Integer getIsLock() {
            return isLock;
        }

        public void setIsLock(Integer isLock) {
            this.isLock = isLock;
        }

        public Integer getIsExpired() {
            return isExpired;
        }

        public void setIsExpired(Integer isExpired) {
            this.isExpired = isExpired;
        }

        public List<RoleModel> getRoles() {
            return roles;
        }

        public void setRoles(List<RoleModel> roles) {
            this.roles = roles;
        }

    }

    public class RoleModel {

        private String roleName;
        private String description;

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
}
