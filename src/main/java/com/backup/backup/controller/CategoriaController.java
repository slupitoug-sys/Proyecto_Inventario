package com.backup.backup.controller;
import com.backup.backup.model.Categoria;
import com.backup.backup.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String listarCategorias(Model model) {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("categorias", listaCategorias);
        return "categorias/categorias";

    }
}
