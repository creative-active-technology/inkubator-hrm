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
    
    public List<HrmMenu> getAllDataByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids);
    
    public List<HrmMenu> getAllDataByLevel(Integer level);

	public HrmMenu getEntityByPkWithDetail(long id);

	public List<HrmMenu> getAllDataByLevelAndNotId(int level, Long id);
	
	public List<HrmMenu> getAllDataByUserRolesAndHaveNoChild(String parameter, List<String> roles, int firstResult, int maxResults, Order orderable);
	
	public Long getTotalByUserRolesAndHaveNoChild(String parameter, List<String> roles);

}
