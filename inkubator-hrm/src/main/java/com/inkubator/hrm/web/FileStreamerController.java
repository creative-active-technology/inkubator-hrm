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
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.RmbsApplicationService;
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
	@ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
	@ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;
	
	public StreamedContent getRmbsApplicationReceiptBlob() {
		FacesContext context = FacesUtil.getFacesContext();
        String rmbsApplicationId = context.getExternalContext().getRequestParameterMap().get("rmbsApplicationId");
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if((!context.getRenderResponse()) && (rmbsApplicationId!=null)) {
        	try {
        		RmbsApplication entity = rmbsApplicationService.getEntiyByPK(Long.parseLong(rmbsApplicationId)); 
	        	if(entity.getReceiptAttachment() != null && entity.getReceiptAttachment().length > 0){
		        	InputStream is = new ByteArrayInputStream(entity.getReceiptAttachment());
		        	streamedContent = new DefaultStreamedContent(is, null, entity.getReceiptAttachmentName());
	        	}
        	} catch (Exception ex){
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
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if (!context.getRenderResponse() && approvalActivityId != null) {
            try {
                ApprovalActivity appActivity = approvalActivityService.getEntiyByPK(Long.parseLong(approvalActivityId));
                
                Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
    	    	Announcement announcement =  gson.fromJson(appActivity.getPendingData(), Announcement.class);
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
	
}
