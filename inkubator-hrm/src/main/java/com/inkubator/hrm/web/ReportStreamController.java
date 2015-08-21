 /* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.LogMonthEndTaxesService;
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

import org.apache.commons.lang3.StringUtils;
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
    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    @ManagedProperty(value = "#{logMonthEndTaxesService}")
    private LogMonthEndTaxesService logMonthEndTaxesService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

    }

    @PreDestroy
    private void cleanAndExit() {
        bioDataService = null;
        empDataService = null;
        logMonthEndPayrollService = null;
        logMonthEndTaxesService = null;
    }

    public StreamedContent getFileCardName() throws JRException, Exception {
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
            if (empData.getBioData() != null) {
                mapData.put("photo", empData.getBioData().getPathFoto());
            } else {
                mapData.put("photo", "C:/tmp/FolderUpload/no_image.png");
            }
            mapData.put("barcode", empData.getNik());
            maps.add(mapData);
        }
        if (id == null) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            try {
                return CommonReportUtil.exportReportToPDFStream("card_name.jasper", params, "Biodata", maps);
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
        }
    }

    public StreamedContent getFileCv() {
        FacesContext context = FacesUtil.getFacesContext();
        String param = context.getExternalContext().getRequestParameterMap().get("id");
    	StreamedContent file = new DefaultStreamedContent();
    	try {
    		if(param != null){
    			file = bioDataService.generateCV(Long.parseLong(param));
    		}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			LOGGER.error(ex, ex);
		}
    	return file;
    }

    public StreamedContent getFilePph() {
    
        FacesContext context = FacesUtil.getFacesContext();
        String param = context.getExternalContext().getRequestParameterMap().get("id");
      
        Map<String, Object> params = new HashMap<>();

        List<String> attachments = new ArrayList<String>();
        StreamedContent file = null;
        if (param != null) {
            params.put("emp_data_id", Integer.valueOf(param));
            params.put("SUBREPORT_DIR", FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\reports\\"));
            params.put("IMAGE_DIR", FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\images\\"));

        }
        if (param == null) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            try {
                file = CommonReportUtil.exportReportToPDFStreamWithAttachment("pph_report.jasper", params, "Report_Pph.pdf", attachments);
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
                return new DefaultStreamedContent();
            }
        }
        return file;
    }

    public StreamedContent getFileSalarySlip(Long periodId, Long empDataId) {
        StreamedContent file = new DefaultStreamedContent();
        try {
            if (empDataId != null && empDataId != null) {
                file = logMonthEndPayrollService.generateSalarySlip(periodId, empDataId);
            }
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            LOGGER.error(ex, ex);
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

    public LogMonthEndPayrollService getLogMonthEndPayrollService() {
        return logMonthEndPayrollService;
    }

    public void setLogMonthEndPayrollService(
            LogMonthEndPayrollService logMonthEndPayrollService) {
        this.logMonthEndPayrollService = logMonthEndPayrollService;
    }

    public LogMonthEndTaxesService getLogMonthEndTaxesService() {
        return logMonthEndTaxesService;
    }

    public void setLogMonthEndTaxesService(LogMonthEndTaxesService logMonthEndTaxesService) {
        this.logMonthEndTaxesService = logMonthEndTaxesService;
    }

}
