package com.backup.backup.controller;

import com.backup.backup.model.Categoria;
import com.backup.backup.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categorias/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        Categoria categoria = new Categoria();
        categoria.setEstado(true);
        model.addAttribute("categoria", categoria);
        model.addAttribute("titulo", "Registrar categoría");
        return "categorias/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("categoria") Categoria categoria,
                          BindingResult resultado, Model model,
                          RedirectAttributes redirectAttributes) {
        if (resultado.hasErrors()) {
            model.addAttribute("titulo", categoria.getIdCategoria() == null
                    ? "Registrar categoría" : "Editar categoría");
            return "categorias/formulario";
        }

        categoriaService.guardar(categoria);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría guardada correctamente.");
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model,
                                          RedirectAttributes redirectAttributes) {
        return categoriaService.buscarPorId(id).map(categoria -> {
            model.addAttribute("categoria", categoria);
            model.addAttribute("titulo", "Editar categoría");
            return "categorias/formulario";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("error", "La categoría indicada no existe.");
            return "redirect:/categorias";
        });
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (categoriaService.buscarPorId(id).isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "La categoría indicada no existe.");
        } else {
            try {
                categoriaService.eliminar(id);
                redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada correctamente.");
            } catch (DataIntegrityViolationException ex) {
                redirectAttributes.addFlashAttribute("error",
                        "No se puede eliminar esta categoría porque tiene productos asociados.");
            }
        }

        return "redirect:/categorias";
    }
}