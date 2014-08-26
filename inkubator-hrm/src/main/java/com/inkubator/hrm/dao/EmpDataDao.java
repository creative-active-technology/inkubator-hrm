/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface EmpDataDao extends IDAO<EmpData> {

    public Long getTotalByGender(Integer gender);

    public Long getTotalByAgeBetween(Date startDate, Date endDate);

    public Long getTotalByAgeLessThan(Date date);

    public Long getTotalByAgeMoreThan(Date date);

    public Long getTotalByDepartmentId(Long departmentId);

    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalEmpDataByParam(EmpDataSearchParameter searchParameter);

    public EmpData getByEmpIdWithDetail(long id);

    public EmpData getByBioDataWithDepartment(long id);

    public Long getTotalByNIKandId(String nik, Long id);
    
    public Long getTotalByNIK(String nik);
    
    public List<EmpData> getAllDataWithRelation();

	public List<EmpData> getAllDataByNameOrNik(String param);
	
	public EmpData getByIdWithDetail(long id);

}
