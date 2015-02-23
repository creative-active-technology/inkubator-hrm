package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogListOfTransferDao;
import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.web.model.BankTransferDistributionReportModel;
import com.inkubator.hrm.web.search.ReportBankTransferDataSearchParameter;

/**
*
* @author rizkykojek
*/
@Repository(value = "logListOfTransferDao")
@Lazy
public class LogListOfTransferDaoImpl extends IDAOImpl<LogListOfTransfer> implements LogListOfTransferDao {

	@Override
	public Class<LogListOfTransfer> getEntityClass() {		
		return LogListOfTransfer.class;
	}
	
	@Override
	public void deleteByPeriodId(Long periodId) {
		Query query = getCurrentSession().createQuery("DELETE FROM LogListOfTransfer temp WHERE temp.periodeId = :periodId")
				.setLong("periodId", periodId);
        query.executeUpdate();
		
	}

    @Override
    public List<BankTransferDistributionReportModel> getBankTransferDistributionByPayrollHistoryReport(Long periodeId) {
         final StringBuilder query = new StringBuilder("SELECT lt.bankName AS bankName, COUNT(lt.accountNumber) AS totalAccountNumber "
                + "  FROM LogListOfTransfer lt "
                + "  WHERE lt.periodeId = :periodeId "
                + "  GROUP BY lt.bankName  ");       
      
         return getCurrentSession().createQuery(query.toString()) 
               .setParameter("periodeId", periodeId)
               .setResultTransformer(Transformers.aliasToBean(BankTransferDistributionReportModel.class))
               .list();
       
    }

    @Override
    public Long getTotalBankTransferByPayrollHistoryReport(Long periodeId) {
       Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
       criteria.add(Restrictions.eq("periodeId", periodeId));
       return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

	@Override
	public List<LogListOfTransfer> getAllDataReportBankTransferData(ReportBankTransferDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		this.doSearchReportBankTransferData(criteria, parameter);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		criteria.addOrder(orderable);
		return criteria.list();
	}

	@Override
	public Long getTotalReportBankTransferData(ReportBankTransferDataSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		this.doSearchReportBankTransferData(criteria, parameter);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchReportBankTransferData(Criteria criteria, ReportBankTransferDataSearchParameter parameter){
		if(parameter.getPeriodeId() != null){
			criteria.add(Restrictions.eq("periodeId", parameter.getPeriodeId()));
		}
		
		if(parameter.getDepartmentId() != null && parameter.getDepartmentId() != 0){
			criteria.add(Restrictions.eq("departmentId", parameter.getDepartmentId()));
		}
		
		if(parameter.getBankId() != null && parameter.getBankId() != 0){
			criteria.add(Restrictions.eq("bankId", parameter.getBankId()));
		}
		
		if(!parameter.getListGolJab().isEmpty()){
			criteria.add(Restrictions.in("empGolJabatan", parameter.getListGolJab()));
		}
	}

}
