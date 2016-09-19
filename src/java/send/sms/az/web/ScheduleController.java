/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import send.sms.az.dao.BulkSMSDao;
import send.sms.az.model.ScheduledModel;
import send.sms.az.model.ScheduledSMS;
import send.sms.az.util.FacesUtil;

/**
 *
 * @author Mobby
 */
@ManagedBean(name = "scheduleCnt")
@ViewScoped
public class ScheduleController implements Serializable {

    private static final Logger logger = Logger.getLogger(ScheduleController.class.getName());
    private ScheduledModel scheduledModel = new ScheduledModel();
    private BulkSMSDao bulkSMSDao;
 
    public ScheduleController() {
        bulkSMSDao = new BulkSMSDao();
        scheduledModel.setScheduledList(bulkSMSDao.scheduledSMSList(scheduledModel));
    }
    
    
    public void search() {
        logger.info("search " + scheduledModel.getToDate() + " " + scheduledModel.getFromDate());
        boolean notValid = false;
        if (scheduledModel.getFromDate() == null) {
            FacesUtil.addErrorMessage("Başlanğıc tarixi daxil et");
            notValid = true;
        }
        if (scheduledModel.getToDate() == null) {
            FacesUtil.addErrorMessage("Son tarixi daxil et");
            notValid = true;
        }
        scheduledModel.setSearch(!notValid);
        if (!notValid) {
            List<ScheduledSMS> list = bulkSMSDao.scheduledSMSList(scheduledModel);
            scheduledModel.setScheduledList(list);
            scheduledModel.setSearch(notValid);
        }

    }



    public void searchBeetweenDate() {

    }

    public ScheduledModel getScheduledModel() {
        return scheduledModel;
    }

    public void setScheduledModel(ScheduledModel scheduledModel) {
        this.scheduledModel = scheduledModel;
    }

}
