# 🎟️ Event Seat Booking System

A microservices-based Event Seat Booking System developed using **Spring Boot**.

The application is divided into multiple independent microservices to handle different functionalities such as user management, events, seats, bookings, payments, and notifications.

---

# 🏗️ System Architecture & Working Flow

This section explains the overall **system design and working flow** of the Event Seat Booking System.

The application follows a **Microservices Architecture**, where different functionalities are divided into separate services. The system uses an **API Gateway** as the central entry point and a **Discovery Server (Eureka)** for service registration and discovery.

The architecture also explains how a user interacts with the system, starting from **registration and login**, followed by authentication and access to the available application services.

---

# 📂 Project Structure

```text
Event-Seat-Booking-System
│
├── api-gateway
├── discovery-server
├── user-service
├── event-service
├── seat-service
├── booking-service
├── payment-service
└── notification-service
```

---

# 🔐 Authentication & Access Flow

Before accessing protected application features, a user must be registered in the system.

The user can log in only after successful registration.

After successful login, the user is authenticated and can access the protected services of the application.

```text
User
 │
 ├── New User
 │      │
 │      ▼
 │   Register
 │      │
 │      ▼
 │  User Service
 │
 └── Existing User
        │
        ▼
      Login
        │
        ▼
   Authentication
        │
        ▼
   Authenticated User
        │
        ▼
 Access Protected Services
```

---

# 🏗️ Microservices System Architecture

The application follows a microservices architecture where each major functionality is handled by a separate service.

The **API Gateway** acts as the main entry point for client requests and routes requests to the appropriate microservice.

All microservices are registered with the **Discovery Server (Eureka)** for service registration and discovery.

```text
                              Client
                                 │
                                 ▼
                           API Gateway
                                 │
        ┌────────────────────────┼────────────────────────┐
        │                        │                        │
        ▼                        ▼                        ▼
   User Service             Event Service             Seat Service
        │                        │                        │
        └────────────────────────┼────────────────────────┘
                                 │
                 ┌───────────────┼────────────────┐
                 ▼               ▼                ▼
          Booking Service   Payment Service   Notification Service


        All Microservices Register With
                    │
                    ▼
             Discovery Server
                  (Eureka)
```

---

# 🔄 Overall Working Flow

The overall working flow of the Event Seat Booking System is explained below.

## 1️⃣ User Registration

A new user registers in the application through the **User Service**.

## 2️⃣ User Login

An existing registered user logs into the application.

## 3️⃣ Authentication

After successful login, the user is authenticated.

## 4️⃣ Access to Application Services

After authentication, the user can access the protected services available in the application.

These services include:

* Event Service
* Seat Service
* Booking Service
* Payment Service
* Notification Service

## 5️⃣ Request Routing

Client requests are sent through the **API Gateway**.

The API Gateway routes each request to the appropriate microservice.

## 6️⃣ Service Discovery

All microservices register themselves with the **Discovery Server (Eureka)**.

This allows services to discover each other within the microservices architecture.

---

# 🔗 High-Level System Flow

```text
Register
   │
   ▼
Login
   │
   ▼
Authentication
   │
   ▼
Authenticated User
   │
   ▼
API Gateway
   │
   ├──────────► Event Service
   │
   ├──────────► Seat Service
   │
   ├──────────► Booking Service
   │
   ├──────────► Payment Service
   │
   └──────────► Notification Service
```

---

# 🔹 Microservices

## 🌐 API Gateway

The API Gateway acts as the central entry point for client requests and routes requests to the appropriate microservices.

---

## 🔎 Discovery Server

The Discovery Server is used for service registration and discovery using **Eureka**.

---

## 👤 User Service

The User Service handles user-related operations, including user registration, login, and authentication.

---

## 🎭 Event Service

The Event Service handles event-related operations.

---

## 💺 Seat Service

The Seat Service handles seat-related operations.

---

## 📅 Booking Service

The Booking Service handles booking-related operations.

---

## 💳 Payment Service

The Payment Service handles payment-related operations.

---

## 🔔 Notification Service

The Notification Service handles notification-related operations.

---

# 🔗 Service Communication

The microservices architecture uses the **Discovery Server** for service discovery.

Where required, services can communicate with each other using **OpenFeign**.

The API Gateway provides centralized routing for client requests.

---

# 🛠️ Technologies Used

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate

### Security

* Spring Security
* JWT

### Microservices

* Spring Cloud
* Eureka Discovery Server
* API Gateway
* OpenFeign
* Spring Cloud LoadBalancer

### Database

* MySQL

### Tools

* Maven
* Postman
* Lombok

---

# 🗄️ Database

The application uses **MySQL** for data persistence.

**Spring Data JPA** and **Hibernate** are used for database operations and object-relational mapping.

---

# ▶️ How to Run the Project

## 1️⃣ Clone the Repository

```bash
git clone https://github.com/Anushka-kumari37/Event-Seat-Booking-System.git
```

## 2️⃣ Configure MySQL

Configure the database properties in the required microservices.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## 3️⃣ Start the Discovery Server

Start the **Discovery Server** first.

The microservices will register with Eureka after they are started.

---

## 4️⃣ Start the Microservices

Run the following services:

* User Service
* Event Service
* Seat Service
* Booking Service
* Payment Service
* Notification Service

---

## 5️⃣ Start the API Gateway

Finally, start the **API Gateway**.

The API Gateway acts as the central entry point for accessing the application services.

---

# 🎯 What Does This Architecture Explain?

This architecture explains:

* How a new user registers in the system.
* How an existing registered user logs in.
* How authentication controls access to protected services.
* How client requests enter the system through the API Gateway.
* How requests are routed to different microservices.
* How the application is divided into independent services.
* How Eureka Discovery Server helps services register and discover each other.
* How different services work together within the application.

---

# 👩‍💻 Author

**Anushka Kumari**

Java Backend Developer | Spring Boot | Microservices

GitHub: https://github.com/Anushka-kumari37
