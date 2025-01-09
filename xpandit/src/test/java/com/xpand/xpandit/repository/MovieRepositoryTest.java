package com.xpand.xpandit.repository;

import com.xpand.xpandit.domain.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MovieRepositoryTest {


    @Autowired
    private MovieRepository repository;

    @Test
    void should_createMovie(){

        assertNotNull(repository.save(Movie.builder()
                .id(6L)
                .title("JUst a test")
                .rank(1)
                .date(LocalDate.now())
                .revenue(1234566D)
                .build()));
    }

    @Test
    void should_findMoreThanOneMovie()
    {
        LocalDate date = LocalDate.of(2000,04,30);

        assertEquals(2,repository.findByDate(date).size());

    }
}
