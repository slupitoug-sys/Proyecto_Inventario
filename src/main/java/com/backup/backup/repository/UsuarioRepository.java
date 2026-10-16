package com.backup.backup.repository;

import com.backup.backup.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCaseAndIdUsuarioNot(String username, Long idUsuario);

    List<Usuario> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrCorreoContainingIgnoreCase(
            String nombre, String apellido, String username, String correo);

    Optional<Usuario> findByUsernameIgnoreCase(String username);
}
