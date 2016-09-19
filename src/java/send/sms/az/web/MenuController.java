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
import send.sms.az.dao.MenuDao;
import send.sms.az.model.Menu;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "menuCnt")
@ViewScoped
public class MenuController implements Serializable {

    private static final Logger LOG = Logger.getLogger(MenuController.class.getName());

    private List<Menu> menuList;
    private Menu menu = new Menu();
    private MenuDao menuDao;

    public MenuController() {
        menuDao = new MenuDao();
        menuList = menuDao.menuList();
    }

    public void listener() {
        LOG.info(menu.getId().toString());
    }

    public void save() {
        LOG.info("save invoked" + menu);
        if (menu.getId().compareTo(BigDecimal.ZERO) == 0) {
//            MenuDao.insertRole(balance);
        } else {
            //update
//            MenuDao.updateBalance(menu);
        }
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
