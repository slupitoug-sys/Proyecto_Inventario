package com.backup.backup.repository;

import com.backup.backup.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí ya tienes heredado el findAll(), save(), etc.
}
