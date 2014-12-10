/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayTempKalkulasi;

/**
 *
 * @author denifahri
 */
public interface PayTempKalkulasiService extends IService<PayTempKalkulasi> {

    /*public void calculatePayRoll() throws Exception;*/

	public List<PayTempKalkulasi> getAllDataCalculatedPayment() throws Exception;
	
	public void deleteAllData() throws Exception;
	
}
