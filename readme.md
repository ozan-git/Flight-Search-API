# Flight Search API

## Description

The Flight Search API is a backend service designed to provide comprehensive flight search functionalities. It allows
users to query flights based on various criteria and manage bookings. This project is built using Spring Boot and
follows RESTful principles.

## Features

- Search for flights based on departure and arrival locations, dates, and times.
- View detailed information about flights including price and duration.
- User authentication and authorization for secure access.
- Ability to create, read, update, and delete (CRUD) flight and airport data.
- Scheduled tasks for fetching and updating flight data from third-party services.

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or later.
- Maven or Gradle (as your build tool).
- MySQL (or any other relational database you prefer).

### Setup

1. **Clone the repository**
    ```bash
    git clone https://github.com/ozan-git/flight-search-api
    cd flight-search-api
    ```

2. **Configure the database**
    - Create a new database in your MySQL server.
    - Update `src/main/resources/application.properties` with your database credentials and URL.

3. **Build the application**
    - For Maven:
        ```bash
        mvn clean install
        ```
    - For Gradle:
        ```bash
        gradle build
        ```

4. **Run the application**
    - For Maven:
        ```bash
        mvn spring-boot:run
        ```
    - For Gradle:
        ```bash
        gradle bootRun
        ```

## Usage

After starting the application, you can access the API endpoints through `http://localhost:8080/`. Here are some common
endpoints you might use:

- `GET /api/flights`: Retrieves all flights.
- `POST /api/flights`: Adds a new flight.
- `GET /api/airports`: Retrieves all airports.
- `POST /api/auth/signup`: Registers a new user.

For detailed API documentation, visit `http://localhost:8080/swagger-ui.html` after starting the application.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request or create an issue for any bugs or improvements.

## License

[MIT License](LICENSE.md)
