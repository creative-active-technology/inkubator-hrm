package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioFamilyRelationshipDao;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.web.model.FamilyRelationhipReportModel;
import com.inkubator.hrm.web.search.ReportOfEmployeesFamilySearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "bioFamilyRelationshipDao")
@Lazy
public class BioFamilyRelationshipDaoImpl extends IDAOImpl<BioFamilyRelationship> implements BioFamilyRelationshipDao {

    @Override
    public Class<BioFamilyRelationship> getEntityClass() {
        return BioFamilyRelationship.class;
    }

    @Override
    public List<BioFamilyRelationship> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        criteria.setFetchMode("familyRelation", FetchMode.JOIN);
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public BioFamilyRelationship getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("familyRelation", FetchMode.JOIN);
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        return (BioFamilyRelationship) criteria.uniqueResult();
    }

    @Override
    public List<BioFamilyRelationship> getAllDataReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("familyRelation", "familyRelation", JoinType.INNER_JOIN);
//        criteria.createAlias("bioData.empDatas", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("educationLevel", "educationLevel", JoinType.INNER_JOIN);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("bioData.empDatas", FetchMode.JOIN);
        criteria.setFetchMode("bioData.empDatas.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("bioData.empDatas.jabatanByJabatanId.department", FetchMode.JOIN);
        
        doSearchReportOfEmployeesFamilyByParam(searchParameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("familyRelation", "familyRelation", JoinType.INNER_JOIN);
//        criteria.createAlias("bioData.empDatas", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("educationLevel", "educationLevel", JoinType.INNER_JOIN);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("bioData.empDatas", FetchMode.JOIN);
        criteria.setFetchMode("bioData.empDatas.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("bioData.empDatas.jabatanByJabatanId.department", FetchMode.JOIN);
        doSearchReportOfEmployeesFamilyByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private Criteria doSearchReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter param, Criteria criteria) {
        if (param.getDepartmentId() != null && param.getDepartmentId() != 0) {
            criteria.createAlias("bioData.empDatas", "empDatas", JoinType.INNER_JOIN);
            criteria.createAlias("bioData.empDatas.jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
            criteria.createAlias("bioData.empDatas.jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
            criteria.add(Restrictions.eq("department.id", param.getDepartmentId()));
        }

        return criteria;
    }

    @Override
    public List<FamilyRelationhipReportModel> getAllDataReportOfEmployeesFamilyModelByParam(ReportOfEmployeesFamilySearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
