package com.backup.service;

import com.backup.backup.model.Producto;
import com.backup.backup.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Listar todos los productos
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    // Buscar producto por ID
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Guardar o actualizar producto
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    // Eliminar producto
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    // Verificar si existe un producto
    public boolean existePorId(Long id) {
        return productoRepository.existsById(id);
    }
}