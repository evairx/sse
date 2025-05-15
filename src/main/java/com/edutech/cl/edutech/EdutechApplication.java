package com.edutech.cl.edutech;

import com.edutech.cl.edutech.controller.UsuarioController;
import com.edutech.cl.edutech.controller.CursoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EdutechApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EdutechApplication.class, args);
    
        context.getBean(UsuarioController.class);
        context.getBean(CursoController.class);
    }
}