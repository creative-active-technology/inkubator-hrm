/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.JabatanEdukasi;
import com.inkubator.hrm.entity.JabatanFakulty;
import com.inkubator.hrm.entity.JabatanMajor;
import com.inkubator.hrm.entity.JabatanProfesi;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.KlasifikasiKerjaJabatan;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.FacultyService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.JabatanDeskripsiService;
import com.inkubator.hrm.service.JabatanEdukasiService;
import com.inkubator.hrm.service.JabatanFacultyService;
import com.inkubator.hrm.service.JabatanMajorService;
import com.inkubator.hrm.service.JabatanProfesiService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.KlasifikasiKerjaJabatanService;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.service.MajorService;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BusinessTravelModel;
import com.inkubator.hrm.web.model.JabatanModel;
import com.inkubator.hrm.web.model.JobJabatanModel;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Component(value = "jobJabatanFormController")
@Lazy
public class JobJabatanFormController implements Serializable {
	org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
	@Autowired
	private JabatanService jabatanService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private GolonganJabatanService golonganJabatanService;
	@Autowired
	private CostCenterService costCenterService;
	@Autowired
	private UnitKerjaService unitKerjaService;
	@Autowired
	private KlasifikasiKerjaService klasifikasiKerjaService;
	@Autowired
	private EducationLevelService educationLevelService;
	@Autowired
	private JabatanEdukasiService jabatanEdukasiService;
	@Autowired
	private OccupationTypeService occupationTypeService;
	@Autowired
	private JabatanProfesiService jabatanProfesiService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private JabatanMajorService jabatanMajorService;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private JabatanFacultyService jabatanFacultyService;
	@Autowired
	private KlasifikasiKerjaJabatanService klasifikasiKerjaJabatanService;
	@Autowired
	private JabatanDeskripsiService jabatanDeskripsiService;
	
	private Boolean isDisable;
	private Boolean isEdit;
	
	private Map<String, Long> departments = new TreeMap<>();
	private Map<String, Long> untiKerjas = new TreeMap<>();
	private Map<String, Long> golJabatans = new TreeMap<>();
	private Map<String, Long> posBiayas = new TreeMap<>();
	private Map<String, Long> jabatanAtasans = new TreeMap<>();
	private DualListModel<KlasifikasiKerja> dualListModelKlasifikasiKerja = new DualListModel<>();
	private DualListModel<EducationLevel> dualListModelEducationLevel = new DualListModel<>();
	private DualListModel<OccupationType> dualListModelOccupationType = new DualListModel<>();
	private DualListModel<Major> dualListModelMajor = new DualListModel<>();
	private DualListModel<Faculty> dualListModelFaculty = new DualListModel<>();
	private JabatanDeskripsi selectedJabatanDeskripsi;
	
	private JobJabatanModel jobJabatanModel;
	

	public void initJabatanProcessFlow(RequestContext context){
		try {
			
			//binding value to model
			Long id = context.getFlowScope().getLong("id");	
			jobJabatanModel = new JobJabatanModel();
			if(id != null){			
				
				Jabatan jabatan = jabatanService.getByIdWithKlasifikasiKerja(id);
				jobJabatanModel = getJabatanModelFromEntity(jabatan);
				
                List<EducationLevel> listSourceEducationLevel = educationLevelService.getAllData();
                List<JabatanEdukasi> listTargetJabatanEdukasi = jabatanEdukasiService.getAllDataByJabatanId(jabatan.getId());
                List<EducationLevel> listTargetEducationLevel = Lambda.extract(listTargetJabatanEdukasi, Lambda.on(JabatanEdukasi.class).getEducationLevel());
                listSourceEducationLevel.removeAll(listTargetEducationLevel);
                dualListModelEducationLevel = new DualListModel<EducationLevel>(listSourceEducationLevel, listTargetEducationLevel);
                
                List<OccupationType> listSourceOccupationType = occupationTypeService.getAllData();
                List<JabatanProfesi> listTargetJabatanProfesi = jabatanProfesiService.getAllDataByJabatanId(jabatan.getId());
                List<OccupationType> listTargetOccupationType = Lambda.extract(listTargetJabatanProfesi, Lambda.on(JabatanProfesi.class).getOccupationType());
                listSourceOccupationType.removeAll(listTargetOccupationType);
                dualListModelOccupationType = new DualListModel<OccupationType>(listSourceOccupationType, listTargetOccupationType);
                
                List<Major> listSourceMajor = majorService.getAllData();
                List<JabatanMajor> listTargetJabatanMajor = jabatanMajorService.getAllDataByJabatanId(jabatan.getId());
                List<Major> listTargetMajor = Lambda.extract(listTargetJabatanMajor, Lambda.on(JabatanMajor.class).getMajor());
                listSourceMajor.removeAll(listTargetMajor);
                dualListModelMajor = new DualListModel<Major>(listSourceMajor, listTargetMajor);
                
                List<Faculty> listSourceFaculty = facultyService.getAllData();
                List<JabatanFakulty> listTargetJabatanFakulty = jabatanFacultyService.getAllDataByJabatanId(jabatan.getId());
                List<Faculty> listTargetFaculty = Lambda.extract(listTargetJabatanFakulty, Lambda.on(JabatanFakulty.class).getFaculty());
                listSourceFaculty.removeAll(listTargetFaculty);
                dualListModelFaculty = new DualListModel<Faculty>(listSourceFaculty, listTargetFaculty);
                
                List<KlasifikasiKerja> listSourceKlasifikasiKerja = klasifikasiKerjaService.getAllData();
			    List<KlasifikasiKerjaJabatan> listTargetKlasifikasiKerjaJabatan = klasifikasiKerjaJabatanService.getAllDataByJabatanId(jobJabatanModel.getId());
			    List<KlasifikasiKerja> listTargetKlasifikasiKerja = Lambda.extract(listTargetKlasifikasiKerjaJabatan, Lambda.on(KlasifikasiKerjaJabatan.class).getKlasifikasiKerja());
			    listSourceKlasifikasiKerja.removeAll(listTargetKlasifikasiKerja);
			    dualListModelKlasifikasiKerja = new DualListModel<KlasifikasiKerja>(listSourceKlasifikasiKerja, listTargetKlasifikasiKerja);
			    
			    List<JabatanDeskripsi> listJabatanDeskripsi = jabatanDeskripsiService.getAllDataByJabatanId(jobJabatanModel.getId());
			    jobJabatanModel.setListJabatanDeskripsi(listJabatanDeskripsi);
                
			}else{
				
				jobJabatanModel = new JobJabatanModel();
	            isEdit = Boolean.FALSE;
	            
				List<KlasifikasiKerja> source = klasifikasiKerjaService.getAllData();
	            dualListModelKlasifikasiKerja.setSource(source);
	            
	            List<EducationLevel> listSourceEducationLevel = educationLevelService.getAllData();
	            dualListModelEducationLevel.setSource(listSourceEducationLevel);
	            
	            List<OccupationType> listSourceOccupationType = occupationTypeService.getAllData();
	            dualListModelOccupationType.setSource(listSourceOccupationType);
	            
	            List<Major> listSourceMajor = majorService.getAllData();
	            dualListModelMajor.setSource(listSourceMajor);
	            
	            List<Faculty> listSourceFaculty = facultyService.getAllData();
	            dualListModelFaculty.setSource(listSourceFaculty);
	            
	            List<KlasifikasiKerja> listSourceKlasifikasiKerja = klasifikasiKerjaService.getAllData();
	            dualListModelKlasifikasiKerja.setSource(listSourceKlasifikasiKerja);
	            
	            jobJabatanModel.setListJabatanDeskripsi(new ArrayList<JabatanDeskripsi>());
			}
			
			context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
			
			//Inisialisasi List Departemen
			List<Department> listDepartemens = departmentService.getAllData();
			for (Department department : listDepartemens) {
	               departments.put(department.getDepartmentName(), department.getId());
	        }
			MapUtil.sortByValue(departments);
			context.getFlowScope().put("departments", departments);
			
			//Inisialisasi List GolonganJabatan
			List<GolonganJabatan> listGolonganJabatans = golonganJabatanService.getAllWithDetail();
			for (GolonganJabatan golonganJabatan : listGolonganJabatans) {
                golJabatans.put(golonganJabatan.getCode() + " - " + golonganJabatan.getPangkat().getPangkatName(), golonganJabatan.getId());
            }
			MapUtil.sortByValue(golJabatans);
			context.getFlowScope().put("golJabatans", golJabatans);
			
			//Inisialisasi CostCenter
			List<CostCenter> listCostCenters = costCenterService.getAllData();
			for (CostCenter costCenter : listCostCenters) {
                posBiayas.put(costCenter.getName(), costCenter.getId());
            }
			MapUtil.sortByValue(posBiayas);
			context.getFlowScope().put("posBiayas", posBiayas);
			
			//Inisialisasi UnitKerja
			List<UnitKerja> listUnitKerjas = unitKerjaService.getAllData();
			for (UnitKerja unitKerja : listUnitKerjas) {
                untiKerjas.put(unitKerja.getName(), unitKerja.getId());
            }
			MapUtil.sortByValue(untiKerjas);
			context.getFlowScope().put("untiKerjas", untiKerjas);
			
			//Inisialisasi Jabatan (atasan)
			List<Jabatan> listJabatans = jabatanService.getAllData();
			for (Jabatan jabatan : listJabatans) {
                jabatanAtasans.put(jabatan.getCode() + " | " + jabatan.getName(), jabatan.getId());
            }
			MapUtil.sortByValue(jabatanAtasans);
			context.getFlowScope().put("jabatanAtasans", jabatanAtasans);
			
			
		}catch(Exception e){
			
		}
	}
	
	 private JobJabatanModel getJabatanModelFromEntity(Jabatan jabatan) {
		 	JobJabatanModel jbm = new JobJabatanModel();
	        jbm.setDepartementId(jabatan.getDepartment().getId());
	        jbm.setGolonganJabatanId(jabatan.getGolonganJabatan().getId());
	        jbm.setId(jabatan.getId());
	        if (jabatan.getJabatan() != null) {
	            jbm.setJabatanAtasanId(jabatan.getJabatan().getId());
	        }
	        jbm.setKodeJabatan(jabatan.getCode());
	        jbm.setNamaJabatan(jabatan.getName());
	        jbm.setPosBiayaId(jabatan.getCostCenter().getId());
	        jbm.setTujuanJabatan(jabatan.getTujuanJabatan());
	        jbm.setUnitKerjaId(jabatan.getUnitKerja().getId());
	        return jbm;
	    }
	 
	 public void doChangeDepartement() {
	        untiKerjas=new HashMap<>();
	        try {
	            Department selected = departmentService.getDepartementWithUnitKerja(jobJabatanModel.getDepartementId());
	            List<UnitKerja> listUnitKerjas = selected.getListUnit();
	            for (UnitKerja unitKerja : listUnitKerjas) {
	                untiKerjas.put(unitKerja.getName(), unitKerja.getId());
	            }

	        } catch (Exception ex) {
	            LOGGER.error(ex, ex);
	        }
	    }
	 
	 public void doResetJobJabatanForm(RequestContext context){
		JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		if(jobJabatanModel.getId() == null){
			jobJabatanModel = new JobJabatanModel();
		} else {
			try {
				Jabatan jabatan = jabatanService.getByIdWithKlasifikasiKerja(jobJabatanModel.getId());
				jobJabatanModel = getJabatanModelFromEntity(jabatan);
			} catch (Exception e) {
				LOGGER.error("Error", e);
			}
		}
		
		context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	}
	
	 /* Start method JabatanEdukasi */
	 
	 public void setListEdukasiToFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 jobJabatanModel.setListEducationLevel(dualListModelEducationLevel.getTarget());
		 context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	 }
	 
	 public void getListEdukasiFromFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 List<EducationLevel> listModelEducationLevel =  jobJabatanModel.getListEducationLevel();
		 dualListModelEducationLevel.setTarget(listModelEducationLevel);
	 }
	 
	 public void doResetJobJabatanEdukasiForm(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		try {
			if(jobJabatanModel.getId() == null){
				 List<EducationLevel> listSourceEducationLevel = educationLevelService.getAllData();
			     dualListModelEducationLevel.setSource(listSourceEducationLevel);
			     dualListModelEducationLevel.setTarget(new ArrayList<EducationLevel>());
			} else {
				
				List<EducationLevel> listSourceEducationLevel = educationLevelService.getAllData();
			    List<JabatanEdukasi> listTargetJabatanEdukasi = jabatanEdukasiService.getAllDataByJabatanId(jobJabatanModel.getId());
			    List<EducationLevel> listTargetEducationLevel = Lambda.extract(listTargetJabatanEdukasi, Lambda.on(JabatanEdukasi.class).getEducationLevel());
			    listSourceEducationLevel.removeAll(listTargetEducationLevel);
			    dualListModelEducationLevel = new DualListModel<EducationLevel>(listSourceEducationLevel, listTargetEducationLevel);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jobJabatanModel.setListEducationLevel(new ArrayList<EducationLevel>());
		context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	}
	 
	 /* End method JabatanEdukasi */
	 
	 /* Start method JabatanProfesi */
	 public void setListOccupationTypeToFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 jobJabatanModel.setListOccupationType(dualListModelOccupationType.getTarget());
		 context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	 }
	 
	 public void getListOccupationTypeFromFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 List<OccupationType> listOccupationType =  jobJabatanModel.getListOccupationType();
		 dualListModelOccupationType.setTarget(listOccupationType);
	 }
	 
	 public void doResetJobJabatanProfesiForm(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 
		try {
			if(jobJabatanModel.getId() == null){
				 List<OccupationType> listSourceOccupationType = occupationTypeService.getAllData();
			     dualListModelOccupationType.setSource(listSourceOccupationType);
			     dualListModelOccupationType.setTarget(new ArrayList<OccupationType>());
			} else {
				
				List<OccupationType> listSourceEducationLevel = occupationTypeService.getAllData();
			    List<JabatanProfesi> listTargetJabatanProfesi = jabatanProfesiService.getAllDataByJabatanId(jobJabatanModel.getId());
			    List<OccupationType> listTargetOccupationType = Lambda.extract(listTargetJabatanProfesi, Lambda.on(JabatanProfesi.class).getOccupationType());
			    listSourceEducationLevel.removeAll(listTargetOccupationType);
			    dualListModelOccupationType = new DualListModel<OccupationType>(listSourceEducationLevel, listTargetOccupationType);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jobJabatanModel.setListOccupationType(new ArrayList<OccupationType>());
		context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	}
	 
	 /* End method JabatanProfesi */
	 
	 /* Start method JabatanMajor */
	 
	 public void setListMajorToFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 jobJabatanModel.setListMajor(dualListModelMajor.getTarget());
		 context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	 }
	 
	 public void getListMajorFromFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 List<Major> listMajor =  jobJabatanModel.getListMajor();
		 dualListModelMajor.setTarget(listMajor);
	 }
	 
	 public void doResetJobJabatanMajorForm(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 
		try {
			if(jobJabatanModel.getId() == null){
				 List<Major> listSourceMajor = majorService.getAllData();
			     dualListModelMajor.setSource(listSourceMajor);
			     dualListModelMajor.setTarget(new ArrayList<Major>());
			} else {
				
				List<Major> listSourceMajor = majorService.getAllData();
			    List<JabatanMajor> listTargetJabatanMajor = jabatanMajorService.getAllDataByJabatanId(jobJabatanModel.getId());
			    List<Major> listTargetMajor = Lambda.extract(listTargetJabatanMajor, Lambda.on(JabatanMajor.class).getMajor());
			    listSourceMajor.removeAll(listTargetMajor);
			    dualListModelMajor = new DualListModel<Major>(listSourceMajor, listTargetMajor);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jobJabatanModel.setListMajor(new ArrayList<Major>());
		context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	}
	 
	 /* End method JabatanMajor */
	 
	 /* Start method JabatanFaculty */
	 public void setListFacultyToFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 jobJabatanModel.setListFaculties(dualListModelFaculty.getTarget());
		 context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	 }
	 
	 public void getListFacultyFromFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 List<Faculty> listFaculties =  jobJabatanModel.getListFaculties();
		 dualListModelFaculty.setTarget(listFaculties);
	 }
	 
	 public void doResetJobJabatanFacultyForm(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		try {
			if(jobJabatanModel.getId() == null){
				 List<Faculty> listSourceFaculty = facultyService.getAllData();
			     dualListModelFaculty.setSource(listSourceFaculty);
			     dualListModelFaculty.setTarget(new ArrayList<Faculty>());
			} else {
				
				List<Faculty> listSourceFaculty = facultyService.getAllData();
			    List<JabatanFakulty> listTargetJabatanFakulty = jabatanFacultyService.getAllDataByJabatanId(jobJabatanModel.getId());
			    List<Faculty> listTargetFaculty = Lambda.extract(listTargetJabatanFakulty, Lambda.on(JabatanFakulty.class).getFaculty());
			    listSourceFaculty.removeAll(listTargetFaculty);
			    dualListModelFaculty = new DualListModel<Faculty>(listSourceFaculty, listTargetFaculty);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jobJabatanModel.setListFaculties(new ArrayList<Faculty>());
		context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	}
	 
	 /* End method JabatanFaculty */
	 
	 /* Start method KlasifikasiKerjaJabatan */
	 
	 public void setListKlasifikasiKerjaToFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 jobJabatanModel.setListKlasifikasiKerja(dualListModelKlasifikasiKerja.getTarget());
		 context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	 }
	 
	 public void getListKlasifikasiKerjaFromFlow(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		 List<KlasifikasiKerja> listKlasifikasiKerja =  jobJabatanModel.getListKlasifikasiKerja();
		 dualListModelKlasifikasiKerja.setTarget(listKlasifikasiKerja);
	 }
	 
	 public void doResetJobJabatanKlasifikasiKerjaForm(RequestContext context){
		 JobJabatanModel jobJabatanModel = (JobJabatanModel) context.getFlowScope().get("jobJabatanModel");
		try {
			if(jobJabatanModel.getId() == null){
				 List<KlasifikasiKerja> listSourceKlasifikasiKerja = klasifikasiKerjaService.getAllData();
				 dualListModelKlasifikasiKerja.setSource(listSourceKlasifikasiKerja);
				 dualListModelKlasifikasiKerja.setTarget(new ArrayList<KlasifikasiKerja>());
			} else {
				
				List<KlasifikasiKerja> listSourceKlasifikasiKerja = klasifikasiKerjaService.getAllData();
			    List<KlasifikasiKerjaJabatan> listTargetKlasifikasiKerjaJabatan = klasifikasiKerjaJabatanService.getAllDataByJabatanId(jobJabatanModel.getId());
			    List<KlasifikasiKerja> listTargetKlasifikasiKerja = Lambda.extract(listTargetKlasifikasiKerjaJabatan, Lambda.on(KlasifikasiKerjaJabatan.class).getKlasifikasiKerja());
			    listSourceKlasifikasiKerja.removeAll(listTargetKlasifikasiKerja);
			    dualListModelKlasifikasiKerja = new DualListModel<KlasifikasiKerja>(listSourceKlasifikasiKerja, listTargetKlasifikasiKerja);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jobJabatanModel.setListKlasifikasiKerja(new ArrayList<KlasifikasiKerja>());
		context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
	}
	 
	 /* End method KlasifikasiKerjaJabatan */
	 
	 /* Start Deskripsi Jabatan*/
	 
	 public void doSelectJabatanDeskripsi() {
	        try {
	            selectedJabatanDeskripsi = this.jabatanDeskripsiService.getEntiyByPK(selectedJabatanDeskripsi.getId());
	        } catch (Exception ex) {
	            LOGGER.error("Error", ex);
	        }
	    }
	 
	 public void doDeleteJabatanDeskripsi() {
	        try {
	            this.jabatanDeskripsiService.delete(selectedJabatanDeskripsi);
	            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
	                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

	        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
	            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
	                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	            LOGGER.error("Error", ex);
	        } catch (Exception ex) {
	            LOGGER.error("Error", ex);
	        }
	    }
	 
	 public void doAddJabatanDeskripsi(){
		Map<String, List<String>> dataToSend = new HashMap<>();
		List<String> dataIsi = new ArrayList<>();
		dataIsi.add(String.valueOf(jobJabatanModel.getId()));
		dataToSend.put("jabatanId", dataIsi);
		//showDialog(dataToSend);
	 }
	 
	 /* End Deskripsi Jabatan*/

	public Boolean getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Boolean isDisable) {
		this.isDisable = isDisable;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public DualListModel<EducationLevel> getDualListModelEducationLevel() {
		return dualListModelEducationLevel;
	}

	public void setDualListModelEducationLevel(
			DualListModel<EducationLevel> dualListModelEducationLevel) {
		this.dualListModelEducationLevel = dualListModelEducationLevel;
	}

	public DualListModel<KlasifikasiKerja> getDualListModelKlasifikasiKerja() {
		return dualListModelKlasifikasiKerja;
	}

	public void setDualListModelKlasifikasiKerja(
			DualListModel<KlasifikasiKerja> dualListModelKlasifikasiKerja) {
		this.dualListModelKlasifikasiKerja = dualListModelKlasifikasiKerja;
	}

	public DualListModel<OccupationType> getDualListModelOccupationType() {
		return dualListModelOccupationType;
	}

	public void setDualListModelOccupationType(
			DualListModel<OccupationType> dualListModelOccupationType) {
		this.dualListModelOccupationType = dualListModelOccupationType;
	}

	public DualListModel<Major> getDualListModelMajor() {
		return dualListModelMajor;
	}

	public void setDualListModelMajor(DualListModel<Major> dualListModelMajor) {
		this.dualListModelMajor = dualListModelMajor;
	}

	public DualListModel<Faculty> getDualListModelFaculty() {
		return dualListModelFaculty;
	}

	public void setDualListModelFaculty(DualListModel<Faculty> dualListModelFaculty) {
		this.dualListModelFaculty = dualListModelFaculty;
	}

	public JabatanDeskripsi getSelectedJabatanDeskripsi() {
		return selectedJabatanDeskripsi;
	}

	public void setSelectedJabatanDeskripsi(
			JabatanDeskripsi selectedJabatanDeskripsi) {
		this.selectedJabatanDeskripsi = selectedJabatanDeskripsi;
	}

	
	
	
	
}
