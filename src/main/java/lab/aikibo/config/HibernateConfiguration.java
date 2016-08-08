package lab.aikibo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * seharusnya ditambahkan bonecp
 *
 * @author Tamami
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"lab.aikibo.config"})
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfiguration {
  @Autowired
  private Environment environment;

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    //sessionFactory.setDataSource(dataSource());
    sessionFactory.setDataSource(boneCPDataSource());
    sessionFactory.setPackagesToScan(new String[] {"lab.aikibo.model"});
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  @Bean
  public BoneCPDataSource boneCPDataSource() {
    BoneCPDataSource ds = new BoneCPDataSource();
    ds.setDriverClass(environment.getRequiredProperty("jdbc.driverClassName"));
    ds.setJdbcUrl(environment.getRequiredProperty("jdbc.url"));
    ds.setUsername(environment.getRequiredProperty("jdbc.username"));
    ds.setPassword(environment.getRequiredProperty("jdbc.password"));

    ds.setIdleConnectionTestPeriodInMinutes(60);
    ds.setIdleMaxAgeInMinutes(420);
    ds.setMaxConnectionsPerPartition(30);
    ds.setMaxConnectionsPerPartition(10);
    ds.setPartitionCount(10);
    ds.setAcquireIncrement(5);
    ds.setStatementsCacheSize(100);
    ds.setReleaseHelperThreads(3);
    return ds;
  }

  @Bean
  public DataSource dataSource() {
    //HikariDataSource dataSource = new HikariDataSource();
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
    dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
    dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
    dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

    // coba pake hikari

    //dataSource.setMaximumPoolSize(100);
    //dataSource.setDataSourceClassName(environment.getRequiredProperty("dataSourceClassName"));
    //Properties prop = new Properties();
    //prop.put("user", "PBB");
    //prop.put("password", "RAHASIAPBB");
    //prop.put("databaseName", "SISMIOP");
    //prop.put("serverName", "192.168.2.7");
    //prop.put("driverType", "thin");
    //dataSource.setDataSourceProperties(prop);
    //dataSource.addDataSourceProperty("url", environment.getRequiredProperty("url"));
    //dataSource.addDataSourceProperty("user", environment.getRequiredProperty("username"));
    //dataSource.addDataSourceProperty("password", environment.getRequiredProperty("password"));
    //dataSource.addDataSourceProperty("initializationFailFast", "false");
    return dataSource;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

    /** bonecp */

    /** hikaricp */
    //properties.put("hibernate.connection.provider_class", environment.getRequiredProperty("providerClass"));
    //properties.put("hibernate.hikari.minimumIdle", environment.getRequiredProperty("minIdle"));
    //properties.put("hibernate.hikari.maximumPoolSize", environment.getRequiredProperty("maxPool"));
    //properties.put("hibernate.hikari.dataSourceClassName", environment.getRequiredProperty("dataSourceClassName"));
    //properties.put("hibernate.hikari.dataSource.url", environment.getRequiredProperty("url"));
    //properties.put("hibernate.hikari.serverName", "192.168.2.7");
    //properties.put("hibernate.hikari.port", "1521");
    //properties.put("hibernate.hikari.databaseName", "SISMIOP");
    //properties.put("hibernate.hikari.dataSource.implicitCachingEnabled", environment.getRequiredProperty("implicitCache"));
    //properties.put("hibernate.hikari.initializationFailFast", "false");

    return properties;
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory sf) {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sf);
    return txManager;
  }
}
