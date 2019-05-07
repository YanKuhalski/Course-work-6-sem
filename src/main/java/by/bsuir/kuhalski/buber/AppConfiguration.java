package by.bsuir.kuhalski.buber;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource("classpath:application.properties")
@Import(SecurityConfig.class)
@ComponentScans(value = {
        @ComponentScan("by.bsuir.kuhalski.buber.service.impl"),
        @ComponentScan("by.bsuir.kuhalski.buber.repository.impl"),
        @ComponentScan("by.bsuir.kuhalski.buber.model")
})
public class AppConfiguration {
    private static final String URL = "db.url";
    private static final String USER = "db.username";
    private static final String PASSWORD = "db.username";
    private static final String CACHE_PREPARED_STATEMENT = "cachePrepStmts";
    private static final String PREPARED_STATEMENT_CACHE_LIMIT = "prepStmtCacheSqlLimit";
    private static final String PREPARED_STATEMENT_SIZE = "prepStmtCacheSize";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW = "hibernate.show_sql";

    @Autowired
    private Environment environment;

    @Bean
    @Scope("prototype")
    public DataSource dataSource() {
        HikariConfig CONFIG = new HikariConfig();
        String url = environment.getProperty(URL);
        String user = environment.getProperty(USER);
        String password = environment.getProperty(PASSWORD);

        CONFIG.setJdbcUrl(url);
        CONFIG.setUsername(user);
        CONFIG.setPassword(password);

        CONFIG.addDataSourceProperty(CACHE_PREPARED_STATEMENT, environment.getProperty(CACHE_PREPARED_STATEMENT));
        CONFIG.addDataSourceProperty(PREPARED_STATEMENT_SIZE, environment.getProperty(PREPARED_STATEMENT_SIZE));
        CONFIG.addDataSourceProperty(PREPARED_STATEMENT_CACHE_LIMIT, environment.getProperty(PREPARED_STATEMENT_CACHE_LIMIT));

        HikariDataSource dataSource = new HikariDataSource(CONFIG);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"by.bsuir.kuhalski.buber"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getAdditionalProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties getAdditionalProperties() {
        Properties props = new Properties();
        props.setProperty(HIBERNATE_DIALECT, environment.getProperty(HIBERNATE_DIALECT));
        props.setProperty(HIBERNATE_SHOW, environment.getProperty(HIBERNATE_SHOW));
        return props;
    }
}
