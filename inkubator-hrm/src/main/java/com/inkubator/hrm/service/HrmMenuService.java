package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.web.search.HrmMenuSearchParameter;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author rizkykojek
 */
public interface HrmMenuService extends IService<HrmMenu> {

    public List<HrmMenu> getByParam(HrmMenuSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(HrmMenuSearchParameter parameter) throws Exception;

    public List<HrmMenu> getAllDataByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids) throws Exception;

    public List<HrmMenu> getAllDataFetchChildByLevel(Integer level) throws Exception;

    public HrmMenu getEntityByPkWithDetail(long id) throws Exception;

    public List<HrmMenu> getAllDataByLevelAndNotId(int level, Long ids) throws Exception;
    
    public List<HrmMenu> getAllDataByLevelAndNotId(int level, List<Long> id) throws Exception;

    public List<HrmMenu> getAllDataByUserRolesAndHaveNoChild(String parameter, List<Long> exceptMenuIds, List<String> roles, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByUserRolesAndHaveNoChild(String parameter, List<Long> exceptMenuIds, List<String> roles) throws Exception;

    public MenuModel getMenuHirarki() throws Exception;
    
    public List<HrmMenu> getAllDataFetchChildByParentId(Long parentId, List<Long> exceptId) throws Exception;
    
    public List<HrmMenu> getAllDataFetchChildByParentId(Long parentId) throws Exception;

    public HrmMenu getEntityFetchChildById(Long id) throws Exception;

}
