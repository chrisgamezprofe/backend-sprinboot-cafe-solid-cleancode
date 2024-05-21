# Usa la imagen base de OpenJDK 17
FROM adoptopenjdk/openjdk17:alpine

# Establece el directorio de trabajo en /app
WORKDIR /rapp

# Copia el archivo JAR construido en el directorio de trabajo
COPY target/tareas.jar /app/app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]
