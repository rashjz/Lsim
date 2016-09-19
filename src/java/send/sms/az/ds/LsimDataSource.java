/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.ds;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale; 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author Rashad Javadov
 */
public class LsimDataSource extends LsimDS {
    private static final Logger logger = Logger.getLogger(LsimDataSource.class.getName());

    private final String jndiName;

    public LsimDataSource(String jndiName) {
        this.jndiName = jndiName;
    }

    @Override
    public Connection connect() throws SQLException { 
        Connection connection = null;
        Locale def = Locale.getDefault();
        try {

            Locale.setDefault(new Locale("en", "US"));
            Context initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env");
            //setting up in context init
            DataSource ds = (DataSource) initContext.lookup(jndiName);
            connection = ds.getConnection();
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Locale.setDefault(def);
        } 
        return connection;
    }

}
