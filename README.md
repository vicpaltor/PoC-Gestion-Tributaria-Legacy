# PoC - Migración Gestión Tributaria (Legacy a Java)

Prueba de Concepto (PoC) diseñada para simular la arquitectura de un sistema de **Gestión Tributaria** en proceso de migración de **Oracle Forms** a una arquitectura **Java + PL/SQL**.

## 🎯 Objetivo del Proyecto

Replicar el ciclo de vida de un framework propietario (similar a *Morphis*) y demostrar la delegación de lógica de negocio compleja en base de datos para optimizar el rendimiento y mantener la integridad transaccional.

## 🛠️ Tecnologías

*   **Base de Datos:** Oracle Database 21c (XE) sobre Docker.
*   **Backend Logic:** PL/SQL (Stored Procedures, Functions, Triggers, Sequences).
*   **App:** Java 17 (Simulación de Framework Legacy).
*   **Build Tool:** Maven.

## 🏗️ Arquitectura

El sistema implementa un patrón híbrido donde Java actúa como orquestador de eventos y Oracle gestiona la lógica de negocio:

1.  **Java (Simulación Forms):** Implementa clases propias (`NString`) y un ciclo de vida de eventos (`Pre-Query`, `Execute`, `Post-Query`).
2.  **PL/SQL (Lógica Core):**
    *   Cálculo de valoraciones catastrales.
    *   Generación automática de notificaciones en la misma transacción.

## 🚀 Cómo ejecutar el proyecto

### 1. Base de Datos (Oracle)

Ejecutar los scripts de la carpeta `/database` en orden:

1.  `01_setup_users.sql`: Creación del esquema `GESTION_TRIBUTARIA` en una PDB.
2.  `02_schema_tables.sql`: Creación de tablas (`INMUEBLES`, `NOTIFICACIONES`) y secuencias.
3.  `03_procedures_logic.sql`: Despliegue de los Procedimientos Almacenados.

### 2. Aplicación Java

El proyecto es una aplicación de consola Spring Boot (simulada).

```bash
cd app-legacy
mvn clean install
mvn spring-boot:run
```

### 📋 Flujo de Prueba

La aplicación simulará la valoración del Inmueble ID 101:

1. **Java:** Valida el ID (Pre-Query).
2. **Java:** Invoca al Procedimiento `PROCESO_COMPLETO_VALORACION`.
3. **Oracle:** Calcula el valor según zona y metros.
4. **Oracle:** Inserta una notificación automática.
5. **Java:** Recupera y muestra los datos actualizados (Post-Query).

---
*Proyecto realizado como ejercicio de preparación para arquitectura de migración de sistemas legacy.*
