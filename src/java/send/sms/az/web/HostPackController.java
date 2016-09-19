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
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import send.sms.az.dao.HostPackDao;
import send.sms.az.model.ComboModel;
import send.sms.az.model.HPackHosts;
import send.sms.az.model.ext.HostPckModel;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "hostpackCnt")
@ViewScoped
public class HostPackController implements Serializable {

    private static final Logger LOG = Logger.getLogger(HostPackController.class.getName());
    private ComboModel host = new ComboModel();
    private List<HPackHosts> postpackList = new ArrayList<>();
    private HPackHosts hpackHosts;
    private HostPackDao dao;
    private HostPckModel model = new HostPckModel("hostList");

    public HostPackController() {
        dao = new HostPackDao();
    }

    public void rowSelListener(ComboModel cm) {
        LOG.info("row select " + cm);
        host = cm;
        hostView();
        postpackList = dao.getHostPackHostsList(cm.getItem_value());
        LOG.info(model.isGeneral() + " " + model.isPackList());
    }

    public void hostView() {
        model = new HostPckModel("hostView");
    }

    public void saveEditedPack() {
        LOG.info("saveEditedPack " + hpackHosts.toString());
    }

    public ComboModel getHost() {
        return host;
    }

    public void editHost(String action) {
        LOG.info("editHost " + host);
        if (action.equals("ins")) {
            host = new ComboModel();
        }
        model = new HostPckModel("hostEdit");
    }

    public void hostList() {
        model = new HostPckModel("hostList");
    }

    public void editPack(HPackHosts cm) {
        LOG.info("editPack " + host);
        hpackHosts = cm;
        model = new HostPckModel("packEdit");
    }

    public void setHost(ComboModel host) {
        this.host = host;
    }

    public List<HPackHosts> getPostpackList() {
        return postpackList;
    }

    public void setPostpackList(List<HPackHosts> postpackList) {
        this.postpackList = postpackList;
    }

    public HPackHosts getHpackHosts() {
        return hpackHosts;
    }

    public void setHpackHosts(HPackHosts hpackHosts) {
        this.hpackHosts = hpackHosts;
    }

    public HostPckModel getModel() {
        return model;
    }

    public void setModel(HostPckModel model) {
        this.model = model;
    }

}
