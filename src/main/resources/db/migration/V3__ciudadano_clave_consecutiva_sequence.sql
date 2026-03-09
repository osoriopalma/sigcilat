/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  WOPS
 * Created: 10 feb 2026
 */

-- 1) Crear secuencia (si no existe)
CREATE SEQUENCE IF NOT EXISTS ciudadano_clave_consecutiva_seq;

-- 2) Poner valor por defecto a la columna usando la secuencia
ALTER TABLE ciudadano
ALTER COLUMN clave_consecutiva
SET DEFAULT nextval('ciudadano_clave_consecutiva_seq');

-- 3) Asegurar que la secuencia quede "dueña" de la columna (opcional pero recomendado)
ALTER SEQUENCE ciudadano_clave_consecutiva_seq OWNED BY ciudadano.clave_consecutiva;

-- 4) Si ya existen filas con NULL, asignarles consecutivo
UPDATE ciudadano
SET clave_consecutiva = nextval('ciudadano_clave_consecutiva_seq')
WHERE clave_consecutiva IS NULL;
