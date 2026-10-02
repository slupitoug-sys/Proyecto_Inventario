package com.backup.service;

import com.backup.backup.model.Usuario;
import com.backup.backup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos los usuarios
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Buscar usuario por ID
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Guardar o actualizar usuario
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Verificar si existe un usuario
    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }
}