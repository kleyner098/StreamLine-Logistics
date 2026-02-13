# Etapa de compilación
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

#Copiar pom.xml padre
COPY pom.xml ./pom.xml 

# Copiar el pom.xml de cada módulo para resolver dependencias
COPY microservice-eureka/pom.xml ./microservice-eureka/
COPY microservice-gateway/pom.xml ./microservice-gateway/
COPY microservice-config/pom.xml ./microservice-config/
COPY microservice-order/pom.xml ./microservice-order/
COPY microservice-inventory/pom.xml ./microservice-inventory/
COPY microservice-tracking/pom.xml ./microservice-tracking/

# Descargar dependencias (Capa de caché)
RUN mvn dependency:go-offline -B

# Copiar el código fuente del módulo Eureka
COPY microservice-eureka/src ./microservice-eureka/src

# Compilar específicamente este módulo 
RUN mvn clean package -pl microservice-eureka -am -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Crear un usuario sin privilegios por seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el jar desde la etapa de build
COPY --from=build /app/microservice-eureka/target/*.jar app.jar

# Exponer el puerto por defecto de Eureka
EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]

