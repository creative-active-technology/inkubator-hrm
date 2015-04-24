/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplates;
import java.util.List;
import org.primefaces.model.TreeNode;

/**
 *
 * @author deni.fahri
 */
public interface RecruitSelectionTypeTemplatesService extends IService<RecruitSelectionTypeTemplates> {

    public RecruitSelectionTypeTemplates getLevelOne(String root) throws Exception;

    public List<RecruitSelectionTypeTemplates> getByParentId(long id) throws Exception;

    public TreeNode cretaeNodeBreakEndPoint(String param) throws Exception;
    
    public RecruitSelectionTypeTemplates getWithJabatan(long id) throws Exception;

}
