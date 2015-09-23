package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.MedicalCareDao;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.web.search.MedicalCareSearchParameter;
import com.inkubator.hrm.web.search.ReportSickDataSearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "medicalCareDao")
@Lazy
public class MedicalCareDaoImpl extends IDAOImpl<MedicalCare> implements MedicalCareDao {

    @Override
    public Class<MedicalCare> getEntityClass() {
        return MedicalCare.class;
    }

    @Override
    public List<MedicalCare> getByParam(MedicalCareSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMedicalCareByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalMedicalCareByParam(MedicalCareSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMedicalCareByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchMedicalCareByParam(MedicalCareSearchParameter parameter, Criteria criteria) {
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);

        if (parameter.getEmployeeName() != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmployeeName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmployeeName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("empData.nik", parameter.getEmployeeName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }

        if (parameter.getJabatan() != null) {
            criteria.add(Restrictions.like("jabatanByJabatanId.name", parameter.getJabatan(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public MedicalCare getEntityWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("disease", FetchMode.JOIN);
        criteria.setFetchMode("hospital", FetchMode.JOIN);
        return (MedicalCare) criteria.uniqueResult();
    }

    @Override
    public List<MedicalCare> getAllDataWithDetail() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("disease", FetchMode.JOIN);
        criteria.setFetchMode("hospital", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public MedicalCare getEntityWithNameAndNik(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);

        return (MedicalCare) criteria.uniqueResult();
    }

    @Override
    public MedicalCare getByEmpIdAndDate(long empId, Date doDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empId));
        criteria.add(Restrictions.le("startDate", doDate));
        criteria.add(Restrictions.ge("endDate", doDate));
        return (MedicalCare) criteria.uniqueResult();
    }

	@Override
	public List<MedicalCare> getListWhereStartDateBetweenDateFromAndDateUntillByEmpId(
			Long empDataId, Date dateFrom, Date dateUntill) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.ge("startDate", dateFrom));
        criteria.add(Restrictions.le("startDate", dateUntill));

        return criteria.list();
	}

	@Override
	public List<MedicalCare> getByParamForReportSickData(ReportSickDataSearchParameter searchParameter, int firstResult,
			int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchMedicalCareByParamforReport(searchParameter, criteria);
		/*criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("disease", FetchMode.JOIN);
        criteria.setFetchMode("empData.golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId.department", FetchMode.JOIN);
        criteria.setFetchMode("hospital", FetchMode.JOIN);*/
		criteria.addOrder(orderable);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}
	
	private void doSearchMedicalCareByParamforReport(ReportSickDataSearchParameter searchParameter, Criteria criteria){
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        criteria.createAlias("empData.golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("disease", "disease", JoinType.INNER_JOIN);
        criteria.createAlias("hospital", "hospital", JoinType.INNER_JOIN);
        
        if(searchParameter.getStartDate() != null){
        criteria.add(Restrictions.between("requestDate", searchParameter.getStartDate(), searchParameter.getEndDate()));
        }
        if(!searchParameter.getListDepartment().isEmpty()){
        	criteria.add(Restrictions.in("department.id", searchParameter.getListDepartment()));
        }
        
        if(!searchParameter.getListGolJab().isEmpty()){
        	criteria.add(Restrictions.in("golonganJabatan.id", searchParameter.getListGolJab()));
        }
        criteria.add(Restrictions.isNotNull("id"));
        
    }

	@Override
	public Long getTotalByParamForReportSickData(ReportSickDataSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchMedicalCareByParamforReport(searchParameter, criteria);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
}
