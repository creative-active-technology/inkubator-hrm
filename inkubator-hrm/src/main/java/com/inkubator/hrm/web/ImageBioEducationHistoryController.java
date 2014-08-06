/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.service.EducationHistoryService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import java.io.InputStream;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "imageBioEducationHistoryController")
@ApplicationScoped
public class ImageBioEducationHistoryController extends BaseController{
    private StreamedContent file;
    @ManagedProperty(value = "#{educationHistoryService}")
    private EducationHistoryService educationHistoryService;
    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;
    
    public ImageBioEducationHistoryController() {  
        FacesContext context = FacesUtil.getFacesContext();
        String bioId = context.getExternalContext().getRequestParameterMap().get("id");
        
        
        InputStream is = null;
            try {
                String url = educationHistoryService.getEntiyByPK(Long.parseLong(bioId)).getPathFoto();
                System.out.println(" hahahahha");
                if(url==null|| url.isEmpty()){
                    url=facesIO.getPathUpload()+"no_image.png";
                }
                is = facesIO.getInputStreamFromURL(url);
                InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(url);
                file = new DefaultStreamedContent(stream, "image/jpg", url);
            } catch (Exception ex) {
//                return new DefaultStreamedContent();
                LOGGER.error(ex, ex);
//                return new DefaultStreamedContent();
            }
            
    }
 
    public StreamedContent getFile() {
        return file;
    }

    public EducationHistoryService getEducationHistoryService() {
        return educationHistoryService;
    }

    public void setEducationHistoryService(EducationHistoryService educationHistoryService) {
        this.educationHistoryService = educationHistoryService;
    }

    public FacesIO getFacesIO() {
        return facesIO;
    }

    public void setFacesIO(FacesIO facesIO) {
        this.facesIO = facesIO;
    }
    
}
