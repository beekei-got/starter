package com.starter.app.config.library;

import com.beekei.library.apiVersion.ApiVersionRequestMappingHandlerMapping;
import com.beekei.library.querydslBuilder.QuerydslBuilder;
import com.querydsl.jpa.JPQLTemplates;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class LibraryConfig {

	@PersistenceContext
	private EntityManager entityManager;

	@Bean
	public QuerydslBuilder querydslBuilder() {
		return new QuerydslBuilder(JPQLTemplates.DEFAULT, entityManager);
	}

	@Bean
	public WebMvcRegistrations webMvcRegistrations() {
		return new WebMvcRegistrations() {
			@Override
			public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
				return new ApiVersionRequestMappingHandlerMapping("api", "v");
			}
		};
	}

}
