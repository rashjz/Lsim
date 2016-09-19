/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

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
import send.sms.az.model.Handler;

/**
 *
 * @author rasha_000
 */
public class HandlersDao {

    private static final Logger LOG = Logger.getLogger(HandlersDao.class.getName());

    public List<Handler> handlerList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Handler> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("select * from HANDLER_LIST");
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Handler bm = new Handler();
                bm.setHandler_id(rs.getBigDecimal(1));
                bm.setHandler_name(rs.getString(2));
                bm.setHostname(rs.getString(3));
                bm.setA_num(rs.getString(4));
                bm.setB_num(rs.getString(5));
                bm.setContent(rs.getString(6));
                bm.setHand_type_id(rs.getBigDecimal(7));
                bm.setProc_name(rs.getString(8));

                bm.setSort_order(rs.getBigDecimal(9));
                bm.setHand_status(rs.getString(10));
                bm.setValid_from(rs.getTimestamp(11));
                bm.setValid_to(rs.getTimestamp(12));
                bm.setPack_tarif_id(rs.getBigDecimal(13));
                bm.setCharge_reason(rs.getString(14));

                bm.setApp_name(rs.getString(15));
                bm.setRetry_on_netErr(rs.getBigDecimal(16));
                bm.setRep_rule_id(rs.getBigDecimal(17));
                bm.setNumparam1(rs.getBigDecimal(18));
                bm.setNumparam2(rs.getBigDecimal(19));
                bm.setStrparam1(rs.getString(20));
                bm.setStrparam2(rs.getString(21));

                bm.setSend_Charge_Fail(rs.getBigDecimal(22));
                bm.setSeg_id(rs.getBigDecimal(23));
                bm.setUser_id(rs.getBigDecimal(24));

                dtList.add(bm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return dtList;
    }

    public Handler updHandler(Handler bm) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("update HANDLER_LIST set user_id=" + bm.getUser_id() + ",    \n"
                    + "SEGMENT_ID =" + bm.getSeg_id() + ",  \n"
                    + "SEND_CHARGE_FAIL_MSG =" + bm.getSend_Charge_Fail() + ",   \n"
                    + "STR_PARAM2  ='" + bm.getStrparam2() + "',  \n"
                    + "STR_PARAM1   ='" + bm.getStrparam1() + "',  \n"
                    + "NUM_PARAM2  =" + bm.getNumparam2() + ",  \n"
                    + "NUM_PARAM1     =" + bm.getNumparam1() + ", \n"
                    + "CHR_REPLACEMENT_RULE_ID   =" + bm.getRep_rule_id() + ", \n"
                    + "RETRY_ON_NETWORK_ERROR     =" + bm.getRetry_on_netErr() + ",\n"
                    + "APP_NAME         ='" + bm.getApp_name() + "',  \n"
                    + "CHARGE_REASON    ='" + bm.getCharge_reason() + "', \n"
                    + "TARIFF_ID   =" + bm.getPack_tarif_id() + ", \n"
                    + "VALID_TO    = ?, \n"
                    + "VALID_FROM     =? , \n"
                    + "HANDLER_STATUS  ='" + bm.getHand_status() + "',   \n"
                    + "SORT_ORDER   =" + bm.getSort_order() + ",  \n"
                    + "PROC_NAME   ='" + bm.getProc_name() + "',\n"
                    + "HANDLER_TYPE_ID    =" + bm.getHand_type_id() + ",\n"
                    + "CONTENT    ='" + bm.getContent() + "',\n"
                    + "B_NUM   ='" + bm.getB_num() + "', \n"
                    + "A_NUM   ='" + bm.getA_num() + "',  \n"
                    + "HOST_NAME   ='" + bm.getHostname() + "', \n"
                    + "HANDLER_NAME  ='" + bm.getHandler_name() + "'  \n"
                    // + "HANDLER_ID    , \n"
                    + "where handler_id=" + bm.getHandler_id() + "");
            LOG.info(sql.toString());
            pstm = c.prepareStatement(sql.toString());
            LOG.info(sql.toString());
            pstm.setDate(1, new java.sql.Date(bm.getValid_to().getTime()));
            pstm.setDate(2, new java.sql.Date(bm.getValid_from().getTime()));
            int count = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return bm;
    }
//

    public Handler insContent(Handler bm) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("{call  INSERT INTO HANDLER_LIST (HANDLER_ID,\n"
                    + "                          HANDLER_NAME,\n"
                    + "                          HOST_NAME,\n"
                    + "                          A_NUM,\n"
                    + "                          B_NUM,\n"
                    + "                          CONTENT,\n"
                    + "                          HANDLER_TYPE_ID,\n"
                    + "                          PROC_NAME,\n"
                    + "                          SORT_ORDER,\n"
                    + "                          HANDLER_STATUS,\n"
                    + "                          VALID_FROM,\n"
                    + "                          VALID_TO,\n"
                    + "                          TARIFF_ID,\n"
                    + "                          CHARGE_REASON,\n"
                    + "                          APP_NAME,\n"
                    + "                          RETRY_ON_NETWORK_ERROR,\n"
                    + "                          CHR_REPLACEMENT_RULE_ID,\n"
                    + "                          NUM_PARAM1,\n"
                    + "                          NUM_PARAM2,\n"
                    + "                          STR_PARAM1,\n"
                    + "                          STR_PARAM2,\n"
                    + "                          SEND_CHARGE_FAIL_MSG,\n"
                    + "                          SEGMENT_ID,\n"
                    + "                          USER_ID)\n"
                    + "     VALUES ( HANDLER_LIST_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING HANDLER_ID into ?}");

            pstm = c.prepareCall(sql.toString());
            pstm.setString(1, bm.getHandler_name());
            pstm.setString(2, bm.getHostname());
            pstm.setString(3, bm.getA_num());
            pstm.setString(4, bm.getB_num());
            pstm.setString(5, bm.getContent());
            pstm.setBigDecimal(6, bm.getHand_type_id());

            pstm.setString(7, bm.getProc_name());
            pstm.setBigDecimal(8, bm.getSort_order());
            pstm.setString(9, bm.getHand_status());
            pstm.setDate(10, new java.sql.Date(bm.getValid_from().getTime()));
            pstm.setDate(11, new java.sql.Date(bm.getValid_to().getTime()));
            pstm.setBigDecimal(12, bm.getPack_tarif_id());
            pstm.setString(13, bm.getCharge_reason());
            pstm.setString(14, bm.getApp_name());
            pstm.setBigDecimal(15, bm.getRetry_on_netErr());
            pstm.setBigDecimal(16, bm.getRep_rule_id());
            pstm.setBigDecimal(17, bm.getNumparam1());
            pstm.setBigDecimal(18, bm.getNumparam2());
            pstm.setString(19, bm.getStrparam1());
            pstm.setString(20, bm.getStrparam2());
            pstm.setBigDecimal(21, bm.getSend_Charge_Fail());
            pstm.setBigDecimal(22, bm.getSeg_id());
            pstm.setBigDecimal(23, bm.getUser_id());
            pstm.registerOutParameter(24, Types.NUMERIC);

            LOG.info(sql.toString());
            int count = pstm.executeUpdate();

            if (count > 0) {
                bm.setHandler_id(pstm.getBigDecimal(24));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return bm;
    }

    public Handler deleteContent(Handler bm) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM HANDLER_LIST\n"
                    + "      WHERE handler_id =?");

            pstm = c.prepareCall(sql.toString());
            pstm.setBigDecimal(1, bm.getHandler_id()); 
            LOG.info(sql.toString());
            int count = pstm.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return bm;
    }
}
