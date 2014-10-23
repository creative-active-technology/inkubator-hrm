/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.DepartementUploadCapture;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.web.model.FingerUploadModel;
import com.inkubator.hrm.web.search.MecineFingerSearchParameter;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface MecineFingerService extends IService<MecineFinger> {

    public List<MecineFinger> getByParam(MecineFingerSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(MecineFingerSearchParameter parameter) throws Exception;

    public MecineFinger getMecineFingerAndDetaiUploadByFK(long id) throws Exception;
    
    public void saveByModel(FingerUploadModel fingerUploadModel,  Set<DepartementUploadCapture> dataToSave)throws Exception;
}
