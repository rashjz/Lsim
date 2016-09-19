/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

/**
 *
 * @author Mobby
 */
public class SQL {

    public static String sqll = "SELECT *\n"
            + "  FROM (SELECT 'Incoming SMS' AS trans_type,\n"
            + "               in_date AS trans_date,\n"
            + "               TO_CHAR (date_processed, 'yyyy-mm-dd hh24:mi') AS done_date,\n"
            + "               source_addr AS msisdn,\n"
            + "               destination_addr AS short_number,\n"
            + "               TO_CHAR (msg_body) AS trans_desc,\n"
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
            + "                  ON op.operator_id = h.operator_id\n"
            + " WHERE in_date between TO_DATE('2014-12-23 14:20:45', 'yyyy-MM-dd hh24:mi:ss') and  TO_DATE('2015-04-27 14:20:45', 'yyyy-MM-dd hh24:mi:ss')         UNION ALL \n"
            + "        SELECT 'Outgoing SMS' AS trans_type,\n"
            + "               date_added AS trans_date,\n"
            + "               TO_CHAR (done_date, 'yyyy-mm-dd hh24:mi'),\n"
            + "               destination_addr AS msisdn,\n"
            + "               source_addr AS short_number,\n"
            + "               TO_CHAR (msg_body) AS trans_desc,\n"
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
            + "                  ON op.operator_id = h.operator_id\n"
            + " WHERE SEND_AT between TO_DATE('2014-12-23 14:20:45', 'yyyy-MM-dd hh24:mi:ss') and  TO_DATE('2015-04-27 14:20:45', 'yyyy-MM-dd hh24:mi:ss')         UNION ALL\n"
            + "        SELECT 'Charging' AS trans_type,\n"
            + "               date_added AS trans_date,\n"
            + "               TO_CHAR (date_processed, 'yyyy-mm-dd hh24:mi'),\n"
            + "               msisdn,\n"
            + "               'N/A' AS short_number,\n"
            + "               srv.service_name || ' (' || client_charge_desc || ')'\n"
            + "                  AS trans_desc,\n"
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
            + "                  ON t.operator_id = op.operator_id WHERE date_processed "
            + "between TO_DATE('2014-12-23 14:20:45', 'yyyy-MM-dd hh24:mi:ss')"
            + " and  TO_DATE('2015-04-27 14:20:45', 'yyyy-MM-dd hh24:mi:ss')  )  t";

}
