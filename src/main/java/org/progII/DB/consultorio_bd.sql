CREATE DATABASE consultorio_db;

USE consultorio_db;

CREATE TABLE consultorio (
	nro_consultorio INT PRIMARY KEY,
    medico VARCHAR(100) NOT NULL
    );
    
CREATE TABLE paciente (
	nro_paciente INT AUTO_INCREMENT PRIMARY KEY,
    telefono INT,
    nombre VARCHAR(100) NOT NULL
    );
    
CREATE TABLE turno (
	id INT AUTO_INCREMENT PRIMARY KEY,
    dia DATE NOT NULL,
    hora TIME NOT NULL,
    nro_consultorio INT,
    nro_paciente INT,
    
    FOREIGN KEY (nro_consultorio) REFERENCES consultorio(nro_consultorio),
    FOREIGN KEY (nro_paciente) REFERENCES paciente(nro_paciente)
);

