# Getting started
1. The game can be started with the main method in the [MazeSolver](./src/main/java/com/example/mazegame1/MazeSolver.java).
2. [MazeAssembler](src/main/java/com/example/mazegame1/controller/MazeAssembler.java) allows my program to communicate with API of the [MazeGame](https://mazegame.plingot.com/swagger/index.html), so that the program can fetch that data of Room and peform the Move action for the player.
3. The [dto](src/main/java/com/example/mazegame1/dto) package contains serveral Data Transfer Objects, which they can store the data as Java Objects converted from JSON at the runtime.
