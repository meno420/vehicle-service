# vehicle-service

Microservicio de gestión de vehículos: información técnica, estado de la batería/motor, última ubicación y mantenimiento. Base de datos propia `vehicle_db`.

**Stack:** Java 17 · Spring Boot 3.3 · Maven · PostgreSQL  
**Puerto:** 8081

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/vehicles` | Lista todos los vehículos |
| GET | `/vehicles/{id}` | Obtiene un vehículo (404 si no existe) |
| POST | `/vehicles` | Crea un vehículo (201 + Location) |

## Modelo (`vehicles`)

`id`, `plate`, `model`, `batteryStatusPercentage`, `engineStatus`, `lastLatitude`, `lastLongitude`, `lastMaintenanceDate`


## Cómo ejecutarlo

```bash
# Antes: CREATE DATABASE vehicle_db;  (desde psql)
mvn spring-boot:run
```

## Diagramas

Los diagramas editables en draw.io están en `docs/vehicle-service-diagramas.drawio` (páginas: componentes y secuencia).
Las mismas vistas en Mermaid (se renderizan directo en GitHub):

### Componentes

```mermaid
flowchart LR
    C["Cliente<br/>(fleet-composite-service / Postman)"] -->|HTTP REST| VC
    subgraph VS["vehicle-service :8081"]
        VC["VehicleController<br/>(RestController)"] --> VSV["VehicleService<br/>(Service)"]
        VSV --> VR["VehicleRepository<br/>(Spring Data JPA)"]
        DS["DataSeeder"] --> VR
    end
    VR -->|JDBC / Hibernate| DB[("PostgreSQL<br/>vehicle_db")]
```

### Secuencia

```mermaid
sequenceDiagram
    participant C as Cliente
    participant VC as VehicleController
    participant VS as VehicleService
    participant VR as VehicleRepository
    participant DB as PostgreSQL vehicle_db
    C->>VC: GET /vehicles/{id}
    VC->>VS: findById(id)
    VS->>VR: findById(id)
    VR->>DB: SELECT * FROM vehicles WHERE id = ?
    DB-->>VR: fila o sin resultado
    VR-->>VS: Optional con el vehiculo
    alt no existe
        VS-->>VC: ResponseStatusException 404
        VC-->>C: 404 Not Found
    else existe
        VS-->>VC: Vehicle
        VC-->>C: 200 OK + JSON
    end
```
