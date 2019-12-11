/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 11, 2019
 */
package com.ishanitech.ipalika.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addStatusController("/home", HttpStatus.OK);
	}
	
}
