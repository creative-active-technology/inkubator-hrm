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
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.AppraisalCompetencyGroupDao;
import com.inkubator.hrm.dao.AppraisalCompetencyGroupKlasifikasiKerjaDao;
import com.inkubator.hrm.dao.AppraisalCompetencyTypeDao;
import com.inkubator.hrm.dao.KlasifikasiKerjaDao;
import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.entity.AppraisalCompetencyGroupKlasifikasiKerja;
import com.inkubator.hrm.entity.AppraisalCompetencyGroupKlasifikasiKerjaId;
import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.service.AppraisalCompetencyGroupService;
import com.inkubator.hrm.web.search.CompetencyGroupSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Service(value = "appraisalCompetencyGroupService")
@Lazy
public class AppraisalCompetencyGroupServiceImpl extends IServiceImpl implements AppraisalCompetencyGroupService {

	@Autowired
	private AppraisalCompetencyGroupDao appraisalCompetencyGroupDao;
	@Autowired
	private AppraisalCompetencyGroupKlasifikasiKerjaDao appraisalCompetencyGroupKlasifikasiKerjaDao;
	@Autowired
	private AppraisalCompetencyTypeDao appraisalCompetencyTypeDao;
	@Autowired
	private KlasifikasiKerjaDao klasifikasiKerjaDao;
	
	@Override
	public AppraisalCompetencyGroup getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(AppraisalCompetencyGroup enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AppraisalCompetencyGroup saveData(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup updateData(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup saveOrUpdateData(AppraisalCompetencyGroup entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppraisalCompetencyGroup getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(AppraisalCompetencyGroup entity) throws Exception {
		appraisalCompetencyGroupDao.delete(entity);

	}

	@Override
	public void softDelete(AppraisalCompetencyGroup entity) throws Exception {
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
	public List<AppraisalCompetencyGroup> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order,
			Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalCompetencyGroup> getAllDataByParam(CompetencyGroupSearchParameter searchParameter, int firstResult, int maxResult, Order order) throws Exception {
		// TODO Auto-generated method stub
		return appraisalCompetencyGroupDao.getAllDataByParam(searchParameter, firstResult, maxResult, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(CompetencyGroupSearchParameter searchParameter) throws Exception {
		// TODO Auto-generated method stub
		return appraisalCompetencyGroupDao.getTotalByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalCompetencyGroup getEntityByIdWithDetail(Long id) throws Exception {
		return appraisalCompetencyGroupDao.getEntityByIdWithDetail(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(AppraisalCompetencyGroup competencyGroup, List<KlasifikasiKerja> listKlasifikasiKerja) throws Exception {
		// check duplicate name
		long totalDuplicates = appraisalCompetencyGroupDao.getTotalByNameAndNotId(competencyGroup.getName(), competencyGroup.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_group.error_name_duplicate");
		}
		// check duplicate code
		totalDuplicates = appraisalCompetencyGroupDao.getTotalByCodeAndNotId(competencyGroup.getCode(), competencyGroup.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_group.error_code_duplicate");
		}
		
		AppraisalCompetencyGroup update = appraisalCompetencyGroupDao.getEntiyByPK(competencyGroup.getId());
		update.setCode(competencyGroup.getCode());
		update.setName(competencyGroup.getName());
		update.setDescription(competencyGroup.getDescription());
		update.setCompetencyType(appraisalCompetencyTypeDao.getEntiyByPK(competencyGroup.getCompetencyType().getId()));
		update.setUpdatedBy(UserInfoUtil.getUserName());
		update.setUpdatedOn(new Date());
		appraisalCompetencyGroupDao.update(update);
		
		//delete first, then save it again
		appraisalCompetencyGroupKlasifikasiKerjaDao.deleteByCompetencyGroupId(update.getId());
		
		for(KlasifikasiKerja klasifikasiKerja : listKlasifikasiKerja){
			AppraisalCompetencyGroupKlasifikasiKerja manyToMany = new AppraisalCompetencyGroupKlasifikasiKerja();
			manyToMany.setId(new AppraisalCompetencyGroupKlasifikasiKerjaId(competencyGroup.getId(), klasifikasiKerja.getId()));
			manyToMany.setAppraisalCompetencyGroup(update);
			manyToMany.setKlasifikasiKerja(klasifikasiKerjaDao.getEntiyByPK(klasifikasiKerja.getId()));
			manyToMany.setCreatedBy(UserInfoUtil.getUserName());
			manyToMany.setCreatedOn(new Date());
			appraisalCompetencyGroupKlasifikasiKerjaDao.save(manyToMany);
		}
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(AppraisalCompetencyGroup competencyGroup, List<KlasifikasiKerja> listKlasifikasiKerja) throws Exception {
		// check duplicate name
		long totalDuplicates = appraisalCompetencyGroupDao.getTotalByName(competencyGroup.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_group.error_name_duplicate");
		}
		// check duplicate code
		totalDuplicates = appraisalCompetencyGroupDao.getTotalByCode(competencyGroup.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("competency_group.error_code_duplicate");
		}
		
		AppraisalCompetencyType competencyType = appraisalCompetencyTypeDao.getEntiyByPK(competencyGroup.getCompetencyType().getId());
		
		competencyGroup.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		competencyGroup.setCompetencyType(competencyType);
		competencyGroup.setCreatedBy(UserInfoUtil.getUserName());
		competencyGroup.setCreatedOn(new Date());
		appraisalCompetencyGroupDao.save(competencyGroup);
		
		for(KlasifikasiKerja klasifikasiKerja : listKlasifikasiKerja){
			AppraisalCompetencyGroupKlasifikasiKerja manyToMany = new AppraisalCompetencyGroupKlasifikasiKerja();
			manyToMany.setId(new AppraisalCompetencyGroupKlasifikasiKerjaId(competencyGroup.getId(), klasifikasiKerja.getId()));
			manyToMany.setAppraisalCompetencyGroup(competencyGroup);
			manyToMany.setKlasifikasiKerja(klasifikasiKerjaDao.getEntiyByPK(klasifikasiKerja.getId()));
			manyToMany.setCreatedBy(UserInfoUtil.getUserName());
			manyToMany.setCreatedOn(new Date());
			appraisalCompetencyGroupKlasifikasiKerjaDao.save(manyToMany);
			
		}
	}

}
