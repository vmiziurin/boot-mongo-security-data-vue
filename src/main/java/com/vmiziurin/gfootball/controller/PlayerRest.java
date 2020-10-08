package com.vmiziurin.gfootball.controller;

import com.vmiziurin.gfootball.model.Player;
import com.vmiziurin.gfootball.repos.PlayerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerRest {
    @Autowired
    private PlayerRepo repo;

    @GetMapping
    public List<Player> getAllPlayers() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") Player player) {
        return player;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player createPlayer(@RequestBody Player player) {
        return repo.save(player);
    }

    @PutMapping("/{id}")
    public Player modifyPlayerById(@PathVariable("id") Player playerFromDb, @RequestBody Player playerFromUser) {
        BeanUtils.copyProperties(playerFromUser, playerFromDb, "id");
        return repo.save(playerFromDb);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayer(@PathVariable("id") Player player) {
        repo.delete(player);
    }
}
