package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.service.ApprovalActivityCronService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.service.ReimbursmentService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;

/**
 *
 * @author rizkykojek
 */
public class ApprovalActivityCronServiceImpl implements ApprovalActivityCronService {

	@Autowired
	private ApprovalActivityDao approvalActivityDao;
	@Autowired
	private LoanService loanService;
	@Autowired
	private BusinessTravelService businessTravelService;
	@Autowired
	private ReimbursmentService reimbursmentService;
	@Autowired
	private TempJadwalKaryawanService tempJadwalKaryawanService;
	@Autowired
	private LeaveImplementationService leaveImplementationService;
	
	
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
			if(approvalActivity.getApprovalDefinition().getAutoApproveOnDelay()) {
				//do Approved
				switch (approvalActivity.getApprovalDefinition().getName()) {
					case HRMConstant.BUSINESS_TRAVEL:
						businessTravelService.approved(approvalActivity.getId(), null, null);
						break;
					case HRMConstant.LOAN:
						loanService.approved(approvalActivity.getId(), null, null);
						break;
					case HRMConstant.REIMBURSEMENT:
						reimbursmentService.approved(approvalActivity.getId(), null, null);
						break;
					case HRMConstant.SHIFT_SCHEDULE:
						tempJadwalKaryawanService.approved(approvalActivity.getId(), null, null);
						break;
					case HRMConstant.LEAVE:
						leaveImplementationService.approved(approvalActivity.getId(), null, null);
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
						loanService.diverted(approvalActivity.getId());
						break;
					case HRMConstant.REIMBURSEMENT:
						reimbursmentService.diverted(approvalActivity.getId());
						break;
					case HRMConstant.SHIFT_SCHEDULE:
						tempJadwalKaryawanService.diverted(approvalActivity.getId());
						break;
					case HRMConstant.LEAVE:
						leaveImplementationService.diverted(approvalActivity.getId());
						break;
					default:
						break;
				}				
			}			
		}		
	}
}
