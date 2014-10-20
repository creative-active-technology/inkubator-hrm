/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.JabatanFakulty;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
public interface JabatanFakultyDao extends IDAO<JabatanFakulty>{
    public List<JabatanFakulty> getAllDataByJabatanId(Long jabatanId);
}
