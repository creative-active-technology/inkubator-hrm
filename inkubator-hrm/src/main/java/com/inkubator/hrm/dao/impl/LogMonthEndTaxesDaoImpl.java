package com.inkubator.hrm.dao.impl;

import org.hibernate.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogMonthEndTaxesDao;
import com.inkubator.hrm.entity.LogMonthEndTaxes;

/**
*
* @author rizkykojek
*/
@Repository(value = "logMonthEndTaxesDao")
@Lazy
public class LogMonthEndTaxesDaoImpl extends IDAOImpl<LogMonthEndTaxes> implements LogMonthEndTaxesDao {

	@Override
	public Class<LogMonthEndTaxes> getEntityClass() {
		return LogMonthEndTaxes.class;
	}

	@Override
	public void deleteByPeriodId(Long periodId) {
		Query query = getCurrentSession().createQuery("DELETE FROM LogMonthEndTaxes temp WHERE temp.periodeId = :periodId")
				.setLong("periodId", periodId);
        query.executeUpdate();
	}

}
