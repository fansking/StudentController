package com.myjpa.springboot;

import com.myjpa.springboot.dao.AthleteDao;
import com.myjpa.springboot.dao.base.Athlete_competitionDao;
import com.myjpa.springboot.entity.Competition;
import com.myjpa.springboot.entity.Team;
import com.myjpa.springboot.entity.dbentity.DBAthlete;
import com.myjpa.springboot.entity.dbentity.DBAthlete_competition;
import com.myjpa.springboot.repository.AthleteCompetitionRepository;
import com.myjpa.springboot.repository.AthleteRepository;
import com.myjpa.springboot.repository.CompetitionRepository;
import com.myjpa.springboot.repository.TeamRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private AthleteCompetitionRepository athleteCompetitionRepository;

//    @Test
    /*public void init() {
        Athlete athlete1 = new Athlete("001", 12, "张三",
                "123235345", "哈哈哈", false,
                99, "", new HashSet<AthleteCompetition>());
        Athlete athlete2 = new Athlete("002", 12, "老王",
                "123242345345", "哈哈哈", false,
                99, "", new HashSet<AthleteCompetition>());

        Competition competition1 = new Competition("hahah", 12,
                false, true, new HashSet<AthleteCompetition>());


        AthleteCompetition athleteCompetition1 = new AthleteCompetition(athlete1, competition1, 1, 0, 0);
        AthleteCompetition athleteCompetition2 = new AthleteCompetition(athlete2, competition1, 1, 0, 0);


        competition1.getAthleteCompetitions().addAll(Arrays.asList(athleteCompetition2, athleteCompetition1));

        Team team1 = new Team();
        team1.setAthletes(Arrays.asList(athlete1, athlete2));
        athlete1.setTeam(team1);
        athlete2.setTeam(team1);
        team1.setName("asd");
        teamRepository.save(team1);
//        athlete1.setId(8);
//        athlete2.setId(10);

        athlete1.getAthleteCompetitions().add(athleteCompetition1);
        athlete2.getAthleteCompetitions().add(athleteCompetition2);

        //  List<Team> all = teamRepository.findAll();


        competitionRepository.save(competition1);
        athleteRepository.save(athlete1);
        athleteRepository.save(athlete2);
        athleteCompetitionRepository.save(new AthleteCompetition(athlete1, competition1, 99, 1, 1));


    }*/

    @Test
    public void testQuery(){
        List<Competition> all1 = competitionRepository.findAll();
        Team asd = teamRepository.findTeamByName("asd");
        List<Team> all = teamRepository.findAll();
    }

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
        DBAthlete_competition byPrimKey = athlete_competitionDao.findByPrimKey(6, 8);
    }

    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public AthleteCompetitionRepository getAthleteCompetitionRepository() {
        return athleteCompetitionRepository;
    }

    public void setAthleteCompetitionRepository(AthleteCompetitionRepository athleteCompetitionRepository) {
        this.athleteCompetitionRepository = athleteCompetitionRepository;
    }
}
