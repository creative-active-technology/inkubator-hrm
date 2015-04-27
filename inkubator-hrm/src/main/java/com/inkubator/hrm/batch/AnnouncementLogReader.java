package com.inkubator.hrm.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;

/**
*
* @author rizkykojek
*/
public class AnnouncementLogReader implements ItemReader<EmpData> {

	private List<EmpData> empDatas;
	
	public AnnouncementLogReader(EmpDataService empDataService, String announcementId) throws Exception{
		empDatas = empDataService.getAllDataByAnnouncementId(Long.parseLong(announcementId));		;
	}
	
	@Override
	public EmpData read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		EmpData object = null;
		
		if(empDatas.size()>0) {
			object = empDatas.remove(0);
		}
		
		return object;
	}
	
}
