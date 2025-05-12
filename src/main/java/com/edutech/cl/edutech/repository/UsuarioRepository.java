package com.edutech.cl.edutech.repository;

import com.edutech.cl.edutech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    @Query("SELECT u FROM Usuario u WHERE u.id_usuario = :id")
    Optional<Usuario> encontrarPorId(Integer id);
}