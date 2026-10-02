package com.backup.backup.controller;

import com.backup.backup.repository.MovimientoInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoInventarioRepository movimientoRepository;

    @GetMapping
    public String listarMovimientos(Model model) {
        model.addAttribute("movimientos", movimientoRepository.findAll());
        return "movimientos/movimientos";
    }
}
