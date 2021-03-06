package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.web.search.PayTempUploadDataSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface PayTempUploadDataDao extends IDAO<PayTempUploadData> {

    public List<PayTempUploadData> getAllDataByParam(PayTempUploadDataSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(PayTempUploadDataSearchParameter parameter);

    public PayTempUploadData getEntityByPkWithDetail(Long id);

    public Long getTotalByPaySalaryComponentId(Long paySalaryComponentId);
    
    public Long getTotalByPaySalaryCompIdAndEmpDataId(Long paySalaryComponentId, Long empDataId);
    
    public Long getTotalByPaySalaryCompIdAndEmpDataIdAndNotId(Long paySalaryComponentId, Long empDataId, Long id);

    public Double getTotalSalaryByPaySalaryComponentId(Long paySalaryComponentId);

    public void deleteByPaySalaryComponentId(Long paySalaryComponentId);

    public PayTempUploadData getEntityByEmpIdAndComponentId(Long empId, Long componentId);

	public PayTempUploadData getEntityByNikAndComponentId(String nik, Long componentId);

}
