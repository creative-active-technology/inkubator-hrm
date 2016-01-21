package com.inkubator.hrm.web.recruitment;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.web.personalia.*;
import com.inkubator.hrm.web.reference.*;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioEmploymentHistory;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.service.BioEmploymentHistoryService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.hrm.service.RecruitMppApplyDetailService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.BioEmploymentHistoryModel;
import com.inkubator.hrm.web.model.MppApplyDetailModel;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "mppApplyDetailFormController")
@ViewScoped
public class MppApplyDetailFormController extends BaseController {

    private MppApplyDetailModel mppApplyDetailModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;
    @ManagedProperty(value = "#{recruitMppApplyDetailService}")
    private RecruitMppApplyDetailService recruitMppApplyDetailService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{recruitMppPeriodService}")
    private RecruitMppPeriodService recruitMppPeriodService;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private Map<String, Long> mapDepartement = new HashMap<>();
    private Map<String, Long> mapJabatan = new HashMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {

            mppApplyDetailModel = new MppApplyDetailModel();
            isUpdate = Boolean.FALSE;

            List<Department> listDepartments = departmentService.getAllData();
            for (Department department : listDepartments) {
                mapDepartement.put(department.getDepartmentName(), department.getId());
            }

            String mppApplyCode = FacesUtil.getRequestParameter("mppApplyCode");
            String mppApplyName = FacesUtil.getRequestParameter("mppApplyName");
            String mppApplyPeriod = FacesUtil.getRequestParameter("mppApplyPeriod");
            String isDataUpdate = FacesUtil.getRequestParameter("isUpdate");
            
            RecruitMppPeriod mppPeriod = recruitMppPeriodService.getEntiyByPK(Long.parseLong(mppApplyPeriod));
            
            
            if (StringUtils.equals(isDataUpdate, "true")) {
                isUpdate = Boolean.TRUE;
                
                //get existing data parameter
                String idDetail = FacesUtil.getRequestParameter("idDetail");
                String idJabatan = FacesUtil.getRequestParameter("jabatan");
                String departemen = FacesUtil.getRequestParameter("departemen");
                String numberOfActualPosition = FacesUtil.getRequestParameter("numberOfActualPosition");
                String numberOfPlanningPosition = FacesUtil.getRequestParameter("numberOfPlanningPosition");               
                
                mapJabatan.clear();
                List<Jabatan> listJabatan = jabatanService.getByDepartementId(Long.parseLong(departemen));
                for (Jabatan jabatan : listJabatan) {
                    mapJabatan.put(jabatan.getName(), jabatan.getId());
                }
                
                //if isUpdate equals true, set field with existing data
                mppApplyDetailModel.setDepartemenId(Long.parseLong(departemen));               
                mppApplyDetailModel.setJabatanId(Long.parseLong(idJabatan));
                mppApplyDetailModel.setId(Long.parseLong(idDetail));
                mppApplyDetailModel.setNumberOfActualPosition(Long.parseLong(numberOfActualPosition));
                mppApplyDetailModel.setNumberOfPlanningPosition(Long.parseLong(numberOfPlanningPosition));
                
            } else if (StringUtils.equals(isDataUpdate, "false")) {
                //if isUpdate equals false, set new Random ID
                mppApplyDetailModel.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            }

            mppApplyDetailModel.setMppCode(mppApplyCode);
            mppApplyDetailModel.setMppName(mppApplyName);
            mppApplyDetailModel.setMppPeriodeId(Long.parseLong(mppApplyPeriod));
            mppApplyDetailModel.setMppPeriodeName(mppPeriod.getName());
            mppApplyDetailModel.setRecruitMppMoth(mppPeriod.getPeriodeStart());

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void updateJabatanByDepartment() {
        try {

            mapJabatan.clear();
            mppApplyDetailModel.setJabatanId(null);
            mppApplyDetailModel.setNumberOfActualPosition(0l);

            List<Jabatan> listJabatan = jabatanService.getByDepartementId(mppApplyDetailModel.getDepartemenId());
            for (Jabatan jabatan : listJabatan) {
                mapJabatan.put(jabatan.getName(), jabatan.getId());
            }

        } catch (Exception ex) {
            Logger.getLogger(MppApplyDetailFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateActualTotalEmployeeBasedOnJabatan() {
        try {
            Long totalEmp = empDataService.getTotalKaryawanByJabatanId(HrmUserInfoUtil.getCompanyId(), mppApplyDetailModel.getJabatanId());
            mppApplyDetailModel.setNumberOfActualPosition(totalEmp);
        } catch (Exception ex) {
            Logger.getLogger(MppApplyDetailFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        recruitMppApplyService = null;
        recruitMppApplyDetailService = null;
        recruitMppPeriodService = null;
        departmentService = null;
        jabatanService = null;
        isUpdate = null;
    }
    
    public void doReset() {
    	if(!isUpdate){
    		mppApplyDetailModel.setDepartemenId(null);               
            mppApplyDetailModel.setJabatanId(null);
    	}
    	 
    }

    public void doSave() {
    	
        RecruitMppApplyDetail recruitMppApplyDetail = getEntityFromViewModel(mppApplyDetailModel);
        
        try {
        	checkPositionAlreadyExistAtSelectedMppPeriod(recruitMppApplyDetail);
            RequestContext.getCurrentInstance().closeDialog(recruitMppApplyDetail);
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private RecruitMppApplyDetail getEntityFromViewModel(MppApplyDetailModel mppApplyDetailModel) {
        try {

            RecruitMppApplyDetail recruitMppApplyDetail = new RecruitMppApplyDetail();
            if (mppApplyDetailModel.getId() != null) {
                recruitMppApplyDetail.setId(mppApplyDetailModel.getId());
            }

            Jabatan jabatan = jabatanService.getEntiyByPK(mppApplyDetailModel.getJabatanId());

            recruitMppApplyDetail.setJabatan(jabatan);
            recruitMppApplyDetail.setRecruitMppMonth(mppApplyDetailModel.getRecruitMppMoth());

            Integer plan = mppApplyDetailModel.getNumberOfPlanningPosition().intValue();
            Integer actual = mppApplyDetailModel.getNumberOfActualPosition().intValue();
            Integer difference = plan == actual ? 0 : plan > actual ? (plan - actual) : (actual - plan);
            recruitMppApplyDetail.setActualNumber(actual);
            recruitMppApplyDetail.setRecruitPlan(plan);
            recruitMppApplyDetail.setDifference(difference);

            return recruitMppApplyDetail;
        } catch (Exception ex) {
            Logger.getLogger(MppApplyDetailFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void checkPositionAlreadyExistAtSelectedMppPeriod(RecruitMppApplyDetail recruitMppApplyDetail) throws Exception{
    	Jabatan jabatan = recruitMppApplyDetail.getJabatan();
    	Boolean result = recruitMppApplyDetailService.isJabatanMppExistOnSelectedMppPeriod(jabatan.getId(), mppApplyDetailModel.getMppPeriodeId());
    	if(result){
    		throw new BussinessException("mpp_recruitment.selected_position_already_exist_at_selected");
    	}
    }
    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public MppApplyDetailModel getMppApplyDetailModel() {
        return mppApplyDetailModel;
    }

    public void setMppApplyDetailModel(MppApplyDetailModel mppApplyDetailModel) {
        this.mppApplyDetailModel = mppApplyDetailModel;
    }

    public void setRecruitMppApplyService(RecruitMppApplyService recruitMppApplyService) {
        this.recruitMppApplyService = recruitMppApplyService;
    }

    public void setRecruitMppApplyDetailService(RecruitMppApplyDetailService recruitMppApplyDetailService) {
        this.recruitMppApplyDetailService = recruitMppApplyDetailService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void setRecruitMppPeriodService(RecruitMppPeriodService recruitMppPeriodService) {
        this.recruitMppPeriodService = recruitMppPeriodService;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public Map<String, Long> getMapDepartement() {
        return mapDepartement;
    }

    public void setMapDepartement(Map<String, Long> mapDepartement) {
        this.mapDepartement = mapDepartement;
    }

    public Map<String, Long> getMapJabatan() {
        return mapJabatan;
    }

    public void setMapJabatan(Map<String, Long> mapJabatan) {
        this.mapJabatan = mapJabatan;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

}
