package com.inkubator.hrm.web.career;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.entity.CareerEmpElimination;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.CareerEmpEliminationService;
import com.inkubator.hrm.service.CareerTerminationTypeService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.EmpEliminationModel;
import com.inkubator.hrm.web.model.LeaveModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "empEliminationFormController")
@ViewScoped
public class EmpEliminationFormController extends BaseController {

    private EmpEliminationModel model;
    @ManagedProperty(value = "#{careerEmpEliminationService}")
    private CareerEmpEliminationService careerEmpEliminationService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{careerTerminationTypeService}")
    private CareerTerminationTypeService careerTerminationTypeService;
    private List<CareerTerminationType> listTerminationType;
    private Boolean isEmployeeSelected;
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new EmpEliminationModel();
            isEmployeeSelected = Boolean.FALSE;
            String param = FacesUtil.getRequestParameter("execution");
            listTerminationType = careerTerminationTypeService.getAllData();
            
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        careerEmpEliminationService = null;
        careerTerminationTypeService = null;
        empDataService = null;
        model = null;
    }

    public List<EmpData> doAutoCompleteEmployee(String param) {
        List<EmpData> empDatas = new ArrayList<EmpData>();
        try {
            empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param), HrmUserInfoUtil.getCompanyId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return empDatas;
    }


	public void doReset() {
       model = new EmpEliminationModel();
       isEmployeeSelected = Boolean.FALSE;
    }

    public String doSave() {
    	CareerEmpElimination careerEmpElimination = getEntityFromViewModel(model);
        /*try {
            if (isUpdate) {
                leaveService.update(careerEmpElimination, appDefs);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                leaveService.save(careerEmpElimination, appDefs);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/working_time/leave_detail.htm?faces-redirect=true&execution=e" + careerEmpElimination.getId();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }*/
        return null;
    }

    private CareerEmpElimination getEntityFromViewModel(EmpEliminationModel model) {
    	CareerEmpElimination careerEmpElimination = new CareerEmpElimination();
        if (model.getId() != null) {
            careerEmpElimination.setId(model.getId());
        }
        careerEmpElimination.setCareerTerminationType(new CareerTerminationType(model.getTerminationTypeId()));
        careerEmpElimination.setEmpData(new EmpData(model.getEmpData().getId()));
        careerEmpElimination.setPendingLoan(model.getTotalOutstandingLoan());
        careerEmpElimination.setReason(model.getReason());
        careerEmpElimination.setSeparationPay(model.getSeparationPay());
        careerEmpElimination.setWtPeriode(new WtPeriode(model.getWtPeriodeId()));
        return careerEmpElimination;
    }

   
    public String doBack() {
        return "/protected/career/emp_elimination_view.htm?faces-redirect=true";
    }

    public void onChangeEmployee() {
        try {
        	
        	EmpData empData = empDataService.getByEmpIdWithDetail(model.getEmpData().getId());
        	model = empDataService.generateEmpEliminationModelByEmpDataId(empData.getId());
        	isEmployeeSelected = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    public void onChangeSeparationPay(){
    	Double remainSeparationPay = model.getSeparationPay() - model.getTotalOutstandingLoan();
    	model.setRemainSeparationPay(remainSeparationPay);
    }

	public EmpEliminationModel getModel() {
        return model;
    }

    public void setModel(EmpEliminationModel model) {
        this.model = model;
    }
    
	public void setCareerEmpEliminationService(CareerEmpEliminationService careerEmpEliminationService) {
		this.careerEmpEliminationService = careerEmpEliminationService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public void setCareerTerminationTypeService(CareerTerminationTypeService careerTerminationTypeService) {
		this.careerTerminationTypeService = careerTerminationTypeService;
	}

	public List<CareerTerminationType> getListTerminationType() {
		return listTerminationType;
	}

	public void setListTerminationType(List<CareerTerminationType> listTerminationType) {
		this.listTerminationType = listTerminationType;
	}

	public Boolean getIsEmployeeSelected() {
		return isEmployeeSelected;
	}

	public void setIsEmployeeSelected(Boolean isEmployeeSelected) {
		this.isEmployeeSelected = isEmployeeSelected;
	}
	
	
}
