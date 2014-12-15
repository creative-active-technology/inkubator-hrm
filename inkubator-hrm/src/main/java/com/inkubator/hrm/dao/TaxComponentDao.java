package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TaxComponent;

/**
 *
 * @author Taufik hidayat
 */
public interface TaxComponentDao extends IDAO<TaxComponent> {

    public List<TaxComponent> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalTaxComponentByParam(String parameter);

    public List<TaxComponent> getAllDataByUseComponent();
}
