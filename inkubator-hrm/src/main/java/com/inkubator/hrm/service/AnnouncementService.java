package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.web.model.AnnouncementModelJson;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author WebGenX
 */
public interface AnnouncementService extends IService<Announcement>, BaseApprovalService {

    public List<Announcement> getByParam(AnnouncementSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(AnnouncementSearchParameter searchParameter) throws Exception;
    
    public String saveWithApproval(Announcement entity, UploadedFile attachmentFile, List<Long> listEmployeeTypeId, List<Long> listGolonganJabatanId, List<Long> listUnitKerja) throws Exception;
    
    public String saveWithRevised(Announcement entity, UploadedFile attachmentFile, List<Long> listEmployeeTypeId, List<Long> listGolonganJabatanId, List<Long> listUnitKerja, Long approvalActivityId) throws Exception;
    
    public String save(AnnouncementModelJson announcementModelJson, boolean isBypassApprovalChecking) throws Exception;
}
