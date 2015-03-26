package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.web.search.OrgTypeOfSpecSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author WebGenX
 */
public interface OrgTypeOfSpecDao extends IDAO<OrgTypeOfSpec> {

    public List<OrgTypeOfSpec> getByParam(OrgTypeOfSpecSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalOrgTypeOfSpecByParam(OrgTypeOfSpecSearchParameter searchParameter);
}
