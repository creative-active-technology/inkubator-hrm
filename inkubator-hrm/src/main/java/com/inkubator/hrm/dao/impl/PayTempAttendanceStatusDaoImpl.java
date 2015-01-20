package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempAttendanceStatusDao;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.web.model.InclusionReimbursmentModel;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import com.inkubator.hrm.web.search.PayTempAttendanceSearchParameter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

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
        
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.wtGroupWorking", FetchMode.JOIN);
        
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }
    
    
    private void doSearchByParam(PayTempAttendanceSearchParameter parameter, Criteria criteria, PayTempAttendanceStatusModel model) {       
        if(null != parameter.getNIK()){ 
            criteria.createAlias("empData", "e", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("e.nik", parameter.getNIK(), MatchMode.ANYWHERE));            
        }
        if(null != parameter.getName()){           
            criteria.createAlias("empData", "e", JoinType.INNER_JOIN);
            criteria.createAlias("e.bioData", "b", JoinType.INNER_JOIN);
            
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("b.firstName", parameter.getName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("b.lastName", parameter.getName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);   
        }
        if(null != parameter.getWorkGroup()){ 
            criteria.createAlias("empData", "e", JoinType.INNER_JOIN);
            criteria.createAlias("e.wtGroupWorking", "w", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("w.name", parameter.getWorkGroup(), MatchMode.ANYWHERE));            
        }
        criteria.add(Restrictions.isNotNull("id"));
    }


    @Override
    public Long getTotalResourceTypeByParam(PayTempAttendanceSearchParameter parameter, PayTempAttendanceStatusModel payTempAttendanceStatusModel) {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());         
         doSearchByParam(parameter, criteria, payTempAttendanceStatusModel);
//         criteria.setFetchMode("empData", FetchMode.JOIN);
//         criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
//         criteria.setFetchMode("empData.wtGroupWorking", FetchMode.JOIN);
         return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
