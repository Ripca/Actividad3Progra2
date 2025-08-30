DROP DATABASE IF EXISTS db_escuela;

CREATE DATABASE db_escuela;
USE db_escuela;

-- Crear tabla Profesion
CREATE TABLE Profesion (
    id_profesion INT PRIMARY KEY AUTO_INCREMENT,
    profesion VARCHAR(100) NOT NULL
);

-- Crear tabla Estudiantes
CREATE TABLE Estudiante (
    id_estudiante INT PRIMARY KEY AUTO_INCREMENT,
    carnet VARCHAR(50) UNIQUE NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(15),
    fecha_nacimiento VARCHAR(20) NOT NULL,  -- Fecha como string
    genero BOOLEAN
);

-- Crear tabla Docente
CREATE TABLE Docente (
    id_docente INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(15),
    fecha_nacimiento VARCHAR(20) NOT NULL,  -- Fecha como string
    genero BOOLEAN,
    salario DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    id_profesion INT NOT NULL,
    FOREIGN KEY (id_profesion) REFERENCES Profession(id_profesion)
);



