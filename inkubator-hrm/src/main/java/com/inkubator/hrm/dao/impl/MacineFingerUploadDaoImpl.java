/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.MacineFingerUploadDao;
import com.inkubator.hrm.entity.MacineFingerUpload;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "macineFingerUploadDao")
@Lazy
public class MacineFingerUploadDaoImpl extends IDAOImpl<MacineFingerUpload> implements MacineFingerUploadDao {

    @Override
    public Class<MacineFingerUpload> getEntityClass() {
        return MacineFingerUpload.class;
    }

}
