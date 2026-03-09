/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  WOPS
 * Created: 10 feb 2026
 */

create table app_user (
  id bigserial primary key,
  username varchar(50) not null unique,
  password_hash varchar(255) not null,
  role varchar(30) not null,
  active boolean not null default true,
  created_at timestamp not null default now()
);

create table ciudadano (
  id bigserial primary key,
  clave_consecutiva bigint not null unique,
  nombre varchar(80) not null,
  apellido_paterno varchar(80) not null,
  apellido_materno varchar(80) not null,
  curp char(18) not null unique,
  fecha_nacimiento date not null,
  telefono varchar(20),
  correo varchar(120),
  domicilio text not null,
  activo boolean not null default true,
  created_at timestamp not null default now()
);

create index idx_ciudadano_curp on ciudadano(curp);