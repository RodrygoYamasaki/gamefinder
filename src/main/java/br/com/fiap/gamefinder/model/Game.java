package br.com.fiap.gamefinder.model;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Game {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate releaseDate;

    private Double rating;

    private Genre genre;

    private Platform platform;

    private String coverUrl;

    private String backdropUrl;

    private boolean inWishlist;
}
