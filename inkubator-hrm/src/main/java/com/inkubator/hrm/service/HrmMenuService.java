package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.web.search.HrmMenuSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface HrmMenuService extends IService<HrmMenu> {
	
	public List<HrmMenu> getByParam(HrmMenuSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(HrmMenuSearchParameter parameter);
    
    public List<HrmMenu> getAllDataByParamAndNotRoleId(Long roleId, HrmMenuSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParamAndNotRoleId(Long roleId, HrmMenuSearchParameter parameter);
    
    public List<HrmMenu> getAllDataByLevel(Integer level);

	public HrmMenu getEntityByPkWithDetail(long id);

	public List<HrmMenu> getAllDataByLevelAndNotId(int level, Long id);

}
