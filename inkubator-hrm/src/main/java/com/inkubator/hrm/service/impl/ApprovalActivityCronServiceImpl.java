package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.service.ApprovalActivityCronService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.service.EmpCareerHistoryService;
import com.inkubator.hrm.service.ImplementationOfOverTimeService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.hrm.service.RecruitHireApplyService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.service.RecruitVacancyAdvertisementService;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.service.RmbsDisbursementService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.hrm.service.WtEmpCorrectionAttendanceService;

/**
 *
 * @author rizkykojek
 */
public class ApprovalActivityCronServiceImpl implements ApprovalActivityCronService {

	protected transient Logger LOGGER = Logger.getLogger(getClass());
	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private LoanService loanService;
	@Autowired
	private LoanNewApplicationService loanNewApplicationService;
	@Autowired
	private BusinessTravelService businessTravelService;
	@Autowired
	private RmbsApplicationService rmbsApplicationService;
	@Autowired
	private RmbsDisbursementService rmbsDisbursementService;
	@Autowired
	private TempJadwalKaryawanService tempJadwalKaryawanService;
	@Autowired
	private LeaveImplementationService leaveImplementationService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private WtEmpCorrectionAttendanceService wtEmpCorrectionAttendanceService;
	@Autowired
	private RecruitVacancyAdvertisementService recruitVacancyAdvertisementService;
	@Autowired
    private ImplementationOfOverTimeService implementationOfOverTimeService;
    @Autowired
    private PermitImplementationService permitImplementationService;
    @Autowired
    private RecruitHireApplyService recruitHireApplyService;
    @Autowired
    private RecruitMppApplyService recruitMppApplyService;
    @Autowired
    private EmpCareerHistoryService empCareerHistoryService;
	
	
	@Override
    @Scheduled(cron = "${cron.check.automatic.approval}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void checkAutomaticApproval() throws Exception {
		List<ApprovalActivity> autoApprovals = new ArrayList<ApprovalActivity>();
		Date now = new Date();
		
		//filter based on createdTime +delayTime < nowDate
		List<ApprovalActivity> waitingApprovals = approvalActivityDao.getAllDataWaitingStatusApproval();		
		for(ApprovalActivity approvalActivity : waitingApprovals){
			Date delayDate = DateUtils.addDays(approvalActivity.getCreatedTime(), approvalActivity.getApprovalDefinition().getDelayTime()); 
			if(now.after(delayDate)){
				autoApprovals.add(approvalActivity);
			}
		}
		
		//do autoApproval process
		for(ApprovalActivity approvalActivity : autoApprovals){
			try {
				if(approvalActivity.getApprovalDefinition().getAutoApproveOnDelay()) {
					//do Approved
					switch (approvalActivity.getApprovalDefinition().getName()) {
						case HRMConstant.BUSINESS_TRAVEL:
	                        businessTravelService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.LOAN:
	                        //loanService.approved(approvalActivity.getId(), null, null);
	                        loanNewApplicationService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.REIMBURSEMENT:
	                        //reimbursmentService.approved(approvalActivity.getId(), null, null);
	                        rmbsApplicationService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.REIMBURSEMENT_DISBURSEMENT:
	                        rmbsDisbursementService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.SHIFT_SCHEDULE:
	                        tempJadwalKaryawanService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.LEAVE:
	                        leaveImplementationService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.LEAVE_CANCELLATION:
	                        leaveImplementationService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.ANNOUNCEMENT:
	                        announcementService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.EMP_CORRECTION_ATTENDANCE:
	                        wtEmpCorrectionAttendanceService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.VACANCY_ADVERTISEMENT:
	                        recruitVacancyAdvertisementService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.OVERTIME:
	                    	implementationOfOverTimeService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.PERMIT:
	                    	permitImplementationService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.RECRUITMENT_REQUEST:
	                    	recruitHireApplyService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.RECRUIT_MPP_APPLY:
	                    	recruitMppApplyService.approved(approvalActivity.getId(), null, null);
	                        break;
	                    case HRMConstant.EMPLOYEE_CAREER_TRANSITION:
	                    	empCareerHistoryService.approved(approvalActivity.getId(), null, null);
	                        break;
						default:
							break;
					}
				} else if(approvalActivity.getApprovalDefinition().getEscalateOnDelay()){
					//do Diverted
					switch (approvalActivity.getApprovalDefinition().getName()) {
						case HRMConstant.BUSINESS_TRAVEL:
	                        businessTravelService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.LOAN:
	                        //loanService.diverted(approvalActivity.getId());
	                        loanNewApplicationService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.REIMBURSEMENT:
	                        //reimbursmentService.diverted(approvalActivity.getId());
	                        rmbsApplicationService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.REIMBURSEMENT_DISBURSEMENT:
	                        rmbsDisbursementService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.SHIFT_SCHEDULE:
	                        tempJadwalKaryawanService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.LEAVE:
	                        leaveImplementationService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.LEAVE_CANCELLATION:
	                        leaveImplementationService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.ANNOUNCEMENT:
	                        announcementService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.EMP_CORRECTION_ATTENDANCE:
	                        wtEmpCorrectionAttendanceService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.VACANCY_ADVERTISEMENT:
	                        recruitVacancyAdvertisementService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.OVERTIME:
	                    	implementationOfOverTimeService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.PERMIT:
	                    	permitImplementationService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.RECRUITMENT_REQUEST:
	                    	recruitHireApplyService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.RECRUIT_MPP_APPLY:
	                    	recruitMppApplyService.diverted(approvalActivity.getId());
	                        break;
	                    case HRMConstant.EMPLOYEE_CAREER_TRANSITION:
	                    	empCareerHistoryService.diverted(approvalActivity.getId());
	                        break;
						default:
							break;
					}				
				}	
			} catch (Exception ex) {
	            LOGGER.error("Error on approvalActivity with ID : " + approvalActivity.getId(), ex);
	        }
		}		
	}
}
