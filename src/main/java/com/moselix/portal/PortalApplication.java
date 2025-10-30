package com.moselix.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
//		SpringApplication app = new SpringApplication(PortalApplication.class);
//		app.addInitializers(new com.moselix.portal.config.DotenvInitializer());
//        app.run(args);
	}

}
