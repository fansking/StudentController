package com.myjpa.springboot.controller;

import com.myjpa.springboot.config.Setting;
import com.myjpa.springboot.dao.RefereesDao;
import com.myjpa.springboot.dao.TeamsDao;
import com.myjpa.springboot.entity.Referee;
import com.myjpa.springboot.entity.Team;
import com.myjpa.springboot.repository.RefereeRepository;
import com.myjpa.springboot.repository.TeamRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    RefereeRepository refereeRepository;

    TeamsDao teamsDao = new TeamsDao();
    RefereesDao refereesDao = new RefereesDao();

    @ApiOperation(value="领队登陆")
    @GetMapping("/leader/{account}/{passWord}")
    public Team leaderLogin(@PathVariable String account, @PathVariable String passWord){
        List<Team> teams;
        if(Setting.runModel == 1){
            teams = teamRepository.findByAccountAndPassWord(account,passWord);
        } else {
//            String sql = "select * from teams where account like ? and pass_word like ?";
            teams = Team.getListTeam(teamsDao.findByAccountAndPass_word(account,passWord));
        }
        if (teams.size()>0){
            return teams.get(0);
        } else {
            return null;
        }
    }

    @ApiOperation(value="教练登陆")
    @GetMapping("/referee/{account}/{passWord}")
    public Referee RefereeLogin(@PathVariable String account, @PathVariable String passWord){
        List<Referee> referees ;
        if(Setting.runModel == 1){
            referees = refereeRepository.findByAccount(account,passWord);
        } else {
            String sql = "select * from referees,teams where referees.team_id=teams.id and teams.account like ? and referees.name like ?";
            referees = Referee.getListReferee(refereesDao.findBySql(sql,account,passWord));
        }
        if (referees.size()>0){
            return referees.get(0);
        } else {
            return null;
        }
    }
}
