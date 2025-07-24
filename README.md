# Transportation Management System Backend

This backend system is designed for logistics companies seeking to streamline daily operations related to order creation, delivery coordination, and document generation. It is built using Java and Spring Boot with a multi-layered architecture that promotes clean design, modularity, and secure access control.

## Purpose

The primary goal of this application is to eliminate the manual, fragmented processes that dominate small-to-medium logistics companies. It enables real-time tracking of transportation tasks, improves communication between customers and drivers, and automates the creation of essential shipping documents such as consignment notes.

Many logistics operations in Kazakhstan and neighboring regions still rely on outdated tools or disjointed software, leading to inefficiencies, errors, and delays. This system addresses that gap by offering a modern, integrated backend platform that can serve as the foundation for a scalable and full-featured transportation management system.

## Why This System?

Compared to existing regional platforms like **fafa.kz**, **della.kz**, and **ati.su**, this system offers several architectural and functional advantages:

### fafa.kz, della.kz, ati.su – Limitations

No User Interface (UI)
The application currently lacks a web or mobile frontend. It is designed to expose REST APIs and requires integration with a client application to be used interactively.

No Real-Time Notifications
Features such as email, SMS, or in-app notifications for order updates and delivery status changes are not yet implemented.

No Document Export (PDF/Excel)
Although consignment generation is supported at the data level, export functionality (e.g., to PDF or Excel) has not yet been added.

No Admin or Dispatcher Role
Only customer and driver roles are implemented. Dispatcher or administrator functionalities (e.g., overseeing all orders, assigning drivers) are planned for future versions.

No Payment or Billing Integration
The system does not yet support financial operations such as invoicing, payment tracking, or cost estimation.

Limited Error Reporting and Logging
While basic exception handling exists, detailed operational logging, auditing, and centralized error tracking have not been integrated.

Deployment and Scalability Features Not Included
There is no out-of-the-box support for containerization (e.g., Docker), CI/CD pipelines, or horizontal scaling infrastructure.



### This System – Advantages

- **True multi-role access**: Customers and drivers interact with the system based on secure role-based logic enforced at both the controller and service layers.
- **Order lifecycle management**: Allows delivery statuses to change in a controlled and validated way depending on the user's role.
- **Document-ready**: Consignments are generated based on actual order data, ready for further extension into PDF generation.
- **Ownership and access validation**: No user can update or access entities they do not own, which is especially crucial for sensitive data such as order records and financial documents.
- **Clean, layered architecture**: Built using Spring Boot’s multi-tier architecture separating controllers, services, repositories, DTOs, mappers, and entities for scalability and maintainability.

## Key Features

### Logistics Workflow Automation

- Manage and track shipment orders from placement to delivery
- Validate delivery status transitions based on user role and previous state
- Assign vehicles to drivers dynamically
- Generate consignment records from actual order data

### Role-Based Access Control

- Drivers can only see and update their assigned orders
- Customers can only interact with their own shipment records
- All operations are validated using a centralized authorization utility

### Order Status Transitions

- Transitions follow a strict model (e.g., `PENDING → GOING_TO_LOAD → IN_TRANSIT → DELIVERED`)
- Only customers or drivers with ownership can perform transitions
- Illegal transitions throw descriptive exceptions for developer clarity

### Extensible Document System

- Every order can produce a corresponding consignment
- Future extension includes PDF or Excel export of delivery reports

## Technologies Used

| Layer / Feature         | Technology                                |
|-------------------------|--------------------------------------------|
| Programming Language    | Java 17+                                   |
| Framework               | Spring Boot 3.x                            |
| Security                | Spring Security, JWT                       |
| Persistence             | Spring Data JPA (Hibernate)                |
| Database                | PostgreSQL                                 |
| Testing                 | JUnit 5, Mockito                           |
| Build Tool              | Maven                                      |
| Authentication Utility  | Custom `AuthUtil` with role resolution     |


## System Modules

- **Order Module**: Handles full shipment lifecycle from placement to delivery
- **Customer Module**: CRUD operations for customer data and their orders
- **Driver Module**: Vehicle assignment, order handling, delivery updates
- **Vehicle Module**: Register, assign, and manage freight vehicles
- **Consignment Module**: Generate official shipping records
- **Security Module**: Role-based access validation, authentication context

## Limitations

While the system provides strong backend infrastructure, it currently operates as a prototype. Some limitations include:

- No web frontend or mobile client is yet integrated
- No payment processing or invoicing logic is included
- PDF document generation is planned but not implemented
- Real-time notifications (e.g., via SMS or email) are not active

Despite these limits, the codebase is ready for extension, and the architecture supports rapid future development with minimal refactoring.

## How This Simplifies Logistics

- Reduces manual tracking of orders and deliveries
- Eliminates unauthorized changes through strict ownership checks
- Accelerates generation of shipping documents
- Lowers communication overhead between drivers, logisticians, and clients
- Supports modern security practices and modular scalability

## Conclusion
This application serves as a foundational backend for logistics digitalization. It can significantly improve operational transparency, accountability, and efficiency for transportation firms. With support for automated order tracking, role-specific logic, and document management, it aims to go beyond what regional platforms currently offer.
