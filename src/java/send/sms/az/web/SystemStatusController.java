/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import send.sms.az.dao.SystemStatusDao;
import send.sms.az.model.ext.SystemModel;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "systemCnt")
@ViewScoped
public class SystemStatusController extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(SystemStatusController.class.getName());
    private SystemModel model = new SystemModel();
    private SystemStatusDao statusDao;

    public SystemStatusController() {
        statusDao = new SystemStatusDao();
        getDetails();
    }

    public void getDetails() {
        LOG.info("setting data from dao");
        systemLogs();
    }

    public void systemLogs() {
        model.setAppLogErrors(statusDao.getAppLogErrors());
        model.setIncomingNotProcessed(statusDao.getIncomingNotProcessed());
        model.setLastBlackListEntryDate(statusDao.getLastBlackListEntryDate());
        model.setLogErrorCount(statusDao.getLogErrorCount());
        model.setOutgoingInError(statusDao.getOutgoingInError());
        model.setOutgoingNotProcessed(statusDao.getOutgoingNotProcessed());
    }

    public SystemModel getModel() {
        return model;
    }

    public void setModel(SystemModel model) {
        this.model = model;
    }

}
