package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.web.search.KlasifikasiKerjaSearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
*
* @author Taufik Hidayat
*/
public class KlasifikasiKerjaLazyDataModel extends LazyDataModel<KlasifikasiKerja> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(KlasifikasiKerjaLazyDataModel.class);
    private final KlasifikasiKerjaSearchParameter klasifikasiKerjaSearchParameter;
    private final KlasifikasiKerjaService klasifikasiKerjaService;
    private List<KlasifikasiKerja> klasifikasiKerjas = new ArrayList<>();
    private Integer total;

    public KlasifikasiKerjaLazyDataModel(KlasifikasiKerjaSearchParameter klasifikasiKerjaSearchParameter, KlasifikasiKerjaService klasifikasiKerjaService) {
        this.klasifikasiKerjaSearchParameter = klasifikasiKerjaSearchParameter;
        this.klasifikasiKerjaService = klasifikasiKerjaService;
    }

    @Override
    public List<KlasifikasiKerja> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
          LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	klasifikasiKerjas = klasifikasiKerjaService.getByParam(klasifikasiKerjaSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(klasifikasiKerjaService.getTotalByParam(klasifikasiKerjaSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	klasifikasiKerjas = klasifikasiKerjaService.getByParam(klasifikasiKerjaSearchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(klasifikasiKerjaService.getTotalByParam(klasifikasiKerjaSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	klasifikasiKerjas = klasifikasiKerjaService.getByParam(klasifikasiKerjaSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(klasifikasiKerjaService.getTotalByParam(klasifikasiKerjaSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return klasifikasiKerjas;
    }

    @Override
    public Object getRowKey(KlasifikasiKerja klasifikasiKerja) {
        return klasifikasiKerja.getId();
    }

    @Override
    public KlasifikasiKerja getRowData(String id) {
        for (KlasifikasiKerja klasifikasiKerja : klasifikasiKerjas) {
            if (id.equals(String.valueOf(klasifikasiKerja.getId()))) {
                return klasifikasiKerja;
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
