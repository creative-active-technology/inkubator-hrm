package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.AdmonitionType;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.AdmonitionTypeService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.model.RecruitAgreementNoticeViewModel;
import com.inkubator.hrm.web.search.AdmonitionTypeSearchParameter;
import com.inkubator.hrm.web.search.RecruitAgreementNoticeSearchParameter;

public class RecruitAgreementNoticeLazyDataModel extends LazyDataModel<RecruitAgreementNoticeViewModel> implements Serializable{
	private static final Logger LOGGER = Logger.getLogger(RecruitAgreementNoticeLazyDataModel.class);
    private final RecruitAgreementNoticeSearchParameter searchParameter;
    private final EmpDataService empDataService;
    private List<RecruitAgreementNoticeViewModel> listEmpData = new ArrayList<>();
    private Integer total;
    
	public RecruitAgreementNoticeLazyDataModel(RecruitAgreementNoticeSearchParameter searchParameter, EmpDataService empDataService) {
		this.searchParameter = searchParameter;
		this.empDataService = empDataService;
	}
    
	@Override
    public List<RecruitAgreementNoticeViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		LOGGER.info("Step Load Lazy data Model");

        try {
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            }else{
                order = Order.desc("firstName");
            }
            listEmpData = empDataService.getAllEmployeeForRecruitAggrementNoticeWithNativeQuery(searchParameter, first, pageSize, order);
            total = Integer.parseInt(String.valueOf(empDataService.getTotalAllEmployeeForRecruitAggrementNoticeWithNativeQuery(searchParameter)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    
    LOGGER.info("Success Load Lazy data Model");

    setPageSize(pageSize);
    setRowCount(total);
    return listEmpData;
    }

    @Override
    public Object getRowKey(RecruitAgreementNoticeViewModel agreementNoticeViewModel) {
        return agreementNoticeViewModel.getEmployeeId();
    }

    @Override
    public RecruitAgreementNoticeViewModel getRowData(String id) {
        for (RecruitAgreementNoticeViewModel agreementNoticeViewModel : listEmpData) {
            if (id.equals(String.valueOf(agreementNoticeViewModel.getEmployeeId()))) {
                return agreementNoticeViewModel;
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
