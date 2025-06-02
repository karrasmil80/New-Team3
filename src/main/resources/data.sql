-- Usuarios del sistema
INSERT INTO usuario (id, nombre, password) VALUES
(1, 'admin', 'admin123');

-- Equipo MUPPET
INSERT INTO equipo (nombre_equipo)
VALUES ('MUPPET');

-- Equipo New Team
INSERT INTO equipo (nombre_equipo)
VALUES ('NEW_TEAM');

-- Jugadores
INSERT INTO persona (
    id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, pais,
    rol, equipo
) VALUES
      (37, 'Daisuke', 'Furukawa', '1971-11-12', '1983-04-01', 34100.0, 'Japón', 'jugador', 'MUPPET'),
      (38, 'Koichiro', 'Kazami', '1971-05-08', '1983-04-01', 33600.0, 'Japón', 'jugador', 'MUPPET');

-- Entrenadores
INSERT INTO persona (
    id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, pais,
    rol, equipo
) VALUES
      (39, 'Kozo', 'Kira', '1952-09-18', '1983-04-01', 53000.0, 'Japón', 'entrenador', 'MUPPET'),
      (40, 'Jeff', 'Turner', '1950-09-08', '1983-04-01', 46000.0, 'Japón', 'entrenador', 'MUPPET');

-- Convocatorias
INSERT INTO convocatoria (id, jornada) VALUES
      (1, '2025-08-10 15:30:00'),
      (2, '2025-08-17 17:00:00'),
      (3, '2025-08-24 16:00:00');