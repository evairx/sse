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

   public Usuario actualizar(Integer id, Usuario usuarioUpdate) {
        Usuario usuarioExist = usuarioRepository.findById(id).orElse(null);
        
        if (usuarioExist == null) {
            return null;
        }
        
        usuarioExist.setPnombre_usu(usuarioUpdate.getPnombre_usu());
        usuarioExist.setSnombre_usu(usuarioUpdate.getSnombre_usu());
        usuarioExist.setAppaterno_usu(usuarioUpdate.getAppaterno_usu());
        usuarioExist.setApmaterno_usu(usuarioUpdate.getApmaterno_usu());
        usuarioExist.setCorreo_usu(usuarioUpdate.getCorreo_usu());
        usuarioExist.setTipo_rol_usu(usuarioUpdate.getTipo_rol_usu());
        
        return usuarioRepository.save(usuarioExist);
    }
}