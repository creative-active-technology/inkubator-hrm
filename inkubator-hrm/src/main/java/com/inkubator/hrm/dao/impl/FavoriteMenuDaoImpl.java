package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FavoriteMenuDao;
import com.inkubator.hrm.entity.FavoriteMenu;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "favoriteMenuDao")
@Lazy
public class FavoriteMenuDaoImpl extends IDAOImpl<FavoriteMenu> implements FavoriteMenuDao {

	@Override
	public Class<FavoriteMenu> getEntityClass() {
		return FavoriteMenu.class;
		
	}

	@Override
	public List<FavoriteMenu> getAllDataByUserIdWithMenus(String userId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("hrmUser", "hrmUser", JoinType.INNER_JOIN);
		criteria.setFetchMode("hrmMenu", FetchMode.JOIN);
		criteria.add(Restrictions.eq("hrmUser.userId", userId));
		
		return criteria.list();
	}
	

}
