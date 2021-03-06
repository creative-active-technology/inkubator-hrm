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
import com.inkubator.hrm.dao.RecruitMppApplyDetailDao;
import com.inkubator.hrm.dao.RecruitMppApplyDetailTimeDao;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppApplyDetailTime;
import com.inkubator.hrm.service.RecruitMppApplyDetailTimeService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Service(value = "recruitMppApplyDetailTimeService")
@Lazy
public class RecruitMppApplyDetailTimeServiceImpl extends IServiceImpl implements RecruitMppApplyDetailTimeService {
	
	 @Autowired
	 private RecruitMppApplyDetailTimeDao recruitMppApplyDetailTimeDao;
	 
	 @Autowired
	 private RecruitMppApplyDetailDao recruitMppApplyDetailDao;

	@Override
	public void delete(RecruitMppApplyDetailTime arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RecruitMppApplyDetailTime> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitMppApplyDetailTime> getAllData(Boolean arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitMppApplyDetailTime> getAllData(Integer arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitMppApplyDetailTime> getAllData(Byte arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitMppApplyDetailTime> getAllDataPageAble(int arg0,
			int arg1, Order arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitMppApplyDetailTime> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Boolean arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitMppApplyDetailTime> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Integer arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitMppApplyDetailTime> getAllDataPageAbleIsActive(int arg0,
			int arg1, Order arg2, Byte arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntityByPkIsActive(String arg0,
			Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntityByPkIsActive(String arg0,
			Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntityByPkIsActive(String arg0,
			Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntityByPkIsActive(Integer arg0,
			Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntityByPkIsActive(Integer arg0,
			Byte arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntityByPkIsActive(Integer arg0,
			Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntityByPkIsActive(Long arg0,
			Integer arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntityByPkIsActive(Long arg0, Byte arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntityByPkIsActive(Long arg0,
			Boolean arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntiyByPK(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitMppApplyDetailTime getEntiyByPK(Integer arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitMppApplyDetailTime getEntiyByPK(Long arg0) throws Exception {
		return recruitMppApplyDetailTimeDao.getEntiyByPK(arg0);
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
	public void save(RecruitMppApplyDetailTime arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecruitMppApplyDetailTime saveData(RecruitMppApplyDetailTime arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(RecruitMppApplyDetailTime arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecruitMppApplyDetailTime saveOrUpdateData(
			RecruitMppApplyDetailTime arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void softDelete(RecruitMppApplyDetailTime arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RecruitMppApplyDetailTime arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecruitMppApplyDetailTime updateData(RecruitMppApplyDetailTime arg0)	throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateActualAndDifferenceListMppDetailTimeBasedOnSelectedEntity(RecruitMppApplyDetailTime entity) throws Exception {
		RecruitMppApplyDetailTime mppDetailToUpdate = recruitMppApplyDetailTimeDao.getEntiyByPK(entity.getId());
		Date mppMonthApplyOfSelectedEntity = mppDetailToUpdate.getMppMonthApply();
		Integer difference = entity.getDifference();
		
		mppDetailToUpdate.setActual(entity.getActual());
		mppDetailToUpdate.setPlanningPerson(entity.getPlanningPerson());
		mppDetailToUpdate.setDifference(entity.getDifference());
		recruitMppApplyDetailTimeDao.update(mppDetailToUpdate);
		
		List<RecruitMppApplyDetailTime> listMppDetailTime = recruitMppApplyDetailTimeDao.getListWithMppMonthApplyLaterThanParam(entity.getRecruitMppApplyDetail().getId(), mppMonthApplyOfSelectedEntity);
		
		for(RecruitMppApplyDetailTime mppApplyDetailTime : listMppDetailTime){
			RecruitMppApplyDetailTime mppApplyDetailTimeLaterToUpdate = recruitMppApplyDetailTimeDao.getEntiyByPK(mppApplyDetailTime.getId());
			
			mppApplyDetailTimeLaterToUpdate.setActual(difference);
			difference = mppApplyDetailTimeLaterToUpdate.getPlanningPerson() == mppApplyDetailTimeLaterToUpdate.getActual() ? 0 : 
				mppApplyDetailTimeLaterToUpdate.getPlanningPerson() > mppApplyDetailTimeLaterToUpdate.getActual() ? 
						(mppApplyDetailTimeLaterToUpdate.getPlanningPerson() - mppApplyDetailTimeLaterToUpdate.getActual()) :
							(mppApplyDetailTimeLaterToUpdate.getActual() - mppApplyDetailTimeLaterToUpdate.getPlanningPerson());
						
			mppApplyDetailTimeLaterToUpdate.setDifference(difference);
			recruitMppApplyDetailTimeDao.update(mppApplyDetailTimeLaterToUpdate);
		}
		
	}


}
