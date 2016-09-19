/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web.ext;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import org.apache.log4j.Logger;
import send.sms.az.dao.UserDao;
import send.sms.az.dao.UserLogDao;
import send.sms.az.model.Right;
import send.sms.az.model.User;
import send.sms.az.model.UserLogger;
import send.sms.az.util.FacesUtil;
import send.sms.az.util.LogTypes;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "loginCnt")
@SessionScoped
public class LoginController implements Serializable {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    private User user = new User();
    private UserLogger userLogger = new UserLogger();
    private int timeout;

    public void checkLogin() throws IOException {
        LOG.info(user.getLogin() + " " + user.getPassw());

        try {
            if (user == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Istifadəçi adı və ya şifrəni daxil edin!"));
            } else {
                LOG.info(UserDao.checkLogin(user).getUser_id().toString());
                if (UserDao.checkLogin(user).getUser_id().compareTo(BigDecimal.ZERO) == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Istifadəçi adı və ya şifrə yalnış!"));
                } else {
                    UserDao.getUserBalance(user);
                    UserDao.getUserRights(user);
//                    for (Map.Entry<String, Right> entrySet : user.getRightsMap().entrySet()) {
//                        Object key = entrySet.getKey();
//                        Object value = entrySet.getValue();
//                        LOG.info(value + " " + key);
//
//                    }

//                    LOG.info(user.getRights().size());
                    userLogger.setType(LogTypes.LOGIN.toString());
                    userLogger.setDescription(user.toString());
                    userLogger.setUserID(user.getUser_id());
                    UserLogDao.insertUserLog(userLogger);
                    FacesContext.getCurrentInstance().getExternalContext().redirect(
                            FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/index.xhtml");//
                }
                user.setPassw("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            userLogger.setType(LogTypes.LOGINERROR.toString());
            userLogger.setUserID(user.getUser_id());
            userLogger.setDescription(e.toString());
            UserLogDao.insertUserLog(userLogger);
        }
    }

    public boolean checkAccessFP(String privName) {
        Object isNull = user.getRightsMap().get(privName);
        if (isNull == null) {
            return false;
        } else {
            return true;
        }
//        LOG.info(isNull + " " + privName);
    }

    public void logOUT() {
        LOG.info("logOUT");
        user = new User();
        FacesUtil.menuCloseOut();
    }

    public void redHome() {
        LOG.info("redHome");
        FacesUtil.redToMenu();
    }

    public void hasAccess(ComponentSystemEvent event) {
        if (user != null && user.getUser_id().compareTo(BigDecimal.ZERO) == 0) {
            FacesUtil.menuCloseOut();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
