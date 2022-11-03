package com.example.java25springengine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectApplicationConfiguration {

    @Bean
    public Map<SendServiceType, SendService> sendServiceMap(@Autowired Map<String, SendService> sendServiceMap) {
        Map<SendServiceType, SendService> map = new HashMap<>();

        sendServiceMap.forEach((bean, sendServiceInstance) -> {
            map.put(SendServiceType.findByBeanName(bean), sendServiceInstance);
        });

        return map;
    }
}
