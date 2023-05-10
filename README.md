# Getting started
1. The game can be started with the main method in the [MazeSolver](./src/main/java/com/example/mazegame1/MazeSolver.java).
2. [MazeAssembler](src/main/java/com/example/mazegame1/controller/MazeAssembler.java) allows my program to communicate with API of the [MazeGame](https://mazegame.plingot.com/swagger/index.html), so that the program can fetch that data of Room and perform the Move action for the player.
3. The [dto](src/main/java/com/example/mazegame1/dto) package contains serveral Data Transfer Objects, which they can store the data as Java Objects converted from JSON at the runtime.
4. Spring framework provides some very handy tools RestTemplate and Lombok, which are used for handling API calling and configuring DTO class respectively.

# Approach
The player starts with a root Room, then sequentially try to reach next Room with different direction which the current Room has. Meanwhile, we mark a Room as visited once the player gone through. Since there are the recursive processes for each reaching, the player always can go back to previous layer of recursion immediately by the False boolean value, if it doesn't find the exit or nowhere to go.
