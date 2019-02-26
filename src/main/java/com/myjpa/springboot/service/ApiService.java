package com.myjpa.springboot.service;

import com.myjpa.springboot.repository.AthleteRepository;
import com.myjpa.springboot.repository.TeamRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    AthleteRepository athleteRepository;
}
