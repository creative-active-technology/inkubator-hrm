package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioBankAccount;

/**
*
* @author Taufik Hidayat
*/
public interface BioBankAccountService extends IService<BioBankAccount> {

	public List<BioBankAccount> getAllDataByBioDataId(Long bioDataId) throws Exception;
        
        public BioBankAccount getEntityByPKWithDetail(Long id) throws Exception;

}
