/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioSertifikasiDao;
import com.inkubator.hrm.dao.EducationNonFormalDao;
import com.inkubator.hrm.dao.OccupationTypeDao;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioDocument;
import com.inkubator.hrm.entity.BioSertifikasi;
import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.BioSertifikasiService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;
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

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "bioSertifikasiService")
@Lazy
public class BioSertifikasiServiceImpl extends IServiceImpl implements BioSertifikasiService{
    
    @Autowired
    private BioSertifikasiDao bioSertifikasiDao;
    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private EducationNonFormalDao educationNonFormalDao;
    @Autowired
    private OccupationTypeDao occupationTypeDao;
    @Autowired
    private FacesIO facesIO;
    
    @Override
    public BioSertifikasi getEntiyByPK(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntiyByPK(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioSertifikasi getEntiyByPK(Long l) throws Exception {
        return this.bioSertifikasiDao.getEntiyByPK(l);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(BioSertifikasi t) throws Exception {
        this.bioSertifikasiDao.save(t);
    }

    @Override
     @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioSertifikasi t) throws Exception {
        this.bioSertifikasiDao.update(t);
    }

    @Override
    public void saveOrUpdate(BioSertifikasi t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi saveData(BioSertifikasi t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi updateData(BioSertifikasi t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi saveOrUpdateData(BioSertifikasi t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntityByPkIsActive(String string, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntityByPkIsActive(String string, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntityByPkIsActive(String string, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntityByPkIsActive(Integer intgr, Integer intgr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntityByPkIsActive(Integer intgr, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntityByPkIsActive(Integer intgr, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntityByPkIsActive(Long l, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntityByPkIsActive(Long l, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioSertifikasi getEntityByPkIsActive(Long l, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, noRollbackFor = IOException.class)
    public void delete(BioSertifikasi t) throws Exception {
        //remove physical file
    	try {
	        File oldFile = new File(t.getUploadPath());
	        oldFile.delete();
    	} catch (Exception e){
    		//if any error when removing file, system will continue deleting the record
    	}
    	
    	//remove entity
        bioSertifikasiDao.delete(t);
    }

    @Override
    public void softDelete(BioSertifikasi t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSertifikasi> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSertifikasi> getAllData(Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSertifikasi> getAllData(Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSertifikasi> getAllData(Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSertifikasi> getAllDataPageAble(int i, int i1, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSertifikasi> getAllDataPageAbleIsActive(int i, int i1, Order order, Boolean bln) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSertifikasi> getAllDataPageAbleIsActive(int i, int i1, Order order, Integer intgr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioSertifikasi> getAllDataPageAbleIsActive(int i, int i1, Order order, Byte b) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioSertifikasi> getAllDataByBioDataId(Long bioDataId) {
        return this.bioSertifikasiDao.getAllDataByBioDataId(bioDataId);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(BioSertifikasi entity, UploadedFile documentFile) throws Exception {
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        BioData biodata = bioDataDao.getEntiyByPK(entity.getBioData().getId());
        OccupationType occupationType = occupationTypeDao.getEntiyByPK(entity.getOccupationType().getId());
        EducationNonFormal educationNonFormal = educationNonFormalDao.getEntiyByPK(entity.getEducationNonFormal().getId());
        
        entity.setBioData(biodata);
        entity.setOccupationType(occupationType);
        entity.setEducationNonFormal(educationNonFormal);
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        bioSertifikasiDao.save(entity);

        if (documentFile != null) {
            String uploadPath = getUploadPath(entity.getId(), documentFile);
            facesIO.transferFile(documentFile);
            File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
            file.renameTo(new File(uploadPath));

            entity.setUploadPath(uploadPath);
            bioSertifikasiDao.update(entity);
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioSertifikasi entity, UploadedFile documentFile) throws Exception {
         BioSertifikasi bioSertifikasi = bioSertifikasiDao.getEntiyByPK(entity.getId());
        String uploadPath = bioSertifikasi.getUploadPath();

        if (documentFile != null) {
            //remove old file if exist
            if(null != bioSertifikasi.getUploadPath()){
            File oldFile = new File(bioSertifikasi.getUploadPath());
            oldFile.delete();
            }
            
            //added new file
            uploadPath = getUploadPath(bioSertifikasi.getId(), documentFile);
            facesIO.transferFile(documentFile);
            File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
            file.renameTo(new File(uploadPath));
        }

        //BioData bioData = bioDataDao.getEntiyByPK(entity.getBioData().getId());
        EducationNonFormal educationNonFormal = educationNonFormalDao.getEntiyByPK(entity.getEducationNonFormal().getId());
        OccupationType occupationType = occupationTypeDao.getEntiyByPK(entity.getOccupationType().getId());
        
        bioSertifikasi.setEducationNonFormal(educationNonFormal);
        //bioSertifikasi.setBioData(bioData);
        bioSertifikasi.setOccupationType(occupationType);
        bioSertifikasi.setNamaSertifikasi(entity.getNamaSertifikasi());
        bioSertifikasi.setDocumentTitle(entity.getDocumentTitle());
        bioSertifikasi.setNomorDokumen(entity.getNomorDokumen());
        bioSertifikasi.setUploadPath(uploadPath);
        bioSertifikasi.setUpdatedBy(UserInfoUtil.getUserName());
        bioSertifikasi.setUpdatedOn(new Date());

        bioSertifikasiDao.update(bioSertifikasi);
    }
    
     private String getUploadPath(Long id, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "biosertifikasi_" + id + "." + extension;
        return uploadPath;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioSertifikasi getEntityByPKWithDetail(Long id) throws Exception {
        return this.bioSertifikasiDao.getEntityByPKWithDetail(id);
    }
}
