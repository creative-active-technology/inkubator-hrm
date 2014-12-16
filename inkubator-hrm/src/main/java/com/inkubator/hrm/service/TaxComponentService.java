package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TaxComponent;

/**
 *
 * @author Taufik Hidayat
 */
public interface TaxComponentService extends IService<TaxComponent> {

    public List<TaxComponent> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalTaxComponentByParam(String parameter) throws Exception;

    public List<TaxComponent> getAllDataByUseComponent();
    
    public String getTaxComponentNameByPk(Long id) throws Exception;

}
