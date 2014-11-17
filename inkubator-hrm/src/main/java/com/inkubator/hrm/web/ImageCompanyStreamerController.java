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

import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.service.CompanyPolicyService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "imageCompanyStreamerController")
@ApplicationScoped
public class ImageCompanyStreamerController extends BaseController {

	@ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;
	@ManagedProperty(value = "#{companyPolicyService}")
    private CompanyPolicyService companyPolicyService;
	@ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;
	
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void setCompanyPolicyService(CompanyPolicyService companyPolicyService) {
		this.companyPolicyService = companyPolicyService;
	}

	public void setFacesIO(FacesIO facesIO) {
		this.facesIO = facesIO;
	}

	public StreamedContent getCompanyLogo() {
		FacesContext context = FacesUtil.getFacesContext();
        String companyId = context.getExternalContext().getRequestParameterMap().get("companyId");
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if((!context.getRenderResponse()) && (companyId!=null)) {
        	try {
	        	Company company = companyService.getEntiyByPK(Long.parseLong(companyId)); 
	        	if(company.getCompanyLogo() != null && company.getCompanyLogo().length > 0){
		        	InputStream is = new ByteArrayInputStream(company.getCompanyLogo());
		        	streamedContent = new DefaultStreamedContent(is, null, company.getCompanyLogoName());
	        	}
        	} catch (Exception ex){
        		 LOGGER.error("Error", ex);
        	}        	        	
        }
        
        return streamedContent;
	}
	
	public StreamedContent getCompanyPolicyAttachment() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String id = context.getExternalContext().getRequestParameterMap().get("id");
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if (!context.getRenderResponse() && id != null) {
            try {
                CompanyPolicy companyPolicy = companyPolicyService.getEntiyByPK(Long.parseLong(id));
                String path = companyPolicy.getAttachFilePath();
                if (StringUtils.isEmpty(path)) {
                    path = facesIO.getPathUpload() + "no_image.png";
                }
                InputStream is = facesIO.getInputStreamFromURL(path);
                streamedContent = new DefaultStreamedContent(is, null, StringUtils.substringAfterLast(path, "/"));

            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
        
        return streamedContent;
    }
}
