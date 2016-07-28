package org.egdeveloper.web.controller.rest;

import org.egdeveloper.data.model.HealthStat;
import org.egdeveloper.data.model.User;
import org.egdeveloper.service.IHealthService;
import org.egdeveloper.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthServiceController {

    @Autowired
    private IHealthService healthService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/user/{userId}/health/stat")
    Collection<HealthStat> findHealthStatistics(@PathVariable Long userId){
        return healthService.findHealthStatistics(userId);
    }

    @RequestMapping(value = "/user/{userId}/health/stat", method = RequestMethod.POST)
    void uploadHealthStatistics(@PathVariable Long userId, @RequestBody HealthStat healthStat){
        healthService.uploadHealthStatistics(userId, healthStat);
    }

    @RequestMapping(value = "/user/{userId}/health/alarm", method = RequestMethod.POST)
    void sendHealthRiskAlarm(@PathVariable Long userId, @RequestBody HealthStat sos){
        healthService.sendHealthRiskAlarm(userId, sos);
    }

    @RequestMapping(value = "/user/health/state")
    Map sendHealthRiskAlarm(@RequestParam String token, @RequestBody HealthStat state){
        User user = userService.findUserByToken(token.trim());
        Map res = new HashMap<>();
        if(state.getHeartRate() < user.getNormalHeartRateMin() || state.getHeartRate() > user.getNormalHeartRateMax()){
            healthService.sendHealthRiskAlarm(user.getId(), state);
            res.put("state", state);
            res.put("status", "health-risk");
            res.put("user", user);
        }
        else{
            res.put("state", state);
            res.put("status", "normal");
            res.put("user", user);
        }
        healthService.uploadHealthStatistics(user.getId(), state);
        return res;
    }
}
