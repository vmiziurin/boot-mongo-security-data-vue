package com.vmiziurin.gfootball.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("auth")
public class AuthRest {

    @PostMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(req, resp, null);
    }
}
