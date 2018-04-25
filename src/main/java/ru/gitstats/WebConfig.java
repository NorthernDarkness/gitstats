//package ru.gitstats;
//
//import javax.naming.NamingException;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.http.MediaType;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//import org.thymeleaf.spring4.SpringTemplateEngine;
//import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring4.view.ThymeleafViewResolver;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//@Configuration
////@EnableTransactionManagement
////@EnableCaching
////@EnableAsync
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"ru.gitstats.model"})
//public class WebConfig {
//
//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//        templateResolver.setCacheable(false);
//        templateResolver.setPrefix("classpath:/templates/");
//        templateResolver.setSuffix(".html");
//        return templateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
//        springTemplateEngine.addTemplateResolver(templateResolver());
//        return springTemplateEngine;
//    }
//
//    @Bean
//    public ThymeleafViewResolver viewResolver() {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setOrder(1);
//        return viewResolver;
//    }
//
////
////    @Bean
////    public FreeMarkerViewResolver freemarkerViewResolver() {
////        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
////        resolver.setCache(true);
////        resolver.setPrefix("");
////        resolver.setSuffix(".ftl");
////        return resolver;
////    }
////
////    @Bean
////    public FreeMarkerConfigurer freemarkerConfig() {
////        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
////        freeMarkerConfigurer.setTemplateLoaderPath("classpath:resources\\templates");
////        return freeMarkerConfigurer;
////    }
////
////    @Bean
////    public EmbeddedDatabase dataSource() {
////        // org.apache.commons.dbcp.BasicDataSource
//////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//////        dataSource.setDriverClassName("org.h2.Driver");
//////        dataSource.setUrl("jdbc:h2:mem:testdb");
//////        dataSource.setUsername( "user" );
//////        dataSource.setPassword( "" );
////        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
////        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("classpath:apply_schema.sql")
////                .addScript("classpath:add_data.sql").build();
////        return db;
////    }
////
////    @Bean
////    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EmbeddedDatabase dataSource) {
////        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//////    entityManagerFactory.setPersistenceUnitName("hibernate-persistence");
////        entityManagerFactory.setDataSource(dataSource);
////        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
////        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
////        entityManagerFactory.setPackagesToScan("ru.gitstats.model");
////        return entityManagerFactory;
////    }
////
////    private Map<String, ?> hibernateJpaProperties() {
////        Map<String, String> properties = new HashMap<>();
////
////        return properties;
////    }
////
////    @Bean
////    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
////        //org.springframework.orm.jpa.JpaTransactionManager
////        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
////        jpaTransactionManager.setEntityManagerFactory(emf);
////        return jpaTransactionManager;
////    }
//
//}
