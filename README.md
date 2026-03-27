***

# Bookstore CLI - Lecture 7 (Winter 2026)
> **Baseline Codebase for Lecture 7: Services, IoC, & Dependency Injection**

[**Click here for the Lecture 7 Notes**](https://docs.google.com/document/d/1CNWeKsbrgE8uDYgdh7PUsoQvBi3bSh4NAqlRU6gNyZk/edit?usp=sharing)

This repository demonstrates the final stage of "decoupling" in a raw Java application. We move away from tight coupling where the application creates its own dependencies, and instead move to an **Inversion of Control (IoC)** model where dependencies are **Injected**.

## 🎯 Lecture 7 Learning Objectives
* **The Service Layer:** Implementing the "Chef" of the application to handle business rules.
* **Inversion of Control (IoC):** Moving the "Wiring Phase" out of the application logic and into a central assembler (`Main.java`).
* **Dependency Injection (DI):** Using Constructor Injection to pass repositories into services.
* **Plug-and-Play Architecture:** Swapping between different storage strategies at runtime without changing a single line of business logic.

## 🍽 The Restaurant Metaphor
To understand this architecture, we use the **Chef and Waiter** analogy:
*   **The Waiter (`App.java`):** The Presentation Layer. Takes orders (User Input) but doesn't know how to cook.
*   **The Pantry (`IRepository`):** The Data Access Layer. Holds the ingredients (Data) but doesn't know the recipes.
*   **The Chef (`BookstoreService`):** The Business Logic Layer. Receives the order from the Waiter, gets ingredients from the Pantry, applies the recipe (rules), and hands the result back.

## 🛠 Supported Storage Strategies
This codebase allows you to "plug in" different Pantries into the Chef at startup:
1.  **In-Memory (ArrayList):** A volatile storage strategy using standard Java collections.
2.  **H2 Database:** An in-memory SQL engine that runs inside your app (No Docker required!).
3.  **MySQL Database:** Persistent production storage (Requires Docker).

---

## 🚀 How it Works (The IoC Container)
The logic for choosing the repository has been moved to **`Main.java`**. When you start the application, you will see a prompt:

```text
Select Data Persistence Strategy:
1. In-Memory (ArrayList)
2. H2 Database (In-Memory SQL)
3. MySQL Database (Production)
```

Once you choose, `Main.java` instantiates the specific repository class and **injects** it into the `App` and `Service` layers via their constructors. The `App` layer never uses the `new` keyword to create a repository.

---

## 📋 Prerequisites
* **Java JDK:** 24
* **Maven:** 3.9+
* **Docker:** (Optional) Only required if choosing the MySQL strategy.

## 🚦 Getting Started

### 1. (Optional) Start Docker
If you intend to use Option 3 (MySQL):
```bash
docker-compose up -d
```

### 2. Compile and Run
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="csd214.bookstore.Main"
```

---

## 🏗 Project Structure
```text
src/main/java/csd214/bookstore/
├── App.java                 # Presentation Layer (The Waiter)
├── Main.java                # IoC Container (The Assembler/Wiring)
├── entities/                # Database Models
├── pojos/                   # UI Data Transfer Objects
├── services/                # Business Logic Layer (The Chef)
│   └── BookstoreService.java
└── repositories/            # Data Access Layer (The Pantry)
    ├── IRepository.java     # The Contract
    ├── InMemoryListRepository.java
    ├── H2Repository.java
    └── MySqlRepository.java
```

## ⚖️ License
Educational use for CSD214 - Sault College.

## LAB 5 Reflections

1. No, because App.java and services are database agnostic

2. Using a HashMap is more optimal for large datasets because lookups 
and inserts are average O(1), whereas a list based repository requires O(n) scans for findById 
and removals by id. That difference becomes significant as the dataset grows. However, maps trade off memory overhead and hash maintenance.

3. RAM-based repositories are volatile and excellent for fast iteration, unit tests, 
and isolated demos because they are simple, deterministic, and easy to reset. Persistent 
stores like MySQL provide durability, ACID guarantees, backup and recovery, and are 
required for production data. Moving from in-memory to a relational store 
introduces schema management, connection pooling, transaction boundaries, and operational concerns (migrations, backups, monitoring), 
so the repository abstraction protects the app from those complexities until you opt into them.

4. Decoupling via interfaces and DI makes large codebase changes far safer and cheaper.
A single wiring change at startup can replace a core subsystem without touching business
logic, which reduces the scope of code reviews and the likelihood of regression bugs.

5. Modularizing code (separation of concerns and SRP) improves maintainability, testability, 
and onboarding. When repositories, services, and presentation are clearly separated, new contributors can focus on one 
area without understanding the entire stack.