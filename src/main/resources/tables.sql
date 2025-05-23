CREATE TABLE IF NOT EXISTS plantilla (
    id IDENTITY NOT NULL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    fechaIncorporacion DATE NOT NULL,
    salario NUMERIC NOT NULL,
    pais VARCHAR(100) NOT NULL,
    rol VARCHAR(50) NOT NULL CHECK (rol IN ('jugador', 'entrenador')),
    equipo VARCHAR(200) NOT NULL
    ruta_imagen VARCHAR(255) DEFAULT 'images/default_profile.png'
    CONSTRAINT fk_equipo_personal FOREIGN KEY (equipo) REFERENCES equipo(nombre_equipo)
);

CREATE TABLE IF NOT EXISTS jugador (
    id INT NOT NULL PRIMARY KEY,
    posicion VARCHAR(50) NOT NULL,
    dorsal INTEGER NOT NULL,
    altura DOUBLE PRECISION NOT NULL,
    peso DOUBLE PRECISION NOT NULL,
    goles INTEGER NOT NULL,
    partidosJugados INTEGER NOT NULL,
    FOREIGN KEY (id) REFERENCES plantilla(id)
);

CREATE TABLE IF NOT EXISTS entrenador (
    id INT NOT NULL PRIMARY KEY,
    especialidad VARCHAR(100) NOT NULL,
    equipo VARCHAR(200),
    FOREIGN KEY (id) REFERENCES plantilla(id)
);

CREATE TABLE IF NOT EXISTS equipo (
    nombre_equipo VARCHAR(200) NOT NULL PRIMARY KEY,
    escudo_equipo VARCHAR(200) DEFAULT 'images/default_profile.png'
);
