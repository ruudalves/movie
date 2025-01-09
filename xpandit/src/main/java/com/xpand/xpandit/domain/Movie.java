package com.xpand.xpandit.domain;

import com.xpand.xpandit.dto.MovieDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIE")
public class Movie {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "RANK")
    private int rank;

    @Column(name = "REVENUE")
    private Double revenue;

    public MovieDto toDto(){
        return MovieDto.builder().id(getId())
                .title(getTitle())
                .revenue(getRevenue())
                .date(getDate())
                .rank(Ranks.valueOf(String.valueOf(getRank())))
                .build();
    }

    public Movie fromDto(MovieDto movieDto){
        return Movie.builder().date(movieDto.getDate())
                .rank(movieDto.getRank().getValue())
                .title(movieDto.getTitle())
                .revenue(movieDto.getRevenue())
                .id(movieDto.getId())
                .build();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}
