/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.listener;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author @rashjz
 */
@WebFilter
public class CacheListener implements PhaseListener, Filter {

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {

        FacesContext facesContext = event.getFacesContext();
        // HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        res.addHeader("Cache-Control", "no-cache,no-store,must-revalidate");//http 1.0
        res.addHeader("Progma", "no-cache");
        res.addHeader("Cache-Control", "no-store");//ie8 others
        res.addHeader("Cache-Control", "must-revalidate");
        res.setDateHeader("Expires", 0);//

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
