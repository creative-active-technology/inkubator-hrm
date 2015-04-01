package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.web.search.GolonganJabatanSearchParameter;


/**
*
* @author rizkykojek
*/
public class GolonganJabatanLazyDataModel extends LazyDataModel<GolonganJabatan> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(GolonganJabatanLazyDataModel.class);
    private final GolonganJabatanSearchParameter parameter;
    private final GolonganJabatanService golJabatanService;
    private List<GolonganJabatan> golonganJabatans = new ArrayList<>();
    private Integer total;

    public GolonganJabatanLazyDataModel(GolonganJabatanSearchParameter parameter, GolonganJabatanService golJabatanService) {
        this.parameter = parameter;
        this.golJabatanService = golJabatanService;
    }

    @Override
    public List<GolonganJabatan> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("paySalaryGrade");
	        }
	        
	        golonganJabatans = golJabatanService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(golJabatanService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return golonganJabatans;
    }

    @Override
    public Object getRowKey(GolonganJabatan golonganJabatan) {
        return golonganJabatan.getId();
    }

    @Override
    public GolonganJabatan getRowData(String id) {
        for (GolonganJabatan golonganJabatan : golonganJabatans) {
            if (id.equals(String.valueOf(golonganJabatan.getId()))) {
                return golonganJabatan;
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
