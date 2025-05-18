# Mobility System

A Java-based mobility system that manages vehicles, stations, and users for a shared mobility service.

## Project Structure

```
src/
├── controller/
│   └── MobilitySystem.java       # Main system controller
├── model/
│   ├── locations/
│   │   ├── Location.java         # Base location class
│   │   └── Station.java          # Station class for vehicle docking
│   ├── people/
│   │   ├── Person.java           # Base person class
│   │   ├── PeopleManager.java    # Manages all people in the system
│   │   ├── admin/
│   │   │   └── Admin.java        # Admin user class
│   │   ├── members/
│   │   │   └── Member.java       # Member user class
│   │   └── workers/
│   │       ├── Worker.java       # Base worker class
│   │       ├── Mechanic.java     # Mechanic worker class
│   │       └── FieldOperator.java # Field operator worker class
│   ├── prices/
│   │   └── Pricing.java          # Pricing rules and calculations
│   ├── trips/
│   │   ├── Trip.java            # Trip class
│   │   └── TripsManager.java    # Manages all trips
│   └── vehicles/
│       ├── Vehicle.java         # Base vehicle class
│       ├── VehiclesManager.java # Manages all vehicles
│       ├── Bicycle.java         # Bicycle vehicle class
│       ├── Scooter.java         # Scooter vehicle class
│       ├── SmallMotorcycle.java # Small motorcycle class
│       └── BigMotorcycle.java   # Big motorcycle class
├── utils/
│   └── MockGenerator.java       # Generates mock data for testing
├── view/
│   ├── VAdmin.java             # Admin view
│   ├── VInitial.java           # Initial view/menu
│   ├── VMember.java            # Member view
│   └── VWorker.java            # Worker view
└── movilidad.java              # Main application entry point
```

## Key Features

### Vehicle Management
- Support for different vehicle types (Bicycles, Scooters, Motorcycles)
- Vehicle status tracking (battery, repair needs, location)
- Vehicle assignment to workers

### Station Management
- Station capacity management
- Location tracking
- Support for different vehicle types

### User Management
- Different user roles (Admin, Member, Worker)
- Worker types (Mechanic, Field Operator)
- Balance management for members

### Trip Management
- Trip creation and tracking
- Cost calculation based on duration and vehicle type
- Location validation
- Balance checks

### Worker Operations
- Vehicle repair
- Vehicle movement
- Assignment management

### Member Operations
- Trip booking
- Balance management
- Vehicle issue reporting

## Usage

1. Compile the project:
```bash
javac -d bin src/**/*.java src/movilidad.java
```

2. Run the application:
```bash
java -cp bin src/movilidad
```

3. Login as:
   - Admin: Manage system, view reports
   - Worker: Handle vehicle maintenance and movement
   - Member: Book trips and manage account

## Features

- User Management (Standard and Premium users)
- Vehicle Rental System
- Station Management
- Battery Management
- Trip Tracking
- Premium User Benefits
- Mock Data Generation for Testing

## Getting Started

1. Compile the Java files:
   ```bash
   javac -d bin src/model/*.java src/controller/*.java movilidad.java
   ```

2. Run the application:
   ```bash
   java -cp bin movilidad
   ```

## Available Options

### Main Menu
1. Login
2. Generate Mock Data
3. Save Data
4. Exit

### User Features

#### Standard Users
- View available vehicles
- Rent a vehicle
- End trip
- View trip history
- View balance
- Add balance
- Report vehicle issues
- Find nearest vehicle

#### Premium Users (Additional Features)
- 20% discount on trips
- Vehicle reservation
- Lower battery level requirements
- Priority access to vehicles

## Vehicle Types

1. Electric Motorcycle
   - Battery consumption: 0.5% per unit of distance
   - Can be left anywhere within city limits
   - Minimum battery level: 20% (Standard), 10% (Premium)

## City Layout

- The city is represented as a grid with coordinates (x, y)
- Default city limits: 100x100 units
- Electric motorcycles can operate anywhere within city limits
- Other vehicles must start and end at stations

## Battery Management

- Standard users can only rent vehicles with battery level ≥ 20%
- Premium users can rent vehicles with battery level ≥ 10%
- Battery consumption varies by vehicle type
- Vehicles can be charged at stations

## Additional Notes

- Monetary values are in Euros (€)
- Mock data includes sample stations, vehicles, and users
- Vehicle locations are tracked in real-time
- Stations have limited capacity for vehicle storage 

