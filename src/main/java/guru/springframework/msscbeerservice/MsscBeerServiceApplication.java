package guru.springframework.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;

//  We're bringing in the JMS>we get an exception from Apache ActiveMQ Artemis, to prevent error on startup:- 
//  exclude = ArtemisAutoConfiguration.class, 
// exclude tells Spring not to do that auto configuration, Spring is bringing it in from the Parent POM + that comes in on the classpath > 
// Spring is trying to auto configure that. 
// JMS will be implemented later. 
// IntelliJ>Run>Edit configurations>Active profiles=application-localmysql>apply>OK, we will connect to MySQL + not H2>get an error h2 was OK, MySQL isn't
@SpringBootApplication(exclude = ArtemisAutoConfiguration.class)
public class MsscBeerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscBeerServiceApplication.class, args);
    }

}
