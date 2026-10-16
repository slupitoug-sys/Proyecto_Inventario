package com.backup.backup.controller;

import com.backup.backup.model.Proveedor;
import com.backup.backup.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", proveedorService.listarTodos());
        return "proveedores/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Proveedor proveedor = new Proveedor();
        proveedor.setEstado(true);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("titulo", "Registrar proveedor");
        return "proveedores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("proveedor") Proveedor proveedor,
                          BindingResult resultado,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        if (resultado.hasErrors()) {
            model.addAttribute("titulo",
                    proveedor.getIdProveedor() == null
                            ? "Registrar proveedor"
                            : "Editar proveedor");
            return "proveedores/formulario";
        }

        proveedorService.guardar(proveedor);
        redirectAttributes.addFlashAttribute("mensaje",
                "Proveedor guardado correctamente.");
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id,
                                          Model model,
                                          RedirectAttributes redirectAttributes) {

        return proveedorService.buscarPorId(id).map(proveedor -> {
            model.addAttribute("proveedor", proveedor);
            model.addAttribute("titulo", "Editar proveedor");
            return "proveedores/formulario";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("error",
                    "El proveedor indicado no existe.");
            return "redirect:/proveedores";
        });
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id,
                           RedirectAttributes redirectAttributes) {

        if (proveedorService.buscarPorId(id).isEmpty()) {
            redirectAttributes.addFlashAttribute("error",
                    "El proveedor indicado no existe.");
        } else {
            try {
                proveedorService.eliminar(id);
                redirectAttributes.addFlashAttribute("mensaje",
                        "Proveedor eliminado correctamente.");
            } catch (DataIntegrityViolationException ex) {
                redirectAttributes.addFlashAttribute("error",
                        "No se puede eliminar este proveedor porque tiene entradas de inventario asociadas.");
            }
        }

        return "redirect:/proveedores";
    }
}