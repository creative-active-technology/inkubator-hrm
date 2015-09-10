/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModelView;
import com.inkubator.hrm.web.search.PayComponentDataExceptionSearchParameter;
import com.inkubator.hrm.web.search.PaySalaryComponentSearchParameter;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface PaySalaryComponentDao extends IDAO<PaySalaryComponent> {

	public void saveAndMerge(PaySalaryComponent paySalaryComponent);
	
	
	
	//pageable
    public List<PaySalaryComponent> getAllDataByParamWithDetail(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParamWithDetail(PaySalaryComponentSearchParameter searchParameter);
    
    public List<PaySalaryComponent> getAllDataComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter);
    
    public List<PayComponentDataExceptionModelView> getAllDataByParamDataException(PayComponentDataExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParamDataException(PayComponentDataExceptionSearchParameter searchParameter);
    
    
        
    //get Entity
    public PaySalaryComponent getEntityByPkWithDetail(Long id);

    public PaySalaryComponent getByEmployeeTypeIdAndComponentIdAndJoinDate(Long typeId, Long componentId, Date joinDate);
    
    public PaySalaryComponent getEntityBySpecificModelComponent(Integer specific);
    
    
    
    //get List
    public List<PaySalaryComponent> getAllDataByEmpTypeIdAndActiveFromTmAndIdNotIn(Long empTypeId, int fromTbm, List<Long> componentIds);
    
    public List<PaySalaryComponent> getAllDataByComponentCategoryZeroOrOne();
    
    public List<Integer> getAllModelReferensiId();
    
    
    
    //get Property
    public Long getTotalByModelComponentAndModelReferensi(Long modelComponentId, Integer modelReferensi);

    public Long getTotalByModelComponentAndModelReferensiAndNotId(Long modelComponentId, Integer modelReferensi, Long id);
    
    
}
