package com.edutech.cl.edutech.services;

import com.edutech.cl.edutech.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Map<String, Object>> conseguir() {
        String sql = "SELECT * FROM usuario";

        List<Usuario> usuarios = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class));

        List<Map<String, Object>> usuariosFormateados = new ArrayList<>();
        
        for (Usuario usuario : usuarios) {
            Map<String, Object> usuarioFormateado = new HashMap<>();
            
            usuarioFormateado.put("id", usuario.getId_usuario());
            usuarioFormateado.put("apellidoPaterno", usuario.getAppaterno_usu());
            usuarioFormateado.put("apellidoMaterno", usuario.getApmaterno_usu());
            usuarioFormateado.put("correo", usuario.getCorreo_usu());
            usuarioFormateado.put("fechaRegistro", usuario.getFec_registro_usu());
            usuarioFormateado.put("nombre", usuario.getPnombre_usu());
            usuarioFormateado.put("segundoNombre", usuario.getSnombre_usu());
            usuarioFormateado.put("rol", usuario.getTipo_rol_usu());
            
            usuariosFormateados.add(usuarioFormateado);
        }
        
        return usuariosFormateados;
    }
    
    public List<Usuario> obtenerTodosUsuarios() {
        String sql = "SELECT * FROM usuario";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class));
    }
}