package com.backup.backup.repository;
import com.backup.backup.model.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> { }
