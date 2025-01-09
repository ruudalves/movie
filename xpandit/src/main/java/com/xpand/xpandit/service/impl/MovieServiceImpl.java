package com.xpand.xpandit.service.impl;


import com.xpand.xpandit.domain.Movie;
import com.xpand.xpandit.dto.MovieDto;
import com.xpand.xpandit.repository.MovieRepository;
import com.xpand.xpandit.service.MovieService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable).stream().map(Movie::toDto).toList();
    }

    @Override
    public List<MovieDto> findByDate(LocalDate date) {
        List<Movie> foundMovies = movieRepository.findByDate(date);
        if(!foundMovies.isEmpty()){
            return foundMovies.stream().map(Movie::toDto).toList();
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<MovieDto> findByName(MovieDto movie) {
        return Optional.ofNullable(movieRepository.findByTitle(movie.getTitle()).toDto());
    }

    @Override
    public Optional<MovieDto> findById(MovieDto movie) {
        return Optional.of(movieRepository.findById(movie.getId()).map(Movie::toDto).orElse(new MovieDto()));
    }

    @Override
    public Optional<MovieDto> createMovie(MovieDto movieDto) {
        return Optional.of(movieRepository.save(new Movie().fromDto(movieDto))).map(Movie::toDto);
    }

    @Override
    public Optional<MovieDto> updateMovie(MovieDto movie) {
        return Optional.of(movieRepository.save(new Movie().fromDto(movie))).map(Movie::toDto);
    }

    @Override
    public void deleteMovie(MovieDto movie) {
        movieRepository.delete(new Movie().fromDto(movie));
    }
}
