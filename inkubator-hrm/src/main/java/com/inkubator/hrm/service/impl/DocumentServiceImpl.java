package com.inkubator.hrm.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.DocumentDao;
import com.inkubator.hrm.entity.Document;
import com.inkubator.hrm.service.DocumentService;
import com.inkubator.hrm.web.search.DocumentSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

@Service(value = "documentService")
@Lazy
public class DocumentServiceImpl extends IServiceImpl implements DocumentService{
	
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private FacesIO facesIO;

	@Override
	public Document getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Document getEntiyByPK(Long id) throws Exception {
		return this.documentDao.getEntiyByPK(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Document entity, UploadedFile documentFile) throws Exception {
		//check duplicate data
		long totalDuplicate = documentDao.getTotalByCode(entity.getCode());
		if(totalDuplicate > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		entity.setCreatedBy(UserInfoUtil.getUserName());
		entity.setCreatedOn(new Date());
		this.documentDao.save(entity);
		
		if(documentFile != null){
			String uploadPath = getUploadPath(entity.getId(), documentFile);
			facesIO.transferFile(documentFile);
			File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
			file.renameTo(new File(uploadPath));
			
			entity.setUploadPath(uploadPath);
			this.documentDao.update(entity);
		}
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, noRollbackFor = IOException.class)
	public void update(Document entity, UploadedFile documentFile) throws Exception {
		long totalDuplicates = documentDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
		if(totalDuplicates > 0){
			throw new BussinessException("marital.error_duplicate_marital_code");
		}
		Document document = documentDao.getEntiyByPK(entity.getId());
		String uploadPath = document.getUploadPath();
		
		if(documentFile != null){
			//delete old file
			File oldFile = new File(document.getUploadPath());
			oldFile.delete();
			
			//add new file
			uploadPath = getUploadPath(document.getId(), documentFile);
			facesIO.transferFile(documentFile);
			File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
			file.renameTo(new File(uploadPath));
		}
		
		Document update = documentDao.getEntiyByPK(entity.getId());
		update.setCode(entity.getCode());
		update.setName(entity.getName());
		update.setDescription(entity.getDescription());
		update.setUploadPath(uploadPath);
		update.setUpdatedBy(UserInfoUtil.getUserName());
		update.setUpdatedOn(new Date());
		this.documentDao.update(update);
	}

	@Override
	public void saveOrUpdate(Document enntity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Document saveData(Document entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document updateData(Document entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document saveOrUpdateData(Document entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, noRollbackFor = IOException.class)
	public void delete(Document entity) throws Exception {
		//remove uploaded file
		try{
			File oldFile = new File(entity.getUploadPath());
			oldFile.delete();
		} catch(Exception ex){
			//if got an error during deleting file, system will continue deleting the recordl
		}
		
		//delete entity
		this.documentDao.delete(entity);
	}

	@Override
	public void softDelete(Document entity) throws Exception {
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
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<Document> getAllData() throws Exception {
		return this.documentDao.getAllData();
	}

	@Override
	public List<Document> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<Document> getByParam(DocumentSearchParameter searchParameter, int firstResult, int maxResults,
			Order order) throws Exception {
		return this.documentDao.getByParam(searchParameter, firstResult, maxResults, order);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(DocumentSearchParameter searchParameter) throws Exception {
		return this.documentDao.getTotalByParam(searchParameter);
	}

	@Override
	public void save(Document entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Document entity) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private String getUploadPath(Long id, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "referencedoc_" + id + "." + extension;
        return uploadPath;
    }

}
