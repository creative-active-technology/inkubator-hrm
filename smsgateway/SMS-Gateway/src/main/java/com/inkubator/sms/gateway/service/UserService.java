/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.sms.gateway.entity.User;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deni
 */
public interface UserService extends IService<User> {

    public List<User> getAllByFullTextService(String parameter, int minResult, int maxResult, Order order) throws Exception;

    public Integer getTotalByFullTextService(String parameter) throws Exception;
}
