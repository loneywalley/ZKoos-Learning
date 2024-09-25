package com.fif.services.impl;

import com.fif.entity.Registration;
import com.fif.repository.RegisterRepo;
import java.util.List;

import com.fif.services.RegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("regisService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RegisServiceImpl implements RegisService {

    @Autowired
    RegisterRepo registerRepo;

    public Registration addRegis(Registration registration){
        return registerRepo.save(registration);
    }

    public List<Registration> getRegis(){
        return registerRepo.queryAll();
    }

    public void deleteRegis(Registration registration){
        registerRepo.delete(registration);
    }
}
