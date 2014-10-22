/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.web.search.MecineFingerSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface MecineFingerDao extends IDAO<MecineFinger> {

    public List<MecineFinger> getByParam(MecineFingerSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(MecineFingerSearchParameter parameter);
    
    public Long getByCode(String code);
    
    public Long getTotalByCodeAndNotId(String name, Long id);
}
