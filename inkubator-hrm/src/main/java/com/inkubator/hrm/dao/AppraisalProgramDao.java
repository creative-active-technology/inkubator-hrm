package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.web.search.AppraisalProgramSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalProgramDao extends IDAO<AppraisalProgram> {

	public List<AppraisalProgram> getAllDataByParam(AppraisalProgramSearchParameter searchParameter, int firstResult, int maxResult, Order order);
	
	public Long getTotalByParam(AppraisalProgramSearchParameter searchParameter);

	public AppraisalProgram getEntityByIdWithDetail(Long id);
	
}
