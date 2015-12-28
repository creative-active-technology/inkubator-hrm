package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.DiseaseDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HospitalDao;
import com.inkubator.hrm.dao.MedicalCareDao;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.service.MedicalCareService;
import com.inkubator.hrm.web.lazymodel.MedicalCareLazyDataModel;
import com.inkubator.hrm.web.search.MedicalCareSearchParameter;
import com.inkubator.hrm.web.search.ReportSickDataSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesIO;
import java.io.File;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
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
 * @author Taufik Hidayat
 */
@Service(value = "medicalCareService")
@Lazy
public class MedicalCareServiceImpl extends IServiceImpl implements MedicalCareService {

    @Autowired
    private MedicalCareDao medicalCareDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private HospitalDao hospitalDao;
    @Autowired
    private DiseaseDao diseaseDao;
    @Autowired
    private FacesIO facesIO;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(MedicalCare medicalCare) throws Exception {
        try {
            File oldFile = new File(medicalCare.getUploadPath());
            oldFile.delete();
        } catch (Exception e) {
            //if any error when removing file, system will continue deleting the record
        }
        
        medicalCareDao.delete(medicalCare);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<MedicalCare> getAllData() throws Exception {
        return this.medicalCareDao.getAllData();
    }

    @Override
    public List<MedicalCare> getAllData(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<MedicalCare> getAllData(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<MedicalCare> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<MedicalCare> getAllDataPageAble(int arg0, int arg1, Order arg2)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<MedicalCare> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<MedicalCare> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public List<MedicalCare> getAllDataPageAbleIsActive(int arg0, int arg1,
            Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntityByPkIsActive(String arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntityByPkIsActive(String arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntityByPkIsActive(Integer arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntityByPkIsActive(Integer arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public MedicalCare getEntiyByPK(Long id) throws Exception {
        return medicalCareDao.getEntiyByPK(id);
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
    public void save(MedicalCare medicalCare) throws Exception {

        medicalCare.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        medicalCare.setCreatedBy(UserInfoUtil.getUserName());
        medicalCare.setCreatedOn(new Date());
        medicalCareDao.save(medicalCare);
    }

    @Override
    public MedicalCare saveData(MedicalCare arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void saveOrUpdate(MedicalCare arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public MedicalCare saveOrUpdateData(MedicalCare arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    public void softDelete(MedicalCare arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(MedicalCare b) throws Exception {

        MedicalCare medicalCare = medicalCareDao.getEntiyByPK(b.getId());
        medicalCare.setEmpData(empDataDao.getEntiyByPK(b.getEmpData().getId()));
        medicalCare.setTemporaryActing(empDataDao.getEntiyByPK(b.getTemporaryActing().getId()));
        medicalCare.setDisease(diseaseDao.getEntiyByPK(b.getDisease().getId()));
        medicalCare.setHospital(hospitalDao.getEntiyByPK(b.getHospital().getId()));
        medicalCare.setDocterName(b.getDocterName());
        medicalCare.setEndDate(b.getEndDate());
        medicalCare.setMaterialJobsAbandoned(b.getMaterialJobsAbandoned());
        medicalCare.setMedicalNotes(b.getMedicalNotes());
        medicalCare.setRequestDate(b.getRequestDate());
        medicalCare.setStartDate(b.getStartDate());
        medicalCare.setTotalDays(b.getTotalDays());
        medicalCare.setUpdatedBy(UserInfoUtil.getUserName());
        medicalCare.setUpdatedOn(new Date());
        medicalCareDao.update(medicalCare);
    }

    @Override
    public MedicalCare updateData(MedicalCare arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose ECLIPSE Preferences | Code Style | Code Templates.

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<MedicalCare> getByParam(MedicalCareSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.medicalCareDao.getByParam(parameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(MedicalCareSearchParameter parameter) throws Exception {
        return this.medicalCareDao.getTotalMedicalCareByParam(parameter);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    @Override
    public MedicalCare getEntityWithDetail(Long id) throws Exception {
        return medicalCareDao.getEntityWithDetail(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<MedicalCare> getAllDataWithDetail() throws Exception {
        return medicalCareDao.getAllDataWithDetail();
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(MedicalCare medicalCare, UploadedFile documentFile) throws Exception {
        medicalCare.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        medicalCare.setCreatedBy(UserInfoUtil.getUserName());
        medicalCare.setCreatedOn(new Date());
        medicalCare.setDisease(diseaseDao.getEntiyByPK(medicalCare.getDisease().getId()));
        medicalCare.setHospital(hospitalDao.getEntiyByPK(medicalCare.getHospital().getId()));
        medicalCareDao.save(medicalCare);

        if (documentFile != null) {
            String uploadPath = getUploadPath(medicalCare.getId(), documentFile);
            facesIO.transferFile(documentFile);
            File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
            file.renameTo(new File(uploadPath));

            medicalCare.setUploadPath(uploadPath);
            medicalCareDao.update(medicalCare);
        }

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(MedicalCare b, UploadedFile documentFile) throws Exception {
//      

        MedicalCare medicalCare = medicalCareDao.getEntiyByPK(b.getId());

        // check duplicate number filling
        String uploadPath = medicalCare.getUploadPath();

        if (documentFile != null) {
            //remove old file
            File oldFile = new File(medicalCare.getUploadPath());
            oldFile.delete();

            //added new file
            uploadPath = getUploadPath(medicalCare.getId(), documentFile);
            facesIO.transferFile(documentFile);
            File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
            file.renameTo(new File(uploadPath));
        }

        medicalCare.setEmpData(empDataDao.getEntiyByPK(b.getEmpData().getId()));
        medicalCare.setTemporaryActing(empDataDao.getEntiyByPK(b.getTemporaryActing().getId()));
        medicalCare.setDisease(diseaseDao.getEntiyByPK(b.getDisease().getId()));
        medicalCare.setHospital(b.getHospital() == null ? null : hospitalDao.getEntiyByPK(b.getHospital().getId()));
        medicalCare.setDocterName(b.getDocterName());
        medicalCare.setEndDate(b.getEndDate());
        medicalCare.setMaterialJobsAbandoned(b.getMaterialJobsAbandoned());
        medicalCare.setMedicalNotes(b.getMedicalNotes());
        medicalCare.setRequestDate(b.getRequestDate());
        medicalCare.setStartDate(b.getStartDate());
        medicalCare.setTotalDays(b.getTotalDays());
        medicalCare.setUpdatedBy(UserInfoUtil.getUserName());
        medicalCare.setUpdatedOn(new Date());
        
        medicalCareDao.update(medicalCare);
    }

    private String getUploadPath(Long id, UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "medical_" + id + "." + extension;
        return uploadPath;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public MedicalCare getEntityWithNameAndNik(Long id) throws Exception {
        return this.medicalCareDao.getEntityWithNameAndNik(id);
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<MedicalCare> getByParamForReportSickData(ReportSickDataSearchParameter searchParameter, int firstResult,
			int maxResults, Order orderable) throws Exception{
		return this.medicalCareDao.getByParamForReportSickData(searchParameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParamForReportSickData(ReportSickDataSearchParameter searchParameter) throws Exception{
		return this.medicalCareDao.getTotalByParamForReportSickData(searchParameter);
	}
}
