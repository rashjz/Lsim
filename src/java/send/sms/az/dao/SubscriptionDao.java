/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.model.Subscribe;

/**
 *
 * @author rasha_000
 */
public class SubscriptionDao {

    private static final Logger LOG = Logger.getLogger(SubscriptionDao.class.getName());

    public List<Subscribe> subscriptionList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Subscribe> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT s.subs_id,\n"
                    + "       s.subs_name,\n"
                    + "       s.subs_desc,\n"
                    + "       s.status,\n"
                    + "       a.alg_name AS selection_algorithm,\n"
                    + "       a.alg_id ,\n"
                    + "       s.selection_max_days_back,\n"
                    + "       s.welcome_message,\n"
                    + "       s.welcome_message2,\n"
                    + "       s.info_message,\n"
                    + "       s.goodbye_message,\n"
                    + "       s.already_member_message\n"
                    + "  FROM subs_list s\n"
                    + "       INNER JOIN subs_sel_algorithms a\n"
                    + "          ON s.selection_algorithm_id = a.alg_id ");

            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Subscribe bm = new Subscribe();
                bm.setSubs_id(rs.getBigDecimal(1));
                bm.setSubs_name(rs.getString(2));
                bm.setSubs_desc(rs.getString(3));
                bm.setStatus(rs.getString(4));
                bm.setAlgorithm_name(rs.getString(5));
                bm.setSelection_algorithm(rs.getBigDecimal(6));
                bm.setSelection_max_days_back(rs.getBigDecimal(7));
                bm.setWelcome_message(rs.getString(8));
                bm.setWelcome_message2(rs.getString(9));
                bm.setInfo_message(rs.getString(10));
                bm.setGoodbye_message(rs.getString(11));
                bm.setAlready_member_message(rs.getString(12));
                dtList.add(bm);
            }

//            model.setCustomers(dtList);
//            StringBuffer sqlCount = new StringBuffer("SELECT COUNT (0) from (");
//            sqlCount.append(sqlcc);
//            sqlCount.append(" )");
//            pstm = c.prepareStatement(sqlCount.toString());
//            LOG.info(" count " + sqlCount);
//            rs = pstm.executeQuery();
//            while (rs.next()) {
//                chargeModel.setRowCount(rs.getBigDecimal(1));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return dtList;
    }

    public Subscribe insSubs(Subscribe bm) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        List<Subscribe> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("{call INSERT INTO subs_list (subs_id,\n"
                    + "                       subs_name,\n"
                    + "                       subs_desc,\n"
                    + "                       status,\n"
                    + "                       selection_algorithm_id,\n"
                    + "                       selection_max_days_back,\n"
                    + "                       welcome_message,\n"
                    + "                       welcome_message2,\n"
                    + "                       info_message,\n"
                    + "                       goodbye_message,\n"
                    + "                       already_member_message)\n"
                    + "     VALUES (subs_list_seq.NEXTVAL,\n"
                    + "             ?,\n"
                    + "             ?,\n"
                    + "             ?,\n"
                    + "             ?,\n"
                    + "             ?,\n"
                    + "             ?,\n"
                    + "             ?,\n"
                    + "             ?,\n"
                    + "             ?,\n"
                    + "             ?)\n"
                    + "  RETURNING subs_id\n"
                    + "       INTO ? }");

            pstm = c.prepareCall(sql.toString());
            pstm.setString(1, bm.getSubs_name());
            pstm.setString(2, bm.getSubs_desc());
            pstm.setString(3, bm.getStatus());
            pstm.setBigDecimal(4, bm.getSelection_algorithm());
            pstm.setBigDecimal(5, bm.getSelection_max_days_back());
            pstm.setString(6, bm.getWelcome_message());
            pstm.setString(7, bm.getWelcome_message2());
            pstm.setString(8, bm.getInfo_message());
            pstm.setString(9, bm.getGoodbye_message());
            pstm.setString(10, bm.getAlready_member_message());

            pstm.registerOutParameter(11, Types.NUMERIC);
            int count = pstm.executeUpdate();
            if (count > 0) {
                bm.setSubs_id(pstm.getBigDecimal(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return bm;
    }

    public Subscribe updSubs(Subscribe bm) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        List<Subscribe> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("update subs_list set \n"
                    + "         subs_name=?, \n"
                    + "         subs_desc=?, \n"
                    + "         status=?, \n"
                    + "         SELECTION_ALGORITHM_ID=?,\n"
                    + "         SELECTION_MAX_DAYS_BACK=?,\n"
                    + "         welcome_message=?,\n"
                    + "         welcome_message2=?,\n"
                    + "         info_message=?,\n"
                    + "         already_member_message=?,\n"
                    + "         goodbye_message=?\n"
                    + "         where subs_id=? ");

            pstm = c.prepareCall(sql.toString());
            pstm.setString(1, bm.getSubs_name());
            pstm.setString(2, bm.getSubs_desc());
            pstm.setString(3, bm.getStatus());
            pstm.setBigDecimal(4, bm.getSelection_algorithm());
            pstm.setBigDecimal(5, bm.getSelection_max_days_back());
            pstm.setString(6, bm.getWelcome_message());
            pstm.setString(7, bm.getWelcome_message2());
            pstm.setString(8, bm.getInfo_message());
            pstm.setString(9, bm.getAlready_member_message());
            pstm.setString(10, bm.getGoodbye_message());
            pstm.setBigDecimal(11, bm.getSubs_id());
            int count = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return bm;
    }
}
