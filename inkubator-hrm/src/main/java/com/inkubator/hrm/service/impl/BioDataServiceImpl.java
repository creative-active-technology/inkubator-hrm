/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioDocumentDao;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.dao.DialectDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.MaritalStatusDao;
import com.inkubator.hrm.dao.NationalityDao;
import com.inkubator.hrm.dao.RaceDao;
import com.inkubator.hrm.dao.ReligionDao;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioDocument;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.util.CommonReportUtil;
import com.inkubator.hrm.web.search.BioDataSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "bioDataService")
@Lazy
public class BioDataServiceImpl extends IServiceImpl implements BioDataService {

    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private MaritalStatusDao maritalStatusDao;
    @Autowired
    private DialectDao dialectDao;
    @Autowired
    private NationalityDao nationalityDao;
    @Autowired
    private RaceDao raceDao;
    @Autowired
    private ReligionDao religionDao;
    @Autowired
    private FacesIO facesIO;
    @Autowired
    private BioDocumentDao bioDocumentDao;
    @Autowired
    private EmpDataDao empDataDao;

    @Override
    public BioData getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioData getEntiyByPK(Long id) throws Exception {
        BioData bioData = bioDataDao.getEntiyByPK(id);
        /*bioData.getCity().getCityName();
        bioData.getReligion().getName();
        bioData.getMaritalStatus().getName();
        bioData.getNationality().getNationalityName();
        bioData.getRace().getRaceName();
        bioData.getDialect().getDialectName();*/
        return bioData;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(BioData entity) throws Exception {
    	if(StringUtils.isNotBlank(entity.getNpwp())){
    		Long totalDuplicateNpwp = bioDataDao.getTotalByNpwp(entity.getNpwp());
        	if(totalDuplicateNpwp > 0){
        		throw new BussinessException("biodata.error_duplicate_npwp");
        	}
    	}
    	
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        entity.setCity(this.cityDao.getEntiyByPK(entity.getCity().getId()));
        entity.setDialect(this.dialectDao.getEntiyByPK(entity.getDialect().getId()));
        entity.setMaritalStatus(this.maritalStatusDao.getEntiyByPK(entity.getMaritalStatus().getId()));
        entity.setNationality(nationalityDao.getEntiyByPK(entity.getNationality().getId()));
        entity.setRace(this.raceDao.getEntiyByPK(entity.getRace().getId()));
        entity.setReligion(this.religionDao.getEntiyByPK(entity.getReligion().getId()));
        this.bioDataDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioData entity) throws Exception {
    	if(StringUtils.isNotBlank(entity.getNpwp())){
    		Long totalDuplicateNpwp = bioDataDao.getTotalByNpwpAndNotId(entity.getNpwp(), entity.getId());
        	System.out.println(totalDuplicateNpwp);
        	if(totalDuplicateNpwp > 0){
        		throw new BussinessException("biodata.error_duplicate_npwp");
        	}
    	}
    	
        BioData bioData = this.bioDataDao.getEntiyByPK(entity.getId());
        bioData.setBloodType(entity.getBloodType());
        bioData.setCity(this.cityDao.getEntiyByPK(entity.getCity().getId()));
        bioData.setBirthplaceText(entity.getBirthplaceText());
        bioData.setDateOfBirth(entity.getDateOfBirth());
        bioData.setDialect(this.dialectDao.getEntiyByPK(entity.getDialect().getId()));
        bioData.setFirstName(entity.getFirstName());
        bioData.setMiddleName(entity.getMiddleName());
        bioData.setGender(entity.getGender());
        bioData.setJamsostek(entity.getJamsostek());
        bioData.setLastName(entity.getLastName());
        bioData.setMaritalStatus(this.maritalStatusDao.getEntiyByPK(entity.getMaritalStatus().getId()));
        bioData.setMobilePhone(entity.getMobilePhone());
        bioData.setNationality(nationalityDao.getEntiyByPK(entity.getNationality().getId()));
        bioData.setNickname(entity.getNickname());
        bioData.setNoKK(entity.getNoKK());
        bioData.setNpwp(entity.getNpwp());
        if (entity.getPathFinger() != null) {
            bioData.setPathFinger(entity.getPathFinger());
        }
        if (entity.getPathFoto() != null) {
            bioData.setPathFoto(entity.getPathFoto());
        }
        if (entity.getPathSignature() != null) {
            bioData.setPathSignature(entity.getPathSignature());
        }
        bioData.setPersonalEmail(entity.getPersonalEmail());
        bioData.setRace(this.raceDao.getEntiyByPK(entity.getRace().getId()));
        bioData.setReligion(this.religionDao.getEntiyByPK(entity.getReligion().getId()));
        bioData.setTitle(entity.getTitle());
        bioData.setUpdatedBy(UserInfoUtil.getUserName());
        bioData.setUpdatedOn(new Date());
        this.bioDataDao.update(bioData);
    }

    @Override
    public void saveOrUpdate(BioData enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData saveData(BioData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData updateData(BioData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData saveOrUpdateData(BioData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioData getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = DataIntegrityViolationException.class, noRollbackFor = IOException.class)
    public void delete(BioData entity) throws Exception {
        this.bioDataDao.delete(entity);
//        if (entity.getPathFoto() != null || entity.getPathFoto().isEmpty()) {

//        }
    }

    @Override
    public void softDelete(BioData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<BioData> getAllData() throws Exception {
        return bioDataDao.getAllData();
    }

    @Override
    public List<BioData> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioData> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioData> getByParam(BioDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.bioDataDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(BioDataSearchParameter parameter) throws Exception {
        return this.bioDataDao.getTotalByParam(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioData getEntityByPKWithDetail(long id) throws Exception {
        return bioDataDao.getEntityByPKWithDetail(id);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    @Override
    public List<BioData> getByName(String name) throws Exception {
        return this.bioDataDao.getByName(name);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public StreamedContent generateCV(long id) throws Exception {
        /*GsonBuilder gsonBuilder = new GsonBuilder();
         gsonBuilder.setDateFormat("dd MMMM yyyy");
         gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
         gsonBuilder.setExclusionStrategies(new EntityExclusionStrategy());
         Gson gson = gsonBuilder.create();
         JsonParser parser = new JsonParser();		
         JsonObject json = new JsonObject();
		
         BioData bioData = bioDataDao.getEntiyByPK(id);
         boolean isBioDataExist = bioData != null;		
         json.addProperty("isBioDataExist", isBioDataExist);
         if(isBioDataExist){
         json.add("bioData", parser.parse(gson.toJson(bioData)));
         }*/

        BioData bioData = bioDataDao.getEntiyByPK(id);
        List<BioDocument> bioDocuments = bioDocumentDao.getAllDataByBioDataId(id);
        List<String> attachments = new ArrayList<String>();
        for (BioDocument document : bioDocuments) {
            if (StringUtils.isNotEmpty(document.getUploadPath())) {
                attachments.add(document.getUploadPath());
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("BIODATA_ID", id);
        params.put("IS_RENDER_ADDRESS", !bioData.getBioAddresses().isEmpty());
        params.put("IS_RENDER_EDU_HISTORY", !bioData.getEducationHistories().isEmpty());
        params.put("IS_RENDER_ID_CARD", !bioData.getBioIdCards().isEmpty());
        params.put("IS_RENDER_EMP_HISTORY", !bioData.getBioEmploymentHistories().isEmpty());
        params.put("SUBREPORT_DIR", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/"));
        StreamedContent file = CommonReportUtil.exportReportToPDFStreamWithAttachment("cv_builder.jasper", params, bioData.getFirstName() + ".pdf", attachments);
        return file;
    }
    
    @Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updatePhoto(String nik, MultipartFile photoFile) throws Exception {
		EmpData empData = empDataDao.getEntityByNik(nik);
		BioData bioData = empData.getBioData();
		
		//delete old photo (if any)
		if(bioData.getPathFoto() != null) {
			File oldFile = new File(bioData.getPathFoto());            
			FileUtils.deleteQuietly(oldFile);
		}
        
        //save new photo to disk
		String pathPhoto = facesIO.getPathUpload() + bioData.getId() + "_" + photoFile.getOriginalFilename();
		File file = new File(pathPhoto);
		photoFile.transferTo(file);		
		
		//save new path photo
		bioData.setPathFoto(pathPhoto);
		bioDataDao.update(bioData);
		
	}
    
    @Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateSignature(String nik, MultipartFile signatureFile) throws Exception {
		EmpData empData = empDataDao.getEntityByNik(nik);
		BioData bioData = empData.getBioData();
		
		//delete old signature (if any)
		if(bioData.getPathSignature() != null) {
			File oldFile = new File(bioData.getPathSignature());            
			FileUtils.deleteQuietly(oldFile);
		}
        
        //save new signature to disk
		String pathSignature = facesIO.getPathUpload() + bioData.getId() + "_" + signatureFile.getOriginalFilename();
		File file = new File(pathSignature);
		signatureFile.transferTo(file);		
		
		//save new path signature
		bioData.setPathSignature(pathSignature);
		bioDataDao.update(bioData);
		
	}
    
    @Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFingerPrint(String nik, MultipartFile fingerPrintFile) throws Exception {
		EmpData empData = empDataDao.getEntityByNik(nik);
		BioData bioData = empData.getBioData();
		
		//delete old fingerPrint (if any)
		if(bioData.getPathFinger() != null) {
			File oldFile = new File(bioData.getPathFinger());            
			FileUtils.deleteQuietly(oldFile);
		}
        
        //save new fingerPrint to disk
		String pathFingerPrint = facesIO.getPathUpload() + bioData.getId() + "_" + fingerPrintFile.getOriginalFilename();
		File file = new File(pathFingerPrint);
		fingerPrintFile.transferTo(file);		
		
		//save new path fingerPrint
		bioData.setPathFinger(pathFingerPrint);
		bioDataDao.update(bioData);
		
	}

}
