package com.inkubator.hrm.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.AppraisalCompetencyTypeGolJabDao;
import com.inkubator.hrm.entity.AppraisalCompetencyTypeGolJab;
import com.inkubator.hrm.service.AppraisalCompetencyTypeGolJabService;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Service(value = "appraisalCompetencyTypeGolJabService")
@Lazy
public class AppraisalCompetencyTypeGolJabServiceImpl extends IServiceImpl implements AppraisalCompetencyTypeGolJabService {
	
	@Autowired
	private AppraisalCompetencyTypeGolJabDao appraisalCompetencyTypeGolJabDao;

	@Override
	public void delete(AppraisalCompetencyTypeGolJab arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AppraisalCompetencyTypeGolJab> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyTypeGolJab> getAllData(Boolean arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyTypeGolJab> getAllData(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyTypeGolJab> getAllData(Byte arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyTypeGolJab> getAllDataPageAble(int arg0, int arg1, Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyTypeGolJab> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Boolean arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyTypeGolJab> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Integer arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyTypeGolJab> getAllDataPageAbleIsActive(int arg0, int arg1, Order arg2, Byte arg3)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntityByPkIsActive(String arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntityByPkIsActive(String arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntityByPkIsActive(String arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntityByPkIsActive(Integer arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntityByPkIsActive(Integer arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntityByPkIsActive(Integer arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntityByPkIsActive(Long arg0, Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntityByPkIsActive(Long arg0, Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntityByPkIsActive(Long arg0, Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntiyByPK(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyTypeGolJab getEntiyByPK(Long arg0) throws Exception {
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
	public void save(AppraisalCompetencyTypeGolJab arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppraisalCompetencyTypeGolJab saveData(AppraisalCompetencyTypeGolJab arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(AppraisalCompetencyTypeGolJab arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppraisalCompetencyTypeGolJab saveOrUpdateData(AppraisalCompetencyTypeGolJab arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(AppraisalCompetencyTypeGolJab arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AppraisalCompetencyTypeGolJab arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppraisalCompetencyTypeGolJab updateData(AppraisalCompetencyTypeGolJab arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalCompetencyTypeGolJab> getListByAppraisalCompetenceTypeId(Long appraisalCompetenceTypeId) throws Exception {
		return appraisalCompetencyTypeGolJabDao.getListByAppraisalCompetenceTypeId(appraisalCompetenceTypeId);
	}

}
