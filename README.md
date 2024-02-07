# <p align="center"> 📚 Online Book Store 📚
This project aims to simplify the path to literary enjoyment by providing users with access to a wide range of books in a convenient online format.📱 For the convenience of our users, was created an intuitively understandable interface that allows easy browsing of books🔎 , adding them to the cart🛒, and managing orders📋. Additionally, administrators have the ability to enrich the catalog with new books📖 and perform deletion operations, ensuring the relevance and diversity of the assortment📜.
## 🔧 Technologies and tools used
### 🔩 Spring Framework:
  - Spring Boot
  - Spring Data JPA
  - Spring Boot Security
  - Spring Boot Validation
  - Spring Boot Docker
### 🔩 Core Technologies:
  - Java
  - Maven
### 🔩 Auxiliary Libraries:
  - Lombok
  - MapStruct
### 🔩 Database and Persistence:
  - MySQL
  - Hibernate
  - Liquibase
### 🔩 Testing:
  - JUnit 5
  - Mockito
  - Docker
  - Test Containers
### 🔩 API Documentation:
  - Swagger
---

# 💻 Project endpoints:
### 🔑 Authentication Controller - endpoints for user registration and authentication of registered users.

| HTTP method |       Endpoint        |           Description           |
|:-----------:|:---------------------:|:-------------------------------:|
|    POST     | `/auth/registration ` |       Register a new user       |
|    POST     |    `/auth/login `     |   Login as a registered user    |

---
### 📔 BookController - endpoints for managing books.
| HTTP method |    Endpoint    | Role  |    Description    |
|:-----------:|:--------------:|:-----:|:-----------------:|
|     GET     |   `/books `    |  ALL  |   Get all books   |
|     GET     | `/books/{id}`  |  ALL  |  Get book by Id   |
|    POST     |   `/books `    | ADMIN |  Save a new book  |
|   DELETE    | `/books/{id} ` | ADMIN | Delete book by Id |
|     PUT     | `/books/{id} ` | ADMIN | Update book by Id |

---
### 📑 Category Controller - endpoints for managing categories.
| HTTP method |    Endpoint    | Role  |           Description            |
|:-----------:|:--------------:|:-----:|:--------------------------------:|
|     GET     |   `/categories `    |  ALL  | Get all categories |
|     GET     | `/categories/{id}`  |  ALL  |        Get category by Id        |
|     GET     | `/categories/{id}/books`  |  ALL  |   Get all books by category Id   |
|    POST     |   `/categories`    | ADMIN |         Save a new category        |
|   DELETE    | `/categories/{id} ` | ADMIN |        Delete category by Id         |
|     PUT     | `/categories/{id} ` | ADMIN |        Update category by Id         |

---
### 🛒 Shopping Cart Controller - endpoints for managing shopping cart.
| HTTP method |              Endpoint              | Role  |           Description            |
|:-----------:|:----------------------------------:|:-----:|:--------------------------------:|
|    POST     |             `/cart `             |  USER  | Add book to shopping cart |
|     GET     |     `/cart`    |  USER  |       Get shopping cart with books        |
|   DELETE    | `/cart/cart-items/{cartItemId}` | USER |       Delete book from shopping cart    |
|     PUT     |          `/cart/cart-items/{cartItemId}`          | USER |       Update book quantity in shopping cart        |

---
### 📜 Order Controller - endpoints for managing orders.

| HTTP method |              Endpoint              | Role  |           Description            |
|:-----------:|:----------------------------------:|:-----:|:--------------------------------:|
|     GET     |             `/orders `             |  USER  | Get order history |
|     GET     |     `/orders/{orderId}/items`      |  USER  |        Get order items by order id        |
|     GET     | `/orders/{orderId}/items/{itemId}` | USER |        Get order item by order and item id    |
|    POST     |          `/orders`          | USER |        Place a new order and clear the cart        |
|    PATCH    |          `/orders/{id} `           | ADMIN |       Update order status       |

---
## 🚀 How to run the project?
  - Start by making a local copy of the project's repository
  - Create the .env file with the corresponding variables
  - Build images using docker-compose build and run the service in containers using docker-compose up
  - Start the application and have a good use

## 💪 Challenges and Solutions
  - Understanding the project architecture: It was my first time dealing with such a large-scale project. It was challenging to piece everything together, comprehend the interrelation of each part, and integrate them effectively. However, with consistent effort, extensive research, and consultations with seasoned professionals, I managed to establish a suitable structure. Furthermore, grappling with Spring Security proved to be a formidable task. Despite the complexity, I eventually grasped its configuration for application integration. While encountering significant challenges, I must acknowledge that creating this project was an immensely gratifying experience. I eagerly look forward to further opportunities for practice and refinement.
---
## 🌟 Possible improvements
  1. Implement Payment Using Stripe API:
     - Enable payment functionality by integrating the Stripe API. Implement secure and seamless transactions, leveraging Stripe's robust payment infrastructure. Ensure adherence to industry standards for payment security and provide a smooth user experience throughout the payment process.
  2. Real-time Notifications:
     - Build a Telegram bot for efficient notification delivery. Implement a notification system to update users on order status, promotions, and new arrivals.
---
