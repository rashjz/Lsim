/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import send.sms.az.dao.OutgoingByUserDao;
import send.sms.az.model.OngoingSMSUser;
import send.sms.az.model.ext.TarifSearchModel;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author Mobby
 */
@ManagedBean(name = "onSMSreportCnt")
@ViewScoped
public class OngoingUserSMSCnt extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(OngoingUserSMSCnt.class.getName());
    
    private List<OngoingSMSUser> osmsus = new ArrayList<>();
    private TarifSearchModel searchModel = new TarifSearchModel();
    private OutgoingByUserDao byUserDao;

    public OngoingUserSMSCnt() {
        byUserDao = new OutgoingByUserDao();
        searchModel.setStartDate(new Date());
        searchModel.setEndDate(new Date());
    }

    public void ongoingStatistic() {
        osmsus = byUserDao.ongoingSMSbyUser(searchModel);
    }

    public List<OngoingSMSUser> getOsmsus() {
        return osmsus;
    }

    public void setOsmsus(List<OngoingSMSUser> osmsus) {
        this.osmsus = osmsus;
    }

    public TarifSearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(TarifSearchModel searchModel) {
        this.searchModel = searchModel;
    }

}
