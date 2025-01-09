package com.xpand.xpandit.configuration;

import com.xpand.xpandit.XpanditApplication;
import com.xpand.xpandit.repository.MovieRepository;
import com.xpand.xpandit.service.MovieService;
import com.xpand.xpandit.service.impl.MovieServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = XpanditApplication.class)
public class BeanConfiguration {

    @Bean
    MovieService movieService(MovieRepository movieRepository){
        return new MovieServiceImpl(movieRepository);
    }
}