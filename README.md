# Cinema Ticket Service – Coding Exercise

## Overview

This project implements a `TicketService` for a cinema ticketing system. It adheres to the business rules and constraints provided in the coding exercise brief. The solution is written in **Java 21** and demonstrates clean, testable, and maintainable code.

## Business Rules Implemented

- Supports three ticket types: **INFANT**, **CHILD**, and **ADULT**.
- **INFANT** tickets are free and do not require a seat.
- **CHILD** tickets cost £15 and require a seat.
- **ADULT** tickets cost £25 and require a seat.
- A maximum of **25 tickets** can be purchased at once.
- **CHILD** and **INFANT** tickets **must be accompanied by at least one ADULT** ticket.
- Validates account ID (must be greater than zero).
- Calculates total payment and seat reservation accurately.
- Calls external services:
  - `TicketPaymentService` for payment processing.
  - `SeatReservationService` for seat allocation.

## Technologies Used

- Java 21
- JUnit 5 for unit testing
- Maven for build and dependency management

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/cinema-tickets.git
   cd cinema-tickets
