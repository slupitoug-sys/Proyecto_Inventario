package com.backup.backup.repository;

import com.backup.backup.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Hereda automáticamente métodos como findAll(), save(), findById(), etc.
}
