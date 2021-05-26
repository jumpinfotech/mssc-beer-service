package guru.springframework.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Error in console>No qualifying bean of type 'org.springframework.jms.core.JmsTemplate'
// JMS is on the classpath, verified>Maven window>mssc-beer-service>Dependencies>org.springframework.boot:spring-boot-starter-artemis:2.1.5.RELEASE>open + we see JMS.
// JMS configuration is setup>JmsConfig.java
// Solution, this exclude caused the problem, it disabled the Spring Boot autoconfiguration:-
// @SpringBootApplication(exclude = ArtemisAutoConfiguration.class)
@SpringBootApplication
public class MsscBeerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscBeerServiceApplication.class, args);
    }

}
