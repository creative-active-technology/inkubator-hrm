/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Gender;

/**
 *
 * @author Deni
 */
public interface GenderDao extends IDAO<Gender> {
    public List<Integer> getAllGender();
}
