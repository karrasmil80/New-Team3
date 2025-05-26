
-- Tabla de EQUIPOS
CREATE TABLE IF NOT EXISTS equipo (
    nombre_equipo VARCHAR(200) NOT NULL PRIMARY KEY, -- Nombre único del equipo (clave primaria)
    escudo_equipo VARCHAR(200) DEFAULT 'images/default_profile.png' -- Ruta al escudo del equipo
);

-- Tabla base: PERSONAS
CREATE TABLE IF NOT EXISTS persona (
    id IDENTITY NOT NULL PRIMARY KEY, -- ID autoincremental (PK)
    nombre VARCHAR(100) NOT NULL, -- Nombre
    apellido VARCHAR(150) NOT NULL, -- Apellido
    fechaNacimiento DATE NOT NULL, -- Fecha de nacimiento
    fechaIncorporacion DATE NOT NULL, -- Fecha de ingreso al club
    salario NUMERIC NOT NULL, -- Salario actual
    pais VARCHAR(100) NOT NULL, -- Nacionalidad
    rol VARCHAR(50) NOT NULL CHECK (rol IN ('jugador', 'entrenador')), -- Rol: jugador o entrenador (restricción)
    equipo VARCHAR(200) NOT NULL, -- Nombre del equipo (FK)
    imagen VARCHAR(255) DEFAULT 'images/default_profile.png', -- Imagen de perfil
    CONSTRAINT fk_equipo_personal FOREIGN KEY (equipo) REFERENCES equipo(nombre_equipo) -- FK a la tabla equipo
);

-- Tabla JUGADOR (subtipo de persona)
CREATE TABLE IF NOT EXISTS jugador (
    id INT NOT NULL PRIMARY KEY, -- ID que debe coincidir con persona.id
    posicion VARCHAR(50) NOT NULL, -- Posición en el campo (Portero, Defensa, etc.)
    dorsal INTEGER NOT NULL, -- Número de camiseta
    altura DOUBLE PRECISION NOT NULL, -- Altura en metros
    peso DOUBLE PRECISION NOT NULL, -- Peso en kilogramos
    goles INTEGER NOT NULL, -- Total de goles anotados
    partidosJugados INTEGER NOT NULL, -- Partidos jugados
    FOREIGN KEY (id) REFERENCES persona(id) -- FK: hereda de persona
);

-- Tabla ENTRENADOR (subtipo de persona)
CREATE TABLE IF NOT EXISTS entrenador (
    id INT NOT NULL PRIMARY KEY, -- ID que debe coincidir con persona.id
    especialidad VARCHAR(100) NOT NULL, -- Ej. Táctico, físico, porteros, etc.
    equipo VARCHAR(200), -- Equipo actual (opcional)
    FOREIGN KEY (id) REFERENCES persona(id) -- FK: hereda de persona
);

-- Tabla de CONVOCATORIAS
CREATE TABLE IF NOT EXISTS convocatoria (
    id IDENTITY PRIMARY KEY, -- ID autoincremental (clave primaria)
    jornada TIMESTAMP NOT NULL, -- Fecha y hora de la jornada/partido
    descripcion VARCHAR(255), -- Descripción libre de la convocatoria
);

-- Tabla intermedia: JUGADORES CONVOCADOS
CREATE TABLE IF NOT EXISTS convocatoria_jugador (
    id IDENTITY PRIMARY KEY, -- ID autoincremental
    convocatoria_id INT NOT NULL, -- FK a la convocatoria
    jugador_id INT NOT NULL, -- FK al jugador convocado
    es_titular BOOLEAN DEFAULT FALSE, -- Marca si el jugador es titular
    CONSTRAINT fk_convocatoria_cj FOREIGN KEY (convocatoria_id) REFERENCES convocatoria(id), -- FK -> convocatoria
    CONSTRAINT fk_jugador_cj FOREIGN KEY (jugador_id) REFERENCES jugador(id), -- FK -> jugador
    CONSTRAINT uq_convocatoria_jugador UNIQUE (convocatoria_id, jugador_id) -- Evita duplicar un jugador en la misma convocatoria
    );
