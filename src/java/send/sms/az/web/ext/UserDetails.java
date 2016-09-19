/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web.ext;

import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import send.sms.az.model.UserLogger;

/**
 *
 * @author Mobby
 */
public class UserDetails implements Serializable {

    @ManagedProperty(value = "#{loginCnt}")
    public LoginController loginCnt;
    public UserLogger userLogger = new UserLogger();

    public LoginController getLoginCnt() {
        return loginCnt;
    }

    public void setLoginCnt(LoginController loginCnt) {
        this.loginCnt = loginCnt;
    }

    public UserLogger getUserLogger() {
        return userLogger;
    }

    public void setUserLogger(UserLogger userLogger) {
        this.userLogger = userLogger;
    }

}
