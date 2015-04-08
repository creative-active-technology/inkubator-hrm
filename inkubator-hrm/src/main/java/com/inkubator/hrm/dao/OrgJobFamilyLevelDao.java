package com.inkubator.hrm.dao;

import com.inkubator.hrm.entity.OrgJobFamilyLevel;
import com.inkubator.datacore.dao.IDAO;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.OrgJobFamilyLevelSearchParameter;

/**
 *
 * @author WebGenX
 */
public interface OrgJobFamilyLevelDao extends IDAO<OrgJobFamilyLevel> {

    public List<OrgJobFamilyLevel> getByParam(OrgJobFamilyLevelSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalOrgJobFamilyLevelByParam(OrgJobFamilyLevelSearchParameter searchParameter);
}
