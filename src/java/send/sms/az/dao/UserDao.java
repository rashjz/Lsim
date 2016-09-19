/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

import com.sun.mail.imap.Rights;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import send.sms.az.model.ComboModel;
import send.sms.az.model.Right;
import send.sms.az.model.User;
import send.sms.az.model.ext.UserModel;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;

/**
 *
 * @author rasha_000
 */
public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    public static User checkLogin(User user) {
        logger.info("checkLogin");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("select * from ui_users where login = ? and pwd_hash = MD5(?) and status='a' ");
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassw());
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setUser_id(rs.getBigDecimal(1));
                user.setFullname(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setLang_id(rs.getInt(7));
                user.setMob(rs.getString(12));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return user;
    }

    public static List<ComboModel> getCompanyList() {
        logger.info("getCompanyList");
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> tempList = new ArrayList<>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = " SELECT company_id, company_name\n"
                    + "    FROM rsb_user.companies\n"
                    + "ORDER BY company_name";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                ComboModel cm = new ComboModel();
                cm.setItem_value(rs.getBigDecimal(1));
                cm.setItem_name(rs.getString(2));
                tempList.add(cm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return tempList;
    }

    public static User getUserBalance(User user) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
//            con = DatabaseUtil.connect();
            con = DataBaseHelper.connect();
            ps = con.prepareStatement("SELECT pkg_bulk_sms2.get_user_balance (?) AS balance FROM DUAL");
            ps.setBigDecimal(1, user.getUser_id());
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setBalance(rs.getBigDecimal(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return user;
    }

    public static void getUserRights(User user) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
//        List<Right> rights = new ArrayList<>();
        try {
//            con = DatabaseUtil.connect();
            con = DataBaseHelper.connect();
            ps = con.prepareStatement("SELECT *\n"
                    + "  FROM RSB_USER.UI_RIGHTS r\n"
                    + " WHERE R.RIGHT_ID IN (SELECT RR.RIGHT_ID\n"
                    + "                        FROM RSB_USER.UI_ROLES_RIGHTS rr\n"
                    + "                       WHERE RR.ROLE_ID IN (SELECT UR.ROLE_ID\n"
                    + "                                              FROM RSB_USER.UI_USERS_ROLES ur\n"
                    + "                                             WHERE UR.USER_ID IN (?)))");
            ps.setBigDecimal(1, user.getUser_id());
            rs = ps.executeQuery();
            user.getRights().clear();
            while (rs.next()) {
                Right r = new Right();
                r.setId(rs.getBigDecimal(1));
                r.setRight_name(rs.getString(2));
                r.setRight_desc(rs.getString(3));
                r.setFor_everyone(rs.getBigDecimal(4));

                user.getRightsMap().put(r.getRight_name(), r);
//                user.getRights().add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }

    }

    public static UserModel userList(UserModel model) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> menuList = new ArrayList<User>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "SELECT *\n"
                    + "  FROM (SELECT u1.user_id,\n"
                    + "               u1.login,\n"
                    + "               u1.full_name,\n"
                    + "               u1.email,\n"
                    + "               c.company_name,\n"
                    + "               c.company_id,\n"
                    + "               g.group_name,\n"
                    + "               u1.comments,\n"
                    + "               u2.login AS added_by,\n"
                    + "               ROW_NUMBER () OVER (ORDER BY u1.user_id) row_num\n"
                    + "          FROM ui_users u1\n"
                    + "               LEFT JOIN ui_user_groups g ON u1.GROUP_ID = g.GROUP_ID\n"
                    + "               LEFT JOIN ui_users u2 ON u2.user_id = u1.added_by_user_id\n"
                    + "               LEFT JOIN companies c ON u1.company_id = c.company_id)\n"
                    + " WHERE row_num BETWEEN ? AND ? ";
            pstm = c.prepareStatement(sql.toString());
            logger.info(model.getStart() + " " + model.getEnd());
            pstm.setBigDecimal(1, model.getStart());
            pstm.setBigDecimal(2, model.getEnd());
            rs = pstm.executeQuery();
            while (rs.next()) {
                User bl = new User();
                bl.setUser_id(rs.getBigDecimal(1));
                bl.setLogin(rs.getString(2));
                bl.setFullname(rs.getString(3));
                bl.setEmail(rs.getString(4));
                bl.setCompany_name(rs.getString(5));
                bl.setCompany_id(rs.getBigDecimal(6));
                bl.setGroup_name(rs.getString(7));
                bl.setComments(rs.getString(8));
                bl.setAddedby(rs.getString(9));
                menuList.add(bl);
            }
            model.setList(menuList);
            String sqlCount = "select count(0) as item_count from ui_users u1";
            pstm = c.prepareStatement(sqlCount.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                model.setRowCount(rs.getBigDecimal(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return model;
    }

    public static User insertUser(User user, BigDecimal userID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement(""
                    + "  declare\n"
                    + "            l_pwd_hash varchar2(50);\n"
                    + "         begin \n"
                    + "            l_pwd_hash := lower(RAWTOHEX(UTL_RAW.cast_to_raw(DBMS_OBFUSCATION_TOOLKIT.md5 (input_string => ?))));\n"
                    + "            insert into ui_users (user_id, login, pwd_hash, full_name, phone_number, email, status, comments, company_id, added_by_user_id) values \n"
                    + "                                 (ui_users_seq.nextval, \n"
                    + "                                 ?, \n"
                    + "                                 l_pwd_hash,\n"
                    + "                                 ?,\n"
                    + "                                 ?,\n"
                    + "                                 ?,\n"
                    + "                                 'a',\n"
                    + "                                 ?,\n"
                    //+ "                                 :LANG_ID,\n"
                    + "                                 ?,\n"//COMPANY_ID
                    //  + "                                 :GROUP_ID,\n"
                    + "                                 ?) ;\n"
                    //                    + "                                 \n"
                    //                    + "            for i in 1..:ROLES_SIZE \n"
                    //                    + "            loop\n"
                    //                    + "              insert into ui_users_roles (rec_id, user_id, role_id) values (ui_users_roles_seq.nextval, ui_users_seq.currval, :ROLES(i));\n"
                    //                    + "            end loop;\n"
                    //                    + "            \n"
                    //                    + "            :NEW_ID := ui_users_seq.currval;\n"
                    + "         end;");
            ps.setString(1, user.getPassw());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getMob());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getComments());
            ps.setBigDecimal(7, user.getCompany_id());
            ps.setBigDecimal(8, userID);
            int resut = ps.executeUpdate();
            if (resut == 1) {
                for (String rolID : user.getRolesId()) {
                    ps = con.prepareStatement("insert into ui_users_roles (rec_id, user_id, role_id) values (ui_users_roles_seq.nextval, ui_users_seq.currval, " + new BigDecimal(rolID) + ")");
                    rs = ps.executeQuery();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return user;
    }

    public static User updateUser(User user, BigDecimal userID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement(""
                    + " declare\n"
                    + "             l_pwd_hash varchar2(50);\n"
                    + "         begin \n"
                    + "            if ? is not null then\n"//passw
                    + "              l_pwd_hash := lower(RAWTOHEX(UTL_RAW.cast_to_raw(DBMS_OBFUSCATION_TOOLKIT.md5 (input_string => ?)))); \n"//passw
                    + "            end if;\n"
                    + "            update ui_users set login=?, \n"//login
                    + "            pwd_hash=(case when ? is not null then l_pwd_hash\n"//pass
                    + "                           else pwd_hash end),\n"
                    + "            full_name=?,\n"//FULL_NAME
                    + "            phone_number=?,\n"//PHONE_NUMBER
                    + "            email=?,\n"//EMAIL
                    //                    + "            lang_id=?,\n"//LANG_ID
                    + "            company_id=?,\n"//COMPANY_ID
                    //                    + "            group_id=?,\n"//group_id
                    + "            comments=? \n"//COMMENTS
                    //                    + "            status='a'\n"//STATUS
                    + "            where user_id=? ; \n"//id
                    + "             "
                    + "     end;");
            ps.setString(1, user.getPassw());
            ps.setString(2, user.getPassw());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassw());

            ps.setString(5, user.getFullname());
            ps.setString(6, user.getMob());
            ps.setString(7, user.getEmail());
            ps.setBigDecimal(8, user.getCompany_id());
            ps.setString(9, user.getComments());

            ps.setBigDecimal(10, user.getUser_id());
//            ps.setBigDecimal(11, user.getUser_id());
            logger.info(ps);
            int resut = ps.executeUpdate();

            if (resut == 1) {
                ps = con.prepareStatement("  delete from ui_users_roles where user_id=?");
                ps.setBigDecimal(1, user.getUser_id());
                rs = ps.executeQuery();
                for (String rolID : user.getRolesId()) {
                    ps = con.prepareStatement("insert into ui_users_roles (rec_id, user_id, role_id) values (ui_users_roles_seq.nextval, " + user.getUser_id() + ", " + new BigDecimal(rolID) + ")");
                    rs = ps.executeQuery();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return user;
    }

    public static void setUserRoles(User user) {
        logger.info("setUserRoles");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> roles = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("SELECT role_id\n"
                    + "  FROM rsb_user.ui_users_roles\n"
                    + " WHERE user_id = ? ");
            ps.setBigDecimal(1, user.getUser_id());

            rs = ps.executeQuery();
            while (rs.next()) {
//                logger.info("id " + rs.getBigDecimal(1));
                roles.add("" + rs.getBigDecimal(1));
            }
            user.setRolesId(roles.toArray(new String[roles.size()]));
//            logger.info(user.getRolesId().length);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
    }

    public static List<ComboModel> userListDetailed() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ComboModel> usrs = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("SELECT user_id, full_name || ' (' || login || ')' FROM ui_users");
            rs = ps.executeQuery();
            while (rs.next()) {
                ComboModel cm = new ComboModel();
                cm.setItem_value(rs.getBigDecimal(1));
                cm.setItem_name(rs.getString(2));
                usrs.add(cm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return usrs;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setLogin("rashad");
        user.setPassw("12");
//        user.setPassw("c20ad4d76fe97759aa27a0c99bff6710");
//        System.out.println(checkLogin(user).getUser_id());

        user.setUser_id(new BigDecimal(82));
        setUserRoles(user);
        System.out.println(user.getRolesId().length);
//        UserModel model=new UserModel();
//        model.setStart(BigDecimal.ZERO);
//        model.setEnd(BigDecimal.TEN);
//        for (User arg : userList(model).getList()) {
//            System.out.println(arg.getEmail()); 
//        }
    }
}
