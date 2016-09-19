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
import send.sms.az.model.DiameterCharge;
import send.sms.az.model.ext.DiameterChargeModel;
import send.sms.az.util.ConvertUtil;

/**
 *
 * @author Mobby
 */
public class DiameterChargeDao {

    private static final Logger LOG = Logger.getLogger(DiameterChargeDao.class.getName());

    public   DiameterChargeModel diameterChargeList(DiameterChargeModel chargeModel) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<DiameterCharge> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("Select * from ( ");
            sql.append("SELECT msisdn,\n"
                    + "       date_added,\n"
                    + "       date_processed,\n"
                    + "       date_sent,\n"
                    + "       op.operator_name,\n"
                    + "       p.pack_name AS tariff_name,\n"
                    + "       t.tariff_price_novat,\n"
                    + "       dr.charge_reason,\n"
                    + "       u.login AS client_login,\n"
                    + "       is_async,\n"
                    + "       NVL (drc.code_desc, diameter_answer) AS charge_result,\n"
                    + "       srv.service_name,\n"
                    + "       client_charge_desc AS client_desc,"
                    + "       ROW_NUMBER () OVER (ORDER BY DR.REQ_ID) row_num \n"
                    + "  FROM diameter_requests dr\n"
                    + "       INNER JOIN diameter_tariffs_view t\n"
                    + "          ON (dr.diam_tariff_id = t.tariff_id)\n"
                    + "       LEFT JOIN chgw_packs_diam_tariffs pt\n"
                    + "          ON (t.tariff_id = pt.diam_tariff_id)\n"
                    + "       LEFT JOIN chgw_tariff_packs p ON (pt.chgw_pack_id = p.pack_id)\n"
                    + "       INNER JOIN mobile_operators op ON (t.operator_id = op.operator_id)\n"
                    + "       INNER JOIN ui_users u ON (dr.client_id = u.user_id)\n"
                    + "       INNER JOIN chgw_services srv ON (dr.service_id = srv.service_id)\n"
                    + "       LEFT JOIN diameter_response_codes drc\n"
                    + "          ON (dr.diameter_answer = drc.code_id)");
            sql.append("  WHERE msisdn LIKE '" + chargeModel.getMsisdn() + "%' ");
            if (chargeModel.getPackID().compareTo(BigDecimal.ZERO) != 0) {
                sql.append(" AND P.PACK_ID LIKE '" + chargeModel.getPackID() + "%' ");
            }
            if (chargeModel.getCodeID().compareTo(new BigDecimal(-1)) != 0) {
                sql.append(" AND DRC.CODE_ID = " + chargeModel.getCodeID() + " ");
            }
            if (chargeModel.getStartDate() != null) { 
                sql.append(" AND date_added >= to_date('" + ConvertUtil.dateToString(chargeModel.getStartDate(), "MM/dd/YYYY") + "', 'MM/DD/YYYY')");
            }
              if (chargeModel.getEndDate()!= null) { 
                sql.append(" AND date_processed <= to_date('" + ConvertUtil.dateToString(chargeModel.getEndDate(), "MM/dd/YYYY") + "', 'MM/DD/YYYY')");
            }
            String sqlcc = sql.substring(16, sql.length());
            sql.append(" ) WHERE row_num between " + chargeModel.getStart() + " and " + chargeModel.getEnd());
            LOG.info(sql.toString());
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {

                DiameterCharge bm = new DiameterCharge();
                bm.setMsisdn(rs.getString(1));
                bm.setDateAdded(rs.getTimestamp(2));
                bm.setDateProcessed(rs.getTimestamp(3));
                bm.setDateSend(rs.getTimestamp(4));
                bm.setOperatorName(rs.getString(5));
                bm.setTarifName(rs.getString(6));
                bm.setTarifPriceNovat(rs.getBigDecimal(7));
                bm.setChargeReason(rs.getString(8));
                bm.setClientLogin(rs.getString(9));
                bm.setIsAsync(rs.getInt(10));
                bm.setChargeResult(rs.getString(11));
                bm.setServiceName(rs.getString(12));
                bm.setClientDesc(rs.getString(13));
                dtList.add(bm);
            }

            chargeModel.setDiameterList(dtList);
            StringBuffer sqlCount = new StringBuffer("SELECT COUNT (0) from (");
            sqlCount.append(sqlcc);
            sqlCount.append(" )");
            pstm = c.prepareStatement(sqlCount.toString());
            LOG.info(" count " + sqlCount);
            rs = pstm.executeQuery();
            while (rs.next()) {
                chargeModel.setRowCount(rs.getBigDecimal(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return chargeModel;
    }

    public static List<ComboModel> diameterResponseCodeList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = "  SELECT code_id, code_desc\n"
                    + "    FROM diameter_response_codes\n"
                    + "ORDER BY code_desc ";
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

    public static List<ComboModel> tarifPacksList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ComboModel> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            String sql = "   SELECT pack_id, pack_name\n"
                    + "    FROM chgw_tariff_packs\n"
                    + "ORDER BY pack_name";
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
        DiameterChargeModel model = new DiameterChargeModel();
        model.setEnd(BigDecimal.TEN);
//        List<DiameterCharge> list = diameterChargeList(model).getDiameterList();
//        for (DiameterCharge arg : list) {
//            System.out.println(arg.toString()
//            );
//        }
    }
}
