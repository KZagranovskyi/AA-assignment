# Prerequisites:

1. Ensure you have Java SE Development Kit (JDK) v21 installed on your machine. You can download it from [Oracle's official website](https://www.oracle.com/java/technologies/javase-downloads.html).

2. Make sure you have IntelliJ IDEA 2023.3.1, Ultimate Edition. You can download it from [JetBrains' official website](https://www.jetbrains.com/idea/download/).

3. A database compatible with Spring Data JPA (for example, MySQL, PostgreSQL).

# Steps to run the application:

1. Open Terminal in your macOS and navigate to your project directory.

2. Within your project root directory, if you use Maven, run the following command to clean and build your project:
   ```bash
   mvn clean install
   ```
   If you use Gradle, use this command instead:
   ```bash
   ./gradlew clean build
   ```

3. Import the project into IntelliJ IDEA:
    - Click on `File > Open`.
    - Navigate to your project directory and click `Open`.

4. IntelliJ IDEA will detect the build file and import the project automatically.

5. Update the `spring.datasource` properties in your `application.properties` file with your actual database details.

6. Start your database server.

7. Locate your main Spring Boot Application class in IntelliJ IDEA, Right-click and run. Or use command line:
   ./gradlew bootRun
   `

8. The application will be running on your local server.

# Access the Application:

After you finish these steps, if you are running a web application, you can take these steps to access the application:

1. Open your browser, in the address bar, type `localhost:<your_port>` and press `Enter`.

   **Note:** substitute `<your_port>` with the port number on which your application is running (for example, the default port for Spring Boot applications is `8080`).

If these steps don't work for your project or if there are any specific configurations necessary for your project, I recommend checking the project's README.md file or any other project documentation.