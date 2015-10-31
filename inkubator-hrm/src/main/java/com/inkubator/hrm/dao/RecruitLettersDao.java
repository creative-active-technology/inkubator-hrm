/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.web.search.RecrutimentLetterSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author denifahri
 */
public interface RecruitLettersDao extends IDAO<RecruitLetters> {

    public RecruitLetters getByPkWithDetail(long id);

    public List<RecruitLetters> getAllWithSpecificLetterType(int type);

    public void saveAndMerge(RecruitLetters letters);
    
    public RecruitLetters sanvAndFlus(RecruitLetters letters);

    public List<RecruitLetters> getByParam(RecrutimentLetterSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(RecrutimentLetterSearchParameter parameter);

}
