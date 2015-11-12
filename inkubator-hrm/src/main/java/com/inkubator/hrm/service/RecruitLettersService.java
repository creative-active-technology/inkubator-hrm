/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.web.search.RecrutimentLetterSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author denifahri
 */
public interface RecruitLettersService extends IService<RecruitLetters> {

    public RecruitLetters getByPkWithDetail(long id) throws Exception;

    public List<RecruitLetters> getByParam(RecrutimentLetterSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(RecrutimentLetterSearchParameter parameter) throws Exception;
    public  void saveWithSelectionType(RecruitLetters letters, List<Long>data)throws Exception;
    
    public void doDistributionRescruitLetter(RecruitLetters letters) throws Exception;
}
