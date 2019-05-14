# **Plataforma Historial de Equipos Laboratorio de Sistemas. Equipo NGCM**
### **CVDS 2019 - 1**
|     Nombre    |     Rol         |
|:--------------:|:-------------: |
|Julian Velasco|Product Owner|
|Andres Gualdron|Scrum Master|
|Natalia Palacios|Team Developer|
|Cesar Villamil|Team Developer|
|Miguel Sanchez|Team Developer|

## **Descripción del Producto**
### Descripción General
Esta aplicación busca llevar un historial de novedades de los elementos del laboratorio de informática de la decanatura de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería Julio Garavito. Esto permitirá una gestión más fácil de los recursos y un acceso sencillo a la información de cada elemento de las salas. 
La Plataforma esta fundamentada en tres pilares: elementos, equipos y laboratorios. Sobre estos tres componentes guarda registros de las novedades que ocurren con ellos. También tiene la funcionalidad de asociar elementos a un equipo y equipos a un laboratorio, a su vez registra o muestra el estado de los tres componentes. 
 
### Manual de Usuario
Es necesario iniciar sesión para poder utilizar la aplicación.
+ **Usuario:** *gualdronsito@hotmail.com*
+ **Contraseña:** *123*

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/1.png "Página de Login")

Una vez dentro de la aplicación podremos utilizar las funcionalidades relacionadas con los elementos, equipos y laboratorios, de igual manera podremos hacer uso de las opciones relacionadas con las novedades.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/2.png "Módulos de la aplicación")

#### 1. Elementos
La primera funcionalidad de este módulo es la de poder realizar el registro de un nuevo elemento, ingresando un identificador, nombre, tipo de elemento y equipo podremos agregar a la plataforma un componente nuevo.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/3.png "Registrar un elemento")

Aparte de registrar un elemento nuevo, la aplicación me permite ver un listado de todos los elementos existentes. De cada elemento puedo conocer: identificador, nombre, tipo de elemento, equipo al que está relacionado y estado de funcionamiento, que me indica si el elemento está en condiciones de ser utilizado.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/4.png "Tabla de Elementos")

Adicional a esto puedo conocer las novedades de cada elemento que estén registradas en el historial y modificar el nombre del mismo.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/5.png "Novedades del Elemento")

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/6.png "Modificar un Elemento")

También tengo la posibilidad de eliminar uno a varios elementos si lo deseo, así como descargar la información de los elementos en formato Excel.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/7.png "Eliminar un Elemento")

Como ultima funcionalidad de este modulo tengo acceso a un listado de los elementos que no están enlazados con algún equipo, para poder relacionarlo si lo deseo.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/8.png "Enlazar un Elemento")

#### 2. Equipos
La aplicación, de la misma forma que con los elementos, me permite registrar un nuevo equipo. La información requerida es: Laboratorio, monitor, mouse, torre y teclado, permitiéndome así enlazar un equipo a un respectivo laboratorio o a varios elementos desde el inicio. 

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/9.png "Registrar un Equipo")

La aplicación también me da acceso al listado de equipos existentes, permitiéndome ver las novedades que cada uno de ellos tiene registradas, modificar su información, eliminar un equipo y descargar toda esta información en formato Excel.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/10.png "Tabla de Elementos")

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/11.png "Modificar un Elemento")

#### 3. Laboratorios
Al igual que en los otros módulos, la aplicación me permite registrar nuevos laboratorios dada una información requerida.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/12.png "Registrar un Laboratorio")

La aplicación también me permite ver el listado de los laboratorios registrados, eliminarlos y descargar toda la información en formato Excel.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/13.png "Tabla de Laboratorios")

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/14.png "Eliminar Laboratorio")

Este módulo nos permite ver algunas estadísticas que facilitan la administración de los recursos del laboratorio. Encontramos dos gráficas que nos indican la cantidad de equipos registrados y eliminados por laboratorio.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/15.png "Tablas de Laboratorios")

#### 4. Novedades
El ultimo módulo de la aplicación me permite registrar una novedad, ya sea referente a un equipo o a un elemento.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/16.png "Registrar una Novedad")

Cada novedad que se registre va a requerir cierta información, tal como descripción, tipo de novedad, entre otros datos.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/17.png "Novedad de Elemento")

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/18.png "Novedad de Equipo")

Al igual que en los demás módulos, la aplicación me permite desplegar una tabla con la lista de novedades registrada en el historial de la aplicación, así como descargar el listado en formato Excel.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/19.png "Tabla de Novedades")

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/20.png "Descargar Novedades")


## **Arquitectura y Diseño detallado**
### Modelo Entidad - Relación
![](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.github.com/proyectocvds2019/NGCM/master/modeloBaseDatos.puml "Modelo Entidad-Relación")

### Diagrama de Clases
![](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.github.com/proyectocvds2019/NGCM/master/diagramaClases.puml "Diagrama de Clases")

### Arquitectura de la Aplicación
#### Front-End
- Login
	- Apache Shiro
- Elementos Visuales
	- PrimeFaces
	- JavaScript
#### Back-End
- Test
	- QuickTheories 
- Dependency Injection and Mappers
	- MyBatis
	-  Guice
#### Database
- PostgreSQL
### Despliegue
https://proyectocvds2019.herokuapp.com

### Integracion Continua
https://circleci.com/gh/proyectocvds2019/NGCM

## **Descripción del Proceso**
### Integrantes
- Andrés Gualdron - Scrum Master
- Natalia Palacios - Team Developer
- Cesar Villamil - Team Developer
- Miguel Sanchez -Team Developer
### Metodología
Se utilizo la metodología ágil de proyectos Scrum, se realizaron sprints de 15 días de los cuales su resultado era un producto funcional y con valor. El trabajo a realizar en cada sprint fue planeado por el equipo antes de su inicio, dividiendo este en tareas de usuario muy especificas, por lo cual había gran numero de estas. Las tareas que no se pudieron realizar en los sprints fueron postergadas al siguiente ciclo.

### Backlog

[Taiga backlog](https://tree.taiga.io/project/gualdronsito-historial-de-equipos-labinfo/backlog)

### Release-Burndown Chart
#### Puntos de Historia
Se lograron completar todos los puntos de historia propuestos en el backlog del proyecto.

![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/PuntosHistoriaProyecto.png "Puntos de Historia del Proyecto")

### Sprint 1
#### Sprint Backlog 
![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/BacklogSprint1.png "Backlog del Primer Sprint")
#### Sprint Burndown Chart
![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/RBDCSprint1.png "Burndown Chart del Primer Sprint")

En este sprint el equipo estaba entendiendo la problemática, esto combinado con la falta de comunicación provoco cierto retraso en la realización y completitud de las tareas. No se lograron terminar todas las historias de usuario, por lo que la tarea faltante fue movida al segundo ciclo.
 
### Sprint 2
#### Sprint Backlog
![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/BacklogSprint2.png "Backlog del Segundo Sprint")
#### Sprint Burndown Chart
![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/RBDCSprint2.png "Burndown Chart del Segundo Sprint")

El equipo tomo más historias de usuario. La mejor comunicación mejoró el desempeño general del trabajo, por lo que las historias y tareas fueron completadas con gran agilidad.

### Sprint 3
#### Sprint Backlog
![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/BacklogSprint3.png "Backlog del Tercer Sprint")
#### Sprint Burndown Chart
![](https://raw.githubusercontent.com/proyectocvds2019/NGCM/master/images/RBDCSprint3.png "Burndown Chart del Tercer Sprint")

En este sprint el equipo siguió con el buen trabajo realizado en el segundo ciclo, adicionalmente se logro trabajar en conjunto mucho más tiempo, permitiendo aplicar practicas de la programación extrema. A pesar de que uno de los miembros del equipo estuvo ausente durante la gran mayoría del proyecto, el desempeño del resto del equipo, y su habilidad de gestionar las tareas y el tiempo, permitieron obtener un resultado satisfactorio.

### Reporte de Pruebas
Se probaron los registros de cada modulo, los enlaces entre elementos y equipos, los enlaces entre equipos y laboratorios y las eliminaciones de los diversos componentes de la aplicación. De igual manera se probaron las distintas consultas realizadas en las funcionalidades.

[![CircleCI](https://circleci.com/gh/proyectocvds2019/NGCM.svg?style=svg)](https://circleci.com/gh/proyectocvds2019/NGCM)

### Análisis de Código

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2c430545fff64cf8a324cf969958f1c7)](https://www.codacy.com/app/AndresFelipeGualdron/NGCM?utm_source=github.com&utm_medium=referral&utm_content=proyectocvds2019/NGCM&utm_campaign=Badge_Grade)
