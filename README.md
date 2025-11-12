🛍️ Online E-commerce Portal

An Online E-commerce Portal application built with Spring Boot designed to manage product inventory and facilitate the full shopping experience, from browsing to order placement. This system coordinates actions between Admins (product management) and Users (shopping and purchasing).

✨ Features and Workflow

This application implements a complete e-commerce lifecycle managed across two core user roles:

Admin Functionality (Product Management):

Admins log in to the portal to manage the online store's inventory.

They are responsible for adding new products, including:

Product Name and Description.

Setting the Price.

Uploading Images for visualization.

Managing product categories and stock levels.

User/Customer Functionality (Shopping & Purchasing):

Users can register and log in to the portal.

They can browse all products, prices, and images added by the Admin.

If interested, users can move products to their shopping cart.

When ready to finalize the purchase, users proceed to checkout.

While buying, the user is required to provide the delivery address and payment information to complete the order.

Technical Features: Built on Spring Boot for robust backend logic and a clean, responsive front-end framework.

🚀 Getting Started

Follow these steps to set up and run the project on your local machine. You will be using Spring Tool Suite (STS) for development.

📋 Prerequisites

Ensure the following software is installed on your system:

Java Development Kit (JDK) 17+

Apache Maven (for dependency management and building)

MySQL Database (e.g., MySQL Workbench or a similar client)

Key Project Dependencies (Added via STS Initializer):
The project is built using the following core Spring Boot dependencies, which are typically included when creating the project in Spring Tool Suite (STS) or using the Spring Initializr:

Spring Data JPA: Used for interacting with the MySQL database via ORM (Object-Relational Mapping).

Spring Web (MVC): Enables the creation of the web application, handling requests, and defining RESTful endpoints.

MySQL Driver (Connector/J): The JDBC driver necessary to connect the Spring application to the MySQL database.

Spring Boot DevTools: Provides development-time features like automatic application restarts upon code changes.

⚙️ Installation and Setup

1. Database Setup

The first step is to create the necessary database and prepare the schema using MySQL.

Open your MySQL Workbench or command-line client.

Create the required database as specified for this project:

CREATE DATABASE online_ecommerce_portal;
















2. Configuration Update

You need to update the database credentials in the Spring Boot configuration file.

Navigate to the configuration file:

src/main/resources/application.properties
















Find the following lines related to MySQL connection and update them to match your local setup. Ensure the database name matches online_ecommerce_portal.

# Database Configuration Properties
spring.datasource.url=jdbc:mysql://localhost:3306/online_ecommerce_portal
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD_HERE
















3. Build and Run

With the database configured, you can now build and launch the application using Spring Tool Suite (STS).

Open Spring Tool Suite (STS):

Import the project as an existing Maven project.

Run the Application:

Right-click the project in the Project Explorer.

Select Run As -> Spring Boot App.

The application will now start, typically accessible at http://localhost:8080 unless configured otherwise.
