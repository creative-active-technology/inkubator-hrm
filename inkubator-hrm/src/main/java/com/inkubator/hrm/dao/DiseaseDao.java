package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.web.search.DiseaseSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface DiseaseDao extends IDAO<Disease> {

	public List<Disease> getByParam(DiseaseSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalDiseaseByParam(DiseaseSearchParameter parameter);

}
