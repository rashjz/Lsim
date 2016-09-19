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
import send.sms.az.model.DTarifs;
import send.sms.az.model.OngoingSMSUser;
import send.sms.az.model.ext.TarifSearchModel;
import send.sms.az.util.ConvertUtil;

/**
 *
 * @author Mobby
 */
public class OutgoingByUserDao {
    
    private static final Logger LOG = Logger.getLogger(OutgoingByUserDao.class.getName());
    
    public static List<OngoingSMSUser> ongoingSMSbyUser(TarifSearchModel model) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<OngoingSMSUser> dtList = new ArrayList<>();
        try {
            c = DataBaseHelper.connect();
//            c = DatabaseUtil.connect();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT tbl.*,\n"
                    + "         NVL (\"'DELIVRD'\", 0)\n"
                    + "       + NVL (\"'UNDELIV'\", 0)\n"
                    + "       + NVL (\"'EXPIRED'\", 0)\n"
                    + "       + NVL (\"'Queued'\", 0)\n"
                    + "       + NVL (\"'Error - not sent'\", 0)\n"
                    + "       + NVL (\"'Waiting for status'\", 0)\n"
                    + "          AS total\n"
                    + "  FROM (WITH t\n"
                    + "             AS (  SELECT u.login AS username,\n"
                    + "                          DECODE (\n"
                    + "                             o.status,\n"
                    + "                             0, 'Queued',\n"
                    + "                             2, 'Error - not sent',\n"
                    + "                             1, DECODE (o.final_status,\n"
                    + "                                        NULL, 'Waiting for status',\n"
                    + "                                        o.final_status))\n"
                    + "                             AS current_status,\n"
                    + "                          SUM (GREATEST (CEIL (LENGTH (o.msg_body) / 160), 1))\n"
                    + "                             AS sms_count\n"
                    + "                     FROM smpp_outgoing o\n"
                    + "                          INNER JOIN ui_users u ON o.user_id = u.user_id\n");
            if (model.getEndDate() != null && model.getStartDate() != null) {
                sql.append(" where o.date_added >= to_date('" + ConvertUtil.dateToString(model.getStartDate(), "MM/dd/YYYY") + "', 'MM/DD/YYYY')");
                sql.append("  and o.date_added <= to_date('" + ConvertUtil.dateToString(model.getEndDate(), "MM/dd/YYYY") + "', 'MM/DD/YYYY')");
            }
            
            sql.append("                 GROUP BY u.login,\n"
                    + "                          DECODE (\n"
                    + "                             o.status,\n"
                    + "                             0, 'Queued',\n"
                    + "                             2, 'Error - not sent',\n"
                    + "                             1, DECODE (o.final_status,\n"
                    + "                                        NULL, 'Waiting for status',\n"
                    + "                                        o.final_status)))\n"
                    + "          SELECT *\n"
                    + "            FROM t PIVOT (SUM (sms_count)\n"
                    + "                   FOR current_status\n"
                    + "                   IN  ('DELIVRD',\n"
                    + "                       'UNDELIV',\n"
                    + "                       'EXPIRED',\n"
                    + "                       'Queued',\n"
                    + "                       'Error - not sent',\n"
                    + "                       'Waiting for status'))\n"
                    + "        ORDER BY username) tbl ");
            LOG.info(sql.toString());
            pstm = c.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                OngoingSMSUser bm = new OngoingSMSUser();
                bm.setUsername(rs.getString(1));
                bm.setDelivrd(rs.getBigDecimal(2));
                bm.setUndeliv(rs.getBigDecimal(3));
                bm.setExpired(rs.getBigDecimal(4));
                bm.setQueued(rs.getBigDecimal(5));
                bm.setNotSent(rs.getBigDecimal(6));
                bm.setWait_status(rs.getBigDecimal(7));
                bm.setTotal(rs.getBigDecimal(8));
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
        for (OngoingSMSUser arg : ongoingSMSbyUser(null)) {
            LOG.info(arg.toString());
        }
    }
}
