# POO - Clase 09: MVC, JDBC y Bases de Datos SQL

Este repositorio acompaña la presentación de la **Clase 09** sobre el
patrón arquitectónico **MVC**, el acceso a **bases de datos relacionales
mediante SQL** y la utilización de **JDBC** desde Java.

Los ejemplos integran los conceptos de Programación Orientada a Objetos
con una arquitectura separada en **Modelos, Vistas y Controladores**,
incorporando además una capa de acceso a datos mediante DAO y una clase
de conexión a la base de datos. La presentación destaca como objetivo la
separación de responsabilidades y el desacoplamiento entre interfaz,
lógica y datos.

------------------------------------------------------------------------

## Temas

|  | Tema | Paquete / recurso |
|---|------|---------|
| 1 | Patrón MVC | `controllers`, `models`, `views` |
| 2 | Modelo (Model) | `models.Modelo` |
| 3 | Vista (View) | `views.Vista` |
  4 | Controlador (Controller) | `controllers.Controlador` |
| 5 | MVC y múltiples vistas | Conceptos |
| 6 | Dato e información | Conceptos |
| 7 | Bases de datos relacionales | SGBD / herramienta gráfica |
| 8 | MySQL y MySQL Workbench | SQL |
| 9 | SQL | `java.sql` |
| 10 | JDBC | `java.sql.*` |
| 11 | Conexión a la base de datos | `connection.ConexionDB`|
| 12 | DAO (Data Access Object)  | `models`, `daos` |
| 13 | Modelo + DAO + JDBC | Diagramas de clases |
| 14 | Base de datos instituto | Ejemplo práctico |
| 15 | Script SQL | Descarga el archivo .sql |

              
  -------------------------------------------------------------------------------

------------------------------------------------------------------------

## Estructura del proyecto

La presentación propone una organización por responsabilidades,
separando modelos, vistas, controladores, conexión, excepciones y
utilidades. 

``` text
POO_Clase09_aux/
└── src/
    └── com/
        └── darkwhiskers/
            ├── Main.java                          ← punto de entrada
            ├── connection/
            │   └── ConexionDB.java                ← conexión JDBC
            ├── models/
            │   ├── Persona.java
            │   ├── Estudiante.java
            │   ├── Materia.java
            │   ├── GenericDAO.java                ← interfaz DAO
            │   ├── EstudianteDAO.java
            │   └── MateriaDAO.java
            ├── views/
            │   ├── Vista.java                     ← interfaz
            │   ├── VistaEstudiante.java
            │   ├── VistaMateria.java
            │   └── VistaPrincipal.java
            ├── controllers/
            │   ├── Controller.java                ← interfaz
            │   ├── ControladorEstudiante.java
            │   ├── ControladorMateria.java
            │   └── ControladorPrincipal.java
            ├── exceptions/
            │   ├── InstitutoException.java
            │   ├── FechaInvalidaException.java
            │   ├── DatosInvalidosException.java
            │   └── UsuarioDuplicadoException.java
            └── utils/
                └── Validador.java
```

------------------------------------------------------------------------

## DESCRIPCIÓN DE CADA MÓDULO

### 1 · Patrón MVC

**MVC (Model-View-Controller)** es un patrón arquitectónico que busca
separar las responsabilidades de una aplicación en tres componentes
principales. Su objetivo es lograr un código más manejable, extensible y
fácil de mantener.

La arquitectura se divide en:

  Componente       Responsabilidad
  ---------------- ---------------------------------------------------------
  **Model**        Representa los datos y la lógica relacionada con ellos.
  **View**         Presenta la información al usuario.
  **Controller**   Coordina la interacción entre la vista y el modelo.

------------------------------------------------------------------------

### 2 · Modelo (`Model`)

El modelo representa los datos de la aplicación y las reglas que
gobiernan cómo se modifican esos datos. Es independiente de la interfaz
de usuario.

En el ejemplo inicial de la presentación:

``` java
public class Alumno {

    private String nombre;
    private int nota;

    public Alumno(String nombre, int nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
```

El modelo no decide cómo se muestran los datos.

En el proyecto de la clase, los modelos principales son:

-   `Persona`
-   `Estudiante`
-   `Materia`

Además, los DAO se encargan de la persistencia de los datos.

------------------------------------------------------------------------

### 3 · Vista (`View`)

La vista se encarga de presentar la información y de interactuar con el
usuario. Puede implementarse mediante una interfaz gráfica, una página
web o incluso mediante la consola.

En el proyecto:

``` text
Vista.java
├── VistaEstudiante.java
├── VistaMateria.java
└── VistaPrincipal.java
```

La interfaz permite desacoplar al controlador de implementaciones
concretas de vistas.

``` java
public interface Vista {

    void mostrarAlumno(String nombre, int nota);
}
```

Las implementaciones concretas pueden ser:

``` java
public class AlumnoViewSimple implements Vista {
    ...
}

public class AlumnoViewTabla implements Vista {
    ...
}
```

De esta forma, el controlador puede trabajar contra la abstracción
`Vista` y no contra una implementación específica.

------------------------------------------------------------------------

### 4 · Controlador (`Controller`)

El controlador actúa como intermediario entre el modelo y la vista.
Recibe las acciones del usuario, decide qué operación realizar y
coordina la actualización correspondiente.


En el proyecto:

``` text
Controller.java
├── ControladorEstudiante.java
├── ControladorMateria.java
└── ControladorPrincipal.java
```

Un controlador puede mantener referencias a los objetos que necesita
para coordinar el flujo:

``` java
private EstudianteDAO dao;
private VistaEstudiante vista;
```

Esto representa asociaciones entre el controlador y sus colaboradores.

------------------------------------------------------------------------

### 5 · MVC y múltiples vistas

Un mismo modelo puede ser presentado mediante distintas vistas. La
presentación utiliza un ejemplo con dos vistas para un mismo objeto
`Alumno`: una vista simple y una vista en forma de tabla.


Una primera implementación del controlador depende directamente de las
clases concretas:

``` java
private AlumnoViewSimple vistaSimple;
private AlumnoViewTabla vistaTabla;
```

El problema aparece cuando se desea agregar una nueva vista: el
controlador debe modificarse.

La solución presentada consiste en utilizar una interfaz común:

``` java
public interface Vista {
    void mostrarAlumno(String nombre, int nota);
}
```

y mantener una colección:

``` java
private List<Vista> vistas;
```

Así pueden incorporarse nuevas vistas sin modificar el controlador
existente. La presentación relaciona esta solución con los principios
**DIP** y **OCP** de SOLID.

------------------------------------------------------------------------

### 6 · Dato e información

Un **dato** es una representación simbólica de un atributo o variable
que, por sí solo, no necesariamente posee significado suficiente.

La **información** surge cuando los datos son procesados y adquieren
significado.

``` text
DATOS
  │
  │ procesamiento
  ▼
INFORMACIÓN
```

------------------------------------------------------------------------

### 7 · Bases de datos relacionales

Una base de datos relacional organiza los datos en **tablas relacionadas
entre sí**.

Cada tabla está formada por:

-   **Filas** → registros.
-   **Columnas** → campos o atributos.

La presentación utiliza **MySQL** como ejemplo de SGBD y **MySQL
Workbench** como herramienta gráfica para trabajar con la base de datos.


------------------------------------------------------------------------

### 8 · MySQL y MySQL Workbench

**MySQL** es el sistema gestor de bases de datos que ejecuta las
instrucciones SQL y permite crear, almacenar, consultar y administrar
datos.

**MySQL Workbench** es una herramienta gráfica que permite:

-   Escribir consultas SQL.
-   Diseñar tablas.
-   Consultar datos.
-   Administrar la base de datos.
-   Interactuar visualmente con MySQL.

------------------------------------------------------------------------

### 9 · SQL

**SQL (Structured Query Language)** es un lenguaje estándar para
gestionar y manipular bases de datos relacionales. La presentación
diferencia principalmente:

-   **DDL (Data Definition Language)** → definición y modificación de
    estructuras.
-   **DML (Data Manipulation Language)** → manipulación y consulta de
    datos.

#### Operaciones CRUD

  | CRUD | Operación | SQL |
  |------|-----------|-----|
  | **C** | Create | `INSERT` |
  | **R** | Read | `SELECT` |
  | **U** | Update | `UPDATE` |
  | **D** | Delete | `DELETE` |

Ejemplos:

``` sql
INSERT INTO estudiante (dni, nombre, apellido, fechaNacimiento)
VALUES (?, ?, ?, ?);
```

``` sql
SELECT * FROM estudiante;
```

``` sql
UPDATE estudiante
SET nombre = ?
WHERE legajo = ?;
```

``` sql
DELETE FROM estudiante
WHERE legajo = ?;
```

La presentación identifica `SELECT` como consulta y `INSERT`, `UPDATE` y
`DELETE` como operaciones de modificación de datos.


------------------------------------------------------------------------

### 10 · JDBC

**JDBC (Java Database Connectivity)** es la API de Java que permite
conectarse y trabajar con bases de datos. Forma parte del paquete:

``` java
java.sql
```

JDBC define interfaces para establecer conexiones y ejecutar consultas o
actualizaciones. Para trabajar con un SGBD específico se necesita un
**driver JDBC**, que implementa dichas interfaces.


En el caso de MySQL se utiliza el **MySQL Connector/J**.

El flujo conceptual es:

``` text
Java
  │
  │ JDBC
  ▼
Driver JDBC
  │
  ▼
MySQL
  │
  ▼
Base de datos
```

------------------------------------------------------------------------

### 11 · Conexión a la base de datos

La clase:

``` text
connection/
└── ConexionDB.java
```

centraliza la obtención de conexiones JDBC.

El resto de la aplicación no debería encargarse directamente de
construir la conexión cada vez que necesita acceder a los datos.

Por ejemplo:

``` java
Connection conn = ConexionDB.getConnection();
```

Esto permite aislar el detalle técnico de la conexión.

> *"Conectar con la base de datos es un detalle técnico;*\
> *separar quién consulta, quién decide y quién muestra es verdadero
> diseño MVC."*

------------------------------------------------------------------------

### 12 · DAO (Data Access Object)

Los DAO encapsulan el acceso a los datos y aíslan las operaciones SQL
del resto de la aplicación.

La presentación incorpora:

``` text
GenericDAO<T, ID>
├── EstudianteDAO
└── MateriaDAO
```

La interfaz genérica define operaciones comunes:

``` java
public interface GenericDAO<T, ID> {

    void create(T t);

    List<T> findAll();

    T findById(ID id);

    void update(T t);

    void delete(ID id);
}
```

De esta forma, cada DAO concreto puede implementar las operaciones
correspondientes a su entidad.

Por ejemplo:

``` text
EstudianteDAO
    │
    ├── operaciones CRUD de Estudiante
    │
    └── findByDni(int dni)
```

y:

``` text
MateriaDAO
    │
    └── operaciones CRUD de Materia
```

------------------------------------------------------------------------

### 13 · Modelo + DAO + JDBC

La arquitectura del proyecto separa las responsabilidades:

``` text
┌──────────────────────┐
│     Controlador      │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│         DAO          │
│  EstudianteDAO       │
│  MateriaDAO          │
└──────────┬───────────┘
           │
           │ JDBC / SQL
           ▼
┌──────────────────────┐
│     ConexionDB       │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│       MySQL          │
│     Base de datos    │
└──────────────────────┘
```

El controlador coordina; el DAO realiza el acceso a datos; `ConexionDB`
centraliza la conexión; y MySQL almacena la información.

------------------------------------------------------------------------

### 14 · Base de datos `instituto`

El ejemplo de la presentación utiliza una base de datos llamada:

``` text
instituto
```

con las tablas:

``` text
estudiante
materia
```

La tabla `estudiante` contiene:

| Campo            | Tipo         | Restricción                    |
|------------------|--------------|--------------------------------|
| `legajo`         | `INT`        | `PRIMARY KEY`, `AUTO_INCREMENT` |
| `dni`            | `INT`        | `NOT NULL`                     |
| `nombre`         | `VARCHAR(50)`| `NOT NULL`                     |
| `apellido`       | `VARCHAR(50)`| `NOT NULL`                     |
| `fechaNacimiento`| `DATE`       | `NOT NULL`                     |

La tabla `materia` contiene:

| Campo           | Tipo          | Restricción                    |
|-----------------|---------------|--------------------------------|
| `codigo`        | `INT`         | `PRIMARY KEY`, `AUTO_INCREMENT` |
| `nombreMateria` | `VARCHAR(100)`| `NOT NULL`                     |



------------------------------------------------------------------------

### 15 · Script SQL

Se incluye en este repositorio un archivo .sql con el script para crear la base de datos, tablas y agregar algunos datos de prueba.


------------------------------------------------------------------------

## UML de la arquitectura

Se presenta un archivo UML con el diagrama de clases para
utilizarse en: *https://www.umletino.com/umletino.html*

------------------------------------------------------------------------

## Excepciones y validación

El proyecto organiza las excepciones específicas en:

``` text
exceptions/
├── InstitutoException.java
   ├── FechaInvalidaException.java
   ├── DatosInvalidosException.java
   └── UsuarioDuplicadoException.java
```

La presentación también incluye una utilidad:

``` text
utils/
└── Validador.java
```

El objetivo es separar las reglas de validación y los errores
específicos de la lógica principal de los controladores.

------------------------------------------------------------------------

## Flujo general de una operación

Una operación típica puede seguir este recorrido:

``` text
USUARIO
   │
   ▼
VISTA
   │
   │ entrada
   ▼
CONTROLADOR
   │
   │ decide
   ▼
DAO
   │
   │ SQL / JDBC
   ▼
BASE DE DATOS
   │
   │ resultado
   ▼
DAO
   │
   ▼
CONTROLADOR
   │
   ▼
VISTA
   │
   ▼
USUARIO
```

La idea fundamental es evitar que una única clase se encargue de
**mostrar datos, decidir qué hacer y ejecutar SQL**.

------------------------------------------------------------------------

## Principios de diseño trabajados

La separación propuesta por MVC favorece:

1.  **Separación de responsabilidades**
    -   Cada componente tiene una función específica.
2.  **Reutilización**
    -   Un mismo modelo puede utilizarse con distintas vistas.
3.  **Mantenibilidad**
    -   Los cambios quedan más acotados a la responsabilidad
        correspondiente.
4.  **Escalabilidad**
    -   Se pueden incorporar nuevas funcionalidades sin concentrar toda
        la lógica en una única clase.
5.  **Desacoplamiento**
    -   Las interfaces permiten depender de abstracciones en lugar de
        implementaciones concretas.
6.  **SOLID**
    -   La solución con una interfaz `Vista` permite aplicar
        especialmente **OCP** y **DIP**, tal como se analiza en la
        presentación.

------------------------------------------------------------------------

## Instalación y herramientas

Para trabajar con los ejemplos de esta clase se utilizan:

-   **Java**
-   **MySQL Community Server**
-   **MySQL Workbench**
-   **MySQL Connector/J**

La presentación indica que MySQL Community Server debe instalarse y
configurarse con una contraseña para el usuario `root`. También
proporciona los enlaces oficiales de descarga de MySQL Server y
Workbench.

Para JDBC se requiere el driver correspondiente a MySQL, **MySQL
Connector/J**.

------------------------------------------------------------------------

## Cómo estudiar los ejemplos

Se recomienda seguir este orden:

1.  Comprender la separación **Model -- View -- Controller**.
2.  Analizar cómo una interfaz permite desacoplar el controlador de las
    vistas concretas.
3.  Comprender qué es una base de datos relacional.
4.  Practicar las operaciones básicas de SQL y CRUD.
5.  Comprender qué problema resuelve JDBC.
6.  Analizar `ConexionDB`.
7.  Analizar `GenericDAO`.
8.  Estudiar `EstudianteDAO` y `MateriaDAO`.
9.  Relacionar los DAO con los modelos.
10. Analizar los controladores y sus vistas.
11. Finalmente, estudiar el diagrama UML completo.

------------------------------------------------------------------------

## Idea central de la clase

> **MVC no consiste simplemente en separar archivos en carpetas.**
>
> La verdadera separación consiste en decidir **quién representa los
> datos, quién los modifica, quién coordina el flujo, quién los muestra
> y quién se encarga de persistirlos**.

La combinación de MVC, DAO, JDBC y SQL permite construir una aplicación
donde cada responsabilidad se encuentra en un lugar definido y donde los
detalles técnicos de persistencia quedan aislados de la interacción con
el usuario.

---

| Realizado por **Miguel Silva C.** \
| GitHub: [github.com/darkwhiskers] *( https://github.com/darkwhiskers )* \
| Licencia: © darkwhiskers.org — *Attribution-NonCommercial-NoDerivatives 4.0 International (CC BY-NC-ND 4.0)*

