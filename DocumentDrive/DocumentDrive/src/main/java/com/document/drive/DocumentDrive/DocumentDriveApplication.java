package com.document.drive.DocumentDrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
		//(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan({"com.document.drive.DocumentDrive.repository"})
public class DocumentDriveApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DocumentDriveApplication.class, args);
	}
}
