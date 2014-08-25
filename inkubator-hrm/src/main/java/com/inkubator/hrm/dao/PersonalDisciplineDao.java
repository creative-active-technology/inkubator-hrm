/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PersonalDiscipline;
import com.inkubator.hrm.web.search.PersonalDisciplineSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface PersonalDisciplineDao extends IDAO<PersonalDiscipline>{
    public List<PersonalDiscipline> getAllDataWithAllRelation(PersonalDisciplineSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalPersonalDisciplineByParam(PersonalDisciplineSearchParameter searchParameter);
    
    public PersonalDiscipline getEntityByPkWithAllRelation(Long id);
}
