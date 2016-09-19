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
import java.util.Date;
import java.util.List;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.model.LogError;
import send.sms.az.model.ShortNum;

/**
 *
 * @author rasha_000
 */
public class SystemStatusDao {

    public BigDecimal getLogErrorCount() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BigDecimal errorCount = BigDecimal.ZERO;
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("SELECT COUNT (0) AS item_count\n"
                    + "  FROM app_log\n"
                    + " WHERE log_date > TRUNC (SYSDATE) AND log_category = 'ERROR'");
            rs = ps.executeQuery();
            while (rs.next()) {
                errorCount = rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return errorCount;
    }

    public List<LogError> getAppLogErrors() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LogError> list = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("SELECT *\n"
                    + "  FROM (  SELECT *\n"
                    + "            FROM app_log\n"
                    + "           WHERE log_date > TRUNC (SYSDATE) AND log_category = 'ERROR'\n"
                    + "        ORDER BY log_id DESC) t\n"
                    + " WHERE ROWNUM <= 10");
            rs = ps.executeQuery();
            while (rs.next()) {
                LogError error = new LogError();
                error.setLogID(rs.getBigDecimal(1));
                error.setLogType(rs.getString(2));
                error.setLogDate(rs.getTimestamp(3));
                error.setLogText(rs.getString(4));
                error.setAppName(rs.getString(5));
                error.setMsisdn(rs.getString(6));
                list.add(error);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return list;
    }

    public Date getLastBlackListEntryDate() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Date date = null;
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("SELECT MAX (fd) AS last_added_date FROM black_list");
            rs = ps.executeQuery();
            while (rs.next()) {
                date = rs.getTimestamp(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return date;
    }

    public BigDecimal getOutgoingNotProcessed() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BigDecimal errorCount = BigDecimal.ZERO;
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("select count(0) as item_count from smpp_outgoing where date_added>trunc(sysdate) and status=0");
            rs = ps.executeQuery();
            while (rs.next()) {
                errorCount = rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return errorCount;
    }

    public BigDecimal getOutgoingInError() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BigDecimal errorCount = BigDecimal.ZERO;
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("select count(0) as item_count from smpp_outgoing where date_added>trunc(sysdate) and status=2");
            rs = ps.executeQuery();
            while (rs.next()) {
                errorCount = rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return errorCount;
    }

    public BigDecimal getIncomingNotProcessed() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BigDecimal errorCount = BigDecimal.ZERO;
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("select count(0) as item_count from smpp_incoming where in_date>trunc(sysdate) and is_processed=0");
            rs = ps.executeQuery();
            while (rs.next()) {
                errorCount = rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return errorCount;
    }

    public static void main(String[] args) {

    }
}
