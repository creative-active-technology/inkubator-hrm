package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioDocument;

/**
 *
 * @author rizkykojek
 */
public interface BioDocumentDao extends IDAO<BioDocument> {

	public List<BioDocument> getAllDataByBioDataId(Long bioDataId);
        
        public Long getTotalByDocumentNumber(String documentNo);
	
}
