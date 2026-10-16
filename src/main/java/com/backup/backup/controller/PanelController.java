package com.backup.backup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class PanelController {

    @GetMapping
    public String panel(Model model) {
        model.addAttribute("titulo", "Panel Principal");
        return "panel/index";
    }
}