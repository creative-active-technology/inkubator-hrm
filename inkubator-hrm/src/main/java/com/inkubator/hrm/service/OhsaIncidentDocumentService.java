package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.OhsaIncidentDocument;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface OhsaIncidentDocumentService extends IService<OhsaIncidentDocument> {

    public List<OhsaIncidentDocument> getListByOhsaIncidentId(Long ohsaIncidentId);

    public OhsaIncidentDocument getEntityByPKWithDetail(Integer id);

    public void save(OhsaIncidentDocument entity, UploadedFile documentFile) throws Exception;

    public void update(OhsaIncidentDocument entity, UploadedFile documentFile) throws Exception;

}
