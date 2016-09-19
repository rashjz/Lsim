package send.sms.az.listener;

import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author @rashjz
 */
@WebListener()
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Logger.getLogger(this.getClass().getName()).info("LSIM onCreate session id:" + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Logger.getLogger(this.getClass().getName()).info("LSIM onDestroy session id:" + se.getSession().getId());
    }
}
