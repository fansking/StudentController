package com.myjpa.springboot.dao;

import com.myjpa.springboot.dao.base.BaseDao;
import com.myjpa.springboot.entity.dbentity.DBReferees;

import java.util.List;

public class RefereesDao extends BaseDao<DBReferees> {
    public List<DBReferees> findByTeam_id(Integer teamId){return findBy(teamId);}
}
