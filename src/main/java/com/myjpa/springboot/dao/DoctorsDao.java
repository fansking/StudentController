package com.myjpa.springboot.dao;

import com.myjpa.springboot.dao.base.BaseDao;
import com.myjpa.springboot.entity.dbentity.DBDoctors;

import java.util.List;

public class DoctorsDao extends BaseDao<DBDoctors> {
    public List<DBDoctors> findByTeam_id(Integer teamId){return findBy(teamId);}
}
