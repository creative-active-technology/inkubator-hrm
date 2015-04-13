package com.inkubator.hrm.service;
import com.inkubator.hrm.entity.OrgJobFamilyLevel;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.search.OrgJobFamilyLevelSearchParameter;
/**
 *
 * @author WebGenX
 */
public interface OrgJobFamilyLevelService extends IService<OrgJobFamilyLevel>{
public List<OrgJobFamilyLevel> getByParam(OrgJobFamilyLevelSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception ;
public Long getTotalOrgJobFamilyLevelByParam(OrgJobFamilyLevelSearchParameter searchParameter) throws Exception;
}
