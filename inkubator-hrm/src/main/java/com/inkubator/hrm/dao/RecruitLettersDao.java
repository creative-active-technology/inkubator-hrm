/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitLetters;
import java.util.List;

/**
 *
 * @author denifahri
 */
public interface RecruitLettersDao extends IDAO<RecruitLetters> {

    public RecruitLetters getByPkWithDetail(long id);

    public List<RecruitLetters> getAllWithSpecificLetterType(int type);
    
    public void saveAndMerge(RecruitLetters letters);
}