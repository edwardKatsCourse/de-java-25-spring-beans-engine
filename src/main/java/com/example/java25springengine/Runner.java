package com.example.java25springengine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component("runner")
public class Runner implements CommandLineRunner {

    // Map<String, Object> applicationContext
    // Map<Class<?>, List<Object>> applicationContext

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private VictimBean victimBean;     // applicationContext.getBean()

    @Autowired
    private List<SendService> sendServices;

    @Autowired
//    private Map<String, SendService> sendServiceMap;
    private Map<SendServiceType, SendService> sendServiceMap;

    @Override
    public void run(String... args) throws Exception {
        sendServiceMap.get(SendServiceType.SMS).send("message");
        sendServiceMap.get(SendServiceType.EMAIL).send("message");
        sendServiceMap.get(SendServiceType.TELEGRAM).send("message");
    }
}

//class A implements FactoryBean<A> {
//    @Override
//    public A getObject() throws Exception {
//        return null;
//    }
//
//    @Override
//    public Class<?> getObjectType()
//
//    @Override
//    public boolean isSingleton() {
//        return FactoryBean.super.isSingleton();
//    }
//}


//   TransactionalChildProxy        VictimBean
//                                @Transactional
//      save(open tx)        ->        save
//      save(close tx)       <-        save


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Service
@interface TelRanServiceSpringComponent {

    String value();
}

@TelRanServiceSpringComponent("victimBean")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Transactional
class VictimBean {

//    private String adminUsername;
    // private AccountRepository accountRepository;

    // 11:24:44
    // thread.sleep(10_000)
    // 11:24:44
    private LocalDateTime currentTime;

    public VictimBean() {
        System.out.println("Victim ctor");
        currentTime = LocalDateTime.now();
    }

    @PostConstruct
    public void init() {
        System.out.println("Victim Bean initialization");
        // adminUsername = accountRepository.findFirstAdmin();
    }

    public void printTime() {
        System.out.println(currentTime);
    }

    // Bean Post Processor

    // Context Refresh
    // Ready to work


    @PreDestroy
    public void destroy() {
        System.out.println("Victim bean destroy");
    }
}

interface SendService {
    void send(String message);
}


@Service("emailSendServiceImpl")
class EmailSendServiceImpl implements SendService {
    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

@Service("telegramSendServiceImpl")
class TelegramSendServiceImpl implements SendService {
    @Override
    public void send(String message) {
        System.out.println("Telegram: " + message);

    }
}

@Service("smsSendServiceImpl")
class SmsSendServiceImpl implements SendService {
    @Override
    public void send(String message) {
        System.out.println("Sms: " + message);
    }
}

@Service("whatsAppSendServiceImpl")
class WhatsAppSendServiceImpl implements SendService {
    @Override
    public void send(String message) {
        System.out.println("WhatsApp: " + message);
    }
}