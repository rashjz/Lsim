/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "lang")
@SessionScoped
public class LanguageController implements Serializable {

    private static final Logger LOG = Logger.getLogger(LanguageController.class.getName());
    private String language = "ru";

    public String actionAZ() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("az"));
        language = "az";
        LOG.info(FacesContext.getCurrentInstance().getViewRoot().getLocale() + " " + FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true");
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public String actionRU() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("ru"));
        language = "ru";
        LOG.info(FacesContext.getCurrentInstance().getViewRoot().getLocale() + " " + FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true");
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public String actionEN() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
        language = "en";
        LOG.info(FacesContext.getCurrentInstance().getViewRoot().getLocale() + " " + FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true");
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

//    protected void refreshPage() {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        String refreshpage = fc.getViewRoot().getViewId();
//        ViewHandler ViewH = fc.getApplication().getViewHandler();
//        UIViewRoot UIV = ViewH.createView(fc, refreshpage);
//        UIV.setViewId(refreshpage);
//        fc.setViewRoot(UIV);
//    }

}
