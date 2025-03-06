INSERT INTO bdgym.tipoEntrenamiento (tipo) VALUES
('body_building'),
('cross_training');

INSERT INTO tipoejerciciocrosstraining (tipo) VALUES 
('fuerza/resistencia'),
('capacidad aeróbica'),
('velocidad/potencia'),
('estabilidad'),
('movilidad');

INSERT INTO tipoejerciciobodybuilding (tipo) VALUES 
('pierna'),
('hombro'),
('pecho'),
('espalda'),
('biceps'),
('triceps');

INSERT INTO trol (rol) VALUES 
('admin'),
('cliente'),
('entrenador');


INSERT INTO bdgym.ejercicio (nombre, descripcion, video, tipoentrenamiento_id, tipoejerciciobodybuilding_id, tipoejerciciocrosstraining_id)
VALUES
('Flexiones de Brazos', 'Ejercicio para pecho, tríceps y hombros.', 'https://www.youtube.com/watch?v=KEFQyLkDYtI', 1, 3, NULL),
('Sentadillas', 'Ejercicio para piernas.', 'https://www.youtube.com/watch?v=R2dMsNhN3DE', 1, 1, NULL),
('Press de Banca', 'Ejercicio para pectorales.', 'https://youtu.be/tuwHzzPdaGc', 1, 3, NULL),
('Peso Muerto', 'Ejercicio para espalda baja y piernas.', 'https://youtu.be/wjsu6ceEkAQ', 1, 4, NULL),
('Remo con Barra', 'Ejercicio para espalda y bíceps.', 'https://www.youtube.com/watch?v=R2dMsNhN3DE', 1, 4, NULL),
('Press Militar', 'Ejercicio para hombros.', 'https://www.youtube.com/watch?v=j7ULT6dznNc', 1, 2, NULL),
('Dominadas', 'Ejercicio para espalda y brazos.', 'https://www.youtube.com/watch?v=5oxviYmdHCY', 1, 4, NULL),
('Fondos en Paralelas', 'Ejercicio para tríceps y pecho.', 'https://www.youtube.com/watch?v=FG1ENBFsdHU', 1, 3, NULL),
('Curl de Bíceps', 'Ejercicio para bíceps.', 'https://www.youtube.com/watch?v=UeleXjsE-98', 1, 5, NULL),
('Elevaciones Laterales', 'Ejercicio para hombros.', 'https://www.youtube.com/watch?v=Fv-eAW1uKDI', 1, 2, NULL),
('Curl de Bíceps con Mancuernas', 'Ejercicio para bíceps.', 'https://www.youtube.com/watch?v=UeleXjsE-98', 1, 5, NULL),
('Sentadillas Frontales', 'Ejercicio para piernas.', 'https://www.youtube.com/watch?v=R2dMsNhN3DE', 1, 1, NULL),
('Prensa de Piernas', 'Ejercicio para piernas.', 'https://www.youtube.com/watch?v=sEM_zo9w2ss', 1, 1, NULL),
('Flexión de alcance alternante', 'Ejercicio para pecho, tríceps y hombros.', 'https://www.youtube.com/watch?v=uGNvdM0vEYs', 1, 3, NULL),
('Zancadas', 'Ejercicio para piernas.', 'https://youtu.be/Ix9QZ3Pnneo', 1, 1, NULL),
('Press de Hombros con Mancuernas', 'Ejercicio para hombros.', 'https://www.youtube.com/watch?v=FRxZ6wr5bpA', 1, 2, NULL),
('Patada de Tríceps con Mancuerna', 'Ejercicio para tríceps.', 'https://www.youtube.com/watch?v=BL9CzOQZDrs', 1, 6, NULL),
('Extensión de Tríceps con Polea Alta', 'Ejercicio para tríceps.', 'https://www.youtube.com/watch?v=mpZ9VRisAyw', 1, 6, NULL),
('Peso Muerto Rumano', 'Ejercicio para espalda baja y piernas.', 'https://www.youtube.com/watch?v=CkrqLaDGvOA', 1, 4, NULL);

INSERT INTO bdgym.usuario (nombre, apellidos, fecha_nacimiento, dni, genero, correo, telefono, fecha_ingreso, nombre_usuario, contraseña, validado, tipoentrenamiento_id, trol_id) VALUES
('Juan', 'Pérez González', '1990-05-15', '12345678A', 'Masculino', 'juan@example.com', '123456789', '2023-01-10', 'juanito', 'contraseña123', 1, 1, 1),
('Pedro', 'García Sánchez', '1978-03-12', '56789012C', 'Masculino', 'pedro@example.com', '789012345', '2024-02-28', 'pedrogar', 'qwerty456', 1, 1, 1),
('Dani', 'Ruiz Fernández', '1997-12-25', '90148356D', 'Masculino', 'dani@example.com', '234345890', '2023-07-15', 'danirui', 'pasaaa', 1, 2, 3);

