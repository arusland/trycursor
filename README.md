# Full-Stack Web Application

A modern web application built with Vue.js frontend and Spring Boot backend, featuring a dashboard and multiple views.

## Project Structure

```
trycursor/
├── frontend/          # Vue.js frontend application
├── backend/           # Spring Boot backend application
└── README.md         # This file
```

## Features

- **Dashboard**: Overview with charts and statistics
- **User Management**: CRUD operations for users
- **Analytics**: Data visualization and reporting
- **Responsive Design**: Mobile-friendly interface
- **RESTful API**: Spring Boot backend with JPA/H2 database

## Technology Stack

### Frontend
- Vue.js 3 with Composition API
- Vue Router for navigation
- Axios for HTTP requests
- Chart.js for data visualization
- Tailwind CSS for styling

### Backend
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Spring Web
- Spring Boot DevTools

## Getting Started

### Prerequisites
- Node.js (v16 or higher)
- Java 17 or higher
- Maven

### Running the Application

1. **Start the Backend**
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```
   The backend will be available at http://localhost:8080

2. **Start the Frontend**
   ```bash
   cd frontend
   npm install
   npm run dev
   ```
   The frontend will be available at http://localhost:5173

## API Endpoints

- `GET /api/dashboard` - Dashboard statistics
- `GET /api/users` - Get all users
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user
- `GET /api/analytics` - Analytics data

## Development

The application includes:
- Hot reload for both frontend and backend
- API documentation with Swagger
- Error handling and validation
- Modern UI components 