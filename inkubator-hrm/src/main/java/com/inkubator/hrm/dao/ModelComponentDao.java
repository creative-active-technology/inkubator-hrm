package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.web.search.ModelComponentSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Taufik hidayat
 */
public interface ModelComponentDao extends IDAO<ModelComponent> {

    public List<ModelComponent> getByParam(ModelComponentSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalModelComponentByParam(ModelComponentSearchParameter parameter);

    public ModelComponent getEntityByPKWithDetail(Long id);
   

}
