CREATE DATABASE instituto;

USE instituto;

CREATE TABLE estudiante (
	legajo INT AUTO_INCREMENT PRIMARY KEY,
	dni INT NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	fechaNacimiento DATE NOT NULL
) AUTO_INCREMENT=1000;

CREATE TABLE materia (
	codigo INT AUTO_INCREMENT PRIMARY KEY,
	nombreMateria VARCHAR(100) NOT NULL
);

INSERT INTO estudiante (dni, nombre, apellido, fechaNacimiento) VALUES
	('30111222', 'Juan', 'Pérez', '2000-05-12'),
	('30222333', 'María', 'González', '1999-11-23'),
	('30333444', 'Lucas', 'Fernández', '2001-03-15'),
	('30444555', 'Ana', 'Martínez', '2000-07-01'),
	('30555666', 'Pedro', 'López', '1998-12-09'),
	('30666777', 'Carla', 'Díaz', '2002-01-20'),
	('30777888', 'Sofía', 'Ramírez', '1999-08-30'),
	('30888999', 'Martín', 'Sánchez', '2001-11-11'),
	('30999000', 'Julieta', 'Torres', '2000-02-05'),
	('31000111', 'Diego', 'Morales', '1998-09-25');

INSERT INTO materia (nombreMateria) VALUES
	('Programación I'),
	('Análisis Matemático'),
	('Estructura y Bases de Datos'),
	('Laboratorio de Informática'),
	('Paradigmas de Programación');