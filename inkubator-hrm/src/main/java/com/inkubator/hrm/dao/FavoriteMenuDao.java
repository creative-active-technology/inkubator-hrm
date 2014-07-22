package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.FavoriteMenu;

/**
 *
 * @author rizkykojek
 */
public interface FavoriteMenuDao extends IDAO<FavoriteMenu> {

	public List<FavoriteMenu> getAllDataByUserIdWithMenus(String userId);
}
