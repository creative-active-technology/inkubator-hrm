/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RiwayatAkses;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface RiwayatAksesDao extends IDAO<RiwayatAkses> {

    public List<RiwayatAkses> getDataByUserId(String userID, int firstResult, int maxResults, Order order);

    public List<RiwayatAkses> getByWeekDif(int value);

    public void deleteBatch(List<RiwayatAkses> data);

    public List<RiwayatAkses> getRiwayatAksesByUserIdWithModel(String userID, int firstResult, int maxResults, Order order);

}
