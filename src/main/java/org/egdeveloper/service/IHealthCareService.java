package org.egdeveloper.service;

import org.egdeveloper.data.model.HealthSOS;
import org.egdeveloper.data.model.HealthStat;
import org.egdeveloper.data.model.User;

import java.util.Collection;

public interface IHealthCareService {
    Collection<HealthStat> findHealthStatistics(Long userId);
    void uploadHealthStatistics(Long userId, HealthStat healthStat);
    boolean sendHealthRiskAlarm(Long userId, HealthSOS sos);
}
