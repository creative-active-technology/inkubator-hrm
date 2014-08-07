package com.inkubator.hrm.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.UploadedFile;

import com.inkubator.common.util.FilesUtil;

/**
 *
 * @author rizkykojek
 */
public class UploadFilesUtil {
	private Long jpgSizeLimit;
	private Long pngSizeLimit;
	private Long mp4SizeLimit;
	private Long flvSizeLimit;
	private Long docSizeLimit;
	private Long xlsSizeLimit;
	private Long pdfSizeLimit;
	private Long pptSizeLimit;
	private Long ppsSizeLimit;
	
	public Map<String, String> checkUploadFileSizeLimit(UploadedFile file){		
		String extension = StringUtils.substringAfterLast(file.getFileName(), ".");
		boolean isValid = Boolean.TRUE;
		long sizeMax = 0;
		if(StringUtils.equals(extension , "jpg") && file.getSize() > jpgSizeLimit){
			isValid =  Boolean.FALSE;
			sizeMax = jpgSizeLimit;
		} else if(StringUtils.equals(extension , "png") && file.getSize() > pngSizeLimit){
			isValid =  Boolean.FALSE;
			sizeMax = pngSizeLimit;
		} else if(StringUtils.equals(extension , "mp4") && file.getSize() > mp4SizeLimit){
			isValid =  Boolean.FALSE;
			sizeMax = mp4SizeLimit;
		} else if(StringUtils.equals(extension , "flv") && file.getSize() > flvSizeLimit){
			isValid =  Boolean.FALSE;
			sizeMax = flvSizeLimit;
		} else if((StringUtils.equals(extension , "doc") || StringUtils.equals(extension , "docx"))  && file.getSize() > docSizeLimit){
			isValid =  Boolean.FALSE;
			sizeMax = docSizeLimit;
		} else if((StringUtils.equals(extension , "xls")  || StringUtils.equals(extension , "xlsx")) && file.getSize() > xlsSizeLimit){
			isValid =  Boolean.FALSE;
			sizeMax = xlsSizeLimit;
		} else if(StringUtils.equals(extension , "pdf") && file.getSize() > pdfSizeLimit){
			isValid =  Boolean.FALSE;
			sizeMax = pdfSizeLimit;
		} else if((StringUtils.equals(extension , "ppt") || StringUtils.equals(extension , "pptx")) && file.getSize() > pptSizeLimit){
			isValid =  Boolean.FALSE;
			sizeMax = pptSizeLimit;
		} else if((StringUtils.equals(extension , "pps") || StringUtils.equals(extension , "ppsx")) && file.getSize() > ppsSizeLimit){
			isValid =  Boolean.FALSE;
			sizeMax = ppsSizeLimit;
		}
		
		//set messages to resultMap
		Map<String, String> results = new HashMap<String, String>();
		if(isValid){
			results.put("result", "true");
		} else {
			String sizeMaxMessage = FilesUtil.getHumanReadableSize(sizeMax);
			results.put("result", "false");
			results.put("sizeMax", sizeMaxMessage);
		}
		
		return results;
	}

	public Long getJpgSizeLimit() {
		return jpgSizeLimit;
	}

	public void setJpgSizeLimit(Long jpgSizeLimit) {
		this.jpgSizeLimit = jpgSizeLimit;
	}

	public Long getPngSizeLimit() {
		return pngSizeLimit;
	}

	public void setPngSizeLimit(Long pngSizeLimit) {
		this.pngSizeLimit = pngSizeLimit;
	}

	public Long getMp4SizeLimit() {
		return mp4SizeLimit;
	}

	public void setMp4SizeLimit(Long mp4SizeLimit) {
		this.mp4SizeLimit = mp4SizeLimit;
	}

	public Long getFlvSizeLimit() {
		return flvSizeLimit;
	}

	public void setFlvSizeLimit(Long flvSizeLimit) {
		this.flvSizeLimit = flvSizeLimit;
	}

	public Long getDocSizeLimit() {
		return docSizeLimit;
	}

	public void setDocSizeLimit(Long docSizeLimit) {
		this.docSizeLimit = docSizeLimit;
	}

	public Long getXlsSizeLimit() {
		return xlsSizeLimit;
	}

	public void setXlsSizeLimit(Long xlsSizeLimit) {
		this.xlsSizeLimit = xlsSizeLimit;
	}

	public Long getPdfSizeLimit() {
		return pdfSizeLimit;
	}

	public void setPdfSizeLimit(Long pdfSizeLimit) {
		this.pdfSizeLimit = pdfSizeLimit;
	}

	public Long getPptSizeLimit() {
		return pptSizeLimit;
	}

	public void setPptSizeLimit(Long pptSizeLimit) {
		this.pptSizeLimit = pptSizeLimit;
	}

	public Long getPpsSizeLimit() {
		return ppsSizeLimit;
	}

	public void setPpsSizeLimit(Long ppsSizeLimit) {
		this.ppsSizeLimit = ppsSizeLimit;
	}
}
