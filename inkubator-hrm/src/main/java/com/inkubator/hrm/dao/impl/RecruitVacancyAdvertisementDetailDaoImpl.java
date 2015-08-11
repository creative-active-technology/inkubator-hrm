package com.inkubator.hrm.dao.impl;

import org.hibernate.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitVacancyAdvertisementDetailDao;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "recruitVacancyAdvertisementDetailDao")
@Lazy
public class RecruitVacancyAdvertisementDetailDaoImpl extends IDAOImpl<RecruitVacancyAdvertisementDetail> implements
		RecruitVacancyAdvertisementDetailDao {

	@Override
	public Class<RecruitVacancyAdvertisementDetail> getEntityClass() {
		return RecruitVacancyAdvertisementDetail.class;
	}

	@Override
	public void deleteByVacancyAdvertisementId(Long vacancyAdvertisementId){
		Query query = getCurrentSession().createQuery("DELETE FROM RecruitVacancyAdvertisementDetail temp WHERE temp.vacancyAdvertisement.id = :vacancyAdvertisementId")
				.setLong("vacancyAdvertisementId", vacancyAdvertisementId);
        query.executeUpdate();
	}
	
}
