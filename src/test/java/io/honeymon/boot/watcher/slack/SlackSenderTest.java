package io.honeymon.boot.watcher.slack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.honeymon.boot.watcher.slack.SlackSender.SlackTarget;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlackSenderTest {

    @Autowired
    SlackSender slackSender;
    
    @Test
    public void testSender() {
        SlackTarget target = SlackTarget.NOTIFICATION;
        SlackMessage msg = SlackMessage.builder().username("Test").text("Test").build();
        
        slackSender.sender(target, msg);
    }

}
