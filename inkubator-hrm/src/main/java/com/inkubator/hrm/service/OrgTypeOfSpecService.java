package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.web.search.OrgTypeOfSpecSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author WebGenX
 */
public interface OrgTypeOfSpecService extends IService<OrgTypeOfSpec> {

    public List<OrgTypeOfSpec> getByParam(OrgTypeOfSpecSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalOrgTypeOfSpecByParam(OrgTypeOfSpecSearchParameter searchParameter) throws Exception;
}
