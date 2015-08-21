package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.RecruitVacancySelectionDetailDao;
import com.inkubator.hrm.dao.RecruitVacancySelectionDetailPicDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetail;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetailPic;
import com.inkubator.hrm.service.RecruitVacancySelectionDetailService;

@Service(value = "recruitVacancySelectionDetailService")
@Lazy
public class RecruitVacancySelectionDetailServiceImpl extends IServiceImpl implements RecruitVacancySelectionDetailService{

	@Autowired
	private RecruitVacancySelectionDetailDao recruitVacancySelectionDetailDao;
	@Autowired
	private RecruitVacancySelectionDetailPicDao recruitVacancySelectionDetailPicDao;
	
	@Override
	public RecruitVacancySelectionDetail getEntiyByPK(String id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntiyByPK(Integer id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public RecruitVacancySelectionDetail getEntiyByPK(Long id) throws Exception {
		return recruitVacancySelectionDetailDao.getEntiyByPK(id);
	}

	@Override
	public void save(RecruitVacancySelectionDetail entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RecruitVacancySelectionDetail entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(RecruitVacancySelectionDetail enntity)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecruitVacancySelectionDetail saveData(
			RecruitVacancySelectionDetail entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail updateData(
			RecruitVacancySelectionDetail entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail saveOrUpdateData(
			RecruitVacancySelectionDetail entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByPkIsActive(String id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByPkIsActive(String id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByPkIsActive(String id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByPkIsActive(Integer id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByPkIsActive(Integer id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByPkIsActive(Integer id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByPkIsActive(Long id,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByPkIsActive(Long id,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitVacancySelectionDetail getEntityByPkIsActive(Long id,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecruitVacancySelectionDetail entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void softDelete(RecruitVacancySelectionDetail entity)
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
	public List<RecruitVacancySelectionDetail> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetail> getAllData(Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetail> getAllData(Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetail> getAllData(Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetail> getAllDataPageAble(
			int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetail> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetail> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitVacancySelectionDetail> getAllDataPageAbleIsActive(
			int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<RecruitVacancySelectionDetail> getAllDataByRecruitVacancySelection(Long id)throws Exception {
		List<RecruitVacancySelectionDetail> listRecruitVacancySelection = recruitVacancySelectionDetailDao.getAllDataByRecruitVacancySelection(id);
		List<RecruitVacancySelectionDetailPic> listRecruitVacancySelectionPic = new ArrayList<RecruitVacancySelectionDetailPic>();
		List<EmpData> listEmpData;
		for(RecruitVacancySelectionDetail data : listRecruitVacancySelection){
			listEmpData = new ArrayList<EmpData>();
			listRecruitVacancySelectionPic = recruitVacancySelectionDetailPicDao.getAllDataByRecruitVacancySelectionDetailId(data.getId());
			
			for(RecruitVacancySelectionDetailPic dataEmployee : listRecruitVacancySelectionPic){
				
				listEmpData.add(dataEmployee.getEmpData());
			}
			
			data.setListEmpData(listEmpData);
		}
		return listRecruitVacancySelection;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public RecruitVacancySelectionDetail getEntityByRecruitVacancySelection(Long id) throws Exception {
		RecruitVacancySelectionDetail recruitVacancySelectionDetail = recruitVacancySelectionDetailDao.getEntityByRecruitVacancySelection(id);
		List<RecruitVacancySelectionDetailPic> listRecruitVacancySelectionPic = recruitVacancySelectionDetailPicDao.getAllDataByRecruitVacancySelectionDetailId(recruitVacancySelectionDetail.getId());
		List<EmpData> listEmployee = new ArrayList<EmpData>();
		
		for(RecruitVacancySelectionDetailPic employee : listRecruitVacancySelectionPic){
			listEmployee.add(employee.getEmpData());
			
		}
		recruitVacancySelectionDetail.setListEmpData(listEmployee);
		System.out.println(recruitVacancySelectionDetail.getListEmpData().size()  +  " size di entity");
		return recruitVacancySelectionDetail;
	}

}
