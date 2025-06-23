package com.edutech.cl.edutech;

import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.model.Evaluacion;
import com.edutech.cl.edutech.model.Pago;
import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.repository.CursoRepository;
import com.edutech.cl.edutech.repository.EvaluacionRepository;
import com.edutech.cl.edutech.repository.PagoRepository;
import com.edutech.cl.edutech.repository.UsuarioRepository;

import net.datafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {
    
    private static final Logger logger = Logger.getLogger(DataLoader.class.getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private PagoRepository pagoRepository;
    
    @Value("${spring.profiles.active}")
    private String activeProfile;
    
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Override
    public void run(String... args) {
        try {
            if (activeProfile.equals("test") && !databaseUrl.contains("edutech_test")) {
                logger.warning("El perfil activo es 'test' pero no estás usando la base de datos edutech_test");
                System.out.println("ADVERTENCIA: El perfil activo es 'test' pero la base de datos no es edutech_test");
                System.out.println("Base de datos actual: " + databaseUrl);
                return;
            } else if (activeProfile.equals("dev") && !databaseUrl.contains("edutech_dev")) {
                logger.warning("El perfil activo es 'dev' pero no estás usando la base de datos edutech_dev");
                System.out.println("ADVERTENCIA: El perfil activo es 'dev' pero la base de datos no es edutech_dev");
                System.out.println("Base de datos actual: " + databaseUrl);
                return;
            }
            
            logger.info("DataLoader ejecutándose con perfil: " + activeProfile);
            logger.info("Conectado a la base de datos: " + databaseUrl);
            System.out.println("DataLoader ejecutándose con perfil: " + activeProfile);
            System.out.println("Conectado a la base de datos: " + databaseUrl);
            
            cargarDatosDePrueba();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar datos de prueba: " + e.getMessage(), e);
            System.out.println("Error al cargar datos de prueba: " + e.getMessage());
            System.out.println("La aplicación continuará ejecutándose con los datos que se hayan cargado correctamente.");
        }
    }
    
    @Transactional
    public void cargarDatosDePrueba() {
        if (usuarioRepository.count() > 0) {
            System.out.println("La base de datos ya contiene datos, no se cargarán nuevos datos de prueba");
            System.out.println("- Usuarios existentes: " + usuarioRepository.count());
            System.out.println("- Cursos existentes: " + cursoRepository.count());
            System.out.println("- Pagos existentes: " + pagoRepository.count());
            System.out.println("- Evaluaciones existentes: " + evaluacionRepository.count());
            return;
        }

        System.out.println("Base de datos vacía, cargando datos de prueba...");

        Faker faker = new Faker(java.util.Locale.forLanguageTag("es"));
        Random random = new Random();

        System.out.println("Starting data loading for " + activeProfile + " profile");

        crearUsuarios(faker, random);
        
        crearCursos(faker);
        
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Curso> cursos = cursoRepository.findAll();
        
        if (usuarios.isEmpty()) {
            System.out.println("No se encontraron usuarios para crear pagos o evaluaciones");
            return;
        }
        
        crearPagos(faker, random, usuarios);
        
        crearEvaluaciones(faker, random, usuarios, cursos);

        System.out.println("Data loaded successfully for " + activeProfile + " profile");
        System.out.println("- Created " + usuarioRepository.count() + " usuarios");
        System.out.println("- Created " + cursoRepository.count() + " cursos");
        System.out.println("- Created " + pagoRepository.count() + " pagos");
        System.out.println("- Created " + evaluacionRepository.count() + " evaluaciones");
    }
    
    private void crearUsuarios(Faker faker, Random random) {
        try {
            for (int i = 0; i < 20; i++) {
                Usuario usuario = new Usuario();
                
                usuario.setPnombre_usu(faker.name().firstName());
                usuario.setSnombre_usu(random.nextBoolean() ? faker.name().firstName() : null);
                usuario.setAppaterno_usu(faker.name().lastName());
                usuario.setApmaterno_usu(faker.name().lastName());
                usuario.setCorreo_usu(faker.internet().emailAddress("usuario" + i));
                usuario.setTipo_rol_usu(faker.options().option("estudiante", "docente", "administrador"));
                usuario.setFec_registro_usu(faker.date().past(365, TimeUnit.DAYS));
                
                usuarioRepository.save(usuario);
            }
            System.out.println("Usuarios creados correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear usuarios: " + e.getMessage());
        }
    }
    
    private void crearCursos(Faker faker) {
        try {
            for (int i = 0; i < 10; i++) {
                Curso curso = new Curso();
                
                curso.setNom_curso(faker.educator().course().replaceAll("(?i)\\b(course|curso)\\b", "Curso"));
                curso.setDesc_curso(faker.lorem().sentence(8) + ".");
                curso.setCate_curso(faker.options().option("Programación", "Diseño", "Marketing", "Negocios", "Idiomas"));
                curso.setEstado_curso(faker.options().option("Activo", "Inactivo", "Próximamente"));
                curso.setFec_curso(faker.date().past(180, TimeUnit.DAYS));
                
                cursoRepository.save(curso);
            }
            System.out.println("Cursos creados correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear cursos: " + e.getMessage());
        }
    }
    
    private void crearPagos(Faker faker, Random random, List<Usuario> usuarios) {
        Set<Integer> usuariosConPago = new HashSet<>();
        
        try {
            for (int i = 0; i < 15; i++) {
                if (usuarios.isEmpty()) break;
                
                Usuario usuario = null;
                int intentos = 0;
                while (usuario == null && intentos < 30) {
                    Usuario candidato = usuarios.get(random.nextInt(usuarios.size()));
                    if (!usuariosConPago.contains(candidato.getId_usuario())) {
                        usuario = candidato;
                        usuariosConPago.add(usuario.getId_usuario());
                    }
                    intentos++;
                }
                
                if (usuario == null) {
                    System.out.println("No se encontraron más usuarios disponibles para pagos");
                    break;
                }
                
                try {
                    Pago pago = new Pago();
                    Date fechaPago = faker.date().past(90, TimeUnit.DAYS);
                    Date fechaProximoPago = new Date(fechaPago.getTime() + 30 * 24 * 60 * 60 * 1000L);
                    
                    pago.setUsuario(usuario);
                    pago.setFecha_pago(fechaPago);
                    pago.setFecha_prox_pago(fechaProximoPago);
                    pago.setStatus_pago(faker.options().option("Pagado", "Pendiente", "Rechazado", "Procesando"));
                    
                    pagoRepository.save(pago);
                } catch (DataIntegrityViolationException e) {
                    System.out.println("Error al crear pago para usuario " + usuario.getId_usuario() + ": " + e.getMessage());
                }
            }
            System.out.println("Pagos creados correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear pagos: " + e.getMessage());
        }
    }
    
    private void crearEvaluaciones(Faker faker, Random random, List<Usuario> usuarios, List<Curso> cursos) {
        try {
            if (cursos.isEmpty()) {
                System.out.println("No hay cursos disponibles para crear evaluaciones");
                return;
            }
            
            for (int i = 0; i < 25; i++) {
                if (usuarios.isEmpty() || cursos.isEmpty()) break;
                
                Evaluacion evaluacion = new Evaluacion();
                
                Usuario usuario = usuarios.get(random.nextInt(usuarios.size()));
                Curso curso = cursos.get(random.nextInt(cursos.size()));
                
                evaluacion.setTitulo_eva(faker.book().title());
                evaluacion.setDesc_eva(faker.lorem().sentence(10));
                evaluacion.setStatus_eva(faker.options().option("Pendiente", "Completada", "En revisión", "Aprobada", "Reprobada"));
                evaluacion.setUsuario(usuario);
                evaluacion.setCurso(curso);
                evaluacion.setFecha_evaluacion(faker.date().past(60, TimeUnit.DAYS));
                
                try {
                    evaluacionRepository.save(evaluacion);
                } catch (DataIntegrityViolationException e) {
                    System.out.println("Error al crear evaluación #" + (i+1) + ": " + e.getMessage());
                }
            }
            System.out.println("Evaluaciones creadas correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear evaluaciones: " + e.getMessage());
        }
    }
}