# Task Management Application: Feature Roadmap

This document tracks the features and changes being implemented for the new Task Management application.

### **Technology Stack**
*   **Backend:** Spring Boot 4.1, Spring Security 7.x, Java 17
*   **Data Persistence:** Spring Data JPA, H2 Database (for development)
*   **Frontend:** Thymeleaf
*   **Build Tool:** Gradle

---

### **Phase 1: Project Foundation & Domain Model (In Progress)**

- [ ] **Initialize Project:** Set up a clean Spring Boot project structure.
- [ ] **Add Dependencies:** Update `build.gradle` with dependencies for JPA, H2, and Validation.
- [ ] **Create `User` Entity:** Define the data model for users, including ID, username, password, and roles.
- [ ] **Create `Task` Entity:** Define the data model for tasks, including ID, title, description, due date, status, and a relationship to the user.
- [ ] **Create Repositories:** Implement Spring Data JPA repositories for `User` and `Task` entities.

---

### **Phase 2: Security & User Authentication**

- [ ] **Configure `SecurityConfig`:**
    - Set up password encoding (`BCryptPasswordEncoder`).
    - Configure form-based login.
    - Define authorization rules (e.g., permit all for login/registration, authenticated for the rest).
- [ ] **Implement `UserDetailsService`:** Create a service to load user-specific data for Spring Security.
- [ ] **Create `UserController`:**
    - Implement registration form display (`GET /register`).
    - Implement user registration logic (`POST /register`).
- [ ] **Develop Views:** Create Thymeleaf templates for registration (`register.html`) and update the login page if needed.

---

### **Phase 3: Core Task Management**

- [ ] **Create `TaskController`:**
    - Implement a view to display all tasks for the logged-in user.
    - Implement functionality to create, update, and delete tasks.
- [ ] **Create `TaskService`:** Implement business logic for task management.
- [ ] **Develop Views:** Create Thymeleaf templates for listing, creating, and editing tasks.
- [ ] **Secure Endpoints:** Ensure that users can only manage their own tasks.

---

### **Phase 4: Admin Functionality**

- [ ] **Create `AdminController`:**
    - Implement an admin dashboard to show system statistics.
    - Implement a view to manage all users and tasks.
- [ ] **Secure Admin Endpoints:** Restrict access to admin-only pages using `hasRole('ADMIN')`.
- [ ] **Develop Admin Views:** Create Thymeleaf templates for the admin dashboard.

---
