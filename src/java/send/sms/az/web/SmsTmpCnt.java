/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import send.sms.az.dao.SMSTemplDao;
import send.sms.az.model.sms_temp;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "smstmpCnt")
@ViewScoped
public class SmsTmpCnt implements Serializable {

    private static final Logger LOG = Logger.getLogger(SmsTmpCnt.class.getName());

    private List<sms_temp> listSms;
    private sms_temp smstmp = new sms_temp();
    private SMSTemplDao templDao;

    public SmsTmpCnt() {
        templDao = new SMSTemplDao();
        listSms = templDao.getSMStempList();
    }

    public void listener() {
        LOG.info(smstmp.getId().toString());
    }

    public void newTmp() {
        smstmp = new sms_temp();
    }

    public void save() {
        LOG.info("save invoked" + smstmp);
        if (smstmp.getId().compareTo(BigDecimal.ZERO) == 0) {
            smstmp = templDao.insSMStemp(smstmp);
            listSms.add(smstmp);
        } else {
            //update
            smstmp = templDao.updSMStemp(smstmp);
        }
    }

    public void delete() {
        LOG.info("delete invoked" + smstmp);
        smstmp = templDao.deleteSMStemp(smstmp);
    }

    public sms_temp getSmstmp() {
        return smstmp;
    }

    public void setSmstmp(sms_temp smstmp) {
        this.smstmp = smstmp;
    }

    public List<sms_temp> getListSms() {
        return listSms;
    }

    public void setListSms(List<sms_temp> listSms) {
        this.listSms = listSms;
    }

}
