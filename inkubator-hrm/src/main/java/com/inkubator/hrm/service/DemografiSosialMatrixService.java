/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import java.util.List;

/**
 *
 * @author deni
 */
public interface DemografiSosialMatrixService {

    public List<EducationLevel> getAllDataOrderByLevel() throws Exception;

    public List<String> getAllNameByParamOrderByLevelForAbsis(String param) throws Exception;

    public List<EmpDataMatrixModel> getAllDataPendidikanVsGender() throws Exception;

    public List<EmpDataMatrixModel> getAllDataGenderVsPendidikan() throws Exception;
    
    public List<EmpDataMatrixModel> getAllDataUmurVsGender() throws Exception;
    
     public List<EmpDataMatrixModel> getAllDataGenderVsUmur() throws Exception;

}
