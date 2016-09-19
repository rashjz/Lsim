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
import send.sms.az.dao.RoleDao;
import send.sms.az.model.Role;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "roleCnt")
@ViewScoped
public class RoleController implements Serializable {

    private static final Logger LOG = Logger.getLogger(RoleController.class.getName());

    private List< Role> roleList ;
    private Role role = new Role();
    private RoleDao roleDao;
    private List<String> selected;

    public RoleController() {
        roleDao = new RoleDao();
        roleList = new ArrayList<Role>(roleDao.getRoleList().values());
    }

    public void listener() {
        LOG.info(role.getId().toString());
        selected = roleDao.getRoleRights(role);
        LOG.info("selected size " + selected.size());
    }

    public void newRole() {
        role = new Role();
        selected = null;
    }

    public void deleteRole() {
        role = new Role();
    }

    public void save() {
        LOG.info("save invoked" + role.getName() + " " + selected);
        if (role.getId().compareTo(BigDecimal.ZERO) == 0) {
            role = roleDao.insertRole(role, selected);
        } else {
            //update
            role = roleDao.updateRole(role, selected);
        }
    }

    public List< Role> getRoleList() {
        return roleList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<String> getSelected() {
        return selected;
    }

    public void setSelected(List<String> selected) {
        this.selected = selected;
    }

}
