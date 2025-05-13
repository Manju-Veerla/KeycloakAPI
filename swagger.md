# Swagger Documentation

## Overview
This document provides an overview of the API endpoints available in the Keycloak integration project.

## API Endpoints

### User Controller
- **GET /users/all**: Retrieves all users. Requires `CLIENT_ADMIN` or `ADMIN` role.
- **GET /user/{id}**: Retrieves a specific user by ID. Requires `CLIENT_ADMIN` or `USER` role.

## Authentication
All endpoints require JWT authentication. Include the token in the Authorization header as follows:
```
Authorization: Bearer <your_token>
```

## License
This documentation is licensed under the MIT License.
