package com.example.java25springengine;

import java.util.Arrays;

public enum SendServiceType {

    EMAIL("emailSendServiceImpl"),
    TELEGRAM("telegramSendServiceImpl"),

    SMS("smsSendServiceImpl")
    ;

    private final String beanName;

    SendServiceType(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public static SendServiceType findByBeanName(String name) {
        return Arrays.stream(values())
                .filter(x -> x.getBeanName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No bean with such name"));
    }
}
