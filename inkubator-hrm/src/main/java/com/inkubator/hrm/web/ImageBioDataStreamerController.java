/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.service.BioDataService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
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

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
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
                is = facesIO.getInputStreamFromURL(url);

            } catch (Exception ex) {
//                return new DefaultStreamedContent();
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
            return new DefaultStreamedContent(is);

        }

    }
}
