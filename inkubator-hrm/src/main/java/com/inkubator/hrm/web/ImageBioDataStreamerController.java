/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.BioDocument;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.BioDocumentService;
import com.inkubator.hrm.service.BioEducationHistoryService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "imageBioDataStreamerController")
@ApplicationScoped
public class ImageBioDataStreamerController extends BaseController {

    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{bioEducationHistoryService}")
    private BioEducationHistoryService educationHistoryService;
    @ManagedProperty(value = "#{bioDocumentService}")
    private BioDocumentService bioDocumentService;
    private StreamedContent ijazahFile;
    
    

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    public void setEducationHistoryService(BioEducationHistoryService educationHistoryService) {
        this.educationHistoryService = educationHistoryService;
    }
    
    public void setBioDocumentService(BioDocumentService bioDocumentService) {
		this.bioDocumentService = bioDocumentService;
	}

	public void setFacesIO(FacesIO facesIO) {
        this.facesIO = facesIO;
    }

    public StreamedContent getFotoImage() throws IOException {

        FacesContext context = FacesUtil.getFacesContext();
        String bioId = context.getExternalContext().getRequestParameterMap().get("id");
        if (context.getRenderResponse() || bioId == null) {
            return new DefaultStreamedContent();
        } else {
            InputStream is = null;
            try {
                String url = bioDataService.getEntiyByPK(Long.parseLong(bioId)).getPathFoto();
              
                if(url==null|| url.isEmpty()){
                    url=facesIO.getPathUpload()+"no_image.png";
                }
                is = facesIO.getInputStreamFromURL(url);

            } catch (Exception ex) {
//                return new DefaultStreamedContent();
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
            return new DefaultStreamedContent(is);

        }

    }
    
    public StreamedContent getIjazahFile() throws IOException {

        FacesContext context = FacesUtil.getFacesContext();
        String bioId = context.getExternalContext().getRequestParameterMap().get("id");
        String url;
        String filename;
        if (context.getRenderResponse() || bioId == null) {
            return new DefaultStreamedContent();
        } else {
            InputStream is = null;
            try {
                url = educationHistoryService.getEntiyByPK(Long.parseLong(bioId)).getPathFoto();
                is = facesIO.getInputStreamFromURL(url);
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
            return new DefaultStreamedContent(is, null, StringUtils.substringAfterLast(url, "/"));

        }

    }
    
    public StreamedContent getFingerImage() throws IOException {

        FacesContext context = FacesUtil.getFacesContext();
        String bioId = context.getExternalContext().getRequestParameterMap().get("id");
        if (context.getRenderResponse() || bioId == null) {
            return new DefaultStreamedContent();
        } else {
            InputStream is = null;
            try {
                String url = bioDataService.getEntiyByPK(Long.parseLong(bioId)).getPathFinger();
                 if(url==null|| url.isEmpty()){
                    url=facesIO.getPathUpload()+"no_image.png";
                }
                is = facesIO.getInputStreamFromURL(url);

            } catch (Exception ex) {
//                return new DefaultStreamedContent();
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
            return new DefaultStreamedContent(is);

        }

    }
    
    public StreamedContent getSignaturImage() throws IOException {

        FacesContext context = FacesUtil.getFacesContext();
        String bioId = context.getExternalContext().getRequestParameterMap().get("id");
        if (context.getRenderResponse() || bioId == null) {
            return new DefaultStreamedContent();
        } else {
            InputStream is = null;
            try {
                String url = bioDataService.getEntiyByPK(Long.parseLong(bioId)).getPathSignature();
                  if(url==null|| url.isEmpty()){
                    url=facesIO.getPathUpload()+"no_image.png";
                }
                is = facesIO.getInputStreamFromURL(url);

            } catch (Exception ex) {
//                return new DefaultStreamedContent();
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
            return new DefaultStreamedContent(is);

        }

    }
    
    public StreamedContent getDocumentFile() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String id = context.getExternalContext().getRequestParameterMap().get("id");
        if (context.getRenderResponse() || id == null) {
            return new DefaultStreamedContent();
        } else {
            InputStream is = null;
            try {
            	BioDocument bioDocument = bioDocumentService.getEntiyByPK(Long.parseLong(id));
                String path = bioDocument.getUploadPath();
                  if(StringUtils.isEmpty(path)){
                    path=facesIO.getPathUpload()+"no_image.png";
                }
                is = facesIO.getInputStreamFromURL(path);
                
                return new DefaultStreamedContent(is, null, StringUtils.substringAfterLast(path, "/"));
                
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
        }
    }
}
