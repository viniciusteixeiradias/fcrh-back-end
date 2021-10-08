package br.gov.prodemge.seplag.fcrhprova.config.oracle;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.gov.prodemge.seplag.fcrhprova.persistencia.oracle", 
                        entityManagerFactoryRef = "oracleEntityManagerFactory", 
                        transactionManagerRef = "oracleTransactionManager")
public class OracleDatasourceConfig {

	@Primary
    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "app.datasource.oracle")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

	@Primary
    @Bean(name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("oracleDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                      .packages("br.gov.prodemge.seplag.fcrhprova.entidades.oracle")
                      .persistenceUnit("oraclePU")
                      .properties(hibernateProperties())
                      .build();
    }

	@Primary
    @Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager oracleTransactionManager(
            @Qualifier("oracleEntityManagerFactory") EntityManagerFactory oracleEntityManagerFactory) {
        return new JpaTransactionManager(oracleEntityManagerFactory);
    }

    protected Map<String, String> hibernateProperties() {
        return new HashMap<String, String>() {
            {
                put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
                put("hibernate.hbm2ddl.auto", "none");
                put("hibernate.show_sql", "true");            	
            }
        };
    }

}

