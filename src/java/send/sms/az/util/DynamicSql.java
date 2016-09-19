/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

import java.util.Map;
import org.apache.log4j.Logger;
import send.sms.az.model.ext.TransModel;

/**
 *
 * @author rasha_000
 */
public class DynamicSql {

    private static final Logger LOG = Logger.getLogger(DynamicSql.class.getName());

    public static String createSQL(Map typeMap, TransModel model, String strShortNum, boolean order) {
        StringBuffer sql = new StringBuffer("SELECT *\n"
                + "  FROM (");

        if (typeMap.size() == 0 | typeMap.get("1") != null) {
            sql.append("SELECT '1' AS trans_type,\n"
                    + "               I.MSG_ID,\n"
                    + "               in_date AS trans_date,\n"
                    + "               TO_CHAR (date_processed, 'yyyy-mm-dd hh24:mi') AS done_date,\n"
                    + "               source_addr AS msisdn,\n"
                    + "               destination_addr AS short_number,\n"
                    + "               TO_CHAR (msg_body) AS trans_desc,\n"
                    + "                TRUNC (nvl(LENGTH (TO_CHAR (msg_body)),0) / 160) +1 as smssize,\n"
                    + "               DECODE (is_processed,\n"
                    + "                       1, 'Processed',\n"
                    + "                       0, 'Not processed',\n"
                    + "                       'Error')\n"
                    + "                  AS trans_result,\n"
                    + "               op.operator_name,\n"
                    + "               i.host_name,\n"
                    + "               app_name,\n"
                    + "               'N/A' AS client,\n"
                    + "               ROW_NUMBER () OVER (ORDER BY I.MSG_ID) row_num\n"
                    + "               \n"
                    + "          FROM smpp_incoming i\n"
                    + "               LEFT JOIN smpp_hosts h ON i.host_name = h.host_name\n"
                    + "               LEFT JOIN mobile_operators op\n"
                    + "                  ON op.operator_id = h.operator_id\n");
//where clause for Incoming
            if (model.getFromdate() != null && model.getTodate() != null) {
//                    LOG.info(model.getTodate().toString());
                sql.append(" WHERE in_date between TO_DATE('" + ConvertUtil.dateToString(model.getFromdate()) + "', 'yyyy-MM-dd hh24:mi:ss') and  TO_DATE('" + ConvertUtil.dateToString(model.getTodate()) + "', 'yyyy-MM-dd hh24:mi:ss') ");
            }
            if (!model.getSearch().getMsisdn().equals("")) {
                sql.append(" and source_addr like '" + model.getSearch().getMsisdn() + "%' ");
            }
            if (model.getSearch().getApp_name() != null && !model.getSearch().getApp_name().equals("")) {
                sql.append(" and app_name like '" + model.getSearch().getApp_name() + "%' ");
            }
            if (model.getSearch().getTrans_desc() != null && !model.getSearch().getTrans_desc().equals("")) {
                sql.append(" and TO_CHAR (msg_body)  like '" + model.getSearch().getTrans_desc() + "%' ");
            }
            if (!model.getSearch().getOperator_name().equals("0")) {
                sql.append(" and op.operator_name  like '" + model.getSearch().getOperator_name() + "'");
            }
            if (!model.getSearch().getHost_name().equals("0")) {
                sql.append(" and i.host_name  like '" + model.getSearch().getHost_name() + "'");
            }
            if (!model.getSearch().getTrans_result().equals("-1")) {
                sql.append(" and is_processed like  DECODE ('" + model.getSearch().getTrans_result() + "' ,   'Processed',1,'Not processed',  0,   -1) ");
            }
            if (model.getSelectedShortNum() != null & model.getSelectedShortNum().length > 0) {
                sql.append(" and destination_addr  in " + strShortNum + " ");
            }
//            if (order && model.getSortField() != null && !model.getSortField().equals("")) {
//                sql.append(" ORDER BY " + model.getSortField() + " " + model.getSortOrder());
//            }
        }
        LOG.info(typeMap.get("3") + "");
        if (typeMap.size() == 0 | (typeMap.get("1") != null & typeMap.size() > 1 & typeMap.get("3") == null) | (typeMap.get("1") != null & typeMap.get("2") != null & typeMap.get("3") != null)) {
//eger 2 selecti varsa ya da 3 varsa
            sql.append("        UNION ALL \n");
        }
        if (typeMap.size() == 0 | typeMap.get("2") != null) {
            sql.append("        SELECT '2' AS trans_type,\n"
                    + "               O.MSG_ID,\n"
                    + "               date_added AS trans_date,\n"
                    + "               TO_CHAR (done_date, 'yyyy-mm-dd hh24:mi'),\n"
                    + "               destination_addr AS msisdn,\n"
                    + "               source_addr AS short_number,\n"
                    + "               TO_CHAR (msg_body) AS trans_desc,\n"
                    + "                 TRUNC (nvl(LENGTH (TO_CHAR (msg_body)),0) / 160) +1   as smssize,\n"
                    + "               DECODE (o.status,\n"
                    + "                       0, 'Queued',\n"
                    + "                       1, DECODE (final_status, NULL, 'Sent', final_status),\n"
                    + "                       2, 'Error',\n"
                    + "                       3, 'Black list'),\n"
                    + "               op.operator_name,\n"
                    + "               o.host_name,\n"
                    + "               app_name,\n"
                    + "               u.login,\n"
                    + "                 ROW_NUMBER () OVER (ORDER BY O.MSG_ID) row_num\n"
                    + "          FROM smpp_outgoing o\n"
                    + "               LEFT JOIN ui_users u ON o.user_id = u.user_id\n"
                    + "               LEFT JOIN smpp_hosts h ON o.host_name = h.host_name\n"
                    + "               LEFT JOIN mobile_operators op\n"
                    + "                  ON op.operator_id = h.operator_id\n");
//where clause for Outgoing
            if (model.getFromdate() != null && model.getTodate() != null) {
                sql.append(" WHERE SEND_UNTIL between TO_DATE('" + ConvertUtil.dateToString(model.getFromdate()) + "', 'yyyy-MM-dd hh24:mi:ss') and  TO_DATE('" + ConvertUtil.dateToString(model.getTodate()) + "', 'yyyy-MM-dd hh24:mi:ss') ");
            }//sent at deyisdim send until
            if (!model.getSearch().getMsisdn().equals("")) {
                sql.append("  and destination_addr like '" + model.getSearch().getMsisdn() + "%' ");
            }
            if (model.getSearch().getApp_name() != null && !model.getSearch().getApp_name().equals("")) {
                sql.append(" and app_name like '" + model.getSearch().getApp_name() + "%' ");
            }
            if (model.getSearch().getTrans_desc() != null && !model.getSearch().getTrans_desc().equals("")) {
                sql.append(" and TO_CHAR (msg_body)  like '" + model.getSearch().getTrans_desc() + "%' ");
            }
            if (model.getSelectedShortNum() != null & model.getSelectedShortNum().length > 0) {
                sql.append(" and source_addr  in " + strShortNum + " ");
            }
            if (!model.getSearch().getOperator_name().equals("0")) {
                sql.append(" and op.operator_name  like '" + model.getSearch().getOperator_name() + "'");
            }
            if (!model.getSearch().getHost_name().equals("0")) {
                sql.append(" and o.host_name  like '" + model.getSearch().getHost_name() + "'");
            }
            if (!model.getSearch().getClient().equals("0")) {
                sql.append(" and   U.LOGIN like '" + model.getSearch().getClient() + "'");
            }
            if (!model.getSearch().getTrans_result().equals("-1")) {
                sql.append(" and o.status like  DECODE ('" + model.getSearch().getTrans_result() + "' ,   'Queued',0,'Sent',1,'Error',  2, 'Black list',3,  -1) ");
            }
//            if (order && model.getSortField() != null && !model.getSortField().equals("")) {
//                sql.append(" ORDER BY " + model.getSortField() + " " + model.getSortOrder());
//            }

        }
        if (typeMap.size() == 0 | (typeMap.get("3") != null & typeMap.size() > 1)) {//eger 3 selecti varsa
            sql.append("        UNION ALL\n");
        }//eger ozunden qabag qosulma varsa ve 
        if (typeMap.size() == 0 | typeMap.get("3") != null) {
            sql.append("        SELECT '3' AS trans_type,\n"
                    + "               dr.REQ_ID,\n"
                    + "               date_added AS trans_date,\n"
                    + "               TO_CHAR (date_processed, 'yyyy-mm-dd hh24:mi') done_date,\n"
                    + "               msisdn,\n"
                    + "               'N/A' AS short_number,\n"
                    + "               srv.service_name || ' (' || client_charge_desc || ')'\n"
                    + "                  AS trans_desc,\n"
                    + "                1 as smssize,\n"
                    + "               drc.code_desc,\n"
                    + "               op.operator_name,\n"
                    + "               'N/A',\n"
                    + "               'Charging gateway',\n"
                    + "               u.login,\n"
                    + "                 ROW_NUMBER () OVER (ORDER BY DR.REQ_ID) row_num\n"
                    + "          FROM diameter_requests dr\n"
                    + "               INNER JOIN chgw_services srv ON dr.service_id = srv.service_id\n"
                    + "               LEFT JOIN ui_users u ON dr.client_id = u.user_id\n"
                    + "               LEFT JOIN diameter_response_codes drc\n"
                    + "                  ON dr.diameter_answer = drc.code_id\n"
                    + "               LEFT JOIN diameter_tariffs t\n"
                    + "                  ON dr.diam_tariff_id = t.tariff_id\n"
                    + "               LEFT JOIN mobile_operators op\n"
                    + "                  ON t.operator_id = op.operator_id");
            //where clause for Charging
            if (model.getFromdate() != null && model.getTodate() != null) {
                sql.append(" WHERE date_processed between TO_DATE('" + ConvertUtil.dateToString(model.getFromdate()) + "', 'yyyy-MM-dd hh24:mi:ss') and  TO_DATE('" + ConvertUtil.dateToString(model.getTodate()) + "', 'yyyy-MM-dd hh24:mi:ss') ");
            }
            if (!model.getSearch().getMsisdn().equals("")) {
                sql.append(" and msisdn like '" + model.getSearch().getMsisdn() + "%' ");
            }
            if (model.getSearch().getTrans_desc() != null && !model.getSearch().getTrans_desc().equals("")) {
                sql.append(" and srv.service_name like '" + model.getSearch().getTrans_desc() + "%' ");
            }
            if (!model.getSearch().getOperator_name().equals("0")) {
                sql.append(" and op.operator_name  like '" + model.getSearch().getOperator_name() + "'");
            }
            if (!model.getSearch().getClient().equals("0")) {
                sql.append(" and   U.LOGIN like '" + model.getSearch().getClient() + "'");
            } //  'Operator error'
            if (!model.getSearch().getTrans_result().equals("-1")) {
                sql.append(" and drc.CODE_ID like '" + model.getSearch().getTrans_result() + "'");
            }
        }
        sql.append(" )  t ");
        model.setExpSQL(sql.toString());//jasper export
        sql.append(" WHERE row_num BETWEEN ? AND ? ");
        if (order && model.getSortField() != null && !model.getSortField().equals("")) {
            sql.append(" ORDER BY " + model.getSortField() + " " + model.getSortOrder());
        }
        return sql.toString();
    }
}
