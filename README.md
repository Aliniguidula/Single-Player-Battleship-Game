🛳️ **Single-Player Battleship Game**

This is a one-player console-based Battleship game developed in Java as the final project for my Introduction to Software Development course. The game challenges the player to sink a fleet of computer-placed ships on a 10x10 ocean grid using as few shots as possible.

🧩 **Gameplay Overview**
- The computer randomly places 10 ships on the board, ensuring no adjacent ships.
- The player attempts to sink the fleet by entering row and column coordinates.
- After each shot, the board updates to display hits (X) and misses (-).
- When a ship is fully hit, the game announces: You just sunk a <SHIP_TYPE>
The game ends when all ships are sunk and shows the number of shots used.

🧱 **Core Classes**

BattleshipGame: Main class handling the game loop and user interaction.

Ocean: Maintains the game state, including ship placement and hit detection.

Ship (abstract): Base class for all ship types.
- Battleship
- Cruiser
- Destroyer
- Submarine
- EmptySea

OceanInterface: Defines methods to be implemented by Ocean.java.

✅ **Key Features**
- Full implementation of an object-oriented architecture with abstract and derived classes.
- Randomized ship placement algorithm ensuring valid board state.
- Clear and dynamic text-based board visualization.
- Game logic separated cleanly from I/O.
