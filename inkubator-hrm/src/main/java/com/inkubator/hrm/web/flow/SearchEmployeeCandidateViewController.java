/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import ch.lambdaj.Lambda;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.web.lazymodel.SearchEmployeeCandidateLazyDataModel;
import com.inkubator.hrm.web.lazymodel.SearchEmployeeLazyDataModel;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateModel;
import com.inkubator.hrm.web.model.SearchEmployeeModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Component(value = "searchEmployeeCandidateViewController")
@Lazy
public class SearchEmployeeCandidateViewController implements Serializable {

    org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(getClass());
    @Autowired
    private JabatanService jabatanService;
    @Autowired
    private ReligionService religionService;
    @Autowired
    private EmployeeTypeService employeeTypeService;
    @Autowired
    private EmpDataService empDataService;
    @Autowired
    private EducationLevelService educationLevelService;

    public SearchEmployeeCandidateModel initSearchEmployeeCandidateFormFlow(RequestContext context) throws Exception {
        //deklarasi variable
        SearchEmployeeCandidateModel searchEmployeeCandidateModel = new SearchEmployeeCandidateModel();
        DualListModel<Jabatan> dualListModelJabatan = new DualListModel<>();
        DualListModel<Religion> dualListModelReligion = new DualListModel<>();

        //get data
        List<Jabatan> listJabatan = jabatanService.getAllData();
        List<Religion> listReligion = religionService.getAllData();
        
        //assign value
        dualListModelJabatan.setSource(listJabatan);
        dualListModelReligion.setSource(listReligion);
        searchEmployeeCandidateModel.setDualListModelJabatan(dualListModelJabatan);
        searchEmployeeCandidateModel.setDualListModelReligion(dualListModelReligion);

        List<EducationLevel> educationLevelList = educationLevelService.getAllData();
        Map<String, Long> mapEducation = new TreeMap<>();
        for (EducationLevel educationLevel : educationLevelList) {
            mapEducation.put(educationLevel.getName(), educationLevel.getId());
        }
        searchEmployeeCandidateModel.setMapEducation(mapEducation);
      
        return searchEmployeeCandidateModel;
    }

    public void doSearchByParam(RequestContext context) throws Exception {
//        String jabatan = StringUtils.EMPTY;
//        String religion = StringUtils.EMPTY;
       System.out.println("Masuk doSearchByParam");
        SearchEmployeeCandidateModel searchEmployeeCandidateModel = (SearchEmployeeCandidateModel) context.getFlowScope().get("searchEmployeeCandidateModel");
        List<Jabatan> listJabatan = searchEmployeeCandidateModel.getDualListModelJabatan().getTarget();        
        List<Religion> listReligion = searchEmployeeCandidateModel.getDualListModelReligion().getTarget();
         List<Long> listJabatanId = new ArrayList<>();
         List<Long> listReligionId = new ArrayList<>();
         
        if(null != listJabatan){
            listJabatanId = Lambda.extract(listJabatan, Lambda.on(Jabatan.class).getId());
        }
        
        if(null != listReligion){
            listReligionId = Lambda.extract(listReligion, Lambda.on(Religion.class).getId());
        }
        
        //String[] listEmployeeType = searchEmployeeModel.getEmployeeTypeName();
//        int sizeJabatan = searchEmployeeCandidateModel.getDualListModelJabatan().getTarget().size();
//        int sizeReligion = searchEmployeeCandidateModel.getDualListModelReligion().getTarget().size();
        //int sizeTipeKaryawan = searchEmployeeModel.getEmployeeTypeName().length;
        int fromBirth = searchEmployeeCandidateModel.getAgeFrom();
        int untilBirth = searchEmployeeCandidateModel.getAgeUntil();
        int joinDate = searchEmployeeCandidateModel.getWorkingPeriodFrom();
        int untilDate = searchEmployeeCandidateModel.getWorkingPeriodEnd();
    
        Double gpa = searchEmployeeCandidateModel.getGpa();        
        Long educationLevelId = searchEmployeeCandidateModel.getEducationLevelType();
//        String nikFrom = searchEmployeeModel.getNikFrom();
//        String nikUntil = searchEmployeeModel.getNikUntil();
        
//        for (int j = 0; j < sizeJabatan; j++) {
//            if (j == (sizeJabatan - 1)) {
//                jabatan += listJabatan.get(j).getId();
//            } else {
//                jabatan += listJabatan.get(j).getId() + ", ";
//            }
//        }
//
//        for (int r = 0; r < sizeReligion; r++) {
//            if (r == (sizeReligion - 1)) {
//                religion += listReligion.get(r).getCode();
//            } else {
//                religion += listReligion.get(r).getCode() + ", ";
//            }
//        }
//
//        searchEmployeeCandidateModel.setJabatans(jabatan);
//        searchEmployeeCandidateModel.setReligions(religion);

        List<Integer> listAge = getNumberBetweenFromAndUntil(fromBirth, untilBirth);
        List<Integer> listJoinDate = getNumberBetweenFromAndUntil(joinDate, untilDate);
        LazyDataModel<EmpData> lazyDataModel = new SearchEmployeeCandidateLazyDataModel(empDataService, listJabatanId, listReligionId, listAge, listJoinDate, gpa, educationLevelId);
         System.out.println("lazyDataModel after setup is null ?  : " + (lazyDataModel == null));
        searchEmployeeCandidateModel.setLazyDataModel(lazyDataModel);
        
    }

    public List<Integer> getNumberBetweenFromAndUntil(int from, int until) {
        List<Integer> listNumber = new ArrayList<Integer>();
        for (int i = from; i <= until; i++) {
            listNumber.add(i);
        }
        return listNumber;
    }

    public void listEmployeeTypeNameView(RequestContext context) {
        String tipeKaryawan = "";
        SearchEmployeeModel searchEmployeeModel = (SearchEmployeeModel) context.getFlowScope().get("searchEmployeeModel");
        String[] listEmployeeType = searchEmployeeModel.getEmployeeTypeName();
        int sizeTipeKaryawan = searchEmployeeModel.getEmployeeTypeName().length;
        for (int j = 0; j < sizeTipeKaryawan; j++) {
            if (j == (sizeTipeKaryawan - 1)) {
                tipeKaryawan += listEmployeeType[j];
            } else {
                tipeKaryawan += listEmployeeType[j] + ", ";
            }
        }
        searchEmployeeModel.setEmployeeTypeView(tipeKaryawan);
    }

    public String doBack() {
        return "/flow-protected/search_employee_candidate";
    }
}
