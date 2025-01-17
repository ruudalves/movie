package com.xpand.xpandit.domain;

import com.xpand.xpandit.dto.MovieDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
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
                .rank(getRank())
                .build();
    }

    public Movie fromDto(MovieDto movieDto){
        return Movie.builder().date(movieDto.getDate())
                .rank(movieDto.getRank())
                .title(movieDto.getTitle())
                .revenue(movieDto.getRevenue())
                .id(movieDto.getId())
                .build();

    }

    public boolean validateInputs(){
        return this.rank > 10 || this.rank < 1 || this.revenue < 0 || this.date == null || this.title == null || this.title.isEmpty();
    }

}
