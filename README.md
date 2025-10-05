# 📂 Estructura del Proyecto
```
my-first-API-and-Jenkins/
├── Dockerfile                          # Para crear imagen Docker
├── Jenkinsfile                         # Pipeline CI/CD
├── LICENSE
├── pom.xml                             # Configuración Maven
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── demo/
    │   │           └── Application.java    # la aplicacion
    │   └── resources/
    │       └── application.properties      # Configuración
    └── test/
        └── java/
            └── com/
                └── demo/
                    └── ApplicationTest.java # Tests

```

# 🔄 Cómo Funciona tu API - Paso a Paso
```
1. JVM ejecuta main()
        ↓
2. SpringApplication.run() inicia Spring Boot
        ↓
3. Spring Boot:
   - Lee application.properties
   - Escanea @SpringBootApplication
   - Encuentra @RestController
   - Detecta los @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
        ↓
4. Inicia servidor Tomcat en puerto 8081
        ↓
5. Registra las rutas:
   - GET /api/health → health()
   - GET /api/tasks → getAllTasks()
   - GET /api/tasks/{id} → getTask()
   - POST /api/tasks → createTask()
   - PUT /api/tasks/{id} → updateTask()
   - DELETE /api/tasks/{id} → deleteTask()
        ↓
6. Aplicación lista! 🚀
   Listening on http://localhost:8081
```

# 📊 Endpoints Disponibles
| Método    | URL | Descripción| Respuesta |
| -------- | ------- | ---------| ---------|
| GET      | /api/tasks       | Obtener todas las tareas | Array JSON de tareas| 
| GET      | /api/health     | Verificar estado de la API | JSON con status, timestamp y mensaje |




# 🧪 Pruebas Rápidas
## Health check
curl http://localhost:8081/api/health

## Crear una tarea
curl -X POST http://localhost:8081/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Test","description":"Demo","completed":false}'

## Listar tareas
curl http://localhost:8081/api/tasks

## Actualizar tarea
curl -X PUT http://localhost:8081/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Test actualizado","description":"Demo","completed":true}'
  
  
  
#  🛠️ Tecnologías
Java 17

Spring Boot 3.1.5

Maven

Docker

# 📝 Notas
- Spring Security está configurado para permitir todos los requests sin autenticación (solo para demo)
- Los datos se almacenan en memoria y se pierden al reiniciar la aplicación
- El puerto por defecto es 8081 (configurable en application.properties)


