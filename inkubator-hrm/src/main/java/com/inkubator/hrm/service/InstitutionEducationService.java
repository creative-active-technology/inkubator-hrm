package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.web.search.InstitutionEducationSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface InstitutionEducationService extends IService<InstitutionEducation> {

	public List<InstitutionEducation> getByParam(InstitutionEducationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(InstitutionEducationSearchParameter parameter) throws Exception;
        
        public InstitutionEducation getInstitutionEducationByIdWithDetail(Long id) throws Exception;

}
