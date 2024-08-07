Computer Store REST API Application
Project Overview

This project is a backend application for a computer store that sells various types of computer products, including:

    Desktop Computers
    Laptops
    Monitors
    Hard Drives

Each product type has specific attributes:

    Desktop Computers: Form factors such as desktops, nettops.
    Laptops: Screen sizes of 13, 14, 15, and 17 inches.
    Monitors: Display diagonal size.
    Hard Drives: Storage capacity.

The application provides RESTful HTTP methods to manage the inventory, including:

    Adding a product.
    Editing a product.
    Viewing all products by type.
    Viewing a product by its identifier.

Technology Stack

    Programming Language: Java
    Framework: Spring Boot
    Database: In-memory H2 database
    Build Tool: Maven

Installation
Prerequisites

Ensure that you have the following installed on your system:

    Java Development Kit (JDK) 8 or higher
    Apache Maven

Steps to Set Up the Project

    git clone [<repository-url>](https://github.com/keksmd/REST_API_SHOP/)
    cd <project-directory>
    mvn clean install
    java -jar /out/artifatcs/REST_API_SHOP.jar
    
Usage

Once the application is running, you can interact with the REST API using tools like Postman or curl.
API Endpoints

For Each of Monitors,Desktop Computers (PK),Laptops,Harddisk there is 4 end-points:

    POST /api/v1/{}: Add a new.
    POST /api/v1/{}/{id}: Update an existing by ID.
    GET /api/v1/{}: View all.
    GET /api/v1/{}/{id}: View a specific by ID.
    
    choose one product of {monitor},{harddisk},{pk},{laptop} and insert instead of {}


Add a {Product}

```
POST /api/v1/{product}
Request Body: JSON object with product details.
```
      
Edit a {Product}

  ```
  PUT /api/v1/{product}/{id}
  Path Parameter: id - The unique identifier of the product.
  Request Body: JSON object with updated product details.
```

View All {Products} by Type

  ```
  GET /api/v1/{product}
  Query Parameter: productType - The type of products to view (e.g., "desktop", "laptop", "monitor", "harddrive").
  ```
      
View a {Product} by ID

  ```
  GET /api/v1/{product}/{id}
  Path Parameter: id - The unique identifier of the product.
  ```

Configuration
    In-Memory Database (H2)
The application uses the H2 in-memory database by default. No additional configuration is required for the database.
