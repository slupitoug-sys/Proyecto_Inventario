package com.backup.backup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorVistas {

    // Ruta raíz: http://localhost:8080/ abre el login directo
    @GetMapping("/")
    public String raiz() {
        return "login";
    }

    // Ruta explícita por si escriben /ingresar
    @GetMapping("/ingresar")
    public String mostrarLogin() {
        return "login";
    }

    // Acción al presionar el botón Ingresar del formulario
    @PostMapping("/ingresar")
    public String procesarLogin() {
        return "redirect:/panel";
    }

    // Panel principal
    @GetMapping("/panel")
    public String mostrarPanel() {
        return "dashboard";
    }
    @GetMapping("/inventario")
public String mostrarInventario() {
    return "mods"; // Busca mods.html en templates
}
}