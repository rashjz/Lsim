/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import send.sms.az.model.Right;
import send.sms.az.model.Role;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;

/**
 *
 * @author rasha_000
 */
public class RightsDao {

    public static Map<BigDecimal, Right> getRightsList() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<BigDecimal, Right> roleList = new HashMap<BigDecimal, Right>();
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement(SQLquery.rightsList);
            rs = ps.executeQuery();
            while (rs.next()) {
                Right right = new Right();
                right.setId(rs.getBigDecimal(1));
                right.setRight_name(rs.getString(2));
                right.setRight_desc(rs.getString(3));
                right.setFor_everyone(rs.getBigDecimal(4));
                roleList.put(right.getId(), right);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
//        System.out.println(roleList.size());
        return roleList;
    }

    public static Right updateRight(Right right) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("update ui_rights set right_name=?, right_desc=?, for_everyone=? where right_id=?");
            ps.setString(1, right.getRight_name());
            ps.setString(2, right.getRight_desc());
            ps.setBigDecimal(3, right.getFor_everyone());
            ps.setBigDecimal(4, right.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return right;
    }

    public static Right insertRight(Right right) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("insert into ui_rights (right_id, right_name, right_desc, for_everyone) values (ui_rights_seq.nextval, ?, ?, ?)");
            ps.setString(1, right.getRight_name());
            ps.setString(2, right.getRight_desc());
            ps.setBigDecimal(3, right.getFor_everyone());
            int resut = ps.executeUpdate();
            if (resut == 1) {
                ps = con.prepareStatement("select ui_rights_seq.currval from dual");
                rs = ps.executeQuery();
                while (rs.next()) {
                    right.setId(rs.getBigDecimal(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return right;
    }

    public static Right getRight(BigDecimal id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Right right = null;
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement(SQLquery.getRight);
            ps.setBigDecimal(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                right = new Right();
                right.setId(rs.getBigDecimal(1));
                right.setRight_name(rs.getString(2));
                right.setRight_desc(rs.getString(3));
                right.setFor_everyone(rs.getBigDecimal(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
//        System.out.println(roleList.size());
        return right;
    }

    public static void main(String[] args) {
//        for (Map.Entry<BigDecimal, Right> entrySet : getRightsList().entrySet()) {
//            Object key = entrySet.getKey();
//            Object value = entrySet.getValue();
//            System.out.println(key + " " + value.toString());
//        }

        System.out.println(getRight(new BigDecimal(336)));
    }
}
