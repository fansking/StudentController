package com.myjpa.springboot.dao;

import com.myjpa.springboot.dao.base.BaseDao;
import com.myjpa.springboot.entity.dbentity.DBAthlete;

import java.util.List;

public class AthleteDao extends BaseDao<DBAthlete> {
    public List<DBAthlete> findByName(String name){
        return findBy(name);
    }

    public List<DBAthlete> findByNameAndScores(String name,Integer scores){
        return findBy(name,scores);
    }
}
