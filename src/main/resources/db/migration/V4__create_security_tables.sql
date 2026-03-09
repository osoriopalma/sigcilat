/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  WOPS
 * Created: 11 feb 2026
 */

create table if not exists usuario (
  id bigserial primary key,
  username varchar(100) not null unique,
  password varchar(255) not null,
  enabled boolean not null default true
);

create table if not exists rol (
  id bigserial primary key,
  nombre varchar(50) not null unique
);

create table if not exists usuario_rol (
  usuario_id bigint not null references usuario(id) on delete cascade,
  rol_id bigint not null references rol(id) on delete cascade,
  primary key (usuario_id, rol_id)
);

-- roles base
insert into rol(nombre) values ('ROLE_ADMIN') on conflict (nombre) do nothing;
insert into rol(nombre) values ('ROLE_CAPTURISTA') on conflict (nombre) do nothing;
insert into rol(nombre) values ('ROLE_CONSULTA') on conflict (nombre) do nothing;
