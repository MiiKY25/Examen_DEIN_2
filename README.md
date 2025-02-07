# Proyecto de Gestión de Préstamos y Libros

Este proyecto es una aplicación de gestión de préstamos de libros y alumnos. Permite realizar las siguientes funcionalidades:

- Gestión de alumnos: altas, modificaciones y consultas.
- Gestión de libros: altas, bajas, modificaciones y consultas.
- Gestión de préstamos: altas, consultas y validación para evitar que un libro prestado sea prestado nuevamente.
- Gestión de devoluciones: registro en el histórico de préstamos, modificación del estado del libro y eliminación de registros de préstamos actuales.
- Consulta de historial de préstamos con filtro por criterios.
- Generación de informes en PDF y gráficos estadísticos.

## Funcionalidades

### Gestión de Alumnos
- Alta, modificación y consulta de datos de los alumnos (nombre, apellidos, DNI).

### Gestión de Libros
- Alta, baja, modificación y consulta de libros (ISBN, título, autor, editorial, estado).
- Cuando un libro es dado de baja, no se elimina de la base de datos, pero se marca como "baja" para no aparecer en la tabla, solo en el histórico de préstamos.
- Los libros tienen los siguientes estados: *Nuevo*, *Usado nuevo*, *Usado seminuevo*, *Usado estropeado*, *Restaurado*.

### Gestión de Préstamos
- Alta y consulta de préstamos de libros a alumnos.
- Validación para evitar que un libro que ya está prestado pueda ser prestado de nuevo.
- Los préstamos pueden ser realizados en línea.

### Gestión de Devoluciones
- Registro de la devolución en el histórico de préstamos.
- Modificación del estado del libro cuando se devuelve.
- Eliminación del registro de préstamo de la tabla de préstamos activos.

### Consultas del Histórico de Préstamos
- Consulta del histórico de préstamos con filtros por criterios como alumno o libro.

### Multilenguaje
- La aplicación soporta múltiples idiomas, utilizando un archivo de configuración para manejar el idioma actual de la aplicación.

### Informes Generados
1. **Informe 1**: Al registrar un préstamo, se genera un informe PDF con los siguientes detalles:
   - Alumno (nombre, apellidos y DNI).
   - Libro (código, título, autor, editorial y estado).
   - Fecha de préstamo.
   - Fecha límite de devolución (fecha del préstamo + 15 días).
2. **Informe 2**: Listado de libros, con un subinforme por cada libro que muestra los préstamos asociados a él.
3. **Informe 3**: Informe con múltiples subinformes gráficos (como gráfico de barras) mostrando estadísticas de los libros prestados.
4. **Informe 4**: Informe de alumnos con cálculos como el número de libros prestados y días de préstamo.

## Requisitos

- **JDK**: 11 o superior.
- **MySQL**: Para gestionar la base de datos de alumnos, libros y préstamos.
- **JavaFX**: Para la interfaz gráfica de la aplicación.
- **Dependencias**:
  - Ikonli (para iconos personalizados).
  - PDF generation libraries (como iText) para la creación de informes PDF.
  - Hibernate para la gestión de la persistencia de los datos.

## Instalación

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/usuario/gestion-prestamos-libros.git
   cd gestion-prestamos-libros
