package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PublicHolidayException;
import com.inkubator.hrm.web.search.PublicHolidayExceptionSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface PublicHolidayExceptionDao extends IDAO<PublicHolidayException> {

    public List<PublicHolidayException> getByParam(PublicHolidayExceptionSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalPublicHolidayExceptionByParam(PublicHolidayExceptionSearchParameter parameter);

    public PublicHolidayException getEntityByPKWithDetail(Long id);

}
