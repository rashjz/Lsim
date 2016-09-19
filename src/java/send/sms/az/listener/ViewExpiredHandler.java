/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.listener;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class ViewExpiredHandler extends ExceptionHandlerWrapper {

    private static final Logger logger = Logger.getLogger(ViewExpiredHandler.class.getName());

    private ExceptionHandler wrapped;

    public ViewExpiredHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
//        logger.info("handle()");
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable t = context.getException();
            if (t instanceof ViewExpiredException) {
//                String errorPageLocation = "WEB-INF/login.xhtml";
                ViewExpiredException vee = (ViewExpiredException) t;
                FacesContext facesContext = FacesContext.getCurrentInstance();
//                Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
//                NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
                try {
                    // Push some useful stuff to the request scope for use in the page
//                    requestMap.put("currentViewId", vee.getViewId());
//                    navigationHandler.handleNavigation(facesContext, null,  errorPageLocation);
                    facesContext.getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/login.xhtml");

                    facesContext.getPartialViewContext().setRenderAll(true);
                    facesContext.renderResponse();
                } catch (IOException ex) {
                    Logger.getLogger(ViewExpiredHandler.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    i.remove();
                }
            }
        }

        // At this point, the queue will not contain any ViewExpiredEvents. Therefore, let the parent handle them.
        getWrapped().handle();
    }
}
