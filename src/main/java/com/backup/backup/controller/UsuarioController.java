package com.backup.backup.controller;

import com.backup.backup.model.Usuario;
import com.backup.backup.repository.RolRepository;
import com.backup.backup.service.UsuarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    private final RolRepository roles;

    public UsuarioController(UsuarioService service, RolRepository roles) {
        this.service = service;
        this.roles = roles;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String q, @RequestParam(required = false) String estado, Model model) {
        model.addAttribute("usuarios", service.listar(q, estado));
        model.addAttribute("q", q == null ? "" : q);
        model.addAttribute("estadoFiltro", estado == null ? "" : estado);
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Usuario usuario = new Usuario();
        usuario.setEstado(true);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Registrar usuario");
        model.addAttribute("roles", roles.findByEstadoTrueOrderByNombreAsc());
        return "usuarios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        return service.buscar(id).map(usuario -> {
            model.addAttribute("usuario", usuario);
            model.addAttribute("titulo", "Editar usuario");
            model.addAttribute("roles", roles.findByEstadoTrueOrderByNombreAsc());
            return "usuarios/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "El usuario indicado no existe.");
            return "redirect:/usuarios";
        });
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, @RequestParam(required = false) String passwordNueva,
                          @RequestParam(required = false) String confirmarPassword, Model model,
                          RedirectAttributes flash) {
        String titulo = usuario.getIdUsuario() == null ? "Registrar usuario" : "Editar usuario";
        String error = null;
        if (vacio(usuario.getNombre()) || vacio(usuario.getApellido()) || vacio(usuario.getUsername()) || usuario.getRol() == null || usuario.getRol().getIdRol() == null)
            error = "Completa nombre, apellido, nombre de usuario y rol.";
        else if (usuario.getNombre().length() > 80 || usuario.getApellido().length() > 80 || usuario.getUsername().length() > 50)
            error = "Revisa la longitud de los campos: nombre y apellido hasta 80 caracteres, usuario hasta 50.";
        else if (service.usernameEnUso(usuario.getUsername().trim(), usuario.getIdUsuario()))
            error = "Ese nombre de usuario ya está registrado.";
        else if (usuario.getIdUsuario() == null && vacio(passwordNueva))
            error = "La contraseña es obligatoria para un usuario nuevo.";
        else if (!vacio(passwordNueva) && !passwordNueva.equals(confirmarPassword))
            error = "La contraseña y su confirmación no coinciden.";
        else if (!vacio(passwordNueva) && passwordNueva.length() < 8)
            error = "La contraseña debe tener al menos 8 caracteres.";
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("usuario", usuario);
            model.addAttribute("titulo", titulo);
            model.addAttribute("roles", roles.findByEstadoTrueOrderByNombreAsc());
            return "usuarios/formulario";
        }
        try {
            usuario.setUsername(usuario.getUsername().trim());
            if (usuario.getCorreo() != null && usuario.getCorreo().isBlank()) usuario.setCorreo(null);
            service.guardar(usuario, passwordNueva);
            flash.addFlashAttribute("mensaje", "Usuario guardado correctamente.");
            return "redirect:/usuarios";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("error", "No se pudo guardar el usuario. Verifica que el nombre de usuario no esté duplicado y que el rol exista.");
            model.addAttribute("usuario", usuario);
            model.addAttribute("titulo", titulo);
            model.addAttribute("roles", roles.findByEstadoTrueOrderByNombreAsc());
            return "usuarios/formulario";
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        if (service.buscar(id).isEmpty()) flash.addFlashAttribute("error", "El usuario indicado no existe.");
        else try {
            service.eliminar(id);
            flash.addFlashAttribute("mensaje", "Usuario eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            flash.addFlashAttribute("error", "No se puede eliminar porque este usuario tiene movimientos o entradas asociados. Edita el usuario y desactívalo para conservar el historial.");
        }
        return "redirect:/usuarios";
    }

    private boolean vacio(String valor) {
        return valor == null || valor.isBlank();
    }
}