package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.web.search.InstitutionEducationSearchParameter;

/**
 *
 * @author Taufik hidayat
 */
public interface InstitutionEducationDao extends IDAO<InstitutionEducation> {

	public List<InstitutionEducation> getByParam(InstitutionEducationSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalInstitutionEducationByParam(InstitutionEducationSearchParameter parameter);

	public Long getTotalByCode(String name);

	public Long getTotalByCodeAndNotId(String code, Long id);

	public InstitutionEducation getInstitutionEducationByIdWithDetail(Long id);

	public InstitutionEducation getEntityByName(String name);
	
}
