/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.service.CountryService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@ManagedBean(name = "imageStreamerController")
@ApplicationScoped
public class ImageStreamerController extends BaseController {

    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;
    

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }


    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String countryId = context.getExternalContext().getRequestParameterMap().get("countryId");
        if (context.getRenderResponse() || countryId == null) {
            System.out.println("di lakukan pemanggilan");

            return new DefaultStreamedContent();
        } else {
            InputStream is = null;
            try {
                
                is = new ByteArrayInputStream(countryService.getEntiyByPK(Long.parseLong(countryId)).getFlagIcon());
                
            } catch (Exception ex) {
                return new DefaultStreamedContent();
//                LOGGER.error(ex, ex);
//                return new DefaultStreamedContent();
            }
            return new DefaultStreamedContent(is);
            

        }
    }
}
