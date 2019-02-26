package com.myjpa.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.repository.AthleteRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.jfunc.json.impl.JSONArray;

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
    @ApiOperation(value="插入一个运动员")
    //插入一个学生
    @PostMapping("/insert")
    public Athlete insertAthlete(Athlete athlete){
        Athlete save=athleteRepository.save(athlete);
        return save;
    }
    @ApiOperation(value="寻查找所有的运动员信息")
    @GetMapping("/findAll")
    public List<Athlete> getAthleteList(){
               return athleteRepository.findAll();
    }

    @GetMapping("/findByAge/{age}")
    public List<Athlete> findByAge(@PathVariable("age") int age){
        return athleteRepository.findByAge(age);
    }
    @GetMapping( "/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        athleteRepository.deleteById(id);
    }


}
