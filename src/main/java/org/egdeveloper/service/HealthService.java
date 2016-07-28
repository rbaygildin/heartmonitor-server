package org.egdeveloper.service;

import org.egdeveloper.data.dao.IUserDAO;
import org.egdeveloper.data.model.HealthStat;
import org.egdeveloper.data.model.User;
import org.egdeveloper.web.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HealthService implements IHealthService {

    @Autowired
    private IUserService userService;

    @Autowired
    private EmailSender sender;

    @Autowired
    private IUserDAO userDAO;

    @Override
    public Collection<HealthStat> findHealthStatistics(Long userId) {
        return userService.findUserById(userId).getHealthStat();
    }

    @Override
    public void uploadHealthStatistics(Long userId, HealthStat healthStat) {
        userDAO.addHealthStat(userId, healthStat);
    }

    @Override
    public boolean sendHealthRiskAlarm(Long userId, HealthStat sos) {
        User user = userService.findUserById(userId);
        sender.sendAlarm(user, sos);
        return true;
    }
}
