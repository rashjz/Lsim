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
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import send.sms.az.model.BlackList;
import send.sms.az.model.ext.BlackListModel;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.ds.LsimDataSource;

/**
 *
 * @author Mobby
 */
public class BlackListDao {

    private static final Logger logger = Logger.getLogger(BlackListDao.class.getName());

    public   BlackListModel blackList(BlackListModel blackListModel) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<BlackList> blList = new ArrayList<BlackList>();
        try {
//            c = DatabaseUtil.connect();
//            System.out.println(c);
            c = DataBaseHelper.connect();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT *\n"
                    + "  FROM ("
                    + "SELECT rec_id,\n"
                    + "               msisdn,\n"
                    + "               fd,\n"
                    + "               DECODE (for_vas, 1, 'Yes', 'No') AS for_vas,\n"
                    + "               DECODE (for_bulk, 1, 'Yes', 'No') AS for_bulk,\n"
                    + "               ROW_NUMBER () OVER (ORDER BY rec_id) row_num\n"
                    + "          FROM black_list ");
            sql.append(" where msisdn like  '" + blackListModel.getMsisdn() + "%' ");
            if (blackListModel.getFdate() != null) {
                sql.append(" and fd >= ? ");
            }
            sql.append(") t\n");
            blackListModel.setExpSQL(sql.toString());
            sql.append(" WHERE row_num BETWEEN ? AND ?");
            pstm = c.prepareStatement(sql.toString());
            if (blackListModel.getFdate() != null) {
                pstm.setDate(1, new java.sql.Date(blackListModel.getFdate().getTime()));
                pstm.setBigDecimal(2, blackListModel.getStart());
                pstm.setBigDecimal(3, blackListModel.getEnd());
            } else {
                pstm.setBigDecimal(1, blackListModel.getStart());
                pstm.setBigDecimal(2, blackListModel.getEnd());
            }

            logger.info(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                BlackList bl = new BlackList();
                bl.setId(rs.getBigDecimal(1));
                bl.setMsisdn(rs.getString(2));
                bl.setFdate(rs.getDate(3));
                bl.setFor_vas(rs.getString(4));
                bl.setFor_bulk(rs.getString(5));
                blList.add(bl);
            }
            blackListModel.setList(blList);
            String sqlCount = "SELECT COUNT (0)  \n"
                    + "  FROM rsb_user.black_list\n"
                    + " WHERE msisdn LIKE '" + blackListModel.getMsisdn() + "%'";
            pstm = c.prepareStatement(sqlCount.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                blackListModel.setRowCount(rs.getBigDecimal(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
//            DatabaseUtil.close(rs, pstm, c);
        }
        return blackListModel;
    }

    public   BlackList insBlackList(BlackList bl) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            setDetails(bl);
            String sql = "insert into black_list (rec_id, msisdn, fd, is_manual, for_vas, for_bulk) values (black_list_seq.nextval, ?, sysdate, 1, ?, ?)";
            pstm = c.prepareStatement(sql.toString());
            pstm.setString(1, bl.getMsisdn());
            pstm.setString(2, bl.getFor_vas());
            pstm.setString(3, bl.getFor_bulk());
            int result = pstm.executeUpdate();
            if (result == 1) {
                pstm = c.prepareStatement("select black_list_seq.currval from dual");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    bl.setId(rs.getBigDecimal(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return bl;
    }

    public   void setDetails(BlackList bl) {
        if (bl.getFor_vas().equals("Yes")) {
            bl.setFor_vas("1");
        } else {
            bl.setFor_vas("0");
        }
        if (bl.getFor_bulk().equals("Yes")) {
            bl.setFor_bulk("1");
        } else {
            bl.setFor_bulk("0");
        }
        logger.info(bl.getFor_bulk()+" "+bl.getFor_vas());
    }

    public   BlackList updateBlackList(BlackList bl) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
             setDetails(bl);
             logger.info(bl.getFor_bulk()+" "+bl.getFor_vas());
            String sql = "update black_list set msisdn=?, for_vas=?, for_bulk=? where rec_id=?";
            pstm = c.prepareStatement(sql.toString());
            pstm.setString(1, bl.getMsisdn());
            pstm.setString(2, bl.getFor_vas());
            pstm.setString(3, bl.getFor_bulk());
            pstm.setBigDecimal(4, bl.getId());
            int result = pstm.executeUpdate();
            System.out.println("upd result " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return bl;
    }

    public   BlackList deleteBlackList(BlackList bl) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = "delete from black_list where rec_id in (?)";
            pstm = c.prepareStatement(sql.toString());
            pstm.setBigDecimal(1, bl.getId());
            int result = pstm.executeUpdate();
            System.out.println("upd result " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return bl;
    }

    public static void main(String[] args) {
//        BlackList bl = new BlackList();
//        bl.setMsisdn("9999999999");
//        bl.setFor_vas("0");
//        bl.setFor_bulk("0");
//        bl.setId(new BigDecimal(345135));
//        deleteBlackList(bl); 
        BlackListModel blm = new BlackListModel();
        blm.setEnd(BigDecimal.ZERO);
        blm.setStart(BigDecimal.TEN);

//        for (BlackList arg : blackList(blm).getList()) {
////            if (arg.getId().compareTo(new BigDecimal(345135)) == 0) {
//            System.out.println(arg.getMsisdn());
//        }
    }

//        
//        BlackList bl = new BlackList();
//        bl.setMsisdn("9999999999");
//        bl.setFor_vas("0");
//        bl.setFor_bulk("0");
//        bl.setId(new BigDecimal(345135));
//        System.out.println(updateBlackList(bl).toString());
//        System.out.println(insBlackList(bl).toString());
//    }
}
