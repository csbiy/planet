package com.planet.dashboard;

import com.planet.dashboard.service.crawling.CrawlingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ScheduleConfig {
    @Bean
    public TaskScheduler taskScheduler(Schedule schedule , CrawlingService crawlingService){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("crawling");
        scheduler.initialize();
        scheduler.schedule(crawlingService,schedule.getCronTrigger());
        return scheduler;
    }
}
