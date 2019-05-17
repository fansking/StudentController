package com.myjpa.springboot.dao.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

public abstract class BaseDao<T> {
    DBUtil dbUtil ;
    Class<T> clazz;

    public BaseDao() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // BaseDaoImpl<User>
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
        dbUtil = new MysqlDBUtil();
    }

    /**
     * 查询数据库的通用方法
     *
     * @param sql     查询的SQL语句
     * @param objects sql的参数
     * @return 结果集
     */
    public ResultSet getResultSet(String sql, Object... objects) {
        Connection conn = dbUtil.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            if (objects != null && objects.length > 0) {
                for (int i = 0; i < objects.length; i++) {
                    statement.setObject((i + 1), objects[i]);
                }
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //dbUtil.close(conn);
        }
        return null;
    }


    /**
     * 增删改的通用方法
     * @param sql
     * @param objects 参数
     * @return 影响的行数
     */
    public int update(String sql, Object... objects) {
        Connection conn = dbUtil.getConnection();
        int num = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    statement.setObject((i + 1), objects[i]);
                }
            }
            num = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(conn);
        }
        return num;
    }

    /**
     * 通过sql查询，并返回List对象
     *
     * @param sql    要执行的sql语句
     * @param params 传入的参数(具体类型和个数不确定，所以用Object数组接收)
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> findBySql(String sql, Object... params) {
        Connection conn = dbUtil.getConnection();
        List<T> entityList = null;

        try {
            ResultSet rs = getResultSet(sql,params);

            entityList = new ArrayList<T>();

            // 如果结果集对象不为null 而且不为空
            if (rs != null && rs.next()) {
                rs.previous();
                //得到 结果集 rs的元数据
                ResultSetMetaData data = rs.getMetaData();

                while (rs.next()) {

                    // 创建泛型对象
                    // 通过class.forName 加载已知类, clazz.getName() 得到实体类类名,
                    // 调用newInstance方法 获得该实体类对象，并向上转型;
                    T t = (T) Class.forName(clazz.getName()).newInstance();

                    // 通过rs的元数据    获得查到的结果集中有多少列
                    int count = data.getColumnCount();

                    // 循环遍历所有列，得到所有列对应的值, 并得到所有setXXX()方法,将所有值通过set方法赋值给
                    // 泛型对象 Object o
                    for (int i = 1; i <= count; i++) {
                        // 获得列名
                        String columnName = data.getColumnName(i);
                        // 获得列值
                        Object value = rs.getObject(i);

                        // //生成要执行的方法,因为方法的的第二个单词大写所有需要转换大小写
                        // 生成要执行的SetXXX()方法 (如：SetAdminName()该方法和属性
                        // (列名adminName)区别是列名首字母是小写的)
                        // 注意！！！ 应该用replaceFirst 方法 (如果用replace方法)
                        String methodName = "set"
                                + columnName.replaceFirst(columnName.charAt(0)
                                + "", new String(columnName.charAt(0)
                                + "").toUpperCase());


                        // //利用反射机制，生成setXX()方法的 Method 对象并调用(invoke)该setXX()方法。
                        // 通过泛型对象t 的getClass() 方法得到其类, 然后 通过刚得到 方法名
                        // 调用类的setXXX()方法. 其中 getMethod 方法需要参数 分别为 (方法名,参数列表的类型)
                        try {
                        Method method = t.getClass().getMethod(methodName,
                                value.getClass());

                        // 调用method 的invoke 方法 调用得到的方法(即setXXX(value) 方法)

                            method.invoke(t, value);
                        }catch (Exception e){
                            System.out.println("method: "+methodName+" fail!!");
                        }
                    }
                    // 将赋值 完成的泛型 对象 t 放入列表中 并返回这个列表
                    entityList.add((T) t);
                }
            }

            return entityList;

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            dbUtil.close(conn);
        }
        return null;
    }

    public List<HashMap<String,Object>> getResultMap(String sql,Object... params){
        Connection conn = dbUtil.getConnection();
        try {
            ResultSet rs = getResultSet(sql,params);
            List<HashMap<String,Object>> result = new ArrayList<>();
            if (rs != null && rs.next()) {
                rs.previous();
                ResultSetMetaData data = rs.getMetaData();
                while (rs.next()) {
                    int count = data.getColumnCount();
                    HashMap<String,Object> mapData = new HashMap<>();
                    for (int i = 1; i <= count; i++) {
                        String columnName = data.getColumnName(i);
                        Object value = rs.getObject(i);
                         mapData.put(columnName,value);
                    }
                   result.add(mapData);
                }
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            dbUtil.close(conn);
        }
        return null;
    }

    /**
     * 获取当前实体的类名转化后的对应表名
     * @param clazz
     * @return
     */
    public String getTableName(Class clazz) {
        String tableName = clazz.getSimpleName();
        tableName = tableName.substring(2, tableName.length()).toLowerCase();
        return tableName;
    }

    /**
     * 查询所有结果
     * @return
     */
    public List<T> findAll() {
        String sql = "select * " +
                "from ";
        String tableName = getTableName(clazz);
        sql += tableName;
        List<T> result = findBySql(sql);
        return result;
    }

    public void save(T entity) {
        try {
            List<T> info = findInfo(entity);
            if(info!=null&&info.size()>0){  // 已存在记录
                modifyInfo(entity);
            }else{
                saveInfo(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 内部的查找方法，通过传入的数据和上层调用方法的名称来确定查找的列
     * 不能被其他类调用，只能被子类调用
     * @param objects
     * @return
     */
    protected List<T> findBy(Object... objects){
        try {
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement element = stacktrace[2];
            String methodName = element.getMethodName();
            String findNames = methodName.substring(6,methodName.length());
            String[] columns = findNames.split("And");
            Map<String,Object> map = new HashMap<>();
            int i = 0;
            for(String column : columns){
                map.put(column.toLowerCase(),objects[i]);
                i++;
            }

            return findInfo(map);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据主键查找
     * @param objects
     * @return
     */
    public T findByPrimKey(Object... objects){
        List<String> primKeys = getPrimKey(getTableName(clazz));
        Map<String,Object> paramMap = new HashMap<>();
        int i = 0;
        for(String key : primKeys){
            paramMap.put(key,objects[i]);
            i++;
        }
        List<T> info = findInfo(paramMap);
        if(info != null && info.size()>0){
            return info.get(0);
        }
        else{
            return null;
        }
    }

    /**
     * 获取下一个id的值（hibernate自增）并使next_val加一
     * @return 下一个id的值
     */
    public Integer getNextId() {
        try {
            String sql = "select * from hibernate_sequence";
            ResultSet resultSet = getResultSet(sql);
            resultSet.next();
            Object object = resultSet.getObject(1);
            String updateSql = "update hibernate_sequence set next_val = next_val + 1";
            update(updateSql);
            Long result = (Long)object;
            return result.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 保存方法
     */
    public boolean saveInfo(T entity) {
        boolean flag = true;
        try {
            Class<?> cls = entity.getClass();
            //获取表名
            String tableName = clazz.getSimpleName();
            tableName = tableName.substring(2, tableName.length()).toLowerCase();
            //获取主键
            List<String> prykey = getPrimKey(tableName);
            //记录数据列
            List<String> filedList = new ArrayList<String>();
            //获取sql语句
            String sql = getsavesql(tableName, filedList);
            // 修改实体的主键
            entity = generatePrimKey(prykey,entity);
            //执行sql
            flag = excuteSQL(sql, entity, filedList);
        } catch (Exception e1) {
            flag = false;
            e1.printStackTrace();
        }
        return flag;
    }

    public T generatePrimKey(List<String> primKey,T entity){
        try {
            for (String key : primKey) {
                StringBuilder stringBuilder = new StringBuilder();
                String setMethodName = "set" + stringBuilder.append(Character.toUpperCase(key.charAt(0)))
                        .append(key.substring(1)).toString();
                String getMethodName = "get" + stringBuilder.toString();
                Method getMethod = entity.getClass().getMethod(getMethodName,null);
                Object result = getMethod.invoke(entity);

                // 仅对未传入默认主键值才让主键自增
                if(result == null && result.getClass() == Integer.class) {
                    Method setmethod = entity.getClass().getMethod(setMethodName, Integer.class);
                    setmethod.invoke(entity, getNextId());
                }
            }
            return entity;
        } catch (Exception e
        ){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改方法
     */
    public void modifyInfo(T e) {
        Class<?> cls = e.getClass();
        //获取表名
        String tableName = getTableName(e.getClass());
        //获取主键
        List<String> prykey = getPrimKey(tableName);
        //记录数据列
        List<String> filedList = new ArrayList<String>();
        //获取sql语句
        String sql = getmodifysql(tableName, prykey, filedList);
        //添加主键到集合
        filedList.addAll(prykey);
        //执行sql
        excuteSQL(sql, e, filedList);
    }

    /**
     * 删除方法
     */
    public void deleteInfo(Object... ids) {
        //获取表名
        String tableName = getTableName(clazz);
        //获取主键
        List<String > prykey = getPrimKey(tableName);
        //获取sql语句
        String sql = "delete from " + tableName + " where ";
        for(String key : prykey){
            sql += key+"=? and ";
        }
        if (sql.toString().endsWith("and ")) {
            sql = sql.substring(0,sql.length()-5);
        }
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = dbUtil.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            for(Object id : ids){
                pstm.setObject(i,id);
                i++;
            }
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           dbUtil.close(conn);
        }
    }

    /**
     * 查询全部方法
     */
    public List<T> findInfo(T e) {
        //获取表名
        String tableName = getTableName(clazz);
        //获取查询条件
        Map<String,Object> paramMap = getKeyMap(e);
        //获取sql
        String sql = getquerySQL(paramMap,tableName);
        // 执行sql
        return excutQuery(sql,paramMap);
    }

    public List<T> findInfo(Map paramMap) {
        //获取表名
        String tableName = getTableName(clazz);
        //获取查询条件
//        Map<String,Object> paramMap = getKeyMap(e);
        //获取sql
        String sql = getquerySQL(paramMap,tableName);
        //执行SQL
        return excutQuery(sql,paramMap);
    }

    /**
     * 单个查询方法
     */
    public T findById(Object id) {
        //获取表名
        String tableName = getTableName(clazz);
        //获取主键
        String prykey = getPrimKey(tableName).get(0);
        //获取sql
        String sql = "select * from " + tableName + " where 1 = 1 and " + prykey + " = ?";
        //执行SQL
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        T e = null;
        try {
            conn = dbUtil.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setObject(1, id);
            rs = pstm.executeQuery();
            List<T> list = getEntityList(rs);
            e = list.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbUtil.close(conn);
        }
        return e;
    }

    /**
     * 获取总条数
     *
     * @param paramMap
     * @param tableName
     * @return
     */
    private Integer getPagenumsss(Map<String, Object> paramMap, String tableName) {
        paramMap.remove("pageSize");
        paramMap.remove("pageNum");
        String sql = getquerySQL(paramMap, tableName);
        sql = "select count(*) from (" + sql + ") tempTab";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Integer pagenumsss = 0;
        try {
            conn = dbUtil.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                Object val = entry.getValue();
                if (val instanceof java.lang.String) {
                    pstm.setString(i, "%" + val.toString() + "%");
                } else if (val instanceof java.lang.Integer) {
                    pstm.setInt(i, Integer.parseInt(val.toString()));
                }
                i++;
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                pagenumsss = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(conn);
        }
        return pagenumsss;
    }

    /**
     * 获取查询SQL
     *
     * @param paramMap
     * @param tableName
     * @return
     */
    private String getquerySQL(Map<String, Object> paramMap, String tableName) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ")
                .append(tableName)
                .append(" where 1 = 1 ");
        List<String> columlist = getTableColumns(tableName);
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String columName = entry.getKey();
            for (String colnName : columlist) {
                if (colnName.equalsIgnoreCase(columName)) {
                    if (entry.getValue() instanceof java.lang.String) {
                        sql.append(" and ").append(columName).append(" like ?");
                    } else {
                        sql.append(" and ").append(columName).append("=?");
                    }
                    break;
                }
            }
        }
        return sql.toString();
    }

    /**
     * 获取查询条件
     *
     * @param e
     * @return
     */
    private Map<String, Object> getKeyMap(T entity) {
        Map<String, Object> paramMap = new HashMap<>();
        // 首先找到主键
        List<String> primKey = getPrimKey(this.getTableName(clazz));
        try {
            for (String key : primKey) {
                StringBuilder stringBuilder = new StringBuilder();
                String methodName = "get" + stringBuilder.append(
                        Character.toUpperCase(key.charAt(0))).append(key.substring(1)).toString();
                Method method = entity.getClass().getMethod(methodName, null);
                Object result = method.invoke(entity);
                paramMap.put(key,result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return paramMap;
    }


    /**
     * 获取主键
     *
     * @param tableName
     * @return
     */
    private List<String> getPrimKey(String tableName) {
        Connection conn = null;
        DatabaseMetaData metaData = null;
        ResultSet rs = null;
       List<String> primKeyName = new ArrayList<>();
        try {
            conn = dbUtil.getConnection();
            metaData = conn.getMetaData();
            rs = metaData.getPrimaryKeys(conn.getCatalog(), null, tableName.toUpperCase());
            while (rs.next()) {
                primKeyName.add(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(conn);
        }
        return primKeyName;
    }

    /**
     * 保存方法执行SQL
     *
     * @param sql
     * @param e
     * @param filedList
     * @return
     */
    private boolean excuteSQL(String sql, T entity, List<String> filedList) {
        boolean flag = true;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = dbUtil.getConnection();
            pstm = conn.prepareStatement(sql);
            //赋值
            int i = 1;
            for (String columName : filedList) {
                Object val = getFieldValue(entity, columName);
                if(columName.equals("id")){
                    val = getNextId();
                }
                pstm.setObject(i, val);
                i++;
            }
            pstm.execute();
        } catch (SQLException e1) {
            e1.printStackTrace();
            flag = false;
        } finally {
            dbUtil.close(conn);
        }
        return flag;
    }

    /**
     * 获取修改方法的SQL
     *
     * @param tableName
     * @param prykey
     * @param filedList
     * @return
     */
    private String getmodifysql(String tableName, List<String> prykey, List<String> filedList) {
        StringBuffer sql = new StringBuffer();
        sql.append("update ").append(tableName).append(" set ");
        List<String> columnList = getTableColumns(tableName);
        for (String columnName : columnList) {
            if (!prykey.contains(columnName)) {
                filedList.add(columnName);
                sql.append(columnName).append("=?,");
            }
        }
        if (sql.toString().endsWith(",")) {
            sql = new StringBuffer(sql.substring(0, sql.length() - 1));
        }
        sql.append(" where ");
        for(String key : prykey) {
            sql.append(key).append("=? and ");
        }
        if (sql.toString().endsWith("and ")) {
            sql = new StringBuffer(sql.substring(0, sql.length() - 5));
        }
        return sql.toString();
    }

    /**
     * 执行查询全部SQL
     * @param sql
     * @param paramMap
     */
    private List<T> excutQuery(String sql, Map<String, Object> paramMap) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            for (Entry<String,Object> entry : paramMap.entrySet()) {
                Object val = entry.getValue();
                if(val instanceof java.lang.String){
                    pstm.setString(i, "%"+val.toString()+"%");
                }else if(val instanceof java.lang.Integer){
                    pstm.setInt(i, Integer.parseInt(val.toString()));
                }
                i++;
            }
            rs = pstm.executeQuery();
            List<T> list = getEntityList(rs);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            dbUtil.close(conn);
        }
        return null;
    }

    /**
     * 获取表中属性
     *
     * @param entity
     * @param columName
     * @return
     */
    private Object getFieldValue(T entity, String columName) {
        Class<?> cls = entity.getClass();
        Object value = null;
        //获取类中的所有成员属性
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            //获取属性名称
            String fieldName = field.getName();
            //判断属性名称是否与列名相同
            if (fieldName.equalsIgnoreCase(columName)) {
                //根据规则获取方法名称
                String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    //根据方法名称获取方法对象
                    Method method = cls.getMethod(methodName);
                    //执行方法并获取返回值
                    value = method.invoke(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return value;
    }

    /**
     * 保存方法获取SQL
     *
     * @param tableName
     * @param prykey
     * @param filedList
     * @return
     */
    private String getsavesql(String tableName , List<String> filedList) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ").append(tableName).append(" (");
        List<String> columnList = getTableColumns(tableName);
        for (String string : columnList) {
//            if (!string.equalsIgnoreCase("id")) {
            sql.append(string).append(",");
            filedList.add(string);
//            }
        }
        if (sql.toString().endsWith(",")) {
            sql = new StringBuffer(sql.substring(0, sql.length() - 1));
        }
        sql.append(") value (");
        for (int i = 0; i < filedList.size(); i++) {

            sql.append("?,");

        }
        if (sql.toString().endsWith(",")) {
            sql = new StringBuffer(sql.substring(0, sql.length() - 1));
        }
        sql.append(")");

        return sql.toString();
    }

    /**
     * 获取表列
     *
     * @param tableName
     * @return
     */
    private List<String> getTableColumns(String tableName) {
        List<String> columnList = new ArrayList<String>();
        Connection conn = null;
        DatabaseMetaData metaData = null;
        ResultSet rs = null;
        conn = dbUtil.getConnection();
        try {
            metaData = conn.getMetaData();
            rs = metaData.getColumns(conn.getCatalog(), null, tableName.toUpperCase(), null);
            while (rs.next()) {
                String clumnName = rs.getString("COLUMN_NAME");
                columnList.add(clumnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(conn);
        }
        return columnList;
    }

    /**
     * 封装查询结果
     *
     * @param rs
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private List<T> getEntityList(ResultSet rs) throws Exception {
        List<T> list = new ArrayList<T>();
        Field[] fields = clazz.getDeclaredFields();
        while (rs.next()) {
            T e = (T) clazz.newInstance();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    String columName = field.getName();
                    String fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {
                        field.set(e, rs.getString(columName));
                    } else if ("Integer".equals(fieldType)) {
                        field.set(e, rs.getInt(columName));
                    }else if ("Boolean".equals(fieldType)){
                        field.set(e,rs.getBoolean(columName));
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            list.add(e);
        }
        return list;
    }

    public List<List<Object>> findWithSqlAndLeftJoin(String sql,List<Class> classes,Object... params) {
        try {
            ResultSet rs = getResultSet(sql, params);
            List<List<Object>> result = new ArrayList<>();
            if (rs != null && rs.next()) {
                rs.previous();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<String> getForeignTableNameAndKey(){
        List<String> names = new ArrayList<>();
        try {
            Connection conn = dbUtil.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getExportedKeys(conn.getCatalog(), null, getTableName(clazz).toUpperCase());
            while (rs.next()) {
//                exportKeyName.add(rs.getString("PKTABLE_NAME"));
//                exportKeyName.add(rs.getString("PKCOLUMN_NAME"));
                names.add(rs.getString("FKTABLE_NAME"));
                names.add(rs.getString("FKCOLUMN_NAME"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return names;
    }

    public String getSelectSql(){
        String result = "select * from "+getTableName(clazz)+" ";
        String curTableName = getTableName(clazz);
        String curPrimKey = getPrimKey(getTableName(clazz)).get(0);
        StringBuilder sb = new StringBuilder(result);
        List<String> foreignTableNameAndKey = getForeignTableNameAndKey();
        for(int i = 0 ; i < foreignTableNameAndKey.size() ; i+=2){
            String tableName = foreignTableNameAndKey.get(i);
            String id = foreignTableNameAndKey.get(i+1);
            sb.append("left join ").append(tableName).append(" on ").append(curTableName)
                    .append(".");
        }

        return result;
    }
}
