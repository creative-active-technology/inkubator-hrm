package com.inkubator.hrm.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsApplicationDao;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "rmbsApplicationDao")
@Lazy
public class RmbsApplicationDaoImpl extends IDAOImpl<RmbsApplication> implements RmbsApplicationDao {

	@Override
	public Class<RmbsApplication> getEntityClass() {
		return RmbsApplication.class;
		
	}

	@Override
	public BigDecimal getTotalNominalByEmpDataIdAndRmbsTypeIdAndDateBetween(Long empDataId, Long rmbsTypeId, Date startDate, Date endDate) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT SUM(nominal) " +
    			"FROM RmbsApplication " +
    			"WHERE empData.id = :empDataId " +
    			"AND rmbsType.id = :rmbsTypeId " +
    			"AND applicationDate >= :startDate " +
    			"AND applicationDate <= :endDate");
		
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString())
    			.setParameter("empDataId", empDataId)
    			.setParameter("rmbsTypeId", rmbsTypeId)
    			.setParameter("startDate", startDate)
    			.setParameter("endDate", endDate);
    	
    	Object obj = hbm.uniqueResult();
    	return obj != null ? new BigDecimal(obj.toString()) : new BigDecimal(0);
		
	}
	
	@Override
	public List<RmbsApplicationUndisbursedViewModel> getUndisbursedByParam(RmbsApplicationUndisbursedSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT approvalActivity.id AS approvalActivityId, " +
    			"empData.nik AS empNik," +
    			"CONCAT(bioData.first_name,' ',bioData.last_name) AS empName, " +
    			"rmbsType.id AS rmbsTypeId, " +
    			"rmbsType.name AS rmbsTypeName, " +
    			"approvalActivity.approval_status AS approvalStatus, " +
    			"approvalActivity.pending_data AS jsonData " +
    			"FROM hrm.approval_activity approvalActivity " +
    			"LEFT JOIN hrm.hrm_user AS user ON user.user_id = approvalActivity.request_by " +
    			"LEFT JOIN hrm.emp_data AS empData ON user.emp_data_id = empData.id " +
    			"LEFT JOIN hrm.bio_data AS bioData ON empData.bio_data_id = bioData.id " +
    			"LEFT JOIN hrm.rmbs_type AS rmbsType ON approvalActivity.type_specific = rmbsType.id " +
    			"LEFT JOIN hrm.rmbs_application AS rmbsApplication ON approvalActivity.activity_number = rmbsApplication.approval_activity_number " +
    			"WHERE approvalActivity.approval_status IN (0,1,5) " +
    			"AND (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM hrm.approval_activity app GROUP BY app.activity_number) " +
    			"AND (rmbsApplication.application_status = 0 OR rmbsApplication.application_status is null) ");    	
    	selectQuery.append(this.setWhereQueryUndisburseByParam(parameter));
    	selectQuery.append("GROUP BY approvalActivity.activity_number ");
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(RmbsApplicationUndisbursedViewModel.class));
    	hbm = this.setValueQueryUndisburseByParam(hbm, parameter);
    	
    	return hbm.list();                
    }
    
    @Override
    public Long getTotalUndisbursedByParam(RmbsApplicationUndisbursedSearchParameter parameter) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(*) " +
    			"FROM hrm.approval_activity approvalActivity " +
    	    	"LEFT JOIN hrm.hrm_user AS user ON user.user_id = approvalActivity.request_by " +
    	    	"LEFT JOIN hrm.emp_data AS empData ON user.emp_data_id = empData.id " +
    	    	"LEFT JOIN hrm.bio_data AS bioData ON empData.bio_data_id = bioData.id " +
    	    	"LEFT JOIN hrm.rmbs_type AS rmbsType ON approvalActivity.type_specific = rmbsType.id " +
    	    	"LEFT JOIN hrm.rmbs_application AS rmbsApplication ON approvalActivity.activity_number = rmbsApplication.approval_activity_number " +
    	    	"WHERE approvalActivity.approval_status IN (0,1,5) " +
    	    	"AND (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM hrm.approval_activity app GROUP BY app.activity_number) " +
    	    	"AND (rmbsApplication.application_status = 0 OR rmbsApplication.application_status is null) ");    	
    	selectQuery.append(this.setWhereQueryUndisburseByParam(parameter));
    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryUndisburseByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String setWhereQueryUndisburseByParam(RmbsApplicationUndisbursedSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();    	
    	
        if (StringUtils.isNotEmpty(parameter.getEmpNik())) {
        	whereQuery.append("AND empData.nik LIKE :empNik ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getEmpName())) {
        	whereQuery.append("AND (bioData.first_name LIKE :empName OR bioData.last_name LIKE :empName) ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getRmbsType())) {
        	whereQuery.append("AND rmbsType.name LIKE :rmbsType ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getUserId())) {
        	whereQuery.append("AND user.user_id = :userId ");
        }
        
        return whereQuery.toString();
    }
    
    private Query setValueQueryUndisburseByParam(Query hbm, RmbsApplicationUndisbursedSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "empNik")){
    			hbm.setParameter("empNik", "%" + parameter.getEmpNik() + "%");
    		} else if(StringUtils.equals(param, "rmbsType")){
    			hbm.setParameter("rmbsType", "%" + parameter.getRmbsType() + "%");
    		} else if(StringUtils.equals(param, "userId")){
    			hbm.setParameter("userId", parameter.getUserId());
    		}
    	}
    	
    	return hbm;
    }

	
}
