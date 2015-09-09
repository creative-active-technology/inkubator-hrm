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
            ApprovalDefinition selected = approvalDefinitionService.getEntiyByPK(selectedApprovalDef);
            LOGGER.info("sdfsdfsdfds " + selected.getName());

            model = new DefaultDiagramModel();
            model.setMaxConnections(-1);
            int x = 6;
            int y = 10;
            FlowChartConnector connector = new FlowChartConnector();
            connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
            model.setDefaultConnector(connector);
            String header = selected.getName();
            if (selected.getSpecificName() != null) {
                header = header + "-" + selected.getSpecificName();
            }
            Element start = new Element(header, y + "em", x + "em");
            start.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
            start.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
            start.setStyleClass("ui-diagram-success");

//            Element revisi = new Element(header, y + "em", x-10 + "em");
//            start.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
//            start.setStyleClass("ui-diagram-success");
            Element step;
//            String header1;
            if (selected.getMinApprover() > 1) {
                step = new Element(selected.getApproverType(), "30em", "6em");
                step.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
                step.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                step.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//            trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                header = selected.getName();
                if (selected.getSpecificName() != null) {
                    header = header + "-" + selected.getSpecificName();
                }
            } else {
                step = new Element(selected.getApproverType(), "35em", "10em");
                step.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
                step.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                step.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                step.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
//            trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));

            }
            Element step1 = new Element("BY Posititon", "65em", "10em");
            step1.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
            System.out.println("sdjjsdhfsdjhfdsjfhj");
            step1.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
            step1.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
            step1.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));

            Element finish = new Element("Approved", "80em", "10em");
            finish.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
            finish.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
            finish.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
            finish.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));

            String headarReject;
            Element reject = new Element("Reject Notification", y + "em", x + 10 + "em");
            reject.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
            reject.setStyleClass("ui-diagram-fail");

            Element approve = new Element("Approve Notification", y + "em", x + 20 + "em");
            approve.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
            approve.setStyleClass("ui-diagram-fail");

//            start.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
            model.addElement(start);
            model.addElement(reject);
            model.addElement(step);
            model.addElement(step1);
            model.addElement(finish);
            model.addElement(approve);
            model.connect(createConnection(start.getEndPoints().get(0), step.getEndPoints().get(0), "Request"));
            model.connect(createConnection(step.getEndPoints().get(1), reject.getEndPoints().get(0), "No"));
            model.connect(createConnection(step1.getEndPoints().get(3), reject.getEndPoints().get(0), null));
            model.connect(createConnection(step.getEndPoints().get(2), start.getEndPoints().get(1), "Revisi"));
            model.connect(createConnection(step1.getEndPoints().get(2), start.getEndPoints().get(1), null));
            model.connect(createConnection(step.getEndPoints().get(3), step1.getEndPoints().get(0), "Yes"));
            model.connect(createConnection(step1.getEndPoints().get(1), finish.getEndPoints().get(0), "Yes"));
            model.connect(createConnection(finish.getEndPoints().get(1), approve.getEndPoints().get(0), "Notification"));
        } catch (Exception ex) {
            Logger.getLogger(ApprovalDefinitionGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Long getSelectedApprovalDef() {
        return selectedApprovalDef;
    }

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
            System.out.println("jgkgjgjgjgjgjg");
        }

        return conn;
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
