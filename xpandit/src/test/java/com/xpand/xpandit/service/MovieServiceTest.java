package com.xpand.xpandit.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {

    @Qualifier("movieServiceImpl")
    @Autowired
    MovieService service;

    @Test
    void should_findAllMovies(){
        assertNotNull(service.findAll(Pageable.unpaged()));
    }

    @Test
    void should_findMoviesInDate(){
        assertEquals(2,service.findByDate(LocalDate.of(2000, 4,30)).size());
    }

    @Test
    void should_not_findMoviesInDate(){
        assertTrue(service.findByDate(LocalDate.of(2000, 4,29)).isEmpty());
    }


}
