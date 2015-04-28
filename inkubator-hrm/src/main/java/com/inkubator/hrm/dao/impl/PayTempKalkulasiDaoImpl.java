/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.PayTempKalkulasiDao;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.model.SalaryJournalModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
import java.math.BigDecimal;

/**
 *
 * @author denifahri
 */
@Repository(value = "payTempKalkulasiDao")
@Lazy
public class PayTempKalkulasiDaoImpl extends IDAOImpl<PayTempKalkulasi> implements PayTempKalkulasiDao {

    @Override
    public Class<PayTempKalkulasi> getEntityClass() {
        return PayTempKalkulasi.class;
    }

    @Override
    public void saveBatch(List<PayTempKalkulasi> data) {
        int counter = 0;
        for (PayTempKalkulasi kalkulasi : data) {
            getCurrentSession().save(kalkulasi);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    @Override
    public List<PayTempKalkulasiModel> getByParam(String searchParameter, int firstResult, int maxResults, Order order) {
        final StringBuilder query = new StringBuilder("select B.id as paySalaryComponentId, A.id as id, B.code as code, B.name as name, count(A.empData) as jumlahKaryawan, sum(A.nominal) as nominal from PayTempKalkulasi A");
        query.append(" inner join A.paySalaryComponent B");
        if (searchParameter != null) {
            query.append(" WHERE name like :name");
        }
        query.append(" group by B.name");
        if (order.toString().contains("jumlahKaryawan") || order.toString().contains("nominal")) {
            query.append(" order by " + order);
        } else {
            query.append(" order by B." + order);
        }
        if (searchParameter != null) {
            return getCurrentSession().createQuery(query.toString())
                    .setParameter("name", '%' + searchParameter + '%')
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(PayTempKalkulasiModel.class))
                    .list();
        } else {
            return getCurrentSession().createQuery(query.toString())
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(PayTempKalkulasiModel.class))
                    .list();
        }
    }

    @Override
    public Long getTotalPayTempKalkulasiByParam(String searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT count(B.name) FROM hrm.pay_temp_kalkulasi A INNER JOIN hrm.pay_salary_component B WHERE A.pay_salary_component_id = B.id");

        if (searchParameter != null) {
            query.append(" AND B.name like :name ");
        }
        query.append(" GROUP BY B.name) AS jumlahRow");

        if (searchParameter != null) {
            Query hbm = getCurrentSession().createSQLQuery(query.toString()).setParameter("name", '%' + searchParameter + '%');
            return Long.valueOf(hbm.uniqueResult().toString());
        } else {
            Query hbm = getCurrentSession().createSQLQuery(query.toString());
            return Long.valueOf(hbm.uniqueResult().toString());
        }

    }

    private void doSearchByParam(PayTempKalkulasiSearchParameter parameter, Criteria criteria) {
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(parameter.getEmpData())) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmpData(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmpData(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(parameter.getPaySalaryComponent())) {
            criteria.add(Restrictions.like("bioData.firstName", parameter.getPaySalaryComponent(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PayTempKalkulasi getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("paySalaryComponent", FetchMode.JOIN);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        return (PayTempKalkulasi) criteria.uniqueResult();
    }

    @Override
    public void deleteAllData() {
        Query query = getCurrentSession().createQuery("delete from PayTempKalkulasi");
        query.executeUpdate();
    }

    @Override
    public Long getTotalKaryawan() {
        final StringBuilder query = new StringBuilder("select  count(A.empData) from PayTempKalkulasi A");
        Query hbm = getCurrentSession().createQuery(query.toString());
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    @Override
    public List<PayTempKalkulasi> getByParamForDetail(String searchParameter, int firstResult, int maxResults, Order order, Long paySalaryComponentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria, paySalaryComponentId);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPayTempKalkulasiByParamForDetail(String searchParameter, Long paySalaryComponentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria, paySalaryComponentId);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public void doSearchByParam(String searchParameter, Criteria criteria, Long paySalaryComponentId) {
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("paySalaryComponent.id", paySalaryComponentId));
        if (StringUtils.isNotEmpty(searchParameter)) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", searchParameter, MatchMode.START));
            disjunction.add(Restrictions.like("bioData.firstName", searchParameter, MatchMode.START));
            disjunction.add(Restrictions.like("bioData.lastName", searchParameter, MatchMode.START));
            criteria.add(disjunction);
//            criteria.add(Restrictions.like("bioData.firstName", parameter, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<PayTempKalkulasi> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryComponent.taxComponent", "taxComponent", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.isNotNull("paySalaryComponent.taxComponent"));
        criteria.addOrder(Order.desc("taxComponent.id"));
        return criteria.list();
    }

    @Override
    public PayTempKalkulasi getEntityByEmpIdAndModelTakeHomePayId(Long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryComponent.modelComponent", "modelComponent", JoinType.INNER_JOIN);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.golonganJabatan", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empId));
        criteria.add(Restrictions.eq("modelComponent.spesific", HRMConstant.MODEL_COMP_TAKE_HOME_PAY));
        return (PayTempKalkulasi) criteria.uniqueResult();
    }

    @Override
    public List<PayTempKalkulasi> getAllDataByEmpDataIdAndExcludeCompTHP(Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryComponent.modelComponent", "modelComponent", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.ne("modelComponent.spesific", HRMConstant.MODEL_COMP_TAKE_HOME_PAY));
//        criteria.addOrder(Order.asc("modelComponent.spesific"));
        return criteria.list();
    }

    @Override
    public List<SalaryJournalModel> getByParamForSalaryJournal(String searchParameter, int firstResult, int maxResults, Order order) {
    
        BigDecimal zero = new BigDecimal(0.0);
        final StringBuilder query = new StringBuilder("select D.code as costCenterCode,");
        query.append("D.name AS costCenterName,");
        query.append("D.id AS costCenterId,");
        query.append("N.code AS jurnalCode,");
        query.append("N.name AS jurnalName,");
        query.append("N.id AS jurnalId,");
//        query.append("case when N.typeJurnal=0 then sum(A.nominal) else 0 end AS debet,");
//        query.append("case when N.typeJurnal=0 then 0 else sum(A.nominal) end AS kredit");
        query.append("sum(case when N.typeJurnal=0 then A.nominal else 0 end) AS debet2,");
        query.append("sum(case when N.typeJurnal=0 then 0.0 else A.nominal end) AS kredit");
        query.append(" FROM PayTempKalkulasi A");
        query.append(" INNER JOIN A.paySalaryComponent F");
        query.append(" INNER JOIN F.paySalaryJurnal N");
        query.append(" INNER JOIN A.empData B");
        query.append(" INNER JOIN B.jabatanByJabatanId X ");
        query.append(" INNER JOIN B.bioData bio ");
        query.append(" INNER JOIN X.department C");
        query.append(" INNER JOIN C.costCenterDept D ");
        if (searchParameter != null) {
            query.append(" WHERE D.name like :name OR N.name like :name OR N.code like :name OR D.code like :name ");
        }
        query.append(" GROUP BY D.id, F.paySalaryJurnal");
        if (order.toString().contains("costCenterCode") || order.toString().contains("costCenterName") || order.toString().contains("jurnalName") || order.toString().contains("debet2") || order.toString().contains("kredit")) {
            query.append(" order by " + order);
        } else {
            query.append(" order by B." + order);
        }
        
        if (searchParameter != null) {
            return getCurrentSession().createQuery(query.toString())
                    .setParameter("name", '%' + searchParameter + '%')
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(SalaryJournalModel.class))
                    .list();
        } else {
            return getCurrentSession().createQuery(query.toString())
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(SalaryJournalModel.class))
                    .list();
        }
    }

    @Override
    public Long getTotalPayTempKalkulasiForSalaryJournal(String searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM");
        query.append(" (SELECT count(d.`code`)");
        query.append(" FROM hrm.pay_temp_kalkulasi a");
        query.append(" INNER JOIN hrm.pay_salary_component f ON f.id = a.pay_salary_component_id");
        query.append(" INNER JOIN hrm.pay_salary_jurnal n ON n.id = f.paysalary_jurnal_id");
        query.append(" INNER JOIN hrm.emp_data b ON a.emp_data_id = b.id");
        query.append(" INNER JOIN hrm.jabatan x ON x.id = b.jabatan_id");
        query.append(" INNER JOIN hrm.department c ON c.id = x.departement_id");
        query.append(" INNER JOIN hrm.cost_center d ON d.id = c.cost_center_id");
        if (searchParameter != null) {
            query.append(" WHERE d.name like '%" + searchParameter + "%'");
            query.append(" OR d.code like '%" + searchParameter + "%'");
            query.append(" OR n.name like '%" + searchParameter + "%'");
            query.append(" OR n.code like '%" + searchParameter + "%'");
        }
        query.append(" GROUP BY d.id, f.paysalary_jurnal_id) AS jumlahRows");

        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    @Override
    public List<SalaryJournalModel> getByParamForSalaryJournalDebet(String searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT d.code as costCenterCodeDebet ,");
        query.append("d.name AS costCenterNameDebet ,");
        query.append("SUM(a.nominal) as jumlahDebet");
        query.append(" FROM pay_temp_kalkulasi a");
        query.append(" INNER JOIN pay_salary_component f ON f.id = a.pay_salary_component_id");
        query.append(" INNER JOIN pay_salary_jurnal n ON n.id =  f.paysalary_jurnal_id");
        query.append(" INNER JOIN emp_data b ON a.emp_data_id = b.id");
        query.append(" INNER JOIN jabatan x ON x.id = b.jabatan_id");
        query.append(" INNER JOIN department c ON c.id = x.departement_id");
        query.append(" INNER JOIN cost_center d ON d.id = c.cost_center_id");
        query.append(" where n.type_jurnal=0");
        if (searchParameter != null) {
            query.append(" AND d.name like '%" + searchParameter + "%'");
            query.append(" OR d.code like '%" + searchParameter + "%'");
        }
        query.append(" GROUP BY d.id ORDER BY d.code");
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(SalaryJournalModel.class)).list();
    }

    @Override
    public List<SalaryJournalModel> getByParamForSalaryJournalKredit(String searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT d.code as costCenterCodeKredit ,");
        query.append("d.name AS costCenterNameKredit ,");
        query.append("SUM(a.nominal) as jumlahKredit");
        query.append(" FROM pay_temp_kalkulasi a");
        query.append(" INNER JOIN pay_salary_component f ON f.id = a.pay_salary_component_id");
        query.append(" INNER JOIN pay_salary_jurnal n ON n.id =  f.paysalary_jurnal_id");
        query.append(" INNER JOIN emp_data b ON a.emp_data_id = b.id");
        query.append(" INNER JOIN jabatan x ON x.id = b.jabatan_id");
        query.append(" INNER JOIN department c ON c.id = x.departement_id");
        query.append(" INNER JOIN cost_center d ON d.id = c.cost_center_id");
        query.append(" where n.type_jurnal=1");
        if (searchParameter != null) {
            query.append(" AND d.name like '%" + searchParameter + "%'");
            query.append(" OR d.code like '%" + searchParameter + "%'");
        }
        query.append(" GROUP BY d.id ORDER BY d.code");
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(SalaryJournalModel.class)).list();
    }

    @Override
    public Long getTotalPayTempKalkulasiForSalaryJournalDebetAndKredit(String searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM");
        query.append(" (SELECT count(d.code)");
        query.append(" FROM pay_temp_kalkulasi a");
        query.append(" INNER JOIN pay_salary_component f ON f.id = a.pay_salary_component_id");
        query.append(" INNER JOIN pay_salary_jurnal n ON n.id =  f.paysalary_jurnal_id");
        query.append(" INNER JOIN emp_data b ON a.emp_data_id = b.id");
        query.append(" INNER JOIN jabatan x ON x.id = b.jabatan_id");
        query.append(" INNER JOIN department c ON c.id = x.departement_id");
        query.append(" INNER JOIN cost_center d ON d.id = c.cost_center_id");
        query.append(" where n.type_jurnal=1");
        if (searchParameter != null) {
            query.append(" AND d.name like '%" + searchParameter + "%'");
            query.append(" OR d.code like '%" + searchParameter + "%'");
        }
        query.append(" GROUP BY d.id) AS jumlahRowsDebetAndKredit");
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    @Override
    public PayTempKalkulasi getEntityByEmpDataIdAndSpecificModelComponent(Long empDataId, Integer specific) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryComponent.modelComponent", "modelComponent", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("modelComponent.spesific", specific));

        return (PayTempKalkulasi) criteria.uniqueResult();
    }

    
}
