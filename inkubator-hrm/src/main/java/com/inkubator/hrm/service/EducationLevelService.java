package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import com.inkubator.hrm.web.search.EducationLevelSearchParameter;

/**
*
* @author rizkykojek
*/
public interface EducationLevelService extends IService<EducationLevel> {

	public List<EducationLevel> getByParam(EducationLevelSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(EducationLevelSearchParameter searchParameter) throws Exception;
        
        public EducationLevel getEntityByPkWithDetail(Long id) throws Exception;
        
        public List<EducationLevel> getAllDataOrderByLevel() throws Exception;
        
        public List<String> getAllNameOrderByLevel() throws Exception;
        
        public List<EmpDataMatrixModel> getAllNameOrderByLevelWithModel() throws Exception;
        
        public List<EmpDataMatrixModel> getAllNameByGenderOrderByLevelWithModel() throws Exception;

}
