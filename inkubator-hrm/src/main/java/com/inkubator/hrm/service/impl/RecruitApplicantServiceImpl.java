package com.inkubator.hrm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.model.DualListModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BusinessTypeDao;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.dao.DialectDao;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.InstitutionEducationDao;
import com.inkubator.hrm.dao.KlasifikasiKerjaDao;
import com.inkubator.hrm.dao.MaritalStatusDao;
import com.inkubator.hrm.dao.NationalityDao;
import com.inkubator.hrm.dao.OrgTypeOfSpecDao;
import com.inkubator.hrm.dao.OrgTypeOfSpecListDao;
import com.inkubator.hrm.dao.RaceDao;
import com.inkubator.hrm.dao.RecruitApplicantDao;
import com.inkubator.hrm.dao.RecruitApplicantSpecListDao;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.dao.RecruitVacancyAdvertisementDao;
import com.inkubator.hrm.dao.RecruitVacancyAdvertisementDetailDao;
import com.inkubator.hrm.dao.ReligionDao;
import com.inkubator.hrm.entity.ApplicantSpecListId;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BusinessType;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Dialect;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.MaritalStatus;
import com.inkubator.hrm.entity.Nationality;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.Race;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitApplicantSpecList;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.ApplicantModel;
import com.inkubator.hrm.web.model.ApplicantStatisticViewModel;
import com.inkubator.hrm.web.model.ApplicantUploadBatchModel;
import com.inkubator.hrm.web.model.ApplicantViewModel;
import com.inkubator.hrm.web.model.RadarChartData;
import com.inkubator.hrm.web.model.RadarDataset;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author rizkykojek
 */
@Service(value = "recruitApplicantService")
@Lazy
public class RecruitApplicantServiceImpl extends IServiceImpl implements RecruitApplicantService {

	@Autowired
	private RecruitApplicantDao recruitApplicantDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private MaritalStatusDao maritalStatusDao;
	@Autowired
	private NationalityDao nationalityDao;
	@Autowired
	private DialectDao dialectDao;
	@Autowired
	private ReligionDao religionDao;
	@Autowired
	private RaceDao raceDao;
	@Autowired
	private BioDataDao bioDataDao;
	@Autowired
	private BusinessTypeDao businessTypeDao;
	@Autowired
	private KlasifikasiKerjaDao klasifikasiKerjaDao;
	@Autowired
	private InstitutionEducationDao institutionEducationDao;
	@Autowired
	private EducationLevelDao educationLevelDao;
	@Autowired
	private RecruitApplicantSpecListDao recruitApplicantSpecListDao;
	@Autowired
	private OrgTypeOfSpecListDao orgTypeOfSpecListDao;
	@Autowired
	private RecruitVacancyAdvertisementDao recruitVacancyAdvertisementDao;
	@Autowired
	private OrgTypeOfSpecDao orgTypeOfSpecDao;
	@Autowired
	private EmpDataDao empDataDao;
	@Autowired
	private RecruitHireApplyDao recruitHireApplyDao;
	@Autowired
	private RecruitVacancyAdvertisementDetailDao recruitVacancyAdvertisementDetailDao;
	
	@Override
	public RecruitApplicant getEntiyByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntiyByPK(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntiyByPK(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(RecruitApplicant enntity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public RecruitApplicant saveData(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant updateData(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant saveOrUpdateData(RecruitApplicant entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(String id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(String id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecruitApplicant getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(RecruitApplicant entity) throws Exception {
		recruitApplicantDao.delete(entity);

	}

	@Override
	public void softDelete(RecruitApplicant entity) throws Exception {
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
	public List<RecruitApplicant> getAllData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllData(Boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllData(Integer isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllData(Byte isActive) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecruitApplicant> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<RecruitApplicant> getByParam(RecruitApplicantSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception {
		
		return recruitApplicantDao.getByParam(parameter, first, pageSize, orderable);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParam(RecruitApplicantSearchParameter parameter) throws Exception {

		return recruitApplicantDao.getTotalByParam(parameter);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public RecruitApplicant getEntityByPkWithDetail(Long id) throws Exception {
		
		return recruitApplicantDao.getEntityByPkWithDetail(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RecruitApplicant update(ApplicantModel model) throws Exception {
		
		//Start updating BioData
		BioData bioData = bioDataDao.getEntiyByPK(model.getBioDataId());
		bioData = this.bindingValueBioData(bioData, model);
		bioData.setUpdatedBy(UserInfoUtil.getUserName());
		bioData.setUpdatedOn(new Date());
		bioDataDao.update(bioData);
		
		
		//Start updating RecruitApplicant
		RecruitApplicant applicant = recruitApplicantDao.getEntiyByPK(model.getId());
		applicant = this.bindingValueApplicant(applicant, model);
		applicant.setBioData(bioData);
		applicant.setUpdatedBy(UserInfoUtil.getUserName());
		applicant.setUpdatedOn(new Date());
		recruitApplicantDao.update(applicant);		
		
		
		//Start updating list of orgTypeOfSpec
		recruitApplicantSpecListDao.deleteByApplicantId(applicant.getId());
		List<OrgTypeOfSpecList> listSelectedSpec = new ArrayList<>();
        for (DualListModel<OrgTypeOfSpecList> dual : model.getSpecListDualModel()) {
            listSelectedSpec.addAll(dual.getTarget());
        }
        for(OrgTypeOfSpecList spec : listSelectedSpec){
        	spec = orgTypeOfSpecListDao.getEntiyByPK(spec.getId());
        	
        	RecruitApplicantSpecList specList = new RecruitApplicantSpecList();
        	ApplicantSpecListId id = new ApplicantSpecListId(applicant.getId(), spec.getId());
        	specList.setId(id);
        	specList.setOrgTypeOfSpecList(spec);
        	specList.setRecruitApplicant(applicant);
        	specList.setCreatedBy(UserInfoUtil.getUserName());
        	specList.setCreatedOn(new Date());
        	recruitApplicantSpecListDao.save(specList);
        }		
		        
		return applicant;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RecruitApplicant save(ApplicantModel model) throws Exception {
		
		//Start saving BioData
		BioData bioData = new BioData();
		bioData = this.bindingValueBioData(bioData, model);
		bioData.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		bioData.setCreatedBy(UserInfoUtil.getUserName());
		bioData.setCreatedOn(new Date());
		bioDataDao.save(bioData);
		
		
		//Start saving RecruitApplicant
		RecruitApplicant applicant = new RecruitApplicant();
		applicant = this.bindingValueApplicant(applicant, model);
		applicant.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		applicant.setBioData(bioData);
		applicant.setCareerCandidate(HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_EXTERNAL); //default
		applicant.setCreatedBy(UserInfoUtil.getUserName());
		applicant.setCreatedOn(new Date());
		recruitApplicantDao.save(applicant);
		
		
		//Start saving list of orgTypeOfSpec
		List<OrgTypeOfSpecList> listSelectedSpec = new ArrayList<>();
        for (DualListModel<OrgTypeOfSpecList> dual : model.getSpecListDualModel()) {
            listSelectedSpec.addAll(dual.getTarget());
        }
        for(OrgTypeOfSpecList spec : listSelectedSpec){
        	spec = orgTypeOfSpecListDao.getEntiyByPK(spec.getId());
        	
        	RecruitApplicantSpecList specList = new RecruitApplicantSpecList();
        	ApplicantSpecListId id = new ApplicantSpecListId(applicant.getId(), spec.getId());
        	specList.setId(id);
        	specList.setOrgTypeOfSpecList(spec);
        	specList.setRecruitApplicant(applicant);
        	specList.setCreatedBy(UserInfoUtil.getUserName());
        	specList.setCreatedOn(new Date());
        	recruitApplicantSpecListDao.save(specList);
        }
		
		return applicant;
	}
	
	private BioData bindingValueBioData(BioData bioData, ApplicantModel model){
		City cityOfBirth = cityDao.getEntiyByPK(model.getCityOfBirthId());
		MaritalStatus maritalStatus = maritalStatusDao.getEntiyByPK(model.getMaritalStatusId());
		Nationality nationality = nationalityDao.getEntiyByPK(model.getNationalityId());
		Dialect dialect = dialectDao.getEntiyByPK(model.getDialectId());
		Religion religion = religionDao.getEntiyByPK(model.getReligionId());
		Race race = raceDao.getEntiyByPK(model.getRaceId());
		
		bioData.setFirstName(model.getFirstName());
		bioData.setLastName(model.getLastName());
		bioData.setTitle(model.getTitle());
		bioData.setNickname(model.getNickname());
		bioData.setDateOfBirth(model.getDateOfBirth());
		bioData.setCity(cityOfBirth);
		bioData.setGender(model.getGender());
		bioData.setMaritalStatus(maritalStatus);
		bioData.setNoKK(model.getNoKK());
		bioData.setBloodType(model.getBloodType());
		bioData.setNpwp(model.getNpwp());
		bioData.setJamsostek(model.getJamsostek());
		bioData.setNationality(nationality);
		bioData.setDialect(dialect);
		bioData.setReligion(religion);
		bioData.setRace(race);
		bioData.setMobilePhone(model.getPhoneNumber());
		bioData.setPersonalEmail(model.getEmailAddress());
		
		return bioData;
	}
	
	private RecruitApplicant bindingValueApplicant(RecruitApplicant applicant, ApplicantModel model){
		BusinessType businessType = businessTypeDao.getEntiyByPK(model.getBusinessTypeId()!= null ? model.getBusinessTypeId() : 0);
		KlasifikasiKerja klasifikasiKerja = klasifikasiKerjaDao.getEntiyByPK(model.getKlasifikasiKerjaId() != null ? model.getKlasifikasiKerjaId() : 0);
		InstitutionEducation institutionEducation = institutionEducationDao.getEntiyByPK(model.getInstitutionEducationId());
		EducationLevel educationLevel = educationLevelDao.getEntiyByPK(model.getEducationLevelId());
		
		List<RecruitVacancyAdvertisementDetail> listVacancyAdvertisementDetail = recruitVacancyAdvertisementDetailDao.getAllData();
		
		//RecruitVacancyAdvertisement vacancyAdvertisement = recruitVacancyAdvertisementDao.getEntiyByPK(model.getVacancyAdvertisementId());
		
		
		applicant.setBusinessType(businessType);
		applicant.setCertificateNumber(model.getCertificateNumber());
		applicant.setEducationEndYear(model.getEducationEndYear());
		applicant.setEducationStartYear(model.getEducationStartYear());
		applicant.setEducationLevel(educationLevel);
		applicant.setInstitutionEducation(institutionEducation);
		applicant.setKlasifikasiKerja(klasifikasiKerja);
		applicant.setLastJabatan(model.getLastJabatan());
		applicant.setLastJabatanSince(model.getLastJabatanSince());
		applicant.setLastWorkCompany(model.getLastWorkCompany());
		applicant.setLastWorkEnd(model.getLastWorkEnd());
		applicant.setLastWorkSince(model.getLastWorkSince());
		applicant.setScale(model.getScale());
		applicant.setScore(model.getScore());
		//applicant.setRecruitVacancyAdvertisement(vacancyAdvertisement);
		applicant.setRecruitVacancyAdvertisementDetail(listVacancyAdvertisementDetail.get(0));
		applicant.setIsFreshGraduate(model.getIsFreshGraduate());
		applicant.setIsActive(model.getIsActive());
		applicant.setIsVerified(model.getIsVerified());
		
		return applicant;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void uploadBatchProcess(ApplicantUploadBatchModel model) throws Exception {
		if(StringUtils.isNotEmpty(model.getFirstName()) || StringUtils.isNotEmpty(model.getLastName())){
			
			//Start saving BioData
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateOfBirth = null;		
			try {
				dateFormat.parse(model.getDateOfBirth());
			} catch (Exception e) {}
			
			BioData bioData = new BioData();
			bioData.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			bioData.setFirstName(model.getFirstName());
			bioData.setLastName(model.getLastName());
			bioData.setDateOfBirth(dateOfBirth);
			bioData.setCity(cityDao.getEntityByName(model.getCityOfBirth()));
			bioData.setGender(StringUtils.equals("Perempuan", model.getGender()) ? 0 : 1);
			bioData.setMobilePhone(model.getPhoneNumber());
			bioData.setPersonalEmail(model.getEmailAddress());
			bioData.setCreatedBy(model.getCreatedBy());
			bioData.setCreatedOn(model.getCreatedOn());
			bioDataDao.save(bioData);			
			
			
			//Start saving RecruitApplicant
			Integer educationStartYear = null;
			Integer educationEndYear = null;
			try {
				educationStartYear = Integer.parseInt(model.getEducationStartYear());
			} catch (Exception e) {}
			try {
				educationEndYear = Integer.parseInt(model.getEducationEndYear());
			} catch (Exception e) {}
			
			RecruitApplicant applicant = new RecruitApplicant();
			applicant.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			applicant.setBioData(bioData);
			applicant.setCareerCandidate(HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_EXTERNAL); //default
			applicant.setIsFreshGraduate(Boolean.FALSE); //default
			applicant.setIsActive(Boolean.FALSE); //default
			applicant.setIsVerified(Boolean.FALSE); //default
			applicant.setEducationLevel(educationLevelDao.getEntityByName(model.getEducationLevel()));
			applicant.setInstitutionEducation(institutionEducationDao.getEntityByName(model.getInstitutionEducation()));
			applicant.setEducationStartYear(educationStartYear);
			applicant.setEducationEndYear(educationEndYear);
			applicant.setScore(model.getScore());
			applicant.setScale(model.getScale());
			applicant.setCertificateNumber(model.getCertificateNumber());
			applicant.setUploadPath(model.getUploadPath());
			//applicant.setRecruitVacancyAdvertisement(recruitVacancyAdvertisementDao.getEntiyByPK(model.getVacancyAdvertisementId()));
			applicant.setRecruitVacancyAdvertisementDetail(recruitVacancyAdvertisementDetailDao.getAllData().get(0));
			applicant.setCreatedBy(model.getCreatedBy());
			applicant.setCreatedOn(model.getCreatedOn());
			recruitApplicantDao.save(applicant);
		}
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public ApplicantStatisticViewModel getAllDataApplicantStatistic() throws Exception {
		String locale = FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(locale));
		
		ApplicantStatisticViewModel model = new ApplicantStatisticViewModel();
		
        model.setEducationLevelApplicant(this.initEducationLevelApplicantBarModel(resourceBundle));
        model.setWorkingExperienceApplicant(this.initWorkingExperienceApplicantPieModel(resourceBundle));
        model.setJobClassificationApplicant(this.initJobClassificationApplicantRadarModel());
        //model.setJobClassificationApplicantLineModel(this.initJobClassificationApplicantLineModel(resourceBundle));
        model.setListApplicantAge(this.recruitApplicantDao.getDataChartAge());
        
		return model;
	}
	
	private String initJobClassificationApplicantRadarModel(){
		RadarDataset internal = new RadarDataset();
		internal.setLabel("Internal");
		internal.setFillColor("rgba(75,178,197,0.3)");
		internal.setStrokeColor("rgba(75,178,197,0.3)");
		internal.setPointColor("rgba(75,178,197,1)");
		internal.setPointStrokeColor("#fff");
		internal.setPointHighlightFill("#fff");
		
		RadarDataset external = new RadarDataset();
		external.setLabel("External");
		external.setFillColor("rgba(234,162,40,0.3)");
		external.setStrokeColor("rgba(234,162,40,0.3)");
		external.setPointColor("rgba(234,162,40,1)");
		external.setPointStrokeColor("#fff");
		external.setPointHighlightFill("#fff");
		
		List<OrgTypeOfSpec> listSpec = orgTypeOfSpecDao.getAllData();
		//List<ApplicantViewModel> listApplicant = recruitApplicantDao.getDataChartJobClassification();
		RadarChartData data = new RadarChartData(listSpec.size());
		int i=0;
		for(OrgTypeOfSpec spec : listSpec){
			System.out.println(spec.getName() + " internal  " + recruitApplicantDao.getTotalByCareerCandidateAndOrgTypeOfSpecId(HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL, spec.getId()));
			System.out.println(spec.getName() + " external  " + recruitApplicantDao.getTotalByCareerCandidateAndOrgTypeOfSpecId(HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_EXTERNAL, spec.getId()));
			data.addLabel(i, spec.getName());
			internal.addData(String.valueOf(recruitApplicantDao.getTotalByCareerCandidateAndOrgTypeOfSpecId(HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL, spec.getId())));
			external.addData(String.valueOf(recruitApplicantDao.getTotalByCareerCandidateAndOrgTypeOfSpecId(HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_EXTERNAL, spec.getId())));
			i++;
		}
		
		RadarDataset[] datasets = new RadarDataset[2];
		datasets[0] = internal;
		datasets[1] = external;
		data.setDatasets(datasets);
    	
		Gson gson = new Gson();
    	return gson.toJson(data);
	}
	
	/*private LineChartModel  initJobClassificationApplicantLineModel(ResourceBundle resourceBundle) {
		LineChartModel model = new LineChartModel();
		model.setTitle(resourceBundle.getString("recruitment_applicant.applicant_job_classification"));
		model.setLegendPosition("ne");
        model.setShowPointLabels(true);
        model.setShowDatatip(false);
		 
        ChartSeries internal = new ChartSeries();
        internal.setLabel("Internal");
 
        ChartSeries external = new ChartSeries();
        external.setLabel("External");
        
        List<ApplicantViewModel> listApplicant = recruitApplicantDao.getDataChartJobClassification();
        for(ApplicantViewModel applicant : listApplicant) {
        	internal.set(applicant.getName(), applicant.getTotalInternalDecimal());
        	external.set(applicant.getName(), applicant.getTotalExternalDecimal());
        }
        model.addSeries(internal);
        model.addSeries(external);
        
        Long maxInternal = Lambda.max(listApplicant, Lambda.on(ApplicantViewModel.class).getTotalInternalDecimal().longValue());
        Long maxExternal = Lambda.max(listApplicant, Lambda.on(ApplicantViewModel.class).getTotalExternalDecimal()).longValue();
        Long max = maxInternal > maxExternal ? maxInternal : maxExternal;
        max =  max + (10 - (max % 10)); //tujuannya untuk buat genap, per 10
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel(resourceBundle.getString("educationhistory.score"));
        yAxis.setMin(0);
        yAxis.setMax(max);
                
        model.getAxes().put(AxisType.X, new CategoryAxis(""));
        
        return model;
	}*/
	
	private PieChartModel initWorkingExperienceApplicantPieModel(ResourceBundle resourceBundle) {
		ApplicantViewModel applicant = recruitApplicantDao.getDataChartWorkingExperience();
		
		PieChartModel model = new PieChartModel();
        model.set("Internal", applicant.getTotalInternal());
        model.set("Fresh Graduate", applicant.getTotalFreshGraduate());
        model.set("Working", applicant.getTotalNotFreshGraduate());
         
        model.setTitle(resourceBundle.getString("recruitment_applicant.applicant_working_experience"));
        model.setLegendPosition("ne");
        model.setFill(true);
        model.setShowDataLabels(true);
        
		return model;
	}
	
	private BarChartModel initEducationLevelApplicantBarModel(ResourceBundle resourceBundle) {		
		BarChartModel model = new BarChartModel();
		model.setTitle(resourceBundle.getString("recruitment_applicant.applicant_education_level"));
        model.setLegendPosition("ne");
        model.setShowPointLabels(true);
        model.setShowDatatip(false);
        
        ChartSeries internal = new ChartSeries();
        internal.setLabel("Internal");
 
        ChartSeries external = new ChartSeries();
        external.setLabel("External");
 
        List<ApplicantViewModel> listApplicant = recruitApplicantDao.getDataChartEducationLevel();
        for(ApplicantViewModel applicant : listApplicant) {
        	internal.set(applicant.getName(), applicant.getTotalInternal());
        	external.set(applicant.getName(), applicant.getTotalExternal());
        }
        model.addSeries(internal);
        model.addSeries(external);
         
        Long maxInternal = Lambda.max(listApplicant, Lambda.on(ApplicantViewModel.class).getTotalInternal());
        Long maxExternal = Lambda.max(listApplicant, Lambda.on(ApplicantViewModel.class).getTotalExternal());
        Long max = maxInternal > maxExternal ? maxInternal : maxExternal;
        max =  max + (10 - (max % 10)); //tujuannya untuk buat genap, per 10
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel(resourceBundle.getString("educationhistory.score"));
        yAxis.setMin(0);
        yAxis.setMax(max);
        
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setTickAngle(30);
        
        return model;
    }

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String commitDataInternalCareerCandidate(List<Long> listEmpDataId)	throws Exception {
		String result = "error";
		
		if(listEmpDataId.isEmpty()){
            throw new BussinessException("empCandidateSearch.commit_data_still_empty");
        }
		for(Long empDataId : listEmpDataId){
			RecruitApplicant recruitApplicant = convertInternalCandidateToRecruitApplicant(empDataId);
			recruitApplicantDao.save(recruitApplicant);
		}
		
		result = "success";
		return result;
	}
	
	private RecruitApplicant convertInternalCandidateToRecruitApplicant(Long empDataId) throws Exception{
		RecruitApplicant recruitApplicant = new RecruitApplicant();
		EmpData empData = empDataDao.getByEmpIdWithDetail(empDataId);
		BioData bioData = bioDataDao.getEntiyByPK(empData.getBioData().getId());
		Jabatan jabatan = empData.getJabatanByJabatanId();
		RecruitHireApply recruitHireApply = recruitHireApplyDao.getEntityByJabatanId(empData.getJabatanByJabatanId().getId());
		RecruitVacancyAdvertisementDetail recruitVacancyAdvertisementDetail = recruitVacancyAdvertisementDetailDao.getEntityByRecruitHireApplyId(recruitHireApply.getId());
		
		//Jika recruitVacancyAdvertisementDetail == null, lempar business Exception (salah satu jabatan kandidat tenaga kerja belum dibuatkan iklan lowongan
		if(recruitVacancyAdvertisementDetail == null){
			throw new BussinessException("empCandidateSearch.emp_candidate_still_not_have_advertisement");
		}
		
		recruitApplicant.setBioData(bioData);
		recruitApplicant.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		recruitApplicant.setRecruitVacancyAdvertisementDetail(recruitVacancyAdvertisementDetail);
		recruitApplicant.setCareerCandidate(HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL);
		recruitApplicant.setLastJabatan(jabatan.getName());
		recruitApplicant.setCreatedOn(new Date());
		recruitApplicant.setCreatedBy(HrmUserInfoUtil.getUserName());
		
		return recruitApplicant;
	}
	
	

}
