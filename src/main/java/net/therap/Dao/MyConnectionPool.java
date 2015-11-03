package main.java.net.therap.Dao;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import main.java.net.therap.Facebook.DatabaseConstants;

import javax.sql.PooledConnection;
import java.sql.*;

/**
 * @author babar
 * @since 11/1/15
 */
public class MyConnectionPool {

    private static MysqlConnectionPoolDataSource dataSource=null;
    private static PooledConnection pooledConnection=null;
    //private static Connection connection=null;
    private static int connectionCounter = 0;
    public static Connection getConnection() throws SQLException {
        if(dataSource==null){
            dataSource = new MysqlConnectionPoolDataSource();
            dataSource.setServerName(DatabaseConstants.HOST_NAME);
            dataSource.setDatabaseName(DatabaseConstants.DB_NAME);
            dataSource.setUser(DatabaseConstants.USER_NAME);
            dataSource.setPassword(DatabaseConstants.PASSWORD);
        }
        if(pooledConnection==null){
            try {
                pooledConnection = dataSource.getPooledConnection();
            } catch (SQLException e) {
                System.out.println("Error while getting pooled connection from data source");
            }
            System.out.println("Creating Connection "+connectionCounter);
        }
        return pooledConnection.getConnection();
    }
}
