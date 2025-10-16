package br.com.fiap.gamefinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.gamefinder.model.Game;
import br.com.fiap.gamefinder.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("games")
@Slf4j
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> index() {
        return gameRepository.findAll();
    }

    @GetMapping("{id}")
    public Game get(@PathVariable Long id) {
        log.info("Buscando game com id: " + id);
        return getGameById(id);
    }

    @GetMapping("/genres/{genreId}")
    public List<Game> getGamesByGenre(@PathVariable Long genreId) {
        log.info("Buscando games para o gênero com id: " + genreId);

        List<Game> games = gameRepository.findByGenreId(genreId);

        if (games.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum jogo encontrado para o gênero com id " + genreId);
        }

        return games;
    }

    public List<Game> getGamesByPlatform(@PathVariable long platformId) {
        log.info("Buscando jogos para a plataforma com id: " + platformId);

        List<Game> games = gameRepository.findByPlatformId(platformId);

        if (games.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum jogo encontrado para a plataforma com id: " + platformId);
        }
        
        return games;
    }

    private Game getGameById(Long id) {
        return gameRepository
                    .findById(id)
                    .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game não encontrado com id: " + id)
                    );
    }
}
