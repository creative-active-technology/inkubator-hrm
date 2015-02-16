package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.web.search.ProvinceSearchParameter;

/**
 *
 * @author Taufik hidayat
 */
public interface ProvinceDao extends IDAO<Province> {

    public List<Province> getByParam(ProvinceSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalProvinceByParam(ProvinceSearchParameter parameter);

    public Long getTotalByCode(String name);

    public Long getTotalByCodeAndNotId(String code, Long id);

    public Province getProvinceByIdWithDetail(Long id);

    public List<Province> getByCountryIdWithDetail(Long id);

    public List<Province> getByCountryId(Long countryId);

    public Long getTotalByPhoneCode(String phoneCode);

    public Long getTotalByPhoneCodeAndNotId(String phoneCode, Long id);

}
