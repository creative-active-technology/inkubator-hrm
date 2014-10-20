/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.dao.FacultyDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.JabatanEdukasiDao;
import com.inkubator.hrm.dao.JabatanFakultyDao;
import com.inkubator.hrm.dao.JabatanMajorDao;
import com.inkubator.hrm.dao.JabatanOccupationDao;
import com.inkubator.hrm.dao.JabatanSpesifikasiDao;
import com.inkubator.hrm.dao.MajorDao;
import com.inkubator.hrm.dao.OccupationTypeDao;
import com.inkubator.hrm.dao.SpecificationAbilityDao;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanEdukasi;
import com.inkubator.hrm.entity.JabatanEdukasiId;
import com.inkubator.hrm.entity.JabatanFakulty;
import com.inkubator.hrm.entity.JabatanFakultyId;
import com.inkubator.hrm.entity.JabatanMajor;
import com.inkubator.hrm.entity.JabatanMajorId;
import com.inkubator.hrm.entity.JabatanProfesi;
import com.inkubator.hrm.entity.JabatanProfesiId;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.JabatanSpesifikasiService;
import com.inkubator.securitycore.util.UserInfoUtil;
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
@Service(value = "jabatanSpesifikasiService")
@Lazy
public class JabatanSpesifikasiServiceImpl extends IServiceImpl implements JabatanSpesifikasiService{
    
    @Autowired
    private JabatanSpesifikasiDao jabatanSpesifikasiDao;
    @Autowired
    private JabatanDao jabatanDao;
    @Autowired
    private SpecificationAbilityDao specAbilityDao;
    @Autowired
    private JabatanEdukasiDao jabatanEdukasiDao;
    @Autowired
    private JabatanMajorDao jabatanMajorDao;
    @Autowired
    private JabatanFakultyDao jabatanFacultyDao;
    @Autowired
    private JabatanOccupationDao jabatanOccupationDao;
    @Autowired
    private EducationLevelDao educationLevelDao;
    @Autowired
    private MajorDao majorDao;
    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private OccupationTypeDao occupationTypeDao;

    @Override
    public JabatanSpesifikasi getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public JabatanSpesifikasi getEntiyByPK(Long id) throws Exception {
        return jabatanSpesifikasiDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(JabatanSpesifikasi entity) throws Exception {
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setJabatan(jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
        entity.setSpecificationAbility(specAbilityDao.getEntiyByPK(entity.getSpecificationAbility().getId()));
        entity.setValue(entity.getValue());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.jabatanSpesifikasiDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Long id, List<EducationLevel> educationLevels, List<Major> majorLevel, List<Faculty> faculties, List<OccupationType> occupation) throws Exception {
        //education level
        for(EducationLevel educationLevel : educationLevels){
            JabatanEdukasi jabatanEdukasi = new JabatanEdukasi();
            jabatanEdukasi.setId(new JabatanEdukasiId(id, educationLevel.getId()));
            jabatanEdukasi.setEducationLevel(educationLevelDao.getEntiyByPK(educationLevel.getId()));
            jabatanEdukasi.setJabatan(jabatanDao.getEntiyByPK(id));
            this.jabatanEdukasiDao.save(jabatanEdukasi);
        }
        //major
        for(Major major : majorLevel){
            JabatanMajor jabatanMajor = new JabatanMajor();
            jabatanMajor.setId(new JabatanMajorId(id, major.getId()));
            jabatanMajor.setMajor(majorDao.getEntiyByPK(major.getId()));
            jabatanMajor.setJabatan(jabatanDao.getEntiyByPK(id));
            this.jabatanMajorDao.save(jabatanMajor);
        }
        //faculty
        for(Faculty faculty : faculties){
            JabatanFakulty jabatanFakulty = new JabatanFakulty();
            jabatanFakulty.setId(new JabatanFakultyId(id, faculty.getId()));
            jabatanFakulty.setFaculty(facultyDao.getEntiyByPK(faculty.getId()));
            jabatanFakulty.setJabatan(jabatanDao.getEntiyByPK(id));
            this.jabatanFacultyDao.save(jabatanFakulty);
        }
        
        //occupation
        for(OccupationType occupationType : occupation){
            JabatanProfesi jabatanOccupation = new JabatanProfesi();
            jabatanOccupation.setId(new JabatanProfesiId(id, occupationType.getId()));
            jabatanOccupation.setOccupationType(occupationTypeDao.getEntiyByPK(occupationType.getId()));
            jabatanOccupation.setJabatan(jabatanDao.getEntiyByPK(id));
            this.jabatanOccupationDao.save(jabatanOccupation);
        }
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Long id, List<EducationLevel> educationLevels, List<Major> majorLevel, List<Faculty> faculty, List<OccupationType> occupation) throws Exception {
        jabatanEdukasiDao.deleteAllDataByJabatanId(id);
        this.save(id, educationLevels, majorLevel, faculty, occupation);
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(JabatanSpesifikasi entity) throws Exception {
        JabatanSpesifikasi jobSpek=this.jabatanSpesifikasiDao.getEntiyByPK(entity.getId());
        jobSpek.setJabatan(jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
        jobSpek.setSpecificationAbility(specAbilityDao.getEntiyByPK(entity.getSpecificationAbility().getId()));
        jobSpek.setValue(entity.getValue());
        jobSpek.setUpdatedBy(UserInfoUtil.getUserName());
        jobSpek.setUpdatedOn(new Date());
        jobSpek.setOptionAbility(entity.getOptionAbility());
        this.jabatanSpesifikasiDao.update(jobSpek);
    }

    @Override
    public void saveOrUpdate(JabatanSpesifikasi enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi saveData(JabatanSpesifikasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi updateData(JabatanSpesifikasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi saveOrUpdateData(JabatanSpesifikasi entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JabatanSpesifikasi getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =Exception.class)
    public void delete(JabatanSpesifikasi entity) throws Exception {
        this.jabatanSpesifikasiDao.delete(entity);
    }

    @Override
    public void softDelete(JabatanSpesifikasi entity) throws Exception {
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
    public List<JabatanSpesifikasi> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JabatanSpesifikasi> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public JabatanSpesifikasi getDataByPK(Long id) throws Exception {
        return jabatanSpesifikasiDao.getDataByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<JabatanSpesifikasi> getAllDataByJabatanId(Long jabatanId) throws Exception {
        return jabatanSpesifikasiDao.getAllDataByJabatanId(jabatanId);
    }

    

    


}
