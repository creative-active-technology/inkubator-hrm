/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.util.CommonReportUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reportStreamController")
@ApplicationScoped
public class ReportStreamController extends BaseController {

    private Map<String, Object> params;
    private String whereCondition;

    @PostConstruct
    @Override
    public void initialization() {
        System.out.println("init");
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        System.out.println(param);
    }

    public StreamedContent getFile() {
        System.out.println("execute getFile");
        FacesContext context = FacesUtil.getFacesContext();
        String bioId = context.getExternalContext().getRequestParameterMap().get("id");
        System.out.println(bioId + "aaa");
//        countryId = "1";
        List test = new ArrayList<>();
        Map<String, String> mapData = new HashMap<String, String>();
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        mapData.put("NIK", "hehe");
        maps.add(mapData);
        if (context.getRenderResponse() || bioId == null) {
            System.out.println("di lakukan pemanggilan");
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();

        } else {
            System.out.println("masuk else");
            InputStream is = null;
            //return CommonReportUtil.exportReportToPDFStream("biodata4.jasper", params, "City", maps);
            try {
//                String url = bioDataService.getEntiyByPK(Long.parseLong(bioId)).getFirstName();
                System.out.println(" hahahahha kjkjkjkjkjkjk");
//                if(url==null|| url.isEmpty()){
//                    System.out.println("url null");
//                }
//                is = facesIO.getInputStreamFromURL(url);
                return CommonReportUtil.exportReportToPDFStream("biodata4.jasper", params, "City", maps);
            } catch (JRException | SQLException ex) {
//                return new DefaultStreamedContent();
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }

//            return new DefaultStreamedContent(is);

        }
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(String whereCondition) {
        this.whereCondition = whereCondition;
    }
}
