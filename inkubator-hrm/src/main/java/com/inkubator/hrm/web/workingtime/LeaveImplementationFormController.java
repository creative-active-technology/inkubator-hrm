package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;

import ch.lambdaj.Lambda;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LeaveDistributionService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.LeaveImplementationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "leaveImplementationFormController")
@ViewScoped
public class LeaveImplementationFormController extends BaseController {

    private LeaveImplementationModel model;
    private Boolean isUpdate;
    private List<Leave> leaves;
    @ManagedProperty(value = "#{leaveImplementationService}")
    private LeaveImplementationService leaveImplementationService;
    @ManagedProperty(value = "#{leaveDistributionService}")
    private LeaveDistributionService leaveDistributionService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    TransactionCodeficationService transactionCodeficationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;            
            model = new LeaveImplementationModel();

            String param = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(param)) {
                LeaveImplementation leaveImplementation = leaveImplementationService.getEntityByPkWithDetail(Long.parseLong(param.substring(1)));
                if (leaveImplementation != null) {
                    getModelFromEntity(leaveImplementation);
                    List<LeaveDistribution> leaveDistributions = leaveDistributionService.getAllDataByEmpIdFetchLeave(leaveImplementation.getEmpData().getId());
                    leaves = Lambda.extract(leaveDistributions, Lambda.on(LeaveDistribution.class).getLeave());
                    isUpdate = Boolean.TRUE;
                }
            }else{
            	TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.LEAVE_CODE);
            	if(!ObjectUtils.equals(transactionCodefication, null)){
            		model.setNumberFilling(KodefikasiUtil.getKodefikasiOnlyPattern(transactionCodefication.getCode()));
            	}
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        model = null;
        isUpdate = null;
        leaves = null;
        leaveImplementationService = null;
        leaveDistributionService = null;
        empDataService = null;
        transactionCodeficationService = null;
    }

	public LeaveImplementationModel getModel() {
		return model;
	}

	public void setModel(LeaveImplementationModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

	public TransactionCodeficationService getTransactionCodeficationService() {
		return transactionCodeficationService;
	}

	public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
		this.transactionCodeficationService = transactionCodeficationService;
	}

	public void setLeaveImplementationService(
			LeaveImplementationService leaveImplementationService) {
		this.leaveImplementationService = leaveImplementationService;
	}

	public void setLeaveDistributionService(LeaveDistributionService leaveDistributionService) {
		this.leaveDistributionService = leaveDistributionService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public void doReset() {
        if (isUpdate) {
            try {
                LeaveImplementation leaveImplementation = leaveImplementationService.getEntiyByPK(model.getId());
                if (leaveImplementation != null) {
                    getModelFromEntity(leaveImplementation);
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
        	String numberFilling = model.getNumberFilling();
            model = new LeaveImplementationModel();
            model.setNumberFilling(numberFilling);
        }
    }

    public String doSave() {
        LeaveImplementation leaveImplementation = getEntityFromViewModel(model);
        
        try {
        	String path = "";
        	
            if (isUpdate) {
                leaveImplementationService.update(leaveImplementation);
                path = "/protected/working_time/leave_impl_detail.htm?faces-redirect=true&execution=e" + leaveImplementation.getId();
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
            	String message = leaveImplementationService.save(leaveImplementation, false);
            	if(StringUtils.equals(message, "success_need_approval")){
            		path = "/protected/working_time/leave_impl_view.htm?faces-redirect=true";
            		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",
            				FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            	} else {
            		path = "/protected/working_time/leave_impl_detail.htm?faces-redirect=true&execution=e" + leaveImplementation.getId();
            		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
            				FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            	}
            }
            
            return path;
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private LeaveImplementation getEntityFromViewModel(LeaveImplementationModel model) {
        LeaveImplementation leaveImplementation = new LeaveImplementation();
        if (model.getId() != null) {
        	leaveImplementation.setId(model.getId());
        }
        leaveImplementation.setNumberFilling(model.getNumberFilling());
        leaveImplementation.setLeave(new Leave(model.getLeaveId()));
        leaveImplementation.setEmpData(new EmpData(model.getEmpData().getId()));
        leaveImplementation.setStartDate(model.getStartDate());
        leaveImplementation.setEndDate(model.getEndDate());
        leaveImplementation.setFillingDate(model.getFillingDate());
        leaveImplementation.setAddress(model.getAddress());
        leaveImplementation.setMobilePhone(model.getMobilePhone());
        leaveImplementation.setMaterialJobsAbandoned(model.getMaterialJobsAbandoned());
        if(model.getTemporaryActing() != null){
        	leaveImplementation.setTemporaryActing(new EmpData(model.getTemporaryActing().getId()));
        }
        leaveImplementation.setDescription(model.getDescription());
        return leaveImplementation;
    }

    private void getModelFromEntity(LeaveImplementation leaveImplementation) throws Exception {
        model.setId(leaveImplementation.getId());
        model.setNumberFilling(leaveImplementation.getNumberFilling());
        model.setEmpData(leaveImplementation.getEmpData());
        model.setLeaveId(leaveImplementation.getLeave().getId());
        LeaveImplementation latestByFillingDate = leaveImplementationService.getLatestEntityByEmpDataId(leaveImplementation.getEmpData().getId());
        model.setLatestLeaveDate(latestByFillingDate.getEndDate());
        LeaveDistribution leaveDistribution = leaveDistributionService.getEntityByLeaveIdAndEmpDataId(leaveImplementation.getLeave().getId(), leaveImplementation.getEmpData().getId());
        model.setRemainingLeave(leaveDistribution.getBalance());
        model.setStartDate(leaveImplementation.getStartDate());
        model.setEndDate(leaveImplementation.getEndDate());
        model.setFillingDate(leaveImplementation.getFillingDate());
        model.setAddress(leaveImplementation.getAddress());
        model.setMobilePhone(leaveImplementation.getMobilePhone());
        model.setMaterialJobsAbandoned(leaveImplementation.getMaterialJobsAbandoned());
        model.setTemporaryActing(leaveImplementation.getTemporaryActing());
        model.setDescription(leaveImplementation.getDescription());
        double actualLeave = leaveImplementationService.getTotalActualLeave(leaveImplementation.getEmpData().getId(), leaveImplementation.getLeave().getId(), leaveImplementation.getStartDate(), leaveImplementation.getEndDate());
        model.setActualLeaveTaken(actualLeave);
    }

    public String doBack() {
        return "/protected/working_time/leave_impl_view.htm?faces-redirect=true";
    }

    public List<EmpData> doAutoCompleteEmployee(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
    
    public void onChangeEmployee(){
    	try {
	    	List<LeaveDistribution> leaveDistributions = leaveDistributionService.getAllDataByEmpIdFetchLeave(model.getEmpData().getId());
	    	//filter list hanya untuk leave yang isActive = true
	    	leaveDistributions = Lambda.select(leaveDistributions, Lambda.having(Lambda.on(LeaveDistribution.class).getLeave().getIsActive(), Matchers.equalTo(true)));
	        leaves = Lambda.extract(leaveDistributions, Lambda.on(LeaveDistribution.class).getLeave());
	        model.setLeaveId(null);
	        
	        //cek juga actual leave taken-nya
	        this.onChangeStartOrEndDate();
	    } catch (Exception e) {
	    	LOGGER.error("Error", e);
		}
    }
    
    public void onChangeLeave(){
    	try {
    		LeaveDistribution leaveDistribution = leaveDistributionService.getEntityByLeaveIdAndEmpDataId(model.getLeaveId(), model.getEmpData().getId());
    		LeaveImplementation latestByFillingDate = leaveImplementationService.getLatestEntityByEmpDataId(model.getEmpData().getId());
    		model.setRemainingLeave(leaveDistribution.getBalance());
    		Date latestLeaveDate = latestByFillingDate != null ? latestByFillingDate.getEndDate() : null;
    		model.setLatestLeaveDate(latestLeaveDate);
    		
    		//cek juga actual leave taken-nya
	        this.onChangeStartOrEndDate();
    	} catch (Exception e) {
	    	LOGGER.error("Error", e);
		}
    }
    
    public void onChangeStartOrEndDate(){
    	try {
	    	if(model.getStartDate() != null && model.getEndDate() != null && model.getEmpData()!= null && model.getLeaveId() != null){
	    		double actualLeave = leaveImplementationService.getTotalActualLeave(model.getEmpData().getId(), model.getLeaveId(), model.getStartDate(), model.getEndDate());
	    		model.setActualLeaveTaken(actualLeave);
	    	}
    	} catch (Exception e) {
	    	LOGGER.error("Error", e);
		}
    }
}
