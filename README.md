<div align="center">

# 🏘️ SIGCILAT

### Sistema de Gestión para la Comunidad Indígena de Loma Alta Taxhimay

**Una solución de software orientada a la organización, administración y gestión de procesos comunitarios.**

<br>

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge\&logo=springboot\&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge\&logo=postgresql\&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge\&logo=apachemaven\&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge\&logo=git\&logoColor=white)

<br>

**🚧 Proyecto en desarrollo**

</div>

---

## 📖 Sobre el proyecto

**SIGCILAT** es un sistema de gestión desarrollado para la **Comunidad Indígena de Loma Alta Taxhimay**, ubicada en el municipio de Villa del Carbón, Estado de México.

El proyecto surge de la necesidad de contar con una herramienta tecnológica que permita apoyar la organización y administración de diferentes procesos comunitarios.

La intención es centralizar progresivamente la información y facilitar el seguimiento de actividades que actualmente pueden realizarse mediante procesos manuales.

---

## 🎯 Objetivo

Desarrollar una aplicación que permita administrar de manera organizada diferentes procesos de la comunidad, proporcionando una base tecnológica para el manejo de información y el seguimiento de actividades.

Entre los procesos contemplados se encuentran:

* 👥 Gestión de ciudadanos
* 🛠️ Gestión y seguimiento de faenas
* 💰 Registro de cooperaciones
* 📋 Administración de información comunitaria
* 📊 Consulta y seguimiento de información
* 🗂️ Organización de procesos administrativos

> El sistema se encuentra en desarrollo y las funcionalidades se incorporan progresivamente.

---

# 🏗️ Arquitectura

SIGCILAT está siendo desarrollado con una arquitectura orientada a separar las responsabilidades principales de la aplicación.

```text
┌──────────────────────────────┐
│          Frontend            │
│      Interfaz de usuario     │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│           Backend            │
│       Java / Spring Boot     │
├──────────────────────────────┤
│ Controllers                  │
│ Services                     │
│ Repositories                 │
│ Entities                     │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│          PostgreSQL          │
│       Base de datos SQL      │
└──────────────────────────────┘
```

La estructura se irá ampliando conforme avance el desarrollo del sistema.

---

# 🛠️ Tecnologías

### Backend

| Tecnología     | Uso                                 |
| -------------- | ----------------------------------- |
| ☕ Java         | Lenguaje principal                  |
| 🍃 Spring Boot | Desarrollo del backend              |
| 📦 Maven       | Gestión del proyecto y dependencias |
| 🗄️ PostgreSQL | Base de datos relacional            |

### Herramientas

| Herramienta | Uso                                                  |
| ----------- | ---------------------------------------------------- |
| Git         | Control de versiones                                 |
| GitHub      | Repositorio y colaboración                           |
| Docker      | Entorno de desarrollo y servicios                    |
| Flyway      | Control y versionado de migraciones de base de datos |

---

# 📂 Estructura del proyecto

La estructura principal sigue la organización habitual de una aplicación Spring Boot:

```text
sigcilat/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── mx/
│   │   │       └── com/
│   │   │           └── alfirk ti/
│   │   │               └── sigcilat/
│   │   │
│   │   └── resources/
│   │
│   └── test/
│
├── pom.xml
├── README.md
└── ...
```

> La estructura puede cambiar conforme se incorporen nuevos módulos y componentes.

---

# 🧩 Módulos contemplados

El sistema está siendo diseñado de manera modular para permitir su crecimiento progresivo.

### 👥 Ciudadanos

Gestión de información relacionada con los ciudadanos de la comunidad.

### 🛠️ Faenas

Registro y seguimiento del cumplimiento de faenas comunitarias.

El modelo contempla diferentes formas en las que una persona puede cumplir con una faena, de acuerdo con las reglas definidas para la comunidad.

### 💰 Cooperaciones

Registro y seguimiento de aportaciones económicas relacionadas con actividades o proyectos comunitarios.

### 📋 Administración

Herramientas destinadas a facilitar la organización y consulta de información administrativa.

---

# 🌱 Estado del desarrollo

SIGCILAT se encuentra actualmente en **fase de desarrollo**.

El proyecto está siendo construido de manera incremental, priorizando primero la estructura base del sistema y posteriormente la incorporación de los diferentes módulos funcionales.

### Progreso general

```text
Configuración del proyecto       ████████████████████  ✓
Estructura Backend               ███████████████░░░░░  En desarrollo
Base de datos                    ████████████░░░░░░░░  En desarrollo
Modelo de información            ██████████░░░░░░░░░░  En desarrollo
Módulo de ciudadanos             ███████░░░░░░░░░░░░░  Planeado
Módulo de faenas                 ███████░░░░░░░░░░░░░  En desarrollo
Módulo de cooperaciones          █████░░░░░░░░░░░░░░░  Planeado
Interfaz de usuario              ████░░░░░░░░░░░░░░░░  Planeado
```

> Este indicador es únicamente una referencia visual del estado del proyecto y se actualizará conforme avance el desarrollo.

---

# 🚀 Ejecución del proyecto

## Requisitos

Para ejecutar el proyecto localmente se requiere contar con:

* ☕ Java JDK
* 📦 Maven
* 🐘 PostgreSQL
* 🐳 Docker *(opcional, dependiendo del entorno)*
* 🔧 Git

### Clonar el repositorio

```bash
git clone https://github.com/osoriopalma/sigcilat.git
```

Entrar al proyecto:

```bash
cd sigcilat
```

### Ejecutar con Maven

En Windows:

```bash
mvnw.cmd spring-boot:run
```

En sistemas Unix/Linux:

```bash
./mvnw spring-boot:run
```

> La configuración de la base de datos y demás servicios se encuentra en proceso de definición conforme evoluciona el proyecto.

---

# 🌿 Control de versiones

El desarrollo utiliza **Git y GitHub** para llevar el control de versiones.

Actualmente se trabaja con diferentes ramas para separar el desarrollo de la versión principal.

```text
main
 │
 └── staging
       │
       └── desarrollo de nuevas funcionalidades
```

La rama `main` representa la versión principal del proyecto, mientras que `staging` se utiliza como espacio de integración y pruebas durante el desarrollo.

---

# 🔐 Desarrollo y buenas prácticas

Durante el desarrollo se busca aplicar progresivamente buenas prácticas relacionadas con:

* Separación de responsabilidades
* Organización por capas
* Control de versiones
* Migraciones de base de datos
* Validación de información
* Manejo de configuración
* Código mantenible
* Documentación técnica

Estas prácticas se irán incorporando y refinando conforme avance el proyecto.

---

# 🗺️ Roadmap

### Fase 1 — Base del proyecto

* [x] Crear proyecto Spring Boot
* [x] Configurar Maven
* [x] Crear estructura inicial
* [x] Configurar repositorio Git
* [x] Configurar ramas de desarrollo
* [ ] Consolidar configuración de base de datos

### Fase 2 — Backend

* [ ] Definir entidades
* [ ] Crear repositorios
* [ ] Implementar servicios
* [ ] Implementar controladores
* [ ] Validaciones
* [ ] Manejo de errores

### Fase 3 — Módulos

* [ ] Ciudadanos
* [ ] Faenas
* [ ] Cooperaciones
* [ ] Procesos administrativos

### Fase 4 — Interfaz

* [ ] Diseño de interfaz
* [ ] Formularios
* [ ] Consultas
* [ ] Integración con backend

### Fase 5 — Pruebas y despliegue

* [ ] Pruebas
* [ ] Configuración de entorno
* [ ] Contenedores
* [ ] Despliegue
* [ ] Documentación final

---

# 👨‍💻 Autor

<div align="center">

### Salvador Osorio

**Full Stack Developer Junior**

Java · Spring Boot · JavaScript · SQL

[![GitHub](https://img.shields.io/badge/GitHub-osoriopalma-181717?style=for-the-badge\&logo=github\&logoColor=white)](https://github.com/osoriopalma)

</div>

---

<div align="center">

### 🏘️ SIGCILAT

**Tecnología al servicio de la organización comunitaria.**

🚧 *Proyecto en desarrollo*

</div>
