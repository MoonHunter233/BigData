package com.syhbb.bigdata.components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTasks {
    private final RestTemplate restTemplate;

    public ScheduledTasks(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 0 6 * * ?") // 每天早上 6 点执行
    public void popularTask() {
        String url = "http://localhost:8080/MongoDBAndSpark/API/popularInto";
        String response = restTemplate.getForObject(url, String.class);
    }

    @Scheduled(cron = "0 1 6 * * ?") // 每天早上 6 点执行
    public void commentTask() {
        String url = "http://localhost:8080/MongoDBAndSpark/API/updateComment";
        String response = restTemplate.getForObject(url, String.class);
    }
}
