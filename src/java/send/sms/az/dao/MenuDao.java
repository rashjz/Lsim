/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

import send.sms.az.model.Menu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;

/**
 *
 * @author Mobby
 */
public class MenuDao {

    public   List<Menu> menuList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Menu> menuList = new ArrayList<Menu>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "    select m.menu_id, m.menu_name, m2.menu_name as parent_name, r.right_name, m.icon_name from ui_menu m\n"
                    + "       left join ui_menu m2 on (m.parent_id=m2.menu_id)\n"
                    + "       left join ui_rights r on (m.right_id=r.right_id)";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setId(rs.getBigDecimal(1));
                menu.setMenu_name(rs.getString(2));
                menu.setParent_name(rs.getString(3));
                menu.setRight_name(rs.getString(4));
                menu.setIcon_name(rs.getString(5));
                menuList.add(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return menuList;
    }

//    public static void main(String[] args) {
//        for (Menu arg : menuList()) {
//            System.out.println(arg.toString());
//        }
//    }
}
