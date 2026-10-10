# Marvellous ParkEngine – Parking Lot Automation System

## 📌 Description

Marvellous ParkEngine is a console-based parking lot management system developed using Java. It automates vehicle parking allocation, ticket generation, vehicle searching, parking fee calculation, payment processing, and parking availability updates across multiple floors.

The project demonstrates Object-Oriented Programming (OOP) concepts and commonly used design patterns.

## ✨ Features

- **Vehicle Management:** Supports bikes, cars, and trucks.
- **Automated Parking Allocation:** Assigns the first available suitable parking spot.
- **Multi-Floor Management:** Manages parking spots across multiple floors.
- **Ticket Generation:** Generates a unique ticket for each parked vehicle.
- **Vehicle Search:** Searches for a parked vehicle using its registration number.
- **Parking Fee Calculation:** Calculates charges based on parking duration and vehicle type.
- **Multiple Payment Methods:** Supports cash, UPI, and card payment options.
- **Real-Time Availability Updates:** Updates the parking display board when spot availability changes.
- **Duplicate Parking Prevention:** Prevents the same vehicle from being parked more than once.
- **Parking Status Display:** Displays occupied and available parking spots.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Core Concepts:** OOP, Collections Framework, Exception Handling, Java Date and Time API
- **Design Patterns:** Factory, Singleton, Observer, and Strategy
- **User Interface:** Command-Line Interface (CLI)

## 🏗️ Design Patterns Implemented

| Design Pattern    | Purpose                                                                       |
| ----------------- | ----------------------------------------------------------------------------- |
| Factory Pattern   | Creates vehicle objects based on vehicle type.                                |
| Singleton Pattern | Maintains a single ParkingLot instance.                                       |
| Observer Pattern  | Updates parking display boards when availability changes.                     |
| Strategy Pattern  | Supports interchangeable parking allocation, pricing, and payment strategies. |

## 📂 Project Structure

```text
Marvellous_ParkEngine/
│
├── ParkEngine.java
├── README.md
└── Screenshots
    ├── 01_Main_Menu.png
    ├── 02_Vehicle_Parking_and_Ticket.png
    ├── 03_Vehicle_Search.png
    ├── 04_Parking_Lot_Status.png
    ├── 05_Vehicle_Exit_and_Payment.png
    └── 06_Parking_Spot_Released.png
```

## ⚙️ Prerequisites

- Java Development Kit (JDK) 11 or later
- Command Prompt or terminal
- A text editor or Java IDE

## ▶️ How to Run

### Step 1: Clone the Repository

```bash
git clone https://github.com/sharvari-bhosale/Java-Projects.git
```

### Step 2: Navigate to the Project Directory

```bash
cd Java-Projects/Marvellous_ParkEngine
```

### Step 3: Compile the Java Program

```bash
javac ParkEngine.java
```

### Step 4: Run the Application

```bash
java ParkEngine
```

Alternatively, with a compatible Java version, run the source file directly:

```bash
java ParkEngine.java
```

## 🖥️ Application Menu

```text
---------------------------------
---- Marvellous ParkEngine ------
---------------------------------
1 : Park Vehicle
2 : Exit Vehicle
3 : Search Vehicle
4 : Display Parking Lot
5 : Exit
```

## 📸 Screenshots

### 1. Main Menu

Displays the available parking management operations.



### 2. Vehicle Parking and Ticket Generation

Demonstrates vehicle selection, automatic spot allocation, and parking ticket generation.



### 3. Vehicle Search

Displays the parking ticket associated with a vehicle registration number.



### 4. Parking Lot Status

Shows available and occupied parking spots across multiple floors.



### 5. Vehicle Exit and Payment

Demonstrates ticket-based exit processing, parking fee calculation, and payment selection.



### 6. Parking Spot Released

Shows the updated parking availability after a vehicle exits successfully.



## 🎯 Learning Outcomes

- Applied OOP principles including abstraction, inheritance, encapsulation, and polymorphism.
- Implemented Factory, Singleton, Observer, and Strategy design patterns.
- Practiced Java Collections, exception handling, and date-time calculations.
- Developed a modular approach to parking allocation, ticket management, and payment processing.

## 🚀 Future Enhancements

- Develop a graphical user interface or web interface.
- Integrate a database for persistent parking records.
- Add support for multiple entry and exit gates.
- Implement concurrent vehicle handling and more advanced parking strategies.
- Integrate a real payment gateway.

## 👩‍💻 Author

**Sharvari Bhosale**


