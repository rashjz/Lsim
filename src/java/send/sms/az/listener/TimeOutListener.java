/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.listener;

//import org.borislam.util.FacesUtil;
//import org.borislam.util.SecurityUtil;
import java.io.IOException; 
import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import send.sms.az.model.User;
import send.sms.az.util.FacesUtil;
import send.sms.az.web.ext.LoginController;

public class TimeOutListener implements PhaseListener {
    
    private static final Logger LOG = Logger.getLogger(TimeOutListener.class.getName());
    
    public void afterPhase(PhaseEvent event) {
        LOG.info("afterPhase invoked");
    }
    
    public void beforePhase(PhaseEvent event) {
        LOG.info("beforePhase started");
        LoginController timeoutSetting = (LoginController) FacesUtil.getManagedBean("loginCnt");
        LOG.info(timeoutSetting + " tmeoutSetting");
        FacesContext fc = FacesContext.getCurrentInstance();
        RequestContext rc = RequestContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) ec.getResponse();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
   
        if (timeoutSetting == null) {
            LOG.info("JSF Ajax Timeout Setting is not configured. Do Nothing!");
            return;
        }
        User user = timeoutSetting.getUser();
//        LOG.info(user.getLogin()); 
        
        
        if (user == null) { 
            // considered to be a Timeout case
//            LOG.info(user + " is null ");
            if (ec.isResponseCommitted()) {
                // redirect is not possible
                return;
            }
            
            try {
                
                if (((rc != null && RequestContext.getCurrentInstance().isAjaxRequest())|| (fc != null && fc.getPartialViewContext().isPartialRequest()))
                        && fc.getResponseWriter() == null&& fc.getRenderKit() == null) {
                    
                    response.setCharacterEncoding(request.getCharacterEncoding());
                    RenderKitFactory factory = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
                    RenderKit renderKit = factory.getRenderKit(fc,fc.getApplication().getViewHandler().calculateRenderKitId(fc));
                    ResponseWriter responseWriter= renderKit.createResponseWriter(response.getWriter(), null, request.getCharacterEncoding());
                    fc.setResponseWriter(responseWriter);
//                    LOG.info("timeOut url " + timeoutSetting.getTimeoutUrl());
                    ec.redirect(ec.getRequestContextPath() +"/login.xhtml");
                }
                
            } catch (IOException e) {
                LOG.info("Redirect to the specified page failed");
                throw new FacesException(e);
            }
        } else {
            return; //This is not a timeout case . Do nothing !
        }
    }
    
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
