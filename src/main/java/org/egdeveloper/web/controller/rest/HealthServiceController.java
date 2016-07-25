package org.egdeveloper.web.controller.rest;

import org.egdeveloper.data.model.HealthSOS;
import org.egdeveloper.data.model.HealthStat;
import org.egdeveloper.service.IHealthCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/health-service")
public class HealthServiceController {

    @Autowired
    private IHealthCareService healthService;

    @RequestMapping(value = "/health-stat/{userId}")
    Collection<HealthStat> findHealthStatistics(@PathVariable Long userId){
        return healthService.findHealthStatistics(userId);
    }

    @RequestMapping(value = "/user/{userId}/health-stat", method = RequestMethod.POST)
    void uploadHealthStatistics(@PathVariable Long userId, @RequestBody HealthStat healthStat){
        healthService.uploadHealthStatistics(userId, healthStat);
    }

    @RequestMapping(value = "/user/{userId}/sendAlarm", method = RequestMethod.POST)
    void sendHealthRiskAlarm(@PathVariable Long userId, @RequestBody HealthSOS sos){
        healthService.sendHealthRiskAlarm(userId, sos);
    }
}
