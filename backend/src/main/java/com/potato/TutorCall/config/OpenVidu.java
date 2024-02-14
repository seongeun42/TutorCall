package com.potato.TutorCall.config;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class OpenVidu {

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    @Bean
    public io.openvidu.java.client.OpenVidu OpenVidu() {
        return new io.openvidu.java.client.OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

}
