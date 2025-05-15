package com.edutech.cl.edutech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@SpringBootApplication
public class EdutechApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdutechApplication.class, args);
    }

    @GetMapping("/usuario")
    public String mostrarHoli() {
        return "holi";
    }
}