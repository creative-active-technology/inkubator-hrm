/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.DepartementUploadCaptureDao;
import com.inkubator.hrm.entity.DepartementUploadCapture;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "departementUploadCaptureDao")
@Lazy
public class DepartementUploadCaptureDaoImpl extends IDAOImpl<DepartementUploadCapture> implements DepartementUploadCaptureDao {

    @Override
    public Class<DepartementUploadCapture> getEntityClass() {
        return DepartementUploadCapture.class;
    }

}
