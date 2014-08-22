package de.ciber.comics;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories("de.ciber.comics.db.entity")
@EnableAutoConfiguration
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan("de.ciber.comics.db.entity");
		return lef;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication()
			.usersByUsernameQuery("select username,password,status='ACTIVE' from User where username=?")
			.authoritiesByUsernameQuery("select username,role from User where username=?");
	}
	
	@Bean()
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public SimpleMailMessage templateMessage() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("cibercomics@gmx.de");
		message.setSubject("Message from CiberComics");
		return message;
	}
		
	// TO USE THIS, DOWNLOAD Papercut FROM http://papercut.codeplex.com/
	// AND CONFIGURE IT TO USE PORT 465.
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailer = new JavaMailSenderImpl();
		mailer.setHost("localhost");
		mailer.setPort(465);
		
		return mailer;
	}

	// LAST PROBLEM WITH THIS ONE: takes ages, doesn't seem to complete
//	@Bean
//	public JavaMailSender mailSenderGmail() {
//		JavaMailSenderImpl mailer = new JavaMailSenderImpl();
//		mailer.setHost("smtp.gmail.com");
//		mailer.setPort(465); // SSL = 465
//		mailer.setProtocol("smtp");
//		mailer.setUsername("cibercomics2014@gmail.com");
//		mailer.setPassword();
//		
//		Properties mailProperties = new Properties();
//		mailProperties.put("mail.smtp.auth", true);
//		mailProperties.put("mail.smtp.starttls.enable", true);
//        mailProperties.put("mail.smtp.host", "smtp.gmail.com");
//		mailProperties.put("mail.transport.protocol", "smtp");
//		mailProperties.put("mail.debug", true);
//		mailer.setJavaMailProperties(mailProperties);
//    
//		return mailer;
//	}
	
	// LAST PROBLEM WITH THIS ONE: server answers 530 - Authentication  required
//	@Bean
//	public JavaMailSender mailSenderGmx() {
//		JavaMailSenderImpl mailer = new JavaMailSenderImpl();
//		mailer.setHost("mail.gmx.net");
//		mailer.setPort(465); // SSL = 465
//		mailer.setProtocol("smtps");
//		mailer.setUsername("cibercomics@gmx.de");
//		mailer.setPassword();
//		
//		Properties mailProperties = new Properties();
//		mailProperties.put("mail.smtp.auth", true);
//		mailProperties.put("mail.smtps.auth", true);
//		mailProperties.put("mail.smtp.ssl.enable", true);
//		mailProperties.put("mail.transport.protocol", "smtps");
//		mailProperties.put("mail.debug", true);
//		mailer.setJavaMailProperties(mailProperties);
//    
//		return mailer;
//	}

// FIXME find out how to use java configuration for multi-part resolver
//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setMaxUploadSize(5 * 1024 * 1024);
//
//		return resolver;
//	}
}
