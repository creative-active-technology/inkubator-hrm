/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.securitycore.util.UserInfoUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Deni Husni FRs
 */
@ManagedBean(name = "userContext")
@SessionScoped
public class UserContext implements Serializable {

    private static final long serialVersionUID = -1516402176642953626L;

    public String getUserName() {
        return UserInfoUtil.getUserName();
    }

    public List<String> getRoles() {
        return UserInfoUtil.getRoles();
    }

    public String getRolesString() {
        return UserInfoUtil.getRolesString();
    }

    public boolean hasRole(String roleName) {
        return UserInfoUtil.hasRole(roleName);
    }

}
