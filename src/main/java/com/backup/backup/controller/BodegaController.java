package com.backup.backup.controller;

import com.backup.backup.model.Bodega;
import com.backup.backup.service.BodegaService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bodegas")
public class BodegaController {

    private final BodegaService bodegaService;

    public BodegaController(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bodegas", bodegaService.listarTodas());
        return "bodegas/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        Bodega bodega = new Bodega();
        bodega.setEstado(true);
        model.addAttribute("bodega", bodega);
        model.addAttribute("titulo", "Registrar bodega");
        return "bodegas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("bodega") Bodega bodega,
                          BindingResult resultado,
                          Model model,
                          RedirectAttributes flash) {

        if (resultado.hasErrors()) {
            model.addAttribute("titulo",
                    bodega.getIdBodega() == null
                            ? "Registrar bodega"
                            : "Editar bodega");
            return "bodegas/formulario";
        }

        bodegaService.guardar(bodega);
        flash.addFlashAttribute("mensaje", "Bodega guardada correctamente.");
        return "redirect:/bodegas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model model,
                         RedirectAttributes flash) {

        return bodegaService.buscarPorId(id).map(bodega -> {
            model.addAttribute("bodega", bodega);
            model.addAttribute("titulo", "Editar bodega");
            return "bodegas/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "La bodega indicada no existe.");
            return "redirect:/bodegas";
        });
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id,
                           RedirectAttributes flash) {

        if (bodegaService.buscarPorId(id).isEmpty()) {
            flash.addFlashAttribute("error", "La bodega indicada no existe.");
        } else {
            try {
                bodegaService.eliminar(id);
                flash.addFlashAttribute("mensaje",
                        "Bodega eliminada correctamente.");
            } catch (DataIntegrityViolationException ex) {
                flash.addFlashAttribute("error",
                        "No se puede eliminar esta bodega porque tiene existencias o movimientos relacionados.");
            }
        }

        return "redirect:/bodegas";
    }
}