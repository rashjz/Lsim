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
import java.util.List;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.model.HPackHosts;

/**
 *
 * @author rasha_000
 */
public class HostPackDao {

    public List<HPackHosts> getHostPackHostsList(BigDecimal hostID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HPackHosts> smsList = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("SELECT rec_id,\n"
                    + "       HPH.host_id,\n"
                    + "       HPH.operator_id,\n"
                    + "       SH.HOST_NAME,\n"
                    + "       mo.operator_name\n"
                    + "  FROM HOST_PACKS_HOSTS hph\n"
                    + "       INNER JOIN SMPP_HOSTS sh ON HPH.HOST_ID = SH.HOST_ID\n"
                    + "       INNER JOIN mobile_operators mo ON HPH.OPERATOR_ID = MO.OPERATOR_ID\n"
                    + " WHERE pack_id = ?"); 
            ps.setBigDecimal(1, hostID);
             rs = ps.executeQuery();
            while (rs.next()) {
                HPackHosts hs = new HPackHosts();
                hs.setRec_id(rs.getBigDecimal(1));
                hs.setHost_id(rs.getBigDecimal(2));
                hs.setOperator_id(rs.getBigDecimal(3));
                hs.setHost_name(rs.getString(4));
                hs.setOp_name(rs.getString(5));
                smsList.add(hs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return smsList;
    }

    public HPackHosts insertHPackHosts(HPackHosts hpackh) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HPackHosts> smsList = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("INSERT INTO RSB_USER.HOST_PACKS_HOSTS (REC_ID,\n"
                    + "                                       PACK_ID,\n"
                    + "                                       HOST_ID,\n"
                    + "                                       OPERATOR_ID) values (RSB_USER.HOST_PACKS_HOSTS_SEQ,?,?,?) ");
            ps.setBigDecimal(1, hpackh.getPack_id());
            ps.setBigDecimal(2, hpackh.getHost_id());
            ps.setBigDecimal(3, hpackh.getOperator_id());
            int resut = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return hpackh;
    }

    public HPackHosts updateHPackHosts(HPackHosts hpackh) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HPackHosts> smsList = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("UPDATE RSB_USER.HOST_PACKS_HOSTS\n"
                    + "   SET PACK_ID = ?, HOST_ID = ?, OPERATOR_ID = ?\n"
                    + " WHERE REC_ID = ?");
            ps.setBigDecimal(1, hpackh.getPack_id());
            ps.setBigDecimal(2, hpackh.getHost_id());
            ps.setBigDecimal(3, hpackh.getOperator_id());
            ps.setBigDecimal(4, hpackh.getRec_id());
            int resut = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
        return hpackh;
    }

    public void deleteHPackHosts(BigDecimal recID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HPackHosts> smsList = new ArrayList<>();
        try {
            con = DataBaseHelper.connect();
//            con = DatabaseUtil.connect();
            ps = con.prepareStatement("delete from HOST_PACKS_HOSTS where rec_id=?");
            ps.setBigDecimal(4, recID);
            int resut = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, ps, con);
        }
    }
}
