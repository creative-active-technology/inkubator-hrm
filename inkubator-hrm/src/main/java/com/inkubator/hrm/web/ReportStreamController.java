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
import javax.faces.context.FacesContext;
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
    @ManagedProperty(value = "#{bioDataService}")
    private BioDataService bioDataService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;

    @PreDestroy
    private void cleanAndExit() {
        bioDataService = null;
        empDataService = null;
    }

    public StreamedContent getFile() throws JRException, Exception {
        Map<String, Object> params = new HashMap<>();
        FacesContext fc = FacesContext.getCurrentInstance();
        String id = fc.getExternalContext().getRequestParameterMap().get("id");
        Map<String, String> mapData = new HashMap<String, String>();
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        Long cardNameId = null;
        if (id != null) {
            cardNameId = Long.valueOf(id);
            EmpData empData = empDataService.getByBioDataIdWithDepartment(cardNameId);
            // isi data
            mapData.put("NIK", empData.getNik());
            mapData.put("name", empData.getBioData().getFirstName() + " " + empData.getBioData().getLastName());
            mapData.put("jabatan", empData.getJabatanByJabatanId().getName());
            mapData.put("department", empData.getJabatanByJabatanId().getDepartment().getDepartmentName());
            mapData.put("tmb", String.valueOf(empData.getJoinDate()));
            mapData.put("photo", empData.getBioData().getPathFoto());
            mapData.put("barcode", empData.getNik());
            maps.add(mapData);
        }
        if (id == null) {
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