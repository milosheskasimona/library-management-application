# Library Management Application

## Table of Content
- [Project Description](#project-description)
- [Functionalities](#functionalities)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Security](#security)
- [Feature Plans](#feature-plans)

## Project Description

Library Management Application is an application which, as the name suggests for internal library management.
It should allow librarians to store information on an electronic device rather than on paper.
Librarians should have the possibility to register authors, add books from that authors, register clients, 
create orders and gather information regarding books, clients,and orders in an easier and faster manner.
The main goal is to give the librarian the ability to create an order for a book and assign it to a client.
Librarians should be able to retrieve a list of all placed orders along with information for each.

## Functionalities

Below are the main features provided by this application:

#### Authentication
`authenticateUser(AuthenticationRequest request)`: Authenticates a user and generates an authentication token.<br>
`registerLibrarian(AuthenticationRequest request)`: Registers a new librarian with the provided authentication details.<br>
`registerAdmin(AuthenticationRequest request)`: Registers a new administrator with the provided authentication details.<br>
#### Book Management
`getAllBooks()`: Retrieve a list of all books available in the library.<br>
`findBookById(Integer id)`: Retrieve detailed information about a book based on its ID.<br>
`findAllBooksByAuthor(Author author)`: Retrieve all books written by a specific author.<br>
`saveBook(BookRequest bookRequest)`: This method allows adding a new book to the library. 
If a book with the same name already exists, it will only be added as a new entry if the author is different,
otherwise, the quantity of the existing book can be increased<br>
`addBookCopy(Integer id)`: Increasing the quantity of specific book. <br>
`deleteBookById(Integer id)`: Delete a book from the library based on its ID.<br>
`editBookById(Integer id, BookRequest bookRequest)`: Edit and update book information based on its ID.<br>
#### Author Management
`getAllAuthors()`: Retrieve a list of all authors in the library.<br>
`findAuthorById(Integer id)`: Retrieve detailed information about an author based on their ID.<br>
`saveAuthor(AuthorRequest authorRequest)`: Create a new author with the provided details.<br>
`deleteAuthorById(Integer id)`: Delete an author from the system based on their ID.<br>
`editAuthorById(Integer id, AuthorRequest authorRequest)`: Edit and update author information based on their ID.<br>
#### Client Management
`getAllClients()`: Retrieve a list of all registered clients in the library.<br>
`findClientById(Integer id)`: Retrieve detailed information about a client based on their ID.<br>
`saveClient(ClientRequest clientRequest)`: Register a new client in the library. Email address must be unique.<br>
`deleteClientById(Integer id)`: Remove a client from the library based on their ID.<br>
`editClientById(Integer id, ClientRequest clientRequest)`: Edit and update client information based on their ID.
#### Order Management
`getAllOrders()`: Retrieve a list of all orders in the library. <br>
`getOrdersByClientId(int clientId)`: Retrieve a list of orders based on the client ID. <br>
`getOrdersByIssueDate(String date, boolean flag)`: Retrieve a list of orders based on the issue date and a flag indicating whether to retrieve orders before or after the specified date. <br>
`getOrdersByDueDate(String date, boolean flag)`: Retrieve a list of orders based on the due date and a flag indicating whether to retrieve orders before or after the specified date. <br>
`createOrder(OrderRequest orderRequest)`: Create a new order with the provided request details. <br>
## Technologies
Project is created with:
* Java 11
* Spring Boot 
* Spring Data 
* Spring Security 
* JUnit 4
* Maven 3

## Getting Started

To run Library Management Application on your local system, follow these instructions:

* Clone the repository: git clone https://github.com/milosheskasimona/library-management-application.git
* Navigate to the project directory: cd library-management-application
* Install dependencies: npm install
* Configure the database: Ensure a compatible database is set up and modify the configuration file accordingly. <br>
DDL Script for the database is located at: src/main/resources/LibraryManagementDDL.sql <br>
Also you need to set up properties for the database at: src/main/resources/application-postgre.properties 
* Start the application: npm start
* Access the application in your browser at http://localhost:8081.

## API Documentation
The Project API provides a set of endpoints to perform various operations related to
supermarket management system. The API supports JSON data formats and requires authentication using a Cookies
### Base URL
The base URL for all API endpoints is `http://localhost:8081`

### Endpoints

| Endpoint Description                    | URL                           | Method Type | Authentication   |
|-----------------------------------------|-------------------------------|-------------|------------------|
| Get all authors                         | /authors                      | GET         | LIBRARIAN, ADMIN |
| Get author details by ID                | /authors/{id}                 | GET         | LIBRARIAN, ADMIN |
| Update author details by ID             | /authors/{id}                 | PUT         | LIBRARIAN, ADMIN |
| Delete an author by ID                  | /authors/{id}                 | DELETE      | ADMIN            |
| Add a new book to the library           | /books                        | POST        | LIBRARIAN, ADMIN |
| Get book details by ID                  | /books/{bookId}               | GET         | LIBRARIAN, ADMIN |
| Update book details by ID               | /books/{bookId}               | PUT         | LIBRARIAN, ADMIN |
| Update book copies                      | /books/{bookId}               | PATCH       | LIBRARIAN, ADMIN |
| Delete a book by ID                     | /books/{bookId}               | DELETE      | ADMIN            |
| Register a new client                   | /clients                      | POST        | LIBRARIAN        |
| Get client details by ID                | /clients/{id}                 | GET         | LIBRARIAN        |
| Update client details by ID             | /clients/{id}                 | PUT         | LIBRARIAN        |
| Delete a client by ID                   | /clients/{id}                 | DELETE      | ADMIN            |
| Create a new order                      | /orders                       | POST        | LIBRARIAN        |
| Get all orders                          | /orders                       | GET         | LIBRARIAN        |
| Get orders by client ID                 | /clients/{clientId}/orders    | GET         | LIBRARIAN        |
| Get orders before a specific issue date | /orders (params: beforeIssue) | GET         | LIBRARIAN        |
| Get orders after a specific issue date  | /orders (params: afterIssue)  | GET         | LIBRARIAN        |
| Get orders before a specific due date   | /orders (params: beforeDue)   | GET         | LIBRARIAN        |
| Get orders after a specific due date    | /orders (params: afterDue)    | GET         | LIBRARIAN        |
| Get authentication token for login      | /login                        | GET         | Not required     |
| Register a new admin                    | /admin                        | POST        | Not required     |
| Register a new user                     | /user                         | POST        | Not required     |
| Register a new librarian                | /librarian                    | POST        | Not required     |
## Security
The security aspect of our Library Management Application has been 
designed and implemented using Spring Security with JWT (JSON Web Token) and cookies.
### JWT Token and Cookies
Integrated JSON Web Tokens (JWT) to handle secure authentication and authorization.
When users log in, they receive a JWT token, which is then stored as a secure HttpCookie.
This token ensures that only authenticated users can access protected endpoints and perform authorized actions within the application.
### Role-Based Access Control
The application implements two primary roles: ADMIN and LIBRARIAN.
Each role has specific access privileges to perform relevant operations.
ADMIN role grants full control over the system, including managing users, authors, books, and orders.
LIBRARIAN role allows essential library management tasks, such as adding books, creating orders, and managing clients.
Once registered user, can log in using their credentials, and upon successful authentication,
they gain access to their respective roles and associated functionalities.
## Feature Plans
### Online Access and Client Registration
The main focus is to make the library management application accessible online, enabling clients to register themselves on the platform.
Clients will have the convenience of accessing the application remotely to browse and borrow eBooks from the library's digital collection.
<br>
### Author Registration and eBook Addition
To enhance the collection of eBooks, we will introduce an author registration feature.
Authors will have the opportunity to create accounts and contribute their eBooks directly to the library's repository.
This will empower authors to showcase their work and expand the digital offerings of the library.
