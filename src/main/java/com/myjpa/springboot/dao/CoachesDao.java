package com.myjpa.springboot.dao;

import com.myjpa.springboot.dao.base.BaseDao;
import com.myjpa.springboot.entity.dbentity.DBCoaches;

import java.util.List;

public class CoachesDao extends BaseDao<DBCoaches> {
    public List<DBCoaches> findByTeam_id(Integer teamId){
        return findBy(teamId);
    }
}
