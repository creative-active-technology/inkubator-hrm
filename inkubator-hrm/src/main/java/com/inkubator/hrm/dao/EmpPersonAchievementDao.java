/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EmpPersonAchievement;
import com.inkubator.hrm.web.search.EmpPersonAchievementSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface EmpPersonAchievementDao extends IDAO<EmpPersonAchievement>{
    public List<EmpPersonAchievement> getAllDataWithEmployee(EmpPersonAchievementSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalEmpPersonAchievementByParam(EmpPersonAchievementSearchParameter searchParameter);
    
    public EmpPersonAchievement getEntityByPkWithEmployee(Long id);
    
    public List<EmpPersonAchievement> getAllDataWithEmployee();
    
    public List<EmpPersonAchievement> getAllDataByEmployeeId(Long id);
}
