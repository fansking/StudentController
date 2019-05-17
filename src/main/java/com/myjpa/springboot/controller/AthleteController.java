package com.myjpa.springboot.controller;


import com.myjpa.springboot.config.Setting;
import com.myjpa.springboot.dao.AthleteDao;
import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.repository.AthleteRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "athlete")
public class AthleteController {
    @Autowired
    AthleteRepository athleteRepository;
    @ApiOperation(value ="通过id获取运动员的信息")
    @GetMapping("/findById/{id}")
    public Athlete getAthleteById(@PathVariable("id") Integer id){
        Athlete athlete=athleteRepository.findById(id).orElse(null);
        return athlete;
    }
    @ApiOperation(value = "通过ID更新学生信息",notes="更改学生年龄和姓名")
    @RequestMapping(value="/updateById",method= RequestMethod.GET)
    public Athlete updateAthlete(@RequestParam("id") Integer id,@RequestParam("name") String name,@RequestParam("age") Integer age){
        Athlete athlete=athleteRepository.findById(id).orElse(null);
        athlete.setAge(age);
        athlete.setName(name);
        athleteRepository.save(athlete);
        return athlete;
}
    @ApiOperation(value="插入多个运动员")
    //插入一个学生
    @PostMapping("/insertMany")
    public void insertAthletes(@RequestBody List<Athlete> athletes){
        for (Athlete athlete:athletes) {
            if(Setting.runModel == 1) {
                athleteRepository.save(athlete);
            }else {

            }
        }
    }
    @ApiOperation(value="插入一个运动员")
    //插入一个学生
    @PostMapping("/insert")
    public Athlete insertAthlete(@RequestBody Athlete athlete){
        return athleteRepository.save(athlete);
    }
    @ApiOperation(value="寻查找所有的运动员信息")
    @GetMapping("/findAll")
    public List<Athlete> getAthleteList(){
               return athleteRepository.findAll();
    }
    @ApiOperation(value="查找某队的运动员信息")
    @GetMapping("/findByTeam/{id}")
    public List<Athlete> getAthleteListByTeam(@PathVariable Integer id){
        return athleteRepository.findByTeam_Id(id);
    }

    @GetMapping("/findByAge/{age}")
    public List<Athlete> findByAge(@PathVariable("age") int age){
        return athleteRepository.findByAge(age);
    }
    @GetMapping( "/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        athleteRepository.deleteById(id);
    }

    @GetMapping("/autoCalId")
    public List<Athlete> autoCalId(){
        List<Athlete> athleteList;
        if(Setting.runModel == 1){
            athleteList = athleteRepository.findAll();
        } else {
            athleteList = Athlete.getListAthlete(new AthleteDao().findAll());
        }

        int maleId = 0,famaleId = 1;
        for(int i = 0 ; i < athleteList.size() ; i++){
            if(athleteList.get(i).getMale()){
                athleteList.get(i).setAthleteId(String.format("%03d",maleId));
                maleId+=2;
            }else{
                athleteList.get(i).setAthleteId(String.format("%03d",famaleId));
                famaleId+=2;
            }
        }
        athleteRepository.saveAll(athleteList);
        return athleteList;
    }

}
