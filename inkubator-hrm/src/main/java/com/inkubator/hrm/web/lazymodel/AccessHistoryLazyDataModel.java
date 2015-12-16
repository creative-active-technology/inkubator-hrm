package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.service.AccessHistoryService;
import com.inkubator.hrm.web.search.AccessHistorySearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
public class AccessHistoryLazyDataModel extends LazyDataModel<RiwayatAkses> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(AccessHistoryLazyDataModel.class);
    private final AccessHistorySearchParameter searchParameter;
    private final AccessHistoryService service;
    private List<RiwayatAkses> riwayatAksesList = new ArrayList<>();
    private Integer total;

    public AccessHistoryLazyDataModel(AccessHistorySearchParameter searchParameter, AccessHistoryService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }

    @Override
    public List<RiwayatAkses> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step load lazy data model");
        try {
            Order order = null;
            if (sortField != null) {
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("dateAccess");
            }
            riwayatAksesList = service.getByParam(searchParameter, first, pageSize, order);
         
            total = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
     
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success load lazy data model");

        setPageSize(pageSize);
        setRowCount(total);
        return riwayatAksesList;
    }

    @Override
    public Object getRowKey(RiwayatAkses riwayatAkses) {
        return riwayatAkses.getId();
    }

    @Override
    public RiwayatAkses getRowData(String id) {
        for (RiwayatAkses riwayatAkses : riwayatAksesList) {
            if (id.equals(String.valueOf(riwayatAkses.getId()))) {
                return riwayatAkses;
            }
        }
        return null;
    }

	
	
	
}
