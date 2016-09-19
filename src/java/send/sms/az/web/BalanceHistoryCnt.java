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
import send.sms.az.dao.BalanceDao;
import send.sms.az.model.BalanceHistory;
import send.sms.az.model.ext.BalanceHistoryModel;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "balancehCnt")
@ViewScoped
public class BalanceHistoryCnt extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(BalanceHistoryCnt.class.getName());
    private List<BalanceHistory> balanceHistorys;
    private BalanceDao balanceDao;
    private BalanceHistoryModel model = new BalanceHistoryModel();

    public BalanceHistoryCnt() {
        balanceDao = new BalanceDao();
        searchHistory();
    }

    public void searchHistory() {
        balanceHistorys = balanceDao.balanceHistoryList(model);
    }

    public List<BalanceHistory> getBalanceHistorys() {
        return balanceHistorys;
    }

    public void setBalanceHistorys(List<BalanceHistory> balanceHistorys) {
        this.balanceHistorys = balanceHistorys;
    }

    public BalanceHistoryModel getModel() {
        return model;
    }

    public void setModel(BalanceHistoryModel model) {
        this.model = model;
    }

}
