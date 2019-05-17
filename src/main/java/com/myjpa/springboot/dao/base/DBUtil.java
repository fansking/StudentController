package com.myjpa.springboot.dao.base;

import java.sql.Connection;

public interface DBUtil {
    public Connection getConnection();
    public void close(Object... objects);
}
