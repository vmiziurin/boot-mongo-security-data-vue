package com.vmiziurin.gfootball.controller;

import com.vmiziurin.gfootball.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class Main {
    @Autowired
    private PlayerRepo repo;

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> frontData = new HashMap<>();
        frontData.put("profile", user);
        frontData.put("players", repo.findAll());
        model.addAttribute("frontData", frontData);
        return "index";
    }
}
