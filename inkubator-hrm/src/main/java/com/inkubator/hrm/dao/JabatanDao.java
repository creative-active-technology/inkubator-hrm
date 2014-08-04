/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface JabatanDao extends IDAO<Jabatan> {

    public List<Jabatan> getByParam(JabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalJabatanByParam(JabatanSearchParameter searchParameter);

    public Jabatan getJabatanByLevelOne(Integer level);

    public List<Jabatan> getJabatanByParentCode(String parentCode);

    public Jabatan getJabatanByIdWithDetail(Long id);

    public List<Jabatan> getJabatansByLevel(Integer level);

    public Jabatan getByIdWithJobDeskripsi(long id) throws Exception;

    public void saveAndMerge(Jabatan jabatan);
}
