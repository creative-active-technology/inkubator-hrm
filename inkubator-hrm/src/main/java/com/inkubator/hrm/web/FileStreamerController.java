package com.inkubator.hrm.web;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.fusesource.hawtbuf.ByteArrayInputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.RecruitAgreementNotice;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.RecruitAgreementNoticeService;
import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.service.SystemLetterReferenceService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "fileStreamerController")
@ApplicationScoped
public class FileStreamerController extends BaseController {

    @ManagedProperty(value = "#{rmbsApplicationService}")
    private RmbsApplicationService rmbsApplicationService;
    @ManagedProperty(value = "#{announcementService}")
    private AnnouncementService announcementService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{systemLetterReferenceService}")
    private SystemLetterReferenceService systemLetterReferenceService;
    @ManagedProperty(value = "#{recruitAgreementNoticeService}")
    private RecruitAgreementNoticeService recruitAgreementNoticeService;
    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;

    public StreamedContent getFileFromPath() {
        FacesContext context = FacesUtil.getFacesContext();
        String path = context.getExternalContext().getRequestParameterMap().get("path");
        String fileName = context.getExternalContext().getRequestParameterMap().get("fileName");
        StreamedContent streamedContent = new DefaultStreamedContent();

        if (StringUtils.isNotEmpty(path)) {
            try {
            	if(StringUtils.isEmpty(fileName)){
            		fileName = StringUtils.substringAfterLast(path, "/");            		
            	}
                String extension = StringUtils.substringAfterLast(path, ".");
                fileName = fileName+"."+extension;
            	InputStream is = facesIO.getInputStreamFromURL(path);
                streamedContent = new DefaultStreamedContent(is, null, fileName);                
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }

        return streamedContent;
    }
    
    public StreamedContent getRmbsApplicationReceiptBlob() {
        FacesContext context = FacesUtil.getFacesContext();
        String rmbsApplicationId = context.getExternalContext().getRequestParameterMap().get("rmbsApplicationId");
        StreamedContent streamedContent = new DefaultStreamedContent();

        if ((!context.getRenderResponse()) && (rmbsApplicationId != null)) {
            try {
                RmbsApplication entity = rmbsApplicationService.getEntiyByPK(Long.parseLong(rmbsApplicationId));
                if (entity.getReceiptAttachment() != null && entity.getReceiptAttachment().length > 0) {
                    InputStream is = new ByteArrayInputStream(entity.getReceiptAttachment());
                    streamedContent = new DefaultStreamedContent(is, null, entity.getReceiptAttachmentName());
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }

        return streamedContent;
    }
    
    public StreamedContent getFileCv() throws NumberFormatException, Exception{
    	
    	FacesContext context = FacesUtil.getFacesContext();
        String bioDataId = context.getExternalContext().getRequestParameterMap().get("bioDataId");
 
        StreamedContent streamedContent = new DefaultStreamedContent();
        RecruitAgreementNotice entity = recruitAgreementNoticeService.getEntityByBioDataId(Long.parseLong(bioDataId));
        
    
        String fileName = "";
        if (entity != null) {
            try {
        		//get file name from path uploadedCv without extension file, ex: test/testFolder/file.docx
            	fileName = StringUtils.substringAfterLast(entity.getUploadedCv(), "/");
            	//get extension file (docx) from path uploadedCv without extension file, ex: test/testFolder/file.docx
            	String extension = StringUtils.substringAfterLast(entity.getUploadedCv(), ".");
                //combine from file to make name file.docx from variable fileName and extension
            	fileName = fileName+"."+extension;
                InputStream is = facesIO.getInputStreamFromURL(entity.getUploadedCv());
                streamedContent = new DefaultStreamedContent(is, null, fileName);                
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }

        return streamedContent;
    }
    
    public StreamedContent getSystemLetterReferenceFileUploadBlob() {
        FacesContext context = FacesUtil.getFacesContext();
        String id = context.getExternalContext().getRequestParameterMap().get("id");
        StreamedContent streamedContent = new DefaultStreamedContent();

        if ((!context.getRenderResponse()) && (id != null)) {
            try {
                SystemLetterReference entity = systemLetterReferenceService.getEntiyByPK(Long.parseLong(id));
                if (entity.getUploadData() != null && entity.getUploadData().length > 0) {
                    InputStream is = new ByteArrayInputStream(entity.getUploadData());
                    streamedContent = new DefaultStreamedContent(is, null, entity.getFileUploadName());
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }

        return streamedContent;
    }

    public StreamedContent getRmbsApplicationReceiptPath() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String approvalActivityId = context.getExternalContext().getRequestParameterMap().get("approvalActivityId");
        StreamedContent streamedContent = new DefaultStreamedContent();

        if (!context.getRenderResponse() && approvalActivityId != null) {
            try {
                ApprovalActivity appActivity = approvalActivityService.getEntiyByPK(Long.parseLong(approvalActivityId));

                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
                JsonObject jsonObject = gson.fromJson(appActivity.getPendingData(), JsonObject.class);

                JsonElement elReimbursementFileName = jsonObject.get("reimbursementFileName");
                String path = elReimbursementFileName.isJsonNull() ? facesIO.getPathUpload() + "no_image.png" : elReimbursementFileName.getAsString();

                InputStream is = facesIO.getInputStreamFromURL(path);
                streamedContent = new DefaultStreamedContent(is, null, StringUtils.substringAfterLast(path, "/"));

            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }

        return streamedContent;
    }

    public StreamedContent getAnnouncementAttachmentPath() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String approvalActivityId = context.getExternalContext().getRequestParameterMap().get("approvalActivityId");
        String id = context.getExternalContext().getRequestParameterMap().get("id");
        StreamedContent streamedContent = new DefaultStreamedContent();

        if (!context.getRenderResponse() && (StringUtils.isNotEmpty(approvalActivityId) || StringUtils.isNotEmpty(id))) {
            try {
                Announcement announcement = null;
                if (StringUtils.isNotEmpty(approvalActivityId)) {
                    ApprovalActivity appActivity = approvalActivityService.getEntiyByPK(Long.parseLong(approvalActivityId));
                    Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
                    announcement = gson.fromJson(appActivity.getPendingData(), Announcement.class);
                } else if (StringUtils.isNotEmpty(id)) {
                    announcement = announcementService.getEntiyByPK(Long.parseLong(id));
                }

                String path = StringUtils.isEmpty(announcement.getAttachmentPath()) ? facesIO.getPathUpload() + "no_image.png" : announcement.getAttachmentPath();

                InputStream is = facesIO.getInputStreamFromURL(path);
                streamedContent = new DefaultStreamedContent(is, null, StringUtils.substringAfterLast(path, "/"));

            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }

        return streamedContent;
    }

    public RmbsApplicationService getRmbsApplicationService() {
        return rmbsApplicationService;
    }

    public void setRmbsApplicationService(RmbsApplicationService rmbsApplicationService) {
        this.rmbsApplicationService = rmbsApplicationService;
    }

    public ApprovalActivityService getApprovalActivityService() {
        return approvalActivityService;
    }

    public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
        this.approvalActivityService = approvalActivityService;
    }

    public FacesIO getFacesIO() {
        return facesIO;
    }

    public void setFacesIO(FacesIO facesIO) {
        this.facesIO = facesIO;
    }

    public AnnouncementService getAnnouncementService() {
        return announcementService;
    }

    public void setAnnouncementService(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    public SystemLetterReferenceService getSystemLetterReferenceService() {
        return systemLetterReferenceService;
    }

    public void setSystemLetterReferenceService(SystemLetterReferenceService systemLetterReferenceService) {
        this.systemLetterReferenceService = systemLetterReferenceService;
    }

	public RecruitAgreementNoticeService getRecruitAgreementNoticeService() {
		return recruitAgreementNoticeService;
	}

	public void setRecruitAgreementNoticeService(
			RecruitAgreementNoticeService recruitAgreementNoticeService) {
		this.recruitAgreementNoticeService = recruitAgreementNoticeService;
	}

    
    
}
