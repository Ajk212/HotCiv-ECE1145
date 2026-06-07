# HotCiv

## Overview

HotCiv is a Java implementation of a simplified version of the Civilization strategy game series created by Sid Meier. This project was developed as part of a software engineering course and built incrementally through a series of iterations. Each iteration introduced new game mechanics while demonstrating key software design principles such as abstraction, polymorphism, the Strategy pattern, and test-driven development.

The goal of the project was to create a flexible and extensible game engine capable of supporting multiple rule sets without requiring significant changes to the core architecture.

---

## Key Features

- Turn-based strategy gameplay  
- Unit movement and combat  
- City production and growth  
- Multiple victory conditions  
- Flexible rule configuration  
- Extensive automated test suite (JUnit)  
- Modular and extensible architecture  

---

## Testing

A major focus of the HotCiv project is its **extensive automated testing suite**, which was developed alongside the implementation using **Test-Driven Development (TDD)** principles.

### Testing Highlights

- Comprehensive JUnit test coverage for all game versions (AlphaCiv through SemiCiv)
- Unit tests validating:
  - Unit movement and combat behavior  
  - City ownership and production rules  
  - Turn progression and game state transitions  
  - Victory conditions across different game variants  
- Regression testing to ensure new features do not break existing functionality  
- Isolated testing of individual components through well-defined interfaces  

### Design Impact

The testing strategy strongly influenced the architecture of the system:

- Encouraged loose coupling between components  
- Required clear and stable interfaces between game systems  
- Enabled safe refactoring across multiple iterations  
- Supported verification of each incremental game version  

The test suite serves not only as validation, but also as documentation of expected game behavior.

---

## Project Evolution

### AlphaCiv

AlphaCiv establishes the baseline game mechanics and provides the foundation for all future versions.

**Features:**
- Fixed world layout  
- Red and Blue players  
- Fixed city ownership  
- Basic unit definitions  
- Simple turn management  
- Fixed victory condition  
- Winner determined at a specified year  

---

### BetaCiv

BetaCiv introduces alternate victory conditions and world aging mechanics.

**Features:**
- Victory based on conquest of all cities  
- Turn progression advances the game calendar  

---

### GammaCiv

GammaCiv introduces new unit actions and expanded gameplay mechanics.

**Features:**
- Settler units can found new cities  
- Archer units can fortify their positions  

---

### DeltaCiv

DeltaCiv introduces an alternative world layout.

**Features:**
- 16x16 world grid  
- Predefined city placement for both players  

---

### EpsilonCiv

EpsilonCiv introduces combat mechanics and a new victory condition.

**Features:**
- Units can attack other units and cities  
- First player to win three battles is declared the winner  

---

### ZetaCiv

ZetaCiv demonstrates dynamic and interchangeable victory conditions.

**Features:**
- Starts with AlphaCiv win conditions  
- After 20 rounds, switches to EpsilonCiv-style victory conditions  
- Demonstrates flexible rule composition  

---

### SemiCiv

SemiCiv combines advanced features from multiple versions into a unified implementation.

**Features:**
- BetaCiv world aging system  
- GammaCiv unit actions  
- DeltaCiv world layout  
- EpsilonCiv combat and victory conditions  

---

## Design Principles

This project demonstrates several core software engineering principles:

- **Strategy Pattern:** Used for interchangeable game rules such as victory conditions and world aging  
- **Factory Pattern:** Used to create game objects and support multiple game variants  
- **Polymorphism:** Enables multiple implementations behind shared interfaces  
- **Test-Driven Development:** Development guided by automated JUnit tests written alongside features  

---

## Technologies Used

- Java  
- JUnit  
- Git  
- Object-Oriented Programming  
- Design Patterns (Strategy, Factory)  
- Test-Driven Development  

---

### Requirements
- Java 17 or later  
- IntelliJ IDEA (recommended)  

---
