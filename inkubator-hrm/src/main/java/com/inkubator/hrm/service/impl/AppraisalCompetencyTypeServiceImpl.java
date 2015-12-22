package com.inkubator.hrm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(AppraisalCompetencyType appraisalCompetencyType) throws Exception {
		
		List<AppraisalCompetencyTypeGolJab> listCompTypeGolJabToDelete = appraisalCompetencyTypeGolJabDao.getListByAppraisalCompetenceTypeId(appraisalCompetencyType.getId());
		for(AppraisalCompetencyTypeGolJab compTypeGolJab : listCompTypeGolJabToDelete){
			AppraisalCompetencyTypeGolJab compTypeGolJabToDelete = appraisalCompetencyTypeGolJabDao.getEntityByIdGolJabatanAndIdCompType(compTypeGolJab.getGolonganJabatan().getId(), appraisalCompetencyType.getId());
			appraisalCompetencyTypeGolJabDao.delete(compTypeGolJabToDelete);
		}
		
		AppraisalCompetencyType appraisalCompetencyTypeToDelete = appraisalCompetencyTypeDao.getEntiyByPK(appraisalCompetencyType.getId());
		appraisalCompetencyTypeDao.delete(appraisalCompetencyTypeToDelete);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<AppraisalCompetencyType> getAllData() throws Exception {
		
		return appraisalCompetencyTypeDao.getAllData();
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
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalCompetencyType getEntiyByPK(Long arg0) throws Exception {
		return appraisalCompetencyTypeDao.getEntiyByPK(arg0);
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
		
		//Check Duplicate Code
		Long totalDuplicates = appraisalCompetencyTypeDao.getTotalByCode(competencyType.getCode());
		if(totalDuplicates > 0){
			throw new BussinessException("career.competence_type_code_duplicate");
		}
		
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

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String updateDataCompetenceType(AppraisalCompetencyType competencyType, List<Long> listIdGolJabatan) throws Exception {
		
		//Check Duplicate Code
		Long totalDuplicates = appraisalCompetencyTypeDao.getTotalByCodeAndNotId(competencyType.getCode(), competencyType.getId());
		if(totalDuplicates > 0){
			throw new BussinessException("career.competence_type_code_duplicate");
		}
		
		String result = "error";
		String updatedBy = HrmUserInfoUtil.getUserName();
		Date updatedOn = new Date();
		
		AppraisalCompetencyType compTypeToUpdate = appraisalCompetencyTypeDao.getEntiyByPK(competencyType.getId());
		compTypeToUpdate.setCode(competencyType.getCode());
		compTypeToUpdate.setName(competencyType.getName());
		compTypeToUpdate.setDescription(competencyType.getDescription());
		compTypeToUpdate.setVisibility(competencyType.getVisibility());
		compTypeToUpdate.setUpdatedBy(updatedBy);
		compTypeToUpdate.setUpdatedOn(updatedOn);
		appraisalCompetencyTypeDao.update(compTypeToUpdate);
		
		//Dapatkan List AppraisalCompetencyTypeGolJab sebelumnya dari db
		List<AppraisalCompetencyTypeGolJab> listCompTypeGolJabFromDb = appraisalCompetencyTypeGolJabDao.getListByAppraisalCompetenceTypeId(competencyType.getId());
		
		//Extract IdGolongan Jabatannya
		List<Long> listIdGolJabatanFromDb = listCompTypeGolJabFromDb.stream()
				.map(appCompTypeGolJab -> appCompTypeGolJab.getGolonganJabatan().getId())
				.collect(Collectors.toList());
		
		//Filter List AppraisalCompetencyTypeGolJab dari db yang hendak di hapus dari view
		List<Long> listIdGolJabatanToDelete = listCompTypeGolJabFromDb.stream()
			.filter(compTypeGolJab -> !listIdGolJabatan.contains(compTypeGolJab.getGolonganJabatan().getId()))//Filter hanya id yang tidak ada dalam listIdGolJabatan yang dari view depan
			.map(compTypeGolJab -> compTypeGolJab.getGolonganJabatan().getId())// Extract Id Golongan Jabatan hasil filter 
			.collect(Collectors.toList());//Transform ke dalam List
		
		//Looping satu persatu untuk dihapus
		for(Long idGolJabToDelete : listIdGolJabatanToDelete){
			AppraisalCompetencyTypeGolJab compTypeGolJabToDelete = appraisalCompetencyTypeGolJabDao.getEntityByIdGolJabatanAndIdCompType(idGolJabToDelete, competencyType.getId());
			appraisalCompetencyTypeGolJabDao.delete(compTypeGolJabToDelete);
		}
		
		//Filter List AppraisalCompetencyTypeGolJab dari view depan yang yang belum ada di db, untuk di tambahkan
		List<Long> listIdGolJabatanToInsert = listIdGolJabatan.stream()
			.filter(idGolJabatan -> !listIdGolJabatanFromDb.contains(idGolJabatan))//Filter hanya id yang tidak ada dalam listIdGolJabatan yang dari db
			.collect(Collectors.toList());//Transform ke dalam List
		
		//Looping satu persatu untuk di save
		for(Long golJabatanId : listIdGolJabatanToInsert){
			AppraisalCompetencyTypeGolJab apprCompTypeGolJab = generateAppraisalCompetencyTypeGolJab(golJabatanId, compTypeToUpdate);
			apprCompTypeGolJab.setCreatedBy(updatedBy);
			apprCompTypeGolJab.setCreatedOn(updatedOn);
			appraisalCompetencyTypeGolJabDao.save(apprCompTypeGolJab);
		}
		
		result = "success";
		return result;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public AppraisalCompetencyType getEntityByIdWithDetail(Long id) throws Exception {
		List<AppraisalCompetencyTypeGolJab> listAppraisalCompetencyTypeGolJab = appraisalCompetencyTypeGolJabDao.getListByAppraisalCompetenceTypeId(id);
		AppraisalCompetencyType appraisalCompetencyType = appraisalCompetencyTypeDao.getEntiyByPK(id);
		appraisalCompetencyType.setAppraisalCompetencyTypeGolJabs(new HashSet<>(listAppraisalCompetencyTypeGolJab));
		return appraisalCompetencyType;
	}

	
}
