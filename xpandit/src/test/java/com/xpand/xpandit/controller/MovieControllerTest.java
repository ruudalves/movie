package com.xpand.xpandit.controller;

import com.xpand.xpandit.domain.Ranks;
import com.xpand.xpandit.dto.MovieDto;
import com.xpand.xpandit.repository.MovieRepository;
import com.xpand.xpandit.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MovieControllerTest {

    @Mock
    MovieService movieService;
    @Mock
    MovieController movieController;
    @Mock
    MovieRepository repository;

    @Test
    void should_createMovie(){
        MovieDto movieDto = MovieDto.builder()
                .title("JUst a test")
                .rank(Ranks.One)
                .date(LocalDate.now())
                .revenue(1234566D)
                .build();

        ResponseEntity<MovieDto> created = movieController.createMovie(movieDto);

        assertNotNull(created.getBody());
    }
}
