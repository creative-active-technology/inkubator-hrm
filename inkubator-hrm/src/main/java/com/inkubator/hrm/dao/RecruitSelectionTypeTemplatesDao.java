/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplates;
import java.util.List;

/**
 *
 * @author deni.fahri
 */
public interface RecruitSelectionTypeTemplatesDao extends IDAO<RecruitSelectionTypeTemplates> {

    public RecruitSelectionTypeTemplates getLevelOne(String root);

    public List<RecruitSelectionTypeTemplates> getByParentId(long id);
}
