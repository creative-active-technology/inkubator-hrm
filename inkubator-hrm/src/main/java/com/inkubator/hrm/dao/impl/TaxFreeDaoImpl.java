/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TaxFreeDao;
import com.inkubator.hrm.entity.TaxFree;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni
 */
@Repository(value = "taxFreeDao")
@Lazy
public class TaxFreeDaoImpl extends IDAOImpl<TaxFree> implements TaxFreeDao{

    @Override
    public Class<TaxFree> getEntityClass() {
        return TaxFree.class;
    }
    
}
