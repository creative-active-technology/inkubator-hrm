/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.web.model.RiwayatAksesModel;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface RiwayatAksesService extends IService<RiwayatAkses> {

    public void doSaveAccess(RiwayatAkses akses) throws Exception;

    public List<RiwayatAkses> getDataByUserId(String userID, int firstResult, int maxResults, Order order) throws Exception;

    public List<RiwayatAksesModel> getDataByUserIdWithModel(String userID, int firstResult, int maxResults, Order order) throws Exception;

    public List<RiwayatAkses> getRiwayatAksesByUserIdWithModel(String userID, int firstResult, int maxResults, Order order) throws Exception;

}
