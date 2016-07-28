package org.egdeveloper.service;

import org.egdeveloper.data.model.HealthStat;

import java.util.Collection;

public interface IHealthService {
    Collection<HealthStat> findHealthStatistics(Long userId);
    void uploadHealthStatistics(Long userId, HealthStat healthStat);
    boolean sendHealthRiskAlarm(Long userId, HealthStat sos);
}
