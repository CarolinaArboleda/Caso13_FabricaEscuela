-- PostgreSQL DDL script generated from the provided ER model

DROP TABLE IF EXISTS detalle_tareas CASCADE;
DROP TABLE IF EXISTS tareas CASCADE;
DROP TABLE IF EXISTS usuarios CASCADE;
DROP TABLE IF EXISTS grupos CASCADE;
DROP TABLE IF EXISTS estados CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE roles (
    id_rol integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_rol varchar(255) NOT NULL
);

CREATE TABLE grupos (
    id_grupo integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_grupo varchar(255) NOT NULL,
    fecha_creacion date NOT NULL
);

CREATE TABLE estados (
    id_estado integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_estado varchar(255) NOT NULL,
    fecha_actualizacion date NOT NULL
);

CREATE TABLE usuarios (
    id_usuario integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    apellido varchar(255) NOT NULL,
    alias varchar(255),
    email varchar(255) NOT NULL,
    fecha_registro date NOT NULL,
    id_rol integer NOT NULL,
    id_grupo integer,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES roles (id_rol),
    CONSTRAINT fk_usuario_grupo FOREIGN KEY (id_grupo) REFERENCES grupos (id_grupo)
);

CREATE TABLE tareas (
    id_tarea integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_tarea varchar(255) NOT NULL,
    fecha_creacion date NOT NULL,
    fecha_limite date NOT NULL,
    id_estado integer NOT NULL,
    id_usuario integer NULL,
    id_grupo integer NOT NULL,
    CONSTRAINT fk_tarea_estado FOREIGN KEY (id_estado) REFERENCES estados (id_estado),
    CONSTRAINT fk_tarea_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario),
    CONSTRAINT fk_tarea_grupo FOREIGN KEY (id_grupo) REFERENCES grupos (id_grupo)
);

CREATE TABLE detalle_tareas (
    id_detalle integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_tarea integer NOT NULL,
    descripcion text,
    observacion text,
    fecha_actualizacion date,
    CONSTRAINT fk_detalle_tarea FOREIGN KEY (id_tarea) REFERENCES tareas (id_tarea)
);
