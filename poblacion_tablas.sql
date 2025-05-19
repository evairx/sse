-- Inserciones en la tabla 'usuario'
INSERT INTO usuario (id_usuario, apmaterno_usu, appaterno_usu, correo_usu, fec_registro_usu, pnombre_usu, snombre_usu, tipo_rol_usu)
VALUES 
(1, 'Gómez', 'Pérez', 'juan.perez@example.com', '2024-01-15', 'Juan', 'Carlos', 'estudiante'),
(2, 'López', 'Martínez', 'ana.lopez@example.com', '2024-02-10', 'Ana', 'María', 'estudiante'),
(3, 'Ramírez', 'Sánchez', 'mario.ramirez@example.com', '2024-03-05', 'Mario', 'Luis', 'docente');

-- Inserciones en la tabla 'curso'
INSERT INTO curso (id_curso, cate_curso, desc_curso, estado_curso, fec_curso, nom_curso)
VALUES
(1, 'Programación', 'Curso de introducción a la programación en Python', 'activo', '2024-05-01', 'Python Básico'),
(2, 'Diseño', 'Curso de fundamentos de diseño gráfico', 'activo', '2024-06-01', 'Diseño Gráfico 101'),
(3, 'Marketing', 'Curso de marketing digital para redes sociales', 'inactivo', '2024-04-01', 'Marketing Digital');

-- Inserciones en la tabla 'evaluacion'
INSERT INTO evaluacion (id_eva, desc_eva, fecha_evaluacion, status_eva, titulo_eva, id_curso, id_usuario)
VALUES
(1, 'Evaluación inicial del curso de Python', '2024-05-10', 'completada', 'Evaluación 1 - Python', 1, 1),
(2, 'Evaluación de mitad de curso de Diseño Gráfico', '2024-06-15', 'pendiente', 'Evaluación Parcial - Diseño', 2, 2),
(3, 'Evaluación final del curso de Marketing Digital', '2024-04-25', 'completada', 'Evaluación Final - Marketing', 3, 1);

-- Inserciones en la tabla 'pago'
INSERT INTO pago (id_pago, fecha_pago, fecha_prox_pago, status_pago, id_usuario)
VALUES
(1, '2024-01-15', '2024-02-15', 'pagado', 1),
(2, '2024-02-10', '2024-03-10', 'pendiente', 2),
(3, '2024-03-05', '2024-04-05', 'pagado', 3);