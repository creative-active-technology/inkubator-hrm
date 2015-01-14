package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.web.search.BenefitGroupSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Taufik hidayat
 */
public interface BenefitGroupDao extends IDAO<BenefitGroup> {

    public List<BenefitGroup> getByParam(BenefitGroupSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalBenefitGroupByParam(BenefitGroupSearchParameter parameter);
    
    public String getBenefitGroupNameByPk(Long id) throws Exception;
    
    public List<BenefitGroup> getBenefitGroupData(Long id);
}
