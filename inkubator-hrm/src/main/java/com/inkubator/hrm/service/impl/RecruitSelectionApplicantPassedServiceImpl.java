package com.inkubator.hrm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.EmpCareerHistoryDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.PaySalaryGradeDao;
import com.inkubator.hrm.dao.RecruitApplicantDao;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantPassedDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailRealizationDao;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDetailDao;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassedId;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetailId;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.service.RecruitSelectionApplicantPassedService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantPassedModel;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.search.RecruitSelectionApplicantPassedSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@Service(value = "recruitSelectionApplicantPassedService")
@Lazy
public class RecruitSelectionApplicantPassedServiceImpl extends IServiceImpl implements RecruitSelectionApplicantPassedService {

	@Autowired
	private RecruitSelectionApplicantSchedulleDao recruitSelectionApplicantSchedulleDao;
	@Autowired
	private RecruitSelectionApplicantPassedDao recruitSelectionApplicantPassedDao;
	@Autowired
	private RecruitApplicantDao recruitApplicantDao;
	@Autowired
	private RecruitSelectionApplicantSchedulleDetailRealizationDao recruitSelectionApplicantSchedulleDetailRealizationDao;
	@Autowired
	private RecruitmenSelectionSeriesDetailDao recruitmenSelectionSeriesDetailDao;
	@Autowired
	private EmpCareerHistoryDao empCareerHistoryDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private BioDataDao bioDataDao;
	@Autowired
	private EmployeeTypeDao employeeTypeDao;
	@Autowired
	private GolonganJabatanDao golonganJabatanDao;
	@Autowired
	private JabatanDao jabatanDao;
	@Autowired
	private PaySalaryGradeDao paySalaryGradeDao;
	
	@Override
	public RecruitSelectionApplicantPassed getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(RecruitSelectionApplicantPassed enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RecruitSelectionApplicantPassed saveData(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed updateData(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed saveOrUpdateData(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitSelectionApplicantPassed getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RecruitSelectionApplicantPassed entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDelete(RecruitSelectionApplicantPassed entity) throws Exception {
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
	public List<RecruitSelectionApplicantPassed> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllDataPageAble(int firstResult, int maxResults, Order order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitSelectionApplicantPassed> getAllDataPageAbleIsActive(int firstResult, int maxResults,
			Order order, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Long selectionScheduleId, List<Long> listApplicantId) throws Exception {
		RecruitSelectionApplicantSchedulle selectionApplicantSchedulle = recruitSelectionApplicantSchedulleDao.getEntiyByPK(selectionScheduleId);
		RecruitHireApply hireApply = selectionApplicantSchedulle.getHireApply();
		for(Long applicantId : listApplicantId){
			RecruitApplicant applicant = recruitApplicantDao.getEntiyByPK(applicantId);
			
			/** dapatkan list dari realisasinya */
			List<RecruitSelectionApplicantSchedulleDetailRealization> listSelectionRealization = recruitSelectionApplicantSchedulleDetailRealizationDao.getAllDataByApplicantIdAndSelectionApplicantSchedulleId(applicant.getId(), selectionApplicantSchedulle.getId());

			/** dapatkan kapan terakhir test nya, dengan cara order desc dari realization */
			Date latestTestDate = ((RecruitSelectionApplicantSchedulleDetailRealization) Lambda.sort(listSelectionRealization, Lambda.on(RecruitSelectionApplicantSchedulleDetailRealization.class).getRealizationDate(), Collections.reverseOrder()).get(0)).getRealizationDate();
			
			/** dapatkan tanggal expiry nya, untuk digunakan di proses scheduller of offering letter 
			 *  jika result dari letterExpired==null artinya tidak ada proses pengiriman offering letter*/
			Date letterExpired = null;
			RecruitSelectionApplicantSchedulleDetailRealization latestStepSelection = (RecruitSelectionApplicantSchedulleDetailRealization) Lambda.sort(listSelectionRealization, Lambda.on(RecruitSelectionApplicantSchedulleDetailRealization.class).getRecruitSelectionApplicantSchedulleDetail().getSelectionListOrder(), Collections.reverseOrder()).get(0);
			Long selectionSeriesId = latestStepSelection.getRecruitSelectionApplicantSchedulleDetail().getRecruitSelectionApplicantSchedulle().getSelectionSeries().getId();
			Long selectionTypeId = latestStepSelection.getRecruitSelectionApplicantSchedulleDetail().getSelectionType().getId();
			RecruitmenSelectionSeriesDetail selectionSeriesDetail = recruitmenSelectionSeriesDetailDao.getEntityByPk(new RecruitmenSelectionSeriesDetailId(selectionSeriesId, selectionTypeId));
			if(selectionSeriesDetail.getRecruitLettersByAcceptLetterId() != null && selectionSeriesDetail.getRecruitLettersByAcceptLetterId().getExpiryDays() != null){
				Integer expiredInDays = selectionSeriesDetail.getRecruitLettersByAcceptLetterId().getExpiryDays();
				letterExpired = new DateTime().plusDays(expiredInDays).toDate();
			}
			
			/** mulai set value entity nya untuk proses saving */
			RecruitSelectionApplicantPassed selectionApplicantPassed = new RecruitSelectionApplicantPassed();
			selectionApplicantPassed.setId(new RecruitSelectionApplicantPassedId(hireApply.getId(), applicant.getId()));
			selectionApplicantPassed.setHireApply(hireApply);
			selectionApplicantPassed.setApplicant(applicant);
			selectionApplicantPassed.setLatestTestDate(latestTestDate);
			selectionApplicantPassed.setLetterExpired(letterExpired);
			selectionApplicantPassed.setPlacementStatus(HRMConstant.SELECTION_APPLICANT_PASSED_STATUS_PENDING);
			selectionApplicantPassed.setCreatedBy(UserInfoUtil.getUserName());
			selectionApplicantPassed.setCreatedOn(new Date());
			
			recruitSelectionApplicantPassedDao.save(selectionApplicantPassed);
		}
		
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByHireApplyIdAndNotPlacementStatus(Long hireApplyId, String placementStatus) throws Exception {
		return recruitSelectionApplicantPassedDao.getTotalByHireApplyIdAndPlacementStatus(hireApplyId,placementStatus);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitSelectionApplicantPassedViewModel> getListSelectionPassedViewModelByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception {
		return recruitSelectionApplicantPassedDao.getListSelectionPassedViewModelByParam(searchParameter, firstResult, maxResults, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalSelectionPassedViewModelByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter) throws Exception {
		return recruitSelectionApplicantPassedDao.getTotalSelectionPassedViewModelByParam(searchParameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitSelectionApplicantPassed getEntityWithDetailByRecruitSelectionApplicantPassedId(RecruitSelectionApplicantPassedId id) throws Exception {
		return recruitSelectionApplicantPassedDao.getEntityWithDetailByRecruitSelectionApplicantPassedId(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String setupEmployee(RecruitSelectionApplicantPassedModel applicantPassedModel, Boolean isInternalCandidate) throws Exception {
		String result = "error";
		RecruitApplicant applicant = recruitApplicantDao.getEntityByPkWithDetail(applicantPassedModel.getApplicantId());
		if(applicant.getCareerCandidate() == HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_EXTERNAL){// Jika External, Add CareerHistory and Add EmpData (new Hire)
			generateEmpCareerHistoryAndAddEmpData(applicantPassedModel, applicant);
		}else if(applicant.getCareerCandidate() == HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL){// Jika Internal, Add CareerHistory And Edit Existing EmpData (Rotation)
			generateEmpCareerHistoryAndUpdateEmpData(applicantPassedModel, applicant);
		}
		
		//Update status Applicant
		RecruitSelectionApplicantPassed recruitSelectionApplicantPassed = recruitSelectionApplicantPassedDao.getEntityByPk(new RecruitSelectionApplicantPassedId(applicantPassedModel.getHireApplyId(), applicantPassedModel.getApplicantId()));
		recruitSelectionApplicantPassed.setPlacementStatus(HRMConstant.SELECTION_APPLICANT_PASSED_STATUS_EMPLOYEMENT);
		recruitSelectionApplicantPassedDao.update(recruitSelectionApplicantPassed);
		
		result = "success";
		return result;
		
	}
	
	private void generateEmpCareerHistoryAndAddEmpData(RecruitSelectionApplicantPassedModel applicantPassedModel, RecruitApplicant applicant) throws Exception{
		
		EmpCareerHistory empCareerHistory = generateEmpCareerHistory(applicantPassedModel, applicant);
		empCareerHistoryDao.save(empCareerHistory);
		
		EmpData empData = generateEmpData(applicantPassedModel, applicant);
		empDataDao.save(empData);
		
	}
	
	private void generateEmpCareerHistoryAndUpdateEmpData(RecruitSelectionApplicantPassedModel applicantPassedModel, RecruitApplicant applicant) throws Exception{
		
		EmpCareerHistory empCareerHistory = generateEmpCareerHistory(applicantPassedModel, applicant);
		empCareerHistoryDao.save(empCareerHistory);
		
		EmpData empData = updateEmpData(applicantPassedModel, applicant);
		empDataDao.update(empData);
		
	}
	
	private EmpCareerHistory generateEmpCareerHistory(RecruitSelectionApplicantPassedModel applicantPassedModel,RecruitApplicant applicant ){
		
		BioData bioDataApplicant = bioDataDao.getEntiyByPK(applicant.getBioData().getId());
		EmployeeType employeeType = employeeTypeDao.getEntiyByPK(applicantPassedModel.getEmployeeTypeId());
		GolonganJabatan golonganJabatan = golonganJabatanDao.getEntiyByPK(applicantPassedModel.getGolonganJabatanId());
		Jabatan jabatan = jabatanDao.getEntiyByPK(applicantPassedModel.getJabatanId());
		
		EmpCareerHistory empCareerHistory = new EmpCareerHistory();
		empCareerHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
		empCareerHistory.setBioData(bioDataApplicant);
		empCareerHistory.setEmployeeType(employeeType);
		empCareerHistory.setGolonganJabatan(golonganJabatan);
		empCareerHistory.setJabatan(jabatan);
		empCareerHistory.setNik(applicantPassedModel.getNik());
		String basicSalaryEncrypt = AESUtil.getAESEncription(new BigDecimal(applicantPassedModel.getGajiPokok()).toString(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
		empCareerHistory.setSalary(basicSalaryEncrypt);
		
		if(applicant.getCareerCandidate() == HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_EXTERNAL){
			empCareerHistory.setStatus(HRMConstant.EMP_NEW_HIRE);
		}else if(applicant.getCareerCandidate() == HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL){
			empCareerHistory.setStatus(HRMConstant.EMP_ROTATION);
		}
		
		empCareerHistory.setCreatedBy(HrmUserInfoUtil.getUserName());
		empCareerHistory.setCreatedOn(new Date());
		
		return empCareerHistory;
	}
	private EmpData generateEmpData(RecruitSelectionApplicantPassedModel applicantPassedModel, RecruitApplicant applicant) throws Exception{
		
		String basicSalaryEncrypt = AESUtil.getAESEncription(new BigDecimal(applicantPassedModel.getGajiPokok()).toString(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
		BioData bioData = bioDataDao.getEntiyByPK(applicant.getBioData().getId());
		Jabatan jabatan = jabatanDao.getJabatanByIdWithDetail(applicantPassedModel.getJabatanId());
		GolonganJabatan golonganJabatan = golonganJabatanDao.getEntiyByPK(jabatan.getGolonganJabatan().getId());
		EmployeeType employeeType = employeeTypeDao.getEntiyByPK(applicantPassedModel.getEmployeeTypeId());
		PaySalaryGrade paySalaryGrade = paySalaryGradeDao.getEntiyByPK(applicantPassedModel.getPaySalaryGradeId());
		
		EmpData empData = new EmpData();
		empData.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        empData.setBasicSalary(basicSalaryEncrypt);
        empData.setBioData(bioData);
        empData.setEmployeeType(employeeType);
        
        empData.setJabatanByJabatanId(jabatan);
        empData.setJabatanByJabatanGajiId(jabatan);
        empData.setGolonganJabatan(golonganJabatan);
        
        empData.setJoinDate(applicantPassedModel.getTmbDate());
        empData.setNik(applicantPassedModel.getNik());
       
        empData.setPaySalaryGrade(paySalaryGrade);
        double min = paySalaryGrade.getMinSalary().doubleValue();
        double max = paySalaryGrade.getMaxSalary().doubleValue();
        if (applicantPassedModel.getGajiPokok() > max || applicantPassedModel.getGajiPokok() < min) {
            throw new BussinessException("emp_data.error_salary_range");
        }
        
        empData.setCreatedBy(HrmUserInfoUtil.getUserName());
        empData.setCreatedOn(new Date());
        empData.setStatus(HRMConstant.EMP_NEW_HIRE);
        
		return empData;
	}
	
	private EmpData updateEmpData(RecruitSelectionApplicantPassedModel applicantPassedModel, RecruitApplicant applicant) throws Exception{
		
		String basicSalaryEncrypt = AESUtil.getAESEncription(new BigDecimal(applicantPassedModel.getGajiPokok()).toString(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
		BioData bioData = bioDataDao.getEntiyByPK(applicant.getBioData().getId());
		Jabatan jabatan = jabatanDao.getJabatanByIdWithDetail(applicantPassedModel.getJabatanId());
		GolonganJabatan golonganJabatan = golonganJabatanDao.getEntiyByPK(jabatan.getGolonganJabatan().getId());
		EmployeeType employeeType = employeeTypeDao.getEntiyByPK(applicantPassedModel.getEmployeeTypeId());
		PaySalaryGrade paySalaryGrade = paySalaryGradeDao.getEntiyByPK(applicantPassedModel.getPaySalaryGradeId());
		
		EmpData empDataToUpdate = empDataDao.getByEmpDataByBioDataId(bioData.getId());
        empDataToUpdate.setBasicSalary(basicSalaryEncrypt);
        empDataToUpdate.setBioData(bioData);
        empDataToUpdate.setEmployeeType(employeeType);
        
        empDataToUpdate.setJabatanByJabatanId(jabatan);
        empDataToUpdate.setJabatanByJabatanGajiId(jabatan);
        empDataToUpdate.setGolonganJabatan(golonganJabatan);
        
        empDataToUpdate.setJoinDate(applicantPassedModel.getTmbDate());
        empDataToUpdate.setNik(applicantPassedModel.getNik());
       
        empDataToUpdate.setPaySalaryGrade(paySalaryGrade);
        double min = paySalaryGrade.getMinSalary().doubleValue();
        double max = paySalaryGrade.getMaxSalary().doubleValue();
        if (applicantPassedModel.getGajiPokok() > max || applicantPassedModel.getGajiPokok() < min) {
            throw new BussinessException("emp_data.error_salary_range");
        }
        
        empDataToUpdate.setUpdatedBy(HrmUserInfoUtil.getUserName());
        empDataToUpdate.setUpdatedOn(new Date());
        empDataToUpdate.setStatus(HRMConstant.EMP_ROTATION);
       
		return empDataToUpdate;
	}
}
