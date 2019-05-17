package com.myjpa.springboot.dao.base;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrcalDBUtil implements DBUtil {

    // JDBC 驱动名及数据库 URL
    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/dbsport";

    // 数据库的用户名与密码，需要根据自己的设置
    static String USER = "root";
    static String PASS = "123456";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库
     *
     * @return
     * @throws Exception
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 关闭连接
     * @param objects
     * 可以传入复数个需要关闭的对象 类型只能为connect para resultset
     */
    public void close(Object... objects) {
        try {
            for(Object object : objects){
                if(object != null){
                    String methodName = "close";
                    Method method = object.getClass().getMethod(methodName,null);
                    method.invoke(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
