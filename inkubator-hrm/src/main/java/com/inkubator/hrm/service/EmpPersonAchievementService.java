/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpPersonAchievement;
import com.inkubator.hrm.web.search.EmpPersonAchievementSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface EmpPersonAchievementService extends IService<EmpPersonAchievement>{
    public List<EmpPersonAchievement> getAllDataWithEmployee(EmpPersonAchievementSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalEmpPersonAchievementByParam(EmpPersonAchievementSearchParameter searchParameter) throws Exception;
    
    public EmpPersonAchievement getEntityByPkWithEmployee(Long code) throws Exception;
    
    public List<EmpPersonAchievement> getAllDataWithEmployee() throws Exception;
}
