package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.service.LeaveImplementationService;
import com.inkubator.hrm.service.LogListOfTransferService;
import com.inkubator.hrm.web.model.ReportLeaveDataViewModel;
import com.inkubator.hrm.web.search.ReportBankTransferDataSearchParameter;
import com.inkubator.hrm.web.search.ReportLeaveDataSearchParameter;


/**
*
* @author Ahmad Mudzakkir Amal
*/
public class ReportLeaveDataLazyDataModel extends LazyDataModel<ReportLeaveDataViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ReportLeaveDataLazyDataModel.class);
    private final LeaveImplementationService leaveImplementationService;
    private List<ReportLeaveDataViewModel> list = new ArrayList<>();
    private Integer total;
    private ReportLeaveDataSearchParameter parameter;

    public ReportLeaveDataLazyDataModel(LeaveImplementationService leaveImplementationService, ReportLeaveDataSearchParameter parameter) {
        this.leaveImplementationService = leaveImplementationService;
        this.parameter = parameter;
    }

    @Override
    public List<ReportLeaveDataViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	        	orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("firstName");
	        }
	        
	        //Pengecekan ini untuk mencegah data di load ketika pertama kali membuka halaman, karena di requirement dari FSD-nya, 
	        //di awal membuka halaman, tabel grid-nya harus kosong, sehingga di cek nya by startDate / endDate nya,
	        //jika null berarti itu baru pertama buka halaman, cukup return empty list
	        if(parameter.getStartDate() == null || parameter.getEndDate() == null){
	        	total = 0;
	        }else{
	        	list = leaveImplementationService.getAllDataLeaveReport(parameter, first, pageSize, orderable);
		        total = Integer.parseInt(String.valueOf(leaveImplementationService.getTotalLeaveDataReport(parameter)));            
	        }
	        
	        LOGGER.info("Success Load Lazy data Model");

        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }
        
        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }

    @Override
    public Object getRowKey(ReportLeaveDataViewModel model) {
        return model.getId();
    }

    @Override
    public ReportLeaveDataViewModel getRowData(String id) {
        for (ReportLeaveDataViewModel model : list) {
            if (id.equals(String.valueOf(model.getId()))) {
                return model;
            }
        }
        return null;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
