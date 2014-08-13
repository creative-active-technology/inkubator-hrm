package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioBankAccount;

/**
 *
 * @author Taufik hidayat
 */
public interface BioBankAccountDao extends IDAO<BioBankAccount> {

    public List<BioBankAccount> getAllDataByBioDataId(Long bioDataId);

    public BioBankAccount getEntityByPKWithDetail(Long id);

    public Long getTotalBySwiftCode(String swiftCode);

    public Long getTotalBySwiftCodeAndNotId(String swiftCode, Long id);
    
    public Long getTotalByAccountNumber(String accountNumber);

    public Long getTotalByAccountNumberAndNotId(String accountNumber, Long id);

}
