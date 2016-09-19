/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import send.sms.az.dao.UserDao;
import send.sms.az.dao.UserLogDao;
import send.sms.az.model.User;
import send.sms.az.model.UserLogger;
import send.sms.az.model.ext.UserModel;
import send.sms.az.util.LogTypes;
import send.sms.az.web.ext.UserDetails;

/**
 *
 * @author rasha_000
 */
@ManagedBean(name = "userCnt")
@ViewScoped
public class UserController extends UserDetails implements Serializable {

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private LazyDataModel<User> model;
    private User user = new User();
    private UserDao userDao;
    private UserModel usermodel = new UserModel();
    private UserLogger userLogger = new UserLogger();

    public UserController() {
        userDao=new UserDao();
    }

    @PostConstruct
    public void init() {//pagination for DataTable
        LOG.info("init() invoked");
        try {
            LOG.info("here1");
            this.model = new LazyDataModel<User>() {
                private static final long serialVersionUID = 1L;

                @Override
                public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    LOG.info("first " + first + " pageSize " + pageSize);
                    usermodel.setStart(new BigDecimal(first));
                    usermodel.setEnd(new BigDecimal(first + pageSize));
//                    ProtocolFilter.filterProtocolTable(filters, detailSearchModel);
                    usermodel = userDao.userList(usermodel);
                    model.setRowCount((int) usermodel.getRowCount().intValue());
                    LOG.info("count " + usermodel.getRowCount());
                    return usermodel.getList();
                }
            };
        } catch (Exception e) {
            userLogger.setType(LogTypes.USERLIST.toString());
            userLogger.setId(user.getUser_id());
            userLogger.setDescription(usermodel.toString());
            UserLogDao.insertUserLog(userLogger);
            e.printStackTrace();
            LOG.info("exception occured " + e);
        }
    }

    public void listener() {
        LOG.info(user.toString());
        UserDao.setUserRoles(user);
    }

    public void newUser() {
        LOG.info("new user");
        user = new User();
    }

    public void save() {
        LOG.info("save invoked" + user.toString());
        userLogger.setUserID(loginCnt.getUser().getUser_id());
        userLogger.setDescription(user.toString());
        try {
            if (user.getUser_id().compareTo(BigDecimal.ZERO) == 0) {
                //insert   
                userDao.insertUser(user, loginCnt.getUser().getUser_id());
                userLogger.setType(LogTypes.INSERT.toString());
            } else {
                //update  
                userDao.updateUser(user, loginCnt.getUser().getUser_id());
                userLogger.setType(LogTypes.UPDATE.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            userLogger.setType(LogTypes.ERROR.toString());
            userLogger.setDescription(e.toString() + " user ID " + loginCnt.getUser().getUser_id());
        }
        UserLogDao.insertUserLog(userLogger);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LazyDataModel<User> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<User> model) {
        this.model = model;
    }

}
