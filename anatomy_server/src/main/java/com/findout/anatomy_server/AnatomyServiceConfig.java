package com.findout.anatomy_server;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.findout.anatomy_server.services.db.DBService;
import com.findout.anatomy_server.services.interfaces.AnatomyService;
import com.findout.anatomy_server.services.memory.InMemoryService;

@Configuration
public class AnatomyServiceConfig {

	@Bean
	@ConditionalOnProperty(name = "service.type", havingValue = "memory", matchIfMissing = true)
	public AnatomyService inMemoryService() {
		return InMemoryService.getInstance();
	}

	@Bean
	@ConditionalOnProperty(name = "service.type", havingValue = "db")
	public AnatomyService dbService() {
		return new DBService();
	}
}
