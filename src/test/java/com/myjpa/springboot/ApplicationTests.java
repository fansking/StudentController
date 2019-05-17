package com.myjpa.springboot;

import com.myjpa.springboot.dao.AthleteDao;
import com.myjpa.springboot.dao.Athlete_competitionDao;
import com.myjpa.springboot.dao.GradesDao;
import com.myjpa.springboot.dao.TeamsDao;
import com.myjpa.springboot.entity.dbentity.DBAthlete;
import com.myjpa.springboot.entity.dbentity.DBAthlete_competition;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTests {
    TeamsDao teamsDao;
    AthleteDao athleteDao;
    Athlete_competitionDao athlete_competitionDao;
    @Before
    public void generateDao(){
        teamsDao = new TeamsDao();
        athleteDao = new AthleteDao();
        athlete_competitionDao = new Athlete_competitionDao();
    }

    @Test
    public void testDao1(){
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
        //DBAthlete_competition byPrimKey = athlete_competitionDao.findByPrimKey(6, 8);
        DBAthlete_competition athlete_competition = new DBAthlete_competition(16,8,100,22,20d);
//        athlete_competitionDao.saveInfo(athlete_competition);
  //      athlete_competitionDao.modifyInfo(athlete_competition);
        athlete_competitionDao.deleteInfo(6,8);
    }

    @Test
    public void testDao3(){
       // teamsDao.getDetails();
//        AthleteDao athleteDao = new AthleteDao();
//        athleteDao.getDetails();
//        Athlete_competitionDao athlete_competitionDao = new Athlete_competitionDao();
//        athlete_competitionDao.getDetails();
    }

    @Test
    public void testLeftJoin(){
        try {
            String sql = "select * from teams " +
                    "left join athlete on teams.id=athlete.team_id " +
                    "left join coaches on teams.id=coaches.team_id";
            ResultSet resultSet = teamsDao.getResultSet(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()){
                int count = metaData.getColumnCount();
                for(int i = 1 ; i <= count ; i++){
                    System.out.print(metaData.getColumnName(i)+"  ");
                    Object object = resultSet.getObject(i);
                    if(object!=null) {
                        System.out.print(object.toString()+"  ");
                    } else{
                        System.out.print("null  ");
                    }
                }
                System.out.println("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery(){
        GradesDao gradesDao = new GradesDao();
        String sql = "select (sum(n_grade)-max(n_grade)-min(n_grade))/(count(*)-3)*(count(*)-1)+sum(d_grade)-sum(p_grade) score" +
                ",athlete_id,competition_id from grades group by athlete_id,competition_id order by score desc";
        List<HashMap<String, Object>> resultMap = gradesDao.getResultMap(sql);
        String sql22 = "update athlete_competition set score = ? where athlete_id = ? and competition_id = ?";
        for(HashMap<String,Object> tmp : resultMap){
            gradesDao.update(sql22,tmp.get("score"),tmp.get("athlete_id"),tmp.get("competition_id"));
        }

        String sql1 = "select id from competition";
        List<HashMap<String, Object>> competitionIDMapList = gradesDao.getResultMap(sql1);
        for(HashMap<String,Object> competitionIDMap : competitionIDMapList){
            String sql2 = "select athlete_id from athlete_competition where competition_id = ? order by score desc";
            List<HashMap<String, Object>> athleteIDList = gradesDao.getResultMap(sql2, competitionIDMap.get("id"));
            int rank = 1;
            for(HashMap<String,Object> athleteID : athleteIDList){
                String sql3 = "update athlete_competition set athlete_rank = ? where athlete_id = ? and competition_id = ?";
                gradesDao.update(sql3,rank,athleteID.get("athlete_id"),competitionIDMap.get("id"));
                rank++;
            }
        }
    }
}
