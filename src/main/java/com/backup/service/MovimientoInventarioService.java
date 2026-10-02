package com.backup.service;

import com.backup.backup.model.MovimientoInventario;
import com.backup.backup.repository.MovimientoInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    // Listar todos los movimientos
    public List<MovimientoInventario> listarTodos() {
        return movimientoInventarioRepository.findAll();
    }

    // Buscar movimiento por ID
    public Optional<MovimientoInventario> buscarPorId(Long id) {
        return movimientoInventarioRepository.findById(id);
    }

    // Guardar o actualizar movimiento
    public MovimientoInventario guardar(MovimientoInventario movimientoInventario) {
        return movimientoInventarioRepository.save(movimientoInventario);
    }

    // Eliminar movimiento
    public void eliminar(Long id) {
        movimientoInventarioRepository.deleteById(id);
    }

    // Verificar si existe un movimiento
    public boolean existePorId(Long id) {
        return movimientoInventarioRepository.existsById(id);
    }
}