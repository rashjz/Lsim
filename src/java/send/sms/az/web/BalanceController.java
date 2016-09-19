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
import send.sms.az.dao.BalanceDao;
import send.sms.az.dao.RoleDao;
import send.sms.az.model.Balance;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "balanceCnt")
@ViewScoped
public class BalanceController implements Serializable {

    private static final Logger LOG = Logger.getLogger(BalanceController.class.getName());

    private List<Balance> balanceList;
    private BalanceDao balanceDao;
    private Balance balance = new Balance();

    public BalanceController() {
        balanceDao = new BalanceDao();
        balanceList = balanceDao.balanceList();
    }

    public void listener() {
        LOG.info(balance.getId().toString());
    }

    public void save() {
        LOG.info("save invoked" + balance);
        if (balance.getId().compareTo(BigDecimal.ZERO) != 0) {
            balanceDao.updateBalance(balance);
        }
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public List<Balance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<Balance> balanceList) {
        this.balanceList = balanceList;
    }

}
