/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import ch.lambdaj.Lambda;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.web.lazymodel.SearchEmployeeCandidateLazyDataModel;
import com.inkubator.hrm.web.lazymodel.SearchEmployeeLazyDataModel;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateModel;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateViewModel;
import com.inkubator.hrm.web.model.SearchEmployeeModel;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
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
    private EmpDataService empDataService;
    @Autowired
    private EducationLevelService educationLevelService;
    @Autowired
    private RecruitApplicantService recruitApplicantService;
    @Autowired
    private RecruitHireApplyService recruitHireApplyService;

    public SearchEmployeeCandidateModel initSearchEmployeeCandidateFormFlow(RequestContext context) throws Exception {
        //deklarasi variable
        SearchEmployeeCandidateModel searchEmployeeCandidateModel = new SearchEmployeeCandidateModel();
        DualListModel<Jabatan> dualListModelJabatan = new DualListModel<>();
        DualListModel<Religion> dualListModelReligion = new DualListModel<>();

        //get data
        //List<Jabatan> listJabatan = jabatanService.getAllData();
        List<RecruitHireApply> listRecruitHireApplies = recruitHireApplyService.getAllDataWithDetail();
        listRecruitHireApplies = Lambda.select(listRecruitHireApplies, Lambda.having(Lambda.on(RecruitHireApply.class).getApplicationStatus(), Matchers.equalTo(HRMConstant.APPROVAL_STATUS_APPROVED)));
        List<Jabatan> listJabatan = Lambda.extract(listRecruitHireApplies, Lambda.on(RecruitHireApply.class).getJabatan());
        listJabatan = new ArrayList<Jabatan>(Lambda.selectDistinct(listJabatan));//Di Filter supaya tidak duplikat
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

        SearchEmployeeCandidateModel searchEmployeeCandidateModel = (SearchEmployeeCandidateModel) context.getFlowScope().get("searchEmployeeCandidateModel");
        int fromBirth = searchEmployeeCandidateModel.getAgeFrom();
        int untilBirth = searchEmployeeCandidateModel.getAgeUntil();
        int joinDate = searchEmployeeCandidateModel.getWorkingPeriodFrom();
        int untilDate = searchEmployeeCandidateModel.getWorkingPeriodEnd();
        String gender = searchEmployeeCandidateModel.getGender();
        Double gpa = searchEmployeeCandidateModel.getGpa();
        Long educationLevelId = searchEmployeeCandidateModel.getEducationLevelType();
        List<Jabatan> listJabatan = searchEmployeeCandidateModel.getDualListModelJabatan().getTarget();
        List<Religion> listReligion = searchEmployeeCandidateModel.getDualListModelReligion().getTarget();
        List<Integer> listAge = getNumberBetweenFromAndUntil(fromBirth, untilBirth);
        List<Integer> listJoinDate = getNumberBetweenFromAndUntil(joinDate, untilDate);
        
        List<Long> listJabatanId = new ArrayList<>();
        List<Long> listReligionId = new ArrayList<>();

        if (null != listJabatan) {
            listJabatanId = Lambda.extract(listJabatan, Lambda.on(Jabatan.class).getId());
        }

        if (null != listReligion) {
            listReligionId = Lambda.extract(listReligion, Lambda.on(Religion.class).getId());
        }
        
        LazyDataModel<SearchEmployeeCandidateViewModel> lazyDataModel = new SearchEmployeeCandidateLazyDataModel(empDataService, listJabatanId,
                listReligionId, listAge, listJoinDate, gpa, educationLevelId, gender);
        
        searchEmployeeCandidateModel.setLazyDataModel(lazyDataModel);

    }
    
    public String doCommitData(RequestContext context) throws Exception {
    	String result = "error";
    	try{
    		SearchEmployeeCandidateModel searchEmployeeCandidateModel = (SearchEmployeeCandidateModel) context.getFlowScope().get("searchEmployeeCandidateModel");
        	result = recruitApplicantService.commitDataInternalCareerCandidate(searchEmployeeCandidateModel.getListIdEmpDataCandidate());
        	if(StringUtils.equals(result, "success")){
        		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        	}
        	
    	}catch (BussinessException ex) {
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    	
    	return result;
    }

    public List<Integer> getNumberBetweenFromAndUntil(int from, int until) {
        List<Integer> listNumber = new ArrayList<Integer>();
        for (int i = from; i <= until; i++) {
            listNumber.add(i);
        }
        return listNumber;
    }   

    public String doBack() {
        return "/flow-protected/search_employee_candidate";
    }
}
