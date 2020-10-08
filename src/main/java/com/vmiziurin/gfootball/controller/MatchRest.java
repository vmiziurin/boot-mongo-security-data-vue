package com.vmiziurin.gfootball.controller;

import com.vmiziurin.gfootball.model.Match;
import com.vmiziurin.gfootball.repos.MatchRepo;
import org.bson.types.ObjectId;
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
@RequestMapping("/api/matches")
public class MatchRest {
    @Autowired
    private MatchRepo repo;

    @GetMapping
    public List<Match> getAllMatches() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Match getMatchById(@PathVariable("id") ObjectId id) {
        return repo.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMatch(@RequestBody Match match) {
        match.setId(ObjectId.get());
        repo.save(match);
    }

    @PutMapping("{id}")
    public Match modifyMatchById(@PathVariable("id") ObjectId id, @RequestBody Match match) {
        match.setId(id);
        return repo.save(match);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable ObjectId id) {
        repo.delete(repo.findById(id));
    }
}
