package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.WtHitungLemburJam;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface WtHitungLemburJamService extends IService<WtHitungLemburJam> {

	public List<WtHitungLemburJam> getListByWtHitungLemburId(Long wtHitungLemburId) throws Exception;
}
