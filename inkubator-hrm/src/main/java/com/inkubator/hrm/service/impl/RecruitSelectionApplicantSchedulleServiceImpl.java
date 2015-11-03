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
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.RecruitApplicantDao;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.dao.RecruitSelectionTypeDao;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDao;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDetailDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.SelectionPositionPassedViewModel;

/**
 *
 * @author rizkykojek
 */
@Service(value = "recruitSelectionApplicantSchedulleService")
@Lazy
public class RecruitSelectionApplicantSchedulleServiceImpl extends IServiceImpl	implements RecruitSelectionApplicantSchedulleService {

	@Autowired
	private RecruitSelectionApplicantSchedulleDao recruitSelectionApplicantSchedulleDao;
	@Autowired
	private RecruitSelectionApplicantSchedulleDetailDao recruitSelectionApplicantSchedulleDetailDao;
	@Autowired
	private RecruitHireApplyDao recruitHireApplyDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private RecruitmenSelectionSeriesDao recruitmenSelectionSeriesDao;
	@Autowired
	private RecruitmenSelectionSeriesDetailDao recruitmenSelectionSeriesDetailDao;
	@Autowired
	private RecruitApplicantDao recruitApplicantDao;
	@Autowired
	private RecruitSelectionTypeDao recruitSelectionTypeDao;
	
	@Override
	public RecruitSelectionApplicantSchedulle getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitSelectionApplicantSchedulle getEntiyByPK(Long id) throws Exception {
		
		return recruitSelectionApplicantSchedulleDao.getEntiyByPK(id);
	}

	@Override
	public void save(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(RecruitSelectionApplicantSchedulle enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RecruitSelectionApplicantSchedulle saveData(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle updateData(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle saveOrUpdateData(RecruitSelectionApplicantSchedulle entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecruitSelectionApplicantSchedulle entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(RecruitSelectionApplicantSchedulle entity) throws Exception {
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
	public List<RecruitSelectionApplicantSchedulle> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulle> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitSelectionApplicantSchedulle getEntityByPkWithDetail(Long id) {
		
		return recruitSelectionApplicantSchedulleDao.getEntityByPkWithDetail(id);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<SelectionPositionPassedViewModel> getSelectionPositionPassedByParam(String parameter, int firstResults, int maxResults, Order orderable) throws Exception {

		return recruitSelectionApplicantSchedulleDao.getSelectionPositionPassedByParam(parameter, firstResults, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalSelectionPositionPassedByParam(String parameter) throws Exception {
		
		return recruitSelectionApplicantSchedulleDao.getTotalSelectionPositionPassedByParam(parameter);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveData(RecruitSelectionApplicantSchedulle recruitSelectionchedulle, List<RecruitSelectionApplicantSchedulleDetail> listRecruitSelectionScheduleDetail)	throws Exception {
		
		String result = "error";
		String createBy = HrmUserInfoUtil.getUserName();
		Date createOn = new Date(); 
		
		recruitSelectionchedulle.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		recruitSelectionchedulle.setCreatedBy(createBy);
		recruitSelectionchedulle.setCreatedOn(createOn);
		
		RecruitHireApply recruitHireApply = recruitHireApplyDao.getEntiyByPK(recruitSelectionchedulle.getHireApply().getId());
		EmpData empData = empDataDao.getEntiyByPK(recruitSelectionchedulle.getEmpData().getId());
		RecruitmenSelectionSeries recruitmenSelectionSeries = recruitmenSelectionSeriesDao.getEntiyByPK(recruitSelectionchedulle.getSelectionSeries().getId());
		
		recruitSelectionchedulle.setHireApply(recruitHireApply);
		recruitSelectionchedulle.setEmpData(empData);
		recruitSelectionchedulle.setSelectionSeries(recruitmenSelectionSeries);
		recruitSelectionApplicantSchedulleDao.save(recruitSelectionchedulle);
		
		for(RecruitSelectionApplicantSchedulleDetail scheduleDetail : listRecruitSelectionScheduleDetail){
			
			scheduleDetail.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			scheduleDetail.setCreatedBy(createBy);
			scheduleDetail.setCreatedOn(createOn);
			
			RecruitApplicant recruitApplicant = recruitApplicantDao.getEntiyByPK(scheduleDetail.getApplicant().getId());
			EmpData empDataScheduleDetail = empDataDao.getEntiyByPK(scheduleDetail.getEmpData().getId());
			RecruitSelectionType recruitSelectionType = recruitSelectionTypeDao.getEntiyByPK(scheduleDetail.getSelectionType().getId());
			
			scheduleDetail.setApplicant(recruitApplicant);
			scheduleDetail.setEmpData(empDataScheduleDetail);
			scheduleDetail.setRecruitSelectionApplicantSchedulle(recruitSelectionchedulle);
			scheduleDetail.setSelectionType(recruitSelectionType);
			
			recruitSelectionApplicantSchedulleDetailDao.save(scheduleDetail);
			
		}
		
		result = "success";
		
		return result;
	}

}
