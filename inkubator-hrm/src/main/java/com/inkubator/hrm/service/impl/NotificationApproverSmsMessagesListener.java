/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.common.notification.model.SMSSend;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.service.RmbsDisbursementService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.hrm.service.WtEmpCorrectionAttendanceService;

/**
 *
 * @author rizkykojek
 */
public class NotificationApproverSmsMessagesListener extends IServiceImpl implements MessageListener {

    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private JmsTemplate jmsTemplateSMS;
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

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
    	String approverNumber = StringUtils.EMPTY;
    	String notifMessage = StringUtils.EMPTY;
        try {
            TextMessage textMessage = (TextMessage) message;
            String json = textMessage.getText();
            approverNumber = jsonConverter.getValueByKey(json, "senderNumber");
            HrmUser approver = hrmUserDao.getEntityByPhoneNumber("+"+approverNumber);
            String content = jsonConverter.getValueByKey(json, "smsContent");
            String[] arrContent = StringUtils.split(content, "#");
            notifMessage = StringUtils.EMPTY;
            ApprovalActivity approvalActivity = StringUtils.isNumeric(arrContent[0]) ? 
            		approvalActivityDao.getEntiyByPK(Long.parseLong(arrContent[0])) : null;
            
            /** validation */
            if(approver == null){
            	notifMessage = "Maaf, No Telepon tidak terdaftar ";
            } else if(arrContent.length != 3){
            	notifMessage = "Maaf, format tidak sesuai dengan standard : ApprovalActivityID#YES/NO/REVISI#COMMENT ";
            } else if(!(StringUtils.equalsIgnoreCase(arrContent[1], "YES") || StringUtils.equalsIgnoreCase(arrContent[1], "NO") || StringUtils.equalsIgnoreCase(arrContent[1], "REVISI"))){
            	notifMessage = "Maaf, format tidak sesuai dengan standard : ApprovalActivityID#YES/NO/REVISI#COMMENT ";
            } else if(!StringUtils.isNumeric(arrContent[0])){
            	notifMessage = "Maaf, Approval Activity ID tidak terdaftar";
            } else if(approvalActivity == null){
                notifMessage = "Maaf, approval activity ID tidak terdaftar";
            } else if(!StringUtils.equals(approvalActivity.getApprovedBy(), approver.getUserId())){
                notifMessage = "Maaf, No Telpon ini tidak berhak untuk melakukan approval";
            } else if(approvalActivity.getApprovalStatus() != HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL){            	
                notifMessage = "Maaf, permintaan tidak dapat di proses karena status Approval sudah berubah";
            }            
            
            /** proses approval, jika memenuhi validasi */
            if(StringUtils.isEmpty(notifMessage)){
            	if(StringUtils.equalsIgnoreCase(arrContent[1], "YES")){
            		/** do Approved */
					switch (approvalActivity.getApprovalDefinition().getName()) {
						case HRMConstant.BUSINESS_TRAVEL:
							businessTravelService.approved(approvalActivity.getId(), null, arrContent[2]);
							break;
						case HRMConstant.LOAN:
							loanNewApplicationService.approved(approvalActivity.getId(), null, arrContent[2]);
							break;
						case HRMConstant.REIMBURSEMENT:
							rmbsApplicationService.approved(approvalActivity.getId(), null, arrContent[2]);
							break;
						case HRMConstant.REIMBURSEMENT_DISBURSEMENT:
							rmbsDisbursementService.approved(approvalActivity.getId(), null, arrContent[2]);
							break;
						case HRMConstant.SHIFT_SCHEDULE:
							tempJadwalKaryawanService.approved(approvalActivity.getId(), null, arrContent[2]);
							break;
						case HRMConstant.LEAVE:
							leaveImplementationService.approved(approvalActivity.getId(), null, arrContent[2]);
							break;
						case HRMConstant.LEAVE_CANCELLATION:
							leaveImplementationService.approved(approvalActivity.getId(), null, arrContent[2]);
							break;
						case HRMConstant.ANNOUNCEMENT:
							announcementService.approved(approvalActivity.getId(), null, arrContent[2]);
							break;
						case HRMConstant.EMP_CORRECTION_ATTENDANCE:
							wtEmpCorrectionAttendanceService.approved(approvalActivity.getId(), null, arrContent[2]);
							break;
						default:
							break;
					}					
					notifMessage = "Terima kasih, permintaan untuk disetujui telah di proses";
					
            	} else if(StringUtils.equalsIgnoreCase(arrContent[1], "NO")){
            		/** do Rejected */
					switch (approvalActivity.getApprovalDefinition().getName()) {
						case HRMConstant.BUSINESS_TRAVEL:
							businessTravelService.rejected(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.LOAN:
							loanNewApplicationService.rejected(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.REIMBURSEMENT:
							rmbsApplicationService.rejected(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.REIMBURSEMENT_DISBURSEMENT:
							rmbsDisbursementService.rejected(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.SHIFT_SCHEDULE:
							tempJadwalKaryawanService.rejected(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.LEAVE:
							leaveImplementationService.rejected(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.LEAVE_CANCELLATION:
							leaveImplementationService.rejected(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.ANNOUNCEMENT:
							announcementService.rejected(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.EMP_CORRECTION_ATTENDANCE:
							wtEmpCorrectionAttendanceService.rejected(approvalActivity.getId(), arrContent[2]);
							break;
						default:
							break;
					}					
					notifMessage = "Terima kasih, permintaan untuk ditolak telah di proses";
					
            	} else if(StringUtils.equalsIgnoreCase(arrContent[1], "REVISI")){
            		/** do Asking Revised */
					switch (approvalActivity.getApprovalDefinition().getName()) {
						case HRMConstant.LOAN:
							loanNewApplicationService.askingRevised(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.REIMBURSEMENT:
							rmbsApplicationService.askingRevised(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.REIMBURSEMENT_DISBURSEMENT:
							rmbsDisbursementService.askingRevised(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.ANNOUNCEMENT:
							announcementService.askingRevised(approvalActivity.getId(), arrContent[2]);
							break;
						case HRMConstant.EMP_CORRECTION_ATTENDANCE:
							wtEmpCorrectionAttendanceService.askingRevised(approvalActivity.getId(), arrContent[2]);
							break;
						default:
							/** Tidak semua module implement asking revised, jika belum/tidak implement maka kirim sms balik ke sender untuk notifikasi */
							notifMessage = "Maaf, permintaan untuk \"REVISI\" tidak dapat diproses. Hanya bisa melakukan proses \"YES\" atau \"NO\"";
							break;
					}
					if(StringUtils.isEmpty(notifMessage)){
						notifMessage = "Terima kasih, permintaan untuk direvisi telah di proses";
					}					
            	}
            }
            
        } catch (Exception ex) {
        	notifMessage = "Maaf, permintaan tidak dapat di proses, ada kegagalan di sistem. Mohon ulangi proses via aplikasi web atau hubungi Administrator";
            LOGGER.error("Error", ex);
        }
        
        /** kirim sms balik ke sender untuk notifikasi messagenya */
    	final SMSSend mSSend = new SMSSend();
    	LOGGER.error("Info SMS " + notifMessage);
		mSSend.setFrom(HRMConstant.SYSTEM_ADMIN);
		mSSend.setDestination("+"+approverNumber);
		mSSend.setContent(notifMessage);
		//Send notificatin SMS
		this.jmsTemplateSMS.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(jsonConverter.getJson(mSSend));
			}
		});
    }

}
