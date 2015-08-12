package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface BusinessTravelDao extends IDAO<BusinessTravel> {
	
	public List<BusinessTravel> getByParam(BusinessTravelSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(BusinessTravelSearchParameter parameter);

	public BusinessTravel getEntityByPkWithDetail(Long id);
	
	public BusinessTravel getEntityByBusinessTravelNoWithDetail(String businessTravelNo);
	
	public Long getTotalByBusinessTravelNo(String businessTravelNo);
	
	public Long getTotalByBusinessTravelNoAndNotId(String businessTravelNo, Long id);

	public BusinessTravel getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber);
        
    public List<BusinessTravel> getAllDataByEmpDataId(Long empDataId) throws Exception;
        
    public List<BusinessTravel> getListByStartDateBetweenDateAndEmpIdAndNotOff(Long empDataId, Date dateFrom, Date dateUntill);
    
    public BusinessTravel getByEmpIdAndDate(long empId, Date doDate);

	public Long getTotalActualBusinessTravel(Date date);
        
        public Long getCurrentMaxId();
	
}
