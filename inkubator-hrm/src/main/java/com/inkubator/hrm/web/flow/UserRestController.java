/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.HrmUserService;
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

            HrmUser user = hrmUserService.getByUserId(userName);
            System.out.println(" Nilinya " + user.getPhoneNumber());
//            if (user != null) {
                model.setUserName(user.getUserId());
                model.setPhoneNumber(user.getPhoneNumber());
                model.setRealName(user.getRealName());
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

    }
}
