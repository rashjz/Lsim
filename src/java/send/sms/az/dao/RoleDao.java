package send.sms.az.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import send.sms.az.model.Role;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;

/**
 *
 * @author rashjz
 */
public class RoleDao {

    public static Map<BigDecimal, Role> getRoleList() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<BigDecimal, Role> roleList = new HashMap<BigDecimal, Role>();
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement(SQLquery.roleList);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getBigDecimal(1));
                role.setName(rs.getString(2));
                roleList.put(role.getId(), role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return roleList;
    }

    public static List<String> getRoleRights(Role role) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> rightList = new ArrayList<>();
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("select right_id from ui_roles_rights where role_id = ?");
            ps.setBigDecimal(1, role.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                rightList.add(rs.getBigDecimal(1).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return rightList;
    }

    public static Role insertRole(Role role, List<String> rights) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement(" insert into ui_roles (role_id, role_name) values (ui_roles_seq.nextval, ? )");
            ps.setString(1, role.getName());
            int resut = ps.executeUpdate();
            if (resut == 1) {
                ps = con.prepareStatement("select ui_roles_seq.currval from dual");
                rs = ps.executeQuery();
                while (rs.next()) {
                    role.setId(rs.getBigDecimal(1));
                    System.out.println("id " + role.getId());
                }
            }

            for (String s : rights) {
                String sqli = " insert into ui_roles_rights (rec_id, role_id, right_id) values (ui_roles_rights_seq.nextval, ui_roles_seq.currval, ? ) ";
                ps = con.prepareStatement(sqli);
                ps.setBigDecimal(1, new BigDecimal(s));
                int result = ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return role;
    }

    public static Role updateRole(Role role, List<String> rights) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement(" update ui_roles set role_name = ? where role_id=? ");
            ps.setString(1, role.getName());
            ps.setBigDecimal(2, role.getId());
            int resut = ps.executeUpdate();

            ps = con.prepareStatement(" delete from ui_roles_rights where role_id=?");
            ps.setBigDecimal(1, role.getId());
            resut = ps.executeUpdate();

            for (String s : rights) {
                String sqli = " insert into ui_roles_rights (rec_id, role_id, right_id) values (ui_roles_rights_seq.nextval, ?, ? ) ";
                ps = con.prepareStatement(sqli);
                ps.setBigDecimal(1, role.getId());
                ps.setBigDecimal(2, new BigDecimal(s));
                int result = ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return role;
    }

    public static void main(String[] args) {
//        for (Map.Entry<BigDecimal, Role> entrySet : getRoleList().entrySet()) {
//            Object key = entrySet.getKey();
//            Object value = entrySet.getValue();
//            System.out.println(key + " " + value.toString());
//        }
//        List<String> list = new ArrayList<>();
//        list.add("71");
//        list.add("368");
        Role role = new Role();
//        role.setName("Rash");
//        System.out.println(insertRole(role, list));

        role.setId(new BigDecimal(10));
        System.out.println(getRoleRights(role));
    }
}
