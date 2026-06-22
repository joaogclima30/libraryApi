package io.github.joaogclima30.libraryapi.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//Serve para configurar o banco de dados, mas n necessario ja que o application.yml ja cria
@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;


    //@Bean
    //Não muito usado muito simples
    /*public DataSource dataSource() {
        DriverManagerDataSource dataSource = new  DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);

        return dataSource;
    }*/


    /*Melhor usado
    em duvida consutar https://github.com/brettwooldridge/HikariCP
     */
    public DataSource hikariDataSource() {
        HikariConfig dataSource = new HikariConfig();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setJdbcUrl(url);

        //Define um maximo de conexões ao mesmo tempo
        dataSource.setMaximumPoolSize(10);
        //Define o minimo de conexões inicias
        dataSource.setMinimumIdle(1);
        //Tamanha maximo de conexão (em Milisegundos)
        dataSource.setMaxLifetime(30000);
        //Query de teste
        dataSource.setConnectionTestQuery("Select 1");

        return new HikariDataSource(dataSource);
    }
}