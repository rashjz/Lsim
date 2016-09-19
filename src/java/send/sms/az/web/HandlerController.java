/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import send.sms.az.dao.HandlersDao;
import send.sms.az.model.Handler;
import send.sms.az.model.ext.HandlerModel;
import send.sms.az.util.FacesUtil;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "handlerCnt")
@ViewScoped
public class HandlerController extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(HandlerController.class.getName());
    private List<Handler> diameterList = new ArrayList<>();
    private HandlersDao handlersDao;
    private Handler handler = new Handler();
    private HandlerModel model = new HandlerModel();

    public HandlerController() {
        handlersDao = new HandlersDao();
        diameterList = handlersDao.handlerList();
    }

    public void updInsHandler() {
        LOG.info("updInsHandler " + handler);
        try {
            if (handler != null && handler.getHandler_id().compareTo(BigDecimal.ZERO) == 0) {
                LOG.info("updInsHandler " + handler.toString());
                handlersDao.insContent(handler);
                cancelHandler();
            } else if (handler != null && handler.getHandler_id().compareTo(BigDecimal.ZERO) != 0) {
                handlersDao.updHandler(handler);
            }
        } catch (Exception e) {
            e.getStackTrace();
            FacesUtil.addInfoMessage("Xəta baş verdi! Daxil edilən parametrlərin düzgünlüyünü yoxlanın");
        }
    }

    public void listener() {
        LOG.info("ajax listener " + handler);
    }

    public void addHandler() {
        LOG.info("addHandler " + handler);
        handler = new Handler();
        model.setTblList(false);
        model.setTooBar(false);
        model.setEditView(true);
    }

    public void cancelHandler() {
        LOG.info("cancelHandler " + handler);
        handler = new Handler();
        model.setTblList(true);
        model.setTooBar(true);
        model.setEditView(false);
    }

    public void editHandler() {
        LOG.info("editHandler " + handler);
        if (handler != null && handler.getHandler_id().compareTo(BigDecimal.ZERO) != 0) {

            model.setTblList(false);
            model.setTooBar(false);
            model.setEditView(true);
        } else {
            FacesUtil.addWarnMessage(null, "Cədvəldən uyğun sətiri seçin");
        }
    }

    public void deleteHandler() {
        LOG.info("deleteHandler " + handler);
        try {
            if (handler != null && handler.getHandler_id().compareTo(BigDecimal.ZERO) != 0) {
                handlersDao.deleteContent(handler);
                diameterList.remove(handler);
            } else {
                FacesUtil.addWarnMessage(null, "Cədvəldən uyğun sətiri seçin");
            }
        } catch (Exception e) {
            FacesUtil.addInfoMessage("Xəta baş verdi! Birdaha cəhd edin");
        }
    }

    public List<Handler> getDiameterList() {
        return diameterList;
    }

    public void setDiameterList(List<Handler> diameterList) {
        this.diameterList = diameterList;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public HandlerModel getModel() {
        return model;
    }

    public void setModel(HandlerModel model) {
        this.model = model;
    }

}
