package com.xpand.xpandit.dto;

import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title;
    private LocalDate date;
    private Integer rank;
    private Double revenue;
}
