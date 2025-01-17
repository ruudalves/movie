package com.xpand.xpandit.controller;

import com.xpand.xpandit.dto.MovieDto;
import com.xpand.xpandit.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    private final MovieService service;

    @Autowired
    public MovieController(@Qualifier("movieService") MovieService service) {
        this.service = service;
    }

    @GetMapping ("/all")
    public ResponseEntity<List<MovieDto>> findAll(Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<MovieDto>> findByDate(@PathVariable LocalDate date){
        List<MovieDto> data = service.findByDate(date);
        if(!data.isEmpty())
            return ResponseEntity.ok(data);
        else
            return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MovieDto> findById(@PathVariable Long id){
        Optional<MovieDto> movieDto = service.findById(id);

        return movieDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value())).build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<MovieDto> findByTitle(@PathVariable String title){
        Optional<MovieDto> movieDto = service.findByName(title);
        return movieDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value())).build());
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(MovieDto movie){
        if(movie.getId()!=null)
            return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())).build();

        Optional<MovieDto> createdMovie = service.createMovie(movie);
        return createdMovie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())).build());
    }

    @PutMapping
    public ResponseEntity<MovieDto> updateMovie(MovieDto movie){
        Optional<MovieDto> updatedMovie = service.updateMovie(movie);
        return updatedMovie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())).build());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteMovie(MovieDto movie){
        if(movie.getId()==null)
            return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())).build();
        service.deleteMovie(movie);
        return ResponseEntity.ok().build();
    }
}
