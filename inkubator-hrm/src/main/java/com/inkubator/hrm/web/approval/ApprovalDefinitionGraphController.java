/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.approval;

import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.service.ApprovalDefinitionService;
import com.inkubator.webcore.controller.BaseController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "approvalDefinitionGraphController")
@ViewScoped
public class ApprovalDefinitionGraphController extends BaseController {

    @ManagedProperty(value = "#{approvalDefinitionService}")
    private ApprovalDefinitionService approvalDefinitionService;
    private DefaultDiagramModel model;
    private Map<String, Long> approvalNames = new HashMap<String, Long>();
    private Long selectedApprovalDef;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            List<ApprovalDefinition> allData = approvalDefinitionService.getAllData(Boolean.TRUE);

            for (ApprovalDefinition allData1 : allData) {
                approvalNames.put(allData1.getName() + " - " + allData1.getSpecificName(), allData1.getId());
            }

        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public void setApprovalDefinitionService(ApprovalDefinitionService approvalDefinitionService) {
        this.approvalDefinitionService = approvalDefinitionService;
    }

    public Map<String, Long> getApprovalNames() {
        return approvalNames;
    }

    public void setApprovalNames(Map<String, Long> approvalNames) {
        this.approvalNames = approvalNames;
    }

    public void doSelectApprovalName() {
        try {
            model = approvalDefinitionService.getGraphMode(selectedApprovalDef);
        } catch (Exception ex) {
            Logger.getLogger(ApprovalDefinitionGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Long getSelectedApprovalDef() {
        return selectedApprovalDef;
    }

    public void setSelectedApprovalDef(Long selectedApprovalDef) {
        this.selectedApprovalDef = selectedApprovalDef;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

}
