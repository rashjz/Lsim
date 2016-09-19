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
import send.sms.az.model.BlackList;
import send.sms.az.model.UserLogger;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;

/**
 *
 * @author rasha_000
 */
public class UserLogDao {

    public static void insertUserLog(UserLogger log) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
//            c = DatabaseUtil.connect(); 
            c = DataBaseHelper.connect();
            String sql = "INSERT INTO user_logs (ID,\n"
                    + "                       ACTION_TYPE,\n"
                    + "                       ACTION_DATE,\n"
                    + "                       ACTION_DESC,\n"
                    + "                       USER_ID,\n"
                    + "                       STATUS)\n"
                    + "     VALUES (RSB_USER.USER_LOG_SEQ.nextval,\n"
                    + "             ?,\n"
                    + "             SYSDATE,\n"
                    + "             ?,\n"
                    + "             ?,\n"
                    + "             'a')";
            pstm = c.prepareStatement(sql.toString());
            pstm.setString(1, log.getType());
            pstm.setString(2, log.getDescription());
            pstm.setBigDecimal(3, log.getUserID());
            int result = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
    }

    public static void main(String[] args) {
        insertUserLog(new UserLogger());
    }
}
