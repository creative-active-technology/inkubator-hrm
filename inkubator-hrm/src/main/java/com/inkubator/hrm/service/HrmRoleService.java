/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.web.search.HrmRoleSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface HrmRoleService extends IService<HrmRole> {

    public List<HrmRole> getByParam(HrmRoleSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalHrmRoleByParam(HrmRoleSearchParameter searchParameter) throws Exception;

    public HrmRole getByRoleName(String roleName) throws Exception;

	public HrmRole getEntityByPkWithMenus(long id);
}
