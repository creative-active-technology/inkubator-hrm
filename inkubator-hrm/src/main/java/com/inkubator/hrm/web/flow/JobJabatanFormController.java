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

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanEdukasi;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.JabatanEdukasiService;
import com.inkubator.hrm.service.JabatanProfesiService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BusinessTravelModel;
import com.inkubator.hrm.web.model.JabatanModel;
import com.inkubator.hrm.web.model.JobJabatanModel;

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
	private JobJabatanModel jobJabatanModel;
	

	public void initJabatanProcessFlow(RequestContext context){
		try {
			
			//binding value to model
			Long id = context.getFlowScope().getLong("id");	
			jobJabatanModel = new JobJabatanModel();
			if(id != null){			
				
				Jabatan jabatan = jabatanService.getByIdWithKlasifikasiKerja(id);
				jobJabatanModel = getJabatanModelFromEntity(jabatan);
				
				List<KlasifikasiKerja> listSourceKlasifikasiKerja = klasifikasiKerjaService.getAllData();
                List<KlasifikasiKerja> target = jabatan.getKerjaJabatans();
                listSourceKlasifikasiKerja.removeAll(target);
                dualListModelKlasifikasiKerja = new DualListModel<>(listSourceKlasifikasiKerja, target);
                
                List<EducationLevel> listSourceEducationLevel = educationLevelService.getAllData();
                List<JabatanEdukasi> listTargetJabatanEdukasi = jabatanEdukasiService.getAllDataByJabatanId(jabatan.getId());
                List<EducationLevel> listTargetEducationLevel = Lambda.extract(listTargetJabatanEdukasi, Lambda.on(JabatanEdukasi.class).getEducationLevel());
                listSourceEducationLevel.removeAll(listTargetEducationLevel);
                dualListModelEducationLevel = new DualListModel<EducationLevel>(listSourceEducationLevel, listTargetEducationLevel);
                
			}else{
				
				jobJabatanModel = new JobJabatanModel();
	            isEdit = Boolean.FALSE;
	            
				List<KlasifikasiKerja> source = klasifikasiKerjaService.getAllData();
	            dualListModelKlasifikasiKerja.setSource(source);
	            
	            List<EducationLevel> listSourceEducationLevel = educationLevelService.getAllData();
	            dualListModelEducationLevel.setSource(listSourceEducationLevel);
			}
			
			context.getFlowScope().put("jobJabatanModel", jobJabatanModel);
			context.getFlowScope().put("dualListModelKlasifikasiKerja", dualListModelKlasifikasiKerja);
			context.getFlowScope().put("dualListModelEducationLevel", dualListModelEducationLevel);
			
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
//	        jbm.setLevelJabatan(jabatan.getLevelJabatan());
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
	 
	 public void doResetJobJabatanEdukasiForm(RequestContext context){
		 DualListModel<EducationLevel> dualListModelEducationLevel = (DualListModel<EducationLevel>) context.getFlowScope().get("dualListModelEducationLevel");
		 
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
		
		context.getFlowScope().put("dualListModelEducationLevel", dualListModelEducationLevel);
	}

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

	 
}
