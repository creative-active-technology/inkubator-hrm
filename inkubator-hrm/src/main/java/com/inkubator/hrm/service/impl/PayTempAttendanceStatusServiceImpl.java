package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.PayTempAttendanceStatusDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.service.PayTempAttendanceStatusService;
import com.inkubator.hrm.web.model.PaySalaryUploadFileModel;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import com.inkubator.webcore.util.FacesIO;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "payTempAttendanceStatusService")
@Lazy
public class PayTempAttendanceStatusServiceImpl extends IServiceImpl implements
        PayTempAttendanceStatusService {

    @Autowired
    private WtPeriodeDao wtPeriodeDao;

    @Autowired
    private PayTempAttendanceStatusDao payTempAttendanceStatusDao;
    
    @Autowired
    private EmpDataDao empDataDao;
    
    @Autowired
    private FacesIO facesIO;

    @Override
    public void delete(PayTempAttendanceStatus arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PayTempAttendanceStatus> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PayTempAttendanceStatus> getAllData(Boolean arg0)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PayTempAttendanceStatus> getAllData(Integer arg0)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PayTempAttendanceStatus> getAllData(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PayTempAttendanceStatus> getAllDataPageAble(int arg0, int arg1,
            Order arg2) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PayTempAttendanceStatus> getAllDataPageAbleIsActive(int arg0,
            int arg1, Order arg2, Boolean arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PayTempAttendanceStatus> getAllDataPageAbleIsActive(int arg0,
            int arg1, Order arg2, Integer arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PayTempAttendanceStatus> getAllDataPageAbleIsActive(int arg0,
            int arg1, Order arg2, Byte arg3) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntityByPkIsActive(String arg0,
            Integer arg1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntityByPkIsActive(String arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntityByPkIsActive(String arg0,
            Boolean arg1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntityByPkIsActive(Integer arg0,
            Integer arg1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntityByPkIsActive(Integer arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntityByPkIsActive(Integer arg0,
            Boolean arg1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntityByPkIsActive(Long arg0, Integer arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntityByPkIsActive(Long arg0, Byte arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntityByPkIsActive(Long arg0, Boolean arg1)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntiyByPK(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntiyByPK(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus getEntiyByPK(Long arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getTotalDataIsActive(Boolean arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getTotalDataIsActive(Integer arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getTotalDataIsActive(Byte arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(PayTempAttendanceStatus arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus saveData(PayTempAttendanceStatus arg0)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdate(PayTempAttendanceStatus arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus saveOrUpdateData(PayTempAttendanceStatus arg0)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void softDelete(PayTempAttendanceStatus arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(PayTempAttendanceStatus arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PayTempAttendanceStatus updateData(PayTempAttendanceStatus arg0)
            throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PayTempAttendanceStatus> getByParam(String parameter,
            PayTempAttendanceStatusModel payTempAttendanceStatusModel,
            int firstResult, int maxResults, Order order) throws Exception {
        return new ArrayList<PayTempAttendanceStatus>();
    }

    @Override
    public List<PayTempAttendanceStatus> getByWtPeriodeWhereComponentPayrollIsActive(
            PayTempAttendanceStatusModel payTempAttendanceStatusModel)
            throws Exception {
        return new ArrayList<PayTempAttendanceStatus>();
    }

    @Override
    public Long getTotalResourceTypeByParam(String parameter,
            PayTempAttendanceStatusModel payTempAttendanceStatusModel)
            throws Exception {
        // TODO Auto-generated method stub
        return 0l;
    }
    
     @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executeBatchFileUpload(PayTempAttendanceStatusModel model) throws Exception {
        Boolean isInsertable = this.payTempAttendanceStatusDao.getAllByNik(model.getNik()).isEmpty();

        //skip jika data sudah ada di database(tidak boleh duplikat)
        if (isInsertable) {
            EmpData empData = empDataDao.getEntityByNik(model.getNik());            
            if (empData != null) {
                PayTempAttendanceStatus entity = new PayTempAttendanceStatus();
                entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));                
                entity.setEmpData(empData);
                entity.setTotalAttendance(Integer.parseInt(model.getTotalAttendance()));                
                entity.setCreatedBy(model.getCreatedBy());
                entity.setCreatedOn(new Date());
                this.payTempAttendanceStatusDao.save(entity);
            }
        }

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateFileAndDeleteData(UploadedFile documentFile) throws Exception {
        String uploadPath = this.getUploadPath(documentFile);

        if (documentFile != null) {
            //remove old file
            Files.deleteIfExists(Paths.get(uploadPath));

            //added new file            
            facesIO.transferFile(documentFile);
            File file = new File(facesIO.getPathUpload() + documentFile.getFileName());
            file.renameTo(new File(uploadPath));
        }

        //payTempUploadDataDao.deleteByPaySalaryComponentId(paySalaryComponentId);
        return uploadPath;
    }
    
    private String getUploadPath( UploadedFile documentFile) {
        String extension = StringUtils.substringAfterLast(documentFile.getFileName(), ".");
        String uploadPath = facesIO.getPathUpload() + "paysalaryupload_." + extension;
        return uploadPath;
    }

}
