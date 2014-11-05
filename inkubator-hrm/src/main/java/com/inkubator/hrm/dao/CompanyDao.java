package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.web.search.CompanySearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface CompanyDao extends IDAO<Company> {

	public List<Company> getByParam(CompanySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(CompanySearchParameter parameter);
	
	public Company getEntityByPKWithDetail(Long id);
	
	public Long getTotalByLegalNo(String legalNo);

    public Long getTotalByLegalNoAndNotId(String legalNo, Long id);
    
    public Long getTotalByPhone(String phone);

    public Long getTotalByPhoneAndNotId(String phone, Long id);
    
    public Long getTotalByTaxAccountNumber(String taxAccountNumber);

    public Long getTotalByTaxAccountNumberAndNotId(String taxAccountNumber, Long id);
	
}
