/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.CostCenterDao;
import com.inkubator.hrm.dao.DepartmentDao;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.FacultyDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.JabatanDeskripsiDao;
import com.inkubator.hrm.dao.JabatanEdukasiDao;
import com.inkubator.hrm.dao.JabatanFakultyDao;
import com.inkubator.hrm.dao.JabatanMajorDao;
import com.inkubator.hrm.dao.JabatanProfesiDao;
import com.inkubator.hrm.dao.JabatanSpesifikasiDao;
import com.inkubator.hrm.dao.KlasifikasiKerjaDao;
import com.inkubator.hrm.dao.KlasifikasiKerjaJabatanDao;
import com.inkubator.hrm.dao.MajorDao;
import com.inkubator.hrm.dao.OccupationTypeDao;
import com.inkubator.hrm.dao.OrgTypeOfSpecJabatanDao;
import com.inkubator.hrm.dao.OrgTypeOfSpecListDao;
import com.inkubator.hrm.dao.PaySalaryGradeDao;
import com.inkubator.hrm.dao.SpecificationAbilityDao;
import com.inkubator.hrm.dao.UnitKerjaDao;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.JabatanEdukasi;
import com.inkubator.hrm.entity.JabatanEdukasiId;
import com.inkubator.hrm.entity.JabatanFakulty;
import com.inkubator.hrm.entity.JabatanFakultyId;
import com.inkubator.hrm.entity.JabatanMajor;
import com.inkubator.hrm.entity.JabatanMajorId;
import com.inkubator.hrm.entity.JabatanProfesi;
import com.inkubator.hrm.entity.JabatanProfesiId;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.JabatanSpesifikasiId;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.KlasifikasiKerjaJabatan;
import com.inkubator.hrm.entity.KlasifikasiKerjaJabatanId;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatanId;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.JobJabatanModel;
import com.inkubator.hrm.web.model.KompetensiJabatanViewModel;
import com.inkubator.hrm.web.model.PerformanceIndicatorJabatanViewModel;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
import com.inkubator.hrm.web.search.KompetensiJabatanSearchParameter;
import com.inkubator.hrm.web.search.PerformanceIndicatorJabatanSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

import ch.lambdaj.Lambda;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "jabatanService")
@Lazy
public class JabatanServiceImpl extends IServiceImpl implements JabatanService {

    @Autowired
    private JabatanDao jabatanDao;
    @Autowired
    private CostCenterDao costCenterDao;
    @Autowired
    private UnitKerjaDao unitKerjaDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private GolonganJabatanDao golonganJabatanDao;
    @Autowired
    private KlasifikasiKerjaJabatanDao klasifikasiKerjaJabatanDao;
    @Autowired
    private PaySalaryGradeDao paySalaryGradeDao;
    @Autowired
    private EducationLevelDao educationLevelDao;
    @Autowired
    private JabatanEdukasiDao jabatanEdukasiDao;
    @Autowired
    private OccupationTypeDao occupationTypeDao;
    @Autowired
    private JabatanProfesiDao jabatanProfesiDao;
    @Autowired
    private MajorDao majorDao;
    @Autowired
    private JabatanMajorDao jabatanMajorDao;
    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private JabatanFakultyDao jabatanFakultyDao;
    @Autowired
    private KlasifikasiKerjaDao klasifikasiKerjaDao;
    @Autowired
    private JabatanDeskripsiDao jabatanDeskripsiDao;
    @Autowired
    private SpecificationAbilityDao specificationAbilityDao;
    @Autowired
    private JabatanSpesifikasiDao jabatanSpesifikasiDao;
    @Autowired
    private OrgTypeOfSpecListDao orgTypeOfSpecListDao;
    @Autowired
    private OrgTypeOfSpecJabatanDao orgTypeOfSpecJabatanDao;
    @Autowired
    private EmpDataDao empDataDao;


    @Override
    public Jabatan getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Jabatan getEntiyByPK(Long id) throws Exception {
        return this.jabatanDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Jabatan entity) throws Exception {
        long totalDuplicate = this.jabatanDao.getTotalByCode(entity.getCode());
        if (totalDuplicate > 0) {
            throw new BussinessException("jabatan.jabatan_duplicate_code");
        }
//        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCostCenter(this.costCenterDao.getEntiyByPK(entity.getCostCenter().getId()));
        entity.setDepartment(this.departmentDao.getEntiyByPK(entity.getDepartment().getId()));
        entity.setGolonganJabatan(this.golonganJabatanDao.getEntiyByPK(entity.getGolonganJabatan().getId()));
        if (entity.getJabatan() != null) {
            entity.setJabatan(this.jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
        }
        entity.setUnitKerja(this.unitKerjaDao.getEntiyByPK(entity.getUnitKerja().getId()));
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        Set<KlasifikasiKerjaJabatan> klasifikasiKerjaJabatans = entity.getKlasifikasiKerjaJabatans();
        for (KlasifikasiKerjaJabatan klasifikasiKerjaJabatan : klasifikasiKerjaJabatans) {

        }
        this.jabatanDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateForSalaryGrade(Jabatan entity) throws Exception {
        Jabatan jabatan = this.jabatanDao.getEntiyByPK(entity.getId());
        jabatan.setPaySalaryGrade(paySalaryGradeDao.getEntiyByPK(entity.getPaySalaryGrade().getId()));
        this.jabatanDao.update(jabatan);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Jabatan entity) throws Exception {
        long totalDuplicate = this.jabatanDao.getTotalByCodeAndNotId(entity.getCode(), entity.getId());
        if (totalDuplicate > 0) {
            throw new BussinessException("jabatan.jabatan_duplicate_code");
        }
        Jabatan jabatan = this.jabatanDao.getEntiyByPK(entity.getId());
        jabatan.getKlasifikasiKerjaJabatans().clear();
        jabatan.setCode(entity.getCode());
        jabatan.setCostCenter(this.costCenterDao.getEntiyByPK(entity.getCostCenter().getId()));
        jabatan.setDepartment(this.departmentDao.getEntiyByPK(entity.getDepartment().getId()));
        jabatan.setGolonganJabatan(this.golonganJabatanDao.getEntiyByPK(entity.getGolonganJabatan().getId()));
        if (jabatan.getJabatan() != null) {
            jabatan.setJabatan(this.jabatanDao.getEntiyByPK(entity.getJabatan().getId()));
        }

        jabatan.setUnitKerja(this.unitKerjaDao.getEntiyByPK(entity.getUnitKerja().getId()));
        jabatan.setLevelJabatan(entity.getLevelJabatan());
        jabatan.setName(entity.getName());
        jabatan.setTujuanJabatan(entity.getTujuanJabatan());
        jabatan.setUpdatedBy(UserInfoUtil.getUserName());
        jabatan.setUpdatedOn(new Date());
        this.jabatanDao.saveAndMerge(jabatan);
        Set<KlasifikasiKerjaJabatan> getKlasifikasiKerjaJabatans = entity.getKlasifikasiKerjaJabatans();
        for (KlasifikasiKerjaJabatan klasifikasiKerjaJabatan : getKlasifikasiKerjaJabatans) {
            klasifikasiKerjaJabatan.setJabatan(jabatan);
            klasifikasiKerjaJabatanDao.save(klasifikasiKerjaJabatan);
        }

    }

    @Override
    public void saveOrUpdate(Jabatan enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan saveData(Jabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan updateData(Jabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan saveOrUpdateData(Jabatan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jabatan getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Jabatan entity) throws Exception {
        this.jabatanDao.delete(entity);
    }

    @Override
    public void softDelete(Jabatan entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<Jabatan> getAllData() throws Exception {
        return jabatanDao.getAllData();
    }

    @Override
    public List<Jabatan> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jabatan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Jabatan> getByParam(JabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return jabatanDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalJabatanByParam(JabatanSearchParameter searchParameter) throws Exception {
        return this.jabatanDao.getTotalJabatanByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Jabatan getJabatanByLevelOne(Integer level) throws Exception {
        return this.jabatanDao.getJabatanByLevelOne(level);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Jabatan> getJabatanByParentCode(String parentCode) throws Exception {
        return this.jabatanDao.getJabatanByParentCode(parentCode);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public Jabatan getJabatanByIdWithDetail(Long id) throws Exception {
        Jabatan jabatan = jabatanDao.getJabatanByIdWithDetail(id);
        jabatan.getGolonganJabatan().getPangkat().getPangkatName();
        jabatan.getPaySalaryGrade().getId();
        return jabatan;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public Jabatan getJabatanByIdForSpecDetail(Long id) throws Exception {
        Jabatan jabatan = jabatanDao.getJabatanByIdWithDetail(id);
        jabatan.getGolonganJabatan().getPangkat().getPangkatName();
        return jabatan;
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Jabatan> getJabatansByLevel(Integer level) throws Exception {
        return this.jabatanDao.getJabatansByLevel(level);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Jabatan getByIdWithJobDeskripsi(long id) throws Exception {
        Jabatan jabatan = jabatanDao.getByIdWithJobDeskripsi(id);
        jabatan.getGolonganJabatan().getPangkat().getPangkatName();
        List<KlasifikasiKerja> kerjaJabatans = new ArrayList<>();
        for (KlasifikasiKerjaJabatan klasifikasiKerjaJabatan : klasifikasiKerjaJabatanDao.getByJabatanId(id)) {
            kerjaJabatans.add(klasifikasiKerjaJabatan.getKlasifikasiKerja());
        }
        jabatan.setKerjaJabatans(kerjaJabatans);
        return jabatan;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Jabatan getByIdWithKlasifikasiKerja(long id) throws Exception {
        Jabatan jabatan = jabatanDao.getEntiyByPK(id);
        List<KlasifikasiKerja> kerjaJabatans = new ArrayList<>();
        for (KlasifikasiKerjaJabatan klasifikasiKerjaJabatan : klasifikasiKerjaJabatanDao.getByJabatanId(id)) {
            kerjaJabatans.add(klasifikasiKerjaJabatan.getKlasifikasiKerja());
        }
        jabatan.setKerjaJabatans(kerjaJabatans);
        return jabatan;

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Jabatan> getByDepartementId(long id) throws Exception {
        return this.jabatanDao.getByDepartementId(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Jabatan getByIdWithSalaryGrade(long id) throws Exception {
        return jabatanDao.getByIdWithSalaryGrade(id);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    @Override
    public List<Jabatan> getByName(String name) throws Exception {
        return this.jabatanDao.getByName(name);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	@Override
	public List<Jabatan> getAllDataByCodeOrName(String param) throws Exception{
    	return jabatanDao.getAllDataByCodeOrName(param);		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveDataJabatan(JobJabatanModel jobJabatanModel) throws Exception {
		
		//Cek Duplikat Kode Jabatan
		long totalDuplicate = this.jabatanDao.getTotalByCode(jobJabatanModel.getKodeJabatan());
        if (totalDuplicate > 0) {
            throw new BussinessException("jabatan.jabatan_duplicate_code");
        }
        
		Date dateCreated = new Date();
		String userCreated = HrmUserInfoUtil.getUserName();
		
		Jabatan jabatan = getDataJabatanFromModel(jobJabatanModel, Boolean.FALSE);
		jabatan.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
		jabatan.setCreatedBy(userCreated);
		jabatan.setCreatedOn(dateCreated);
		jabatanDao.save(jabatan);
		
		//Saving List JabatanEdukasi
		List<EducationLevel> listEducationLevels = jobJabatanModel.getListEducationLevel();
		for(EducationLevel educationLevel : listEducationLevels){
			JabatanEdukasi jabatanEdukasi = new JabatanEdukasi();
			jabatanEdukasi.setEducationLevel(educationLevelDao.getEntiyByPK(educationLevel.getId()));
			jabatanEdukasi.setJabatan(jabatan);
			jabatanEdukasi.setDescripstion(educationLevel.getDescription());
			jabatanEdukasi.setId(new JabatanEdukasiId(jabatan.getId(), educationLevel.getId()));
			jabatanEdukasiDao.save(jabatanEdukasi);
		}
		
		//Saving List JabatanProfesi
		List<OccupationType> listOccupationTypes = jobJabatanModel.getListOccupationType();
		for(OccupationType occupationType : listOccupationTypes){
			JabatanProfesi jabatanProfesi = new JabatanProfesi();
			jabatanProfesi.setOccupationType(occupationTypeDao.getEntiyByPK(occupationType.getId()));
			jabatanProfesi.setJabatan(jabatan);
			jabatanProfesi.setId(new JabatanProfesiId(jabatan.getId(), occupationType.getId()));
			jabatanProfesiDao.save(jabatanProfesi);
		}
		
		//Saving List JabatanMajor
		List<Major> listMajors = jobJabatanModel.getListMajor();
		for(Major major : listMajors){
			JabatanMajor jabatanMajor = new JabatanMajor();
			jabatanMajor.setMajor(majorDao.getEntiyByPK(major.getId()));
			jabatanMajor.setJabatan(jabatan);
			jabatanMajor.setId(new JabatanMajorId(jabatan.getId(), major.getId()));
			jabatanMajor.setDescription(major.getDescription());
			jabatanMajorDao.save(jabatanMajor);
			
		}
		
		//Saving List JabatanFakulty
		List<Faculty> listFaculties = jobJabatanModel.getListFaculties();
		for(Faculty faculty : listFaculties){
			JabatanFakulty jabatanFakulty = new JabatanFakulty();
			jabatanFakulty.setFaculty(facultyDao.getEntiyByPK(faculty.getId()));
			jabatanFakulty.setJabatan(jabatan);
			jabatanFakulty.setId(new JabatanFakultyId(jabatan.getId(), faculty.getId()));
			jabatanFakulty.setDescription(faculty.getDescription());
			jabatanFakultyDao.save(jabatanFakulty);
		}
		
		//Saving List KlasifikasiKerjaJabatan
		List<KlasifikasiKerja> listKlasifikasiKerja = jobJabatanModel.getListKlasifikasiKerja();
		for(KlasifikasiKerja klasifikasiKerja : listKlasifikasiKerja){
			KlasifikasiKerjaJabatan klasifikasiKerjaJabatan = new KlasifikasiKerjaJabatan();
			klasifikasiKerjaJabatan.setKlasifikasiKerja(klasifikasiKerjaDao.getEntiyByPK(klasifikasiKerja.getId()));
			klasifikasiKerjaJabatan.setJabatan(jabatan);
			klasifikasiKerjaJabatan.setId(new KlasifikasiKerjaJabatanId(jabatan.getId(), klasifikasiKerja.getId()));
			klasifikasiKerjaJabatan.setDescription(klasifikasiKerja.getDescription());
			klasifikasiKerjaJabatanDao.save(klasifikasiKerjaJabatan);
		}
		
		//Saving List JabatanDeskripsi
		List<JabatanDeskripsi> listJabatanDeskripsi = jobJabatanModel.getListJabatanDeskripsi();
		for(JabatanDeskripsi jabatanDeskripsi : listJabatanDeskripsi){
			jabatanDeskripsi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
			jabatanDeskripsi.setCreatedBy(userCreated);
			jabatanDeskripsi.setCreatedOn(dateCreated);
			jabatanDeskripsi.setJabatan(jabatan);
			jabatanDeskripsiDao.save(jabatanDeskripsi);
		}
		
		//Saving List JabatanSpesifikasi
		List<JabatanSpesifikasi> listJabatanSpesifikasi = jobJabatanModel.getListJabatanSpesifikasi();
		for(JabatanSpesifikasi jabatanSpesifikasi : listJabatanSpesifikasi){
			jabatanSpesifikasi.setId(new JabatanSpesifikasiId(jabatan.getId(), jabatanSpesifikasi.getSpecificationAbility().getId()));
			jabatanSpesifikasi.setSpecificationAbility(specificationAbilityDao.getEntiyByPK(jabatanSpesifikasi.getSpecificationAbility().getId()));
			jabatanSpesifikasi.setJabatan(jabatan);
			jabatanSpesifikasi.setCreatedOn(dateCreated);
			jabatanSpesifikasi.setCreatedBy(userCreated);
			jabatanSpesifikasiDao.save(jabatanSpesifikasi);
		}
		
		//Saving List OrgTypeOfSpecJabatan
		List<OrgTypeOfSpecJabatan> listOrgTypeOfSpecJabatans = jobJabatanModel.getListOrgTypeOfSpecJabatan();
		for(OrgTypeOfSpecJabatan orgTypeOfSpecJabatan : listOrgTypeOfSpecJabatans){
			orgTypeOfSpecJabatan.setId(new OrgTypeOfSpecJabatanId(orgTypeOfSpecJabatan.getOrgTypeOfSpecList().getId(), jabatan.getId()));
			orgTypeOfSpecJabatan.setOrgTypeOfSpecList(orgTypeOfSpecListDao.getEntiyByPK(orgTypeOfSpecJabatan.getOrgTypeOfSpecList().getId()));
			orgTypeOfSpecJabatan.setJabatan(jabatan);
			orgTypeOfSpecJabatan.setDescription(orgTypeOfSpecJabatan.getOrgTypeOfSpecList().getDescription());
			orgTypeOfSpecJabatan.setCreatedBy(userCreated);
			orgTypeOfSpecJabatan.setCreatedOn(dateCreated);
			orgTypeOfSpecJabatanDao.save(orgTypeOfSpecJabatan);
		}
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateDataJabatan(JobJabatanModel jobJabatanModel)	throws Exception {
		
		Date dateUpdated = new Date();
		String userUpdated = HrmUserInfoUtil.getUserName();
		
		Jabatan jabatan = getDataJabatanFromModel(jobJabatanModel, Boolean.TRUE);
		jabatan.setUpdatedBy(userUpdated);
		jabatan.setUpdatedOn(dateUpdated);
		jabatanDao.update(jabatan);
		
		// Save/Delete List JabatanEdukasi
		List<EducationLevel> listEducationLevels = jobJabatanModel.getListEducationLevel();
		List<JabatanEdukasi> listJabatanEdukasi = jabatanEdukasiDao.getAllDataByJabatanId(jobJabatanModel.getId());
		
		for(EducationLevel educationLevel : listEducationLevels){
			
			//Hanya educationLevel yang belum ada di jabatanEdukasi yang di simpan, klo sudah ada tidak perlu di update, karena itu hanya tabel tengah antara Jabatan dengan EducationLevel
			 if(!Lambda.exists(listJabatanEdukasi, Lambda.having(Lambda.on(JabatanEdukasi.class).getEducationLevel().getId(), Matchers.equalTo(educationLevel.getId())))){
				 JabatanEdukasi jabatanEdukasi = new JabatanEdukasi();
					jabatanEdukasi.setEducationLevel(educationLevelDao.getEntiyByPK(educationLevel.getId()));
					jabatanEdukasi.setJabatan(jabatan);
					jabatanEdukasi.setDescripstion(educationLevel.getDescription());
					jabatanEdukasi.setId(new JabatanEdukasiId(jabatan.getId(), educationLevel.getId()));
					jabatanEdukasiDao.save(jabatanEdukasi);
			 }
			
		}
		
		//Hapus JabatanEdukasi yang ada di DB, berdasarkan  yang dihapus dari view
		for(JabatanEdukasi jabatanEdukasiFromDb : listJabatanEdukasi){
			if(!Lambda.exists(listEducationLevels, Lambda.having(Lambda.on(EducationLevel.class).getId(), Matchers.equalTo(jabatanEdukasiFromDb.getEducationLevel().getId())))){
				jabatanEdukasiDao.delete(jabatanEdukasiFromDb);
			}
		}
		
		//Save/Delete List JabatanProfesi
		List<OccupationType> listOccupationTypes = jobJabatanModel.getListOccupationType();
		List<JabatanProfesi> listJabatanProfesi = jabatanProfesiDao.getAllDataByJabatanId(jobJabatanModel.getId());
		
		for(OccupationType occupationType : listOccupationTypes){
			
			//Hanya occupationType yang belum ada di listJabatanProfesi yang di simpan, klo sudah ada tidak perlu di update, karena itu hanya tabel tengah antara Jabatan dengan OccupationType
			 if(!Lambda.exists(listJabatanProfesi, Lambda.having(Lambda.on(JabatanProfesi.class).getOccupationType().getId(), Matchers.equalTo(occupationType.getId())))){
				JabatanProfesi jabatanProfesi = new JabatanProfesi();
				jabatanProfesi.setOccupationType(occupationTypeDao.getEntiyByPK(occupationType.getId()));
				jabatanProfesi.setJabatan(jabatan);
				jabatanProfesi.setId(new JabatanProfesiId(jabatan.getId(), occupationType.getId()));
				jabatanProfesiDao.save(jabatanProfesi);
			 }

		}
		
		//Hapus JabatanProfesi yang ada di DB, berdasarkan  yang dihapus dari view
		for(JabatanProfesi jabatanProfesiFromDb : listJabatanProfesi){
			if(!Lambda.exists(listOccupationTypes, Lambda.having(Lambda.on(OccupationType.class).getId(), Matchers.equalTo(jabatanProfesiFromDb.getOccupationType().getId())))){
				jabatanProfesiDao.delete(jabatanProfesiFromDb);
			}
		}
		
		// Save / Delete List JabatanMajor
		List<Major> listMajors = jobJabatanModel.getListMajor();
		List<JabatanMajor> listJabatanMajor = jabatanMajorDao.getAllDataByJabatanId(jobJabatanModel.getId());
		
		for(Major major : listMajors){
			
			//Hanya major yang belum ada di listJabatanMajor yang di simpan, klo sudah ada tidak perlu di update, karena itu hanya tabel tengah antara Jabatan dengan Major
			 if(!Lambda.exists(listJabatanMajor, Lambda.having(Lambda.on(JabatanMajor.class).getMajor().getId(), Matchers.equalTo(major.getId())))){
				JabatanMajor jabatanMajor = new JabatanMajor();
				jabatanMajor.setMajor(majorDao.getEntiyByPK(major.getId()));
				jabatanMajor.setJabatan(jabatan);
				jabatanMajor.setId(new JabatanMajorId(jabatan.getId(), major.getId()));
				jabatanMajor.setDescription(major.getDescription());
				jabatanMajorDao.save(jabatanMajor);
			 }
			
		}
		
		//Hapus JabatanMajor yang ada di DB, berdasarkan  yang dihapus dari view
		for(JabatanMajor jabatanMajorFromDb : listJabatanMajor){
			if(!Lambda.exists(listMajors, Lambda.having(Lambda.on(Major.class).getId(), Matchers.equalTo( jabatanMajorFromDb.getMajor().getId())))){
				jabatanMajorDao.delete(jabatanMajorFromDb);
			}
		}
		
		//Save List JabatanFakulty
		List<Faculty> listFaculties = jobJabatanModel.getListFaculties();
		List<JabatanFakulty> listJabatanFakulties = jabatanFakultyDao.getAllDataByJabatanId(jobJabatanModel.getId());
		
		for(Faculty faculty : listFaculties){
			
			//Hanya faculty yang belum ada di listJabatanFakulties yang di simpan, klo sudah ada tidak perlu di update, karena itu hanya tabel tengah antara Jabatan dengan Faculty
			 if(!Lambda.exists(listJabatanFakulties, Lambda.having(Lambda.on(JabatanFakulty.class).getFaculty().getId(), Matchers.equalTo(faculty.getId())))){
				JabatanFakulty jabatanFakulty = new JabatanFakulty();
				jabatanFakulty.setFaculty(facultyDao.getEntiyByPK(faculty.getId()));
				jabatanFakulty.setJabatan(jabatan);
				jabatanFakulty.setId(new JabatanFakultyId(jabatan.getId(), faculty.getId()));
				jabatanFakulty.setDescription(faculty.getDescription());
				jabatanFakultyDao.save(jabatanFakulty);
			 }
			
		}
		
		//Hapus JabatanFakulty yang ada di DB, berdasarkan  yang dihapus dari view
		for(JabatanFakulty jabatanFacultyFromDb : listJabatanFakulties){
			if(!Lambda.exists(listFaculties, Lambda.having(Lambda.on(Faculty.class).getId(), Matchers.equalTo(jabatanFacultyFromDb.getFaculty().getId())))){
				jabatanFakultyDao.delete(jabatanFacultyFromDb);
			}
		}
		
		// Save / Delete List KlasifikasiKerjaJabatan
		List<KlasifikasiKerja> listKlasifikasiKerja = jobJabatanModel.getListKlasifikasiKerja();
		List<KlasifikasiKerjaJabatan> listKlasifikasiKerjaJabatan = klasifikasiKerjaJabatanDao.getByJabatanId(jobJabatanModel.getId());
		
		for(KlasifikasiKerja klasifikasiKerja : listKlasifikasiKerja){
			
			//Hanya klasifikasiKerja yang belum ada di listKlasifikasiKerjaJabatan yang di simpan, klo sudah ada tidak perlu di update, karena itu hanya tabel tengah antara Jabatan dengan klasifikasiKerja
			 if(!Lambda.exists(listKlasifikasiKerjaJabatan, Lambda.having(Lambda.on(KlasifikasiKerjaJabatan.class).getKlasifikasiKerja().getId(), Matchers.equalTo(klasifikasiKerja.getId())))){
				KlasifikasiKerjaJabatan klasifikasiKerjaJabatan = new KlasifikasiKerjaJabatan();
				klasifikasiKerjaJabatan.setKlasifikasiKerja(klasifikasiKerjaDao.getEntiyByPK(klasifikasiKerja.getId()));
				klasifikasiKerjaJabatan.setJabatan(jabatan);
				klasifikasiKerjaJabatan.setId(new KlasifikasiKerjaJabatanId(jabatan.getId(), klasifikasiKerja.getId()));
				klasifikasiKerjaJabatan.setDescription(klasifikasiKerja.getDescription());
				klasifikasiKerjaJabatanDao.save(klasifikasiKerjaJabatan);
			 }
			
		}
		
		//Hapus KlasifikasiKerjaJabatan yang ada di DB, berdasarkan  yang dihapus dari view
		for(KlasifikasiKerjaJabatan klasifikasiKerjaJabatanFromDb : listKlasifikasiKerjaJabatan){
			if(!Lambda.exists(listKlasifikasiKerja, Lambda.having(Lambda.on(KlasifikasiKerja.class).getId(), Matchers.equalTo(klasifikasiKerjaJabatanFromDb.getKlasifikasiKerja().getId())))){
				klasifikasiKerjaJabatanDao.delete(klasifikasiKerjaJabatanFromDb);
			}
		}
		
		//Save/Update List JabatanDeskripsi
		List<JabatanDeskripsi> listJabatanDeskripsi = jobJabatanModel.getListJabatanDeskripsi();
		List<JabatanDeskripsi> listJabatanDeskripsiFromDb = jabatanDeskripsiDao.getAllDataByJabatanId(jobJabatanModel.getId());
		
		for(JabatanDeskripsi jabatanDeskripsi : listJabatanDeskripsi){
			
			//primitive type, kalau tidak di set defaultnya nilainya 0
			if(jabatanDeskripsi.getId() == 0l){
				jabatanDeskripsi.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
				jabatanDeskripsi.setCreatedBy(userUpdated);
				jabatanDeskripsi.setCreatedOn(dateUpdated);
				jabatanDeskripsi.setJabatan(jabatan);
				jabatanDeskripsiDao.save(jabatanDeskripsi);
			}else{
				JabatanDeskripsi jabatanDeskripsiToUpdate = jabatanDeskripsiDao.getEntiyByPK(jabatanDeskripsi.getId());
				BeanUtils.copyProperties(jabatanDeskripsi, jabatanDeskripsiToUpdate, new String[]{"id","createdOn","createdBy","jabatan","version"});
				jabatanDeskripsiToUpdate.setJabatan(jabatan);
				jabatanDeskripsiToUpdate.setUpdatedBy(userUpdated);
				jabatanDeskripsiToUpdate.setUpdatedOn(dateUpdated);
				jabatanDeskripsiDao.update(jabatanDeskripsiToUpdate);
			}
			
		}
		
		//Hapus JabatanDeskripsi yang ada di DB, berdasarkan  yang dihapus dari view
		for(JabatanDeskripsi jabatanDeskripsiFromDb : listJabatanDeskripsiFromDb){
			if(!Lambda.exists(listJabatanDeskripsi, Lambda.having(Lambda.on(JabatanDeskripsi.class).getId(), Matchers.equalTo(jabatanDeskripsiFromDb.getId())))){
				jabatanDeskripsiDao.delete(jabatanDeskripsiFromDb);
			}
		}
		
		//Save/Update/Delete List JabatanSpesifikasi
		List<JabatanSpesifikasi> listJabatanSpesifikasi = jobJabatanModel.getListJabatanSpesifikasi();
		List<JabatanSpesifikasi> listJabatanSpesifikasiFromDb = jabatanSpesifikasiDao.getAllDataByJabatanId(jobJabatanModel.getId());
		
		for(JabatanSpesifikasi jabatanSpesifikasi : listJabatanSpesifikasi){
			
			if(jabatanSpesifikasi.getId() == null){
				jabatanSpesifikasi.setId(new JabatanSpesifikasiId(jabatan.getId(), jabatanSpesifikasi.getSpecificationAbility().getId()));
				jabatanSpesifikasi.setSpecificationAbility(specificationAbilityDao.getEntiyByPK(jabatanSpesifikasi.getSpecificationAbility().getId()));
				jabatanSpesifikasi.setJabatan(jabatan);
				jabatanSpesifikasi.setCreatedOn(dateUpdated);
				jabatanSpesifikasi.setCreatedBy(userUpdated);
				jabatanSpesifikasiDao.save(jabatanSpesifikasi);
			}else{
				JabatanSpesifikasi jabatanSpesifikasiToUpdate = jabatanSpesifikasiDao.getEntityByBioJabatanSpesifikasiId(jabatanSpesifikasi.getId());
				BeanUtils.copyProperties(jabatanSpesifikasi, jabatanSpesifikasiToUpdate, new String[]{"id","jabatan","specificationAbility","createdOn","createdBy","version"});
				jabatanSpesifikasiToUpdate.setSpecificationAbility(specificationAbilityDao.getEntiyByPK(jabatanSpesifikasi.getSpecificationAbility().getId()));
				jabatanSpesifikasiToUpdate.setJabatan(jabatan);
				jabatanSpesifikasiToUpdate.setUpdatedBy(userUpdated);
				jabatanSpesifikasiToUpdate.setUpdatedOn(dateUpdated);
				jabatanSpesifikasiDao.update(jabatanSpesifikasiToUpdate);
			}
			
		}
		
		//Hapus JabatanSpesifikasi yang ada di DB, berdasarkan  yang dihapus dari view
		for(JabatanSpesifikasi jabatanSpesifikasiFromDb : listJabatanSpesifikasiFromDb){
			if(!Lambda.exists(listJabatanSpesifikasi, Lambda.having(Lambda.on(JabatanSpesifikasi.class).getId(), Matchers.equalTo(jabatanSpesifikasiFromDb.getId())))){
				jabatanSpesifikasiDao.delete(jabatanSpesifikasiFromDb);
			}
		}
		
		//Saving/Update/Delete List OrgTypeOfSpecJabatan
		List<OrgTypeOfSpecJabatan> listOrgTypeOfSpecJabatans = jobJabatanModel.getListOrgTypeOfSpecJabatan();
		List<OrgTypeOfSpecJabatan> listOrgTypeOfSpecJabatansFromDb = orgTypeOfSpecJabatanDao.getAllDataByJabatanId(jobJabatanModel.getId());
		
		for(OrgTypeOfSpecJabatan orgTypeOfSpecJabatan : listOrgTypeOfSpecJabatans){
			
			if(orgTypeOfSpecJabatan.getId() == null){
				orgTypeOfSpecJabatan.setId(new OrgTypeOfSpecJabatanId(orgTypeOfSpecJabatan.getOrgTypeOfSpecList().getId(), jabatan.getId()));
				orgTypeOfSpecJabatan.setOrgTypeOfSpecList(orgTypeOfSpecListDao.getEntiyByPK(orgTypeOfSpecJabatan.getOrgTypeOfSpecList().getId()));
				orgTypeOfSpecJabatan.setJabatan(jabatan);
				orgTypeOfSpecJabatan.setDescription(orgTypeOfSpecJabatan.getOrgTypeOfSpecList().getDescription());
				orgTypeOfSpecJabatan.setCreatedBy(userUpdated);
				orgTypeOfSpecJabatan.setCreatedOn(dateUpdated);
				orgTypeOfSpecJabatanDao.save(orgTypeOfSpecJabatan);
			}else{
				OrgTypeOfSpecJabatan orgTypeOfSpecJabatanToUpdate = orgTypeOfSpecJabatanDao.getEntityByPK(orgTypeOfSpecJabatan.getId());
				orgTypeOfSpecJabatanToUpdate.setOrgTypeOfSpecList(orgTypeOfSpecListDao.getEntiyByPK(orgTypeOfSpecJabatan.getOrgTypeOfSpecList().getId()));
				orgTypeOfSpecJabatanToUpdate.setJabatan(jabatan);
				orgTypeOfSpecJabatanToUpdate.setDescription(orgTypeOfSpecJabatan.getOrgTypeOfSpecList().getDescription());
				orgTypeOfSpecJabatanToUpdate.setUpdatedOn(dateUpdated);
				orgTypeOfSpecJabatanDao.update(orgTypeOfSpecJabatanToUpdate);
			}
			
		}	
		
		//Hapus OrgTypeOfSpecJabatan yang ada di DB, berdasarkan  yang dihapus dari view
		for(OrgTypeOfSpecJabatan orgTypeOfSpecJabatanFromDb : listOrgTypeOfSpecJabatansFromDb){
			if(!Lambda.exists(listOrgTypeOfSpecJabatans, Lambda.having(Lambda.on(OrgTypeOfSpecJabatan.class).getOrgTypeOfSpecList().getId(), Matchers.equalTo(orgTypeOfSpecJabatanFromDb.getOrgTypeOfSpecList().getId())))){
				orgTypeOfSpecJabatanDao.delete(orgTypeOfSpecJabatanFromDb);
			}
		}
		
	}
	
	private Jabatan getDataJabatanFromModel(JobJabatanModel jobJabatanModel, Boolean isEdit){
		
		GolonganJabatan golJabatan = this.golonganJabatanDao.getEntityWithDetailById(jobJabatanModel.getGolonganJabatanId());
		PaySalaryGrade paySalaryGrade = this.paySalaryGradeDao.getEntiyByPK(golJabatan.getPaySalaryGrade().getId());	
		Jabatan jabatan = null;
		
		if(isEdit){
			jabatan = jabatanDao.getEntiyByPK(jobJabatanModel.getId());
		}else{
			jabatan = new Jabatan();
		}
		
		jabatan.setCode(jobJabatanModel.getKodeJabatan());
		jabatan.setName(jobJabatanModel.getNamaJabatan());
		jabatan.setCostCenter(this.costCenterDao.getEntiyByPK(jobJabatanModel.getPosBiayaId()));
		jabatan.setDepartment(this.departmentDao.getEntiyByPK(jobJabatanModel.getDepartementId()));
		jabatan.setGolonganJabatan(golJabatan);
		jabatan.setJabatan(this.jabatanDao.getEntiyByPK(jobJabatanModel.getJabatanAtasanId()));
		jabatan.setTujuanJabatan(jobJabatanModel.getTujuanJabatan());
		jabatan.setPaySalaryGrade(paySalaryGrade);
		jabatan.setUnitKerja(this.unitKerjaDao.getEntiyByPK(jobJabatanModel.getUnitKerjaId()));
		
		return jabatan;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Jabatan getJabatanByCode(String code) throws Exception {
		return jabatanDao.getJabatanByCode(code);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<KompetensiJabatanViewModel> getByParamForKompetensiJabatan(KompetensiJabatanSearchParameter searchParameter,
			int firstResult, int maxResults, Order order) throws Exception {
		System.out.println("MASUK==================================SERVICE");
		List<KompetensiJabatanViewModel> listJabatan = jabatanDao.getByParamForKompetensiJabatan(searchParameter, firstResult, maxResults, order);
		for(KompetensiJabatanViewModel jabatan : listJabatan){
			Long total = empDataDao.getTotalKaryawanByJabatanId(jabatan.getId());
			jabatan.setJumlahPejabat(total.intValue());
		}
		return listJabatan;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParamForKompetensiJabatan(KompetensiJabatanSearchParameter searchParameter) throws Exception {
		return this.jabatanDao.getTotalByParamForKompetensiJabatan(searchParameter);
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<PerformanceIndicatorJabatanViewModel> getByParamForPerformanceIndicatorJabatan(PerformanceIndicatorJabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
		List<PerformanceIndicatorJabatanViewModel> listJabatan = jabatanDao.getByParamForPerformanceIndicatorJabatan(searchParameter, firstResult, maxResults, order);
		
		return listJabatan;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalByParamForPerformanceIndicatorJabatan(PerformanceIndicatorJabatanSearchParameter searchParameter) throws Exception {
		return this.jabatanDao.getTotalByParamForPerformanceIndicatorJabatan(searchParameter);
	}

}
