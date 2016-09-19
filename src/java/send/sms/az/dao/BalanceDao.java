/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

import send.sms.az.model.BalanceHistory;
import send.sms.az.model.Balance;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.model.ext.BalanceHistoryModel;
import send.sms.az.util.ConvertUtil;

/**
 *
 * @author Mobby
 */
public class BalanceDao {
    private static final Logger LOG = Logger.getLogger(BalanceDao.class.getName());

    public List<Balance> balanceList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Balance> menuList = new ArrayList<Balance>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "    SELECT DISTINCT u.user_id,\n"
                    + "                u.login,\n"
                    + "                u.full_name,\n"
                    + "                NVL (balance, 0) AS balance\n"
                    + "  FROM ui_users u\n"
                    + "       LEFT JOIN bulk_sms2_balance b ON b.user_id = u.user_id\n"
                    + "       INNER JOIN\n"
                    + "       ui_user_rights_view rv\n"
                    + "          ON (    u.user_id = rv.user_id\n"
                    + "              AND (   rv.right_name LIKE 'bulk_sms%'\n"
                    + "                   OR rv.right_name = 'smpp_client:index'))";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Balance balance = new Balance();
                balance.setId(rs.getBigDecimal(1));
                balance.setLogin(rs.getString(2));
                balance.setFull_name(rs.getString(3));
                balance.setBalance(rs.getBigDecimal(4));
                menuList.add(balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return menuList;
    }

    public List<BalanceHistory> balanceHistoryList(BalanceHistoryModel model) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<BalanceHistory> menuList = new ArrayList<BalanceHistory>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT BULK_SMS2_BALANCE_HISTORY.REC_ID,\n"
                    + "                      U1.LOGIN,\n"
                    + "                      BULK_SMS2_BALANCE_HISTORY.OLD_VALUE,\n"
                    + "                      BULK_SMS2_BALANCE_HISTORY.NEW_VALUE,\n"
                    + "                      U2.LOGIN AS MODIFIED_BY,\n"
                    + "                      BULK_SMS2_BALANCE_HISTORY.MODIFIED_DATE,\n"
                    + "                      BULK_SMS2_BALANCE_HISTORY.COMMENTS\n"
                    + "               FROM BULK_SMS2_BALANCE_HISTORY\n"
                    + "               INNER JOIN UI_USERS U1 ON U1.USER_ID = BULK_SMS2_BALANCE_HISTORY.USER_ID\n"
                    + "               INNER JOIN UI_USERS U2 ON U2.USER_ID = BULK_SMS2_BALANCE_HISTORY.MODIFIED_BY_USER_ID WHERE U1.LOGIN LIKE '%'");
            if (model.getUserID().compareTo(BigDecimal.ZERO) != 0) {
                sql.append(" AND U1.USER_ID = " + model.getUserID());
            }
            if (model.getFrom() != null) {
                sql.append(" and MODIFIED_DATE >= to_date('" + ConvertUtil.dateToString(model.getFrom(), "MM/dd/YYYY") + "', 'MM/DD/YYYY')");
            }
            if (model.getTo() != null) {
                sql.append(" and MODIFIED_DATE <= to_date('" + ConvertUtil.dateToString(model.getTo(), "MM/dd/YYYY") + "', 'MM/DD/YYYY')");
            }
//            LOG.info(sql.toString());
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                BalanceHistory balance = new BalanceHistory();
                balance.setId(rs.getBigDecimal(1));
                balance.setLogin(rs.getString(2));
                balance.setOld_value(rs.getBigDecimal(3));
                balance.setNew_value(rs.getBigDecimal(4));
                balance.setModified_by(rs.getString(5));
                balance.setModified_date(rs.getDate(6));
                balance.setComments(rs.getString(7));
                menuList.add(balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return menuList;
    }

    public void updateBalance(Balance balance) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
//            insertLOG = "{call PKG_LOG.log_ws_client(?,?,?,?,?,?,?,?)}";
            String sql = "{call  pkg_bulk_sms2.set_user_balance(?, ?, ?, ?)}";
            pstm = c.prepareCall(sql.toString());
            pstm.setBigDecimal(1, balance.getId());
            pstm.setBigDecimal(2, balance.getBalance());
            pstm.setBigDecimal(3, new BigDecimal(70));
            pstm.setString(4, balance.getComment());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
    }

    public static void main(String[] args) {
//        Balance balance = new Balance();
//        balance.setId(new BigDecimal(157));
//        balance.setBalance(new BigDecimal(666));
//
//        balance.setComment("rashad edit");
//
//        updateBalance(balance);
//
//        for (Balance arg : balanceList()) {
//            if (arg.getId().compareTo(new BigDecimal(157)) == 0) {
//                System.out.println(arg.toString());
//            }
//        }

//        for (BalanceHistory arg : balanceHistoryList()) {
//            System.out.println(arg.toString());
//        }
    }
}
