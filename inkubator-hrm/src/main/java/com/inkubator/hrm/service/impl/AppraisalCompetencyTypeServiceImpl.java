package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.AppraisalCompetencyTypeDao;
import com.inkubator.hrm.dao.AppraisalCompetencyTypeGolJabDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.entity.AppraisalCompetencyTypeGolJab;
import com.inkubator.hrm.entity.AppraisalCompetencyTypeGolJabId;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.AppraisalCompetencyTypeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.search.AppraisalCompetencyTypeSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Service(value = "appraisalCompetencyTypeService")
@Lazy
public class AppraisalCompetencyTypeServiceImpl extends IServiceImpl implements AppraisalCompetencyTypeService {
	
	@Autowired
	private AppraisalCompetencyTypeDao appraisalCompetencyTypeDao;
	@Autowired
	private AppraisalCompetencyTypeGolJabDao appraisalCompetencyTypeGolJabDao;
	@Autowired
	private GolonganJabatanDao golonganJabatanDao;

	@Override
	public void delete(AppraisalCompetencyType arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AppraisalCompetencyType> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyType> getAllData(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyType> getAllData(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyType> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyType> getAllDataPageAble(int arg0, int arg1, Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyType> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Boolean arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyType> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Integer arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyType> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Byte arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntityByPkIsActive(String arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntityByPkIsActive(String arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntityByPkIsActive(String arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntityByPkIsActive(Integer arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntityByPkIsActive(Integer arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntityByPkIsActive(Integer arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntityByPkIsActive(Long arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntityByPkIsActive(Long arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntityByPkIsActive(Long arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyType getEntiyByPK(Long arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalDataIsActive(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(AppraisalCompetencyType arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppraisalCompetencyType saveData(AppraisalCompetencyType arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(AppraisalCompetencyType arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppraisalCompetencyType saveOrUpdateData(AppraisalCompetencyType arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(AppraisalCompetencyType arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AppraisalCompetencyType arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppraisalCompetencyType updateData(AppraisalCompetencyType arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalCompetencyType> getListByParam(AppraisalCompetencyTypeSearchParameter searchParameter,
			int firstResult, int maxResult, Order order) throws Exception {
		return appraisalCompetencyTypeDao.getListByParam(searchParameter, firstResult, maxResult, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(AppraisalCompetencyTypeSearchParameter searchParameter) throws Exception {
		return appraisalCompetencyTypeDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveDataCompetenceType(AppraisalCompetencyType competencyType, List<Long> listIdGolonganJabatan)	throws Exception {
		String result = "error";
		
		String createdBy = HrmUserInfoUtil.getUserName();
		Date createdOn = new Date();
		
		competencyType.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
		competencyType.setCreatedBy(createdBy);
		competencyType.setCreatedOn(createdOn);
		appraisalCompetencyTypeDao.save(competencyType);
		
		for(Long golJabatanId : listIdGolonganJabatan){
			AppraisalCompetencyTypeGolJab apprCompTypeGolJab = generateAppraisalCompetencyTypeGolJab(golJabatanId, competencyType);
			apprCompTypeGolJab.setCreatedBy(createdBy);
			apprCompTypeGolJab.setCreatedOn(createdOn);
			appraisalCompetencyTypeGolJabDao.save(apprCompTypeGolJab);
		}
		
		result = "success";
		return result;
	}
	
	private AppraisalCompetencyTypeGolJab generateAppraisalCompetencyTypeGolJab(Long golJabatanId, AppraisalCompetencyType competencyType ){
		GolonganJabatan golonganJabatan = golonganJabatanDao.getEntiyByPK(golJabatanId);
		AppraisalCompetencyTypeGolJab apprCompTypeGolJab = new AppraisalCompetencyTypeGolJab();
		apprCompTypeGolJab.setId(new AppraisalCompetencyTypeGolJabId(competencyType.getId(), golJabatanId));
		apprCompTypeGolJab.setAppraisalCompetencyType(competencyType);
		apprCompTypeGolJab.setGolonganJabatan(golonganJabatan);
		return apprCompTypeGolJab;
		
	}

	
}
