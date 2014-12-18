package com.inkubator.hrm.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PayReceiverBankAccount;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.PayReceiverBankAccountService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.web.model.PayReceiverAccountModel;

/**
 *
 * @author rizkykojek
 */
public class PayReceiverAccountReader implements ItemReader<PayReceiverAccountModel> {
	
	private List<PayReceiverBankAccount> listObject;
	private PayTempKalkulasiService payTempKalkulasiService;
	
	public PayReceiverAccountReader(PayReceiverBankAccountService payReceiverBankAccountService) throws Exception{
		//get populated data
		listObject = payReceiverBankAccountService.getAllDataWithDetail();
	}
	
	@Override
	public PayReceiverAccountModel read() throws Exception, UnexpectedInputException,ParseException, NonTransientResourceException {
		PayReceiverAccountModel object = null;
		
		if(listObject.size() > 0){
			object = this.createModel(listObject.remove(0));
		}
		
		return object;
	}
	
	private PayReceiverAccountModel createModel(PayReceiverBankAccount payReceiverBankAccount){
		PayReceiverAccountModel model = new PayReceiverAccountModel();
		model.setNik(payReceiverBankAccount.getEmpData().getNik());
		model.setName(payReceiverBankAccount.getEmpData().getBioData().getFullName());
		model.setAccountName(payReceiverBankAccount.getBioBankAccount().getOwnerName());
		model.setAccountNumber(payReceiverBankAccount.getBioBankAccount().getAccountNumber());
		model.setBankName(payReceiverBankAccount.getBioBankAccount().getBank().getBankName());
		model.setPercent(payReceiverBankAccount.getPersen());
		
		//kalkulasi nominal yg diterima
		PayTempKalkulasi totalIncomeKalkulasi = payTempKalkulasiService.getEntityByEmpDataIdAndSpecificModelComponent(payReceiverBankAccount.getEmpData().getId(), HRMConstant.MODEL_COMP_TAKE_HOME_PAY);
		model.setNominal((totalIncomeKalkulasi.getNominal().doubleValue() * model.getPercent()) / 100);
		
		return model;
	}

	public PayTempKalkulasiService getPayTempKalkulasiService() {
		return payTempKalkulasiService;
	}

	public void setPayTempKalkulasiService(
			PayTempKalkulasiService payTempKalkulasiService) {
		this.payTempKalkulasiService = payTempKalkulasiService;
	}
	
	

}
