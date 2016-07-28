package org.egdeveloper.web.email;

import org.egdeveloper.data.model.HealthStat;
import org.egdeveloper.data.model.User;

public interface IHealthAlarmSender {
    void sendAlarm(User user, HealthStat healthStat);
}
