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
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import send.sms.az.dao.RightsDao;
import send.sms.az.model.Right;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "rightCnt")
@ViewScoped
public class RightController implements Serializable {

    private static final Logger LOG = Logger.getLogger(RightController.class.getName());

    private List< Right> rightList ;
    private Right right = new Right();
    private List< Right> filteredList;
    private RightsDao rightDao;

    public RightController() {
        rightDao = new RightsDao();
        rightList = new ArrayList<Right>(rightDao.getRightsList().values());
    }

    public void newRight() {
        right = new Right();
    }

    public void save() {
        if (right.getId().compareTo(BigDecimal.ZERO) == 0) {
            LOG.info("insert " + right.toString());
            rightDao.insertRight(right);
        } else {
            LOG.info("update " + right.toString());
            rightDao.updateRight(right);
        }
    }

    public List<Right> getRightList() {
        return rightList;
    }

    public void setRightList(List<Right> rightList) {
        this.rightList = rightList;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }

    public List<Right> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(List<Right> filteredList) {
        this.filteredList = filteredList;
    }

}
