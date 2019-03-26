package com.myjpa.springboot;

import com.myjpa.springboot.dao.AthleteDao;
import com.myjpa.springboot.dao.Athlete_competitionDao;
import com.myjpa.springboot.entity.dbentity.DBAthlete;
import com.myjpa.springboot.entity.dbentity.DBAthlete_competition;
import org.junit.Test;

import java.sql.*;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTests {
    @Test
    public void testJDBC(){
        // JDBC 驱动名及数据库 URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/dbsport";

        // 数据库的用户名与密码，需要根据自己的设置
        String USER = "root";
        String PASS = "123456";

        Connection conn = null;
        Statement stmt = null;

        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM athlete";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                int url = rs.getInt("age");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    @Test
    public void testDao1(){
        AthleteDao athleteDao = new AthleteDao();
        //athleteDao.findById(11);
        //List<DBAthlete> all = athleteDao.findAll();
        // List<DBAthlete> athletes = athleteDao.findBySql("select * from athlete ");
        DBAthlete dbAthlete = new DBAthlete(20, 20, "123123", "dfdfdf",
                "234645", false, "哈哈哈", 234,
                "呜呜呜", 5);
//        athleteDao.saveInfo(dbAthlete);
//        DBAthlete byPrimKey = athleteDao.findByPrimKey(14);
        List<DBAthlete> by = athleteDao.findByName("string");
        List<DBAthlete> byNameAndScores = athleteDao.findByNameAndScores("string", 0);
    }

    @Test
    public void testDao2(){
        Athlete_competitionDao athlete_competitionDao = new Athlete_competitionDao();
        //DBAthlete_competition byPrimKey = athlete_competitionDao.findByPrimKey(6, 8);
        DBAthlete_competition athlete_competition = new DBAthlete_competition(16,8,100,22,20d);
//        athlete_competitionDao.saveInfo(athlete_competition);
  //      athlete_competitionDao.modifyInfo(athlete_competition);
        athlete_competitionDao.deleteInfo(6,8);
    }
}
