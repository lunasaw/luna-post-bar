package com.luna.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author luna@mac
 * 2021年04月29日 09:13
 */
@Controller
public class PageController {

    @GetMapping("/menu")
    public String main() {
        return "menu";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
