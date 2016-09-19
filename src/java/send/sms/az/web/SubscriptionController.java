/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import send.sms.az.dao.SubscriptionDao;
import send.sms.az.model.Subscribe;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "subscribeCnt")
@ViewScoped
public class SubscriptionController extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(SubscriptionController.class.getName());
    private SubscriptionDao dao;
    private List<Subscribe> subsList;
    private Subscribe subscribe = new Subscribe();

    public SubscriptionController() {
        dao = new SubscriptionDao();
        subsList = dao.subscriptionList();
    }

    public void listener() {
        LOG.info(subscribe.toString());
    }

    public void insertSubs() {
        LOG.info("insertSubs " + subscribe.toString());
        subscribe = new Subscribe();
    }

    public void updateSubs() {
        LOG.info("updateSubs " + subscribe.toString());
        if (subscribe != null && subscribe.getSubs_id().compareTo(BigDecimal.ZERO) == 0) {
            LOG.info("insertSubs");
            dao.insSubs(subscribe);
        } else if (subscribe != null && subscribe.getSubs_id().compareTo(BigDecimal.ZERO) != 0) {
            LOG.info("updateSubs");
            dao.updSubs(subscribe);
        }

    }

    public List<Subscribe> getSubsList() {
        return subsList;
    }

    public void setSubsList(List<Subscribe> subsList) {
        this.subsList = subsList;
    }

    public Subscribe getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Subscribe subscribe) {
        this.subscribe = subscribe;
    }

}
