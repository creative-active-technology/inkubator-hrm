/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.SchedulerConfigDao;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.service.SchedulerConfigInitService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author denifahri
 */
public class SchedulerConfigInitialisasiServiceImpl extends IServiceImpl implements SchedulerConfigInitService {

    @Autowired
    private SchedulerConfigDao schedulerConfigDao;
    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;
    @Override
    public void initMethode() throws Exception {
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                //PUT YOUR CALL TO SERVICE HERE Canot using @transacional in inint methode ... harus manual transaksi
                List<SchedulerConfig> dataToUpdateLastExecution = schedulerConfigDao.getAllWithIsTimeDiv(Boolean.TRUE);
                for (SchedulerConfig config : dataToUpdateLastExecution) {
                    Date now = new Date();
                    String nowString = new SimpleDateFormat("dd MM yyyy HH:mm").format(now);
                    try {
                        config.setLastExecution(new SimpleDateFormat("dd MM yyyy HH:mm").parse(nowString));
                    } catch (ParseException ex) {
                       LOGGER.error(ex, ex);
                    }
                    schedulerConfigDao.update(config);
                }
            }
        });

    }
}
