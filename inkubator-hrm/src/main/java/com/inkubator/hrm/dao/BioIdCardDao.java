package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioIdCard;

/**
 *
 * @author Taufik hidayat
 */
public interface BioIdCardDao extends IDAO<BioIdCard> {

    public List<BioIdCard> getAllDataByBioDataId(Long bioDataId);

    public BioIdCard getEntityByPKWithDetail(Long id);
    
    public Long getTotalByCardNumber(String cardNumber);

    public Long getTotalByCardNumberAndNotId(String cardNumber, Long id);

}
