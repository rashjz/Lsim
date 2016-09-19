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
import send.sms.az.dao.ShortNumberDao;
import send.sms.az.model.ShortNum;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "snumCnt")
@ViewScoped
public class ShortNumController implements Serializable {

    private static final Logger LOG = Logger.getLogger(ShortNumController.class.getName());
    private List<ShortNum> shortNums;
    private ShortNum num = new ShortNum();
    private ShortNumberDao dao;
    private List<ShortNum> viewShortNums;

    public ShortNumController() {
        dao = new ShortNumberDao();
        shortNums = dao.getSortNumbList();
    }

    public void newShortNumber() {
        LOG.info("newShortNumber");
        num = new ShortNum();
    }

    public void upd_insShortNumber() {
        LOG.info("upd_insShortNumber");
        if (num != null && num.getId().compareTo(BigDecimal.ZERO) == 0) {
            dao.insShortNumb(num);
            if (num.getId().compareTo(BigDecimal.ZERO) != 0) {
                shortNums.add(num);
            }
            LOG.info(num.getId() + "  " + num.toString());
        } else if (num != null && num.getId().compareTo(BigDecimal.ZERO) != 0) {
            LOG.info(num.toString());
            dao.updShortNumb(num);
        }
    }

    public void delShortNumber() {
        LOG.info("delShortNumber");
        if (num != null && num.getId().compareTo(BigDecimal.ZERO) != 0) {

        }
    }

    public void listener() {
        LOG.info(num.getId().toString());
    }

    public ShortNum getNum() {
        return num;
    }

    public void setNum(ShortNum num) {
        this.num = num;
    }

    public List<ShortNum> getShortNums() {
        return shortNums;
    }

    public void setShortNums(List<ShortNum> shortNums) {
        this.shortNums = shortNums;
    }

    public List<ShortNum> getViewShortNums() {
        return viewShortNums;
    }

    public void setViewShortNums(List<ShortNum> viewShortNums) {
        this.viewShortNums = viewShortNums;
    }

}
