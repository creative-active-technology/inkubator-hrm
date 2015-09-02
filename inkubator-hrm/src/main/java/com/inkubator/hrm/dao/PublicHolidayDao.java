package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PublicHoliday;
import com.inkubator.hrm.web.search.PublicHolidaySearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public interface PublicHolidayDao extends IDAO<PublicHoliday> {

    public List<PublicHoliday> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalPublicHolidayByParam(String parameter);

    public PublicHoliday getEntityByPKWithDetail(Long id);

    public List<PublicHoliday> getAllWithDetail();

    public List<PublicHoliday> getReportByParam(PublicHolidaySearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getReportTotalByParam(PublicHolidaySearchParameter parameter);

    public List<PublicHoliday> getReportHistoryByParam(PublicHolidaySearchParameter parameter);

    public List<PublicHoliday> getByLeavShcemaId(long id);

}
