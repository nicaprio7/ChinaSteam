package com.xin.test;

import com.xin.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author WUDIXIN
 * @Date 2024/1/8 10:46
 */
public class TestJDBC {
    public static void Test01() throws SQLException, ClassNotFoundException {
        Connection conn = DBUtil.getConn();
        Statement statement = conn.createStatement();
        String sql = "select * from user";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
        }

        DBUtil.close(conn, statement, rs);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Test01();
    }
}
