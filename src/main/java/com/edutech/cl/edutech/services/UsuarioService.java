package com.edutech.cl.edutech.services;

import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> conseguir() {
        return usuarioRepository.findAll();
    }

    public Usuario conseguirPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario crear(Usuario usuario) {
        if (usuario.getFec_registro_usu() == null) {
            usuario.setFec_registro_usu(new Date());
        }
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}