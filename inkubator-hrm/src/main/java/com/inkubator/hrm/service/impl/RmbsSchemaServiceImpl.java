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

import ch.lambdaj.Lambda;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.ApprovalDefinitionRmbsSchemaDao;
import com.inkubator.hrm.dao.RmbsSchemaDao;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchema;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchemaId;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.service.RmbsSchemaService;
import com.inkubator.hrm.web.search.RmbsSchemaSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

@Service(value = "rmbsSchemaService")
@Lazy
public class RmbsSchemaServiceImpl extends BaseApprovalConfigurationServiceImpl<RmbsSchema> implements RmbsSchemaService {

	@Autowired
	private RmbsSchemaDao rmbsSchemaDao;
	@Autowired
	private ApprovalDefinitionRmbsSchemaDao approvalDefinitionRmbsSchemaDao;
	
	@Override
	public RmbsSchema getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RmbsSchema getEntiyByPK(Long id) throws Exception {
		return rmbsSchemaDao.getEntiyByPK(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(RmbsSchema entity) throws Exception {
		// check duplicate name
		long totalDuplicates = rmbsSchemaDao.getTotalByName(entity.getName());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = rmbsSchemaDao.getTotalByCode(entity.getCode());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		// check duplicate nomor SK
		totalDuplicates = rmbsSchemaDao.getTotalByNomorSk(entity.getNomorSk());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setIsActive(Boolean.TRUE);
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		rmbsSchemaDao.save(entity);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(RmbsSchema entity) throws Exception {
		// check duplicate name
		long totalDuplicates = rmbsSchemaDao.getTotalByNameAndNotId(entity.getName(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_name");
		}
		// check duplicate code
		totalDuplicates = rmbsSchemaDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}
		// check duplicate nomor SK
		totalDuplicates = rmbsSchemaDao.getTotalByNomorSkAndNotId(entity.getNomorSk(), entity.getId());
		if (totalDuplicates > 0) {
			throw new BussinessException("leave.error_duplicate_code");
		}	
		
		RmbsSchema rmbsSchema =  rmbsSchemaDao.getEntiyByPK(entity.getId());
		rmbsSchema.setCode(entity.getCode());
		rmbsSchema.setName(entity.getName());
		rmbsSchema.setDescription(entity.getDescription());
		rmbsSchema.setMaxTotalReimburst(entity.getMaxTotalReimburst());
		rmbsSchema.setMaxWithoutReceiptPerType(entity.getMaxWithoutReceiptPerType());
		rmbsSchema.setMaxWithReceiptPerType(entity.getMaxWithReceiptPerType());
		rmbsSchema.setNomorSk(entity.getNomorSk());
		rmbsSchema.setNoticeForLimit(entity.getNoticeForLimit());
		rmbsSchema.setSubmissionDeadline(entity.getSubmissionDeadline());
		rmbsSchema.setIsActive(entity.getIsActive());
		rmbsSchema.setUpdatedBy(UserInfoUtil.getUserName());
		rmbsSchema.setUpdatedOn(new Date());
		rmbsSchemaDao.update(rmbsSchema);
	}

	@Override
	public void saveOrUpdate(RmbsSchema enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RmbsSchema saveData(RmbsSchema entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema updateData(RmbsSchema entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema saveOrUpdateData(RmbsSchema entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RmbsSchema getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(RmbsSchema entity) throws Exception {
		rmbsSchemaDao.delete(entity);

	}

	@Override
	public void softDelete(RmbsSchema entity) throws Exception {
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
	public List<RmbsSchema> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsSchema> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsSchema> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsSchema> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsSchema> getAllDataPageAble(int firstResult, int maxResults,
			Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsSchema> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsSchema> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmbsSchema> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RmbsSchema> getByParam(RmbsSchemaSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return rmbsSchemaDao.getByParam(parameter, firstResult, maxResults, orderable);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(RmbsSchemaSearchParameter parameter) throws Exception {
		return rmbsSchemaDao.getTotalByParam(parameter);
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RmbsSchema> getAllDataByStatusActive() throws Exception {
		return rmbsSchemaDao.getAllDataByStatusActive();
		
	}

	@Override
	protected void saveManyToMany(ApprovalDefinition appDef, RmbsSchema entity) {
		ApprovalDefinitionRmbsSchema approvalDefinitionRmbsSchema =  new ApprovalDefinitionRmbsSchema();
		approvalDefinitionRmbsSchema.setId(new ApprovalDefinitionRmbsSchemaId(appDef.getId(), entity.getId()));
		approvalDefinitionRmbsSchema.setApprovalDefinition(appDef);
		approvalDefinitionRmbsSchema.setRmbsSchema(entity);
		approvalDefinitionRmbsSchemaDao.save(approvalDefinitionRmbsSchema);
		
	}

	@Override
	protected void deleteManyToMany(Object entity) {
		approvalDefinitionRmbsSchemaDao.delete((ApprovalDefinitionRmbsSchema) entity);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveApprovalConf(ApprovalDefinition appDef, Long rmbsSchemaId) throws Exception {
		RmbsSchema entity = rmbsSchemaDao.getEntiyByPK(rmbsSchemaId);
		List<ApprovalDefinitionRmbsSchema> approvalDefinitionRmbsSchemas = approvalDefinitionRmbsSchemaDao.getAllDataByRmbsSchemaId(rmbsSchemaId);
		List<ApprovalDefinition> listAppDef = Lambda.extract(approvalDefinitionRmbsSchemas, Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
		listAppDef.add(appDef);
		
		/** validasi approval definition conf */
		super.validateApprovalConf(listAppDef);
		
		/** saving process */
		super.saveApprovalConf(appDef, entity);		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateApprovalConf(ApprovalDefinition appDef, Long rmbsSchemaId) throws Exception {
		List<ApprovalDefinitionRmbsSchema> approvalDefinitionRmbsSchemas = approvalDefinitionRmbsSchemaDao.getAllDataByRmbsSchemaId(rmbsSchemaId);
		List<ApprovalDefinition> listAppDef = Lambda.extract(approvalDefinitionRmbsSchemas, Lambda.on(ApprovalDefinitionRmbsSchema.class).getApprovalDefinition());
		listAppDef.remove(appDef);
		listAppDef.add(appDef);
		
		/** validasi approval definition conf */
		super.validateApprovalConf(listAppDef);
		
		/** updating process */
		super.updateApprovalDefinition(appDef);		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteApprovalconf(Long appDefId, Long rmbsSchemaId) throws Exception {
		ApprovalDefinitionRmbsSchema approvalDefinitionRmbsSchema = approvalDefinitionRmbsSchemaDao.getEntityByPk(appDefId, rmbsSchemaId);
		
		/** deleting process */
		super.deleteApprovalConf(approvalDefinitionRmbsSchema, appDefId);		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public RmbsSchema getEntityByPkFetchApprovalDefinition(Long id) throws Exception {
		return rmbsSchemaDao.getEntityByPkFetchApprovalDefinition(id);
		
	}

}
