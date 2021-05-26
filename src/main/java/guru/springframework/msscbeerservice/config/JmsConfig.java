package guru.springframework.msscbeerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Created by jt on 2019-07-20.
 */
// JmsConfig>see Section 13: JMS Messaging for an explanation, also spring.artemis.user + ...password added to application.properties
// Also done for the mssc-beer-order-service + mssc-beer-inventory-service>in project window copy this class (ctrl+c) + paste it into other projects 
@Configuration
public class JmsConfig { 

    // he's thinking he's defining all JMS things here, most people externalize this>he thinks queue names are pretty static + don't change much between environments
    public static final String BREWING_REQUEST_QUEUE = "brewing-request"; 
    public static final String NEW_INVENTORY_QUEUE = "new-inventory"; // defined centrally

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) { // inject Spring Boot managed ObjectMapper
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        // use Spring Boot managed ObjectMapper to avoid error Cannot construct instance of ‘java.time.OffsetDateTime‘
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
