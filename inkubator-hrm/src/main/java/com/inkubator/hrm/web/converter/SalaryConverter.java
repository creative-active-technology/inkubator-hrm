package com.inkubator.hrm.web.converter;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.NumberFormatter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.text.NumberFormat;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Deni Husni FR
 */
@FacesConverter(value = "salaryConverter")
public class SalaryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        String dataEncripted = (String) obj;
        String dataDecripted = AESUtil.getAESDescription(dataEncripted, HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
        NumberFormat nb = NumberFormatter.getNumberFormatStatic(CommonUtilConstant.NUMBER_FORMAT_NUMBER_TYPE, new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        return nb.format(Double.parseDouble(dataDecripted));

    }

}
