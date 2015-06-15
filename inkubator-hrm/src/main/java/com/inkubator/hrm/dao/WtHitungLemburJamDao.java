package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.WtHitungLemburJam;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface WtHitungLemburJamDao extends IDAO<WtHitungLemburJam> {

	public List<WtHitungLemburJam> getListByWtHitungLemburId(Long wtHitungLemburId);
}
