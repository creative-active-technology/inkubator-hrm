package com.inkubator.hrm.web;

import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesIO;
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "imageFileTemplateStreamerController")
@ApplicationScoped
public class ImageFileTemplateStreamerController extends BaseController {
	
	@ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;

	public void setFacesIO(FacesIO facesIO) {
		this.facesIO = facesIO;
	}
	
	public StreamedContent getPaySalaryUploadFile() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String extension = context.getExternalContext().getRequestParameterMap().get("extension");
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if (!context.getRenderResponse() && extension != null) {
            try {
            	String fileName = StringUtils.EMPTY;
                StringBuffer path = new StringBuffer();
                path.append(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/file_template/"));
//                path.append("\\");
                switch (extension) {
					case "csv":
						path.append("/pay_salary_upload.csv");
						fileName = "pay_salary_upload_template.csv";
						break;
					case "xls":
						path.append("/pay_salary_upload.xls");
						fileName = "pay_salary_upload_template.xls";
						break;
					case "xlsx":
						path.append("/pay_salary_upload.xlsx");
						fileName = "pay_salary_upload_template.xlsx";
						break;
					default:
						path.append("/pay_salary_upload.csv");
						fileName = "pay_salary_upload_template.csv";
						break;
				}
                
                InputStream is = facesIO.getInputStreamFromURL(path.toString());
                streamedContent = new DefaultStreamedContent(is, null, fileName);

            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
        
        return streamedContent;
    }
	
	public StreamedContent getFingerSwapCapturedUploadFile() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String extension = context.getExternalContext().getRequestParameterMap().get("extension");
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if (!context.getRenderResponse() && extension != null) {
            try {
            	String fileName = StringUtils.EMPTY;
                StringBuffer path = new StringBuffer();
                path.append(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/file_template/finger_swap/"));
                switch (extension) {
					case "csv":
						path.append("/finger_swap_captured_upload.csv");
						fileName = "finger_swap_captured_template.csv";
						break;
					case "xls":
						path.append("/finger_swap_captured_upload.xls");
						fileName = "finger_swap_captured_template.xls";
						break;
					case "xlsx":
						path.append("/finger_swap_captured_upload.xlsx");
						fileName = "finger_swap_captured_template.xlsx";
						break;
					default:
						path.append("/finger_swap_captured_upload.csv");
						fileName = "finger_swap_captured_template.csv";
						break;
				}
                
                InputStream is = facesIO.getInputStreamFromURL(path.toString());
                streamedContent = new DefaultStreamedContent(is, null, fileName);

            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
        
        return streamedContent;
    }
	
	public StreamedContent getRecruitApplicantUploadFile() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String extension = context.getExternalContext().getRequestParameterMap().get("extension");
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if (!context.getRenderResponse() && extension != null) {
            try {
            	String fileName = StringUtils.EMPTY;
                StringBuffer path = new StringBuffer();
                path.append(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/file_template/recruit_applicant/"));
                switch (extension) {
					case "csv":
						path.append("/recruit_applicant.csv");
						fileName = "recruit_applicant.csv";
						break;
					case "xls":
						path.append("/recruit_applicant.xls");
						fileName = "recruit_applicant.xls";
						break;
					case "xlsx":
						path.append("/recruit_applicant.xlsx");
						fileName = "recruit_applicant.xlsx";
						break;
					default:
						path.append("/recruit_applicant.csv");
						fileName = "recruit_applicant.csv";
						break;
				}
                
                InputStream is = facesIO.getInputStreamFromURL(path.toString());
                streamedContent = new DefaultStreamedContent(is, null, fileName);

            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
        
        return streamedContent;
    }
        
    public StreamedContent getPayTempAttendanceUploadFile() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String extension = context.getExternalContext().getRequestParameterMap().get("extension");
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if (!context.getRenderResponse() && extension != null) {
            try {
            	String fileName = StringUtils.EMPTY;
                StringBuffer path = new StringBuffer();
                path.append(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/file_template/pay_attendance/"));
//                path.append("\\");
                switch (extension) {
					case "csv":
						path.append("/pay_temp_attendance_upload.csv");
						fileName = "pay_temp_attendance_upload_template.csv";
						break;
					case "xls":
						path.append("/pay_temp_attendance_upload.xls");
						fileName = "pay_temp_attendance_upload_template.xls";
						break;
					case "xlsx":
						path.append("/pay_temp_attendance_upload.xlsx");
						fileName = "pay_temp_attendance_upload_template.xlsx";
						break;
					default:
						path.append("/pay_temp_attendance_upload.csv");
						fileName = "pay_temp_attendance_upload_template.csv";
						break;
				}
                
                InputStream is = facesIO.getInputStreamFromURL(path.toString());
                streamedContent = new DefaultStreamedContent(is, null, fileName);

            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
        
        return streamedContent;
    } 
    
    public StreamedContent getLoanUploadFile() throws IOException {
        FacesContext context = FacesUtil.getFacesContext();
        String extension = context.getExternalContext().getRequestParameterMap().get("extension");
        StreamedContent streamedContent = new DefaultStreamedContent();
        
        if (!context.getRenderResponse() && extension != null) {
            try {
            	String fileName = StringUtils.EMPTY;
                StringBuffer path = new StringBuffer();
                path.append(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/file_template/loan/"));
//                path.append("\\");
                switch (extension) {
					case "csv":
						path.append("/loan_upload.csv");
						fileName = "loan_upload_template.csv";
						break;
					case "xls":
						path.append("/loan_upload.xls");
						fileName = "loan_upload_template.xls";
						break;
					case "xlsx":
						path.append("/loan_upload.xlsx");
						fileName = "loan_upload_template.xlsx";
						break;
					default:
						path.append("/loan_upload.csv");
						fileName = "loan_upload_template.csv";
						break;
				}
                
                InputStream is = facesIO.getInputStreamFromURL(path.toString());
                streamedContent = new DefaultStreamedContent(is, null, fileName);

            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
        
        return streamedContent;
    } 
}
