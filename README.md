# Train Simulation Project

This project is a Java train-railway simulation built on the teaching framework
bundled in `OOPrailwaySim.jar`. It creates a railway layout, displays
locomotives and carriages, and accepts commands for controlling the simulation.

## Requirements

- Java Development Kit (JDK) 8 or newer
- A graphical environment capable of opening the simulation window

The framework and its image/layout resources are already included in this
repository, so no additional dependencies are required.

## Running the project

From the repository root, compile the source files:

```bash
mkdir -p build
javac -cp OOPrailwaySim.jar -d build src/*.java
```

Then start the simulation:

```bash
java -cp "build:OOPrailwaySim.jar" MainClass
```

On Windows, use a semicolon instead of a colon in the classpath:

```bat
java -cp "build;OOPrailwaySim.jar" MainClass
```

Keep the terminal open while the simulation is running. Type one command per
line and press Enter.

## Commands

| Command | Description |
| --- | --- |
| `about` | Display project author information |
| `start` | Start the simulation |
| `stop` | Stop the simulation |
| `reset` | Reset locomotives and simulation state |
| `addloco <x> <y>` | Add a locomotive at a coordinate |
| `addslowloco <x> <y>` | Add a slower locomotive |
| `attachcarriage <loco>` | Attach a carriage to a locomotive |
| `detachcarriage <loco>` | Detach a carriage from a locomotive |
| `speed <loco> <speed>` | Set a locomotive's speed |
| `crossing <crossing>` | Toggle a crossing (`0` or `1`) |
| `startgame` | Start the two-locomotive speed game |

Locomotive IDs start at `1`. Coordinates must refer to valid rail positions.

## Project structure

- `src/MainClass.java` — application entry point and command loop
- `src/MyRailway.java` — simulation setup, command handling, and game logic
- `src/NewLoco.java` — locomotive implementation
- `OOPrailwaySim.jar` — bundled simulation framework and resources
