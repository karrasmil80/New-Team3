INSERT INTO equipo (nombre_equipo)
VALUES ('Muppet');

INSERT INTO equipo (nombre_equipo)
VALUES ('New Team');

-- Jugadores
INSERT INTO persona (
    id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, pais,
    rol, equipo
) VALUES
      (37, 'Daisuke', 'Furukawa', '1971-11-12', '1983-04-01', 34100.0, 'Japón', 'jugador', 'Muppet'),
      (38, 'Koichiro', 'Kazami', '1971-05-08', '1983-04-01', 33600.0, 'Japón', 'jugador', 'Muppet');

-- Entrenadores
INSERT INTO persona (
    id, nombre, apellido, fechaNacimiento, fechaIncorporacion, salario, pais,
    rol, equipo
) VALUES
      (39, 'Kozo', 'Kira', '1952-09-18', '1983-04-01', 53000.0, 'Japón', 'entrenador', 'Muppet'),
      (40, 'Jeff', 'Turner', '1950-09-08', '1983-04-01', 46000.0, 'Japón', 'entrenador', 'Muppet');
