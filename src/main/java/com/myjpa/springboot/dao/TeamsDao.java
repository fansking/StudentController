package com.myjpa.springboot.dao;

import com.myjpa.springboot.dao.base.BaseDao;
import com.myjpa.springboot.entity.dbentity.DBTeams;

import java.util.List;

public class TeamsDao extends BaseDao<DBTeams> {
    public List<DBTeams> findByAccountAndPass_word(String account,String password){
        return findBy(account,password);
    }
}
