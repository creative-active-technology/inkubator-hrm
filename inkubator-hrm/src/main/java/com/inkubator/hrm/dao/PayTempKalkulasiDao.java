/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import java.util.List;

/**
 *
 * @author denifahri
 */
public interface PayTempKalkulasiDao extends IDAO<PayTempKalkulasi>{
	
    public void saveBatch(List<PayTempKalkulasi>data);
    
    public void deleteAllData();
    
}
