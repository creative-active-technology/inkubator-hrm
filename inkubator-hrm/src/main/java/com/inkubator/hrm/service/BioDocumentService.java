package com.inkubator.hrm.service;

import java.util.List;

import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioDocument;

/**
 *
 * @author rizkykojek
 */
public interface BioDocumentService extends IService<BioDocument> {

	public List<BioDocument> getAllDataByBioDataId(Long bioDataId) throws Exception;
	
	public void save(BioDocument entity, UploadedFile documentFile) throws Exception;
	
	public void update(BioDocument entity, UploadedFile documentFile) throws Exception;
	
}
