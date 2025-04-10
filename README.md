# City Mobility System

A Java-based platform for managing sustainable mobility in a city, including electric motorcycles, bicycles, and scooters.

## Project Structure

```
movilidad-project/
├── src/
│   ├── model/
│   │   ├── User.java
│   │   ├── Vehicle.java
│   │   ├── ElectricMotorcycle.java
│   │   ├── Location.java
│   │   ├── Station.java
│   │   └── Trip.java
│   ├── controller/
│   │   └── MobilitySystem.java
│   ├── view/
│   └── utils/
├── movilidad.java
├── mock_data.txt
└── README.md
```

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