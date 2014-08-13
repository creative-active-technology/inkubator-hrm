package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioIdCard;

/**
*
* @author Taufik Hidayat
*/
public interface BioIdCardService extends IService<BioIdCard> {

	public List<BioIdCard> getAllDataByBioDataId(Long bioDataId) throws Exception;
        
        public BioIdCard getEntityByPKWithDetail(Long id) throws Exception;

}
