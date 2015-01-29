package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempProcessReadFingerDao;
import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.web.model.DataFingerRealizationModel;
import com.inkubator.hrm.web.search.DataFingerRealizationSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "tempProcessReadFingerDao")
@Lazy
public class TempProcessReadFingerDaoImpl extends IDAOImpl<TempProcessReadFinger> implements TempProcessReadFingerDao {

	@Override
	public Class<TempProcessReadFinger> getEntityClass() {
		return TempProcessReadFinger.class;
	}

	@Override
	public List<TempProcessReadFinger> getByParam(Long empDataId, int firstResult, int maxResults, Order orderable) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataId));
		criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(Long empDataId) throws Exception {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataId));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public List<DataFingerRealizationModel> getDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
		StringBuffer selectQuery = new StringBuffer(
				"SELECT empData.id as empDataId, "
				+ "empData.nik as nik, "
				+ "CONCAT(empData.bioData.firstName,' ',empData.bioData.lastName) as employeeName, "
				+ "empData.wtGroupWorking.name as workingGroupName, "
				+ "(SUM(CASE WHEN fingerIn IS NULL THEN 0 else 1 END)/count(*))*100 as fingerIn, "
				+ "(SUM(CASE WHEN fingerOut IS NULL THEN 0 else 1 END)/count(*))*100 as fingerOut, "
				+ "(SUM(CASE WHEN webCheckIn IS NULL THEN 0 else 1 END)/count(*))*100 as webCheckIn, "
				+ "(SUM(CASE WHEN webCheckOut IS NULL THEN 0 else 1 END)/count(*))*100 as webCheckOut "
				+ "FROM TempProcessReadFinger ");
		selectQuery.append(this.getWhereQueryDataFingerRealizationByParam(searchParameter));
    	selectQuery.append("GROUP BY empData ");
    	selectQuery.append("ORDER BY " + orderable);
	
		Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
            	.setResultTransformer(Transformers.aliasToBean(DataFingerRealizationModel.class));
		hbm = this.setValueQueryDataFingerRealizationByParam(hbm, searchParameter);
	
		return hbm.list();
	}

	@Override
	public Long getTotalDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter) {		
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(distinct empData) "
    			+ "FROM TempProcessReadFinger ");   
		selectQuery.append(this.getWhereQueryDataFingerRealizationByParam(searchParameter));
    	
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString());  
    	hbm = this.setValueQueryDataFingerRealizationByParam(hbm, searchParameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	private String getWhereQueryDataFingerRealizationByParam(DataFingerRealizationSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();
    	
    	if (StringUtils.isNotEmpty(parameter.getEmployeeName())) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("(empData.bioData.firstName LIKE :employeeName OR empData.bioData.lastName LIKE :employeeName) ");
        }
    	
    	if (StringUtils.isNotEmpty(parameter.getWorkingGroupName())) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("empData.wtGroupWorking.name LIKE :workingGroupName ");
        }
    	
        if (StringUtils.isNotEmpty(parameter.getNik())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("empData.nik LIKE :nik ");
        }
                
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    }
    
    private Query setValueQueryDataFingerRealizationByParam(Query hbm, DataFingerRealizationSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "employeeName")){
    			hbm.setParameter("employeeName", "%" + parameter.getEmployeeName() + "%");
    		} else if(StringUtils.equals(param, "nik")){
    			hbm.setParameter("nik", "%" + parameter.getNik() + "%");
    		} else if(StringUtils.equals(param, "workingGroupName")){
    			hbm.setParameter("workingGroupName", "%" + parameter.getWorkingGroupName() + "%");
    		}
    	}
    	
    	return hbm;
    }

	@Override
	public void deleteByScheduleDateAndIsNotCorrection(Date fromPeriode, Date untilPeriode) {
		Query query = getCurrentSession().createQuery("delete from TempProcessReadFinger temp where temp.scheduleDate >= :fromPeriode and temp.scheduleDate <= :untilPeriode and not(isCorrectionIn = 1 or isCorrectionOut = 1)")
				.setDate("fromPeriode", fromPeriode)
				.setDate("untilPeriode", untilPeriode);
        query.executeUpdate();
		
	}

	@Override
	public TempProcessReadFinger getEntityByEmpDataIdAndScheduleDateAndScheduleInAndScheduleOut(
			Long empDataId, Date scheduleDate, Date scheduleIn, Date scheduleOut) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataId));
		criteria.add(Restrictions.eq("scheduleDate", scheduleDate));
		criteria.add(Restrictions.eq("scheduleIn", scheduleIn));
		criteria.add(Restrictions.eq("scheduleOut", scheduleOut));
		return (TempProcessReadFinger) criteria.uniqueResult();
	}

}