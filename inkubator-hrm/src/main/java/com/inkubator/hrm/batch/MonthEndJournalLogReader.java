package com.inkubator.hrm.batch;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.web.model.SalaryJournalModel;

/**
*
* @author rizkykojek
*/
public class MonthEndJournalLogReader implements ItemReader<SalaryJournalModel> {

	private List<SalaryJournalModel> listSalaryJournal;
	
	public MonthEndJournalLogReader(PayTempKalkulasiService payTempKalkulasiService, String locale) throws Exception{
		long total = payTempKalkulasiService.getTotalPayTempKalkulasiForSalaryJournal("");
		listSalaryJournal = payTempKalkulasiService.getByParamForSalaryJournal(null, 0, (int) total, Order.desc("id"), locale);
	}
	
	@Override
	public SalaryJournalModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		SalaryJournalModel object = null;
		
		if(listSalaryJournal.size()>0) {
			object = listSalaryJournal.remove(0);
		}
		
		return object;
	}
	
}
