/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types; 
import org.apache.log4j.Logger;
import send.sms.az.ds.DataBaseHelper;
import send.sms.az.ds.DatabaseUtil;
import send.sms.az.model.IncomingSMS;

/**
 *
 * @author Mobby
 */
public class SendTestSMSDao {
    private static final Logger LOG = Logger.getLogger(SendTestSMSDao.class.getName());
    
    public static IncomingSMS insertTestSMS(IncomingSMS isms) {
        Connection c = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        
        try {
//            c = DatabaseUtil.connect();
            c = DataBaseHelper.connect();
            String sql = "BEGIN\n"
                    + "   pkg_smpp.register_received_sms (?,\n"
                    + "                                   SYSDATE,\n"
                    + "                                   0,\n"
                    + "                                   ?,\n"
                    + "                                   ?,\n"
                    + "                                   ?,\n"
                    + "                                   'F' || DBMS_RANDOM.VALUE);\n"
                    + "   ? := smpp_incoming_seq.CURRVAL;\n"
                    + "--   dbms_output.put_line(smpp_incoming_seq.CURRVAL);\n"
                    + "--   PKG_REQUEST_HANDLER.PROCESS_QUEUE (1);\n"
                    + "--   PKG_REQUEST_HANDLER.PROCESS_QUEUE (2);\n"
                    + "END;";
            pstm = c.prepareCall(sql.toString());
            pstm.setString(1, isms.getHostname());
            pstm.setString(2, isms.getDest_address());
            pstm.setString(3, isms.getSource_addres());
            pstm.setString(4, isms.getMessage());            
            
            pstm.registerOutParameter(5, Types.NUMERIC);
            int count = pstm.executeUpdate();
            if (count != 0) {
                isms.setMsgID(pstm.getBigDecimal(5));
                LOG.info(pstm.getBigDecimal(5)+"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(rs, pstm, c);
        }
        return isms;
    }
}
