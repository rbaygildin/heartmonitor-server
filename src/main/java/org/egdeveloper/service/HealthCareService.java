package org.egdeveloper.service;

import org.egdeveloper.data.model.HealthSOS;
import org.egdeveloper.data.model.HealthStat;
import org.egdeveloper.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HealthCareService implements IHealthCareService{

    @Autowired
    private IUserService userService;

    @Override
    public Collection<HealthStat> findHealthStatistics(Long userId) {
        return userService.findUserById(userId).getHealthStat();
    }

    @Override
    public void uploadHealthStatistics(Long userId, HealthStat healthStat) {
        User user = userService.findUserById(userId);
        user.getHealthStat().add(healthStat);
        userService.updateUser(user);
    }

    @Override
    public boolean sendHealthRiskAlarm(Long userId, HealthSOS sos) {
        return true;
    }
}
