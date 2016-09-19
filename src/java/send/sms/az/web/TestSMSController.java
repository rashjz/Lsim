/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import send.sms.az.dao.SendTestSMSDao;
import send.sms.az.model.IncomingSMS;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author Mobby
 */
@ManagedBean(name = "testSMSCnt")
@ViewScoped
public class TestSMSController extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(TestSMSController.class.getName());
    private IncomingSMS isms = new IncomingSMS();
    private SendTestSMSDao sMSDao;

    public TestSMSController() {
        sMSDao = new SendTestSMSDao();
    }

    public void sendTestSMS() {
        LOG.info("sendTestSMS " + isms.toString());
        sMSDao.insertTestSMS(isms);
    }

    public IncomingSMS getIsms() {
        return isms;
    }

    public void setIsms(IncomingSMS isms) {
        this.isms = isms;
    }

}
