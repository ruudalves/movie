package com.xpand.xpandit.repository;

import com.xpand.xpandit.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByDate(LocalDate date);

    Movie findByTitle(String title);

}
