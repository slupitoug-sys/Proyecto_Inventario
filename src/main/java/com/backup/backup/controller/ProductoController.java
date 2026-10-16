package com.backup.backup.controller;

import com.backup.backup.model.Producto;
import com.backup.backup.service.ProductoService;
import com.backup.backup.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService,
                              CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "productos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Producto producto = new Producto();
        producto.setEstado(true);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("titulo", "Registrar producto");
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("producto") Producto producto,
                          BindingResult resultado,
                          Model model,
                          RedirectAttributes flash) {

        if (resultado.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            model.addAttribute("titulo",
                    producto.getIdProducto() == null
                            ? "Registrar producto"
                            : "Editar producto");
            return "productos/formulario";
        }

        productoService.guardar(producto);
        flash.addFlashAttribute("mensaje", "Producto guardado correctamente.");
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model model,
                         RedirectAttributes flash) {

        return productoService.buscarPorId(id).map(producto -> {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaService.listarTodas());
            model.addAttribute("titulo", "Editar producto");
            return "productos/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "El producto indicado no existe.");
            return "redirect:/productos";
        });
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id,
                           RedirectAttributes flash) {

        if (productoService.buscarPorId(id).isEmpty()) {
            flash.addFlashAttribute("error", "El producto indicado no existe.");
        } else {
            try {
                productoService.eliminar(id);
                flash.addFlashAttribute("mensaje",
                        "Producto eliminado correctamente.");
            } catch (DataIntegrityViolationException ex) {
                flash.addFlashAttribute("error",
                        "No se puede eliminar este producto porque tiene existencias o movimientos relacionados.");
            }
        }

        return "redirect:/productos";
    }
}