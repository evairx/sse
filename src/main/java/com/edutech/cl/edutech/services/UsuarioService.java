package com.edutech.cl.edutech.services;

import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> conseguir() {
        return usuarioRepository.findAll();
    }

    public Usuario conseguirPorId(Integer id) {
        return usuarioRepository.encontrarPorId(id).orElse(null);
    }
}