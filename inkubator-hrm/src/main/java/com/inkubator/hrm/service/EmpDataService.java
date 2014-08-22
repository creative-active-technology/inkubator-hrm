/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface EmpDataService extends IService<EmpData> {

    public Map<String, Long> getTotalByGender() throws Exception;

    public Map<String, Long> getTotalByAge() throws Exception;

    public Map<String, Long> getTotalByDepartment() throws Exception;

    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalEmpDataByParam(EmpDataSearchParameter searchParameter) throws Exception;

    public EmpData getByEmpIdWithDetail(long id) throws Exception;

    public EmpData getByBioDataIdWithDepartment(long id) throws Exception;
    
    public List<EmpData> getAllDataWithRelation() throws Exception;

}
