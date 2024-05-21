<img src="arquitectura (1).jpg" />

# Nombre del Proyecto

Api backend

## Requisitos

- Java 17
- Maven
- Docker (opcional)

## Configuración

1. Clona el repositorio:

    ```bash
    git clone https://github.com/chrisgamezprofe/backend-sprinboot-cafe-solid-cleancode.git
    ```

2. Navega al directorio del proyecto:

    ```bash
    cd nombre-del-proyecto
    ```

3. (Opcional) Construye el proyecto con Maven:

    ```bash
    mvn clean package
    ```

## Ejecución

### Ejecución local

1. (Opcional) Si has construido el proyecto manualmente, puedes ejecutarlo localmente con Maven:

    ```bash
    mvn spring-boot:run
    ```

2. Accede a la aplicación en [http://localhost:8080](http://localhost:8080).

### Ejecución con Docker

1. Construye la imagen Docker:

    ```bash
    docker build -t nombre-imagen .
    ```

2. Ejecuta el contenedor:

    ```bash
    docker run -d -p 8080:8080 nombre-imagen
    ```

3. Accede a la aplicación en [http://localhost:8080](http://localhost:8080).

## Uso

Describe cómo utilizar las diferentes características de tu API y proporciona ejemplos de solicitudes y respuestas.

## Documentación

Puedes acceder a la documentación de la API en [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) después de iniciar la aplicación.

## Contribuciones

¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto, no dudes en abrir un issue o enviar un pull request.

## Licencia

[Descripción de la licencia y enlace a la misma]
