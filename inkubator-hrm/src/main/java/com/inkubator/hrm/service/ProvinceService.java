package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.web.search.ProvinceSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface ProvinceService extends IService<Province> {

	public List<Province> getByParam(ProvinceSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(ProvinceSearchParameter parameter) throws Exception;
        
        public Province getProvinceByIdWithDetail(Long id) throws Exception;

}
