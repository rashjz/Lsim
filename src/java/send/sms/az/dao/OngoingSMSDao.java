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
import send.sms.az.model.ext.OngoingModel;
import send.sms.az.model.Ongoing_SMS;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;

/**
 *
 * @author rasha_000
 */
public class OngoingSMSDao {

    private static final Logger LOG = Logger.getLogger(OngoingSMSDao.class.getName());

    public   OngoingModel OngoingList(OngoingModel model) {
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Ongoing_SMS> menuList = new ArrayList<Ongoing_SMS>();
        try {
              c = DataBaseHelper.connect(); 
//            c = DatabaseUtil.connect();
            String sqlCount = "  select count(0) as item_count from smpp_outgoing i\n"
                    + "   left join smpp_hosts h on i.host_name=h.host_name\n"
                    + "   left join mobile_operators o on h.operator_id=o.operator_id";

            String sql = "SELECT *\n"
                    + "  FROM (SELECT I.SMSC_ID,\n"
                    + "               o.operator_name,\n"
                    + "               source_addr,\n"
                    + "               destination_addr,\n"
                    + "               msg_body,\n"
                    + "               date_added,\n"
                    + "               dsf.filter_name AS status,\n"
                    + "               app_name,\n"
                    + "               DECODE (ec.err_description,\n"
                    + "                       NULL, i.err_code,\n"
                    + "                       ec.err_description)\n"
                    + "                  AS err_desc,\n"
                    + "               ROW_NUMBER () OVER (ORDER BY I.SMSC_ID) row_num\n"
                    + "          FROM smpp_outgoing i\n"
                    + "               LEFT JOIN smpp_hosts h ON i.host_name = h.host_name\n"
                    + "               LEFT JOIN mobile_operators o ON h.operator_id = o.operator_id\n"
                    + "               LEFT JOIN\n"
                    + "               error_codes ec\n"
                    + "                  ON i.err_code =\n"
                    + "                        'NegativeResponseException: ' || ec.err_code\n"
                    + "               LEFT JOIN delivery_status_filters dsf\n"
                    + "                  ON (is_status_filter_match (i.status,\n"
                    + "                                              i.final_status,\n"
                    + "                                              dsf.filter_code) = 1))\n"
                    + " WHERE row_num BETWEEN ? AND ?";
            pstm = c.prepareStatement(sql.toString());
            pstm.setBigDecimal(1, model.getStart());
            pstm.setBigDecimal(2, model.getEnd());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Ongoing_SMS bl = new Ongoing_SMS();
                bl.setId(rs.getBigDecimal(1));
                bl.setOperatorName(rs.getString(2));
                bl.setSourceAddr(rs.getString(3));
                bl.setDestAddr(rs.getString(4));
                bl.setMsgBody(rs.getString(5));
//                LOG.info(rs.getDate(6).toString());
                bl.setDateAdded(rs.getDate(6));
                bl.setOsStatus(rs.getString(7));
                bl.setAppName(rs.getString(8));
                bl.setAppName(rs.getString(9));
                bl.setErrDesc(rs.getString(10));
                menuList.add(bl);
            }
            model.setList(menuList);
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

    public static void main(String[] args) {
        OngoingModel model = new OngoingModel();
        model.setStart(new BigDecimal(0));
        model.setEnd(new BigDecimal(10));
//        for (Ongoing_SMS arg : OngoingList(model).getList()) {
//            System.out.println(arg.toString());
//        }
        System.out.println(model.getRowCount());
    }
}
