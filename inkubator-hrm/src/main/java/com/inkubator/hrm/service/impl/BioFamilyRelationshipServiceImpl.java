package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioFamilyRelationshipDao;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.dao.FamilyRelationDao;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.service.BioFamilyRelationshipService;
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
 * @author Taufik Hidayat
 */
@Service(value = "bioFamilyRelationshipService")
@Lazy
public class BioFamilyRelationshipServiceImpl extends IServiceImpl implements BioFamilyRelationshipService {

    @Autowired
    private BioFamilyRelationshipDao bioFamilyRelationshipDao;
    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private FamilyRelationDao familyRelationDao;
    @Autowired
    private EducationLevelDao educationLevelDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(BioFamilyRelationship bioFamilyRelationship) throws Exception {
        bioFamilyRelationshipDao.delete(bioFamilyRelationship);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioFamilyRelationship> getAllData() throws Exception {
        return this.bioFamilyRelationshipDao.getAllData();
    }

    @Override
    public List<BioFamilyRelationship> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioFamilyRelationship> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioFamilyRelationship> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioFamilyRelationship> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioFamilyRelationship> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioFamilyRelationship> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioFamilyRelationship> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioFamilyRelationship getEntiyByPK(Long id) throws Exception {
        return bioFamilyRelationshipDao.getEntiyByPK(id);
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

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
    public void save(BioFamilyRelationship bioFamilyRelationship) throws Exception {
        BioData biodata = bioDataDao.getEntiyByPK(bioFamilyRelationship.getBioData().getId());
        FamilyRelation familyRelation = familyRelationDao.getEntiyByPK(bioFamilyRelationship.getFamilyRelation().getId());
        EducationLevel educationLevel = educationLevelDao.getEntiyByPK(bioFamilyRelationship.getEducationLevel().getId());
        bioFamilyRelationship.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        bioFamilyRelationship.setBioData(biodata);
        bioFamilyRelationship.setFamilyRelation(familyRelation);
        bioFamilyRelationship.setEducationLevel(educationLevel);
        bioFamilyRelationship.setCreatedBy(UserInfoUtil.getUserName());
        bioFamilyRelationship.setCreatedOn(new Date());
        bioFamilyRelationshipDao.save(bioFamilyRelationship);
    }

    @Override
    public BioFamilyRelationship saveData(BioFamilyRelationship arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(BioFamilyRelationship arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioFamilyRelationship saveOrUpdateData(BioFamilyRelationship arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(BioFamilyRelationship arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioFamilyRelationship b) throws Exception {
        BioData biodata = bioDataDao.getEntiyByPK(b.getBioData().getId());
        FamilyRelation familyRelation = familyRelationDao.getEntiyByPK(b.getFamilyRelation().getId());
        EducationLevel educationLevel = educationLevelDao.getEntiyByPK(b.getEducationLevel().getId());
        BioFamilyRelationship bioFamilyRelationship = bioFamilyRelationshipDao.getEntiyByPK(b.getId());
        bioFamilyRelationship.setBioData(biodata);
        bioFamilyRelationship.setFamilyRelation(familyRelation);
        bioFamilyRelationship.setEducationLevel(educationLevel);
        bioFamilyRelationship.setName(b.getName());
        bioFamilyRelationship.setDateOfBirth(b.getDateOfBirth());
        bioFamilyRelationship.setGender(b.getGender());
        bioFamilyRelationship.setDependents(b.getDependents());
        bioFamilyRelationship.setOccupation(b.getOccupation());
        bioFamilyRelationship.setUpdatedBy(UserInfoUtil.getUserName());
        bioFamilyRelationship.setUpdatedOn(new Date());
        bioFamilyRelationshipDao.update(bioFamilyRelationship);
    }

    @Override
    public BioFamilyRelationship updateData(BioFamilyRelationship arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioFamilyRelationship> getAllDataByBioDataId(Long bioDataId) throws Exception {
        return bioFamilyRelationshipDao.getAllDataByBioDataId(bioDataId);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioFamilyRelationship getEntityByPKWithDetail(Long id) throws Exception {
        return bioFamilyRelationshipDao.getEntityByPKWithDetail(id);
    }
    
}
