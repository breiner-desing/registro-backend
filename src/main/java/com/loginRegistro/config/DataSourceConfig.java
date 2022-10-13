
package com.loginRegistro.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
    
    @Bean(name = "dbRegistro")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().driverClassName("org.postgresql.Driver").build();
    }

    @Bean(name = "jdbcRegistrar")
    public JdbcTemplate jdbcTransacciones(@Qualifier("dbRegistro") DataSource dataSource1) {
        return new JdbcTemplate(dataSource1);
    }
    
}
