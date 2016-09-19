/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import send.sms.az.model.Host;
import send.sms.az.model.Result;
import send.sms.az.model.Transaction;
import send.sms.az.model.ext.TransModel;
import send.sms.az.util.ConvertUtil;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.util.DynamicSql;

/**
 *
 * @author rasha_000
 */
public class TransactionDao {

    private static final Logger LOG = Logger.getLogger(TransactionDao.class.getName());

    public static TransModel transList(TransModel model) {
        Map typeMap = new HashMap();
//        String shortnums = Arrays.toString(model.getSelectedShortNum());
        if (model.getSelectedTypes() != null) {
            for (String mas : model.getSelectedTypes()) {
                typeMap.put(mas, mas);
            }
        }
        String strShortNum = "(";
        String[] abs = {};
        for (String ab : model.getSelectedShortNum()) {
            strShortNum = strShortNum + "'" + ab + "',";
        }
        if (strShortNum.length() > 1) {
            strShortNum = strShortNum.substring(0, strShortNum.length() - 1);
        }
        strShortNum = strShortNum + ")";

        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Transaction> menuList = new ArrayList<>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            LOG.info("appname " + model.getSearch().getApp_name() + " ");
            long startTimer = System.nanoTime();
            String sql = DynamicSql.createSQL(typeMap, model, strShortNum, true);
            pstm = c.prepareStatement(sql.toString());
            LOG.info(sql.toString());
            pstm.setBigDecimal(1, model.getStart());
            pstm.setBigDecimal(2, model.getEnd());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Transaction bl = new Transaction(); 
                bl.setTrans_type(rs.getString(1));
                bl.setId(rs.getBigDecimal(2));
                bl.setTrans_date(rs.getDate(3));
                bl.setDone_date(rs.getString(4));
                bl.setMsisdn(rs.getString(5));
                bl.setShort_num(rs.getString(6));
                bl.setTrans_desc(rs.getString(7));
                bl.setSmssize("" + rs.getBigDecimal(8));
                bl.setTrans_result(rs.getString(9));
                bl.setOperator_name(rs.getString(10));

                bl.setHost_name(rs.getString(11));
                bl.setApp_name(rs.getString(12));
                bl.setClient(rs.getString(13));
                menuList.add(bl);
            }
            model.setList(menuList);
            StringBuffer sqlCount = new StringBuffer("SELECT COUNT (0) from (");
            sql = DynamicSql.createSQL(typeMap, model, strShortNum, false);
            sqlCount.append(sql.substring(0, sql.length() - 30));
            sqlCount.append(" )");
            pstm = c.prepareStatement(sqlCount.toString());
            LOG.info(" count " + sqlCount);
            rs = pstm.executeQuery();
            while (rs.next()) {
                model.setRowCount(rs.getBigDecimal(1));
            }
            long startTimer2 = startTimer - System.nanoTime();
            LOG.info("-- - - - - - " + startTimer2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return model;
    }

    public static List<String> shortNumList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<String> menuList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = "  SELECT  sender_name\n"
                    + "    FROM bulk_sms2_sender_names\n"
                    + "   WHERE is_deleted = 0\n"
                    + "ORDER BY sender_name";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                menuList.add(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return menuList;
    }

    public static List<String> operatorList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<String> menuList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = "select   operator_name from mobile_operators order by operator_name";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                menuList.add(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return menuList;
    }

    public static List<String> clientList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<String> menuList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = "select   login from ui_users where status='a' order by login";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                menuList.add(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return menuList;
    }

    public static List<Host> hostList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Host> menuList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = " SELECT host_name AS item_id,\n"
                    + "         host_desc || ' (' || host_name || ')' AS host_name\n"
                    + "    FROM smpp_hosts\n"
                    + "ORDER BY host_name";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Host bl = new Host();
                bl.setHost_item(rs.getString(1));
                bl.setHost_name(rs.getString(2));
                menuList.add(bl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return menuList;
    }

    public static List<Result> resultList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Result> menuList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = " select item_name, item_desc from transaction_result_codes order by sort_order";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Result bl = new Result();
                bl.setResult_item(rs.getString(1));
                bl.setResult_name(rs.getString(2));
                menuList.add(bl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return menuList;
    }

    public static void main(String[] args) {
//        User user = new User();
//        user.setLogin("rashad");
//        user.setPassw("12");
////        user.setPassw("c20ad4d76fe97759aa27a0c99bff6710");
//        System.out.println(checkLogin(user).getUser_id());

//        TransModel model = new TransModel();
//        model.setStart(BigDecimal.ZERO);
//        model.setEnd(BigDecimal.TEN);
//        for (Transaction arg : transList(model).getList()) {
//            System.out.println(arg.toString());
//        }
        for (Result arg : resultList()) {
            System.out.println(arg.getResult_item() + "");
        }
//        System.out.println(shortNumList().size());

    }
}
