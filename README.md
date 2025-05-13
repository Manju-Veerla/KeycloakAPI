# Keycloak Integration Project

## Overview
This project integrates Keycloak for authentication and authorization in a Java application. It leverages JWT (JSON Web Tokens) to manage user sessions and roles.

## Features
- Role-based access control using Spring Security.
- JWT authentication and authorization.
- Support for multiple user roles.

## Getting Started
### Prerequisites
- Java 11 or higher
- Maven
- Keycloak server running

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/keycloak-integration.git
   cd keycloak-integration
   ```
2. Install dependencies:
   ```bash
   mvn install
   ```

### Running the Application
To run the application, use:
```bash
mvn spring-boot:run
```

### Configuration
- Update the `application.properties` file with your Keycloak server details.

## Usage
- Access the application at `http://localhost:8080`
- Use the appropriate roles to access secured endpoints.

## Contributing
Contributions are welcome! Please open an issue or submit a pull request.

## License
This project is licensed under the MIT License.
