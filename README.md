# Transportation Management System Backend

This backend system is designed for logistics companies seeking to streamline daily operations related to order creation, delivery coordination, and document generation. It is built using Java and Spring Boot with a multi-layered architecture that promotes clean design, modularity, and secure access control.

## Purpose

The primary goal of this application is to eliminate the manual, fragmented processes that dominate small-to-medium logistics companies. It enables real-time tracking of transportation tasks, improves communication between customers and drivers, and automates the creation of essential shipping documents such as consignment notes.

Many logistics operations in Kazakhstan and neighboring regions still rely on outdated tools or disjointed software, leading to inefficiencies, errors, and delays. This system addresses that gap by offering a modern, integrated backend platform that can serve as the foundation for a scalable and full-featured transportation management system.

## Why This System?

Compared to existing regional platforms like **fafa.kz**, **della.kz**, and **ati.su**, this system offers several architectural and functional advantages:

### fafa.kz, della.kz, ati.su – Limitations

Limitations of Existing Platforms (fafa.kz, della.kz, ati.su)
Popular platforms like fafa.kz, della.kz, and ati.su are widely used in Kazakhstan and CIS countries for cargo matching and logistics coordination. However, they are primarily built around marketplace-style listings, and have critical limitations when it comes to automating and managing end-to-end logistics workflows. Below are the key limitations of these platforms that your system addresses:

1. Lack of End-to-End Workflow Automation
   These platforms focus mainly on listing and searching for transport offers or cargo. They do not provide a structured backend workflow for managing an order from creation to final delivery, including role-specific transitions, document generation, or vehicle assignment.

2. No True Role-Based Logic
   All user roles (drivers, customers, dispatchers) typically operate in the same interface with limited separation. There is no enforcement of ownership at the API or business logic level, which increases the risk of data exposure and unauthorized actions.

3. No Delivery Lifecycle Tracking
   They do not support controlled status transitions like PENDING → IN_TRANSIT → DELIVERED, nor do they validate the roles allowed to perform these changes. Drivers and customers cannot reliably track the status or progression of their deliveries through defined lifecycle states.

4. No Automated Document Handling
   These platforms do not offer backend support for generating official transport documents such as consignment notes based on real-time order data. Users must handle this manually or through external systems.

5. Limited Integration Potential
   Due to their monolithic and often closed architectures, integration with third-party systems (e.g., accounting, CRM, GPS tracking, or warehouse management) is difficult or unsupported. Your system, on the other hand, is modular and API-driven, enabling future integrations.

6. No Extensibility for Logistics Teams
   Logistics companies cannot expand these platforms to include custom workflows, dashboards, role hierarchies, or analytics. Your backend is designed to be extendable, with separate modules for services, entities, DTOs, and role-based access control.

7. Manual Operations Remain the Norm
   Dispatching, consignment generation, delivery confirmation, and status updates must be done manually or via communication channels outside the system. This introduces inefficiencies and reduces accountability.

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

- Does not have frontend part created
- Consignments still created manually by writing some details
- Balance of users are fictional and not applicable in real world scenarios
- There could arise problems from both sides(customer, driver) leading to cancellation of order 
- Some bugs to be handled

### This System – Advantages


Despite these limits, the codebase is ready for extension, and the architecture supports rapid future development with minimal refactoring.

## How This Simplifies Logistics

- Reduces manual tracking of orders and deliveries
- Eliminates unauthorized changes through strict ownership checks
- Accelerates generation of shipping documents
- Lowers communication overhead between drivers, logisticians, and clients
- Supports modern security practices and modular scalability

## Conclusion
This application serves as a foundational backend for logistics digitalization. It can significantly improve operational transparency, accountability, and efficiency for transportation firms. With support for automated order tracking, role-specific logic, and document management, it aims to go beyond what regional platforms currently offer.
