# Etapa de compilación
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

#Copiar pom.xml padre
COPY pom.xml ./pom.xml 

# Copiar el pom.xml de cada módulo para resolver dependencias
COPY microservice-inventory/pom.xml ./microservice-inventory/
COPY microservice-order/pom.xml ./microservice-order/
COPY microservice-eureka/pom.xml ./microservice-eureka/
COPY microservice-gateway/pom.xml ./microservice-gateway/
COPY microservice-config/pom.xml ./microservice-config/

# Descargar dependencias (Capa de caché)
RUN mvn dependency:go-offline -B

# Copiar el código fuente del módulo Inventory
COPY microservice-inventory/src ./microservice-inventory/src

# Compilar específicamente este módulo 
RUN mvn clean package -pl microservice-inventory -am -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Crear un usuario sin privilegios por seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el jar desde la etapa de build
COPY --from=build /app/microservice-inventory/target/*.jar app.jar

# Exponer el puerto en el que corre el microservicio
EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]

