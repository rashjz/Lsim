/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.ds;

import send.sms.az.ds.LsimDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mobby
 */
public class DataBaseHelper {

    private static LsimDataSource lsimDataSource;

    public static void setLsimDataSource(LsimDataSource dataSource) {
        DataBaseHelper.lsimDataSource = dataSource;
    }

    public static Connection connect() throws SQLException {
        return lsimDataSource.connect();
    }

}
