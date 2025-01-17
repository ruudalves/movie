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
    public Optional<MovieDto> findByName(String title) {
        return Optional.ofNullable(movieRepository.findByTitle(title).toDto());
    }

    @Override
    public Optional<MovieDto> findById(Long id) {
        return Optional.of(movieRepository.findById(id).map(Movie::toDto).orElse(new MovieDto()));
    }

    @Override
    public Optional<MovieDto> createMovie(MovieDto movieDto) {
        Movie movie = new Movie().fromDto(movieDto);
        if(movie.validateInputs() || movie.getId()!=null) throw new IllegalArgumentException("Validation failure");
        return Optional.of(movieRepository.save(movie)).map(Movie::toDto);
    }

    @Override
    public Optional<MovieDto> updateMovie(MovieDto movieDto) {
        Movie movie = new Movie().fromDto(movieDto);
        if(movie.validateInputs()) throw new IllegalArgumentException("Validation failure");
        return Optional.of(movieRepository.save(movie)).map(Movie::toDto);
    }

    @Override
    public void deleteMovie(MovieDto movie) {
        movieRepository.delete(new Movie().fromDto(movie));
    }
}
