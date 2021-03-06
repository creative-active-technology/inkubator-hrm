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
import com.inkubator.hrm.dao.BioEducationHistoryDao;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.dao.JabatanEdukasiDao;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.JabatanEdukasi;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import com.inkubator.hrm.web.search.EducationLevelSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

import java.util.ArrayList;

/**
 *
 * @author rizkykojek
 */
@Service(value = "educationLevelService")
@Lazy
public class EducationLevelServiceImpl extends IServiceImpl implements EducationLevelService {

    @Autowired
    private EducationLevelDao educationLevelDao;
    @Autowired
    private JabatanEdukasiDao jabatanEdukasiDao;
    @Autowired
    private BioEducationHistoryDao bioEducationHistoryDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(EducationLevel educationLevel) throws Exception {
    	long totalData = this.educationLevelDao.getTotalData();
        if (educationLevel.getLevel() < totalData) {
            throw new BussinessException("paysalarygrade.error_delete");
        } else if(educationLevel.getLevel() == null) {
        	this.educationLevelDao.delete(educationLevel);
        } else {
            this.educationLevelDao.delete(educationLevel);
        }
        //educationLevelDao.delete(educationLevel);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<EducationLevel> getAllData() throws Exception {
        return educationLevelDao.getAllData();
    }

    @Override
    public List<EducationLevel> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<EducationLevel> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<EducationLevel> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<EducationLevel> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<EducationLevel> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<EducationLevel> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<EducationLevel> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    
    @Override
    public EducationLevel getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public EducationLevel getEntiyByPK(Long id) throws Exception {
        return educationLevelDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalData() throws Exception {
    	return educationLevelDao.getTotalData();

    }

    @Override
    public Long getTotalDataIsActive(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public Long getTotalDataIsActive(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(EducationLevel educationLevel) throws Exception {
        // check duplicate name
        long totalDuplicates = educationLevelDao.getTotalByName(educationLevel.getName());
        if (totalDuplicates > 0) {
            throw new BussinessException("educationlevel.error_duplicate_name");
        }
        
        // check duplicate code
        long totalDuplicateCode = educationLevelDao.getTotalByCode(educationLevel.getCode());
        if (totalDuplicateCode > 0) {
            throw new BussinessException("educationlevel.error_duplicate_code");
        }
        
        //check duplicate level
        long totalDuplicateLevel = educationLevelDao.getTotalByLevel(educationLevel.getLevel());
        if(totalDuplicateLevel > 0){
        	throw new BussinessException("educationlevel.level_could_not_be_same");
        }

        educationLevel.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        educationLevel.setCreatedBy(UserInfoUtil.getUserName());
        educationLevel.setCreatedOn(new Date());
        educationLevel.setIsActive(educationLevel.getIsActive());
        educationLevelDao.save(educationLevel);
    }

    @Override
    public EducationLevel saveData(EducationLevel arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(EducationLevel arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public EducationLevel saveOrUpdateData(EducationLevel arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(EducationLevel arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(EducationLevel edu) throws Exception {
        //check duplicate name
        long totalDuplicates = educationLevelDao.getTotalByNameAndNotId(edu.getName(), edu.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("educationlevel.error_duplicate_name");
        }

        // check duplicate code
        long totalDuplicateCode = educationLevelDao.getTotalByCodeAndNotId(edu.getCode(), edu.getId());
        if (totalDuplicateCode > 0) {
            throw new BussinessException("educationlevel.error_duplicate_code");
        }
        //delete old data
        EducationLevel educationLevel = educationLevelDao.getEntiyByPK(edu.getId());
        educationLevelDao.delete(educationLevel);
        
        //saving new entity
        edu.setName(edu.getName());
        edu.setDescription(edu.getDescription());
        edu.setCode(edu.getCode());
        edu.setUpdatedBy(UserInfoUtil.getUserName());
        edu.setUpdatedOn(new Date());
        edu.setIsActive(edu.getIsActive());
        /*
         * if isActive = false, set level to null
         */
        if(edu.getIsActive()){
            edu.setLevel(edu.getLevel());
        }else{
        	edu.setLevel(null);
        }
    
        educationLevelDao.save(edu);
    }

    @Override
    public EducationLevel updateData(EducationLevel arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EducationLevel> getByParam(EducationLevelSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.educationLevelDao.getByParam(searchParameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(EducationLevelSearchParameter searchParameter) throws Exception {
        return this.educationLevelDao.getTotalByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public EducationLevel getEntityByPkWithDetail(Long id) throws Exception {
        EducationLevel educationLevel = new EducationLevel();
        List<EducationLevel> educationLevels = new ArrayList<>();
        for (JabatanEdukasi jabatanEdukasi : this.jabatanEdukasiDao.getAllDataByJabatanId(id)) {
            educationLevels.add(jabatanEdukasi.getEducationLevel());
        }

        educationLevel.setListEducationLevels(educationLevels);
        return educationLevel;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<EducationLevel> getAllDataOrderByLevel() throws Exception {
        return educationLevelDao.getAllDataOrderByLevel();
    }

    @Override
    public List<String> getAllNameOrderByLevel() throws Exception {
        List<EducationLevel> listEducationLevel = educationLevelDao.getAllNameOrderByLevel();
        List<String> listEducationShow = new ArrayList<String>();
        List<EmpDataMatrixModel> listDataAbsis = new ArrayList<EmpDataMatrixModel>();
        for (EducationLevel educationLevel : listEducationLevel) {
            listEducationShow.add(educationLevel.getName());
        }
        return listEducationShow;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpDataMatrixModel> getAllNameOrderByLevelWithModel() throws Exception {
        List<EducationLevel> listEducationLevel = educationLevelDao.getAllNameOrderByLevel();
        List<String> listEducationShow;
        EmpDataMatrixModel empDataMatrixModel;
        Long totalMale;
        Long totalFemale;
        Long totalDataBerdasarkanEdukasi;
        List<EmpDataMatrixModel> listDataModel = new ArrayList<EmpDataMatrixModel>();
        for (EducationLevel educationLevel : listEducationLevel) {
            listEducationShow = new ArrayList<String>();
            empDataMatrixModel = new EmpDataMatrixModel();
            totalMale = bioEducationHistoryDao.getTotalByGenderMaleAndEducationLevel(educationLevel.getId());
            totalFemale = bioEducationHistoryDao.getTotalByGenderFemaleAndEducationLevel(educationLevel.getId());
         
            listEducationShow.add(educationLevel.getName());
            listEducationShow.add(String.valueOf(totalFemale));
            listEducationShow.add(String.valueOf(totalMale));
            listEducationShow.add(String.valueOf(totalMale + totalFemale));
            empDataMatrixModel.setListGender(listEducationShow);
            listDataModel.add(empDataMatrixModel);
        }
        return listDataModel;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpDataMatrixModel> getAllNameByGenderOrderByLevelWithModel() throws Exception {
        List<EducationLevel> listEducationLevel = educationLevelDao.getAllNameOrderByLevel();
        List<String> listEducationShow = new ArrayList<String>();
        EmpDataMatrixModel empDataMatrixModel;
        List<String> gender = new ArrayList<String>();
        gender.add("F");
        gender.add("M");
        Long totalMale = null;
        Long totalFemale = null;
        long temp;
        Long totalDataBerdasarkanEdukasi;
        List<EmpDataMatrixModel> listDataModel = new ArrayList<EmpDataMatrixModel>();
        int j;
        for (int i = 0; i < 2; i++) {
            temp = 0;
            j = 0;
            listEducationShow = new ArrayList<String>();
            empDataMatrixModel = new EmpDataMatrixModel();
            listEducationShow.add(gender.get(i));
            while (j < listEducationLevel.size()) {
                if (i == 0) {
                    totalFemale = bioEducationHistoryDao.getTotalByGenderFemaleAndEducationLevel(listEducationLevel.get(j).getId());
                    temp = temp + totalFemale;
                    listEducationShow.add(String.valueOf(totalFemale));
                } else if (i == 1) {

                    totalMale = bioEducationHistoryDao.getTotalByGenderMaleAndEducationLevel(listEducationLevel.get(j).getId());
                    temp = temp + totalMale;
                    listEducationShow.add(String.valueOf(totalMale));
                }

                j = j + 1;
            }
            listEducationShow.add(String.valueOf(temp));
            empDataMatrixModel.setListGender(listEducationShow);
            listDataModel.add(empDataMatrixModel);
        }
        return listDataModel;
    }

	@Override
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doChangerLevel(int newLevel, long oldId) throws Exception {
		EducationLevel targetChage = this.educationLevelDao.getByGradeNumber(newLevel);
		targetChage.setLevel(0);
        targetChage.setUpdatedBy(UserInfoUtil.getUserName());
        targetChage.setUpdatedOn(new Date());
        this.educationLevelDao.saveAndMarge(targetChage);

        EducationLevel newChange = this.educationLevelDao.getEntiyByPK(oldId);
        int gradeNumberOld = newChange.getLevel();
        newChange.setLevel(newLevel);
        newChange.setUpdatedBy(UserInfoUtil.getUserName());
        newChange.setUpdatedOn(new Date());
        this.educationLevelDao.update(newChange);

        EducationLevel targetChageLast = this.educationLevelDao.getByGradeNumber(0);
        targetChageLast.setLevel(gradeNumberOld);
        targetChageLast.setUpdatedBy(UserInfoUtil.getUserName());
        targetChageLast.setUpdatedOn(new Date());
        this.educationLevelDao.update(targetChageLast);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Integer getCurrentMaxLevel() throws Exception {
		return educationLevelDao.getCurrentMaxLevel();
	}

}
