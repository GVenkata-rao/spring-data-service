package com.springdata.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.springdata")
@EnableJpaRepositories(basePackages = "com.springdata.repository")
public class WebConfig {
	private static final String DATA_SRC_JNDI = "java:comp/env/jdbc/MyDB";

	@Bean(name = "dataSource")
	@Primary
	public JndiObjectFactoryBean dataSource() {
		JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
		dataSource.setJndiName(DATA_SRC_JNDI);
		return dataSource;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);

		AbstractJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);

		bean.setJpaVendorAdapter(jpaVendorAdapter);

		bean.setPackagesToScan("com.springdata.domain");
		bean.afterPropertiesSet();

		return bean.getObject();
	}

	@Bean
	public JpaTransactionManager jpaTransactionManager(EntityManagerFactory factory) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(factory);
		return manager;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}