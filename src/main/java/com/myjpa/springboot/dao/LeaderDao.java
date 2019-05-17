package com.myjpa.springboot.dao;

import com.myjpa.springboot.dao.base.BaseDao;
import com.myjpa.springboot.entity.dbentity.DBLeaders;

import java.util.List;

public class LeaderDao extends BaseDao<DBLeaders> {
    public List<DBLeaders> findByTeam_id(Integer teamId){return findBy(teamId);}
}
