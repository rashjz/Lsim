/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.convertor;

import java.math.BigDecimal;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

//import org.primefaces.showcase.domain.Theme;
//import org.primefaces.showcase.service.ThemeService;
import send.sms.az.dao.RightsDao;
import send.sms.az.model.Right;

@FacesConverter("roleConverter")
public class RightConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(RightConverter.class.getName());

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
//                ThemeService service = (ThemeService) fc.getExternalContext().getApplicationMap().get("themeService");
//                LOG.info("Right id from convertor 30 " + RightsDao.getRightsList().get(new BigDecimal(value)).toString());
                return RightsDao.getRight(new BigDecimal(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid role."));
            }
        } else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) { 
//            LOG.info(RightsDao.getRightsList().get(((Right) object).getId()).toString());
            return String.valueOf(((Right) object).getId());
        } else {
            return null;
        }
    }
}
