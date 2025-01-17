package com.xpand.xpandit.service;

import com.xpand.xpandit.dto.MovieDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface MovieService {

    List<MovieDto> findAll(Pageable pageable);

    List<MovieDto> findByDate(LocalDate date);

    Optional<MovieDto> findByName(String title);

    Optional<MovieDto> findById(Long id);

    Optional<MovieDto> createMovie(MovieDto movieDto);

    Optional<MovieDto> updateMovie(MovieDto movie);

    void deleteMovie(MovieDto movie);
}
