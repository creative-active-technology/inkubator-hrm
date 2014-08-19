 /* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.util.CommonReportUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reportStreamController")
@ApplicationScoped
public class ReportStreamController extends BaseController {
   
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        
    }
    
    @PreDestroy
    private void cleanAndExit() {
        bioDataService = null;
        empDataService = null;
    }
    
    public StreamedContent getFile() {
    	try {
	    	String param = FacesUtil.getRequestParameter("id");
	    	Long bioId = Long.parseLong(param);
	    	EmpData empData = empDataService.getByBioDataIdWithDepartment(bioId);
	    	Map<String, Object> params = new HashMap<>();
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
	                return CommonReportUtil.exportReportToPDFStream("biodata4.jasper", params, "Biodata", maps);
	        }
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
            return new DefaultStreamedContent();
        }
    }
    
    public StreamedContent getFileCv() {
    	FacesContext context = FacesUtil.getFacesContext();
        String param = context.getExternalContext().getRequestParameterMap().get("id");
    	StreamedContent file = null;
    	try {
    		file = bioDataService.generateCV(Long.parseLong(param));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e, e);
			file = new DefaultStreamedContent();
		}
    	return file;
    }

    public BioDataService getBioDataService() {
        return bioDataService;
    }

    public void setBioDataService(BioDataService bioDataService) {
        this.bioDataService = bioDataService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }
    
}