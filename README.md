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
   - Detecta los @GetMapping
        ↓
4. Inicia servidor Tomcat en puerto 8080
        ↓
5. Registra las rutas:
   - GET / → home()
   - GET /health → health()
        ↓
6. Aplicación lista! 🚀
   Listening on http://localhost:8080
```

# 📊 Endpoints Disponibles
| Método    | URL | Descripción| Respuesta |
| -------- | ------- | ---------| ---------|
| GET      | /       | Página de inicio | Texto: "¡Hola! La API está funcionando correctamente"| 
| GET      | /health     | Health check| Texto: "OK"|
