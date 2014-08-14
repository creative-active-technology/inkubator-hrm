 
/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.util.CommonReportUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reportStreamController")
@ApplicationScoped
public class ReportStreamController extends BaseController {
   
    private Map<String, Object> params;
    private Long bioId;
    private BioData bioData;
    private EmpData empData;
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    
    
    @PostConstruct
    @Override
    public void initialization() {
        System.out.println("init");
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        bioId = Long.parseLong(param.substring(1));
    }
    
    @PreDestroy
    private void cleanAndExit() {
        params = null;
        bioId = null;
        bioData = null;
        empData = null;
        bioDataService = null;
        empDataService = null;
    }
    
    public StreamedContent getFile() throws JRException, Exception {
        empData = empDataService.getByBioDataIdWithDepartment(bioId);
        params = new HashMap<>();
        Map<String,String> mapData = new HashMap<String, String>();
        List<Map<String, String>> maps= new ArrayList<Map<String, String>>();
        // isi data
        mapData.put("NIK", empData.getNik());
        mapData.put("name", empData.getBioData().getFirstName()+ " "+ empData.getBioData().getLastName());
        mapData.put("jabatan", empData.getJabatanByJabatanId().getName());
        mapData.put("department", empData.getJabatanByJabatanId().getDepartment().getDepartmentName());
        mapData.put("tmb", String.valueOf(empData.getJoinDate()));
        mapData.put("photo", empData.getBioData().getPathFoto());
        mapData.put("barcode", empData.getNik());
        maps.add(mapData);
        if (bioId == null) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();

        } else {
            try {
                return CommonReportUtil.exportReportToPDFStream("biodata4.jasper", params, "Biodata", maps);
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
            

        }
        }
    

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Long getBioId() {
        return bioId;
    }

    public BioData getBioData() {
        return bioData;
    }

    public void setBioData(BioData bioData) {
        this.bioData = bioData;
    }

    public BioDataService getBioDataService() {
        return bioDataService;
    }

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    
}