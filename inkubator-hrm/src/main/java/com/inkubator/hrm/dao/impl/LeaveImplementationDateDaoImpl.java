package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LeaveImplementationDateDao;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.web.model.LeaveImplementationDateModel;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;

import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ASTNode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "leaveImplementationDateDao")
@Lazy
public class LeaveImplementationDateDaoImpl extends IDAOImpl<LeaveImplementationDate> implements LeaveImplementationDateDao {

    @Override
    public Class<LeaveImplementationDate> getEntityClass() {
        return LeaveImplementationDate.class;

    }

    @Override
    public LeaveImplementationDate getByLeavIdDateAndIsTrue(long leavId, Date doDate, Boolean param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("leaveImplementation", "lv");
        criteria.add(Restrictions.eq("lv.id", leavId));
        criteria.add(Restrictions.eq("actualDate", doDate));
        criteria.add(Restrictions.eq("isCancelled", param));
        return (LeaveImplementationDate) criteria.uniqueResult();

    }

	@Override
	public Long getTotalActualLeave(Date date, Long companyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("leaveImplementation", "leaveImplementation", JoinType.INNER_JOIN);
		criteria.createAlias("leaveImplementation.empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        criteria.createAlias("department.company", "company", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("company.id", companyId));
        criteria.add(Restrictions.eq("actualDate", date));
        criteria.add(Restrictions.eq("isCancelled", Boolean.FALSE));
        criteria.add(Restrictions.not(Restrictions.eq("empData.status", HRMConstant.EMP_TERMINATION)));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public List<LeaveImplementationDateModel> getAllDataWithTotalTakenLeaveByEmpDataId(Long empDataId) {
		/*final StringBuilder query = new StringBuilder("SELECT COUNT(a.actualDate) AS totalData FROM LeaveImplementationDate a"); 
		query.append(" INNER JOIN a.leaveImplementation b");
		query.append(" WHERE b.empData.id = :empDataId");
		query.append(" GROUP BY b.leave");*/
		
		final StringBuilder query = new StringBuilder("SELECT l.name as leaveDistributionName, ld.balance as balance,"); 
		query.append(" (SELECT count(*) from leave_implementation li ");
		query.append(" INNER JOIN leave_implementation_date lid ON li.id = lid.leave_implementation_id");
		query.append(" WHERE li.emp_data_id = 101 AND li.leave_id = l.id) as totalPakai");
		query.append(" FROM hrm.leave_distribution ld");
		query.append(" INNER JOIN hrm.`leave` l ON ld.leave_id = l.id");
		query.append(" INNER JOIN hrm.emp_data emp ON ld.emp_data_id = emp.id");
		query.append(" WHERE emp.id = " + empDataId );
		
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(LeaveImplementationDateModel.class))
                .list();
        
	}

	@Override
	public List<LeaveImplementationDate> getAllDataByEmpDataId(Long empDataId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("leaveImplementation", "leaveImplementation", JoinType.INNER_JOIN);
		criteria.createAlias("leaveImplementation.empData", "empData", JoinType.INNER_JOIN);
		criteria.setFetchMode("leaveImplementation.leave", FetchMode.JOIN);
		criteria.add(Restrictions.eq("empData.id", empDataId));
		return criteria.list();
	}

}
