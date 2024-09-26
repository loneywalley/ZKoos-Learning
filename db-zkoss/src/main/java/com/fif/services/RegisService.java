package com.fif.services;

import com.fif.entity.Registration;

import java.util.List;

public interface RegisService {
    Registration addRegis(Registration registration);

    List<Registration> getRegis();

    void deleteRegis(Registration registration);

    Registration updateRegis(Registration registration);
}