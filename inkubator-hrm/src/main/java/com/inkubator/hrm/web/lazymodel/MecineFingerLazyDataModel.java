package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.web.search.MecineFingerSearchParameter;
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
public class MecineFingerLazyDataModel extends LazyDataModel<MecineFinger> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(MecineFingerLazyDataModel.class);
    private final MecineFingerSearchParameter mecineFingerSearchParameter;
    private final MecineFingerService mecineFingerService;
    private List<MecineFinger> listMecineFinger = new ArrayList<>();
    private Integer total;

    public MecineFingerLazyDataModel(MecineFingerSearchParameter mecineFingerSearchParameter, MecineFingerService mecineFingerService) {
        this.mecineFingerSearchParameter = mecineFingerSearchParameter;
        this.mecineFingerService = mecineFingerService;
    }

 
    @Override
    public List<MecineFinger> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    listMecineFinger = mecineFingerService.getByParam(mecineFingerSearchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(mecineFingerService.getTotalByParam(mecineFingerSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    listMecineFinger = mecineFingerService.getByParam(mecineFingerSearchParameter, first, pageSize, Order.desc(sortField));
                    total = Integer.parseInt(String.valueOf(mecineFingerService.getTotalByParam(mecineFingerSearchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                listMecineFinger = mecineFingerService.getByParam(mecineFingerSearchParameter, first, pageSize, Order.asc("name"));
                total = Integer.parseInt(String.valueOf(mecineFingerService.getTotalByParam(mecineFingerSearchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return listMecineFinger;
    }

    @Override
    public Object getRowKey(MecineFinger mecineFinger) {
        return mecineFinger.getId();
    }

    @Override
    public MecineFinger getRowData(String id) {
        for (MecineFinger mecineFinger : listMecineFinger) {
            if (id.equals(String.valueOf(mecineFinger.getId()))) {
                return mecineFinger;
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
