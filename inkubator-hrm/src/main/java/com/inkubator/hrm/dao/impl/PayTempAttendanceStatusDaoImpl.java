package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempAttendanceStatusDao;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import com.inkubator.hrm.web.search.PayTempAttendanceSearchParameter;

@Repository(value = "payTempAttendanceStatusDao")
@Lazy
public class PayTempAttendanceStatusDaoImpl extends
		IDAOImpl<PayTempAttendanceStatus> implements PayTempAttendanceStatusDao {


	@Override
	public Class<PayTempAttendanceStatus> getEntityClass() {
		return PayTempAttendanceStatus.class;
	}
        
       @Override
    public List<PayTempAttendanceStatus> getAllByNik(String nik) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "emp");     
        criteria.add(Restrictions.eq("emp.nik", nik));
        return criteria.list();

    }
    
    @Override
    public List<PayTempAttendanceStatus> getByParam(PayTempAttendanceSearchParameter parameter, PayTempAttendanceStatusModel model, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        doSearchByParam(parameter, criteria, model);
        
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }
    
    
    private void doSearchByParam(PayTempAttendanceSearchParameter parameter, Criteria criteria, PayTempAttendanceStatusModel model) {       
    	criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
    	criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
    	criteria.createAlias("empData.wtGroupWorking", "wtGroupWorking", JoinType.INNER_JOIN);
    	
    	if(null != parameter.getNIK()){             
            criteria.add(Restrictions.like("empData.nik", parameter.getNIK(), MatchMode.ANYWHERE));            
        }
        if(null != parameter.getName()){  
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);   
        }
        if(null != parameter.getWorkGroup()){ 
            criteria.add(Restrictions.like("wtGroupWorking.name", parameter.getWorkGroup(), MatchMode.ANYWHERE));            
        }
        criteria.add(Restrictions.isNotNull("id"));
    }


    @Override
    public Long getTotalByParam(PayTempAttendanceSearchParameter parameter, PayTempAttendanceStatusModel payTempAttendanceStatusModel) {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());         
         doSearchByParam(parameter, criteria, payTempAttendanceStatusModel);
         return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    @Override
    public void deleteAllData() {
        Query query = getCurrentSession().createQuery("delete from PayTempAttendanceStatus");
        query.executeUpdate();
    }

	@Override
	public PayTempAttendanceStatus getEntityByEmpDataId(Long empDataId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataId));
		return (PayTempAttendanceStatus) criteria.uniqueResult();
	}

}
