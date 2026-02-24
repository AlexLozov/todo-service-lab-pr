FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/todo_service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# docker build -t my-todo-backend .
# docker run -d --name my_app_backend --network my_project_network -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://my_db:5432/todo-db-postgresql -e SPRING_DATASOURCE_USERNAME=postgres -e SPRING_DATASOURCE_PASSWORD=postgres my-todo-backend

# создать бд
# docker run -d --name my_db --network my_project_network -v todo-service_postgres_data:/var/lib/postgresql/data -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=todo-db-postgresql -p 5433:5432 postgres:16