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
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import send.sms.az.model.ShortNum;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.model.ComboModel;
import send.sms.az.model.Content;
import send.sms.az.model.Subscribe;

/**
 *
 * @author rasha_000
 */
public class ShortNumberDao {

    private static final Logger LOG = Logger.getLogger(ShortNumberDao.class.getName());
    
    public static List<ShortNum> getSMStempList() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ShortNum> smsList = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("SELECT sn.number_id,\n"
                    + "         o.operator_name || ' - ' || sn.short_number AS short_number\n"
                    + "    FROM short_numbers sn\n"
                    + "         INNER JOIN smpp_hosts h ON h.host_id = sn.host_id\n"
                    + "         INNER JOIN mobile_operators o ON o.operator_id = h.operator_id\n"
                    + "ORDER BY o.operator_name, sn.short_number");
            rs = ps.executeQuery();
            while (rs.next()) {
                ShortNum sms = new ShortNum();
                sms.setId(rs.getBigDecimal(1));
                sms.setShort_num(rs.getString(2));
                smsList.add(sms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return smsList;
    }
    
    public static List<String> getOPShortNumberList() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> smsList = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("  SELECT sn.short_number\n"
                    + "    FROM short_numbers sn\n"
                    + "ORDER BY sn.short_number");
            rs = ps.executeQuery();
            while (rs.next()) {
                smsList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return smsList;
    }
    
    public List<ShortNum> getSortNumbList() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ShortNum> smsList = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("SELECT number_id,\n"
                    + "       host_name,\n"
                    + "       short_number,\n"
                    + "       is_active_now,\n"
                    + "       incoming_price,\n"
                    + "       outgoing_price,\n"
                    + "       short_number_alias,\n"
                    + "       host_desc,\n"
                    + "       sn.HOST_ID\n"
                    + "  FROM SHORT_NUMBERS sn JOIN SMPP_HOSTS h ON h.HOST_ID = sn.HOST_ID");
            rs = ps.executeQuery();
            while (rs.next()) {
                ShortNum sms = new ShortNum();
                sms.setId(rs.getBigDecimal(1));
                sms.setHost_name(rs.getString(2));
                sms.setShort_num(rs.getString(3));
                sms.setIsActive(rs.getBigDecimal(4).intValue());
                sms.setIncomingPrice(rs.getBigDecimal(5).intValue());
                sms.setOutgoingPrice(rs.getBigDecimal(6).intValue());
                sms.setShortNumAlias(rs.getString(7));
                sms.setHostID(rs.getBigDecimal(9));
                sms.setHost_desc(rs.getString(8));
                smsList.add(sms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return smsList;
    }
    
    public static List<ComboModel> getHostList() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ComboModel> smsList = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("SELECT * from SMPP_HOSTS");
            rs = ps.executeQuery();
            while (rs.next()) {
                ComboModel sms = new ComboModel();
                sms.setItem_value(rs.getBigDecimal(1));
                sms.setItem_name(rs.getString(2));
                smsList.add(sms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return smsList;
    }
    
    public int updShortNumb(ShortNum num) {
        int count = 0;
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        List<Subscribe> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect(); 
            String sql = "UPDATE SHORT_NUMBERS sn\n"
                    + "   SET SN.HOST_ID = " + num.getHostID() + ",\n"
                    + "       SN.SHORT_NUMBER = " + num.getShort_num() + ",\n"
                    + "       SN.IS_ACTIVE_NOW = " + num.getIsActive() + ",\n"
                    + "       SN.INCOMING_PRICE = " + num.getIncomingPrice() + ",\n"
                    + "       SN.OUTGOING_PRICE = " + num.getOutgoingPrice() + ",\n"
                    + "       SN.SHORT_NUMBER_ALIAS = '" + num.getShortNumAlias() + "' \n"
                    + " WHERE sn.NUMBER_ID = " + num.getId() + "";
            pstm = c.prepareCall(sql.toString());
//            LOG.info(sql.toString());
            count = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return count;
    }
    
    public int insShortNumb(ShortNum num) {
        int count = 0;
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        List<Subscribe> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect(); 
            String sql = "{call INSERT INTO SHORT_NUMBERS (NUMBER_ID,\n"
                    + "                           HOST_ID,\n"
                    + "                           SHORT_NUMBER,\n"
                    + "                           IS_ACTIVE_NOW,\n"
                    + "                           INCOMING_PRICE,\n"
                    + "                           OUTGOING_PRICE,\n"
                    + "                           SHORT_NUMBER_ALIAS)\n"
                    + "                           values (RSB_USER.SHORT_NUMBERS_SEQ.NEXTVAL," + num.getHostID()
                    + "                                   ," + num.getShort_num()
                    + "                                   ," + num.getIsActive()
                    + "                                   ," + num.getIncomingPrice()
                    + "                                   ," + num.getOutgoingPrice()
                    + "                                   ,'" + num.getShortNumAlias() + "' ) \n"
                    + "                                   RETURNING NUMBER_ID INTO ? }";
            LOG.info(sql.toString());
            pstm = c.prepareCall(sql.toString());
//            count = pstm.executeUpdate();
            pstm.registerOutParameter(1, Types.NUMERIC);
            count = pstm.executeUpdate();
            if (count > 0) {
                num.setId(pstm.getBigDecimal(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return count;
    }
    
    public static void main(String[] args) {
        for (ShortNum arg : getSMStempList()) {
            System.out.println(arg.getId() + "    " + arg.getShort_num());
        }
    }
}
