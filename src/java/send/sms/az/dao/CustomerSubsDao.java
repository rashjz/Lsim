/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.model.SubCustomer;
import send.sms.az.model.ext.SubCustomerModel;

/**
 *
 * @author Mobby
 */
public class CustomerSubsDao {

    private static final Logger LOG = Logger.getLogger(CustomerSubsDao.class.getName());

    public SubCustomerModel subsCustomerList(SubCustomerModel model) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<SubCustomer> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT rec_id,\n"
                    + "       subs_name,\n"
                    + "       msisdn,\n"
                    + "       subs_date,\n"
                    + "       unsubs_date,\n"
                    + "       last_charge_date,\n"
                    + "       next_charge_date,\n"
                    + "       CASE sc.status\n"
                    + "          WHEN 'a' THEN 'active'\n"
                    + "          WHEN 'd' THEN 'deactive'\n"
                    + "          ELSE 'UNKNOWN'\n"
                    + "       END\n"
                    + "          AS subs_status,\n"
                    + "       agent_name\n"
                    + "  FROM subs_customers sc\n"
                    + "       INNER JOIN subs_list_variants sv ON sv.variant_id = sc.subs_variant_id\n"
                    + "       INNER JOIN subs_list sl ON sl.subs_id = sv.subs_id\n"
                    + "       INNER JOIN subs_agents ja ON ja.agent_id = sc.agent_id ");
            sql.append("  WHERE msisdn LIKE '" + model.getMsisdn() + "%' ");

            if (model.getSubscription() != null && !model.getSubscription().equals("0")) {
                sql.append("  AND subs_name LIKE '" + model.getSubscription() + "%' ");
            }
            if (model.getStatus() != null && !model.getStatus().equals("0")) {
                sql.append("  AND sc.status=" + model.getStatus() + " ");
            }
            if (model.getAgent_name() != null && !model.getAgent_name().equals("0")) {
                sql.append("  AND agent_name LIKE '" + model.getAgent_name() + "%' ");
            }
//            String sqlcc = sql.substring(16, sql.length());
//            sql.append(" ) WHERE row_num between " + chargeModel.getStart() + " and " + chargeModel.getEnd());
//            LOG.info(sql.toString());
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                SubCustomer bm = new SubCustomer();
                bm.setRec_id(rs.getBigDecimal(1));
                bm.setSubs_name(rs.getString(2));
                bm.setMsisdn(rs.getString(3));
                bm.setSub_date(rs.getTimestamp(4));
                bm.setUnSubs_date(rs.getTimestamp(5));
                bm.setLastCharge_date(rs.getTimestamp(6));
                bm.setNextCharge_date(rs.getTimestamp(7));
                bm.setSubs_status(rs.getString(8));
                bm.setAgent_name(rs.getString(9));
                dtList.add(bm);
            }

            model.setCustomers(dtList);
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
        return model;
    }

    public List<String> subsNameList() {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<String> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("select subs_name from subs_list group by subs_name");
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                dtList.add(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return dtList;
    }

}
