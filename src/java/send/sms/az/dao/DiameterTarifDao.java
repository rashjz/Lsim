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
import java.util.logging.Logger;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.model.ComboModel;
import send.sms.az.model.DTarifs;
import send.sms.az.model.ext.TarifSearchModel;
import send.sms.az.util.ConvertUtil;

/**
 *
 * @author Mobby
 */
public class DiameterTarifDao {

    private static final Logger LOG = Logger.getLogger(DiameterTarifDao.class.getName());

    public   List<DTarifs> dTarifList(TarifSearchModel model) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<DTarifs> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT op.operator_name,\n"
                    + "         u.login AS client_login,\n"
                    + "         srv.service_name,\n"
                    + "         SUM (t.tariff_price_novat) AS price_novat\n"
                    + "    FROM diameter_requests dr\n"
                    + "         INNER JOIN diameter_tariffs_view t\n"
                    + "            ON (dr.diam_tariff_id = t.tariff_id)\n"
                    + "         INNER JOIN mobile_operators op ON (t.operator_id = op.operator_id)\n"
                    + "         INNER JOIN ui_users u ON (dr.client_id = u.user_id)\n"
                    + "         INNER JOIN chgw_services srv ON (dr.service_id = srv.service_id)\n"
                    + "         WHERE OP.OPERATOR_NAME like '%' ");
            if (model.getOperatorID().compareTo(BigDecimal.ZERO) != 0) {
                sql.append(" and OP.OPERATOR_ID =" + model.getOperatorID());
            }
            if (model.getUserid().compareTo(BigDecimal.ZERO) != 0) {
                sql.append(" and U.USER_ID =" + model.getUserid());
            }
            if (model.getStartDate() != null) {
                sql.append(" and DR.DATE_ADDED >= to_date('" + ConvertUtil.dateToString(model.getStartDate(), "MM/dd/YYYY") + "', 'MM/DD/YYYY')");
            }
            if (model.getEndDate() != null) {
                sql.append(" and DR.DATE_ADDED <= to_date('" + ConvertUtil.dateToString(model.getEndDate(), "MM/dd/YYYY") + "', 'MM/DD/YYYY')");
            }
            sql.append(" GROUP BY op.operator_name, u.login, srv.service_name");
            LOG.info(sql.toString());
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                DTarifs bm = new DTarifs();
                bm.setOperatorName(rs.getString(1));
                bm.setClientName(rs.getString(2));
                bm.setServiceName(rs.getString(3));
                bm.setPrice(rs.getBigDecimal(4));
                dtList.add(bm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return dtList;
    }

    public static List<ComboModel> operatorList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = " select operator_id, operator_name from mobile_operators order by operator_name ";
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

    public static List<ComboModel> mobOperatorList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = " select operator_id, operator_name from mobile_operators order by operator_name ";
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

    public static List<ComboModel> userList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = "select user_id, login from ui_users where status='a'  order by login";
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

    public static void main(String[] args) {
//        for (DTarifs arg : dTarifList()) {
//            LOG.info(arg.toString());
//        }
    }
}
