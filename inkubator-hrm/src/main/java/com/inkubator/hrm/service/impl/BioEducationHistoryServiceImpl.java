/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioEducationHistoryDao;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.dao.FacultyDao;
import com.inkubator.hrm.dao.InstitutionEducationDao;
import com.inkubator.hrm.dao.MajorDao;
import com.inkubator.hrm.entity.BioEducationHistory;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.service.BioEducationHistoryService;
import com.inkubator.hrm.web.model.BioEducationHistoryViewModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni
 */
@Service(value = "bioEducationHistoryService")
@Lazy
public class BioEducationHistoryServiceImpl extends IServiceImpl implements BioEducationHistoryService{

    @Autowired
    private BioEducationHistoryDao educationHistoryDao;
    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private EducationLevelDao educationLevelDao;
    @Autowired
    private InstitutionEducationDao institutionEducationDao;
    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private MajorDao majorDao;
    @Autowired
    private CityDao cityDao; 

    @Override
    public BioEducationHistory getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioEducationHistory getEntiyByPK(Long id) throws Exception {
        return educationHistoryDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(BioEducationHistory entity) throws Exception {
        if(entity.getCity()!=null){
        City city = cityDao.getEntiyByPK(entity.getCity().getId());
        entity.setCity(city);
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setBiodata(bioDataDao.getEntiyByPK(entity.getBiodata().getId()));
        entity.setEducationLevel(educationLevelDao.getEntiyByPK(entity.getEducationLevel().getId()));
        entity.setInstitutionEducation(institutionEducationDao.getEntiyByPK(entity.getInstitutionEducation().getId()));
        entity.setFaculty(facultyDao.getEntiyByPK(entity.getFaculty().getId()));
        entity.setMajor(majorDao.getEntiyByPK(entity.getMajor().getId()));
        entity.setCertificateNumber(entity.getCertificateNumber());
        entity.setScore(entity.getScore());
        entity.setYearIn(entity.getYearIn());
        entity.setYearOut(entity.getYearOut());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.educationHistoryDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioEducationHistory entity) throws Exception {
        BioEducationHistory update = educationHistoryDao.getEntiyByPK(entity.getId());
        if(entity.getCity()!=null){
        City city = cityDao.getEntiyByPK(entity.getCity().getId());
        update.setCity(city);
        }
        update.setBiodata(bioDataDao.getEntiyByPK(entity.getBiodata().getId()));
        update.setEducationLevel(educationLevelDao.getEntiyByPK(entity.getEducationLevel().getId()));
        update.setInstitutionEducation(institutionEducationDao.getEntiyByPK(entity.getInstitutionEducation().getId()));
        update.setFaculty(facultyDao.getEntiyByPK(entity.getFaculty().getId()));
        update.setMajor(majorDao.getEntiyByPK(entity.getMajor().getId()));
        update.setCertificateNumber(entity.getCertificateNumber());
        update.setScore(entity.getScore());
        update.setYearIn(entity.getYearIn());
        update.setYearOut(entity.getYearOut());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        if (entity.getPathFoto() != null) {
            update.setPathFoto(entity.getPathFoto());
        }
        this.educationHistoryDao.update(update);
    }

    @Override
    public void saveOrUpdate(BioEducationHistory enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory saveData(BioEducationHistory entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory updateData(BioEducationHistory entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory saveOrUpdateData(BioEducationHistory entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BioEducationHistory getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
    public void delete(BioEducationHistory entity) throws Exception {
        //remove physical file
    	try {
	        File oldFile = new File(entity.getPathFoto());
	        oldFile.delete();
    	} catch (Exception e){
    		//if any error when removing file, system will continue deleting the record
    	}
        //remove data from database
        this.educationHistoryDao.delete(entity);
    }

    @Override
    public void softDelete(BioEducationHistory entity) throws Exception {
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
    public List<BioEducationHistory> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioEducationHistory> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioEducationHistory> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioEducationHistory> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioEducationHistory> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioEducationHistory> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioEducationHistory> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BioEducationHistory> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public BioEducationHistory getAllDataByPK(Long id) {
        return educationHistoryDao.getAllDataByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioEducationHistoryViewModel> getAllDataByBioDataId(Long bioDataId) throws Exception {
        List<BioEducationHistoryViewModel> modelViews = new ArrayList<BioEducationHistoryViewModel>();
        List<BioEducationHistory> bioEducationHistorys = educationHistoryDao.getAllDataByBioDataId(bioDataId);
        for (BioEducationHistory bioEducationHistory : bioEducationHistorys) {
            BioEducationHistoryViewModel bioEduHistory = new BioEducationHistoryViewModel();
            
            bioEduHistory.setId(bioEducationHistory.getId());
            bioEduHistory.setCertificateNumber(bioEducationHistory.getCertificateNumber());
            bioEduHistory.setYearOut(bioEducationHistory.getYearOut());
            if(bioEducationHistory.getInstitutionEducation() != null){
            bioEduHistory.setInstitutionEducation(bioEducationHistory.getInstitutionEducation().getInstitutionEducationName());
            }
            bioEduHistory.setScore(bioEducationHistory.getScore());
            if( bioEducationHistory.getCity() != null ){
                bioEduHistory.setCity(bioEducationHistory.getCity().getCityName());
            }else{
                bioEduHistory.setCity(bioEducationHistory.getInstitutionEducation().getCity().getCityName());
            }
            //bioEduHistory.setEducationLevelId(bioEducationHistory.getEducationLevel().getId());
            modelViews.add(bioEduHistory);
        }
        return modelViews;
        
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public BioEducationHistoryViewModel getAllByPKByController(Long id) throws Exception {
        BioEducationHistory bioEducationHistory = educationHistoryDao.getAllDataByPK(id);
        BioEducationHistoryViewModel view = new BioEducationHistoryViewModel();
        view.setScore(bioEducationHistory.getScore());
        view.setCertificateNumber(bioEducationHistory.getCertificateNumber());
        if(bioEducationHistory.getFaculty() != null){
            view.setFaculty(bioEducationHistory.getFaculty().getName());
        }
        view.setInstitutionEducation(bioEducationHistory.getInstitutionEducation().getInstitutionEducationName());
        view.setId(bioEducationHistory.getId());
        if(bioEducationHistory.getMajor() != null){
            view.setMajor(bioEducationHistory.getMajor().getMajorName());
        }
        view.setYearIn(bioEducationHistory.getYearIn());
        view.setYearOut(bioEducationHistory.getYearOut());
        view.setEducationLevel(bioEducationHistory.getEducationLevel().getName());
        if( bioEducationHistory.getCity() != null ){
            view.setCity(bioEducationHistory.getCity().getCityName());
        }else{
            view.setCity(bioEducationHistory.getInstitutionEducation().getCity().getCityName());
        }
        if(bioEducationHistory.getPathFoto() != null ){
            view.setIsDownload(Boolean.TRUE);
        }else{
            view.setIsDownload(Boolean.FALSE);
            view.setIsDownloadString("File Not Available");
        }
        return view;
    }
    
}
