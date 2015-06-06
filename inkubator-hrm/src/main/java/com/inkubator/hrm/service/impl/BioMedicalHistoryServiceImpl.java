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
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioMedicalHistoryDao;
import com.inkubator.hrm.dao.DiseaseDao;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioMedicalHistory;
import com.inkubator.hrm.service.BioMedicalHistoryService;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author Taufik Hidayat
 */
@Service(value = "bioMedicalHistoryService")
@Lazy
public class BioMedicalHistoryServiceImpl extends IServiceImpl implements BioMedicalHistoryService {

    @Autowired
    private BioMedicalHistoryDao bioMedicalHistoryDao;
    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private DiseaseDao diseaseDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(BioMedicalHistory bioMedicalHistory) throws Exception {
        bioMedicalHistoryDao.delete(bioMedicalHistory);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioMedicalHistory> getAllData() throws Exception {
        return this.bioMedicalHistoryDao.getAllData();
    }

    @Override
    public List<BioMedicalHistory> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioMedicalHistory> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioMedicalHistory> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioMedicalHistory> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioMedicalHistory> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioMedicalHistory> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<BioMedicalHistory> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioMedicalHistory getEntiyByPK(Long id) throws Exception {
        return bioMedicalHistoryDao.getEntiyByPK(id);
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
    public void save(BioMedicalHistory bioMedicalHistory) throws Exception {
        BioData biodata = bioDataDao.getEntiyByPK(bioMedicalHistory.getBioData().getId());

        bioMedicalHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        bioMedicalHistory.setBioData(biodata);
        bioMedicalHistory.setDisease(diseaseDao.getEntiyByPK(bioMedicalHistory.getDisease().getId()));
        bioMedicalHistory.setCreatedBy(UserInfoUtil.getUserName());
        bioMedicalHistory.setCreatedOn(new Date());
        bioMedicalHistoryDao.save(bioMedicalHistory);
    }

    @Override
    public BioMedicalHistory saveData(BioMedicalHistory arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(BioMedicalHistory arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public BioMedicalHistory saveOrUpdateData(BioMedicalHistory arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(BioMedicalHistory arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(BioMedicalHistory b) throws Exception {
        BioData biodata = bioDataDao.getEntiyByPK(b.getBioData().getId());
        BioMedicalHistory bioMedicalHistory = bioMedicalHistoryDao.getEntiyByPK(b.getId());
        bioMedicalHistory.setBioData(biodata);
        bioMedicalHistory.setYear(b.getYear());
        bioMedicalHistory.setDisease(diseaseDao.getEntiyByPK(b.getDisease().getId()));
        bioMedicalHistory.setStatus(b.getStatus());
        bioMedicalHistory.setDescription(b.getDescription());
        bioMedicalHistory.setUpdatedBy(UserInfoUtil.getUserName());
        bioMedicalHistory.setUpdatedOn(new Date());
        bioMedicalHistoryDao.update(bioMedicalHistory);
    }

    @Override
    public BioMedicalHistory updateData(BioMedicalHistory arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<BioMedicalHistory> getAllDataByBioDataId(Long bioDataId) throws Exception {
        return bioMedicalHistoryDao.getAllDataByBioDataId(bioDataId);

    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioMedicalHistory getEntityByPkWithDetail(Long id) throws Exception {
		return bioMedicalHistoryDao.getEntityByPkWithDetail(id);
	}

}
