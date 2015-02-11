/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import com.inkubator.hrm.web.search.BioDataSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface BioDataDao extends IDAO<BioData> {

    public List<BioData> getByParam(BioDataSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(BioDataSearchParameter parameter);
    
    public List<BioData> getEntityByPKWithDetail(long id);
    
    public List<BioData> getByName(String name);
    
    public List<EmpDataMatrixModel> getAllAgeFromBirthDate();
    
    public List<EmpDataMatrixModel> getTotalByAgeAndGenderMaleFromBirthDate();
    
    public List<EmpDataMatrixModel> getTotalByAgeAndGenderFemaleFromBirthDate();
    
    public Integer getTotalAgeByGenderMaleFromBirthDate();
    
    public Integer getTotalAgeByGenderFemaleFromBirthDate();
    
    public List<EmpDataMatrixModel> getAllAgeByGenderFromBirthDate();
}
