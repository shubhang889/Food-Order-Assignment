# Problem 3: Delivery Partner Assignment API

## Problem Statement
Implement a Delivery Partner Assignment System to allocate orders to nearby available delivery partners.

---

## Requirements

### Partner Management
- **POST /partners** → Register a new partner (`id`, `name`, `status`, `location`)  
- **PUT /partners/{id}/status** → Update status (`AVAILABLE` / `BUSY`)

### Order Assignment
- **POST /orders/assign** → Assign a delivery partner to a given order  
- Choose the nearest available partner based on pickup location  
- Mark that partner as **BUSY**  
- **POST /orders/{orderId}/complete** → Mark order delivered and partner **AVAILABLE** again

---

## Expectations
- In-memory tracking of orders and partners  
- Distance calculation can be mocked (e.g., absolute difference of coordinates)  
- Clean modular design (`PartnerService`, `OrderService`, `Controller`)  
