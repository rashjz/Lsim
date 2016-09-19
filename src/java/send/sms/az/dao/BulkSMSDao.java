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
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import send.sms.az.model.BulkSMS;
import send.sms.az.model.ext.BulkSMSModel;
import send.sms.az.model.BulkSMSSend;
import send.sms.az.model.ComboModel;
import send.sms.az.model.ScheduledModel;
import send.sms.az.model.ScheduledSMS;
import send.sms.az.model.TaskDetail;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;

/**
 *
 * @author Mobby
 */
public class BulkSMSDao {

    private static final Logger LOG = Logger.getLogger(BulkSMSDao.class.getName());

    public static List<ComboModel> shortNumList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> menuList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = " SELECT NS.SENDER_ID, sender_name\n"
                    + "    FROM bulk_sms2_sender_names ns\n"
                    + "   WHERE is_deleted = 0\n"
                    + "ORDER BY sender_name";
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                ComboModel bm = new ComboModel();
                bm.setItem_value(rs.getBigDecimal(1));
                bm.setItem_name(rs.getString(2));
                menuList.add(bm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return menuList;
    }

    public  BulkSMSModel BulkSMSList(BulkSMSModel model) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<BulkSMSSend> menuList = new ArrayList<BulkSMSSend>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "SELECT *\n"
                    + "  FROM (SELECT bs.rec_id,\n"
                    + "               bs.comments,\n"
                    + "               sn.sender_name AS send_from,\n"
                    + "               bs.date_added,\n"
                    + "               bs.last_send_time AS date_started,\n"
                    + "               u.login AS added_by,\n"
                    + "               number_count,\n"
                    + "               sent_count AS waiting_for_status,\n"
                    + "               delivered_count,\n"
                    + "               undelivered_count,\n"
                    + "               queued_count,\n"
                    + "               error_count,\n"
                    + "               black_list_count,\n"
                    + "               DECODE (bs.status, 0, 'No', 'Yes') AS started,\n"
                    + "               ROW_NUMBER () OVER (ORDER BY bs.rec_id) row_num\n"
                    + "          FROM bulk_sms2 bs\n"
                    + "               INNER JOIN ui_users u ON bs.creator_id = u.user_id\n"
                    + "               INNER JOIN bulk_sms2_sender_names sn\n"
                    + "                  ON bs.sender_id = sn.sender_id)\n"
                    + " WHERE row_num BETWEEN ? AND ? ";
            pstm = c.prepareStatement(sql.toString());
            LOG.info(model.getStart() + " " + model.getEnd());
            pstm.setBigDecimal(1, model.getStart());
            pstm.setBigDecimal(2, model.getEnd());
            rs = pstm.executeQuery();
            while (rs.next()) {
                BulkSMSSend bl = new BulkSMSSend();
                bl.setId(rs.getBigDecimal(1));
                bl.setComments(rs.getString(2));
                bl.setSend_from(rs.getString(3));
                bl.setDate_added(rs.getDate(4));
                bl.setDate_started(rs.getDate(5));
                bl.setAdded_by(rs.getString(6));
                bl.setNumber_count(rs.getBigDecimal(7));
                bl.setWait4status(rs.getBigDecimal(8));
                bl.setDelivCount(rs.getBigDecimal(9));
                bl.setUnDelivCount(rs.getBigDecimal(10));
                bl.setQueueCount(rs.getBigDecimal(11));
                bl.setErrorCount(rs.getBigDecimal(12));
                bl.setBlaclListCount(rs.getBigDecimal(13));
                bl.setStarted(rs.getString(14));
                menuList.add(bl);
            }
            model.setList(menuList);
            String sqlCount = "select count(0) as item_count from bulk_sms2 bs";
            pstm = c.prepareStatement(sqlCount.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                model.setRowCount(rs.getBigDecimal(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return model;
    }

    public static List<ComboModel> sendTimeList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> list = new ArrayList<>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "select * from bulk_sms2_send_times order by rec_id";
            pstm = c.prepareStatement(sql.toString());

            rs = pstm.executeQuery();
            while (rs.next()) {
                ComboModel cm = new ComboModel();
                cm.setItem_value(rs.getBigDecimal(1));
                cm.setItem_name(rs.getString(2));
                list.add(cm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return list;
    }

    public  List<TaskDetail> taskDetailList(BigDecimal recID) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<TaskDetail> list = new ArrayList<>();
        try {
////            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "SELECT destination_addr AS msisdn,\n"
                    + "       DECODE (\n"
                    + "          status,\n"
                    + "          0, 'Queued',\n"
                    + "          2, 'Error while sending',\n"
                    + "          3, 'Black list',\n"
                    + "          5, 'Paused',\n"
                    + "          1, DECODE (final_status,\n"
                    + "                     NULL, 'Sent - waiting for status',\n"
                    + "                     final_status))\n"
                    + "          AS current_status,\n"
                    + "       msg_body\n"
                    + "  FROM smpp_outgoing\n"
                    + " WHERE app_name IN ('BULK_SMS2') AND app_num_param1 = ?";
            pstm = c.prepareStatement(sql.toString());
            pstm.setBigDecimal(1, recID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                TaskDetail cm = new TaskDetail();
                cm.setMsisdn(rs.getString(1));
                cm.setMsg_body(rs.getString(2));
                cm.setStatus(rs.getString(3));
                list.add(cm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return list;
    }

    public  List<ScheduledSMS> scheduledSMSList(ScheduledModel model) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ScheduledSMS> list = new ArrayList<>();
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT bs.rec_id,\n"
                    + "       bs.comments,\n"
                    + "       sn.sender_name AS send_from,\n"
                    + "       bs.date_added,\n"
                    + "       BS.SCHEDULE_TIME,\n"
                    + "       u.login AS added_by,\n"
                    + "       number_count,\n"
                    + "       st.rec_title AS send_type,\n"
                    + "       DECODE (bs.send_time_id,  4, pt.rec_title,  5, pt.rec_title,  NULL)\n"
                    + "          AS period_type,\n"
                    + "       NULL AS edit\n"
                    + "  FROM bulk_sms2 bs\n"
                    + "       INNER JOIN ui_users u ON bs.creator_id = u.user_id\n"
                    + "       INNER JOIN bulk_sms2_sender_names sn ON bs.sender_id = sn.sender_id\n"
                    + "       INNER JOIN bulk_sms2_send_times st ON bs.send_time_id = st.rec_id\n"
                    + "       LEFT JOIN bulk_sms2_period_types pt ON bs.period_type_id = pt.rec_id");
            if (model.isSearch()) {
                sql.append(" where BS.SCHEDULE_TIME between ? and ?");
            }

            pstm = c.prepareStatement(sql.toString());
            if (model.isSearch()) {
                pstm.setDate(1, new java.sql.Date(model.getFromDate().getTime()));
                pstm.setDate(2, new java.sql.Date(model.getToDate().getTime()));
            }
//            LOG.info(""+sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                ScheduledSMS cm = new ScheduledSMS();
                cm.setRec_id(rs.getBigDecimal(1));
                cm.setComments(rs.getString(2));
                cm.setSendFrom(rs.getString(3));
                cm.setDateAdded(new Date(rs.getDate(4).getTime()));
                cm.setDateStarted(new Date(rs.getDate(5).getTime()));
                cm.setAddedBy(rs.getString(6));
                cm.setNumberCount("" + rs.getString(7));
                cm.setSendType(rs.getString(8));
                cm.setPeriodType(rs.getString(9));
                list.add(cm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return list;
    }

    public  int insertBulkSMS(BulkSMS bsms) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect(); 
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO bulk_sms2 (rec_id, "
                    + "creator_id, "
                    + "sender_id, "
                    + "comments, "
                    + "msg_body, "
                    + "schedule_time, "
                    //                    + "schedule_column_id, "
                    + "addr_book_id, "
                    + "channel_name)\n"
                    + "     VALUES (bulk_sms2_seq.NEXTVAL,\n"
                    + "              ?,\n" //userid
                    + "             ?,\n"//SENDER_ID
                    + "             ?,\n"//COMMENTS
                    + "             ?,\n"); //MSG_BODY

            if (bsms.getSendAt().compareTo(new BigDecimal(2)) == 0) {
                sql.append(" ? ,\n");
                LOG.info("append date");
            } else {
                LOG.info("append sysdate");
                sql.append(" sysdate ,\n");
            }
            sql.append(" ?,\n"
                    + " 'UI')\n"
            );
            String generatedColumns[] = {"rec_id"};
            pstm = c.prepareStatement(sql.toString(), generatedColumns);
            pstm.setBigDecimal(1, bsms.getUserID());
            pstm.setBigDecimal(2, bsms.getSendFrom());//sender id neucundu
            pstm.setString(3, bsms.getComment());
            pstm.setString(4, bsms.getMessage());
            if (bsms.getSendAt().compareTo(new BigDecimal(2)) == 0) {
                pstm.setDate(5, new java.sql.Date(bsms.getSendDate().getTime()));
                pstm.setBigDecimal(6, bsms.getAddressBooks().getAbid());
            } else {
                pstm.setBigDecimal(5, bsms.getAddressBooks().getAbid());
            }

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            while (rs.next()) {
                LOG.info("" + rs.getBigDecimal(1));
                bsms.setId(rs.getBigDecimal(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return count;
    }

    public  String startTask(BigDecimal recID, BigDecimal userID) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        String message = "";
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "{call  pkg_bulk_sms2.start_sending(? , \n"
                    + "                                   ?, \n"
                    + "                                   ?)}";
            pstm = c.prepareCall(sql.toString());
            pstm.setBigDecimal(1, recID);
            pstm.setBigDecimal(2, userID);
            pstm.registerOutParameter(3, Types.VARCHAR);
            int count = pstm.executeUpdate();
            if (count > 0) {
                message = pstm.getString(3);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return message;
    }

    public  String pauseTask(BigDecimal recID, BigDecimal userID) {
        Connection c = null;
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        ResultSet rs = null;
        String message = "";
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql1 = " update smpp_outgoing set status = 5 where app_name='BULK_SMS2' and app_num_param1 = ? and status = 0 and (user_id is null or user_id = ?) ";
            pstm1 = c.prepareCall(sql1.toString());
            pstm1.setBigDecimal(1, recID);
            pstm1.setBigDecimal(2, userID);
            pstm1.executeUpdate();

            String sql2 = " update bulk_sms2 set is_paused = 1 where rec_id = ? and ( creator_id is null or creator_id = ?) ";
            pstm2 = c.prepareCall(sql2.toString());
            pstm2.setBigDecimal(1, recID);
            pstm2.setBigDecimal(2, userID);
            pstm2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm1, c);
            DatabaseUtil.close(rs, pstm2, c);
        }
        return message;
    }

    public  String resumeTask(BigDecimal recID, BigDecimal userID) {
        Connection c = null;
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        ResultSet rs = null;
        String message = "";
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql1 = " UPDATE smpp_outgoing\n"
                    + "   SET status = 0\n"
                    + " WHERE     app_name = 'BULK_SMS2'\n"
                    + "       AND app_num_param1 = ?\n"
                    + "       AND status = 5\n"
                    + "       AND (user_id IS NULL OR user_id = ?) ";
            pstm1 = c.prepareCall(sql1.toString());
            pstm1.setBigDecimal(1, recID);
            pstm1.setBigDecimal(2, userID);
            pstm1.executeUpdate();

            String sql2 = " UPDATE bulk_sms2\n"
                    + "   SET is_paused = 0\n"
                    + " WHERE rec_id = ? AND (creator_id IS NULL OR creator_id = ?)";
            pstm2 = c.prepareCall(sql2.toString());
            pstm2.setBigDecimal(1, recID);
            pstm2.setBigDecimal(2, userID);
            pstm2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm1, c);
            DatabaseUtil.close(rs, pstm2, c);
        }
        return message;
    }

    public static void main(String[] args) {
//        BulkSMSModel model = new BulkSMSModel();
//        model.setStart(BigDecimal.ZERO);
//        model.setEnd(new BigDecimal(112));
//        for (BulkSMSSend arg : BulkSMSList(model).getList()) {
//            System.out.println(arg.toString());
//        }
//        BulkSMSList(model);
//        System.out.println(model.getRowCount());

//        for (ComboModel arg : shortNumList()) {
//            System.out.println(arg.getItem_name() + " " + arg.getItem_value());
//        }
//        BulkSMS bsms = new BulkSMS();
//        bsms.setMessage("1234");
//        bsms.setComment("cmm");
//        bsms.setBatchSize(BigDecimal.TEN);
//        bsms.setBatchInterval(BigDecimal.ZERO);
//        bsms.setMaxCount(BigDecimal.ONE);
//        bsms.setOffset(BigDecimal.ZERO);
//        bsms.setUserID(new BigDecimal(82));
//        bsms.setSendFrom(new BigDecimal(117));
//        bsms.setValidPeriod(BigDecimal.ZERO);
////        bsms.setAddressBooks("103");
//        insertBulkSMS(bsms);
//        for (ScheduledSMS arg : scheduledSMSList()) {
//            System.out.println(arg.toString());
//        }
//        pauseTask(new BigDecimal(142), new BigDecimal(66));
//        resumeTask(new BigDecimal(142), new BigDecimal(66));
    }
}
