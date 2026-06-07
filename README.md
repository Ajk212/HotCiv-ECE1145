# HotCiv

## Overview

HotCiv is a Java implementation of simplified version of Sid Mier's Civilization strategy games. This game was developed as a part of a software engineering project. The project was built incrementally through a series of development iterations, each introducing new game mechanics and demonstrating software design principles such as abstraction, polymorphism, strategy patterns, and test-driven development. The objective of the project was to create a flexible game engine capable of supporting multiple rule sets without requiring major changes to the core game implementation.

## Key Features
- Turn based strategy gameplay
- Unit movement and combat
- City production and growth
- Multiple victory conditions
- Flexible rule configuration
- Automated test suite
- Modular and extenable architecture

## Project Evolution

### AlphaCiv
AlphaCiv is used to establish the baseline of the game and provide a state to build off of
#### Features
- Fixed world layout
- Red and Blue players
- Fixed city ownership
- Defined units
- Simple turn managment
- Fixed victory condition
- Winner determined at specified year

### BetaCiv
BetaCiv introduces the variation of victory conditions and time managment
#### Features
- Winner declared after conquering all other cities
- Ending turns progress game calander

### GammaCiv
GammaCiv implements alternate unit actions
#### Features
- Settler units can construct citites
- Archers units can fortify therr positions
  

### DeltaCiv
DeltaCiv changes the default world layout
#### Features
- 16x16 world size
- Provides both players with cities at pre-defined locations

### EpsilonCiv
EpsilonCiv adds a new win condition along with unit attack capabilities
#### Features
- First player to win three attacks is declared victor
- Units are now capable of attacking other units or cities

### ZetaCiv
ZetaCiv implements victory condition alterations so that multiple can be used in a single version
#### Features
- Starts with initial AlphaCiv win condition
- If the game lasts more than 20 rounds the win condition is changed to EpsilonCiv's condition

### SemiCiv
SemiCiv combines all advanced requirements of each version into a singular version
### Features
- BetaCiv world aging
- GammaCiv unit actions
- DeltaCiv world layout
- EpsilonCiv Win condition and combat
  
