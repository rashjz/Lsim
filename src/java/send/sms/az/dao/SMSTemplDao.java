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
import java.util.logging.Logger;
import send.sms.az.model.Role;
import send.sms.az.model.sms_temp;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;

/**
 *
 * @author rasha_000
 */
public class SMSTemplDao {

    private static final Logger LOG = Logger.getLogger(SMSTemplDao.class.getName());

    public   List<sms_temp> getSMStempList() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<sms_temp> smsList = new ArrayList<>();
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("select * from sms_templates");
            rs = ps.executeQuery();
            while (rs.next()) {
                sms_temp sms = new sms_temp();
                sms.setId(rs.getBigDecimal(1));
                sms.setTpl_text(rs.getString(2));
                smsList.add(sms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return smsList;
    }

    public   sms_temp insSMStemp(sms_temp sms) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("INSERT INTO sms_templates (tpl_id, tpl_text)\n"
                    + "VALUES (sms_templates_seq.nextval, ?)");
            ps.setString(1, sms.getTpl_text());
            int resut = ps.executeUpdate();
            if (resut == 1) {
                ps = con.prepareStatement("select sms_templates_seq.nextval from dual");
                rs = ps.executeQuery();
                while (rs.next()) {
                    sms.setId(rs.getBigDecimal(1));
                    System.out.println("id " + sms.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return sms;
    }

    public   sms_temp updSMStemp(sms_temp sms) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("UPDATE sms_templates SET TPL_TEXT = ? "
                    + "WHERE TPL_ID = ? ");
            ps.setString(1, sms.getTpl_text());
            ps.setBigDecimal(2, sms.getId());
            int resut = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return sms;
    }

    public   sms_temp deleteSMStemp(sms_temp sms) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
              con = DataBaseHelper.connect(); 
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("delete from sms_templates where tpl_id in (?) ");
            ps.setBigDecimal(1, sms.getId());
            int resut = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return sms;
    }

//    public static void main(String[] args) {
//        sms_temp sms = new sms_temp();
//        sms.setTpl_text("Rashad Javadova sms var");
//        sms.setId(new BigDecimal(21));
////        sms = insSMStemp(sms);
//        System.out.println(sms.getId());
//        for (sms_temp arg : getSMStempList()) {
//            System.out.println(arg.toString());
//        }
//
//        sms.setTpl_text("rashad java");
//        updSMStemp(sms);
//        
////        deleteSMStemp(sms);
//    }
}
