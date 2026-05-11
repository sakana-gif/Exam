package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {

    /** データソース */
    static DataSource ds;

    /**
     * データベースへのコネクションを返す
     * @return データベースへのコネクション
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        if (ds == null) {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/exam");
        }
        return ds.getConnection();
    }

    /**
     * statementとconnectionを閉じる共通処理
     * @param statement
     * @param connection
     * @throws SQLException
     */
    public void close(PreparedStatement statement, Connection connection) throws SQLException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
    }
}