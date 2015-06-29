package com.inkubator.hrm.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.RecruitVacancySelectionDetailPicDao;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetailPic;
import com.inkubator.hrm.service.RecruitVacancySelectionDetailPicService;

@Service(value = "recruitVacancySelectionDetailPicService")
@Lazy
public class RecruitVacancySelectionDetailPicServiceImpl extends IServiceImpl implements RecruitVacancySelectionDetailPicService{

	@Autowired
	private RecruitVacancySelectionDetailPicDao recruitVacancySelectionDetailPicDao;
	
	@Override
	public RecruitVacancySelectionDetailPic getEntiyByPK(String id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntiyByPK(Integer id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntiyByPK(Long id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RecruitVacancySelectionDetailPic entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RecruitVacancySelectionDetailPic entity)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(RecruitVacancySelectionDetailPic enntity)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecruitVacancySelectionDetailPic saveData(
			RecruitVacancySelectionDetailPic entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic updateData(
			RecruitVacancySelectionDetailPic entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic saveOrUpdateData(
			RecruitVacancySelectionDetailPic entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntityByPkIsActive(String id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntityByPkIsActive(String id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntityByPkIsActive(String id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntityByPkIsActive(Integer id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntityByPkIsActive(Long id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntityByPkIsActive(Long id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetailPic getEntityByPkIsActive(Long id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecruitVacancySelectionDetailPic entity)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void softDelete(RecruitVacancySelectionDetailPic entity)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllData(Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllData(Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllData(Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllDataPageAble(
			int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetailPic> getAllDataByRecruitVacancySelectionDetailId(Long id) throws Exception {
		return recruitVacancySelectionDetailPicDao.getAllDataByRecruitVacancySelectionDetailId(id);
	}

}
