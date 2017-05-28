package io.honeymon.boot.watcher.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.honeymon.boot.watcher.slack.SlackMessage;
import io.honeymon.boot.watcher.slack.SlackSender;
import io.honeymon.boot.watcher.slack.SlackMessage.SlackAttachment;
import io.honeymon.boot.watcher.slack.SlackSender.SlackTarget;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActuatorHealthCheckerImpl implements ActuatorHealthChecker {

    @Setter
    @Autowired
    RestTemplateBuilder restTemplaeBuilder;
    @Setter
    @Autowired
    SlackSender slackSender;

    @Override
    public void check(String targetUrl) {
        Assert.hasText(targetUrl, "TargetUrl required.");
        log.info("Health check at {}", targetUrl);
        RestTemplate restTemplate = restTemplaeBuilder.build();
        
        try {
            HealthStatus status = restTemplate.getForObject(targetUrl, HealthStatus.class);
            log.debug("status: {}", status);

            if (status.status != Status.UP) {
                log.error("App is die!!");
                sendSlackMessage(targetUrl, "TargetUrl is not response.");
            }            
        } catch (RestClientException e) {
            log.error("Occur RestClientException: {}", e.getMessage());
            sendSlackMessage(targetUrl, e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private void sendSlackMessage(String targetUrl, String text) {
        List<SlackAttachment> attachments = new ArrayList<>();

        attachments.add(SlackAttachment.builder().color(SlackAttachment.ORANGE).title("Health Check at " + targetUrl)
                .title_link(targetUrl).build());

        SlackMessage msg = SlackMessage.builder().username("ActuatorHealthChecker").text(text).attachments(attachments)
                .build();
        slackSender.sender(SlackTarget.NOTIFICATION, msg);
    }

    @Data
    public static class HealthStatus {
        private Status status;
    }

    public static enum Status {
        UP;
    }
}
