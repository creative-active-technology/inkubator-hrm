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
//            
//            Element ceo = new Element("CEO", "25em", "6em");
//            ceo.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
//            ceo.setDraggable(true);
//            
//            model.addElement(ceo);
//            
//            //CFO
//            Element cfo = new Element("CFO", "10em", "18em");
//            cfo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//            cfo.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
//            
//            Element fin = new Element("FIN", "5em", "30em");
//            fin.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//            
//            Element pur = new Element("PUR", "20em", "30em");
//            pur.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//            
//            model.addElement(cfo);
//            model.addElement(fin);
//            model.addElement(pur);
//            
//            //CTO
//            Element cto = new Element("CTO", "40em", "18em");
//            cto.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//            cto.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
//            
//            Element dev = new Element("DEV", "35em", "30em");
//            dev.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//            
//            Element tst = new Element("TST", "50em", "30em");
//            tst.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//            tst.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
//            
//            Element dd = new Element("HHH", "60em", "30em");
//            dd.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
//            model.addElement(cto);
//            model.addElement(dev);
//            model.addElement(tst);
//            model.addElement(dd);
//            
//            StraightConnector connector = new StraightConnector();
//            connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
//            connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
//            
//            //connections
//            model.connect(new Connection(ceo.getEndPoints().get(0), cfo.getEndPoints().get(0), connector));
//            model.connect(new Connection(ceo.getEndPoints().get(0), cto.getEndPoints().get(0), connector));
//            model.connect(new Connection(cfo.getEndPoints().get(1), fin.getEndPoints().get(0), connector));
//            model.connect(new Connection(cfo.getEndPoints().get(1), pur.getEndPoints().get(0), connector));
//            model.connect(new Connection(cto.getEndPoints().get(1), dev.getEndPoints().get(0), connector));
//            model.connect(new Connection(cto.getEndPoints().get(1), tst.getEndPoints().get(0), connector));
//            model.connect(new Connection(tst.getEndPoints().get(1), dd.getEndPoints().get(0), connector));
//        } catch (Exception ex) {
//            Logger.getLogger(DepartmentDiagramController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    private EndPoint createEndPoint(EndPointAnchor anchor) {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setStyle("{fillStyle:'#404a4e'}");
        endPoint.setHoverStyle("{fillStyle:'#20282b'}");

        return endPoint;
    }

    public DiagramModel getModel() {
        return model;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

}
