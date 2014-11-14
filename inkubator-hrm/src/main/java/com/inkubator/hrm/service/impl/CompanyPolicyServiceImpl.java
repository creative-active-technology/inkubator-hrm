package com.inkubator.hrm.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.inkubator.hrm.dao.CompanyPolicyDao;
import com.inkubator.hrm.dao.DepartmentDao;
import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.entity.CompanyPolicyJabatan;
import com.inkubator.hrm.entity.CompanyPolicyJabatanId;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.CompanyPolicyService;
import com.inkubator.hrm.web.search.CompanyPolicySearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

/**
 *
 * @author rizkykojek
 */
@Service(value = "companyPolicyService")
@Lazy
public class CompanyPolicyServiceImpl extends IServiceImpl implements CompanyPolicyService {

	@Autowired
	private CompanyPolicyDao companyPolicyDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
    private FacesIO facesIO;
	
	@Override
	public CompanyPolicy getEntiyByPK(String id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntiyByPK(Integer id) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public CompanyPolicy getEntiyByPK(Long id) throws Exception {
		return companyPolicyDao.getEntiyByPK(id);

	}

	@Override
	public void save(CompanyPolicy entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void update(CompanyPolicy entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public void saveOrUpdate(CompanyPolicy enntity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy saveData(CompanyPolicy entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy updateData(CompanyPolicy entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy saveOrUpdateData(CompanyPolicy entity)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntityByPkIsActive(String id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntityByPkIsActive(String id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntityByPkIsActive(String id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntityByPkIsActive(Integer id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntityByPkIsActive(Integer id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntityByPkIsActive(Integer id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntityByPkIsActive(Long id, Integer isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntityByPkIsActive(Long id, Byte isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public CompanyPolicy getEntityByPkIsActive(Long id, Boolean isActive)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(CompanyPolicy entity) throws Exception {
		//remove physical file
    	try {
	        File oldFile = new File(entity.getAttachFilePath());
	        oldFile.delete();
    	} catch (Exception e){
    		//if any error when removing file, system will continue deleting the record
    	}
    	
    	//remove entity
		companyPolicyDao.delete(entity);

	}

	@Override
	public void softDelete(CompanyPolicy entity) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalData() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public Long getTotalDataIsActive(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS, timeout = 50)
	public List<CompanyPolicy> getAllData() throws Exception {
		return companyPolicyDao.getAllData();

	}

	@Override
	public List<CompanyPolicy> getAllData(Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<CompanyPolicy> getAllData(Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<CompanyPolicy> getAllData(Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<CompanyPolicy> getAllDataPageAble(int firstResult,
			int maxResults, Order order) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<CompanyPolicy> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Boolean isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<CompanyPolicy> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Integer isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	public List<CompanyPolicy> getAllDataPageAbleIsActive(int firstResult,
			int maxResults, Order order, Byte isActive) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<CompanyPolicy> getByParam(CompanyPolicySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return companyPolicyDao.getByParam(parameter, firstResult, maxResults, orderable);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(CompanyPolicySearchParameter parameter) throws Exception {
		return companyPolicyDao.getTotalByParam(parameter);

	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public CompanyPolicy getEntityByPkWithDetail(Long id) throws Exception {
		return companyPolicyDao.getEntityByPkWithDetail(id);

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(CompanyPolicy entity, UploadedFile attachmentFile, List<Department> departments, List<GolonganJabatan> golonganJabatans) throws Exception {
		
		for(Department dept : departments){
			Department department = departmentDao.getEntiyByPK(dept.getId());
			CompanyPolicy compPolicy = new CompanyPolicy();
			org.springframework.beans.BeanUtils.copyProperties(entity, compPolicy); //copy all properties in entity object to compPolicy
			
			compPolicy.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));			
			compPolicy.setDepartment(department);
			compPolicy.setCreatedBy(UserInfoUtil.getUserName());
			compPolicy.setCreatedOn(new Date());
	        
	        //set children (companyPolicyJabatans)
	        Set<CompanyPolicyJabatan> listCompanyPolicyJabatans = new HashSet<CompanyPolicyJabatan>();
	        for (GolonganJabatan golJabatan : golonganJabatans) {
				CompanyPolicyJabatan companyPolicyJabatan = new CompanyPolicyJabatan();
				companyPolicyJabatan.setId(new CompanyPolicyJabatanId(compPolicy.getId(), golJabatan.getId()));
				companyPolicyJabatan.setCompanyPolicy(compPolicy);
				companyPolicyJabatan.setGolonganJabatan(golJabatan);
				listCompanyPolicyJabatans.add(companyPolicyJabatan);
			}
	        
	        compPolicy.setCompanyPolicyJabatans(listCompanyPolicyJabatans);
	        companyPolicyDao.save(compPolicy);
	        
	        //upload attachment file to server
			if (attachmentFile != null) {
	            String attachFilePath = getAttachFilePath(compPolicy.getId(), attachmentFile);
	            facesIO.transferFile(attachmentFile);
	            File file = new File(facesIO.getPathUpload() + attachmentFile.getFileName());
	            file.renameTo(new File(attachFilePath));

	            compPolicy.setAttachFilePath(attachFilePath);
	            companyPolicyDao.update(compPolicy);
	        }
		}
	}
	
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(CompanyPolicy entity, UploadedFile attachmentFile, List<GolonganJabatan> golonganJabatans) throws Exception {
		
		CompanyPolicy compPolicy = companyPolicyDao.getEntiyByPK(entity.getId());
		String attachFilePath = compPolicy.getAttachFilePath();
		
		if (attachmentFile != null) {
            //remove old file
			try {
				File oldFile = new File(compPolicy.getAttachFilePath());
	            oldFile.delete();
			} catch (Exception ex){
	    		//if any error when removing file, system will continue process
	    	}

            //added new file
            attachFilePath = getAttachFilePath(compPolicy.getId(), attachmentFile);
            facesIO.transferFile(attachmentFile);
            File file = new File(facesIO.getPathUpload() + attachmentFile.getFileName());
            file.renameTo(new File(attachFilePath));
        }
		
		compPolicy.setSubjectTitle(entity.getSubjectTitle());
		compPolicy.setEffectiveDate(entity.getEffectiveDate());
		compPolicy.setContentPolicy(entity.getContentPolicy());
		Department department = departmentDao.getEntiyByPK(entity.getDepartment().getId());
		compPolicy.setDepartment(department);
		compPolicy.setAttachFilePath(attachFilePath);
		compPolicy.setIsBroadcast(entity.getIsBroadcast());
		compPolicy.setRepeatOn(entity.getRepeatOn());
		compPolicy.setIsUseAttachment(entity.getIsUseAttachment());
		
		//set children (companyPolicyJabatans)
        Set<CompanyPolicyJabatan> listCompanyPolicyJabatans = new HashSet<CompanyPolicyJabatan>();
        for (GolonganJabatan golJabatan : golonganJabatans) {
			CompanyPolicyJabatan companyPolicyJabatan = new CompanyPolicyJabatan();
			companyPolicyJabatan.setId(new CompanyPolicyJabatanId(compPolicy.getId(), golJabatan.getId()));
			companyPolicyJabatan.setCompanyPolicy(compPolicy);
			companyPolicyJabatan.setGolonganJabatan(golJabatan);
			listCompanyPolicyJabatans.add(companyPolicyJabatan);
		}
        
        /* 
         * compPolicy.setCompanyPolicyJabatans(listCompanyPolicyJabatans);
         * when saving many to many or childrens objects, if you do (like above) code, it will shown an error
         * Instead of replacing the set by another one, clear the set and add the new children to the cleared set (like below code)           
        */        
        compPolicy.getCompanyPolicyJabatans().clear();
        compPolicy.getCompanyPolicyJabatans().addAll(listCompanyPolicyJabatans);
        
        /* Hibernate merge method will force Hibernate to copy any changes from other detached instances onto the instance you want to save, 
         * and thus merges all the changes in memory before the save. It use when dealing with many to many saving relationship */
        this.companyPolicyDao.updateAndMerge(compPolicy);
		
	}
	
	private String getAttachFilePath(Long id, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "companypolicy_" + id + "." + extension;
        return uploadPath;
    }
}
