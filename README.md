# Project PaymentProcessor
A brief summary of the project purpose, stack and links !

## Stack
- Java, Spring Boot, Maven
- Database: H2 (at least for a while)
- Test: JUnit + Mockito

## Quick start
1. Configure `application.yml`
2. `mvn clean package`
3. `java -jar target/app.jar`

## Directories structure
- `src/main/java/com/paymentprocessor/domain` — entities and rules
- `src/main/java/com/paymentprocessor/application` — use cases and controllers
- `src/main/java/com/paymentprocessor/repositories` — database interface
- `src/main/java/com/paymentprocessor/events` — events and listeners

## Overall
Layered architecture following Clean Architecture/DDD
Application -> Domain -> Infraestructure (Repositories)

## Boundaries
- `TransactionUseCase` guide the app flow (application).
- `TransactionDomainService` contains the actual transaction (`@Transactional`).
- Events published with immutable values (ids, values, timestamp).

## Choices and trade-offs
- Balance updates are processed inside of the transaction to guarantee atomicity!
- Notifications occurs after the transaction with `AFTER_COMMIT`

## Main Endpoints
- `POST /transfer` — create transaction
  - Request: `{ "senderId": 1, "receiverId": 2, "amount": 100.00 }`
  - Response: `201`, `TransactionDTO`
 
- `POST /users/create` — create user
  - Request: `{ "name": "John Doe", "document": "212", "email": "john@rock.com" }`
  - Response: `201`, `User`
