package config;

import dao.OrderDao;
import dao.UserDao;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class ControllerConfig {
    @Autowired
    private Environment env;

    @Bean(name = "postgreDs")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername(env.getProperty("dbUser"));
        ds.setPassword(env.getProperty("dbPassword"));
        ds.setUrl(env.getProperty("dbUrl"));

        return ds;
    }

    @Bean
    public OrderDao getOrderDao() {
        return new OrderDao();
    }

    @Bean
    public UserDao getUserDao() { return new UserDao(); }

    @Bean
    public EntityManagerFactory
    entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceProviderClass(
                HibernatePersistenceProvider.class);
        factory.setPackagesToScan("mapper");
        factory.setDataSource(dataSource);
        factory.setJpaProperties(additionalProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }


    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect",
                "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.setProperty(
                "hibernate.show_sql", "true");
        properties.setProperty(
                "hibernate.hbm2ddl.auto", "validate");
        return properties;
    }
}
