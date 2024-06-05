package com.example.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5000); // Số luồng lõi
        executor.setMaxPoolSize(10000);  // Số luồng tối đa
        executor.setQueueCapacity(500); // Kích thước hàng đợi
        executor.setThreadNamePrefix("MyThread-");
        executor.initialize();
        return executor; 
    }
}
