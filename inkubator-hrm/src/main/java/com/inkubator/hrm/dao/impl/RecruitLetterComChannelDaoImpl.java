/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitLetterComChannelDao;
import com.inkubator.hrm.entity.RecruitLetterComChannel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "recruitLetterComChannelDao")
@Lazy
public class RecruitLetterComChannelDaoImpl extends IDAOImpl<RecruitLetterComChannel> implements RecruitLetterComChannelDao{

    @Override
    public Class<RecruitLetterComChannel> getEntityClass() {
    return RecruitLetterComChannel.class;
    }
    
}
