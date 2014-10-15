/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.JabatanMajor;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
public interface JabatanMajorDao extends IDAO<JabatanMajor>{
    public List<JabatanMajor> getAllDataByJabatanId(Long jabatanId);
}
