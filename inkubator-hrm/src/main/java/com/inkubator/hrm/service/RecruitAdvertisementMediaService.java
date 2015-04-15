/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.web.search.RecruitAdvertisementMediaSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface RecruitAdvertisementMediaService extends IService<RecruitAdvertisementMedia> {

    public List<RecruitAdvertisementMedia> getByParam(RecruitAdvertisementMediaSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(RecruitAdvertisementMediaSearchParameter searchParameter);

    public RecruitAdvertisementMedia getEntityByPkWithDetail(Long id);
}
