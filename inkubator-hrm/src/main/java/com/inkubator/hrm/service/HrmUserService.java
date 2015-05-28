/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.web.search.HrmUserSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface HrmUserService extends IService<HrmUser> {

    public List<HrmUser> getByParam(HrmUserSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalHrmUserByParam(HrmUserSearchParameter searchParameter) throws Exception;

    public HrmUser getEntiyByPkWithDetail(long id) throws Exception;

    public HrmUser getByUserId(String userId) throws Exception;

    public HrmUser getByEmailAddress(String emailAddress) throws Exception;

    public void saveAndNotification(HrmUser hrmUser) throws Exception;

    public HrmUser getByUserIdOrEmail(String param) throws Exception;

    public void updateUserInfo(HrmUser user) throws Exception;

    public void resetPassword(HrmUser user) throws Exception;

    public void updatePassword(Long id, String newPassword) throws Exception;

    public List<HrmUser> getByName(String name) throws Exception;

    public List<HrmUser> getAllDataByNameOrNik(String param) throws Exception;

    public HrmUser getUserWithDetail(String userName) throws Exception;

    public HrmUser getByEmailAddressInNotLock(String emailAddress) throws Exception;

    public HrmUser getByEmpDataId(long empDataId) throws Exception;

    public Long getCompanyId(String userId) throws Exception;

    public HrmUser getByUserIdWithEmpIdAndBioId(String userId) throws Exception;

}
