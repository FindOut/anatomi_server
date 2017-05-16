package com.findout.anatomy_server;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.findout.anatomy_server.services.db.DBService;
import com.findout.anatomy_server.services.interfaces.AnatomyService;
import com.findout.anatomy_server.services.memory.InMemoryService;

/**
 * Configures which implementation of AnatomyService is to be used by the REST-controller.
 * Retrieves the information from the application.properties file.
 * @author Maria Färdig
 *
 */
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
