package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.FavoriteMenu;

/**
 *
 * @author rizkykojek
 */
public interface FavoriteMenuService extends IService<FavoriteMenu> {

	public List<FavoriteMenu> getAllDataByUserIdWithMenus(String userId);
}
