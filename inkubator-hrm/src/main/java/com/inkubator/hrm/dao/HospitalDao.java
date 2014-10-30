package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Hospital;
import com.inkubator.hrm.web.search.HospitalSearchParameter;

/**
 *
 * @author Taufik hidayat
 */
public interface HospitalDao extends IDAO<Hospital> {

    public List<Hospital> getByParam(HospitalSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalHospitalByParam(HospitalSearchParameter parameter);

    public Hospital getEntityByPKWithDetail(Long id);

    public Long getTotalByPhone(String phone);

    public Long getTotalByPhoneAndNotId(String phone, Long id);

}
