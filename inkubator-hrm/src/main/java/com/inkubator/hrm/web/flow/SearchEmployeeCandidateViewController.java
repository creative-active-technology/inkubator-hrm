/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import ch.lambdaj.Lambda;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateModel;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateViewModel;
import com.inkubator.hrm.web.search.SearchEmployeeCandidateParameter;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.primefaces.model.DualListModel;
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
    private RecruitMppApplyDetailService recruitMppApplyDetailService;


    public SearchEmployeeCandidateModel initSearchEmployeeCandidateFormFlow(RequestContext context) throws Exception {
        //deklarasi variable
        SearchEmployeeCandidateModel searchEmployeeCandidateModel = new SearchEmployeeCandidateModel();
        DualListModel<Jabatan> dualListModelJabatan = new DualListModel<Jabatan>();
        DualListModel<Religion> dualListModelReligion = new DualListModel<Religion>();
        DualListModel<EducationLevel> dualListEducationLevel = new DualListModel<EducationLevel>();

        //get data
        List<RecruitMppApplyDetail> listMppDetail = recruitMppApplyDetailService.getAllDataWithDetail();
        listMppDetail = Lambda.select(listMppDetail, Lambda.having(Lambda.on(RecruitMppApplyDetail.class).getRecruitMppApply().getApplicationStatus(), Matchers.equalTo(HRMConstant.APPROVAL_STATUS_APPROVED)));
        List<Jabatan> listJabatan = Lambda.extract(listMppDetail, Lambda.on(RecruitMppApplyDetail.class).getJabatan());
        listJabatan = new ArrayList<Jabatan>(Lambda.selectDistinct(listJabatan));//Di Filter supaya tidak duplikat
        List<Religion> listReligion = religionService.getAllData();
        List<EducationLevel> listEducationLevel = educationLevelService.getAllData();

        //assign value
        dualListModelJabatan.setSource(listJabatan);
        dualListModelReligion.setSource(listReligion);
        dualListEducationLevel.setSource(listEducationLevel);
        searchEmployeeCandidateModel.setDualListModelJabatan(dualListModelJabatan);
        searchEmployeeCandidateModel.setDualListModelReligion(dualListModelReligion);
        searchEmployeeCandidateModel.setDualListEducationLevel(dualListEducationLevel);

        return searchEmployeeCandidateModel;
    }

    public void doSearchByParam(RequestContext context) throws Exception {

        SearchEmployeeCandidateModel searchEmployeeCandidateModel = (SearchEmployeeCandidateModel) context.getFlowScope().get("searchEmployeeCandidateModel");
        SearchEmployeeCandidateParameter searchEmployeeCandidateParameter = generateSearchParam(searchEmployeeCandidateModel);
      
        List<SearchEmployeeCandidateViewModel> list = empDataService.getAllDataEmpCandidateByParamWithDetail(searchEmployeeCandidateParameter);
        searchEmployeeCandidateModel.setListCandidate(list);

    }
    
    private SearchEmployeeCandidateParameter generateSearchParam(SearchEmployeeCandidateModel searchEmployeeCandidateModel){
    	SearchEmployeeCandidateParameter searchEmployeeCandidateParameter = new SearchEmployeeCandidateParameter();
    	
    	int fromBirth = searchEmployeeCandidateModel.getAgeFrom();
        int untilBirth = searchEmployeeCandidateModel.getAgeUntil();
        int joinDate = searchEmployeeCandidateModel.getWorkingPeriodFrom();
        int untilDate = searchEmployeeCandidateModel.getWorkingPeriodEnd();
        
        String gender = searchEmployeeCandidateModel.getGender();
        Double gpa = searchEmployeeCandidateModel.getGpa();
        
        List<EducationLevel> listEducationLevel = searchEmployeeCandidateModel.getDualListEducationLevel().getTarget();
        List<Jabatan> listJabatan = searchEmployeeCandidateModel.getDualListModelJabatan().getTarget();
        List<Religion> listReligion = searchEmployeeCandidateModel.getDualListModelReligion().getTarget();
        List<Integer> listAge = getNumberBetweenFromAndUntil(fromBirth, untilBirth);
        List<Integer> listJoinDate = getNumberBetweenFromAndUntil(joinDate, untilDate);
        
        List<Long> listJabatanId = new ArrayList<>();
        List<Long> listReligionId = new ArrayList<>();
        List<Long> listEducationLevelId = new ArrayList<>();

        if (null != listJabatan) {
            listJabatanId = Lambda.extract(listJabatan, Lambda.on(Jabatan.class).getId());
        }

        if (null != listReligion) {
            listReligionId = Lambda.extract(listReligion, Lambda.on(Religion.class).getId());
        }
        
        if (null != listEducationLevel) {
        	listEducationLevelId = Lambda.extract(listEducationLevel, Lambda.on(EducationLevel.class).getId());
        }
        
        searchEmployeeCandidateParameter.setGender(gender);
        searchEmployeeCandidateParameter.setGpa(gpa);
        searchEmployeeCandidateParameter.setListAge(listAge);
        searchEmployeeCandidateParameter.setListJoinDate(listJoinDate);
        searchEmployeeCandidateParameter.setListEducationlevelId(listEducationLevelId);
        searchEmployeeCandidateParameter.setListJabatanId(listJabatanId);
        searchEmployeeCandidateParameter.setListReligionId(listReligionId);
        
    	return searchEmployeeCandidateParameter;
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
