package guru.springframework.msscbeerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by jt on 2019-07-21.
 */
@EnableAsync
@EnableScheduling
@Configuration
// sets up the Spring Scheduler
public class TaskConfig {

    // provide a task executor
    @Bean // @Bean injects that into the Spring Context
    TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor();
    }
}
