package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
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
 * @author WebGenX
 */
public class AnnouncementLazyDataModel extends LazyDataModel<Announcement> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(AnnouncementLazyDataModel.class);
    private final AnnouncementSearchParameter announcementSearchParameter;
    private final AnnouncementService announcementService;
    private List<Announcement> announcements = new ArrayList<>();
    private Integer totalData;

    public AnnouncementLazyDataModel(AnnouncementSearchParameter searchParameter, AnnouncementService announcementService) {
        this.announcementSearchParameter = searchParameter;
        this.announcementService = announcementService;
    }

    @Override
    public List<Announcement> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("periodeStartDate");
	        }
	        
	        announcements = announcementService.getByParam(announcementSearchParameter, first, pageSize, orderable);
	        totalData = Integer.parseInt(String.valueOf(announcementService.getTotalByParam(announcementSearchParameter)));	
	        LOGGER.info("Success Load Lazy data Model");
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }
        
        setPageSize(pageSize);
        setRowCount(totalData);
        return announcements;
    }

    @Override
    public Object getRowKey(Announcement announcement) {
        return announcement.getId();
    }

    @Override
    public Announcement getRowData(String id) {
        for (Announcement announcement : announcements) {
            if (id.equals(String.valueOf(announcement.getId()))) {
                return announcement;
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
