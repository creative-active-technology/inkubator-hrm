package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioSertifikasi;
import org.primefaces.model.UploadedFile;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface BioSertifikasiService extends IService<BioSertifikasi> {

public List<BioSertifikasi> getAllDataByBioDataId(Long bioDataId);

public void save(BioSertifikasi entity, UploadedFile documentFile) throws Exception;
	
public void update(BioSertifikasi entity, UploadedFile documentFile) throws Exception;

public BioSertifikasi getEntityByPKWithDetail(Long id) throws Exception;

}
