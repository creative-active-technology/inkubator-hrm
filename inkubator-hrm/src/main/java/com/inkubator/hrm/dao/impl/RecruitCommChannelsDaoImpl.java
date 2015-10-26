/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitCommChannelsDao;
import com.inkubator.hrm.entity.RecruitCommChannels;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "recruitCommChannelsDao")
@Lazy
public class RecruitCommChannelsDaoImpl extends IDAOImpl<RecruitCommChannels>implements RecruitCommChannelsDao{

    @Override
    public Class<RecruitCommChannels> getEntityClass() {
     return RecruitCommChannels.class;
    }
    
}
