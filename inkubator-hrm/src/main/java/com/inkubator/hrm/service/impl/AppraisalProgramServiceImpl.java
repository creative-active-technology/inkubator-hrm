package com.inkubator.hrm.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.AppraisalAchievementProgramDao;
import com.inkubator.hrm.dao.AppraisalIndisciplineProgramDao;
import com.inkubator.hrm.dao.AppraisalProgramDao;
import com.inkubator.hrm.dao.CareerAwardTypeDao;
import com.inkubator.hrm.dao.CareerDisciplineTypeDao;
import com.inkubator.hrm.entity.AppraisalAchievementProgram;
import com.inkubator.hrm.entity.AppraisalIndisciplineProgram;
import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.entity.CareerDisciplineType;
import com.inkubator.hrm.service.AppraisalProgramService;
import com.inkubator.hrm.web.model.AppraisalProgramModel;
import com.inkubator.hrm.web.search.AppraisalProgramSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */

@Service(value = "appraisalProgramService")
@Lazy
public class AppraisalProgramServiceImpl extends IServiceImpl implements AppraisalProgramService {

	@Autowired
	private AppraisalProgramDao appraisalProgramDao;
	@Autowired
	private AppraisalAchievementProgramDao appraisalAchievementProgramDao;
	@Autowired
	private AppraisalIndisciplineProgramDao appraisalIndisciplineProgramDao;
	@Autowired
	private CareerAwardTypeDao careerAwardTypeDao;
	@Autowired
	private CareerDisciplineTypeDao careerDisciplineTypeDao;
	
	@Override
	public AppraisalProgram getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AppraisalProgram getEntiyByPK(Long id) throws Exception {
		
		return appraisalProgramDao.getEntiyByPK(id);
	}

	@Override
	public void save(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(AppraisalProgram enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AppraisalProgram saveData(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram updateData(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram saveOrUpdateData(AppraisalProgram entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalProgram getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(AppraisalProgram entity) throws Exception {
		
		appraisalProgramDao.delete(entity);
	}

	@Override
	public void softDelete(AppraisalProgram entity) throws Exception {
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
	public List<AppraisalProgram> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalProgram> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalProgram> getAllDataByParam(AppraisalProgramSearchParameter searchParameter, int firstResult, int maxResult, Order order) throws Exception {
		
		return appraisalProgramDao.getAllDataByParam(searchParameter, firstResult, maxResult, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(AppraisalProgramSearchParameter searchParameter) throws Exception {

		return appraisalProgramDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalProgram getEntityByIdWithDetail(Long id) throws Exception {
		
		return appraisalProgramDao.getEntityByIdWithDetail(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(AppraisalProgramModel model) throws Exception {
		AppraisalProgram entity =  appraisalProgramDao.getEntiyByPK(model.getId());
		entity.setCode(model.getCode());
		entity.setName(model.getName());
		entity.setDescription(model.getDescription());
		entity.setEvalStartDate(model.getEvalStartDate());
		entity.setEvalEndDate(model.getEvalEndDate());
		entity.setIsAchievement(model.getIsAchievement());
		entity.setIsGapCompetency(model.getIsGapCompetency());
		entity.setIsIndiscipline(model.getIsIndiscipline());
		entity.setIsPerformanceScoring(model.getIsPerformanceScoring());
		entity.setUpdatedBy(UserInfoUtil.getUserName());
		entity.setUpdatedOn(new Date());
		appraisalProgramDao.update(entity);
		
		if(model.getIsAchievement()) {
			for(Map.Entry<Long, BigDecimal> achievement : model.getListAchievementScore().entrySet()){
				AppraisalAchievementProgram achievementProgram = appraisalAchievementProgramDao.getEntityByAppraisalProgramIdAndAwardTypeId(entity.getId(), achievement.getKey());
				if(achievementProgram == null){
					//save achievement score
					this.saveAchievementProgram(entity, achievement.getKey(), achievement.getValue().doubleValue());
					
				} else {
					//update achievement score
					achievementProgram.setScore(achievement.getValue().doubleValue());
					achievementProgram.setUpdatedBy(UserInfoUtil.getUserName());
					achievementProgram.setUpdatedOn(new Date());
					appraisalAchievementProgramDao.update(achievementProgram);
				}
			}
			
		} else {
			//delete all achievement score
			appraisalAchievementProgramDao.deleteByAppraisalProgramId(entity.getId());
			
		}
		
		if(model.getIsIndiscipline()) {
			for(Map.Entry<Long, BigDecimal> indiscipline : model.getListIndisciplineScore().entrySet()){
				AppraisalIndisciplineProgram indisciplineProgram = appraisalIndisciplineProgramDao.getEntityByAppraisalProgramIdAndDisciplineTypeId(entity.getId(), indiscipline.getKey());
				if(indisciplineProgram == null){
					//save indiscipline score
					this.saveIndisciplineProgram(entity, indiscipline.getKey(), indiscipline.getValue().doubleValue());
					
				} else {
					//update indiscipline score
					indisciplineProgram.setScore(indiscipline.getValue().doubleValue());
					indisciplineProgram.setUpdatedBy(UserInfoUtil.getUserName());
					indisciplineProgram.setUpdatedOn(new Date());
					appraisalIndisciplineProgramDao.update(indisciplineProgram);
				}
			}	
			
		} else {
			//delete all indiscipline score
			appraisalIndisciplineProgramDao.deleteByAppraisalProgramId(entity.getId());
			
		}
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(AppraisalProgramModel model) throws Exception {
		AppraisalProgram entity =  new AppraisalProgram();
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setCode(model.getCode());
		entity.setName(model.getName());
		entity.setDescription(model.getDescription());
		entity.setEvalStartDate(model.getEvalStartDate());
		entity.setEvalEndDate(model.getEvalEndDate());
		entity.setIsAchievement(model.getIsAchievement());
		entity.setIsGapCompetency(model.getIsGapCompetency());
		entity.setIsIndiscipline(model.getIsIndiscipline());
		entity.setIsPerformanceScoring(model.getIsPerformanceScoring());
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		appraisalProgramDao.save(entity);
		
		for(Map.Entry<Long, BigDecimal> achievement : model.getListAchievementScore().entrySet()){
			//save achievement score
			this.saveAchievementProgram(entity, achievement.getKey(), achievement.getValue().doubleValue());
		}
		
		for(Map.Entry<Long, BigDecimal> indiscipline : model.getListIndisciplineScore().entrySet()){
			//save indiscipline score
			this.saveIndisciplineProgram(entity, indiscipline.getKey(), indiscipline.getValue().doubleValue());
		}		
	}
	
	private void saveAchievementProgram(AppraisalProgram appraisalProgram, Long awardTypeId, Double score){
		CareerAwardType careerAwardType = careerAwardTypeDao.getEntiyByPK(awardTypeId);
		
		AppraisalAchievementProgram achievementProgram =  new AppraisalAchievementProgram();
		achievementProgram.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		achievementProgram.setAppraisalProgram(appraisalProgram);
		achievementProgram.setCareerAwardType(careerAwardType);
		achievementProgram.setScore(score);
		achievementProgram.setCreatedBy(UserInfoUtil.getUserName());
		achievementProgram.setCreatedOn(new Date());
		appraisalAchievementProgramDao.save(achievementProgram);
	}
	
	private void saveIndisciplineProgram(AppraisalProgram appraisalProgram, Long disciplineType, Double score){
		CareerDisciplineType careerDisciplineType = careerDisciplineTypeDao.getEntiyByPK(disciplineType);
		
		AppraisalIndisciplineProgram indisciplineProgram =  new AppraisalIndisciplineProgram();
		indisciplineProgram.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		indisciplineProgram.setAppraisalProgram(appraisalProgram);
		indisciplineProgram.setCareerDisciplineType(careerDisciplineType);
		indisciplineProgram.setScore(score);
		indisciplineProgram.setCreatedBy(UserInfoUtil.getUserName());
		indisciplineProgram.setCreatedOn(new Date());
		appraisalIndisciplineProgramDao.save(indisciplineProgram);
	}
	

}
