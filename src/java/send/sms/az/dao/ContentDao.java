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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.model.ComboModel;
import send.sms.az.model.Content;
import send.sms.az.model.SubCustomer;
import send.sms.az.model.Subscribe;
import send.sms.az.model.ext.SubCustomerModel;

/**
 *
 * @author Mobby
 */
public class ContentDao {

    private static final Logger LOG = Logger.getLogger(ContentDao.class.getName());

    public List<Content> subsCustomerList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Content> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT c.content_id,\n"
                    + "       c.date_added,\n"
                    + "       c.status,\n"
                    + "       c.added_by,\n"
                    + "       b1.content_body AS az_content,\n"
                    + "       b2.content_body AS ru_content,\n"
                    + "       b3.content_body AS en_content,\n"
                    + "       (SELECT TO_STRING (CAST (COLLECT (subs_name) AS ntt_varchar2))\n"
                    + "                  AS subs_names\n"
                    + "          FROM subs_list\n"
                    + "               INNER JOIN subs_list_content\n"
                    + "                  ON subs_list.subs_id = subs_list_content.subs_id\n"
                    + "         WHERE c.content_id = subs_list_content.content_id)\n"
                    + "          AS subscriptions\n"
                    + "  FROM subs_content c\n"
                    + "       LEFT JOIN subs_content_body b1\n"
                    + "          ON (c.content_id = b1.content_id AND b1.lang_id = 1)\n"
                    + "       LEFT JOIN subs_content_body b2\n"
                    + "          ON (c.content_id = b2.content_id AND b2.lang_id = 2)\n"
                    + "       LEFT JOIN subs_content_body b3\n"
                    + "          ON (c.content_id = b3.content_id AND b3.lang_id = 3) ");
//            sql.append("  WHERE msisdn LIKE '" + model.getMsisdn() + "%' ");
//
//            if (model.getSubscription() != null && !model.getSubscription().equals("0")) {
//                sql.append("  AND subs_name LIKE '" + model.getSubscription() + "%' ");
//            }
//            if (model.getStatus() != null && !model.getStatus().equals("0")) {
//                sql.append("  AND sc.status=" + model.getStatus() + " ");
//            }
//            if (model.getAgent_name() != null && !model.getAgent_name().equals("0")) {
//                sql.append("  AND agent_name LIKE '" + model.getAgent_name() + "%' ");
//            }
//            String sqlcc = sql.substring(16, sql.length());
//            sql.append(" ) WHERE row_num between " + chargeModel.getStart() + " and " + chargeModel.getEnd());
//            LOG.info(sql.toString());
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Content bm = new Content();
                bm.setContent_id(rs.getBigDecimal(1));
                bm.setDateAdded(rs.getTimestamp(2));
                bm.setStatus(rs.getString(3));
                bm.setAddedBy(rs.getString(4));
                bm.setAz_content(rs.getString(5));
                bm.setRu_content(rs.getString(6));
                bm.setEn_content(rs.getString(7));
                bm.setSubscription(rs.getString(8));
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

    public List<ComboModel> getSubsList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("select subs_id, subs_name from subs_list order by subs_name");
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                ComboModel bm = new ComboModel();
                bm.setItem_value(rs.getBigDecimal(1));
                bm.setItem_name(rs.getString(2));
                dtList.add(bm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return dtList;
    }

    public Content updContent(Content bm) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        List<Subscribe> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("begin \n"
                    + "            update subs_content set status='" + bm.getStatus() + "' where content_id=" + bm.getContent_id() + " ;"
                    + "            delete from SUBS_CONTENT_BODY where content_id=" + bm.getContent_id() + " ; ");
            if (bm.getAz_content() != null && !bm.getAz_content().equals("")) {
                sql.append("   insert into SUBS_CONTENT_BODY (content_id, lang_id, content_body)\n"
                        + "                values (" + bm.getContent_id() + ", 1, '" + bm.getAz_content() + "') ;");
            }
            if (bm.getRu_content() != null && !bm.getRu_content().equals("")) {
                sql.append("             insert into SUBS_CONTENT_BODY (content_id, lang_id, content_body)\n"
                        + "                values (" + bm.getContent_id() + ", 2, '" + bm.getRu_content() + "') ;");
            }
            if (bm.getEn_content() != null && !bm.getEn_content().equals("")) {
                sql.append("             insert into SUBS_CONTENT_BODY (content_id, lang_id, content_body)\n"
                        + "                values (" + bm.getContent_id() + ", 3, '" + bm.getEn_content() + "') ;");
            }
            sql.append("           delete from SUBS_LIST_CONTENT where content_id=" + bm.getContent_id() + " ; ");
            for (String sid : bm.getSubList()) {
                sql.append("             insert into SUBS_LIST_CONTENT (content_id, subs_id) values (" + bm.getContent_id() + ", " + sid + "); ");
            }
//           if (bm.getEn_content() != null && !bm.getEn_content().equals("")) {
////                    + "            for i in 1..:SUBSCRIPTIONS_SIZE \n"
////                    + "            loop\n"
////                    + "              insert into SUBS_LIST_CONTENT (content_id, subs_id) values (:id, :SUBSCRIPTIONS(i));\n"
////                    + "            end loop;\n"
            sql.append(" end;  ");
////            );

            pstm = c.prepareCall(sql.toString());
            LOG.info(sql.toString());
            int count = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return bm;
    }

    public Content insContent(Content bm) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        List<Subscribe> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("begin \n"
                    + "            insert into subs_content (content_id, added_by, status)\n"
                    + "        VALUES (SUBS_CONTENT_SEQ.NEXTVAL,' " + bm.getAddedBy() + "', '" + bm.getStatus() + "'); ");
            if (bm.getAz_content() != null && !bm.getAz_content().equals("")) {
                sql.append("   insert into SUBS_CONTENT_BODY (content_id, lang_id, content_body)\n"
                        + "                values (SUBS_CONTENT_SEQ.currval, 1, '" + bm.getAz_content() + "') ;");
            }
            if (bm.getRu_content() != null && !bm.getRu_content().equals("")) {
                sql.append("             insert into SUBS_CONTENT_BODY (content_id, lang_id, content_body)\n"
                        + "                values (SUBS_CONTENT_SEQ.currval, 2, '" + bm.getRu_content() + "') ;");
            }
            if (bm.getEn_content() != null && !bm.getEn_content().equals("")) {
                sql.append("             insert into SUBS_CONTENT_BODY (content_id, lang_id, content_body)\n"
                        + "                values (SUBS_CONTENT_SEQ.currval, 3, '" + bm.getEn_content() + "') ;");
            }

            for (String sid : bm.getSubList()) {
                sql.append("             insert into SUBS_LIST_CONTENT (content_id, subs_id) values (SUBS_CONTENT_SEQ.currval, " + sid + "); ");
            }
//           if (bm.getEn_content() != null && !bm.getEn_content().equals("")) {
////                    + "            for i in 1..:SUBSCRIPTIONS_SIZE \n"
////                    + "            loop\n"
////                    + "              insert into SUBS_LIST_CONTENT (content_id, subs_id) values (:id, :SUBSCRIPTIONS(i));\n"
////                    + "            end loop;\n"
            sql.append(" end;");
////            );

            pstm = c.prepareCall(sql.toString());
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
