package com.xin.util;

import java.sql.*;

/**
 * @Author WUDIXIN
 * @Date 2024/1/8 10:34
 */
public class DBUtil {

    // 数据库驱动
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // 数据库 URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/steam?useSSL=false";

    // 数据库用户名和密码
    static final String USER = "root";
    static final String PASS = "xin123456";

    // 获取连接
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }

    // 关闭连接
    public static void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        rs.close();
        stmt.close();
        conn.close();
    }
}
