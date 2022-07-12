package com.faltdor.card.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;


@Configuration
@ConfigurationProperties( prefix = "card.service" )
@Data
public class CardServiceConfig {

    private String msg;

    private String buildVersion;

    private Map<String, String> mailDetails;

    private List<String> activeBranches;
}