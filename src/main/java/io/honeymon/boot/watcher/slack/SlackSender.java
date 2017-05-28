package io.honeymon.boot.watcher.slack;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Slack incoming Webhook 을 {@link RestTemplate}로 호출하여 메시지 전송 
 * @author honeymon
 * @see <a href="https://api.slack.com/incoming-webhooks">Incoming Webhooks</a>
 */
@Slf4j
@Component
public class SlackSender {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;
    
    @PostConstruct
    public void init() {
        restTemplate = restTemplateBuilder.build();
    }
    
    /**
     * Send {@link SlackMessage} to {@link SlackTarget}
     * @param msg
     */
    public void sender(SlackTarget target, SlackMessage msg) {
        if(!StringUtils.hasText(msg.getChannel())) {
            msg.setChannel(target.getChannel());
        }
        
        log.debug("Send {} to {}", msg, target);
        ResponseEntity<String> response = restTemplate.postForEntity(target.getWebhookUrl(), msg, String.class);
        log.debug("Header: {}", response.getHeaders());
        log.debug("Response: {}", response.getBody());
    }
    
    
    @Getter
    public static enum SlackTarget {
        NOTIFICATION("slack api url", "channel", "Generate ");
        
        private String webhookUrl;
        private String channel;
        private String desc;
        
        SlackTarget(String webhookUrl, String channel, String desc) {
            this.webhookUrl = webhookUrl;
            this.channel = channel;
            this.desc = desc;
        }        
    }
}
