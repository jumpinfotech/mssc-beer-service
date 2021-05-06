package guru.springframework.msscbeerservice.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

// we need to add the spring configuration @EnableCaching, 
// technically, I could have put this annotation on the BeerServiceApplication > placed here as it's more intuitive
    
//    If we have other configuration objects that need adding, I'll put them in the config package> 
//    configuration is in one logical spot 
@EnableCaching
@Configuration
public class CacheConfig {
}
