/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "departmentDiagramController")
@RequestScoped
public class DepartmentDiagramController extends BaseController {

    private DefaultDiagramModel model;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;

    @PostConstruct
    @Override
    public void initialization() {
     
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            model = departmentService.createDiagramModel( Long.parseLong(param.substring(1)));
            model.setMaxConnections(-1);

        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

  
    public DiagramModel getModel() {
        return model;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

     public String doBack() {
        return "/protected/organisation/organiztion_level.htm?faces-redirect=true";
    }
}
