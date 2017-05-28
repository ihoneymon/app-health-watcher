package io.honeymon.boot.watcher.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActuatorHealthCheckerTest {

    @Autowired
    ActuatorHealthChecker healthChecker;

    @Test(expected = RuntimeException.class)
    public void test404NotFound() throws Exception {
        String targetUrl = "http://honeymon.io/health";
        healthChecker.check(targetUrl);
    }

    @Test
    public void testCheck() throws Exception {
        String targetUrl = "http://localhost:8080/health";
        healthChecker.check(targetUrl);
    }
}
