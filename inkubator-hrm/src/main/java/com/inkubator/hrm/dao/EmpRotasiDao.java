/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EmpRotasi;
import com.inkubator.hrm.web.search.EmpRotasiSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface EmpRotasiDao extends IDAO<EmpRotasi> {

    public List<EmpRotasi> getByParam(EmpRotasiSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalEmpRotasiDataByParam(EmpRotasiSearchParameter searchParameter);
}
