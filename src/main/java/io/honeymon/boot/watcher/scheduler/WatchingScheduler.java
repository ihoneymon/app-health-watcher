package io.honeymon.boot.watcher.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.honeymon.boot.watcher.service.ActuatorHealthChecker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WatchingScheduler {
    @Value("${target.url}")
    String targetUrl;
    
    @Autowired
    ActuatorHealthChecker actuatorHealthChecker;

    @Scheduled(fixedRate=1 * 60 * 1000)
    public void watch() {
        log.info("watch: {}", targetUrl);
        actuatorHealthChecker.check(targetUrl);
    }
}
