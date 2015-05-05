package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.web.search.HrmMenuSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface HrmMenuDao extends IDAO<HrmMenu> {

    public List<HrmMenu> getByParam(HrmMenuSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(HrmMenuSearchParameter parameter);

    public List<HrmMenu> getAllDataByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids);

    public List<HrmMenu> getAllDataFetchChildByLevel(Integer level);

    public HrmMenu getEntityByPkWithDetail(long id);

    public List<HrmMenu> getAllDataByLevelAndNotId(int level, Long id);
    
    public List<HrmMenu> getAllDataByLevelAndNotId(int level, List<Long> ids);

    public List<HrmMenu> getAllDataByUserRolesAndHaveNoChild(String parameter, List<Long> exceptMenuIds, List<String> roles, int firstResult, int maxResults, Order orderable);

    public Long getTotalByUserRolesAndHaveNoChild(String parameter, List<Long> exceptMenuIds, List<String> roles);

    public List<HrmMenu> getMunuByLevelOneAndRoleName(String roleName);

    public List<HrmMenu> getlistChildByParentMenu(long parentId, String roleName);
    
    public List<HrmMenu> gelAllDataByOrderLevelMenuGreaterThan(Integer orderLevelMenu, Long parentMenuId, Long id);
    
    public HrmMenu getEntityByOrderLevelMenuAndParentMenuIdAndExceptId(Integer orderLevelMenu, Long parentMenuId, Long exceptId);

    public HrmMenu getByPathRelative(String Name);
    
    public List<HrmMenu> getAllDataFetchChildByParentId(Long parentId, List<Long> exceptId);

	public HrmMenu getEntityFetchChildById(Long id);
}
