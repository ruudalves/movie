package com.xpand.xpandit.controller;

import com.xpand.xpandit.dto.MovieDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieControllerTest {

    @Mock
    MovieController movieController;

    @Test
    void should_createMovie(){
        MovieDto movieDto = MovieDto.builder()
                .title("Just a test")
                .rank(1)
                .date(LocalDate.now())
                .revenue(1234566D)
                .build();

        ResponseEntity<MovieDto> created = movieController.createMovie(movieDto);

        assertNotNull(created.getBody());
    }

    @Test
    void should_getAllMovies(){
        assertFalse(Objects.requireNonNull(movieController.findAll(Pageable.unpaged()).getBody()).isEmpty());
    }

    @Test
    void should_getMoreThanOneMovieByDate()
    {
        assertEquals(2, Objects.requireNonNull(movieController.findByDate(LocalDate.of(2000, 4, 30)).getBody()).size());
    }
}
