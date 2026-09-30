# 🎭 Restful Booker API Automation Framework

A robust, automated API testing framework built with **Java**, **Rest Assured**, and **TestNG** for validating the [Restful Booker API](https://restful-booker.herokuapp.com/).

---

## 🛠️ Tech Stack & Tools

* **Language:** Java 21
* **API Testing Library:** Rest Assured (5.5.0)
* **Test Runner / Framework:** TestNG (7.12.0)
* **Data Generation:** Java Faker
* **Build Tool:** Maven
* **Reporting:** Allure Reports

---

## 🚀 Key Features

* **End-to-End API Coverage:** Validates CRUD operations (GET, POST, PATCH, DELETE) and Auth Token creation.
* **Dynamic Test Data:** Integrated `Java Faker` to eliminate hardcoded input data.
* **Centralized Base Configuration:** `BaseTest` manages `baseURI`, authentication setup, and common request specifications.
* **Automated Chain Execution:** Utilizes dynamic ID propagation across request sequences to maintain test isolation.

---

## 📁 Project Structure

```text
com.restful-booker/
├── src/
│   └── test/
│       └── java/
│           └── com/packagebooker/
│               ├── BaseTest.java        # Pre-test configurations & Auth token generation
│               ├── BookingTest.java     # E2E Booking workflow execution
│               └── CreateTokenTest.java  # Dedicated Auth endpoint verification
├── pom.xml
└── README.md