package io.honeymon.boot.watcher.slack;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlackMessage {
    private String username;
    private String text;
    private String channel;
    private String icon_emoji;
    private List<SlackAttachment> attachments;

    void addAttachment(SlackAttachment attachement) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<>();
        }
        this.attachments.add(attachement);
    }

    @Data
    @Builder
    public static class SlackAttachment {
        public static String LIGHT_BLUE = "#03A9F4";
        public static String BLUE = "#2196F3";
        public static String INDIGO = "#3F51B5";
        public static String PURPLE = "#673AB7";
        public static String TEAL = "#009688";
        public static String GREEN = "#4CAF50";
        public static String LIME = "#CDDC39";
        public static String ORANGE = "#FF9800";
        public static String DEEP_ORANGE = "#FF5722";
        public static String BROWN = "#795548";
        public static String BLUE_GREY = "#607D8B";

        private String fallback;
        private String text;
        private String pretext;
        private String color;
        private List<SlackField> fields;
        
        private String author_name;
        @JsonProperty("author_link")
        String author_link;
        @JsonProperty("author_icon")
        String author_icon;
        
        private String title;
        @JsonProperty("title_link")
        private String title_link;
        
        @JsonProperty("image_url")
        private String image_url;
        private String thumb_url;
        private String kkrdwn_in;
    }

    @Data
    @Builder
    public static class SlackField {
        String title;
        String value;
        @JsonProperty("short")
        boolean shortable;
    }
}
