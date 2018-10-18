package com.myjpa.springboot.controller;


import com.myjpa.springboot.entity.Student;
import com.myjpa.springboot.repository.StudentRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @ApiOperation(value ="通过id获取学生的信息" , notes="年龄和姓名")
    @GetMapping("/student/findById/{id}")
    public Student getStudentById(@PathVariable("id") Integer id){
        Student student=studentRepository.findById(id).orElse(null);
        return student;
    }
    @ApiOperation(value = "通过ID更新学生信息",notes="更改学生年龄和姓名")
    //@GetMapping("/student/updateById/{id}/{name}/{age}")
    @RequestMapping(value="/student/updateById",method= RequestMethod.GET)
    public Student updateStudent(@RequestParam("id") Integer id,@RequestParam("name") String name,@RequestParam("age") Integer age){
        Student student=studentRepository.findById(id).orElse(null);
        student.setAge(age);
        student.setName(name);
        studentRepository.save(student);
        return student;
    }
    @ApiOperation(value="插入一个学生对象",notes="输入学生姓名和年龄")
    //插入一个学生
    @PostMapping("/student/insert")
    public Student insertStudent(Student student){
        Student save=studentRepository.save(student);
        return save;
    }
    @ApiOperation(value="寻查找所有的学生信息",notes = "获取所有学生的ID，年龄和姓名")
    @GetMapping("/student/findAll")
    public List<Student> getStuList(){
               return studentRepository.findAll();
    }
    @ApiOperation(value="通过姓名查找学生信息",notes="获取指定学生的ID和年龄")
    @PostMapping("/student/findByName/{name}")
    public List<Student> findByName(@PathVariable("name") String name){
        return studentRepository.findByName(name);
    }
    @GetMapping("/student/findByAge/{age}")
    public List<Student> findByAge(@PathVariable("age") int age){
        return studentRepository.findByAge(age);
    }
    @GetMapping( "student/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        studentRepository.deleteById(id);
    }
    @GetMapping("student/deleteAll")
    public void deleteAllStudent(){
        studentRepository.deleteAll();
    }
    @PostMapping("student/GetJson")
    public Student getUserJson(@RequestParam(value = "id") int id, @RequestParam(value = "name2", required = false) String name2
            , @RequestBody Student pramInfo) {
        return pramInfo;
    }
}
