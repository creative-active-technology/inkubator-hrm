 /* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
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

import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.BioDataService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.LogMonthEndTaxesService;
import com.inkubator.hrm.service.LogUnregPayrollService;
import com.inkubator.hrm.util.CommonReportUtil;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.search.ReportSalaryNoteSearchParameter;
import com.inkubator.hrm.web.search.UnregPayrollSearchParameter;
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
    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    @ManagedProperty(value = "#{logMonthEndTaxesService}")
    private LogMonthEndTaxesService logMonthEndTaxesService;
    @ManagedProperty(value = "#{logUnregPayrollService}")
    private LogUnregPayrollService logUnregPayrollService;
    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;

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
        logUnregPayrollService = null;
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

    public StreamedContent getOfferingLetter() throws JRException, SQLException, Exception{
    	Map<String, Object> params = new HashMap<>();
    	FacesContext context = FacesUtil.getFacesContext();
    	StreamedContent file = new DefaultStreamedContent();
    	String contentSurat = context.getExternalContext().getRequestParameterMap().get("contentSurat");
    	String penandaTangan = context.getExternalContext().getRequestParameterMap().get("penandaTangan");
    	String signature = context.getExternalContext().getRequestParameterMap().get("signature");
    	
    	// parameter
    	params.put("companyId", HrmUserInfoUtil.getCompanyId());
    	params.put("contentSurat", contentSurat);
    	params.put("penandaTangan", penandaTangan);
    	if(signature == null){
    		params.put("signature", signature);
    	}
    	file = CommonReportUtil.exportReportToPDFStream("offering_letter_report.jasper", params, "Test.pdf");
        return file;
    }
    
    public StreamedContent getSystemLetter() throws JRException, SQLException, Exception{
    	Map<String, Object> params = new HashMap<>();
    	FacesContext context = FacesUtil.getFacesContext();
    	StreamedContent file = new DefaultStreamedContent();
    	String contentSurat = context.getExternalContext().getRequestParameterMap().get("contentSurat");
    	String penandaTangan = context.getExternalContext().getRequestParameterMap().get("penandaTangan");
    	String signature = context.getExternalContext().getRequestParameterMap().get("signature");
    	
    	// parameter
    	params.put("companyId", HrmUserInfoUtil.getCompanyId());
    	params.put("contentSurat", contentSurat);
    	params.put("penandaTangan", penandaTangan);
    	if(signature == null){
    		params.put("signature", signature);
    	}
        file = CommonReportUtil.exportReportToPDFStream("system_letter_report.jasper", params, "Test.pdf");
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
            params.put("SUBREPORT_DIR", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/"));
            params.put("IMAGE_DIR", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/"));

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

    public StreamedContent getFilePersonalSalarySlip(Long periodId, Long empDataId) {
        StreamedContent file = new DefaultStreamedContent();
        try {
            if (periodId != null && empDataId != null) {
                file = logMonthEndPayrollService.generatePersonalSalarySlip(periodId, empDataId);
            }
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            LOGGER.error(ex, ex);
        }
        return file;
    }

    public StreamedContent getFileMassSalarySlip(ReportSalaryNoteSearchParameter searchParameter) {
        StreamedContent file = new DefaultStreamedContent();
        try {
        	file = logMonthEndPayrollService.generateMassSalarySlip(searchParameter);
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            LOGGER.error(ex, ex);
        }
        return file;
    }
    
    public StreamedContent getFilePersonalUnregSalarySlip(Long unregSalaryId, Long empDataId) {
        StreamedContent file = new DefaultStreamedContent();
        try {
            if (unregSalaryId != null && empDataId != null) {
                file = logUnregPayrollService.generatePersonalSalarySlip(unregSalaryId, empDataId);
            }
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            LOGGER.error(ex, ex);
        }
        return file;
    }

    public StreamedContent getFileMassUnregSalarySlip(UnregPayrollSearchParameter searchParameter) {
        StreamedContent file = new DefaultStreamedContent();
        try {
        	file = logUnregPayrollService.generateMassSalarySlip(searchParameter);
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

	public LogUnregPayrollService getLogUnregPayrollService() {
		return logUnregPayrollService;
	}

	public void setLogUnregPayrollService(LogUnregPayrollService logUnregPayrollService) {
		this.logUnregPayrollService = logUnregPayrollService;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	

	
}
