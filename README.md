# E-Commerce Order Management System
<br>
A backend REST API application developed using Java, Spring Boot, Spring Data JPA, and MySQL to manage e-commerce operations, including products, customers, orders, inventory, and simulated payments.
<br> <br>
Features
<br>
- Product Management: Create, retrieve, update, delete, and search products by name and category. <br>
- Customer Management: Customer registration, profile management, retrieval, and search.<br>
- Order Management: Create orders containing multiple products with server-side total price calculation.<br>
- Inventory Management: Validate product availability, automatically update stock during order creation, and restore stock when eligible orders are cancelled.<
- Payment Management: Simulated payment processing with duplicate payment prevention and order status updates.<br>
- Order Tracking: Retrieve orders by order ID, customer ID, and view all orders.<br>
- Validation & Exception Handling: Implemented DTO-based request validation and custom global exception handling.<br>
- Transaction Management:  Used Spring's `@Transactional` to maintain consistency during order creation and cancellation.<br> 

## Technology Stack
<br>
- Language: Java  <br>
- Backend Framework: Spring Boot <br>
- ORM: Spring Data JPA, Hibernate  <br>
- Database: MySQL <br>
- API: RESTful APIs  <br>
- Tools: IntelliJ IDEA, Postman, Maven  <br>

## Key Learning Outcomes
<br>
- Implemented layered architecture using Controller, Service, Repository, Entity, and DTO layers.  <br>
- Practiced JPA entity relationships such as One-to-Many and Many-to-One.  <br>
- Developed business logic beyond basic CRUD operations.  <br>
- Worked with derived query methods, database persistence, and transaction management.  <br>
- Implemented validation, custom exceptions, and REST API response handling.  <br>

## Project Status
<br>
Backend development completed. Built as a practical project to strengthen Java backend development and Spring Boot skills.
