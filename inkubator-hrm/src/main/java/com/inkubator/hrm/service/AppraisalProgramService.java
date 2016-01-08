package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.web.model.AppraisalProgramDistributionViewModel;
import com.inkubator.hrm.web.model.AppraisalProgramModel;
import com.inkubator.hrm.web.search.AppraisalProgramSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalProgramService extends IService<AppraisalProgram> {
	
	public List<AppraisalProgram> getAllDataByParam(AppraisalProgramSearchParameter searchParameter, int firstResult, int maxResult, Order order) throws Exception;
	
	public Long getTotalByParam(AppraisalProgramSearchParameter searchParameter) throws Exception;
	
	public List<AppraisalProgramDistributionViewModel> getAllEmpDistributionByParam(AppraisalProgramSearchParameter searchParameter, int firstResult, int maxResult, Order order)  throws Exception;
	
	public Long getTotalEmpDistributionByParam(AppraisalProgramSearchParameter searchParameter) throws Exception;

	public AppraisalProgram getEntityByIdWithDetail(Long id) throws Exception;

	public void update(AppraisalProgramModel model) throws Exception;

	public void save(AppraisalProgramModel model) throws Exception;
	
}
