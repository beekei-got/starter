package com.starter.app.config.library;

import com.beekei.library.querydslBuilder.repostiory.QuerydslBuilder;
import com.querydsl.jpa.JPQLTemplates;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryConfig {

	@PersistenceContext
	private EntityManager entityManager;

	@Bean
	public QuerydslBuilder querydslBuilder() {
		return new QuerydslBuilder(JPQLTemplates.DEFAULT, entityManager);
	}

}
