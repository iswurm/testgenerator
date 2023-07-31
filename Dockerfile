# Establece la imagen base de Java
FROM openjdk:22-slim-bullseye

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el jar de la aplicación a la imagen
COPY testgenerator.jar /app

# Comando para ejecutar la aplicación Java
CMD ["java", "-jar", "testgenerator.jar"]