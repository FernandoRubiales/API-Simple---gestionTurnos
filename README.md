# API de Gestión de Turnos Médicos

API REST desarrollada con Spring Boot para la gestión de turnos médicos, pacientes y doctores.

## Descripción

Sistema de gestión de turnos médicos que permite:
- Registrar y gestionar pacientes
- Registrar y gestionar doctores
- Crear y administrar turnos médicos
- Gestionar estados de turnos médicos

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.5.10**
- **PostgreSQL**
- **Lombok** 
- **Maven**

## Arquitectura del Proyecto
```
src/main/java/com/example/apiTurnos/
├── config/          # Configuraciones (inicialización de datos)
├── controller/      # Controladores REST
├── dto/            # Data Transfer Objects
├── entity/         # Entidades JPA
├── mapper/         # Conversión entre Entity y DTO
├── repository/     # Repositorios JPA
└── service/        # Lógica de negocio
```

## Modelo de Datos

### Entidades Principales

- **Paciente**: Información de pacientes
- **Doctor**: Información de doctores 
- **EstadoTurno**: Estados posibles de un turno
- **Turno**: Relación entre paciente, doctor y estado

### Relaciones

- Un turno pertenece a **un paciente**
- Un turno pertenece a **un doctor**
- Un turno tiene **un estado**

## 🔧 Instalación y Configuración

### Prerrequisitos

- Java 21 o superior
- PostgreSQL 16
- Maven 3.8+

### Configuración de Base de Datos

1. Crear una base de datos en PostgreSQL:
```sql
CREATE DATABASE gestion_turnos;
```

2. Configurar las credenciales en `src/main/resources/application.yaml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/gestion_turnos
    username: tu_usuario
    password: tu_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### Ejecutar la Aplicación
```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/api-gestion-turnos.git

# Navegar al directorio
cd api-gestion-turnos

# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

## Endpoints Principales

### Pacientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/pacientes` | Crear un paciente |
| GET | `/api/pacientes` | Listar todos los pacientes |
| GET | `/api/pacientes/{id}` | Obtener un paciente por ID |
| GET | `/api/pacientes/dni/{dni}` | Buscar paciente por DNI |
| PUT | `/api/pacientes/{id}` | Actualizar un paciente |
| DELETE | `/api/pacientes/{id}` | Eliminar un paciente |

### Doctores

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/doctores` | Crear un doctor |
| GET | `/api/doctores` | Listar todos los doctores |
| GET | `/api/doctores/{id}` | Obtener un doctor por ID |
| GET | `/api/doctores/especialidad/{especialidad}` | Buscar por especialidad |
| GET | `/api/doctores/matricula/{matricula}` | Buscar por matrícula |
| PUT | `/api/doctores/{id}` | Actualizar un doctor |
| DELETE | `/api/doctores/{id}` | Eliminar un doctor |

### Turnos

| Método | Endpoint | Descripción             |
|--------|----------|-------------------------|
| POST | `/api/turnos` | Crear un turno          |
| GET | `/api/turnos` | Listar todos los turnos |
| GET | `/api/turnos/{id}` | Obtener un turno por ID |
| GET | `/api/turnos/paciente/{id}` | Turnos por paciente     |
| GET | `/api/turnos/doctor/{id}` | Turnos por doctor       |
| GET | `/api/turnos/fechas?inicio=X&fin=Y` | Turnos entre fechas     |
| PUT | `/api/turnos/{id}/estado/{nombre}` | Cambiar estado de turno |
| DELETE | `/api/turnos/{id}` | Eliminar turno          |

## Ejemplos de Uso

### Crear un Paciente

**Request:**
```bash
POST http://localhost:8080/api/pacientes
Content-Type: application/json

{
  "nombre": "Mariano",
  "apellido": "Lopez",
  "dni": "42342657",
  "email": "marian@test.com",
  "phone": "2634568798"
}
```

**Response:**
```json
{
  "id": 1,
  "nombre": "Mariano",
  "apellido": "PéLopezrez",
  "dni": "42342657",
  "email": "marian@test.com",
  "phone": "2634568798"
}
```

### Crear un Turno

**Request:**
```bash
POST http://localhost:8080/api/turnos
Content-Type: application/json

{
  "pacienteId": 1,
  "doctorId": 1,
  "fechaCreacion": "2026-03-10T14:00:00"
}
```

**Response:**
```json
{
  "id": 1,
  "paciente": {
    "id": 1,
    "nombre": "Marian",
    "apellido": "Lopez",
    "dni": "42342657"
  },
  "doctor": {
    "id": 1,
    "nombre": "Carlos",
    "apellido": "Fredes",
    "especialidad": "Cardiología"
  },
  "estado": {
    "id": 1,
    "nombreEstado": "CREADO"
  },
  "fechaCreacion": "2026-03-10T14:00:00"
}
```
