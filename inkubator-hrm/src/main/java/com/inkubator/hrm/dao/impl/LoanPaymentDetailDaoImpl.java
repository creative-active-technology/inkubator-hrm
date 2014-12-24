package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanPaymentDetailDao;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.web.model.LoanPaymentDetailModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "loanPaymentDetailDao")
@Lazy
public class LoanPaymentDetailDaoImpl extends IDAOImpl<LoanPaymentDetail> implements LoanPaymentDetailDao {

    @Override
    public Class<LoanPaymentDetail> getEntityClass() {
        return LoanPaymentDetail.class;

    }

    @Override
    public List<LoanPaymentDetail> getAllDataByLoanId(Long loanId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loan.id", loanId));
        return criteria.list();
    }

    @Override
    public void save(List<LoanPaymentDetail> loanPaymentDetails, Loan loan) {
        Session session = getCurrentSession();
        int i = 1;
        for (LoanPaymentDetail loanPaymentDetail : loanPaymentDetails) {
            loanPaymentDetail.setCreatedBy(UserInfoUtil.getUserName());
            loanPaymentDetail.setCreatedOn(new Date());
            loanPaymentDetail.setLoan(loan);
            session.save(loanPaymentDetail);
            if (i % 20 == 0) {
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    @Override
    public Long getTotalUnpaidByEmpDataId(Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("loan", "loan", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("loan.empData.id", empDataId));
        criteria.add(Restrictions.isNull("paymentDate"));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

    }

    @Override
    public List<LoanPaymentDetail> getByParam(String parameter, LoanPaymentDetailModel loanPaymentDetailModel, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria, loanPaymentDetailModel);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public List<LoanPaymentDetail> getByWtPeriodeWhereComponentPayrollIsActive(LoanPaymentDetailModel loanPaymentDetailModel) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("loan", "loan", JoinType.INNER_JOIN);
        criteria.createAlias("loan.empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("loan.loanSchema", "loanSchema", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("loanSchema.payrollComponent", 1));
        criteria.add(Restrictions.ge("dueDate", loanPaymentDetailModel.getEndDataPeriod()));
        criteria.add(Restrictions.le("dueDate", loanPaymentDetailModel.getEndDateAbsen()));
        return criteria.list();
    }

    @Override
    public Long getTotalResourceTypeByParam(String parameter, LoanPaymentDetailModel loanPaymentDetailModel) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria, loanPaymentDetailModel);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public void doSearchByParam(String parameter, Criteria criteria, LoanPaymentDetailModel loanPaymentDetailModel) {
//        criteria.setProjection(Projections.distinct(Projections.property("loan.id")));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createAlias("loan", "loan", JoinType.INNER_JOIN);
        criteria.createAlias("loan.empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("loan.loanSchema", "loanSchema", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("loanSchema.payrollComponent", 1));
        criteria.add(Restrictions.gt("dueDate", loanPaymentDetailModel.getEndDataPeriod()));
        criteria.add(Restrictions.le("dueDate", loanPaymentDetailModel.getEndDateAbsen()));
        if (StringUtils.isNotEmpty(parameter)) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", parameter, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", parameter, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter, MatchMode.ANYWHERE));
            criteria.add(disjunction);
//            criteria.add(Restrictions.like("bioData.firstName", parameter, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalUnPaidLoanByLoanId(Long loanId, Date periodEndDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("loan", "loan", JoinType.INNER_JOIN);
        criteria.createAlias("loan.loanSchema", "loanSchema", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("loan.id", loanId));
        criteria.add(Restrictions.eq("loanSchema.payrollComponent", 1));
        criteria.add(Restrictions.gt("dueDate", periodEndDate));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

	@Override
	public List<LoanPaymentDetail> getAllDataByEmpDataIdAndLoanSchemaIdAndPeriodTime(Long empDataid, Long loanSchemaId, Date fromPeriode, Date untilPeriode) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("loan", "loan", JoinType.INNER_JOIN);
        criteria.createAlias("loan.loanSchema", "loanSchema", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("loan.empData.id", empDataid));
        criteria.add(Restrictions.eq("loanSchema.id", loanSchemaId));
        criteria.add(Restrictions.ge("dueDate", fromPeriode));
        criteria.add(Restrictions.le("dueDate", untilPeriode));
        return criteria.list();
	}
}
