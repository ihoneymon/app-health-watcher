package io.honeymon.boot.watcher.service;

public interface ActuatorHealthChecker {

    /**
     * Health check of target
     * @param targetUrl
     */
    void check(String targetUrl);
}
