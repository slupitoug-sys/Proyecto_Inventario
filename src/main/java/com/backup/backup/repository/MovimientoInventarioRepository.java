package com.backup.backup.repository;
import com.backup.backup.model.MovimientoInventario; // Ajusta según el nombre de tu clase modelo
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
    // Aquí ya tienes heredado el findAll(), save(), delete(), etc.
}
