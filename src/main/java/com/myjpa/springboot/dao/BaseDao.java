package com.myjpa.springboot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
    private String driverName = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
    private String userName = "scott";
    private String pwd = "123";
    public Connection conn;
    public PreparedStatement statement;
    public ResultSet rs;

    /**
     * 连接数据库
     * @return
     * @throws Exception
     */
//    public Connection getConnection() {
//        try {
//            Class.forName(driverName);
//            conn = DriverManager.getConnection(url, userName, pwd);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//
//    /**
//     * 关闭数据库连接
//     *
//     * @throws Exception
//     */
//    public void close() throws Exception {
//        if (rs != null) {
//            rs.close();
//        }
//        if (statement != null) {
//            statement.close();
//        }
//        if (conn != null) {
//            conn.close();
//        }
//    }

    /**
     * 查询数据库的通用方法
     * @param sql  查询的SQL语句
     * @param objects  sql的参数
     * @return  结果集
     */
    public ResultSet executeQuery(String sql, Object... objects) {
      //  getConnection();
        try {
            statement = conn.prepareStatement(sql);
            if (objects != null && objects.length > 0) {
                for (int i = 0; i < objects.length; i++) {
                    statement.setObject((i + 1), objects[i]);
                }
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增删改的通用方法
     */
    public int update(String sql, Object... objects)  {
       // getConnection();
        int num = 0;
        try {
            statement = conn.prepareStatement(sql);
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    statement.setObject((i + 1), objects[i]);
                }
            }
            num = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
