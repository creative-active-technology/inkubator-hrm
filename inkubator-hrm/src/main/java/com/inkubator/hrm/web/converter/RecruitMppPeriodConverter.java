/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.ServiceWebUtil;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;
import static org.fusesource.hawtbuf.Buffer.string;

/**
 *
 * @author Deni
 */
@FacesConverter(value = "recruitMppPeriodConverter")
public class RecruitMppPeriodConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(GolonganJabatanConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        RecruitMppPeriodService recruitMppPeriodService = (RecruitMppPeriodService) ServiceWebUtil.getService("recruitMppPeriodService");
        Object object = null;
        try {
            object = recruitMppPeriodService.getEntiyByPK(Long.parseLong(value));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {       
        RecruitMppPeriodService recruitMppPeriodService = (RecruitMppPeriodService) ServiceWebUtil.getService("recruitMppPeriodService");
        RecruitMppPeriod period = null;
        try {
            period = recruitMppPeriodService.getEntiyByPK((Long) value);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        String periodeStart = DateFormatter.getDateAsStringActiveLocale(period.getPeriodeStart(), "dd MMMM yyyy", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        String periodeEnd = DateFormatter.getDateAsStringActiveLocale(period.getPeriodeEnd(), "dd MMMM yyyy", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        return period.getName() + "  (" + periodeStart + " - " + periodeEnd + ") ";
    }

}
